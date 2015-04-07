
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ipAddrObject complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ipAddrObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assignTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ifIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ipAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isAssigned" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="netAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="origin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="outIpAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outNetAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ipAddrObject", propOrder = {
    "assignTime",
    "id",
    "ifIndex",
    "ipAddr",
    "isAssigned",
    "mask",
    "netAddr",
    "nodeID",
    "origin",
    "outIpAddr",
    "outMask",
    "outNetAddr"
})
public class IpAddrObject {

    protected String assignTime;
    protected long id;
    protected int ifIndex;
    protected String ipAddr;
    protected int isAssigned;
    protected String mask;
    protected String netAddr;
    protected long nodeID;
    protected int origin;
    protected String outIpAddr;
    protected String outMask;
    protected String outNetAddr;

    /**
     * 获取assignTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignTime() {
        return assignTime;
    }

    /**
     * 设置assignTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignTime(String value) {
        this.assignTime = value;
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
     * 获取ifIndex属性的值。
     * 
     */
    public int getIfIndex() {
        return ifIndex;
    }

    /**
     * 设置ifIndex属性的值。
     * 
     */
    public void setIfIndex(int value) {
        this.ifIndex = value;
    }

    /**
     * 获取ipAddr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * 设置ipAddr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAddr(String value) {
        this.ipAddr = value;
    }

    /**
     * 获取isAssigned属性的值。
     * 
     */
    public int getIsAssigned() {
        return isAssigned;
    }

    /**
     * 设置isAssigned属性的值。
     * 
     */
    public void setIsAssigned(int value) {
        this.isAssigned = value;
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
     * 获取netAddr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetAddr() {
        return netAddr;
    }

    /**
     * 设置netAddr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetAddr(String value) {
        this.netAddr = value;
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
     * 获取outIpAddr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutIpAddr() {
        return outIpAddr;
    }

    /**
     * 设置outIpAddr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutIpAddr(String value) {
        this.outIpAddr = value;
    }

    /**
     * 获取outMask属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutMask() {
        return outMask;
    }

    /**
     * 设置outMask属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutMask(String value) {
        this.outMask = value;
    }

    /**
     * 获取outNetAddr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutNetAddr() {
        return outNetAddr;
    }

    /**
     * 设置outNetAddr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutNetAddr(String value) {
        this.outNetAddr = value;
    }

}
