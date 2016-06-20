import com.sun.deploy.net.HttpRequest;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by kangw on 12/11/15.
 */
public class RegisterAction extends Action{
    private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
    private UserDAO userDAO;

    public RegisterAction(Model model){
        userDAO = model.getUserDao();
    }

    @Override
    public String getName() {
        return "register.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            RegisterForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            if (!form.isPresent()){
                return "register.jsp";
            }

            errors.addAll(form.getValidationErrors());
            if (errors.size()>0){
                return "register.jsp";
            }

            // create new user
            UserBean user = new UserBean();
            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setPassword(form.getPassword());
            userDAO.createAutoIncrement(user);

            session.setAttribute("user",user);
            return "manageList.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        }
    }

}
