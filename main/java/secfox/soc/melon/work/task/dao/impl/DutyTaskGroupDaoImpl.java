/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.dao.impl;


import org.springframework.stereotype.Repository;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.work.task.dao.DutyTaskGroupDao;
import secfox.soc.melon.work.task.domain.DutyTaskGroup;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-12
 * @version  1.0
 */
@Repository
public class DutyTaskGroupDaoImpl extends GenericDaoImpl<DutyTaskGroup, Long> implements DutyTaskGroupDao {

	/**
	 * @param persistentClass
	 */
	public DutyTaskGroupDaoImpl() {
		super(DutyTaskGroup.class);
	}

}
