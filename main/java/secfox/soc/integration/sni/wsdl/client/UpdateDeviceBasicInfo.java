
package secfox.soc.integration.sni.wsdl.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>updateDeviceBasicInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="updateDeviceBasicInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ciid" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="properties" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateDeviceBasicInfo", propOrder = {
    "ciid",
    "properties"
})
public class UpdateDeviceBasicInfo {

    protected long ciid;
    protected List<Object> properties;

    /**
     * ��ȡciid���Ե�ֵ��
     * 
     */
    public long getCiid() {
        return ciid;
    }

    /**
     * ����ciid���Ե�ֵ��
     * 
     */
    public void setCiid(long value) {
        this.ciid = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the properties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getProperties() {
        if (properties == null) {
            properties = new ArrayList<Object>();
        }
        return this.properties;
    }

}
