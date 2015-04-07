/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao;

import java.util.List;
import java.util.Map;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.query.DevicePageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericDao;


/**
 * 设备dao
 * @since 2014-9-26
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version  1.0
 */
public interface DeviceDao extends GenericDao<Device,Long>{
	/**
	 * yb 根据组织查询设备
	 * @param typeId
	 * @return
	 */
	List<Device> findByOrganPath(String organPath);
	
	/**
	 * yb 根据类型查询设备
	 * @param typeId
	 * @return
	 */
	List<Device> findByTypePath(String typePath);
	
	/**
	 * yb 根据操作系统查询设备
	 * @param typeId
	 * @return
	 */
	List<Device> findByOs();
	
	/**
	 * yb 根据机房查询设备
	 * @param typeId
	 * @return
	 */
	List<Device> findByEnviPath(String enviPath);

	/**
	 * 获取设备
	 * @param name 名称
	 * @return Device
	 */
	Device findByName(String name);
	
	/**
	 * 分页
	 * @param pageQuery
	 * @return
	 */
	Pagination<Map<String, Object>> findPageByDomain(DevicePageQuery pageQuery);
	
	/**
	 * 分页
	 * @param pageQuery
	 * @return
	 */
	Pagination<Map<String, Object>> findPageByDeviceInfo(DevicePageQuery pageQuery, String enviPath);
	
}
