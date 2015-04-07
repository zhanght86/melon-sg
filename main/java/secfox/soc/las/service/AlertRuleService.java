/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.service;

import java.util.List;

import secfox.soc.las.domain.AlertRule;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 2015-1-21,上午10:27:28
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface AlertRuleService extends GenericService<AlertRule, Long> {
	
	/**
	 * 按规则id查
	 * @param ruleId
	 * @return
	 */
	public AlertRule findByRuleId(String ruleId);
	
	/**
	 * 按规则id查
	 * @param ruleId
	 * @return
	 */
	public AlertRule findByRuleId(Long ruleId);
	
	/**
	 * 查询所有已配置动作的规则
	 * @return
	 */
	public List<AlertRule> findAlertRuleList();
	
	/**
	 * 按告警id删除动作配置
	 * @param ruleId
	 */
	public void removeByRuleId(Long ruleId);
	
}
