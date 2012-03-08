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

package org.fedora.api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceDefinitionPid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="methodName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parameters">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parameter" type="{http://www.fedora.info/definitions/1/0/types/}Property" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="asOfDateTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"pid", "serviceDefinitionPid", "methodName", "parameters", "asOfDateTime"})
@XmlRootElement(name = "getDissemination")
public class GetDissemination {

    /** The pid. */
    @XmlElement(required = true)
    protected String pid;

    /** The service definition pid. */
    @XmlElement(required = true)
    protected String serviceDefinitionPid;

    /** The method name. */
    @XmlElement(required = true)
    protected String methodName;

    /** The parameters. */
    @XmlElement(required = true)
    protected GetDissemination.Parameters parameters;

    /** The as of date time. */
    @XmlElement(required = true, nillable = true)
    protected String asOfDateTime;

    /**
     * Gets the value of the pid property.
     * 
     * @return the pid possible object is {@link String }
     */
    public String getPid() {
        return pid;
    }

    /**
     * Sets the value of the pid property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setPid(String value) {
        this.pid = value;
    }

    /**
     * Gets the value of the serviceDefinitionPid property.
     * 
     * @return the service definition pid possible object is {@link String }
     */
    public String getServiceDefinitionPid() {
        return serviceDefinitionPid;
    }

    /**
     * Sets the value of the serviceDefinitionPid property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setServiceDefinitionPid(String value) {
        this.serviceDefinitionPid = value;
    }

    /**
     * Gets the value of the methodName property.
     * 
     * @return the method name possible object is {@link String }
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Sets the value of the methodName property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setMethodName(String value) {
        this.methodName = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * @return the parameters possible object is
     *         {@link GetDissemination.Parameters }
     */
    public GetDissemination.Parameters getParameters() {
        return parameters;
    }

    /**
     * Sets the value of the parameters property.
     * 
     * @param value
     *        allowed object is {@link GetDissemination.Parameters }
     */
    public void setParameters(GetDissemination.Parameters value) {
        this.parameters = value;
    }

    /**
     * Gets the value of the asOfDateTime property.
     * 
     * @return the as of date time possible object is {@link String }
     */
    public String getAsOfDateTime() {
        return asOfDateTime;
    }

    /**
     * Sets the value of the asOfDateTime property.
     * 
     * @param value
     *        allowed object is {@link String }
     */
    public void setAsOfDateTime(String value) {
        this.asOfDateTime = value;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="parameter" type="{http://www.fedora.info/definitions/1/0/types/}Property" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"parameter"})
    public static class Parameters {

        /** The parameter. */
        protected List<Property> parameter;

        /**
         * Gets the value of the parameter property.
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
         * <CODE>set</CODE> method for the parameter property.
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getParameter().add(newItem);
         * </pre>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * 
         * @return the parameter {@link Property }
         */
        public List<Property> getParameter() {
            if (parameter == null) {
                parameter = new ArrayList<Property>();
            }
            return this.parameter;
        }

    }

}
