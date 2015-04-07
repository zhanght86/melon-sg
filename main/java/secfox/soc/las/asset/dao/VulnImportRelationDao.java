package secfox.soc.las.asset.dao;

import java.util.ArrayList;

import secfox.soc.data.audit.risk.DeviceVulnModel;
import secfox.soc.las.asset.bean.T_VulnImportRelationBean;
import secfox.soc.melon.persistence.GenericDao;

public interface VulnImportRelationDao extends GenericDao<T_VulnImportRelationBean, Long>{
	ArrayList<DeviceVulnModel> getVulnListByDevID(long devid,
			long importTime);
	void addVulnImportRelation(T_VulnImportRelationBean model);
	int[] getImportInfo(long id);
}
