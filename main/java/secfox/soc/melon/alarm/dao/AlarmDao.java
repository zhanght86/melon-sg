/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.dao;

import java.util.Date;

import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;

/**
 *
 * @since 1.0 2014年9月29日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public interface AlarmDao extends GenericDao<Alarm, Long> {

	/**
	 * @param qt
	 * @return
	 */
	Date getMaxDate(QueryTemplate qt);

}
