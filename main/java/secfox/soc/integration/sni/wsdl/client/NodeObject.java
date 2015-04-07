
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>nodeObject complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="nodeObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beanid" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="domainid" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeObject" type="{http://service.soa.system.soc.secfox/}nodeObject" minOccurs="0"/>
 *         &lt;element name="parentID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="position" type="{http://service.soa.system.soc.secfox/}point" minOccurs="0"/>
 *         &lt;element name="proxyType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nodeObject", propOrder = {
    "beanid",
    "domainid",
    "id",
    "name",
    "nodeObject",
    "parentID",
    "position",
    "proxyType"
})
@XmlSeeAlso({
    DeviceObject.class
})
public class NodeObject {

    protected long beanid;
    protected long domainid;
    protected long id;
    protected String name;
    protected NodeObject nodeObject;
    protected long parentID;
    protected Point position;
    protected int proxyType;

    /**
     * ��ȡbeanid���Ե�ֵ��
     * 
     */
    public long getBeanid() {
        return beanid;
    }

    /**
     * ����beanid���Ե�ֵ��
     * 
     */
    public void setBeanid(long value) {
        this.beanid = value;
    }

    /**
     * ��ȡdomainid���Ե�ֵ��
     * 
     */
    public long getDomainid() {
        return domainid;
    }

    /**
     * ����domainid���Ե�ֵ��
     * 
     */
    public void setDomainid(long value) {
        this.domainid = value;
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
     * ��ȡnodeObject���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link NodeObject }
     *     
     */
    public NodeObject getNodeObject() {
        return nodeObject;
    }

    /**
     * ����nodeObject���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link NodeObject }
     *     
     */
    public void setNodeObject(NodeObject value) {
        this.nodeObject = value;
    }

    /**
     * ��ȡparentID���Ե�ֵ��
     * 
     */
    public long getParentID() {
        return parentID;
    }

    /**
     * ����parentID���Ե�ֵ��
     * 
     */
    public void setParentID(long value) {
        this.parentID = value;
    }

    /**
     * ��ȡposition���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getPosition() {
        return position;
    }

    /**
     * ����position���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setPosition(Point value) {
        this.position = value;
    }

    /**
     * ��ȡproxyType���Ե�ֵ��
     * 
     */
    public int getProxyType() {
        return proxyType;
    }

    /**
     * ����proxyType���Ե�ֵ��
     * 
     */
    public void setProxyType(int value) {
        this.proxyType = value;
    }

}
