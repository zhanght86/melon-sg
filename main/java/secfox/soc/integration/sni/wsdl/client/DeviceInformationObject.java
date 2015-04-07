
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>deviceInformationObject complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="deviceInformationObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="avaliablity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="business" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="confidentiality" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cpu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="deptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="foreignKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gateway" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HDNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HDTroughNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hardVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="integrity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCD_ROM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isFD" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="locality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memTroughNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mianBoard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NIC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nodeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="powers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="principal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rackID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="serialNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="softVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subNodeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="subarea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sysLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="used" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="valueType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vendor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="videoAdapter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deviceInformationObject", propOrder = {
    "avaliablity",
    "business",
    "confidentiality",
    "cpu",
    "deptID",
    "deptName",
    "description",
    "employ",
    "foreignKey",
    "gateway",
    "hdNum",
    "hdTroughNum",
    "hardVersion",
    "hight",
    "id",
    "integrity",
    "ip",
    "isCDROM",
    "isFD",
    "locality",
    "mac",
    "mask",
    "memNum",
    "memTroughNum",
    "memory",
    "mianBoard",
    "nic",
    "name",
    "nodeID",
    "nodeType",
    "os",
    "powers",
    "price",
    "principal",
    "rackID",
    "serialNo",
    "softVersion",
    "subNodeID",
    "subarea",
    "sysContact",
    "sysLocation",
    "used",
    "value",
    "valueType",
    "vendor",
    "videoAdapter"
})
public class DeviceInformationObject {

    protected int avaliablity;
    protected String business;
    protected int confidentiality;
    protected String cpu;
    protected long deptID;
    protected String deptName;
    protected String description;
    protected String employ;
    protected String foreignKey;
    protected String gateway;
    @XmlElement(name = "HDNum")
    protected String hdNum;
    @XmlElement(name = "HDTroughNum")
    protected String hdTroughNum;
    protected String hardVersion;
    protected String hight;
    protected long id;
    protected int integrity;
    protected String ip;
    @XmlElement(name = "isCD_ROM")
    protected int isCDROM;
    protected int isFD;
    protected String locality;
    protected String mac;
    protected String mask;
    protected String memNum;
    protected String memTroughNum;
    protected String memory;
    protected String mianBoard;
    @XmlElement(name = "NIC")
    protected String nic;
    protected String name;
    protected long nodeID;
    protected String nodeType;
    @XmlElement(name = "OS")
    protected String os;
    protected String powers;
    protected String price;
    protected String principal;
    protected long rackID;
    protected String serialNo;
    protected String softVersion;
    protected long subNodeID;
    protected String subarea;
    protected String sysContact;
    protected String sysLocation;
    protected String used;
    protected double value;
    protected int valueType;
    protected String vendor;
    protected String videoAdapter;

    /**
     * 获取avaliablity属性的值。
     * 
     */
    public int getAvaliablity() {
        return avaliablity;
    }

    /**
     * 设置avaliablity属性的值。
     * 
     */
    public void setAvaliablity(int value) {
        this.avaliablity = value;
    }

    /**
     * 获取business属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiness() {
        return business;
    }

    /**
     * 设置business属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiness(String value) {
        this.business = value;
    }

    /**
     * 获取confidentiality属性的值。
     * 
     */
    public int getConfidentiality() {
        return confidentiality;
    }

    /**
     * 设置confidentiality属性的值。
     * 
     */
    public void setConfidentiality(int value) {
        this.confidentiality = value;
    }

    /**
     * 获取cpu属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * 设置cpu属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpu(String value) {
        this.cpu = value;
    }

    /**
     * 获取deptID属性的值。
     * 
     */
    public long getDeptID() {
        return deptID;
    }

    /**
     * 设置deptID属性的值。
     * 
     */
    public void setDeptID(long value) {
        this.deptID = value;
    }

    /**
     * 获取deptName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置deptName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptName(String value) {
        this.deptName = value;
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
     * 获取employ属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmploy() {
        return employ;
    }

    /**
     * 设置employ属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmploy(String value) {
        this.employ = value;
    }

    /**
     * 获取foreignKey属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeignKey() {
        return foreignKey;
    }

    /**
     * 设置foreignKey属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeignKey(String value) {
        this.foreignKey = value;
    }

    /**
     * 获取gateway属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGateway() {
        return gateway;
    }

    /**
     * 设置gateway属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGateway(String value) {
        this.gateway = value;
    }

    /**
     * 获取hdNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHDNum() {
        return hdNum;
    }

    /**
     * 设置hdNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHDNum(String value) {
        this.hdNum = value;
    }

    /**
     * 获取hdTroughNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHDTroughNum() {
        return hdTroughNum;
    }

    /**
     * 设置hdTroughNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHDTroughNum(String value) {
        this.hdTroughNum = value;
    }

    /**
     * 获取hardVersion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHardVersion() {
        return hardVersion;
    }

    /**
     * 设置hardVersion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHardVersion(String value) {
        this.hardVersion = value;
    }

    /**
     * 获取hight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHight() {
        return hight;
    }

    /**
     * 设置hight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHight(String value) {
        this.hight = value;
    }

    /**
     * 获取id属性的值。
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * 获取integrity属性的值。
     * 
     */
    public int getIntegrity() {
        return integrity;
    }

    /**
     * 设置integrity属性的值。
     * 
     */
    public void setIntegrity(int value) {
        this.integrity = value;
    }

    /**
     * 获取ip属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
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
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * 获取isCDROM属性的值。
     * 
     */
    public int getIsCDROM() {
        return isCDROM;
    }

    /**
     * 设置isCDROM属性的值。
     * 
     */
    public void setIsCDROM(int value) {
        this.isCDROM = value;
    }

    /**
     * 获取isFD属性的值。
     * 
     */
    public int getIsFD() {
        return isFD;
    }

    /**
     * 设置isFD属性的值。
     * 
     */
    public void setIsFD(int value) {
        this.isFD = value;
    }

    /**
     * 获取locality属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocality() {
        return locality;
    }

    /**
     * 设置locality属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocality(String value) {
        this.locality = value;
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
     * 获取memNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemNum() {
        return memNum;
    }

    /**
     * 设置memNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemNum(String value) {
        this.memNum = value;
    }

    /**
     * 获取memTroughNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemTroughNum() {
        return memTroughNum;
    }

    /**
     * 设置memTroughNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemTroughNum(String value) {
        this.memTroughNum = value;
    }

    /**
     * 获取memory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemory() {
        return memory;
    }

    /**
     * 设置memory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemory(String value) {
        this.memory = value;
    }

    /**
     * 获取mianBoard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMianBoard() {
        return mianBoard;
    }

    /**
     * 设置mianBoard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMianBoard(String value) {
        this.mianBoard = value;
    }

    /**
     * 获取nic属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNIC() {
        return nic;
    }

    /**
     * 设置nic属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNIC(String value) {
        this.nic = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取nodeID属性的值。
     * 
     */
    public long getNodeID() {
        return nodeID;
    }

    /**
     * 设置nodeID属性的值。
     * 
     */
    public void setNodeID(long value) {
        this.nodeID = value;
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
     * 获取os属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOS() {
        return os;
    }

    /**
     * 设置os属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOS(String value) {
        this.os = value;
    }

    /**
     * 获取powers属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPowers() {
        return powers;
    }

    /**
     * 设置powers属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPowers(String value) {
        this.powers = value;
    }

    /**
     * 获取price属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置price属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrice(String value) {
        this.price = value;
    }

    /**
     * 获取principal属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * 设置principal属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipal(String value) {
        this.principal = value;
    }

    /**
     * 获取rackID属性的值。
     * 
     */
    public long getRackID() {
        return rackID;
    }

    /**
     * 设置rackID属性的值。
     * 
     */
    public void setRackID(long value) {
        this.rackID = value;
    }

    /**
     * 获取serialNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 设置serialNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNo(String value) {
        this.serialNo = value;
    }

    /**
     * 获取softVersion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftVersion() {
        return softVersion;
    }

    /**
     * 设置softVersion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftVersion(String value) {
        this.softVersion = value;
    }

    /**
     * 获取subNodeID属性的值。
     * 
     */
    public long getSubNodeID() {
        return subNodeID;
    }

    /**
     * 设置subNodeID属性的值。
     * 
     */
    public void setSubNodeID(long value) {
        this.subNodeID = value;
    }

    /**
     * 获取subarea属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubarea() {
        return subarea;
    }

    /**
     * 设置subarea属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubarea(String value) {
        this.subarea = value;
    }

    /**
     * 获取sysContact属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysContact() {
        return sysContact;
    }

    /**
     * 设置sysContact属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysContact(String value) {
        this.sysContact = value;
    }

    /**
     * 获取sysLocation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysLocation() {
        return sysLocation;
    }

    /**
     * 设置sysLocation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysLocation(String value) {
        this.sysLocation = value;
    }

    /**
     * 获取used属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsed() {
        return used;
    }

    /**
     * 设置used属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsed(String value) {
        this.used = value;
    }

    /**
     * 获取value属性的值。
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * 获取valueType属性的值。
     * 
     */
    public int getValueType() {
        return valueType;
    }

    /**
     * 设置valueType属性的值。
     * 
     */
    public void setValueType(int value) {
        this.valueType = value;
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

    /**
     * 获取videoAdapter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoAdapter() {
        return videoAdapter;
    }

    /**
     * 设置videoAdapter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoAdapter(String value) {
        this.videoAdapter = value;
    }

}
