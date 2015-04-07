/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.alarm.dao.AlarmDao;
import secfox.soc.melon.alarm.dao.AlarmHandleDao;
import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.alarm.domain.AlarmHandle;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 *
 * @author yb
 */
@Repository
public class AlarmHandleDaoImpl extends GenericDaoImpl<AlarmHandle, Long> implements
		AlarmHandleDao {

	public AlarmHandleDaoImpl() {
		super(AlarmHandle.class);
		// TODO Auto-generated constructor stub
	}


}
