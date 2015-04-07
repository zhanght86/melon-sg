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
import secfox.soc.melon.alarm.dao.AlarmHandleDao;
import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.alarm.domain.AlarmHandle;
import secfox.soc.melon.alarm.query.AlarmPageQuery;
import secfox.soc.melon.alarm.query.AlarmSearch;
import secfox.soc.melon.alarm.service.AlarmHandleService;
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
 * @author yb
 */
@Service
public class AlarmHandleServiceImpl extends GenericServiceImpl<AlarmHandle, Long> implements AlarmHandleService {
	
	private AlarmHandleDao alarmHandleDao;
	
	/**
	 * @param alarmDao
	 */
	@Inject
	public AlarmHandleServiceImpl(AlarmHandleDao alarmHandleDao) {
		super();
		this.alarmHandleDao = alarmHandleDao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<AlarmHandle, Long> getDao() {
		return this.alarmHandleDao;
	}

	
}
