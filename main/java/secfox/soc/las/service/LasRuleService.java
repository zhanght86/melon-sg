/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.service;

import secfox.soc.las.domain.LasRule;
import secfox.soc.las.query.LasRulePageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 2015-1-21,上午10:27:28
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface LasRuleService extends GenericService<LasRule, Long> {
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	public Pagination<LasRule> pagination(LasRulePageQuery pageQuery);
	
	/**
	 * 获取规则
	 * @param id
	 * @return
	 */
	public LasRule findRule(Long id);
	
	/**
	 * 添加规则
	 * @param alert
	 */
	public void addRule(LasRule rule);
	
	/**
	 * 修改规则
	 * @param rule
	 */
	public void mergeRule(LasRule rule);
	
}
