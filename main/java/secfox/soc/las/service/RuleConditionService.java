/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.service;

import java.util.List;

import secfox.soc.las.domain.RuleCondition;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 2015-2-2,下午3:37:24
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface RuleConditionService extends GenericService<RuleCondition, Long> {
	
	/**
	 * 创建条件的根节点,虚拟节点,不存储在数据库
	 * @param ruleType
	 * @return
	 */
	RuleCondition createRoot(int ruleType);
	
	/**
	 * 根据规则类型查询条件树
	 * @param ruleType
	 * @param ruleId
	 * @return
	 */
	List<RuleCondition> findTree(int ruleType, Long ruleId);
	
	/**
	 * 拼接节点内容
	 * @param id 节点id
	 */
	String assemContent(Long id);
	
	/**
	 * 新建节点
	 * @param condition
	 * @return
	 */
	List<RuleCondition> add(RuleCondition condition);
	
	/**
	 * 按规则id查询
	 * @param ruleType
	 * @param ruleId
	 * @return
	 */
	List<RuleCondition> findByRuleId(int ruleType, Long ruleId);
	
	/**
	 * 按规则id查询事件
	 * @param ruleId
	 * @return
	 */
	List<RuleCondition> findEveByRuleId(Long ruleId);
	
}
