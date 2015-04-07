/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.system.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.system.dao.WorkCalendarDao;
import secfox.soc.melon.system.domain.WorkCalendar;

/**
 * @since 2014-10-9,下午1:55:59
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class WorkCalendarDaoImpl extends GenericDaoImpl<WorkCalendar, Long> implements
		WorkCalendarDao {

	public WorkCalendarDaoImpl() {
		super(WorkCalendar.class);
	}
}
