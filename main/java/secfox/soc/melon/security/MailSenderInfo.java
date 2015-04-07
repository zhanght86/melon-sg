package secfox.soc.melon.security;
/**
 * @since 1.0 2014-10-13,下午3:36:41
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
import java.util.Properties;

public class MailSenderInfo {
	private String mailServerHost;
	private String mailServerPort = "25";
	private String fromAddress;
	private String toAddress;
	private String userName;
	private String password;
	private boolean validate = false;
    private String subject;
    private String content;
    private String[] attachFileNames;


 public Properties getProperties(){
		Properties p = new Properties();
			p.put("mail.smtp.host", this.mailServerHost);
			p.put("mail.smtp.port", this.mailServerPort);
			p.put("mail.smtp.auth", validate ? "true" : "false");
			return p;
      }


/**
 * @return the mailServerHost
 */
public String getMailServerHost() {
	return mailServerHost;
}


/**
 * @param mailServerHost the mailServerHost to set
 */
public void setMailServerHost(String mailServerHost) {
	this.mailServerHost = mailServerHost;
}


/**
 * @return the mailServerPort
 */
public String getMailServerPort() {
	return mailServerPort;
}


/**
 * @param mailServerPort the mailServerPort to set
 */
public void setMailServerPort(String mailServerPort) {
	this.mailServerPort = mailServerPort;
}


/**
 * @return the fromAddress
 */
public String getFromAddress() {
	return fromAddress;
}


/**
 * @param fromAddress the fromAddress to set
 */
public void setFromAddress(String fromAddress) {
	this.fromAddress = fromAddress;
}


/**
 * @return the toAddress
 */
public String getToAddress() {
	return toAddress;
}


/**
 * @param toAddress the toAddress to set
 */
public void setToAddress(String toAddress) {
	this.toAddress = toAddress;
}


/**
 * @return the userName
 */
public String getUserName() {
	return userName;
}


/**
 * @param userName the userName to set
 */
public void setUserName(String userName) {
	this.userName = userName;
}


/**
 * @return the password
 */
public String getPassword() {
	return password;
}


/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}


/**
 * @return the validate
 */
public boolean isValidate() {
	return validate;
}


/**
 * @param validate the validate to set
 */
public void setValidate(boolean validate) {
	this.validate = validate;
}


/**
 * @return the subject
 */
public String getSubject() {
	return subject;
}


/**
 * @param subject the subject to set
 */
public void setSubject(String subject) {
	this.subject = subject;
}


/**
 * @return the content
 */
public String getContent() {
	return content;
}


/**
 * @param content the content to set
 */
public void setContent(String content) {
	this.content = content;
}


/**
 * @return the attachFileNames
 */
public String[] getAttachFileNames() {
	return attachFileNames;
}


/**
 * @param attachFileNames the attachFileNames to set
 */
public void setAttachFileNames(String[] attachFileNames) {
	this.attachFileNames = attachFileNames;
}

 
}

