/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;

import java.util.List;

import secfox.soc.melon.asset.domain.DeviceRoles;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since @2014-10-8,@下午3:09:24
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface DeviceRolesService extends GenericService<DeviceRoles, Long>{
	
	/**
	 * 获取DeviceRoles
	 * @param infoId 信息系统id
	 * @return
	 */
	List<DeviceRoles> findByInfoId(Long infoId);
	
	/**
	 * 获取DeviceRoles
	 * @param deviceId 设备id
	 * @return
	 */
	List<DeviceRoles> findByDeviceId(Long deviceId);
	
	/**
	 * 删除关系
	 * @param infoId 信息系统id
	 * @return
	 */
	boolean removeByInfoId(Long infoId);
	
	/**
	 * 删除关系
	 * @param deviceId 设备id
	 * @return
	 */
	boolean removeByDeviceId(Long deviceId);

}


