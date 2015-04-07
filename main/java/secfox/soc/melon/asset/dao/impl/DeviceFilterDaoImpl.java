/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.dao.DeviceFilterDao;
import secfox.soc.melon.asset.domain.DeviceFilter;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 *
 * @since 1.0 2014年11月18日下午7:49:15
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Repository
public class DeviceFilterDaoImpl extends GenericDaoImpl<DeviceFilter, Long>
		implements DeviceFilterDao {

	public DeviceFilterDaoImpl() {
		super(DeviceFilter.class);
	}
}

