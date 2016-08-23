package db;

import API.YelpAPI;
import model.Restaurant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Restaurant;

/**
 * Created by kangw on 7/8/16.
 */
public class MySQLDBConnection implements DBConnection {
    private Connection conn = null;
    private static final int MAX_RECOMMENDED_RESTAURANTS = 10;

    public MySQLDBConnection() {
        this(DBUtil.URL);
    }

    public MySQLDBConnection(String url) {
        try {
            // Forcing the class representing the MySQL driver to load and
            // initialize.
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) { /* ignored */
            }
        }
    }


    @Override
    public void setVisitedRestaurants(String userId, List<String> businessIds) {
        String sql1 = "INSERT INTO history (user_id,business_id) VALUES (?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql1);
            for (int i=0;i<businessIds.size();i++){
                statement.setString(1, userId);
                statement.setString(2,businessIds.get(i));
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        String query = "INSERT INTO history (user_id, business_id) VALUES (?, ?)";
//        try {
//            PreparedStatement statement = conn.prepareStatement(query);
//            for (String businessId : businessIds) {
//                statement.setString(1,  userId);
//                statement.setString(2, businessId);
//                statement.execute();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void unsetVisitedRestaurants(String userId, List<String> businessIds) {
        String sql = "DELETE FROM history where user_id =? and business_id = ?";
        try {
            PreparedStatement statement =  conn.prepareStatement(sql);
            for (String s:businessIds){
                statement.setString(1,userId);
                statement.setString(2,s);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Set<String> getVisitedRestaurants(String userId) {
        Set<String> visitedRestaurants = new HashSet<String>();
        try {
            String sql = "SELECT business_id from history WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String visitedRestaurant = rs.getString("business_id");
                visitedRestaurants.add(visitedRestaurant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return visitedRestaurants;
    }

    @Override
    public JSONObject getRestaurantsById(String businessId, boolean isVisited) {
        try {
            String sql = "SELECT * from restaurants where business_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, businessId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Restaurant restaurant = new Restaurant(
                        rs.getString("business_id"), rs.getString("name"),
                        rs.getString("categories"), rs.getString("city"),
                        rs.getString("state"), rs.getFloat("stars"),
                        rs.getString("full_address"), rs.getFloat("latitude"),
                        rs.getFloat("longitude"), rs.getString("image_url"),
                        rs.getString("url"));
                JSONObject obj = restaurant.toJSONObject();
                obj.put("is_visited", isVisited);
                return obj;
            }
        } catch (Exception e) { /* report an error */
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public JSONArray recommendRestaurants(String userId) {
        try {
            if (conn == null) {
                return null;
            }

            Set<String> visitedRestaurants = getVisitedRestaurants(userId);
            Set<String> allCategories = new HashSet<>();// why hashSet?
            for (String restaurant : visitedRestaurants) {
                allCategories.addAll(getCategories(restaurant));
            }
            Set<String> allRestaurants = new HashSet<>();
            for (String category : allCategories) {
                Set<String> set = getBusinessId(category);
                allRestaurants.addAll(set);
            }
            Set<JSONObject> diff = new HashSet<>();
            int count = 0;
            for (String businessId : allRestaurants) {
                // Perform filtering
                if (!visitedRestaurants.contains(businessId)) {
                    diff.add(getRestaurantsById(businessId, false));
                    count++;
                    if (count >= MAX_RECOMMENDED_RESTAURANTS) {
                        break;
                    }
                }
            }
            return new JSONArray(diff);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public Set<String> getCategories(String businessId) {
        String sql = "SELECT Categories FROM restaurants where business_id = ?";
        HashSet<String> visitedCategories = new HashSet<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,businessId);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                String[] tmpCategories = result.getString("categories").trim().split(",");
                for (String s:tmpCategories){
                    visitedCategories.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitedCategories;
    }

    @Override
    public Set<String> getBusinessId(String category) {
        String sql = "SELECT business_id FROM restaurants where categories LIKE ?";
        HashSet<String> businessSet = new HashSet<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + category + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String businessId = result.getString("business_id").trim();
                businessSet.add(businessId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return businessSet;
    }

    @Override
    public JSONArray searchRestaurants(String userId, double lat, double lon) {
        try {
            YelpAPI api = new YelpAPI();
            JSONObject response = new JSONObject(
                    api.searchForBusinessesByLocation(lat, lon));
            JSONArray array = (JSONArray) response.get("businesses");

            List<JSONObject> list = new ArrayList<JSONObject>();
            Set<String> visited = getVisitedRestaurants(userId);

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Restaurant restaurant = new Restaurant(object);
                String businessId = restaurant.getBusinessId();
                String name = restaurant.getName();
                String categories = restaurant.getCategories();
                String city = restaurant.getCity();
                String state = restaurant.getState();
                String fullAddress = restaurant.getFullAddress();
                double stars = restaurant.getStars();
                double latitude = restaurant.getLatitude();
                double longitude = restaurant.getLongitude();
                String imageUrl = restaurant.getImageUrl();
                String url = restaurant.getUrl();
                JSONObject obj = restaurant.toJSONObject();
                if (visited.contains(businessId)) {
                    object.put("is_visited", true);
                } else {
                    object.put("is_visited", false);
                }
                String sql = "INSERT IGNORE INTO restaurants VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, businessId);
                statement.setString(2, name);
                statement.setString(3, categories);
                statement.setString(4, city);
                statement.setString(5, state);
                statement.setDouble(6, stars);
                statement.setString(7, fullAddress);
                statement.setDouble(8, latitude);
                statement.setDouble(9, longitude);
                statement.setString(10, imageUrl);
                statement.setString(11, url);
                statement.execute();
                list.add(obj);
            }
            return new JSONArray(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public JSONArray searchRestaurants(String searchName, String location, String userId) {
        return null;
    }

    @Override
    public Boolean verifyLogin(String userId, String password) {
        return null;
    }

    @Override
    public String getFirstLastName(String userId) {
        return null;
    }
}
