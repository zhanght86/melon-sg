/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import java.util.List;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.system.domain.Logger;
import secfox.soc.melon.system.query.LoggerPageQuery;

/**
 *
 * @since 1.0 2014-10-8 下午8:30:59
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
public interface LoggerService extends GenericService<Logger, Long> {
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	 Pagination<Logger> findPage(LoggerPageQuery pageQuery);
	
	/**
	 * 通过用户名分页查询
	 * @param pageQuery
	 * @return
	 */
	 Pagination<Object[]> findPageByUserName(LoggerPageQuery pageQuery);
	/**
	 * 根据用户ID查找该用户今天的所有操作
	 * @param userId 用户ID
	 * @return 
	 */
	 List<Logger> findAllFunctionByUserId(Long userId);
	
	/**
	 * 查询活跃用户
	 * @param pageQuery
	 * @return
	 */
	 Pagination<Object[]> findActiveUser(LoggerPageQuery pageQuery);
	
}
