package secfox.soc.melon.security;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @since 1.0 2014-10-13,下午3:45:56
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public class MyAuthenticator extends Authenticator {
	String userName=null;
	 String password=null;
	
	 public MyAuthenticator(){
	 }
	 public MyAuthenticator(String username, String password) {
	 this.userName = username;
	 this.password = password;
	 }
	 protected PasswordAuthentication getPasswordAuthentication(){
	 return new PasswordAuthentication(userName, password);
	 } 
	}

