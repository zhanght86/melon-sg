/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.work.plan.service;

import java.util.List;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.work.plan.domain.Plan;

/**
 * @since 2014-11-6,下午3:48:52
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface PlanService extends GenericService<Plan, Long> {
	
	/**
	 * 获取当前时间段内的计划
	 * @param start
	 * @param end
	 * @param userId
	 * @return
	 */
	List<Plan> find(String start, String end, Long userId);

}
