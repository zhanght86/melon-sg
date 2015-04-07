package secfox.soc.las.asset.dao;

import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.melon.persistence.GenericDao;

public interface VulnImportDao extends GenericDao<T_VulnImport, Long>{
	void addVulnImport(T_VulnImport obj);
}
