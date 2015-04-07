package secfox.soc.business.message.service;

import java.util.List;

import secfox.soc.business.message.domain.SysMail;
import secfox.soc.business.message.domain.SysMailReply;
import secfox.soc.business.message.domain.SysMailState;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014-10-20,下午3:24:04
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public interface SysMailService extends GenericService<SysMail, Long> {

	/**
	 * 分页查询信息
	 * @param pageQuery
	 * @return
	 */
	// Pagination<SysMail> findPage(SysMailPageQuery pageQuery);
	 
	 
	 SysMailState findByMailIdAndUserId(Long mailId, Long userId);
	 
	 
	 List<SysMailState> finMailStatesById(Long msgId);

 	 //主题邮件分页查询
 	 //public Pagination<SysMail> findSubjectPages(SysMailPageQuery query);

     //发邮件
     public void sendMail(SysMail mail);

     //收邮件
     public void draftMail(SysMail mail);
     
     /**
      * 删除邮件
	  * @param stateId MailMessageState 主键
	  * @param type 1:收件箱;2:发件箱;3:草稿箱;4:已删邮件
      */
     public void removeMail(Long stateId, int type);
     
     //发主题邮件
     public void sendSubjectMail(SysMail mail);
     
     //回执邮件
     void receipt(Long stateId);
     
     //回复列表
     List<SysMailReply> findReplyList(Long mailId);
}

