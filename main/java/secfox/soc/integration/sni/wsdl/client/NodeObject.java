
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>nodeObject complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取beanid属性的值。
     * 
     */
    public long getBeanid() {
        return beanid;
    }

    /**
     * 设置beanid属性的值。
     * 
     */
    public void setBeanid(long value) {
        this.beanid = value;
    }

    /**
     * 获取domainid属性的值。
     * 
     */
    public long getDomainid() {
        return domainid;
    }

    /**
     * 设置domainid属性的值。
     * 
     */
    public void setDomainid(long value) {
        this.domainid = value;
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
     * 获取nodeObject属性的值。
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
     * 设置nodeObject属性的值。
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
     * 获取parentID属性的值。
     * 
     */
    public long getParentID() {
        return parentID;
    }

    /**
     * 设置parentID属性的值。
     * 
     */
    public void setParentID(long value) {
        this.parentID = value;
    }

    /**
     * 获取position属性的值。
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
     * 设置position属性的值。
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
     * 获取proxyType属性的值。
     * 
     */
    public int getProxyType() {
        return proxyType;
    }

    /**
     * 设置proxyType属性的值。
     * 
     */
    public void setProxyType(int value) {
        this.proxyType = value;
    }

}
