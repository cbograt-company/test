
package com.microsoft.bingads.v11.customermanagement;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ApiFault", targetNamespace = "https://bingads.microsoft.com/Customer/v11")
public class ICustomerManagementServiceSendUserInvitationApiFaultFaultMessage
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ApiFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ICustomerManagementServiceSendUserInvitationApiFaultFaultMessage(String message, ApiFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ICustomerManagementServiceSendUserInvitationApiFaultFaultMessage(String message, ApiFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.microsoft.bingads.v11.customermanagement.ApiFault
     */
    public ApiFault getFaultInfo() {
        return faultInfo;
    }

}
