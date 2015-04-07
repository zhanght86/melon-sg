/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.alarm.dao.AlarmDao;
import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.alarm.query.AlarmPageQuery;
import secfox.soc.melon.alarm.query.AlarmSearch;
import secfox.soc.melon.alarm.service.AlarmService;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.persistence.templates.QueryTemplateManager;

/**
 *
 * @since 1.0 2014年9月29日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
@Service
public class AlarmServiceImpl extends GenericServiceImpl<Alarm, Long> implements AlarmService {
	
	private AlarmDao alarmDao;
	
	/**
	 * @param alarmDao
	 */
	@Inject
	public AlarmServiceImpl(AlarmDao alarmDao) {
		super();
		this.alarmDao = alarmDao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Alarm, Long> getDao() {
		return this.alarmDao;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.service.AlarmService#findPage(secfox.soc.melon.alarm.query.AlarmPageQuery)
	 */
	@Override
	public Pagination<Alarm> findPage(AlarmPageQuery pageQuery) {
		return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<AlarmSearch, AlarmPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("SELECT a FROM Alarm a");
			}

			@Override
			public void buildWhere(AlarmSearch s, QueryTemplate qt) {
				// 标题
				String title = s.getTitle();
				if (StringUtils.isNotBlank(title)) {
					qt.append(" AND a.title LIKE :title");
					qt.addParameter("title", QueryTemplate.buildAllLike(title));
				}
				// 告警分类
				if (s.getType() != null) {
					qt.append(" AND a.type = :type");
					qt.addParameter("type", s.getType());
				}
				// 告警级别,分5级,默认查询时-1
				int level = s.getLevel();
				if (level > 0) {
					qt.append(" AND a.level = :level");
					qt.addParameter("level", level);
				}
				
				//开始时间
				Date startTime = s.getStartTime();
				if(startTime != null){
					qt.append(" and a.occurTime >= :startTime ");
					qt.addParameter("startTime", startTime);
				}
				//结束时间
				Date endTime = s.getEndTime();
				if(endTime != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(endTime);
					cal.add(Calendar.DAY_OF_WEEK, 1);
					qt.append(" and a.occurTime <= :endTime ");
					qt.addParameter("endTime",cal.getTime());
				}
				
				// IP
				String deviceIp = s.getDeviceIp();
				if (StringUtils.isNotBlank(deviceIp)) {
					qt.append(" AND a.deviceIp like :deviceIp");
					qt.addParameter("deviceIp", QueryTemplate.buildAllLike(deviceIp));
				}
				// 告警状态, 0:待确认,1:已忽略,2:待处理;3:处理中;4:已处理；
				int state = s.getState();
				if (state > 0) {
					qt.append(" AND a.state = :state");
					qt.addParameter("state", state);
				}
			}

			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				if (StringUtils.isBlank(column))
					column = "occurTime";
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("SELECT COUNT(a.id) FROM Alarm a");
			}
		});
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.service.AlarmService#listDevicesCount()
	 */
	@Override
	public List<Object[]> listDevicesCount() {
		//统计告警次数最多的前五个设备
		QueryTemplate qt = QueryTemplateManager.find("alarm.groupByDeviceId");
		qt.setMaxResults(5);
		return find(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.service.AlarmService#listTypesCount()
	 */
	@Override
	public List<Object[]> listTypesCount() {
		QueryTemplate qt = QueryTemplateManager.find("alarm.groupByType");
		qt.setMaxResults(5);
		return find(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.service.AlarmService#listDaysCount()
	 */
	@Override
	public List<Object[]> listDaysCount() {
		QueryTemplate qt = QueryTemplateManager.find("alarm.groupByDay");
		qt.setMaxResults(5);
		return find(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.service.AlarmService#listHandleCount()
	 */
	@Override
	public List<Object[]> listHandleCount() {
		QueryTemplate qt = QueryTemplateManager.find("alarm.sumByHandle");
		qt.setMaxResults(5);
		return find(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.service.AlarmService#listTodayCount()
	 */
	@Override
	public List<Object[]> listTodayCount() {
		QueryTemplate qt = QueryTemplateManager.find("alarm.sumByToday");
		qt.setMaxResults(5);
		return find(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.service.AlarmService#listWeekCount()
	 */
	@Override
	public List<Object[]> listWeekCount() {
		QueryTemplate qt = QueryTemplateManager.find("alarm.sumByWeek");
		return find(qt);
	}
	
	
	
}
