import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by kangw on 12/11/15.
 */
public class ManageListAction extends Action {
    private UserDAO userDAO;
    private FavoriteDAO favoriteDAO;

    public ManageListAction(Model model){
        userDAO = model.getUserDao();
        favoriteDAO = model.getFavoriteDAO();
    }

    @Override
    public String getName() {
        return "manageList.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        int id = user.getId();

        FavoriteBean[] items;
        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            items = favoriteDAO.getItems(id);
            request.setAttribute("items",items);
            return "manageList.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "manageList.jsp";
        }
    }
}
