
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getLinkFlow complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="getLinkFlow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="periodType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLinkFlow", propOrder = {
    "periodType"
})
public class GetLinkFlow {

    protected int periodType;

    /**
     * ��ȡperiodType���Ե�ֵ��
     * 
     */
    public int getPeriodType() {
        return periodType;
    }

    /**
     * ����periodType���Ե�ֵ��
     * 
     */
    public void setPeriodType(int value) {
        this.periodType = value;
    }

}
