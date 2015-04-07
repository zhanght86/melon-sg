package secfox.soc.las.asset.service.impl;


import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.dao.DevicePortDao;
import secfox.soc.las.asset.dao.VulnImportDao;
import secfox.soc.las.asset.service.DevicePortService;
import secfox.soc.las.asset.service.VulnImportService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
@Service
public class DevicePortServiceimpl extends GenericServiceImpl<T_DevicePort,Long> 
					implements DevicePortService {
	private DevicePortDao dao;
	@Override
	protected GenericDao<T_DevicePort, Long> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
	@Inject
	public DevicePortServiceimpl(DevicePortDao dao) {
		this.dao = dao;
	}
	@Override
	@Transactional
	public void addDevicePort(ArrayList<T_DevicePort> portList) {
		// TODO Auto-generated method stub
		dao.addDevicePort(portList);
	}
	
	
}
