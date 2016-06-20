import com.sun.deploy.net.HttpRequest;
import java.util.*;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by kangw on 12/11/15.
 */
public class LoginAction extends Action {
    private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
    private UserDAO userDAO;
    private FavoriteDAO favoriteDAO;

    public LoginAction(Model model){
        userDAO = model.getUserDao();
        favoriteDAO = model.getFavoriteDAO();
    }

    @Override
    public String getName() {
        return "login.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null){
            return "manageList.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            if (!form.isPresent()){
                return "login.jsp";
            }

            errors.addAll(form.getValidationErrors());
            if (errors.size()>0){
                return "login.jsp";
            }

            UserBean[] users = userDAO.match(MatchArg.equals("email",form.getEmail()));

            if (users == null || users.length==0){
                errors.add("Email does not exist");
                return "login.jsp";
            }

            UserBean user = users[0];
            if (!user.getPassword().equals(form.getPassword())){
                errors.add("Password is not correct");
                return "login.jsp";
            }

            // Attach (this copy of) the user bean to the session
            session.setAttribute("user",user);

            // If redirectTo is null, redirect to the "manageList" action
            int id = user.getId();
            FavoriteBean[] items = favoriteDAO.getItems(id);
            request.setAttribute("items",items);
            return "manageList.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }

    }

}
