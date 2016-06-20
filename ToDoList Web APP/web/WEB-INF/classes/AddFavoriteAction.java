import com.sun.deploy.net.HttpRequest;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangw on 12/11/15.
 */
public class AddFavoriteAction extends Action {
    private UserDAO userDAO;
    private FavoriteDAO favoriteDAO;
    private FormBeanFactory<FavoriteForm> formBeanFactory = FormBeanFactory.getInstance(FavoriteForm.class);

    public AddFavoriteAction(Model model){
        favoriteDAO = model.getFavoriteDAO();
        userDAO = model.getUserDao();
    }

    @Override
    public String getName() {
        return "add.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        int id = user.getId();

        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            FavoriteForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            FavoriteBean[] items = favoriteDAO.getItems(id);
            request.setAttribute("items",items);

            errors.addAll(form.getValidationErrors());
            if (errors.size()>0) return "manageList.jsp";

            FavoriteBean favorite = new FavoriteBean();
            favorite.setUrl(form.getUrl());
            favorite.setComment(form.getComment());
            favorite.setCount(0);
            favorite.setUserId(id);
            favoriteDAO.createAutoIncrement(favorite);
            errors.add("url link has been added");
            return "manageList.do";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }

}
