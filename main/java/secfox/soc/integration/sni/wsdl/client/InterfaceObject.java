
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>interfaceObject complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡifAdminStatus���Ե�ֵ��
     * 
     */
    public int getIfAdminStatus() {
        return ifAdminStatus;
    }

    /**
     * ����ifAdminStatus���Ե�ֵ��
     * 
     */
    public void setIfAdminStatus(int value) {
        this.ifAdminStatus = value;
    }

    /**
     * ��ȡifDesc���Ե�ֵ��
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
     * ����ifDesc���Ե�ֵ��
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
     * ��ȡifIndex���Ե�ֵ��
     * 
     */
    public int getIfIndex() {
        return ifIndex;
    }

    /**
     * ����ifIndex���Ե�ֵ��
     * 
     */
    public void setIfIndex(int value) {
        this.ifIndex = value;
    }

    /**
     * ��ȡifOperStatus���Ե�ֵ��
     * 
     */
    public int getIfOperStatus() {
        return ifOperStatus;
    }

    /**
     * ����ifOperStatus���Ե�ֵ��
     * 
     */
    public void setIfOperStatus(int value) {
        this.ifOperStatus = value;
    }

    /**
     * ��ȡifPhysAddress���Ե�ֵ��
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
     * ����ifPhysAddress���Ե�ֵ��
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
     * ��ȡifSpeed���Ե�ֵ��
     * 
     */
    public int getIfSpeed() {
        return ifSpeed;
    }

    /**
     * ����ifSpeed���Ե�ֵ��
     * 
     */
    public void setIfSpeed(int value) {
        this.ifSpeed = value;
    }

    /**
     * ��ȡifType���Ե�ֵ��
     * 
     */
    public int getIfType() {
        return ifType;
    }

    /**
     * ����ifType���Ե�ֵ��
     * 
     */
    public void setIfType(int value) {
        this.ifType = value;
    }

    /**
     * ��ȡmonitorStatus���Ե�ֵ��
     * 
     */
    public int getMonitorStatus() {
        return monitorStatus;
    }

    /**
     * ����monitorStatus���Ե�ֵ��
     * 
     */
    public void setMonitorStatus(int value) {
        this.monitorStatus = value;
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
     * ��ȡorigin���Ե�ֵ��
     * 
     */
    public int getOrigin() {
        return origin;
    }

    /**
     * ����origin���Ե�ֵ��
     * 
     */
    public void setOrigin(int value) {
        this.origin = value;
    }

    /**
     * ��ȡphysIndex���Ե�ֵ��
     * 
     */
    public int getPhysIndex() {
        return physIndex;
    }

    /**
     * ����physIndex���Ե�ֵ��
     * 
     */
    public void setPhysIndex(int value) {
        this.physIndex = value;
    }

    /**
     * ��ȡvlanID���Ե�ֵ��
     * 
     */
    public int getVlanID() {
        return vlanID;
    }

    /**
     * ����vlanID���Ե�ֵ��
     * 
     */
    public void setVlanID(int value) {
        this.vlanID = value;
    }

}
