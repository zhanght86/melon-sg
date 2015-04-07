/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import java.text.ParseException;
import java.util.Collection;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.system.domain.WorkCalendar;

/**
 * @since 2014-10-9,下午1:52:45
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface WorkCalendarService extends GenericService<WorkCalendar, Long> {
	
	/**
	 * 将日期置为反向  默认周一到周五为工作日，周六、日为休息日
	 * @param date 选择日期
	 */
	WorkCalendar update(WorkCalendar workCalendar);
	
	/**
	 * 初始化日历
	 * @param startTime
	 * @param endTime
	 * @throws ParseException 
	 */
	Collection<WorkCalendar> find(String startTime, String endTime);
	
	/**
	 * 判断一天是工作日还是休息日
	 * @param date
	 * @return
	 */
	boolean confirm(String date, boolean workDay);
	
	

}
