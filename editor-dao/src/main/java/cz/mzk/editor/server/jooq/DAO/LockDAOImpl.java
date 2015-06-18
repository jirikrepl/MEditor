package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.LockDAO;
import org.springframework.stereotype.Repository;

import javax.inject.Named;

/**
 * Created by rumanekm on 6/18/15.
 */
@Repository
public class LockDAOImpl implements LockDAO {
    @Override
    public boolean lockDigitalObject(String uuid, String description, boolean insert) throws DatabaseException {
        return false;
    }

    @Override
    public long getLockOwnersID(String uuid) throws DatabaseException {
        return 0;
    }

    @Override
    public String getDescription(String uuid) throws DatabaseException {
        return null;
    }

    @Override
    public String[] getTimeToExpirationLock(String uuid) throws DatabaseException {
        return new String[0];
    }
}
