
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ipAddrObject complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡassignTime���Ե�ֵ��
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
     * ����assignTime���Ե�ֵ��
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
     * ��ȡipAddr���Ե�ֵ��
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
     * ����ipAddr���Ե�ֵ��
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
     * ��ȡisAssigned���Ե�ֵ��
     * 
     */
    public int getIsAssigned() {
        return isAssigned;
    }

    /**
     * ����isAssigned���Ե�ֵ��
     * 
     */
    public void setIsAssigned(int value) {
        this.isAssigned = value;
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
     * ��ȡnetAddr���Ե�ֵ��
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
     * ����netAddr���Ե�ֵ��
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
     * ��ȡoutIpAddr���Ե�ֵ��
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
     * ����outIpAddr���Ե�ֵ��
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
     * ��ȡoutMask���Ե�ֵ��
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
     * ����outMask���Ե�ֵ��
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
     * ��ȡoutNetAddr���Ե�ֵ��
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
     * ����outNetAddr���Ե�ֵ��
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
