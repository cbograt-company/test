
package com.microsoft.bingads.v11.adinsight;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "AdInsightService", targetNamespace = "Microsoft.Advertiser.AdInsight.Api.Service.V11", wsdlLocation = "https://adinsight.api.sandbox.bingads.microsoft.com/Api/Advertiser/AdInsight/v11/AdInsightService.svc?wsdl")
public class AdInsightService
    extends Service
{

    private final static URL ADINSIGHTSERVICE_WSDL_LOCATION;
    private final static WebServiceException ADINSIGHTSERVICE_EXCEPTION;
    private final static QName ADINSIGHTSERVICE_QNAME = new QName("Microsoft.Advertiser.AdInsight.Api.Service.V11", "AdInsightService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://adinsight.api.sandbox.bingads.microsoft.com/Api/Advertiser/AdInsight/v11/AdInsightService.svc?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ADINSIGHTSERVICE_WSDL_LOCATION = url;
        ADINSIGHTSERVICE_EXCEPTION = e;
    }

    public AdInsightService() {
        super(__getWsdlLocation(), ADINSIGHTSERVICE_QNAME);
    }

    public AdInsightService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns IAdInsightService
     */
    @WebEndpoint(name = "BasicHttpBinding_IAdInsightService")
    public IAdInsightService getBasicHttpBindingIAdInsightService() {
        return super.getPort(new QName("Microsoft.Advertiser.AdInsight.Api.Service.V11", "BasicHttpBinding_IAdInsightService"), IAdInsightService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IAdInsightService
     */
    @WebEndpoint(name = "BasicHttpBinding_IAdInsightService")
    public IAdInsightService getBasicHttpBindingIAdInsightService(WebServiceFeature... features) {
        return super.getPort(new QName("Microsoft.Advertiser.AdInsight.Api.Service.V11", "BasicHttpBinding_IAdInsightService"), IAdInsightService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ADINSIGHTSERVICE_EXCEPTION!= null) {
            throw ADINSIGHTSERVICE_EXCEPTION;
        }
        return ADINSIGHTSERVICE_WSDL_LOCATION;
    }

}
