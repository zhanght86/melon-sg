package secfox.soc.melon.asset.intergration.service;

import java.util.List;

import secfox.soc.melon.asset.intergration.domain.LasDevices;
import secfox.soc.melon.asset.intergration.query.LasDevicesPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
 * 同步设备Service
 * @author lif
 *
 */
public interface LasDevicesService extends GenericService<LasDevices, Long> {
	
	/**
	 * 保存同步设备
	 * @return
	 */
	void saveAsLasDevices();

	/**
	 * las设备分页方法
	 * @param pageQuery 分页条件
	 * @return 分页数据
	 */
	Pagination<LasDevices> findPages(LasDevicesPageQuery pageQuery);

	/**
	 * 根据lasPk查找数据
	 * @param lasPk
	 * @return
	 */
	LasDevices findByLasPk(Long lasPk);

	/**
	 * 查询全部的las数据
	 * @return
	 */
	List<LasDevices> findAll();
	
	
}
