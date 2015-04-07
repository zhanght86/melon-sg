package secfox.soc.las.asset.service.impl;


import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.data.audit.risk.DeviceVulnModel;
import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.bean.T_VulnFoundValue;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.dao.VulnFoundStatDao;
import secfox.soc.las.asset.dao.VulnFoundValueDao;
import secfox.soc.las.asset.dao.VulnImportDao;
import secfox.soc.las.asset.service.VulnFoundValueService;
import secfox.soc.las.asset.service.VulnImportService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
@Service
public class VulnFoundValueServiceimpl extends GenericServiceImpl<T_VulnFoundValue,Long> 
					implements VulnFoundValueService {
	private VulnFoundValueDao dao;
	private VulnFoundStatDao statDao;
	@Override
	protected GenericDao<T_VulnFoundValue, Long> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
	@Inject
	public VulnFoundValueServiceimpl(VulnFoundValueDao dao,VulnFoundStatDao statDao) {
		this.dao = dao;
		this.statDao = statDao;
	}
	@Override
	@Transactional
	public void addVulnFoundValue(T_VulnFoundValue model) {
		// TODO Auto-generated method stub
		dao.addVulnFoundValue(model);
		
	}
	@Override
	@Transactional
	public boolean refresVulnFoundValue(T_VulnFoundValue model){
		T_VulnFoundValue obj =statDao.statAndSaveDeviceCurrentVulnInfo(model.getId(), model.getDeviceID(), model.getCalTime());
		super.refresh(obj);
		return true;
	}
	@Override
	@Transactional
	public boolean insertVuln(DeviceVulnModel dvModel) {
		// TODO Auto-generated method stub
		dao.insertVuln(dvModel);
		return false;
	}
	@Override
	@Transactional
	public void saveDataToVulnFoundTable(ArrayList<DeviceVulnModel> rsList,long importTime) {
		// TODO Auto-generated method stub
		dao.saveDataToVulnFoundTable(rsList,importTime);
	}
	@Override
	@Transactional
	public T_VulnFoundValue updateVuln(DeviceVulnModel dvModel) {
		// TODO Auto-generated method stub
		boolean result = false;
		long[] parm = dao.updateVuln(dvModel);
		T_VulnFoundValue model =new T_VulnFoundValue();
		model.setId(parm[0]);
		model.setDeviceID(parm[1]);
		model.setCalTime(parm[2]);
		return model;
	}
	
}
