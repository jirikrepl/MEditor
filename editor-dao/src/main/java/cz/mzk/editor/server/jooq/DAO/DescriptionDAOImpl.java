package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.DescriptionDAO;
import cz.mzk.editor.server.jooq.tables.DigitalObject;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rumanekm on 6/9/15.
 */
@Repository
public class DescriptionDAOImpl implements DescriptionDAO {

    @Inject
    private DSLContext dsl;

    @Override
    public String getCommonDescription(String uuid) throws DatabaseException {
        if (uuid == null) throw new NullPointerException("uuid");
        return dsl.select(DigitalObject.DIGITAL_OBJECT.DESCRIPTION).from(DigitalObject.DIGITAL_OBJECT)
                .where(DigitalObject.DIGITAL_OBJECT.UUID.eq(uuid)).fetchOne().value1();
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
