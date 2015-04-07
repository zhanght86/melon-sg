/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.asset.staff.dao.InterStaffDao;
import secfox.soc.melon.asset.staff.domain.InterStaff;

/**
 * @since 2014-11-20,下午2:28:37
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class InterStaffDaoImpl extends GenericDaoImpl<InterStaff, Long> implements
		InterStaffDao {

	public InterStaffDaoImpl() {
		super(InterStaff.class);
	}

}
