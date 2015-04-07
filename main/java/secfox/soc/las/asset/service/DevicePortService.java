package secfox.soc.las.asset.service;

import java.util.ArrayList;
import java.util.HashMap;

import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.bean.T_vulnBean;
import secfox.soc.melon.persistence.GenericService;


public interface DevicePortService extends GenericService<T_DevicePort,Long>{ 
	void addDevicePort(ArrayList<T_DevicePort> portList);
	
}
