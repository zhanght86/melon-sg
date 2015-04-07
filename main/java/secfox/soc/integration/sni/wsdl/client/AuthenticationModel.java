
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>authenticationModel complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="authenticationModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FTPPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FTPPort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FTPUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="getCommunity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="instanceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monitorType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentNodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="popPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="popUsr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="protocol" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="protocolName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SSHPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SSHPort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SSHUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="setCommunity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smtpUsr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpVersion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="superPsw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TELNETPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TELNETPort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TELNETUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="v3AuthPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="v3AuthProtocol" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="v3PrivPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="v3PrivProtocol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="v3SecurityLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="v3UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="webPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="webPort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="webProtocol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="webRelativePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="webUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authenticationModel", propOrder = {
    "categoryId",
    "categoryName",
    "description",
    "ftpPassword",
    "ftpPort",
    "ftpUser",
    "getCommunity",
    "id",
    "instanceName",
    "ip",
    "monitorType",
    "parentNodeName",
    "password",
    "popPwd",
    "popUsr",
    "protocol",
    "protocolName",
    "sshPassword",
    "sshPort",
    "sshUser",
    "setCommunity",
    "smtpPwd",
    "smtpUsr",
    "snmpVersion",
    "superPsw",
    "telnetPassword",
    "telnetPort",
    "telnetUser",
    "type",
    "uid",
    "userName",
    "v3AuthPassword",
    "v3AuthProtocol",
    "v3PrivPassword",
    "v3PrivProtocol",
    "v3SecurityLevel",
    "v3UserName",
    "webPassword",
    "webPort",
    "webProtocol",
    "webRelativePath",
    "webUser"
})
public class AuthenticationModel {

    protected int categoryId;
    protected String categoryName;
    protected String description;
    @XmlElement(name = "FTPPassword")
    protected String ftpPassword;
    @XmlElement(name = "FTPPort")
    protected int ftpPort;
    @XmlElement(name = "FTPUser")
    protected String ftpUser;
    protected String getCommunity;
    protected long id;
    protected String instanceName;
    protected String ip;
    protected String monitorType;
    protected String parentNodeName;
    protected String password;
    protected String popPwd;
    protected String popUsr;
    protected int protocol;
    protected String protocolName;
    @XmlElement(name = "SSHPassword")
    protected String sshPassword;
    @XmlElement(name = "SSHPort")
    protected int sshPort;
    @XmlElement(name = "SSHUser")
    protected String sshUser;
    protected String setCommunity;
    protected String smtpPwd;
    protected String smtpUsr;
    protected int snmpVersion;
    protected String superPsw;
    @XmlElement(name = "TELNETPassword")
    protected String telnetPassword;
    @XmlElement(name = "TELNETPort")
    protected int telnetPort;
    @XmlElement(name = "TELNETUser")
    protected String telnetUser;
    protected int type;
    protected String uid;
    protected String userName;
    protected String v3AuthPassword;
    protected int v3AuthProtocol;
    protected String v3PrivPassword;
    protected String v3PrivProtocol;
    protected int v3SecurityLevel;
    protected String v3UserName;
    protected String webPassword;
    protected int webPort;
    protected String webProtocol;
    protected String webRelativePath;
    protected String webUser;

    /**
     * ��ȡcategoryId���Ե�ֵ��
     * 
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * ����categoryId���Ե�ֵ��
     * 
     */
    public void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     * ��ȡcategoryName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * ����categoryName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
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
     * ��ȡftpPassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFTPPassword() {
        return ftpPassword;
    }

    /**
     * ����ftpPassword���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFTPPassword(String value) {
        this.ftpPassword = value;
    }

    /**
     * ��ȡftpPort���Ե�ֵ��
     * 
     */
    public int getFTPPort() {
        return ftpPort;
    }

    /**
     * ����ftpPort���Ե�ֵ��
     * 
     */
    public void setFTPPort(int value) {
        this.ftpPort = value;
    }

    /**
     * ��ȡftpUser���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFTPUser() {
        return ftpUser;
    }

    /**
     * ����ftpUser���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFTPUser(String value) {
        this.ftpUser = value;
    }

    /**
     * ��ȡgetCommunity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetCommunity() {
        return getCommunity;
    }

    /**
     * ����getCommunity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetCommunity(String value) {
        this.getCommunity = value;
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
     * ��ȡinstanceName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * ����instanceName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceName(String value) {
        this.instanceName = value;
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
     * ��ȡmonitorType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonitorType() {
        return monitorType;
    }

    /**
     * ����monitorType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonitorType(String value) {
        this.monitorType = value;
    }

    /**
     * ��ȡparentNodeName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentNodeName() {
        return parentNodeName;
    }

    /**
     * ����parentNodeName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentNodeName(String value) {
        this.parentNodeName = value;
    }

    /**
     * ��ȡpassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * ����password���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * ��ȡpopPwd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopPwd() {
        return popPwd;
    }

    /**
     * ����popPwd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopPwd(String value) {
        this.popPwd = value;
    }

    /**
     * ��ȡpopUsr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopUsr() {
        return popUsr;
    }

    /**
     * ����popUsr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopUsr(String value) {
        this.popUsr = value;
    }

    /**
     * ��ȡprotocol���Ե�ֵ��
     * 
     */
    public int getProtocol() {
        return protocol;
    }

    /**
     * ����protocol���Ե�ֵ��
     * 
     */
    public void setProtocol(int value) {
        this.protocol = value;
    }

    /**
     * ��ȡprotocolName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolName() {
        return protocolName;
    }

    /**
     * ����protocolName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolName(String value) {
        this.protocolName = value;
    }

    /**
     * ��ȡsshPassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSHPassword() {
        return sshPassword;
    }

    /**
     * ����sshPassword���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSHPassword(String value) {
        this.sshPassword = value;
    }

    /**
     * ��ȡsshPort���Ե�ֵ��
     * 
     */
    public int getSSHPort() {
        return sshPort;
    }

    /**
     * ����sshPort���Ե�ֵ��
     * 
     */
    public void setSSHPort(int value) {
        this.sshPort = value;
    }

    /**
     * ��ȡsshUser���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSHUser() {
        return sshUser;
    }

    /**
     * ����sshUser���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSHUser(String value) {
        this.sshUser = value;
    }

    /**
     * ��ȡsetCommunity���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetCommunity() {
        return setCommunity;
    }

    /**
     * ����setCommunity���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetCommunity(String value) {
        this.setCommunity = value;
    }

    /**
     * ��ȡsmtpPwd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpPwd() {
        return smtpPwd;
    }

    /**
     * ����smtpPwd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpPwd(String value) {
        this.smtpPwd = value;
    }

    /**
     * ��ȡsmtpUsr���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpUsr() {
        return smtpUsr;
    }

    /**
     * ����smtpUsr���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpUsr(String value) {
        this.smtpUsr = value;
    }

    /**
     * ��ȡsnmpVersion���Ե�ֵ��
     * 
     */
    public int getSnmpVersion() {
        return snmpVersion;
    }

    /**
     * ����snmpVersion���Ե�ֵ��
     * 
     */
    public void setSnmpVersion(int value) {
        this.snmpVersion = value;
    }

    /**
     * ��ȡsuperPsw���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperPsw() {
        return superPsw;
    }

    /**
     * ����superPsw���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperPsw(String value) {
        this.superPsw = value;
    }

    /**
     * ��ȡtelnetPassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTELNETPassword() {
        return telnetPassword;
    }

    /**
     * ����telnetPassword���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTELNETPassword(String value) {
        this.telnetPassword = value;
    }

    /**
     * ��ȡtelnetPort���Ե�ֵ��
     * 
     */
    public int getTELNETPort() {
        return telnetPort;
    }

    /**
     * ����telnetPort���Ե�ֵ��
     * 
     */
    public void setTELNETPort(int value) {
        this.telnetPort = value;
    }

    /**
     * ��ȡtelnetUser���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTELNETUser() {
        return telnetUser;
    }

    /**
     * ����telnetUser���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTELNETUser(String value) {
        this.telnetUser = value;
    }

    /**
     * ��ȡtype���Ե�ֵ��
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

    /**
     * ��ȡuid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUid() {
        return uid;
    }

    /**
     * ����uid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * ��ȡuserName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ����userName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * ��ȡv3AuthPassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getV3AuthPassword() {
        return v3AuthPassword;
    }

    /**
     * ����v3AuthPassword���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setV3AuthPassword(String value) {
        this.v3AuthPassword = value;
    }

    /**
     * ��ȡv3AuthProtocol���Ե�ֵ��
     * 
     */
    public int getV3AuthProtocol() {
        return v3AuthProtocol;
    }

    /**
     * ����v3AuthProtocol���Ե�ֵ��
     * 
     */
    public void setV3AuthProtocol(int value) {
        this.v3AuthProtocol = value;
    }

    /**
     * ��ȡv3PrivPassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getV3PrivPassword() {
        return v3PrivPassword;
    }

    /**
     * ����v3PrivPassword���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setV3PrivPassword(String value) {
        this.v3PrivPassword = value;
    }

    /**
     * ��ȡv3PrivProtocol���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getV3PrivProtocol() {
        return v3PrivProtocol;
    }

    /**
     * ����v3PrivProtocol���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setV3PrivProtocol(String value) {
        this.v3PrivProtocol = value;
    }

    /**
     * ��ȡv3SecurityLevel���Ե�ֵ��
     * 
     */
    public int getV3SecurityLevel() {
        return v3SecurityLevel;
    }

    /**
     * ����v3SecurityLevel���Ե�ֵ��
     * 
     */
    public void setV3SecurityLevel(int value) {
        this.v3SecurityLevel = value;
    }

    /**
     * ��ȡv3UserName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getV3UserName() {
        return v3UserName;
    }

    /**
     * ����v3UserName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setV3UserName(String value) {
        this.v3UserName = value;
    }

    /**
     * ��ȡwebPassword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebPassword() {
        return webPassword;
    }

    /**
     * ����webPassword���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebPassword(String value) {
        this.webPassword = value;
    }

    /**
     * ��ȡwebPort���Ե�ֵ��
     * 
     */
    public int getWebPort() {
        return webPort;
    }

    /**
     * ����webPort���Ե�ֵ��
     * 
     */
    public void setWebPort(int value) {
        this.webPort = value;
    }

    /**
     * ��ȡwebProtocol���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebProtocol() {
        return webProtocol;
    }

    /**
     * ����webProtocol���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebProtocol(String value) {
        this.webProtocol = value;
    }

    /**
     * ��ȡwebRelativePath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebRelativePath() {
        return webRelativePath;
    }

    /**
     * ����webRelativePath���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebRelativePath(String value) {
        this.webRelativePath = value;
    }

    /**
     * ��ȡwebUser���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebUser() {
        return webUser;
    }

    /**
     * ����webUser���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebUser(String value) {
        this.webUser = value;
    }

}
