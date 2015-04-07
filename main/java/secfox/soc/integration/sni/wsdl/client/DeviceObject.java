
package secfox.soc.integration.sni.wsdl.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>deviceObject complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="deviceObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.soa.system.soc.secfox/}nodeObject">
 *       &lt;sequence>
 *         &lt;element name="authenticationModel" type="{http://service.soa.system.soc.secfox/}authenticationModel" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="childrens" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceInformationObject" type="{http://service.soa.system.soc.secfox/}deviceInformationObject" minOccurs="0"/>
 *         &lt;element name="eventStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fdbList" type="{http://service.soa.system.soc.secfox/}fdbObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fontSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="interfaceList" type="{http://service.soa.system.soc.secfox/}interfaceObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ipAddrList" type="{http://service.soa.system.soc.secfox/}ipAddrObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="kpi" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monitorStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nodeImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pointX" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pointY" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="portModuleID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="serviceList" type="{http://service.soa.system.soc.secfox/}serviceObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sysName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysObjectID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vendor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceObject", propOrder = {
    "authenticationModel",
    "category",
    "childrens",
    "createTime",
    "description",
    "deviceInformationObject",
    "eventStatus",
    "fdbList",
    "fontSize",
    "ip",
    "interfaceList",
    "ipAddrList",
    "kpi",
    "mac",
    "mask",
    "model",
    "monitorStatus",
    "nodeImage",
    "nodeType",
    "origin",
    "pointX",
    "pointY",
    "portModuleID",
    "serviceList",
    "status",
    "sysName",
    "sysObjectID",
    "vendor"
})
public class DeviceObject
    extends NodeObject
{

    protected AuthenticationModel authenticationModel;
    protected int category;
    @XmlElement(nillable = true)
    protected List<Object> childrens;
    protected long createTime;
    protected String description;
    protected DeviceInformationObject deviceInformationObject;
    protected int eventStatus;
    @XmlElement(nillable = true)
    protected List<FdbObject> fdbList;
    protected String fontSize;
    @XmlElement(name = "IP")
    protected String ip;
    @XmlElement(nillable = true)
    protected List<InterfaceObject> interfaceList;
    @XmlElement(nillable = true)
    protected List<IpAddrObject> ipAddrList;
    protected int kpi;
    protected String mac;
    protected String mask;
    protected String model;
    protected int monitorStatus;
    protected String nodeImage;
    protected String nodeType;
    protected int origin;
    protected int pointX;
    protected int pointY;
    protected long portModuleID;
    @XmlElement(nillable = true)
    protected List<ServiceObject> serviceList;
    protected int status;
    protected String sysName;
    protected String sysObjectID;
    protected String vendor;

    /**
     * 获取authenticationModel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationModel }
     *     
     */
    public AuthenticationModel getAuthenticationModel() {
        return authenticationModel;
    }

    /**
     * 设置authenticationModel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationModel }
     *     
     */
    public void setAuthenticationModel(AuthenticationModel value) {
        this.authenticationModel = value;
    }

    /**
     * 获取category属性的值。
     * 
     */
    public int getCategory() {
        return category;
    }

    /**
     * 设置category属性的值。
     * 
     */
    public void setCategory(int value) {
        this.category = value;
    }

    /**
     * Gets the value of the childrens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childrens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildrens().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getChildrens() {
        if (childrens == null) {
            childrens = new ArrayList<Object>();
        }
        return this.childrens;
    }

    /**
     * 获取createTime属性的值。
     * 
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime属性的值。
     * 
     */
    public void setCreateTime(long value) {
        this.createTime = value;
    }

    /**
     * 获取description属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * 获取deviceInformationObject属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DeviceInformationObject }
     *     
     */
    public DeviceInformationObject getDeviceInformationObject() {
        return deviceInformationObject;
    }

    /**
     * 设置deviceInformationObject属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceInformationObject }
     *     
     */
    public void setDeviceInformationObject(DeviceInformationObject value) {
        this.deviceInformationObject = value;
    }

    /**
     * 获取eventStatus属性的值。
     * 
     */
    public int getEventStatus() {
        return eventStatus;
    }

    /**
     * 设置eventStatus属性的值。
     * 
     */
    public void setEventStatus(int value) {
        this.eventStatus = value;
    }

    /**
     * Gets the value of the fdbList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fdbList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFdbList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FdbObject }
     * 
     * 
     */
    public List<FdbObject> getFdbList() {
        if (fdbList == null) {
            fdbList = new ArrayList<FdbObject>();
        }
        return this.fdbList;
    }

    /**
     * 获取fontSize属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontSize() {
        return fontSize;
    }

    /**
     * 设置fontSize属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontSize(String value) {
        this.fontSize = value;
    }

    /**
     * 获取ip属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIP() {
        return ip;
    }

    /**
     * 设置ip属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIP(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the interfaceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interfaceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterfaceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InterfaceObject }
     * 
     * 
     */
    public List<InterfaceObject> getInterfaceList() {
        if (interfaceList == null) {
            interfaceList = new ArrayList<InterfaceObject>();
        }
        return this.interfaceList;
    }

    /**
     * Gets the value of the ipAddrList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ipAddrList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIpAddrList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IpAddrObject }
     * 
     * 
     */
    public List<IpAddrObject> getIpAddrList() {
        if (ipAddrList == null) {
            ipAddrList = new ArrayList<IpAddrObject>();
        }
        return this.ipAddrList;
    }

    /**
     * 获取kpi属性的值。
     * 
     */
    public int getKpi() {
        return kpi;
    }

    /**
     * 设置kpi属性的值。
     * 
     */
    public void setKpi(int value) {
        this.kpi = value;
    }

    /**
     * 获取mac属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMac() {
        return mac;
    }

    /**
     * 设置mac属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMac(String value) {
        this.mac = value;
    }

    /**
     * 获取mask属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMask() {
        return mask;
    }

    /**
     * 设置mask属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMask(String value) {
        this.mask = value;
    }

    /**
     * 获取model属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置model属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * 获取monitorStatus属性的值。
     * 
     */
    public int getMonitorStatus() {
        return monitorStatus;
    }

    /**
     * 设置monitorStatus属性的值。
     * 
     */
    public void setMonitorStatus(int value) {
        this.monitorStatus = value;
    }

    /**
     * 获取nodeImage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeImage() {
        return nodeImage;
    }

    /**
     * 设置nodeImage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeImage(String value) {
        this.nodeImage = value;
    }

    /**
     * 获取nodeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置nodeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeType(String value) {
        this.nodeType = value;
    }

    /**
     * 获取origin属性的值。
     * 
     */
    public int getOrigin() {
        return origin;
    }

    /**
     * 设置origin属性的值。
     * 
     */
    public void setOrigin(int value) {
        this.origin = value;
    }

    /**
     * 获取pointX属性的值。
     * 
     */
    public int getPointX() {
        return pointX;
    }

    /**
     * 设置pointX属性的值。
     * 
     */
    public void setPointX(int value) {
        this.pointX = value;
    }

    /**
     * 获取pointY属性的值。
     * 
     */
    public int getPointY() {
        return pointY;
    }

    /**
     * 设置pointY属性的值。
     * 
     */
    public void setPointY(int value) {
        this.pointY = value;
    }

    /**
     * 获取portModuleID属性的值。
     * 
     */
    public long getPortModuleID() {
        return portModuleID;
    }

    /**
     * 设置portModuleID属性的值。
     * 
     */
    public void setPortModuleID(long value) {
        this.portModuleID = value;
    }

    /**
     * Gets the value of the serviceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceObject }
     * 
     * 
     */
    public List<ServiceObject> getServiceList() {
        if (serviceList == null) {
            serviceList = new ArrayList<ServiceObject>();
        }
        return this.serviceList;
    }

    /**
     * 获取status属性的值。
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * 获取sysName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * 设置sysName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysName(String value) {
        this.sysName = value;
    }

    /**
     * 获取sysObjectID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysObjectID() {
        return sysObjectID;
    }

    /**
     * 设置sysObjectID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysObjectID(String value) {
        this.sysObjectID = value;
    }

    /**
     * 获取vendor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * 设置vendor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
    }

}
