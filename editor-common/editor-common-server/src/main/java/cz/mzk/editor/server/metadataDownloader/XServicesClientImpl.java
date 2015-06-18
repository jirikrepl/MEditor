package cz.mzk.editor.server.metadataDownloader;

import cz.mzk.editor.server.fedora.utils.BiblioModsUtils;
import cz.mzk.editor.server.fedora.utils.Dom4jUtils;
import cz.mzk.editor.server.newObject.MarcDocument;
import cz.mzk.editor.server.util.XMLUtils;
import cz.mzk.editor.shared.rpc.DublinCore;
import cz.mzk.editor.shared.rpc.MarcSpecificMetadata;
import cz.mzk.editor.shared.rpc.MetadataBundle;
import cz.mzk.editor.server.mods.ModsCollection;
import cz.mzk.editor.server.mods.ModsType;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Namespace;
import org.dom4j.Visitor;
import org.xml.sax.SAXException;

import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by rumanekm on 27.8.14.
 */
@Named
public class XServicesClientImpl implements XServicesClient {



    MarcConvertor marcConvertor = new MarcConvertor();

    @Override
    public ArrayList<MetadataBundle> search(String sysno, String base) {
        ArrayList<MetadataBundle> retList = new ArrayList<MetadataBundle>();

        Client client = ClientBuilder.newClient();
        String marc = client.target("http://aleph.nkp.cz/X?op=ill_get_doc&doc_number=" + sysno + "&library=" + base).request().get(String.class);


        Document documentMarc = null;
        try {
            documentMarc = DocumentHelper.parseText(marc);
            Namespace oldNs = Namespace.get("http://www.loc.gov/MARC21/slim/");
            Namespace newNs = Namespace.get("marc21", "http://www.loc.gov/MARC21/slim");
            Visitor visitor = new NamespaceChangingVisitor(oldNs, newNs);
            documentMarc.accept(visitor);
            MarcDocument marcDoc = new MarcDocument(documentMarc, "/ill-get-doc/");

            DublinCore dc = marcConvertor.marc2dublincore(marcDoc);

            ModsType modsType = marcConvertor.marc2mods(marcDoc);
            final ModsCollection modsCollection = new ModsCollection();
            modsCollection.getMods().addAll(Arrays.<ModsType> asList(modsType));

            MarcSpecificMetadata marcSpecific =
                    new MarcSpecificMetadata(marcDoc.findSysno(),
                            base,
                            marcDoc.find040a(),
                            marcDoc.find080a(),
                            marcDoc.find650a(),
                            marcDoc.find260aCorrected(),
                            marcDoc.find260bCorrected(),
                            marcDoc.find260c(),
                            marcDoc.find910b());

            MetadataBundle bundle = new MetadataBundle(dc, BiblioModsUtils.toModsClient(modsCollection), marcSpecific);
            retList.add(bundle);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retList;
    }
}
