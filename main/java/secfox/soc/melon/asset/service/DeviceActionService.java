/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;

import java.util.List;

import secfox.soc.melon.asset.domain.DeviceAction;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since @2014-11-5,@下午3:14:48
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface DeviceActionService extends GenericService<DeviceAction, Long> {

	/**
	 * 获取操作日志
	 * @param deviceId 设备id
	 * @return
	 */
	List<DeviceAction> findByDeviceId(Long deviceId);

}


