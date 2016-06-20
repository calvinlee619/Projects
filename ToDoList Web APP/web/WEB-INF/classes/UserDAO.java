import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import java.util.Arrays;

/**
 * Created by kangw on 11/27/15.
 */
public class UserDAO extends GenericDAO<UserBean> {

    public UserDAO(ConnectionPool cp,String tableName) throws DAOException {
        super(UserBean.class,tableName,cp);
    }

    public UserBean[] getUsers() throws RollbackException {
        UserBean[] users = match();
        if (users.length>0) {
            Arrays.sort(users); // We want them sorted by last and first names (as per User.compareTo());
        }
        return users;
    }
}
