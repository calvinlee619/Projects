import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * Created by kangw on 12/11/15.
 */
public class Model {
    private UserDAO userDao;
    private FavoriteDAO favoriteDAO;

    public Model (ServletConfig config) throws DAOException, ServletException {
        try{
            String jdbcDriver = config.getInitParameter("jdbcDriver");
            String jdbcURL = config.getInitParameter("jdbcURL");

            ConnectionPool connection = new ConnectionPool(jdbcDriver,jdbcURL);

            userDao = new UserDAO(connection, "kangw_user");
            favoriteDAO = new FavoriteDAO(connection,"kangw_favorite");

            UserBean[] users = userDao.match();
            if ( users == null|| users.length == 0){
                addUser("789@google.com","Mengting","Thsai","123");
                addUser("456@google.com","Jeff","Eppinger","123");
                addUser("334@google.com","Brack","Obama","123");
            }

        }catch (DAOException e){
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }

    }

    public UserDAO getUserDao() {return userDao;}
    public FavoriteDAO getFavoriteDAO() {return favoriteDAO;}

    private void addUser(String email,String firstName,String lastName,String password) throws RollbackException {

        UserBean newUser = new UserBean();
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(password);
        userDao.createAutoIncrement(newUser);

        int id = newUser.getId();
        addFavoriteLink("www.kangwu-web.com","welcome to my website",id);
        addFavoriteLink("www.kangwu-web.com","a nice website",id);
        addFavoriteLink("www.kangwu-web.com","good to have a look",id);
        addFavoriteLink("www.google.com","to connect with others",id);
    }

    private void addFavoriteLink(String url, String comment,int id) throws RollbackException {

        FavoriteBean f = new FavoriteBean();
        f.setUrl(url);
        f.setComment(comment);
        f.setCount(0);
        f.setUserId(id);
        favoriteDAO.createAutoIncrement(f);
    }
}
