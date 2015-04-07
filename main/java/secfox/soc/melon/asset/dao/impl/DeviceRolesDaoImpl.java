/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.dao.DeviceRolesDao;
import secfox.soc.melon.asset.domain.DeviceRoles;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since @2014-10-8,@下午3:06:07
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Repository
public class DeviceRolesDaoImpl extends GenericDaoImpl<DeviceRoles, Long> implements DeviceRolesDao{

	public DeviceRolesDaoImpl() {
		super(DeviceRoles.class);
	}

}


