package secfox.soc.las.asset.service.impl;


import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.dao.VulnImportDao;
import secfox.soc.las.asset.service.VulnImportService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
@Service
public class VulnImportServiceimpl extends GenericServiceImpl<T_VulnImport,Long> 
					implements VulnImportService {
	private VulnImportDao vulnImportDao;
	@Override
	protected GenericDao<T_VulnImport, Long> getDao() {
		// TODO Auto-generated method stub
		return this.vulnImportDao;
	}
	@Inject
	public VulnImportServiceimpl(VulnImportDao dao) {
		this.vulnImportDao = dao;
	}
	@Override
	@Transactional
	public void addVulnImport(T_VulnImport vulnimport) {
		// TODO Auto-generated method stub
		vulnImportDao.addVulnImport(vulnimport);
	}
	
}
