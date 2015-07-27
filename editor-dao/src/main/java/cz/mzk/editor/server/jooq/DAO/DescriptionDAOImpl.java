package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.DescriptionDAO;
import cz.mzk.editor.server.jooq.tables.Description;
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
        if (uuid == null) throw new NullPointerException("uuid");
        if (description == null) throw new NullPointerException("description");

        dsl.update(DigitalObject.DIGITAL_OBJECT)
                .set(DigitalObject.DIGITAL_OBJECT.UUID, uuid)
                .set(DigitalObject.DIGITAL_OBJECT.DESCRIPTION, description).execute();

        return true;
    }

    public String getUserDescription(String uuid, Long userId) throws DatabaseException {
        if (uuid == null) throw new NullPointerException("uuid");
        if (userId == null) throw new NullPointerException("user");

        Description descriptionTable = Description.DESCRIPTION;

        return dsl.select(descriptionTable.DESCRIPTION_).from(descriptionTable)
                .where(descriptionTable.DIGITAL_OBJECT_UUID.eq(uuid))
                .and(descriptionTable.EDITOR_USER_ID.eq(userId.intValue()))
                .fetchOne().value1();
    }



    @Override
    public boolean putUserDescription(String uuid, String description, Long userId) throws DatabaseException {
        if (uuid == null) throw new NullPointerException("uuid");
        if (userId == null) throw new NullPointerException("user");

        Description descriptionTable = Description.DESCRIPTION;

        return false;
    }




}
