/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.intergration.service;

import java.util.List;

import secfox.soc.melon.asset.intergration.domain.DeviceLogStatus;
import secfox.soc.melon.persistence.GenericService;

/**
 *
 * @since 1.0 2014年11月28日
 * @author yb
 * @version 1.0
 */
public interface DeviceLogStatusService extends GenericService<DeviceLogStatus, Long> {
	/**
	 * 查询全部
	 * @return
	 */
	List<DeviceLogStatus> findAll();
	
	/**
	 * 根据ip查
	 * @param ip
	 * @return
	 */
	DeviceLogStatus findByIp(String ip);
}
