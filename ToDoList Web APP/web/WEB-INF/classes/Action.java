import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by kangw on 12/11/15.
 */
public abstract class Action {

    public abstract String getName();

    public abstract String perform(HttpServletRequest request);

    //
    // Class methods to manage dispatching to Actions
    //
    private static HashMap<String,Action> hash = new HashMap<String,Action>();

    public static void addAction(Action action){
        synchronized (hash){
            if (hash.get(action.getName())!= null){
                throw new AssertionError("Two actions with the same name (" + action.getName() + "): " +
                        action.getClass().getName() + " and " + hash.get(action.getName()).getClass().getName());

            }
            hash.put(action.getName(),action);
        }
    }

    public static String perform(String name, HttpServletRequest request){
        Action a;
        synchronized (hash){
            a = hash.get(name);
        }

        if (a == null) return null;
        return a.perform(request);
    }

}
