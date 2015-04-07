/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.system.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.system.dao.WorkCalendarDao;
import secfox.soc.melon.system.domain.WorkCalendar;
import secfox.soc.melon.system.service.WorkCalendarService;

import com.google.common.collect.Sets;

/**
 * @since 2014-10-9,下午1:53:52
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class WorkCalendarServiceImpl extends GenericServiceImpl<WorkCalendar, Long>
		implements WorkCalendarService {
	
	private WorkCalendarDao dao;
	
	@Inject
	public WorkCalendarServiceImpl(WorkCalendarDao dao) {
		super();
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<WorkCalendar, Long> getDao() {
		return dao;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.system.service.WorkCalendarService#setWork(java.util.Date)
	 */
	@Override
	@Transactional(readOnly=false)
	public WorkCalendar update(WorkCalendar workCalendar) {
		workCalendar.setDateFlag(true);
		return merge(workCalendar);

	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.system.service.WorkCalendarService#calendarInit(java.util.Date, java.util.Date)
	 */
	@Override
	public Collection<WorkCalendar> find(String startTime, String endTime) {
		//
		DateFormat dateFormater = DateTimeType.SHORT_DATE.getFormatter();
		Date startDate = new Date();
		Date endDate= new Date();
		try {
			startDate = dateFormater.parse(startTime);
			endDate = dateFormater.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar startCal = DateUtils.toCalendar(startDate);
		Calendar endCal = DateUtils.toCalendar(endDate);
		//
		Set<WorkCalendar> result = Sets.newHashSet();
		while(startCal.before(endCal)) {
			WorkCalendar workCal = new WorkCalendar();
			workCal.setDate(startCal.getTime());
			result.add(workCal);
			startCal.add(Calendar.DAY_OF_WEEK, 1);
		}
		//查询数据库当前时间段内的信息
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "workCalendar.findByTime");
		qt.addParameter("start", startDate);
		qt.addParameter("end", endDate);
		//替换信息
		List<WorkCalendar> exists = findDomains(qt);
		if(result.containsAll(exists)){
			result.removeAll(exists);//删除原有的初始信息
			result.addAll(exists);//重新添加数据库信息
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.system.service.WorkCalendarService#confirm(java.lang.String)
	 */
	@Override
	public boolean confirm(String date, boolean workDay) {
		DateFormat dateFormater = DateTimeType.SHORT_DATE.getFormatter();
		Date now = new Date();
		try {
			now = dateFormater.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		WorkCalendar workCal = new WorkCalendar();
		workCal.setDate(now);
		return workCal.isWorkDay() == workDay;
	}
	
}
