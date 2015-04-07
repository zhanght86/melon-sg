/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;

import java.util.List;

import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.query.AssetGroupPageQuery;
import secfox.soc.melon.asset.query.DevicePageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since @2014-10-10,@下午4:50:51
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface AssetGroupService extends GenericService<AssetGroup, Long> {
	/**
	 * 根据类型查询安全域
	 * @param typeId
	 * @return
	 */
	List<AssetGroup> findByType(Long typeId);
	
	/**
	 * 获取安全域树
	 * @param rootId 节点id
	 * @param choosen
	 * @return List<AssetGroup>
	 */
	List<AssetGroup> findTree(Long rootId, boolean choosen);
	
	/**
	 * 获取安全域
	 * @param organId 单位id
	 * @return 	List<AssetGroup>
	 */
	List<AssetGroup> findByOrgan(Long organId);
	
	
	/**
	 * 获取安全域
	 * @param assetId 设备id或信息系统id
	 * @param type 0:代表安全域与设备, 1:代表安全域与信息系统
	 * @return 	List<AssetGroup>
	 */
	List<AssetGroup> findByAsset(Long assetId,int type);
	

	/**
	 * 获取所有安全域
	 * @return	List<AssetGroup>
	 */
	List<AssetGroup> findAll();

	
	/**
	 * 安全域主页
	 * @return	
	 */
	List<Object[]> getHomeView();
	
	/**
	 * 获取安全域设备
	 * @return
	 */
	List<Object[]> getGroupDevice();
	
	/**
	 * 获取安全域信息系统
	 * @return
	 */
	List<Object[]> getGroupSys();

	/**
	 * 获取安全域
	 * @return 获取所有安全域并的到父节点的数据
	 */
	List<Object[]> findRelationList();
	
	
	/** 
	 * yb 安全域分页
	 * @param query 安全域分页对象
	 * @return Pagination<Device> 安全域分页对象
	 */
	Pagination<AssetGroup> findPages(AssetGroupPageQuery query);
	
	/** 
	 * yb 信息系统安全域分页
	 * @param query 安全域分页对象
	 * @return Pagination<Device> 安全域分页对象
	 */
	Pagination<AssetGroup> findPagesByInfo(AssetGroupPageQuery query);
}


