/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.secpass.service;

import java.util.Date;
import java.util.List;

import secfox.soc.melon.asset.secpass.domain.SecPass;
import secfox.soc.melon.asset.secpass.query.SecPassPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.work.myWork.service.WorkScanService;

/**
 * @since 2014-11-17,下午4:00:22
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface SecPassService extends GenericService<SecPass, Long>, WorkScanService {
	
	/**
	 * 
	 * @param pageQuery
	 * @return
	 */
	Pagination<SecPass> findPagination(SecPassPageQuery pageQuery);
	
	/**
	 * 获取历史记录
	 * @param id
	 * @return
	 */
	List<SecPass> findHistory(Long id);
	
	
	/**
	 * 获取到期的口令数据
	 * @param date
	 * @return
	 */
	List<Object[]> getTask(Date date);
	
	/**
	 * 查询到期口令
	 * @param date
	 * @param userId
	 * @return
	 */
	List<SecPass> findDuePass(Date date, Long userId);
	

}
