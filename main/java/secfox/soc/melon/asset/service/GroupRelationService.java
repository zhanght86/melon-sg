/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;

import java.util.List;

import secfox.soc.melon.asset.domain.GroupRelation;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since @2014-10-20,@下午8:25:19
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface GroupRelationService extends GenericService<GroupRelation, Long> {
	
	
	/**
	 * 获取安全域的id与name
	 * @param type 0:代表安全域与设备, 1:代表安全域与信息系统
	 * @param assetId 设备id or 信息系统id
	 * @return
	 */
	List<Object[]> findByTypeAssetId(int type,Long assetId);
	
	/**
	 * 获取关系数据
	 * @param groupId 安全域id
	 * @return GroupRelation
	 */
	List<GroupRelation> findByDeviceId(Long deviceId);

	/**
	 * 获取关系数据
	 * @param groupId 安全域id
	 * @return GroupRelation
	 */
	List<GroupRelation> findBySysId(Long sysId);
	
	/**
	 * 删除关系 
	 * @param deviceId 设备id
	 */
	void removeByDeviceId(Long deviceId);

	/**
	 * 删除关系
	 * @param sysId 信息系统id
	 */
	void removeBySysId(Long sysId);
}


