/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.work.plan.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.work.plan.dao.PlanDao;
import secfox.soc.melon.work.plan.domain.Plan;

/**
 * @since 2014-11-6,下午3:47:13
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class PlanDaoImpl extends GenericDaoImpl<Plan, Long> implements PlanDao {

	public PlanDaoImpl() {
		super(Plan.class);
	}

}
