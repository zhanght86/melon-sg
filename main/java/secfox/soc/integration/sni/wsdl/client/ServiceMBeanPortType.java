package secfox.soc.integration.sni.wsdl.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2014-11-27T15:08:04.005+08:00
 * Generated source version: 3.0.2
 * 
 */
@WebService(targetNamespace = "http://service.soa.system.soc.secfox/", name = "ServiceMBeanPortType")
@XmlSeeAlso({ObjectFactory.class})
public interface ServiceMBeanPortType {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getLinkFlow", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.GetLinkFlow")
    @WebMethod
    @ResponseWrapper(localName = "getLinkFlowResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.GetLinkFlowResponse")
    public java.util.List<secfox.soc.integration.sni.wsdl.client.LongArray> getLinkFlow(
        @WebParam(name = "periodType", targetNamespace = "")
        int periodType
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "deleteUser", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.DeleteUser")
    @WebMethod
    @ResponseWrapper(localName = "deleteUserResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.DeleteUserResponse")
    public int deleteUser(
        @WebParam(name = "userId", targetNamespace = "")
        int userId
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "updateDeviceBasicInfo", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.UpdateDeviceBasicInfo")
    @WebMethod
    @ResponseWrapper(localName = "updateDeviceBasicInfoResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.UpdateDeviceBasicInfoResponse")
    public int updateDeviceBasicInfo(
        @WebParam(name = "ciid", targetNamespace = "")
        long ciid,
        @WebParam(name = "properties", targetNamespace = "")
        java.util.List<java.lang.Object> properties
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getDeviceObjectList", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.GetDeviceObjectList")
    @WebMethod
    @ResponseWrapper(localName = "getDeviceObjectListResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.GetDeviceObjectListResponse")
    public java.util.List<secfox.soc.integration.sni.wsdl.client.DeviceObject> getDeviceObjectList();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "addSecurityPolicy", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.AddSecurityPolicy")
    @WebMethod
    @ResponseWrapper(localName = "addSecurityPolicyResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.AddSecurityPolicyResponse")
    public int addSecurityPolicy(
        @WebParam(name = "properties", targetNamespace = "")
        java.util.List<java.lang.Object> properties,
        @WebParam(name = "transform", targetNamespace = "")
        boolean transform
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "deleteDevice", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.DeleteDevice")
    @WebMethod
    @ResponseWrapper(localName = "deleteDeviceResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.DeleteDeviceResponse")
    public int deleteDevice(
        @WebParam(name = "ccid", targetNamespace = "")
        long ccid
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.UpdateUser")
    @WebMethod
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.UpdateUserResponse")
    public int updateUser(
        @WebParam(name = "userId", targetNamespace = "")
        int userId,
        @WebParam(name = "properties", targetNamespace = "")
        java.util.List<java.lang.Object> properties
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "updateAlertStatus", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.UpdateAlertStatus")
    @WebMethod
    @ResponseWrapper(localName = "updateAlertStatusResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.UpdateAlertStatusResponse")
    public int updateAlertStatus(
        @WebParam(name = "alertId", targetNamespace = "")
        long alertId
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "insertUser", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.InsertUser")
    @WebMethod
    @ResponseWrapper(localName = "insertUserResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.InsertUserResponse")
    public int insertUser(
        @WebParam(name = "userId", targetNamespace = "")
        int userId,
        @WebParam(name = "properties", targetNamespace = "")
        java.util.List<java.lang.Object> properties
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "distributeIP", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.DistributeIP")
    @WebMethod
    @ResponseWrapper(localName = "distributeIPResponse", targetNamespace = "http://service.soa.system.soc.secfox/", className = "secfox.soc.integration.sni.wsdl.client.DistributeIPResponse")
    public int distributeIP(
        @WebParam(name = "ip", targetNamespace = "")
        java.lang.String ip,
        @WebParam(name = "subnet", targetNamespace = "")
        java.lang.String subnet,
        @WebParam(name = "status", targetNamespace = "")
        java.lang.Integer status
    );
}
