/*
 * Metadata Editor
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */

package cz.mzk.editor.server.newObject;

import java.io.FileNotFoundException;

import java.util.List;
import java.util.logging.Logger;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.XPath;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.client.util.Constants.DATASTREAM_CONTROLGROUP;
import cz.mzk.editor.client.util.Constants.DATASTREAM_ID;
import cz.mzk.editor.server.fedora.utils.Dom4jUtils;
import cz.mzk.editor.shared.domain.DigitalObjectModel;
import cz.mzk.editor.shared.domain.FedoraNamespaces;
import cz.mzk.editor.shared.rpc.MarcSpecificMetadata;
import cz.mzk.editor.shared.rpc.NewDigitalObject;

import javax.inject.Named;

/**
 * @author Jiri Kremser
 * @version 17.11.2011
 */
@SuppressWarnings("unused")
@Named
public class PeriodicalBuilder
        extends FoxmlBuilder {

    final Logger logger = Logger.getLogger(PeriodicalBuilder.class.getName());
    private final XPath modsXpath = Dom4jUtils.createXPath("/mods:modsCollection/mods:mods");
    private final XPath locationXpath = Dom4jUtils
            .createXPath("/mods:modsCollection/mods:mods/mods:location");
    private final XPath copyInformationXpath = Dom4jUtils
            .createXPath("//mods:location/mods:holdingSimple/mods:copyInformation");
    private final XPath recordInfoXpath = Dom4jUtils.createXPath("//mods:recordInfo");

    /**
     * @throws FileNotFoundException
     *         final if some xsl template file cannot be found
     * @throws DocumentException
     *         final case of error in loading xsl template
     */
//    public PeriodicalBuilder(NewDigitalObject object) {
//        super(object);
//    }

    @SuppressWarnings("unchecked")
    private void updateDcDoc(Document dcDoc,
                             String pid,
                             String signature,
                             String sysno,
                             DigitalObjectModel model) {
        Element dcRootEl = dcDoc.getRootElement();
        Attribute schemaLoc = dcRootEl.attribute("schemaLocation");
        dcRootEl.remove(schemaLoc);
        Namespace xsi = DocumentHelper.createNamespace("xsi2", FedoraNamespaces.SCHEMA_NAMESPACE_URI);
        dcRootEl.add(xsi);
        dcRootEl.addAttribute(new QName("schemaLocation", xsi),
                              "http://www.openarchives.org/OAI/2.0/oai_dc/ http://www.openarchives.org/OAI/2.0/oai_dc.xsd");

        XPath typeXpath = Dom4jUtils.createXPath("/oai_dc:dc/dc:identifier");
        List<? extends Node> nodes = typeXpath.selectNodes(dcDoc);
        for (Node node : nodes) {
            node.detach();
        }
        Element idUuid = dcRootEl.addElement("dc:identifier");
        idUuid.addText(pid);

        for (Node node : nodes) {
            if (node.getText() != null && !"".equals(node.getText().trim())
                    && !node.getText().contains(Constants.FEDORA_UUID_PREFIX)) {
                Element temp = dcRootEl.addElement("dc:identifier");
                temp.addText(node.getText());
            }
        }

        if (signature != null) {
            Element idSignature = dcRootEl.addElement("dc:identifier");
            idSignature.addText("signature:" + signature);
        }
        if (sysno != null) {
            Element idSysno = dcRootEl.addElement("dc:identifier");
            idSysno.addText("sysno:" + sysno);
        }
        removeDcTypeElements(dcDoc);
        Element typeEl = dcRootEl.addElement("dc:type");
        typeEl.addText("model:" + model.getValue());
        Element rightsEl = dcRootEl.addElement("dc:rights");
        rightsEl.addText("policy:" + getPolicy().toString().toLowerCase());
        updateDcLanguages(dcDoc);
    }

    @SuppressWarnings("unchecked")
    private void removeDcTypeElements(Document doc) {
        XPath typeXpath = Dom4jUtils.createXPath("/oai_dc:dc/dc:type");
        List<? extends Node> nodes = typeXpath.selectNodes(doc);
        for (Node node : nodes) {
            node.detach();
        }
    }

    private void updateModsLanguages(Document modsDoc) {
        XPath languageXpath = Dom4jUtils.createXPath("/mods:mods/mods:language/mods:languageTerm");
        updateLanguages(modsDoc, languageXpath);
    }

    private void updateDcLanguages(Document dcDoc) {
        XPath languageXpath = Dom4jUtils.createXPath("/oai_dc:dc/dc:language");
        updateLanguages(dcDoc, languageXpath);
    }

    @SuppressWarnings("unchecked")
    private void updateLanguages(Document doc, XPath xpath) {
        List<? extends Node> nodes = xpath.selectNodes(doc);
        for (Node languageNode : nodes) {
            Element languageEl = (Element) languageNode;
            String originalLang = languageEl.getTextTrim();
            languageEl.clearContent();
            languageEl.addText(transformLanguage(originalLang));
        }
    }

    private void updateModsDoc(Document modsDoc, MarcSpecificMetadata marc, String uuid) {
        addRootUdcOrDdc((Element) modsXpath.selectSingleNode(modsDoc));
        addRootTopic((Element) modsXpath.selectSingleNode(modsDoc));
        addSysno(modsDoc, marc);
        addLinks(modsDoc);
        //addPublishment(modsDoc, marc);
        updateRecordInfo(modsDoc);
        addIdentifierUuid((Element) modsXpath.selectSingleNode(modsDoc), uuid);
    }

    private void addSysno(Document modsDoc, MarcSpecificMetadata marc) {
        String sysno = marc.getSysno();
        if (sysno != null) {
            Element modsEl = (Element) modsXpath.selectSingleNode(modsDoc);
            Element identifierEl = modsEl.addElement(new QName("identifier", Namespaces.mods));
            identifierEl.addAttribute("type", "sysno");
            identifierEl.addText(sysno);
        }
    }

    private void addLinks(Document modsDoc) {
        Element locationEl = (Element) locationXpath.selectSingleNode(modsDoc);
        if (locationEl != null) locationEl.detach();
        addLocation(((Element) modsXpath.selectSingleNode(modsDoc)).addElement(new QName("location",
                                                                                         Namespaces.mods)));
    }

    private String alephLink(MarcSpecificMetadata marc) {
        String sysno = marc.getSysno();
        String localBase = "";
        if (isNotNullOrEmpty(getBase())) {
            localBase = "&local_base=" + getBase();
        }
        return getAlephUrl() + "/F?func=direct&doc_number=" + sysno + localBase + "&format=999";
    }

    private void addPublishment(Document modsDoc, MarcSpecificMetadata marc) {
        Element modsEl = (Element) modsXpath.selectSingleNode(modsDoc);
        Element originInfoEl = modsEl.addElement(new QName("originInfo", Namespaces.mods));
        originInfoEl.addAttribute("transliteration", "publisher");
        addPublishmentPlace(originInfoEl, marc);
        addRootPublisher(originInfoEl);
        addPublishmentDate(originInfoEl, marc);
    }

    private void addPublishmentPlace(Element originInfoEl, MarcSpecificMetadata marc) {
        String place = marc.getPlace();
        if (place != null) {
            Element placeEl = originInfoEl.addElement(new QName("place", Namespaces.mods));
            Element placeTerm = placeEl.addElement(new QName("placeTerm", Namespaces.mods));
            placeTerm.addAttribute("type", "text");
            placeTerm.addText(place);
        }
    }

    private void addPublishmentDate(Element originInfoEl, MarcSpecificMetadata marc) {
        String dates = marc.getDateIssued();
        if (dates != null) {
            Element dateIssued = originInfoEl.addElement(new QName("dateIssued", Namespaces.mods));
            dateIssued.addText(dates);
        }
    }

    private void updateRecordInfo(Document modsDoc) {
        Element recordInfo = (Element) recordInfoXpath.selectSingleNode(modsDoc);
        if (recordInfo == null) {
            recordInfo = modsDoc.getRootElement().addElement(new QName("recordInfo", Namespaces.mods));
        }
        addRootRecordInfo(recordInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void decorateMODSStream() {
        if (getBundle() != null && getBundle().getMarc() != null) {
            updateModsDoc(getModsXmlContent(), getBundle().getMarc(), getUuid());
        }
        appendDatastream(DATASTREAM_CONTROLGROUP.X, DATASTREAM_ID.BIBLIO_MODS, getModsXmlContent()
                .getRootElement(), null, null);
    }

    @Override
    protected void decorateDCStream() {
        updateDcDoc(getDcXmlContent(), getPid(), getSignature(), getSysno(), getModel());
        appendDatastream(DATASTREAM_CONTROLGROUP.X,
                         DATASTREAM_ID.DC,
                         getDcXmlContent().getRootElement(),
                         null,
                         null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DigitalObjectModel getModel() {
        return DigitalObjectModel.PERIODICAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createOtherStreams() {
        if (getBundle() != null && getBundle().getMarc() != null) {
            setSysno(getBundle().getMarc().getSysno());
        }
    }
}
