package secfox.soc.las.asset.dao;

import java.util.ArrayList;

import secfox.soc.data.audit.model.T_deviceportModel;
import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.melon.persistence.GenericDao;

public interface DevicePortDao extends GenericDao<T_DevicePort, Long>{
	void addDevicePort(ArrayList<T_DevicePort> portList);
}
