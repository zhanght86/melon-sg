/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.dao.impl;


import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.work.task.dao.DutyTaskItemDao;
import secfox.soc.melon.work.task.domain.DutyTaskItem;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-11
 * @version  1.0
 */
@Repository
public class DutyTaskItemDaoImpl extends GenericDaoImpl<DutyTaskItem, Long> implements DutyTaskItemDao {

	/**
	 * @param persistentClass
	 */
	public DutyTaskItemDaoImpl() {
		super(DutyTaskItem.class);
	}

}
