import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by kangw on 12/12/15.
 */
public class DeleteFavoriteAction extends Action{
    private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);
    private UserDAO userDAO;
    private FavoriteDAO favoriteDAO;

    public DeleteFavoriteAction(Model model){
        favoriteDAO = model.getFavoriteDAO();
        userDAO = model.getUserDao();
    }
    @Override
    public String getName() {
        return "delete.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            IdForm form = formBeanFactory.create(request);

            UserBean user = (UserBean) request.getSession().getAttribute("user");

            int id = form.getIdAsInt();
            favoriteDAO.delete(id,user.getId());

            return "manageList.do";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
