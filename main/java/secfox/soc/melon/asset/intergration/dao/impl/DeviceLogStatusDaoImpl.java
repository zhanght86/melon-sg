package secfox.soc.melon.asset.intergration.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.intergration.dao.DeviceLogStatusDao;
import secfox.soc.melon.asset.intergration.domain.DeviceLogStatus;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * 设备日志状态Dao实现类
 * @author yb
 *
 */
@Repository
public class DeviceLogStatusDaoImpl extends GenericDaoImpl<DeviceLogStatus, Long>
		implements DeviceLogStatusDao {

	public DeviceLogStatusDaoImpl() {
		super(DeviceLogStatus.class);
	}

	
	

	
	
}
