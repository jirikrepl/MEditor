package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.DescriptionDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by rumanekm on 6/9/15.
 */
@Repository
public class DescriptionDAOImpl implements DescriptionDAO {
    @Override
    public String getCommonDescription(String uuid) throws DatabaseException {
        return null;
    }

    @Override
    public boolean putCommonDescription(String uuid, String description, Long user_id) throws DatabaseException {
        return false;
    }

    @Override
    public String getUserDescription(String digital_object_uuid) throws DatabaseException {
        return null;
    }

    @Override
    public boolean checkUserDescription(String digital_object_uuid, String description) throws DatabaseException {
        return false;
    }
}
