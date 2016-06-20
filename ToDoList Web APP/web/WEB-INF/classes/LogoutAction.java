import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by kangw on 12/11/15.
 */
public class LogoutAction extends Action {
    private UserDAO userDAO;

    public LogoutAction(Model model){
        userDAO = model.getUserDao();
    }
    @Override
    public String getName() {
        return "logout.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        HttpSession session = request.getSession();


        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            session.setAttribute("user",null);
            request.setAttribute("errors",null);
            request.setAttribute("form",null);
            return "login.jsp";
        } catch (RollbackException e) {
            e.printStackTrace();
            return "login.jsp";
        }


    }
}
