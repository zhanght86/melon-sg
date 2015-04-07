package secfox.soc.business.message;
/**
 * @since 1.0 2014-10-27,下午1:43:16
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public interface MailConstants {

	    /**
	    * 普通邮件
	    */
	   static final int TYPE_DEFAULT = 1;
	   /**
	    * 主题邮件
	    */
	   static final int TYPE_SUBJECT = 2;
	   
	   /**
		 * 收件箱
		 */
	   static final int INBOX = 1;
		/**
		 * 发件箱
		 */
	   static final int OUTBOX = 2;
		/**
		 * 草稿箱
		 */
	   static final int DRAFTS = 3;
		/**
		 * 已删邮件
		 */
	   static final int DELETE = 4;
}

