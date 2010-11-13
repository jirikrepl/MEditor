//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

import cz.fi.muni.xkremser.editor.server.mods.HoldingSimpleType;
import cz.fi.muni.xkremser.editor.server.mods.PhysicalLocationType;
import cz.fi.muni.xkremser.editor.server.mods.UrlType;

public class LocationTypeClient implements IsSerializable {

	protected List<PhysicalLocationTypeClient> physicalLocation;
	protected List<String> shelfLocator;
	protected List<UrlTypeClient> url;
	protected HoldingSimpleTypeClient holdingSimple;
	protected ExtensionTypeClient holdingExternal;

	/**
	 * Gets the value of the physicalLocation property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the physicalLocation property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPhysicalLocation().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link PhysicalLocationType }
	 * 
	 * 
	 */
	public List<PhysicalLocationTypeClient> getPhysicalLocation() {
		if (physicalLocation == null) {
			physicalLocation = new ArrayList<PhysicalLocationTypeClient>();
		}
		return this.physicalLocation;
	}

	/**
	 * Gets the value of the shelfLocator property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the shelfLocator property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getShelfLocator().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getShelfLocator() {
		if (shelfLocator == null) {
			shelfLocator = new ArrayList<String>();
		}
		return this.shelfLocator;
	}

	/**
	 * Gets the value of the url property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the url property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getUrl().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link UrlType }
	 * 
	 * 
	 */
	public List<UrlTypeClient> getUrl() {
		if (url == null) {
			url = new ArrayList<UrlTypeClient>();
		}
		return this.url;
	}

	/**
	 * Gets the value of the holdingSimple property.
	 * 
	 * @return possible object is {@link HoldingSimpleType }
	 * 
	 */
	public HoldingSimpleTypeClient getHoldingSimple() {
		return holdingSimple;
	}

	/**
	 * Sets the value of the holdingSimple property.
	 * 
	 * @param value
	 *          allowed object is {@link HoldingSimpleType }
	 * 
	 */
	public void setHoldingSimple(HoldingSimpleTypeClient value) {
		this.holdingSimple = value;
	}

	/**
	 * Gets the value of the holdingExternal property.
	 * 
	 * @return possible object is {@link ExtensionType }
	 * 
	 */
	public ExtensionTypeClient getHoldingExternal() {
		return holdingExternal;
	}

	/**
	 * Sets the value of the holdingExternal property.
	 * 
	 * @param value
	 *          allowed object is {@link ExtensionType }
	 * 
	 */
	public void setHoldingExternal(ExtensionTypeClient value) {
		this.holdingExternal = value;
	}

}
