
package com.microsoft.bingads.v11.campaignmanagement;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBMCStore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBMCStore">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BMCStore" type="{https://bingads.microsoft.com/CampaignManagement/v11}BMCStore" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBMCStore", propOrder = {
    "bmcStores"
})
public class ArrayOfBMCStore {

    @XmlElement(name = "BMCStore", nillable = true)
    protected List<BMCStore> bmcStores;

    /**
     * Gets the value of the bmcStores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bmcStores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBMCStores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BMCStore }
     * 
     * 
     */
    public List<BMCStore> getBMCStores() {
        if (bmcStores == null) {
            bmcStores = new ArrayList<BMCStore>();
        }
        return this.bmcStores;
    }

}
