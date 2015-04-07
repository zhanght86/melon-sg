
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>deviceInformationObject complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡavaliablity���Ե�ֵ��
     * 
     */
    public int getAvaliablity() {
        return avaliablity;
    }

    /**
     * ����avaliablity���Ե�ֵ��
     * 
     */
    public void setAvaliablity(int value) {
        this.avaliablity = value;
    }

    /**
     * ��ȡbusiness���Ե�ֵ��
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
     * ����business���Ե�ֵ��
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
     * ��ȡconfidentiality���Ե�ֵ��
     * 
     */
    public int getConfidentiality() {
        return confidentiality;
    }

    /**
     * ����confidentiality���Ե�ֵ��
     * 
     */
    public void setConfidentiality(int value) {
        this.confidentiality = value;
    }

    /**
     * ��ȡcpu���Ե�ֵ��
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
     * ����cpu���Ե�ֵ��
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
     * ��ȡdeptID���Ե�ֵ��
     * 
     */
    public long getDeptID() {
        return deptID;
    }

    /**
     * ����deptID���Ե�ֵ��
     * 
     */
    public void setDeptID(long value) {
        this.deptID = value;
    }

    /**
     * ��ȡdeptName���Ե�ֵ��
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
     * ����deptName���Ե�ֵ��
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
     * ��ȡdescription���Ե�ֵ��
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
     * ����description���Ե�ֵ��
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
     * ��ȡemploy���Ե�ֵ��
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
     * ����employ���Ե�ֵ��
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
     * ��ȡforeignKey���Ե�ֵ��
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
     * ����foreignKey���Ե�ֵ��
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
     * ��ȡgateway���Ե�ֵ��
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
     * ����gateway���Ե�ֵ��
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
     * ��ȡhdNum���Ե�ֵ��
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
     * ����hdNum���Ե�ֵ��
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
     * ��ȡhdTroughNum���Ե�ֵ��
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
     * ����hdTroughNum���Ե�ֵ��
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
     * ��ȡhardVersion���Ե�ֵ��
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
     * ����hardVersion���Ե�ֵ��
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
     * ��ȡhight���Ե�ֵ��
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
     * ����hight���Ե�ֵ��
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
     * ��ȡid���Ե�ֵ��
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * ��ȡintegrity���Ե�ֵ��
     * 
     */
    public int getIntegrity() {
        return integrity;
    }

    /**
     * ����integrity���Ե�ֵ��
     * 
     */
    public void setIntegrity(int value) {
        this.integrity = value;
    }

    /**
     * ��ȡip���Ե�ֵ��
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
     * ����ip���Ե�ֵ��
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
     * ��ȡisCDROM���Ե�ֵ��
     * 
     */
    public int getIsCDROM() {
        return isCDROM;
    }

    /**
     * ����isCDROM���Ե�ֵ��
     * 
     */
    public void setIsCDROM(int value) {
        this.isCDROM = value;
    }

    /**
     * ��ȡisFD���Ե�ֵ��
     * 
     */
    public int getIsFD() {
        return isFD;
    }

    /**
     * ����isFD���Ե�ֵ��
     * 
     */
    public void setIsFD(int value) {
        this.isFD = value;
    }

    /**
     * ��ȡlocality���Ե�ֵ��
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
     * ����locality���Ե�ֵ��
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
     * ��ȡmac���Ե�ֵ��
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
     * ����mac���Ե�ֵ��
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
     * ��ȡmask���Ե�ֵ��
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
     * ����mask���Ե�ֵ��
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
     * ��ȡmemNum���Ե�ֵ��
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
     * ����memNum���Ե�ֵ��
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
     * ��ȡmemTroughNum���Ե�ֵ��
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
     * ����memTroughNum���Ե�ֵ��
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
     * ��ȡmemory���Ե�ֵ��
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
     * ����memory���Ե�ֵ��
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
     * ��ȡmianBoard���Ե�ֵ��
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
     * ����mianBoard���Ե�ֵ��
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
     * ��ȡnic���Ե�ֵ��
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
     * ����nic���Ե�ֵ��
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
     * ��ȡname���Ե�ֵ��
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
     * ����name���Ե�ֵ��
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
     * ��ȡnodeID���Ե�ֵ��
     * 
     */
    public long getNodeID() {
        return nodeID;
    }

    /**
     * ����nodeID���Ե�ֵ��
     * 
     */
    public void setNodeID(long value) {
        this.nodeID = value;
    }

    /**
     * ��ȡnodeType���Ե�ֵ��
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
     * ����nodeType���Ե�ֵ��
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
     * ��ȡos���Ե�ֵ��
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
     * ����os���Ե�ֵ��
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
     * ��ȡpowers���Ե�ֵ��
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
     * ����powers���Ե�ֵ��
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
     * ��ȡprice���Ե�ֵ��
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
     * ����price���Ե�ֵ��
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
     * ��ȡprincipal���Ե�ֵ��
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
     * ����principal���Ե�ֵ��
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
     * ��ȡrackID���Ե�ֵ��
     * 
     */
    public long getRackID() {
        return rackID;
    }

    /**
     * ����rackID���Ե�ֵ��
     * 
     */
    public void setRackID(long value) {
        this.rackID = value;
    }

    /**
     * ��ȡserialNo���Ե�ֵ��
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
     * ����serialNo���Ե�ֵ��
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
     * ��ȡsoftVersion���Ե�ֵ��
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
     * ����softVersion���Ե�ֵ��
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
     * ��ȡsubNodeID���Ե�ֵ��
     * 
     */
    public long getSubNodeID() {
        return subNodeID;
    }

    /**
     * ����subNodeID���Ե�ֵ��
     * 
     */
    public void setSubNodeID(long value) {
        this.subNodeID = value;
    }

    /**
     * ��ȡsubarea���Ե�ֵ��
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
     * ����subarea���Ե�ֵ��
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
     * ��ȡsysContact���Ե�ֵ��
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
     * ����sysContact���Ե�ֵ��
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
     * ��ȡsysLocation���Ե�ֵ��
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
     * ����sysLocation���Ե�ֵ��
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
     * ��ȡused���Ե�ֵ��
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
     * ����used���Ե�ֵ��
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
     * ��ȡvalue���Ե�ֵ��
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * ����value���Ե�ֵ��
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * ��ȡvalueType���Ե�ֵ��
     * 
     */
    public int getValueType() {
        return valueType;
    }

    /**
     * ����valueType���Ե�ֵ��
     * 
     */
    public void setValueType(int value) {
        this.valueType = value;
    }

    /**
     * ��ȡvendor���Ե�ֵ��
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
     * ����vendor���Ե�ֵ��
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
     * ��ȡvideoAdapter���Ե�ֵ��
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
     * ����videoAdapter���Ե�ֵ��
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
