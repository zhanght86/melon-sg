package secfox.soc.melon.security.service;

import java.util.List;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.security.domain.HistoryPass;

public interface HistoryPassService extends GenericService<HistoryPass, Long> {
	
	/**
	 * 按时间查历史密码
	 * @param accountId
	 * @param num
	 * @return
	 */
	public List<HistoryPass> findHistory(Long accountId, int num);
	
}
