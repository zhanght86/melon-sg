package secfox.soc.business.message.service;

import java.util.List;

import secfox.soc.business.message.domain.SysMailReply;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014-10-27,下午7:26:42
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public interface SysMailReplyService extends GenericService<SysMailReply, Long> {

	
	/**
	 * @param messageId
	 * @return
	 * @author <a href="mailto:wangxba@legendsec.com">王新兵</a>  
	 */
	List<SysMailReply> findList(Long mailId);
}

