
package com.microsoft.bingads.v11.campaignmanagement;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "AdApiFaultDetail", targetNamespace = "https://adapi.microsoft.com")
public class ICampaignManagementServiceGetConfigValueAdApiFaultDetailFaultFaultMessage
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private AdApiFaultDetail faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ICampaignManagementServiceGetConfigValueAdApiFaultDetailFaultFaultMessage(String message, AdApiFaultDetail faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ICampaignManagementServiceGetConfigValueAdApiFaultDetailFaultFaultMessage(String message, AdApiFaultDetail faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.microsoft.bingads.v11.campaignmanagement.AdApiFaultDetail
     */
    public AdApiFaultDetail getFaultInfo() {
        return faultInfo;
    }

}
