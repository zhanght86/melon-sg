/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.work.plan.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.work.plan.dao.PlanDao;
import secfox.soc.melon.work.plan.domain.Plan;
import secfox.soc.melon.work.plan.service.PlanService;

/**
 * @since 2014-11-6,下午3:49:43
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class PlanServiceImpl extends GenericServiceImpl<Plan, Long> implements
		PlanService {

	private PlanDao dao;
	
	@Inject
	public PlanServiceImpl(PlanDao dao) {
		super();
		this.dao = dao;
		
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Plan, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.plan.service.PlanService#find(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Plan> find(String start, String end, Long userId) {
		
		DateFormat dateFormater = DateTimeType.SHORT_DATE.getFormatter();
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = dateFormater.parse(start);
			endDate = dateFormater.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "plan.find");
		qt.addParameter("start", startDate);
		qt.addParameter("end", endDate);
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}


}
