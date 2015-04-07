/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.las.dao.LasRuleCountsDao;
import secfox.soc.las.dao.LasRuleDao;
import secfox.soc.las.domain.AlertRule;
import secfox.soc.las.domain.LasRule;
import secfox.soc.las.domain.LasRuleCounts;
import secfox.soc.las.service.AlertRuleService;
import secfox.soc.las.service.LasRuleCountsService;
import secfox.soc.las.service.LasRuleService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 2015-1-21,下午2:50:48
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class LasRuleCountsServiceImpl extends GenericServiceImpl<LasRuleCounts, Long> implements
	LasRuleCountsService {

	private LasRuleCountsDao lasRuleCountsDao;
	private LasRuleDao dao;
	private AlertRuleService actionService;

	@Inject
	public LasRuleCountsServiceImpl (LasRuleCountsDao lasRuleCountsDao,LasRuleDao dao, AlertRuleService actionService){
		super();
		this.dao = dao;
		this.actionService = actionService;
		this.lasRuleCountsDao = lasRuleCountsDao;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<LasRuleCounts, Long> getDao() {
		return lasRuleCountsDao;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.las.service.LasRuleService#addRule(secfox.soc.las.domain.Alert)
	 */
	@Override
	@Transactional
	public void addRule(LasRuleCounts rule) {
		//保存告警规则
		super.persist(rule);
		//设置规则和动作关系
//		AlertRule action = rule.getAlertRule();
//		action.setRule(rule.getId());
//		//保存动作
//		actionService.persist(rule.getAlertRule());
	}
	
}
