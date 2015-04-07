/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.las.dao.LasRuleDao;
import secfox.soc.las.domain.AlertRule;
import secfox.soc.las.domain.LasRule;
import secfox.soc.las.query.LasRulePageQuery;
import secfox.soc.las.service.AlertRuleService;
import secfox.soc.las.service.LasRuleService;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 2015-1-21,下午2:50:48
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class LasRuleServiceImpl extends GenericServiceImpl<LasRule, Long> implements
	LasRuleService {

	private LasRuleDao dao;
	private AlertRuleService actionService;

	@Inject
	public LasRuleServiceImpl (LasRuleDao dao, AlertRuleService actionService){
		super();
		this.dao = dao;
		this.actionService = actionService;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<LasRule, Long> getDao() {
		return dao;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.las.service.LasRuleService#pagination(secfox.soc.las.query.LasRulePageQuery)
	 */
	@Override
	public Pagination<LasRule> pagination(LasRulePageQuery pageQuery) {
		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<LasRule, LasRulePageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select a from LasRule a ");
			}

			@Override
			public void buildWhere(LasRule s, QueryTemplate qt) {
				if(StringUtils.isNotBlank(s.getName())){
					qt.append(" and a.name like :name");
					qt.addParameter("name", QueryTemplate.buildAllLike(StringUtils.strip(s.getName())));
				}
				if(StringUtils.isNotBlank(s.getCreator().getUsername())) {
					qt.append(" and a.creator.username like :username");
					qt.addParameter("username", QueryTemplate.buildAllLike(StringUtils.strip(s.getCreator().getUsername())));
				}
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				if(StringUtils.isBlank(column)) {
					column = "id";
				}
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(a.id) from LasRule a ");
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.las.service.LasRuleService#addRule(secfox.soc.las.domain.Alert)
	 */
	@Override
	@Transactional
	public void addRule(LasRule rule) {
		//保存告警规则
		super.persist(rule);
		//设置规则和动作关系
		AlertRule action = rule.getAlertRule();
		action.setRule(rule.getId());
		//保存动作
		actionService.merge(rule.getAlertRule());
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.las.service.LasRuleService#findRule(java.lang.Long)
	 */
	@Override
	public LasRule findRule(Long id) {
		//获取规则
		LasRule rule = find(id);
		//获取相应的告警
		AlertRule alertRule = actionService.findByRuleId(id);
		rule.setAlertRule(alertRule);
		return rule;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.LasRuleService#mergeRule(secfox.soc.las.domain.LasRule)
	 */
	@Override
	@Transactional
	public void mergeRule(LasRule rule) {
		//更新规则
		super.merge(rule);
		//更新动作
		actionService.merge(rule.getAlertRule());
	}
	
	@Override
	@Transactional
	public void remove(Long id) {
		//删除规则
		super.remove(id);
		//删除动作配置
		actionService.removeByRuleId(id);
	}
	
}
