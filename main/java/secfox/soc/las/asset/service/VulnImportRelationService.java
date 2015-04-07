package secfox.soc.las.asset.service;

import java.util.ArrayList;

import secfox.soc.data.audit.risk.DeviceVulnModel;
import secfox.soc.las.asset.bean.T_VulnImportRelationBean;
import secfox.soc.melon.persistence.GenericService;

public interface VulnImportRelationService extends GenericService<T_VulnImportRelationBean, Long>{
	ArrayList<DeviceVulnModel> getVulnListByDevID(long devid,
			long importTime);
	void addVulnImportRelation(T_VulnImportRelationBean model);
	int[] getImportInfo(long id);
}
