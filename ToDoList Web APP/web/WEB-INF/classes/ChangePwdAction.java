import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangw on 12/12/15.
 */
public class ChangePwdAction extends Action{
    private UserDAO userDAO;
    private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangePwdForm.class);

    public ChangePwdAction(Model model){
        userDAO = model.getUserDao();
    }

    @Override
    public String getName() {
        return "change-pwd.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            ChangePwdForm form = formBeanFactory.create(request);

            if (!form.isPresent()){
                return "changePwd.jsp";
            }

            errors.addAll(form.getValidationErrors());
            if (errors.size()>0){
                return "changePwd.jsp";
            }

            // update the password
            UserBean user = (UserBean) session.getAttribute("user");
            user.setPassword(form.getPassword1());
            userDAO.update(user);

            errors.add("Password change successfully");
            return "changePwd.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "changePwd.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "changePwd.jsp";
        }
    }
}
