package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.ImageResolverDAO;
import cz.mzk.editor.server.jooq.tables.Image;
import cz.mzk.editor.shared.rpc.ImageItem;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.*;
import org.jooq.Record2;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import static org.jooq.impl.DSL.currentTimestamp;
import javax.inject.Inject;
import java.io.File;
import java.sql.Timestamp;
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
        if (toInsert == null) throw new NullPointerException("toInsert");

        for (ImageItem item : toInsert) {
            Image imageTable = Image.IMAGE;
            dsl.delete(imageTable).where(imageTable.IDENTIFIER.eq(item.getIdentifier())).execute();
            dsl.insertInto(imageTable)
                    .set(imageTable.IDENTIFIER, item.getIdentifier())
                    .set(imageTable.IMAGEFILE, item.getJpeg2000FsPath())
                    .set(imageTable.OLD_FS_PATH, item.getJpgFsPath())
                    .set(imageTable.SHOWN, currentTimestamp()).execute();
        }
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

        Record2<String, String> rs = dsl.select(Image.IMAGE.IDENTIFIER, Image.IMAGE.IMAGEFILE)
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
