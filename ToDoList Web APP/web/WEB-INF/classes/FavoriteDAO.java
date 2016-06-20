import org.genericdao.*;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by kangw on 11/27/15.
 */
public class FavoriteDAO extends GenericDAO<FavoriteBean> {
    public FavoriteDAO(ConnectionPool cp,String tableName) throws DAOException {
        super(FavoriteBean.class, tableName, cp);
    }

    public FavoriteBean[] getItems(int id) throws RollbackException {
        FavoriteBean[] items = match(MatchArg.equals("userId",id));

        Arrays.sort(items, new Comparator<FavoriteBean>() {
            @Override
            public int compare(FavoriteBean o1, FavoriteBean o2) {
                return o1.getId()-o2.getId();
            }
        });

        return items;
    }

    public void delete(int id, int ownerId) throws RollbackException {
        try {
            Transaction.begin();
            FavoriteBean p = read(id);

            if (p == null) {
                throw new RollbackException("Link does not exist: id=" + id);
            }

            if (ownerId != p.getUserId()) {
                throw new RollbackException("Link not owned by the owner id: "+ownerId);
            }

            delete(id);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
}
