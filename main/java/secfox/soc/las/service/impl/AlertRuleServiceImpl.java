/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;

import secfox.soc.las.dao.AlertRuleDao;
import secfox.soc.las.domain.AlertRule;
import secfox.soc.las.service.AlertRuleService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 2015-1-21,下午2:50:48
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class AlertRuleServiceImpl extends GenericServiceImpl<AlertRule, Long> implements
		AlertRuleService {
	
	private AlertRuleDao dao;
	
	@Inject
	public AlertRuleServiceImpl(AlertRuleDao dao) {
		super();
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<AlertRule, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.AlertRuleService#findByRuleId(java.lang.String)
	 */
	@Override
	public AlertRule findByRuleId(String ruleId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select rule from AlertRule rule where rule.ruleId = :ruleId");
		qt.addParameter("ruleId", ruleId);
		return findFirstDomain(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.AlertRuleService#merge(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=false)
	public AlertRule merge(AlertRule rule) {
		Integer[] type = rule.getType();
		if(type != null) {
			rule.setTypes(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(type));
		}
		return super.merge(rule);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.AlertRuleService#findAlertRuleList()
	 */
	@Override
	public List<AlertRule> findAlertRuleList() {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select rule from AlertRule rule");
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.AlertRuleService#removeByRuleId(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeByRuleId(Long id) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "delete from AlertRule where rule = :id ");
		qt.addParameter("id", id);
		dao.execute(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.AlertRuleService#findByRuleId(java.lang.Long)
	 */
	@Override
	public AlertRule findByRuleId(Long ruleId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select alert from AlertRule alert where alert.rule = :ruleId");
		qt.addParameter("ruleId", ruleId);
		return findFirstDomain(qt);
	}
}
