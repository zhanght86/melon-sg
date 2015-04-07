/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;


import java.io.InputStream;
import java.util.List;
import java.util.Map;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.DeviceFilter;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.query.DevicePageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
 * 设备服务接口
 * @since 2014-11-10
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version  1.0
 */
public interface DeviceService extends GenericService<Device,Long>{
	/**
	 * yb 根据类型查询设备
	 * @param typeId
	 * @return
	 */
	List<Device> findByType(Long typeId);
	
	/**
	 * yb 根据安全域显示设备
	 * @param query
	 * @return
	 */
	Pagination<Map<String, Object>> findPageByDomain(DevicePageQuery query);
	
	/**
	 * 获取设备
	 * @param Ip ip地址
	 * （TODO 还未实现）
	 * @return
	 */
	Device findByIp(String Ip);
	
	/**
	 * 获取设备
	 * @param name  名称
	 * @param mode  型号
	 * @return
	 */
	List<Device> findByNameModel(String name,String mode);
	
	/**
	 * 去重复的设备
	 * @param name 名称
	 * @param mode 型号
	 * @return
	 */
	List<List<Device>> exclusionRepeat();
	
	
	/**
	 * 读取excel
	 * @param inputStream 读取excel的文件流
	 * @return using状态值为2的数据作为错误数据
	 * @throws Exception
	 */
	List<Device> readExcelContent(InputStream inputStream) throws Exception;
	
	/**
	 * 保存excel的对象
	 * @param inputStream 导入excel的文件流
	 * @return "errorDevice":错误数据集合,"errorNumber":错误数据条数,"correctDevice":成功导入的数据,"correctNumber":成功导入数据的条数
	 * @throws Exception 
	 */
	Map<String, Object> excelToDb(InputStream inputStream) throws Exception;
	
	
	/**
	 * 设备分页
	 * @param query 设备分页对象
	 * @return Pagination<Device> 设备分页对象
	 */
	Pagination<Device> findPages(DevicePageQuery query);
	
	
	/**
	 * 获取设备
	 * @param code 编码
	 * @return Device
	 */
	Device findByCode(String code);
	
	/**
	 * 获取登录人关注的设备
	 * @param userId 登录人
	 * @return List<Device>
	 */
	List<Device> findByUserBusiness(Long userId);
	
	/**
	 * 获取登录人批注的设备
	 * @param userId 登录人
	 * @return List<Device>
	 */
	List<Device> findByQuickNote(Long userId);
	
	/**
	 * 获取设备
	 * @param has 是否网络设备  
	 * @return List<Device>
	 */
	List<Device> findByHasIp(boolean hasip);
	/**
	 * 获取设备
	 * @param enviId 物理环境id
	 * @return List<Device>
	 */
	List<Device> findByEvi(Long enviId);
	/**
	 * 获取设备
	 * @param vir 是否虚拟设备  
	 * @return List<Device>
	 */
	List<Device> findByVirtual(boolean vir);
	
	/**
	 * 获取设备
	 * @param orgId 单位id
	 * @return List<Device>
	 */
	List<Device> findByOrgan(Long orgId);
	
	/**
	 * 获取设备
	 * @param assetGroupId 安全域id
	 * @return List<Device>
	 */
	List<Device> findByAssetGroup(Long assetGroupId);
	
	/**
	 * 获取设备
	 * @param personId 负责人id
	 * @return List<Device>
	 */
	List<Device> findByCharger(Long personId);

	/**
	 * 获取所有设备
	 * @return List<Device>
	 */
	List<Device> findAll();
	
	/**
	 * 按单位统计设备
	 * @return organName,设备总数,虚拟设备总数,使用率
	 */
	List<Object[]> listOrganWithCount();


	/**
	 * 获取设备
	 * @param ids 多个id
	 * @return List<Device>
	 */
	List<Device> findByIds(Long[] ids);

	/**
	 * 获取设备
	 * @param deviceName 设备名称
	 * @param deviceIp 设备ip
	 * @return
	 */
	List<Device> findByIpOrName(String deviceName, String deviceIp);
	
	//TODO 交换机，路由器，防火墙，入侵检测，防病毒   保障专题需要的接口
	/**
	 * 根据提供的设备到期提醒条件查询符合的设备
	 * @param deviceFilter
	 * @return
	 */
	List<Device> findRemindDevice(DeviceFilter deviceFilter);

	/**
	 * 根据安全域显示设备
	 * @param query
	 * @return
	 */
	Pagination<Device> findPageByDeviceInfo(DevicePageQuery query, String enviPath);

	
	/**
	 * 安全对象主页柱状图数据
	 * @return 0：纵坐标，1：横坐标
	 */
	List<Object[]> findTypeViewDb();
	
}
