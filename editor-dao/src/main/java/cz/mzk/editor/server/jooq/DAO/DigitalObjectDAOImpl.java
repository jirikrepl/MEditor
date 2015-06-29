package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DAOUtilsImpl;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.DigitalObjectDAO;
import cz.mzk.editor.server.jooq.tables.CrudDoActionWithTopObject;
import cz.mzk.editor.server.jooq.tables.CrudTreeStructureAction;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by rumanekm on 6/9/15.
 */
@Repository
public class DigitalObjectDAOImpl implements DigitalObjectDAO {

    @Inject
    private DSLContext dsl;

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
        if (insertNewDigitalObject(newUuid,
                model,
                name,
                DAOUtilsImpl.directoryPathToRightFormat(input_queue_directory_path),
                newUuid,
                true)) {

            String oldPid =
                    (oldUuid.startsWith(Constants.FEDORA_UUID_PREFIX)) ? oldUuid
                            : Constants.FEDORA_UUID_PREFIX.concat(oldUuid);

            String newPid =
                    (newUuid.startsWith(Constants.FEDORA_UUID_PREFIX)) ? newUuid
                            : Constants.FEDORA_UUID_PREFIX.concat(newUuid);


            for (String lowerUuid : lowerObj) {
                String lowerObjPid = lowerUuid.startsWith(Constants.FEDORA_UUID_PREFIX) ? lowerUuid
                        : Constants.FEDORA_UUID_PREFIX.concat(lowerUuid);
                CrudDoActionWithTopObject topObjectTable = CrudDoActionWithTopObject.CRUD_DO_ACTION_WITH_TOP_OBJECT;
                dsl.update(topObjectTable.CRUD_DO_ACTION_WITH_TOP_OBJECT)
                        .set(topObjectTable.TOP_DIGITAL_OBJECT_UUID, newPid)
                        .where(topObjectTable.TOP_DIGITAL_OBJECT_UUID.eq(oldPid), topObjectTable.DIGITAL_OBJECT_UUID.eq(lowerObjPid));

            }
            return true;
        }
        return false;
    }



    @Override
    public void insertDOCrudAction(String tableName, String fkNameCol, Object foreignKey, Constants.CRUD_ACTION_TYPES type) throws DatabaseException {

    }
}
