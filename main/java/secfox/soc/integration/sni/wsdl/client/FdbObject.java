
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>fdbObject complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="fdbObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tempFlag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fdbObject", propOrder = {
    "id",
    "mac",
    "nodeID",
    "port",
    "status",
    "tempFlag"
})
public class FdbObject {

    protected long id;
    protected String mac;
    protected long nodeID;
    protected int port;
    protected int status;
    protected int tempFlag;

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
     * ��ȡport���Ե�ֵ��
     * 
     */
    public int getPort() {
        return port;
    }

    /**
     * ����port���Ե�ֵ��
     * 
     */
    public void setPort(int value) {
        this.port = value;
    }

    /**
     * ��ȡstatus���Ե�ֵ��
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * ����status���Ե�ֵ��
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * ��ȡtempFlag���Ե�ֵ��
     * 
     */
    public int getTempFlag() {
        return tempFlag;
    }

    /**
     * ����tempFlag���Ե�ֵ��
     * 
     */
    public void setTempFlag(int value) {
        this.tempFlag = value;
    }

}
