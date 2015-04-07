/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao;

import java.util.List;

import secfox.soc.melon.asset.domain.DeviceAction;
import secfox.soc.melon.persistence.GenericDao;

/**
 * @since @2014-11-5,@下午3:11:03
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface DeviceActionDao extends GenericDao<DeviceAction, Long>{

	
	/**
	 * 获取运维管理信息
	 * @param deviceType 0:代表设备, 1:代表信息系统
	 * @param assetId 设备或信息系统id
	 * @return List<DeviceAction> 根据条件获得运维管理信息
	 */
	List<DeviceAction> findByTypeAsset(int deviceType,Long assetId);
	
	/**
	 * 删除运维管理信息
	 * @param deviceType 0:代表设备, 1:代表信息系统
	 * @param assetId 设备或信息系统id
	 */
	void removeByTypeAsset(int deviceType,Long assetId);
	
	
}


