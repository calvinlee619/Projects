package API;

import db.DBConnection;
import db.MongoDBConnection;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kangw on 7/29/16.
 */
public class Recommendation extends HttpServlet {
    private static DBConnection connection = new MongoDBConnection();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/Recommendation.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        JSONArray array = new JSONArray();
        if (request.getParameterMap().containsKey("name")) {
            String searchName = request.getParameter("name");
            String defaultLocation  = "San Diego";
            String defaultUserId = "1111";
            if (request.getParameter("location")!= null){
                defaultLocation = request.getParameter("location");
            }
            if (request.getParameter("user_id") != null){
                defaultUserId = request.getParameter("user_id");
            }
            // return some fake restaurants
            array = connection.searchRestaurants(searchName,defaultLocation,defaultUserId);
        }
        RpcParser.writeOutput(response, array);
    }
}
