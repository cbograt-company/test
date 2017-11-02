
package com.microsoft.bingads.v11.customerbilling;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ApiBatchFault", targetNamespace = "https://bingads.microsoft.com/Billing/v11")
public class ApiBatchFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ApiBatchFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ApiBatchFault_Exception(String message, ApiBatchFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ApiBatchFault_Exception(String message, ApiBatchFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.microsoft.bingads.v11.customerbilling.ApiBatchFault
     */
    public ApiBatchFault getFaultInfo() {
        return faultInfo;
    }

}
