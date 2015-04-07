/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.service;

import java.util.List;

import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.alarm.query.AlarmPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
 *
 * @since 1.0 2014年9月29日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public interface AlarmService extends GenericService<Alarm, Long> {
	/**
	 * 告警的分页查询信息
	 * @param pageQuery
	 * @return
	 */
	Pagination<Alarm> findPage(AlarmPageQuery pageQuery);
	
	
	List<Object[]> listDevicesCount();
	
	List<Object[]> listTypesCount();
	
	List<Object[]> listDaysCount();
	
	List<Object[]> listHandleCount();
	
	List<Object[]> listTodayCount();
	
	List<Object[]> listWeekCount();
}
