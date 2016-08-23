package API;

import db.DBConnection;
import db.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by kangw on 7/15/16.
 */
public class VisitHistory extends HttpServlet {
    private static final DBConnection connection = new MongoDBConnection();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // allow access only if session exists
                                           /*
			if (!RpcParser.sessionValid(request, connection)) {
				response.setStatus(403);
				return;
			}*/
            JSONObject input = RpcParser.parseInput(request);
            Map<String, String[]> map = request.getParameterMap();
            String flag = request.getParameter("user_id");
            if (input.has("user_id") && input.has("visited")) {
                String userId = (String) input.get("user_id");
                JSONArray array = (JSONArray) input.get("visited");
                List<String> visitedRestaurants = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    String businessId = (String) array.get(i);
                    visitedRestaurants.add(businessId);
                }
                connection.setVisitedRestaurants(userId, visitedRestaurants);
                RpcParser.writeOutput(response, new JSONObject().put("status", "OK"));
            } else {
                RpcParser.writeOutput(response, new JSONObject().put("status", "InvalidParameter"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
