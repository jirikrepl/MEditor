package cz.mzk.editor.server.DAO;

import cz.mzk.editor.client.util.Constants;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by rumanekm on 6/19/15.
 */
@Repository
public class JooqDAOUtilsImpl implements DAOUtils {

    @Override
    public boolean checkDigitalObject(String uuid, String model, String name, String description, String input_queue_directory_path, boolean state, boolean closeCon) throws DatabaseException, SQLException {
        return true;
    }

    @Override
    public boolean updateDigitalObject(String uuid, String model, String name, String description, String input_queue_directory_path, boolean state, boolean closeCon) throws DatabaseException, SQLException {
        return false;
    }

    @Override
    public boolean insertDigitalObject(String uuid, String model, String name, String description, String input_queue_directory_path, boolean state, boolean closeCon) throws DatabaseException, SQLException {
        return false;
    }

    @Override
    public void insertCrudDigitalObjectAction(Long editor_user_id, Timestamp timestamp, String digital_object_uuid, Constants.CRUD_ACTION_TYPES type) throws DatabaseException {

    }

    @Override
    public Long insertEditorUser(String name, String surname, boolean state) throws DatabaseException {
        return null;
    }

    @Override
    public void insertImage(String identifier, Timestamp shown, String old_fs_path, String imagefile) throws DatabaseException {

    }

    @Override
    public void insertInputQueueItem(String path, String barcode, Boolean ingested) throws DatabaseException {

    }

    @Override
    public boolean checkInputQueue(String directory_path, String name, boolean closeCon) throws DatabaseException, SQLException {
        return false;
    }

    @Override
    public boolean updateInputQueue(String directory_path, String name, boolean closeCon) throws DatabaseException, SQLException {
        return false;
    }

    @Override
    public boolean insertInputQueue(String directory_path, String name, boolean closeCon) throws DatabaseException, SQLException {
        return false;
    }

    @Override
    public void insertOpenIdIdentity(long editor_user_id, String identity) throws DatabaseException {

    }

    @Override
    public boolean insertDescription(long editor_user_id, String digital_object_uuid, String description) throws DatabaseException {
        return false;
    }

    @Override
    public Long insertRequestToAdmin(long admin_editor_user_id, Constants.REQUESTS_TO_ADMIN_TYPES type, String object, String description, boolean solved) throws DatabaseException {
        return null;
    }

    @Override
    public Long insertSavedEditedObject(String digital_object_uuid, String file_name, String description, boolean state) throws DatabaseException {
        return null;
    }

    @Override
    public void insertCrudSavedEditedObjectAction(Long editor_user_id, Timestamp timestamp, Long saved_edited_object_id, Constants.CRUD_ACTION_TYPES type) throws DatabaseException {

    }

    @Override
    public void insertCrudRequestToAdminAction(long editor_user_id, Timestamp timestamp, long request_to_admin_id, Constants.CRUD_ACTION_TYPES type) throws DatabaseException {

    }

    @Override
    public Long insertTreeStructure(String barcode, String description, String name, String model, boolean state, String input_queue_directory_path) throws DatabaseException {
        return null;
    }

    @Override
    public void insertCrudTreeStructureAction(long editor_user_id, Timestamp timestamp, long tree_structure_id, Constants.CRUD_ACTION_TYPES type) throws DatabaseException {

    }

    @Override
    public void insertTreeStructureNode(long tree_structure_id, String prop_id, String prop_parent, String prop_name, String prop_picture_or_uuid, String prop_model_id, String prop_type, String prop_date_or_int_part_name, String prop_note_or_int_subtitle, String prop_part_number_or_alto, String prop_aditional_info_or_ocr, boolean prop_exist) throws DatabaseException {

    }

    @Override
    public Long getUserId(boolean closeCon) throws DatabaseException, SQLException {
        return null;
    }

    @Override
    public long getUsersId(String identifier, Constants.USER_IDENTITY_TYPES type, boolean closeCon) throws DatabaseException, SQLException {
        return 0;
    }

    @Override
    public String getName(Long key) throws DatabaseException {
        return null;
    }

    @Override
    public boolean hasUserRight(Constants.EDITOR_RIGHTS right) throws DatabaseException {
        return false;
    }
}
