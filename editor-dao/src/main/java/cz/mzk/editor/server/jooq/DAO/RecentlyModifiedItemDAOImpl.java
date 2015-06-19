package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.RecentlyModifiedItemDAO;
import cz.mzk.editor.shared.domain.DigitalObjectModel;
import cz.mzk.editor.shared.rpc.RecentlyModifiedItem;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by rumanekm on 6/7/15.
 */
@Repository
public class RecentlyModifiedItemDAOImpl implements RecentlyModifiedItemDAO {

    @Inject
    private DSLContext dsl;

    @Override
    public boolean put(RecentlyModifiedItem toPut) throws DatabaseException {

        return true;
    }

    @Override
    public ArrayList<RecentlyModifiedItem> getItems(int nLatest, Long user_id) throws DatabaseException {
        ArrayList<RecentlyModifiedItem> retList = new ArrayList<RecentlyModifiedItem>();

        //WUT
        String query = "SELECT o.uuid, o.name, d.description, o.model, MAX(o.timestamp) AS modified FROM (SELECT uuid, name, model, a.timestamp FROM (SELECT timestamp, digital_object_uuid FROM "
                + Constants.TABLE_CRUD_DIGITAL_OBJECT_ACTION
                + " WHERE type='"
                + Constants.CRUD_ACTION_TYPES.READ.getValue()
                + "' AND editor_user_id = (?)) a INNER JOIN (SELECT name, model, uuid, description FROM "
                + Constants.TABLE_DIGITAL_OBJECT
                + " WHERE state = 'true') o ON a.digital_object_uuid=o.uuid) o LEFT JOIN (SELECT * FROM "
                + Constants.TABLE_DESCRIPTION
                + " WHERE editor_user_id = (?)) d ON d.digital_object_uuid = o.uuid GROUP BY o.uuid, d.description, name, model ORDER by modified DESC LIMIT (?)";
        return retList;
    }
}
