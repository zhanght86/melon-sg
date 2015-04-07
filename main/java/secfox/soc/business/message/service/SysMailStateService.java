package secfox.soc.business.message.service;

import java.util.List;

import secfox.soc.business.message.domain.SysMailState;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014-10-27,下午6:25:39
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public interface SysMailStateService extends GenericService<SysMailState, Long> {


	/**
	 * @param messageId
	 * @param userId
	 * @return
	 */
	SysMailState findByMailIdAndUserId(Long mailId, Long userId);

	/**
	 * 
	 * @param msgId
	 * @return
	 */
	List<SysMailState> finMailStatesById(Long mailId);
	
	
}

