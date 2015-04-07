/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.service;

import secfox.soc.las.domain.LasRule;
import secfox.soc.las.domain.LasRuleCounts;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 2015-1-21,上午10:27:28
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface LasRuleCountsService extends GenericService<LasRuleCounts, Long> {
	
	
	/**
	 * 添加规则
	 * @param alert
	 */
	public void addRule(LasRuleCounts rule);
	
}
