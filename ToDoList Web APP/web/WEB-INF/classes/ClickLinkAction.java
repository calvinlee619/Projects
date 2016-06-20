import com.sun.deploy.net.HttpRequest;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangw on 12/11/15.
 */
public class ClickLinkAction extends Action {
    private FavoriteDAO favoriteDao;
    private UserDAO userDAO;

    public ClickLinkAction(Model model){
        favoriteDao = model.getFavoriteDAO();
        userDAO = model.getUserDao();
    }
    @Override
    public String getName() {
        return "click.do";
    }

    @Override
    public String perform(HttpServletRequest request) {
        List<String> infos = new ArrayList<String>();
        request.setAttribute("errors",infos);
        String para = request.getQueryString();


        try{
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            String item_id = para.substring(para.indexOf("?") + 1);

            // check errors
            if (item_id == null || item_id.length() ==0){
                infos.add("Id is required");
                return "error.jsp";
            }
            int id = Integer.parseInt(item_id);
            FavoriteBean favorite = favoriteDao.read(id);

            if (favorite == null){
                infos.add("No such item");
                return "error.jsp";
            }

            favorite.setCount(favorite.getCount() + 1);
            favoriteDao.update(favorite);
            String url = favorite.getUrl();

            // return the url that would be used for redirection
            // if the url is not begin with http://
            if (!url.startsWith("http://")){
                url = "http://" + url;
            }
            return url;
        }catch (NumberFormatException e){
            infos.add(e.getMessage());
            return "error.jsp";
        }catch (RollbackException e){
            infos.add(e.getMessage());
            return "error.jsp";
        }catch (StringIndexOutOfBoundsException e){
            infos.add(e.getMessage());
            return "error.jsp";
        }
    }

}
