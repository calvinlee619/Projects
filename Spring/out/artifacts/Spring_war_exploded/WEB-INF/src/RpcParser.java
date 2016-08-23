import org.json.*;

import javax.servlet.http.*;
import java.io.*;

/**
 * Created by kangw on 7/8/16.
 */
public class RpcParser {
    public static JSONObject parseInput(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            reader.close();
            return new JSONObject(jb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeOutput(HttpServletResponse response, JSONObject obj) {
        try {
            response.setContentType("application/json");
            response.addHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            out.print(obj);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeOutput(HttpServletResponse response, JSONArray array) {
        try {
            response.setContentType("application/json");
            response.addHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            out.print(array);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
