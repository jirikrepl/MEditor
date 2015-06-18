package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.RecentlyModifiedItemDAO;
import cz.mzk.editor.shared.rpc.RecentlyModifiedItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by rumanekm on 6/7/15.
 */
@Repository
public class RecentlyModifiedItemDAOImpl implements RecentlyModifiedItemDAO {
    @Override
    public boolean put(RecentlyModifiedItem toPut) throws DatabaseException {
        return false;
    }

    @Override
    public ArrayList<RecentlyModifiedItem> getItems(int nLatest, Long user_id) throws DatabaseException {
        return null;
    }
}
