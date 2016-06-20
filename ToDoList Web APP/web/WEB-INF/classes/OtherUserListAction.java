import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangw on 12/12/15.
 */
public class OtherUserListAction extends Action {
    private UserDAO userDAO;
    private FavoriteDAO favoriteDAO;

    public OtherUserListAction(Model model){
        userDAO = model.getUserDao();
        favoriteDAO = model.getFavoriteDAO();
    }

    @Override
    public String getName() {
        return "otherUser.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        String para = request.getQueryString();

        FavoriteBean[] items;
        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            String item_id = para.substring(para.indexOf("?")+1);

            // check errors
            if (item_id == null || item_id.length() ==0){
                errors.add("The user does not exist");
                return "otherUser.jsp";
            }

            int id = Integer.parseInt(item_id);
            items = favoriteDAO.getItems(id);
            request.setAttribute("items",items);

            UserBean user = userDAO.read(id);
            request.setAttribute("otherUser",user);
            return "otherUser.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "otherUser.jsp";
        } catch (NumberFormatException e){
            errors.add(e.getMessage());
            return "otherUser.jsp";
        } catch (StringIndexOutOfBoundsException e){
            errors.add(e.getMessage());
            return "otherUser.jsp";
        }
    }
}
