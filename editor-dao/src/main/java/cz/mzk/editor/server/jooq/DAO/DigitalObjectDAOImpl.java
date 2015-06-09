package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.DigitalObjectDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rumanekm on 6/9/15.
 */
@Repository
public class DigitalObjectDAOImpl implements DigitalObjectDAO {
    @Override
    public boolean deleteDigitalObject(String uuid, String model, String name, String topObjectUuid) throws DatabaseException {
        return false;
    }

    @Override
    public void updateState(List<String> objects, boolean state) throws DatabaseException {

    }

    @Override
    public boolean insertNewDigitalObject(String uuid, String model, String name, String input_queue_directory_path, String top_digital_object_uuid, boolean state) throws DatabaseException {
        return false;
    }

    @Override
    public void updateTopObjectTime(String uuid) throws DatabaseException {

    }

    @Override
    public boolean updateTopObjectUuid(String oldUuid, String newUuid, List<String> lowerObj, String model, String name, String input_queue_directory_path) throws DatabaseException {
        return false;
    }

    @Override
    public void insertDOCrudAction(String tableName, String fkNameCol, Object foreignKey, Constants.CRUD_ACTION_TYPES type) throws DatabaseException {

    }
}
