
package secfox.soc.integration.sni.wsdl.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>authenticationModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取categoryId属性的值。
     * 
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * 设置categoryId属性的值。
     * 
     */
    public void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     * 获取categoryName属性的值。
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
     * 设置categoryName属性的值。
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
     * 获取description属性的值。
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
     * 设置description属性的值。
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
     * 获取ftpPassword属性的值。
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
     * 设置ftpPassword属性的值。
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
     * 获取ftpPort属性的值。
     * 
     */
    public int getFTPPort() {
        return ftpPort;
    }

    /**
     * 设置ftpPort属性的值。
     * 
     */
    public void setFTPPort(int value) {
        this.ftpPort = value;
    }

    /**
     * 获取ftpUser属性的值。
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
     * 设置ftpUser属性的值。
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
     * 获取getCommunity属性的值。
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
     * 设置getCommunity属性的值。
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
     * 获取instanceName属性的值。
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
     * 设置instanceName属性的值。
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
     * 获取ip属性的值。
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
     * 设置ip属性的值。
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
     * 获取monitorType属性的值。
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
     * 设置monitorType属性的值。
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
     * 获取parentNodeName属性的值。
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
     * 设置parentNodeName属性的值。
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
     * 获取password属性的值。
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
     * 设置password属性的值。
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
     * 获取popPwd属性的值。
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
     * 设置popPwd属性的值。
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
     * 获取popUsr属性的值。
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
     * 设置popUsr属性的值。
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
     * 获取protocol属性的值。
     * 
     */
    public int getProtocol() {
        return protocol;
    }

    /**
     * 设置protocol属性的值。
     * 
     */
    public void setProtocol(int value) {
        this.protocol = value;
    }

    /**
     * 获取protocolName属性的值。
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
     * 设置protocolName属性的值。
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
     * 获取sshPassword属性的值。
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
     * 设置sshPassword属性的值。
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
     * 获取sshPort属性的值。
     * 
     */
    public int getSSHPort() {
        return sshPort;
    }

    /**
     * 设置sshPort属性的值。
     * 
     */
    public void setSSHPort(int value) {
        this.sshPort = value;
    }

    /**
     * 获取sshUser属性的值。
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
     * 设置sshUser属性的值。
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
     * 获取setCommunity属性的值。
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
     * 设置setCommunity属性的值。
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
     * 获取smtpPwd属性的值。
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
     * 设置smtpPwd属性的值。
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
     * 获取smtpUsr属性的值。
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
     * 设置smtpUsr属性的值。
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
     * 获取snmpVersion属性的值。
     * 
     */
    public int getSnmpVersion() {
        return snmpVersion;
    }

    /**
     * 设置snmpVersion属性的值。
     * 
     */
    public void setSnmpVersion(int value) {
        this.snmpVersion = value;
    }

    /**
     * 获取superPsw属性的值。
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
     * 设置superPsw属性的值。
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
     * 获取telnetPassword属性的值。
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
     * 设置telnetPassword属性的值。
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
     * 获取telnetPort属性的值。
     * 
     */
    public int getTELNETPort() {
        return telnetPort;
    }

    /**
     * 设置telnetPort属性的值。
     * 
     */
    public void setTELNETPort(int value) {
        this.telnetPort = value;
    }

    /**
     * 获取telnetUser属性的值。
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
     * 设置telnetUser属性的值。
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
     * 获取type属性的值。
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

    /**
     * 获取uid属性的值。
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
     * 设置uid属性的值。
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
     * 获取userName属性的值。
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
     * 设置userName属性的值。
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
     * 获取v3AuthPassword属性的值。
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
     * 设置v3AuthPassword属性的值。
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
     * 获取v3AuthProtocol属性的值。
     * 
     */
    public int getV3AuthProtocol() {
        return v3AuthProtocol;
    }

    /**
     * 设置v3AuthProtocol属性的值。
     * 
     */
    public void setV3AuthProtocol(int value) {
        this.v3AuthProtocol = value;
    }

    /**
     * 获取v3PrivPassword属性的值。
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
     * 设置v3PrivPassword属性的值。
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
     * 获取v3PrivProtocol属性的值。
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
     * 设置v3PrivProtocol属性的值。
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
     * 获取v3SecurityLevel属性的值。
     * 
     */
    public int getV3SecurityLevel() {
        return v3SecurityLevel;
    }

    /**
     * 设置v3SecurityLevel属性的值。
     * 
     */
    public void setV3SecurityLevel(int value) {
        this.v3SecurityLevel = value;
    }

    /**
     * 获取v3UserName属性的值。
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
     * 设置v3UserName属性的值。
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
     * 获取webPassword属性的值。
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
     * 设置webPassword属性的值。
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
     * 获取webPort属性的值。
     * 
     */
    public int getWebPort() {
        return webPort;
    }

    /**
     * 设置webPort属性的值。
     * 
     */
    public void setWebPort(int value) {
        this.webPort = value;
    }

    /**
     * 获取webProtocol属性的值。
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
     * 设置webProtocol属性的值。
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
     * 获取webRelativePath属性的值。
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
     * 设置webRelativePath属性的值。
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
     * 获取webUser属性的值。
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
     * 设置webUser属性的值。
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
