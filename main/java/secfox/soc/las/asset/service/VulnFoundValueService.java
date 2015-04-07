package secfox.soc.las.asset.service;

import java.util.ArrayList;
import java.util.HashMap;

import secfox.soc.data.audit.risk.DeviceVulnModel;
import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.bean.T_VulnFoundValue;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.bean.T_vulnBean;
import secfox.soc.melon.persistence.GenericService;


public interface VulnFoundValueService extends GenericService<T_VulnFoundValue,Long>{ 
	void addVulnFoundValue(T_VulnFoundValue model);
	void saveDataToVulnFoundTable(ArrayList<DeviceVulnModel> rsList,long importTime);
	T_VulnFoundValue updateVuln(DeviceVulnModel dvModel);
	boolean insertVuln(DeviceVulnModel dvModel);
	boolean refresVulnFoundValue(T_VulnFoundValue model);
}
