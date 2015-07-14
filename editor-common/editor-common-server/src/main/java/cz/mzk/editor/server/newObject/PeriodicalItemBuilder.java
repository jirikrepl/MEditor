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

import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.client.util.Constants.DATASTREAM_CONTROLGROUP;
import cz.mzk.editor.client.util.Constants.DATASTREAM_ID;
import cz.mzk.editor.server.fedora.utils.FoxmlUtils;
import cz.mzk.editor.shared.domain.DigitalObjectModel;
import cz.mzk.editor.shared.rpc.NewDigitalObject;

import javax.inject.Named;

/**
 * @author Jiri Kremser
 * @version 28.11.2011
 */
@Named
public class PeriodicalItemBuilder
        extends FoxmlBuilder {

    /**
     * @param uuid
     * @param label
     */
//    public PeriodicalItemBuilder(NewDigitalObject object) {
//        super(object);
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void decorateMODSStream() {
        Element modsCollection = FoxmlUtils.createModsCollectionEl();
        Namespace modsNs = Namespaces.mods;
        Element mods = modsCollection.addElement(new QName("mods", modsNs));
        mods.addAttribute("version", "3.3");
        mods.addAttribute("ID", getAditionalInfo());
        Element idUrn = mods.addElement(new QName("identifier", modsNs));
        idUrn.addAttribute("type", "urn");
        idUrn.addText(getUuid());

        Element titleInfo = mods.addElement(new QName("titleInfo", modsNs));

        addRootTitle(titleInfo);
        addRootSubtitle(titleInfo);

        if (isNotNullOrEmpty(getName())) {
            Element partName = titleInfo.addElement(new QName("partName", modsNs));
            partName.addText(getName());
        }

        if (isNotNullOrEmpty(getPartNumber())) {
            Element partNumber = titleInfo.addElement(new QName("partNumber", modsNs));
            partNumber.addText(getPartNumber());
        }

        if (isNotNullOrEmpty(getTypeOfResource())) {
            Element typeOfResourceEl = mods.addElement(new QName("typeOfResource", modsNs));
            typeOfResourceEl.addText(getTypeOfResource());
        }

        Element genre = mods.addElement(new QName("genre", modsNs));
        if (isNotNullOrEmpty(getType())) genre.addAttribute("type", getType());
        String levelName = getAditionalInfo().substring(0, getAditionalInfo().indexOf("_", 6));
        genre.addText(Constants.PERIODICAL_ITEM_LEVEL_NAMES.MAP.get(levelName));

        Element originInfo = mods.addElement(new QName("originInfo", modsNs));
        Element dateIssued = originInfo.addElement(new QName("dateIssued", modsNs));
        if (isNotNullOrEmpty(getDateOrIntPartName())) {
            dateIssued.addText(getDateOrIntPartName());
        } else {
            dateIssued.addAttribute("qualifier", "approximate");
        }

        addRootPublisher(originInfo);
        addRootPlace(originInfo);
        addRootLanguage(mods);
        addRootTopic(mods);
        Element locationEl = mods.addElement(new QName("location", modsNs));
        addLocation(locationEl);
        addRootPhysicalLocation(locationEl, false);

        Element physicalDescription = addRootPhysicalDescriptionForm(mods);
        if (isNotNullOrEmpty(getNoteOrIntSubtitle())) {
            Element noteEl = physicalDescription.addElement(new QName("note", modsNs));
            noteEl.addText(getNoteOrIntSubtitle());
        }

        Element part = mods.addElement(new QName("part", modsNs));
        part.addAttribute("type", "issue");

        addIdentifierUuid(mods, getUuid());

        appendDatastream(DATASTREAM_CONTROLGROUP.X, DATASTREAM_ID.BIBLIO_MODS, modsCollection, null, null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DigitalObjectModel getModel() {
        return DigitalObjectModel.PERIODICALITEM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createOtherStreams() {
        // TODO Auto-generated method stub

    }

}
