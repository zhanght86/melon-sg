
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>interfaceObject complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="interfaceObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ifAdminStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ifDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ifIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ifOperStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ifPhysAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ifSpeed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ifType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monitorStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nodeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="origin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="physIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vlanID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interfaceObject", propOrder = {
    "id",
    "ifAdminStatus",
    "ifDesc",
    "ifIndex",
    "ifOperStatus",
    "ifPhysAddress",
    "ifSpeed",
    "ifType",
    "monitorStatus",
    "nodeID",
    "origin",
    "physIndex",
    "vlanID"
})
public class InterfaceObject {

    protected long id;
    protected int ifAdminStatus;
    protected String ifDesc;
    protected int ifIndex;
    protected int ifOperStatus;
    protected String ifPhysAddress;
    protected int ifSpeed;
    protected int ifType;
    protected int monitorStatus;
    protected long nodeID;
    protected int origin;
    protected int physIndex;
    protected int vlanID;

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
     * 获取ifAdminStatus属性的值。
     * 
     */
    public int getIfAdminStatus() {
        return ifAdminStatus;
    }

    /**
     * 设置ifAdminStatus属性的值。
     * 
     */
    public void setIfAdminStatus(int value) {
        this.ifAdminStatus = value;
    }

    /**
     * 获取ifDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfDesc() {
        return ifDesc;
    }

    /**
     * 设置ifDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfDesc(String value) {
        this.ifDesc = value;
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
     * 获取ifOperStatus属性的值。
     * 
     */
    public int getIfOperStatus() {
        return ifOperStatus;
    }

    /**
     * 设置ifOperStatus属性的值。
     * 
     */
    public void setIfOperStatus(int value) {
        this.ifOperStatus = value;
    }

    /**
     * 获取ifPhysAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfPhysAddress() {
        return ifPhysAddress;
    }

    /**
     * 设置ifPhysAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfPhysAddress(String value) {
        this.ifPhysAddress = value;
    }

    /**
     * 获取ifSpeed属性的值。
     * 
     */
    public int getIfSpeed() {
        return ifSpeed;
    }

    /**
     * 设置ifSpeed属性的值。
     * 
     */
    public void setIfSpeed(int value) {
        this.ifSpeed = value;
    }

    /**
     * 获取ifType属性的值。
     * 
     */
    public int getIfType() {
        return ifType;
    }

    /**
     * 设置ifType属性的值。
     * 
     */
    public void setIfType(int value) {
        this.ifType = value;
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
     * 获取physIndex属性的值。
     * 
     */
    public int getPhysIndex() {
        return physIndex;
    }

    /**
     * 设置physIndex属性的值。
     * 
     */
    public void setPhysIndex(int value) {
        this.physIndex = value;
    }

    /**
     * 获取vlanID属性的值。
     * 
     */
    public int getVlanID() {
        return vlanID;
    }

    /**
     * 设置vlanID属性的值。
     * 
     */
    public void setVlanID(int value) {
        this.vlanID = value;
    }

}
