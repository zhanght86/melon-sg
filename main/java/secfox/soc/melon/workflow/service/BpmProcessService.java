/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.workflow.service;

import javax.servlet.http.HttpServletRequest;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.workflow.domain.BpmProcess;
import secfox.soc.melon.workflow.query.BpmProcessPageQuery;

/**
 * 用户管理接口
 * @since 2014-9-23,上午19:34:09
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version  1.0
 */
public interface BpmProcessService extends GenericService<BpmProcess, Long> {

	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	Pagination<BpmProcess> findPage(BpmProcessPageQuery pageQuery);
	
	/**
	 * 存储流程信息
	 * @return
	 */
	BpmProcess drawProps(BpmProcess bpm, HttpServletRequest request);
	
	
}
