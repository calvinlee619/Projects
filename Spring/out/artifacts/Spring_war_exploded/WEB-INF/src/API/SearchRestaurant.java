package API;

import db.DBConnection;
import db.MongoDBConnection;
import db.MySQLDBConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/**
 * Created by kangw on 7/4/16.
 */
public class SearchRestaurant extends HttpServlet{
    private static DBConnection connection = new MongoDBConnection();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String name = httpRequest.getParameter("name");
        JSONArray array = null;
        if (request.getParameterMap().containsKey("user_id")) {
            String userId = request.getParameter("user_id");
            array = connection.recommendRestaurants(userId);
        }
        RpcParser.writeOutput(response, array);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        JSONArray array = new JSONArray();
        String s = request.getParameter("user_id");
        if (request.getParameterMap().containsKey("user_id")
                && request.getParameterMap().containsKey("lat")
                && request.getParameterMap().containsKey("lon")) {
            String userId = request.getParameter("user_id");
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lon = Double.parseDouble(request.getParameter("lon"));
            // return some fake restaurants
            array = connection.searchRestaurants(userId, lat, lon);
        }
        RpcParser.writeOutput(response, array);
    }
}
