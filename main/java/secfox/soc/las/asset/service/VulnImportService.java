package secfox.soc.las.asset.service;

import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.melon.persistence.GenericService;

public interface VulnImportService extends GenericService<T_VulnImport,Long>{
	void addVulnImport(T_VulnImport vulnimport);
}
