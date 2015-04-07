package secfox.soc.las.asset.service.impl;


import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.las.asset.bean.T_VulnFoundStat;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.dao.VulnFoundStatDao;
import secfox.soc.las.asset.dao.VulnImportDao;
import secfox.soc.las.asset.service.VulnFoundStatService;
import secfox.soc.las.asset.service.VulnImportService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
@Service
public class VulnFoundStatServiceimpl extends GenericServiceImpl<T_VulnFoundStat,Long> 
					implements VulnFoundStatService {
	private VulnFoundStatDao dao;
	@Override
	protected GenericDao<T_VulnFoundStat, Long> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
	@Inject
	public VulnFoundStatServiceimpl(VulnFoundStatDao dao) {
		this.dao = dao;
	}
	@Override
	@Transactional
	public void addVulnFoundStat(T_VulnFoundStat model) {
		// TODO Auto-generated method stub
		dao.addVulnFoundStat(model);
	}
	@Override
	public void statAndSaveDeviceCurrentVulnInfo(long id, long deviceId,
			long calTime) {
		// TODO Auto-generated method stub
		dao.statAndSaveDeviceCurrentVulnInfo(id, deviceId, calTime);
	}
	
}
