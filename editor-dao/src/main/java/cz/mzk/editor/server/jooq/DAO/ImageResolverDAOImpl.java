package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.ImageResolverDAO;
import cz.mzk.editor.server.jooq.tables.Image;
import cz.mzk.editor.shared.rpc.ImageItem;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.*;
import org.jooq.Record2;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/18/15.
 */
@Repository
public class ImageResolverDAOImpl implements ImageResolverDAO {

    @Inject
    private DSLContext dsl;

    @Override
    public void insertItems(List<ImageItem> toInsert) throws DatabaseException {

    }

    @Override
    public ArrayList<String> resolveItems(List<String> oldJpgFsPaths) throws DatabaseException {
        if (oldJpgFsPaths == null) throw new NullPointerException("oldJpgFsPaths");
        ArrayList<String> ret = new ArrayList<String>(oldJpgFsPaths.size());

        if (!oldJpgFsPaths.isEmpty()) {

            for (String oldJpgFsPath : oldJpgFsPaths) {

                ret.add(resolveItem(oldJpgFsPath));

            }
        }

        return ret;
    }

    private String resolveItem(String oldJpgFsPath) {
        if (oldJpgFsPath == null || "".equals(oldJpgFsPath)) throw new NullPointerException("oldJpgFsPath");

        Record2<String, String> rs = dsl.select(Image.IMAGE.IDENTIFIER, Image.IMAGE.OLD_FS_PATH)
                .from(Image.IMAGE).where(Image.IMAGE.OLD_FS_PATH.eq(oldJpgFsPath)).fetchOne();

        String identifier = null;
        String ret = null;
        if (rs != null) {
            identifier = rs.value1();
            ret = rs.value2();
        }

        if (ret != null) {
            File img = new File(ret);
            if (identifier != null && img.exists() && img.length() > 0) {
                dsl.update(Image.IMAGE).set(Image.IMAGE.SHOWN, currentTimestamp())
                        .where(Image.IMAGE.IDENTIFIER.eq(identifier)).execute();
                return ret;
            }
        }

        return null;
    }

    @Override
    public ArrayList<String> cacheAgeingProcess(int numberOfDays) throws DatabaseException {
        return null;
    }

    @Override
    public String getOldJpgFsPath(String imageFile) throws DatabaseException {
        return null;
    }

    @Override
    public String getNewImageFilePath(String identifier) throws DatabaseException {
        return null;
    }
}
