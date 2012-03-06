/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseTitleInfoTypeClient.
 */
public class BaseTitleInfoTypeClient
        implements IsSerializable {

    /** The title. */
    protected List<String> title;

    /** The sub title. */
    protected List<String> subTitle;

    /** The part number. */
    protected List<String> partNumber;

    /** The part name. */
    protected List<String> partName;

    /** The non sort. */
    protected List<String> nonSort;

    /** The display label. */
    protected String displayLabel;

    /** The id. */
    protected String id;

    /** The authority. */
    protected String authority;

    /** The xlink. */
    protected String xlink;

    /** The xml lang. */
    protected String xmlLang;

    /** The lang. */
    protected String lang;

    /** The script. */
    protected String script;

    /** The transliteration. */
    protected String transliteration;

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public List<String> getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title
     *        the new title
     */
    public void setTitle(List<String> title) {
        this.title = title;
    }

    /**
     * Gets the sub title.
     * 
     * @return the sub title
     */
    public List<String> getSubTitle() {
        return subTitle;
    }

    /**
     * Sets the sub title.
     * 
     * @param subTitle
     *        the new sub title
     */
    public void setSubTitle(List<String> subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * Gets the part number.
     * 
     * @return the part number
     */
    public List<String> getPartNumber() {
        return partNumber;
    }

    /**
     * Sets the part number.
     * 
     * @param partNumber
     *        the new part number
     */
    public void setPartNumber(List<String> partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Gets the part name.
     * 
     * @return the part name
     */
    public List<String> getPartName() {
        return partName;
    }

    /**
     * Sets the part name.
     * 
     * @param partName
     *        the new part name
     */
    public void setPartName(List<String> partName) {
        this.partName = partName;
    }

    /**
     * Gets the non sort.
     * 
     * @return the non sort
     */
    public List<String> getNonSort() {
        return nonSort;
    }

    /**
     * Sets the non sort.
     * 
     * @param nonSort
     *        the new non sort
     */
    public void setNonSort(List<String> nonSort) {
        this.nonSort = nonSort;
    }

    /**
     * Gets the display label.
     * 
     * @return the display label
     */
    public String getDisplayLabel() {
        return displayLabel;
    }

    /**
     * Sets the display label.
     * 
     * @param displayLabel
     *        the new display label
     */
    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *        the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the authority.
     * 
     * @return the authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Sets the authority.
     * 
     * @param authority
     *        the new authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * Gets the xlink.
     * 
     * @return the xlink
     */
    public String getXlink() {
        return xlink;
    }

    /**
     * Sets the xlink.
     * 
     * @param xlink
     *        the new xlink
     */
    public void setXlink(String xlink) {
        this.xlink = xlink;
    }

    /**
     * Gets the xml lang.
     * 
     * @return the xml lang
     */
    public String getXmlLang() {
        return xmlLang;
    }

    /**
     * Sets the xml lang.
     * 
     * @param xmlLang
     *        the new xml lang
     */
    public void setXmlLang(String xmlLang) {
        this.xmlLang = xmlLang;
    }

    /**
     * Gets the lang.
     * 
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the lang.
     * 
     * @param lang
     *        the new lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Gets the script.
     * 
     * @return the script
     */
    public String getScript() {
        return script;
    }

    /**
     * Sets the script.
     * 
     * @param script
     *        the new script
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * Gets the transliteration.
     * 
     * @return the transliteration
     */
    public String getTransliteration() {
        return transliteration;
    }

    /**
     * Sets the transliteration.
     * 
     * @param transliteration
     *        the new transliteration
     */
    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

}
