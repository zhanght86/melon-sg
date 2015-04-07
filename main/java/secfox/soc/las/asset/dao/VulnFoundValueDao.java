package secfox.soc.las.asset.dao;

import java.util.ArrayList;

import secfox.soc.data.audit.risk.DeviceVulnModel;
import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.bean.T_VulnFoundValue;
import secfox.soc.melon.persistence.GenericDao;

public interface VulnFoundValueDao extends GenericDao<T_VulnFoundValue, Long>{
	void addVulnFoundValue(T_VulnFoundValue model);
	void saveDataToVulnFoundTable(ArrayList<DeviceVulnModel> rsList,long importTime);
	long[] updateVuln(DeviceVulnModel dvModel);
	boolean insertVuln(DeviceVulnModel dvModel);
}
