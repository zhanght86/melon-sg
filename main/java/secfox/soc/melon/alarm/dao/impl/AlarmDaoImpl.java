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
import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 *
 * @since 1.0 2014年9月29日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
@Repository
public class AlarmDaoImpl extends GenericDaoImpl<Alarm, Long> implements
		AlarmDao {

	public AlarmDaoImpl() {
		super(Alarm.class);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.dao.AlarmDao#getMaxDate(secfox.soc.melon.persistence.QueryTemplate)
	 */
	@Override
	public Date getMaxDate(QueryTemplate qt) {
		List<Alarm> maxDate = this.findDomains(qt);
		if (maxDate.size() > 0) {
			return maxDate.get(0).getOccurTime();
		}

		//当天0点
//		Calendar cal = Calendar.getInstance(); 
		Calendar minDate = Calendar.getInstance();
		minDate.set(Calendar.HOUR_OF_DAY, 0); 
		minDate.set(Calendar.SECOND, 0); 
		minDate.set(Calendar.MINUTE, 0); 
		minDate.set(Calendar.MILLISECOND, 0); 
		// 数据库为空，就设置一个最小日期
//		minDate.set(Calendar.YEAR, 2000);
		
		return minDate.getTime();
	}

}
