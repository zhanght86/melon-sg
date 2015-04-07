package secfox.soc.melon.asset.intergration.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.intergration.dao.LasDevicesDao;
import secfox.soc.melon.asset.intergration.domain.LasDevices;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * 同步设备Dao实现类
 * @author lif
 *
 */
@Repository
public class LasDevicesDaoImpl extends GenericDaoImpl<LasDevices, Long>
		implements LasDevicesDao {

	
	public LasDevicesDaoImpl() {
		super(LasDevices.class);
	}
}
