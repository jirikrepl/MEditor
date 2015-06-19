package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.DAO.RequestDAO;
import cz.mzk.editor.shared.rpc.RequestItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by rumanekm on 6/19/15.
 */
@Repository
public class RequestDAOImpl implements RequestDAO {
    @Override
    public int addNewIdentifierRequest(String name, String identifier, Constants.USER_IDENTITY_TYPES idType) throws DatabaseException {
        return 0;
    }

    @Override
    public void solveRequest(long id) throws DatabaseException {

    }

    @Override
    public ArrayList<RequestItem> getAllRequests() throws DatabaseException {
        return null;
    }
}
