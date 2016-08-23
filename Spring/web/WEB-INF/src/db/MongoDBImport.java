package db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * Created by kangw on 7/22/16.
 */
public class MongoDBImport implements DBConnection {

    public static void main(String[] args) throws ParseException {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase(DBUtil.DB_NAME);
        db.getCollection("users").insertOne(
                new Document().append("first_name", "John")
                        .append("last_name", "Smith")
                        .append("password", "3229c1097c00d497a0fd282d586be050")
                        .append("user_id", "1111")
        );
        mongoClient.close();
//        MongoClient mongoClient = new MongoClient();
//        MongoDatabase db = mongoClient.getDatabase(DBUtil.DB_NAME);
//        db.getCollection("users").insertOne(
//                new Document()
//                        .append("first_name", "John")
//                        .append("last_name", "Smith")
//                        .append("password", "3229c1097c00d497a0fd282d586be050")
//                        .append("user_id", "1111"));
//        mongoClient.close();
    }

    @Override
    public void close() {

    }

    @Override
    public void setVisitedRestaurants(String userId, List<String> businessIds) {

    }

    @Override
    public void unsetVisitedRestaurants(String userId, List<String> businessIds) {

    }

    @Override
    public Set<String> getVisitedRestaurants(String userId) {
        return null;
    }

    @Override
    public JSONObject getRestaurantsById(String businessId, boolean isVisited) {
        return null;
    }

    @Override
    public JSONArray recommendRestaurants(String userId) {
        return null;
    }

    @Override
    public Set<String> getCategories(String businessId) {
        return null;
    }

    @Override
    public Set<String> getBusinessId(String category) {
        return null;
    }

    @Override
    public JSONArray searchRestaurants(String userId, double lat, double lon) {
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
