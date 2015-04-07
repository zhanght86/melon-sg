/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.las.dao.RuleConditionDao;
import secfox.soc.las.domain.RuleCondition;
import secfox.soc.las.service.RuleConditionService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 2015-2-2,下午3:38:55
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class RuleConditionServiceImpl extends GenericServiceImpl<RuleCondition, Long>
		implements RuleConditionService {
	
	private RuleConditionDao dao;
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<RuleCondition, Long> getDao() {
		return dao;
	}
	
	@Inject
	public RuleConditionServiceImpl(RuleConditionDao dao) {
		super();
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.RuleConditionService#createRoot()
	 */
	@Override
	public RuleCondition createRoot(int ruleType) {
		RuleCondition condition = new RuleCondition();
		condition.setId(BaseConstants.ROOT_ID);
		condition.setName("规则条件");
		condition.setParentId(null);
		condition.setRuleType(ruleType);
		//设为根节点
		condition.setAsRoot(true);
		//节点打开
		condition.getState().setOpened(true);
		return condition;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.RuleConditionService#findTree(int)
	 */
	@Override
	public List<RuleCondition> findTree(int ruleType, Long ruleId) {
		//按规则查询
		/*QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select condition from RuleCondition condition where condition.ruleType = :ruleType ");
		qt.addParameter("ruleType", ruleType);
		List<RuleCondition> results = findDomains(qt);*/
		List<RuleCondition> results = findByRuleId(ruleType, ruleId);
		//结果添加根节点
		RuleCondition root = createRoot(ruleType);
		results.add(root);
		return results;
	}
	
	@Override
	public RuleCondition find(Long id) {
		RuleCondition condition = super.find(id);
		if(condition.getConditionType() == 0) {
			condition.setContent(assemContent(id));
		}
		return condition;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.las.service.RuleConditionService#add(secfox.soc.las.domain.RuleCondition)
	 */
	@Override
	@Transactional
	public List<RuleCondition> add(RuleCondition condition) {
		List<RuleCondition> results = Lists.newArrayList();
		Long parentId = condition.getParentId();
		//父节点
		RuleCondition parent = new RuleCondition();
		if(parentId.compareTo(BaseConstants.ROOT_ID) == 0) {
			parent = createRoot(condition.getRuleType());
		} else {
			parent = find(parentId);
		}
		//获取父节点下的条件子节点
		RuleCondition silbing = findCondNode(parentId);
		//如果同时满足类型为条件节点、父节点为事件节点并且父节点下有条件子节点
		if(condition.getConditionType() == 2 && parent.getConditionType() == 0 && silbing != null) {
			//添加逻辑节点
			RuleCondition logicNode = new RuleCondition();
			logicNode.setParentId(parentId);
			logicNode.setConditionType(1);
			logicNode.setRuleType(condition.getRuleType());
			logicNode.setLogicalOper("AND");
			logicNode.setName("AND");
			logicNode.setRuleId(condition.getRuleId());
			persist(logicNode);
			//条件节点改到逻辑节点下
			condition.setParentId(logicNode.getId());
			persist(condition);
			//兄弟节点改到逻辑节点下
			silbing.setParentId(logicNode.getId());
			merge(silbing);
			results.add(logicNode);
			results.add(silbing);
		} else {
			persist(condition);
		}
		results.add(condition);
		return results;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.las.service.RuleConditionService#assemContent(java.lang.Long)
	 */
	@Override
	public String assemContent(Long id) {
		
		List<RuleCondition> nodes = getChildren(id);
		if(nodes.size()>0) {
			//根据特性，事件节点下只能有一个子节点
			RuleCondition children = nodes.get(0);
			if(children.getConditionType() == 2) {
				//条件节点
				return children.getContent();
			} else {
				//逻辑节点
				return handler(children, getChildren(children.getId()), false);
			}
		} else {
			return BaseConstants.BLANK_STRING;
		}
	}
	
	/**
	 * 处理子节点内容
	 * @param firNode 一级逻辑节点
	 * @param nodes 子节点
	 * @param inside 是否嵌套节点
	 * @return
	 */
	private String handler(RuleCondition firNode, List<RuleCondition> nodes, boolean inside) {
		
		String content = BaseConstants.BLANK_STRING;
		int index = 0;
		for(RuleCondition condition : nodes) {
			
			if(condition.getConditionType() == 1) {
				//包括逻辑节点
				String tmp = handler(condition, getChildren(condition.getId()), true);
				//逻辑节点不为空
				if(!tmp.equals("")) {
					content += tmp + firNode.getLogicalOper();
				}
			} else {
				//条件节点
				content += BaseConstants.SPACING_STRING + condition.getContent() + BaseConstants.SPACING_STRING + firNode.getLogicalOper();
			}
				
		}
		//获取最后一个连接词
		if(content.endsWith(firNode.getLogicalOper())) {
			index = content.lastIndexOf(firNode.getLogicalOper());
		}
		//省略最后一个连接词
		String result = content.substring(0, index);
		if(inside) {
			//嵌套逻辑节点
			if(result.equals(BaseConstants.BLANK_STRING)) {
				return BaseConstants.BLANK_STRING;
			}
			return BaseConstants.PREFIX_BRACKET + result + BaseConstants.SUFFIX_BRACKET;
		} 
		return result;
		
	}
	
	/**
	 * 查找事件节点的条件子节点
	 * @param id
	 * @return
	 */
	private RuleCondition findCondNode(Long id) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select condition from RuleCondition condition where condition.parentId = :id and condition.conditionType = 2 ");
		qt.addParameter("id", id);
		return findFirstDomain(qt);
	}
	
	/**
	 * 获取子节点
	 * @param id
	 * @return
	 */
	private List<RuleCondition> getChildren(Long id) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select condition from RuleCondition condition where condition.parentId = :id ");
		qt.addParameter("id", id);
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.RuleConditionService#findByRuleId(java.lang.Long)
	 */
	@Override
	public List<RuleCondition> findByRuleId(int ruleType, Long ruleId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select condition from RuleCondition condition where condition.ruleId = :ruleId and condition.ruleType = :ruleType");
		qt.addParameter("ruleId", ruleId);
		qt.addParameter("ruleType", ruleType);
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.RuleConditionService#findEveByRuleId(java.lang.Long)
	 */
	@Override
	public List<RuleCondition> findEveByRuleId(Long ruleId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select condition from RuleCondition condition where condition.ruleId = :ruleId and condition.conditionType = 0");
		qt.addParameter("ruleId", ruleId);
		return findDomains(qt);
	}

}
