/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.business.db.dao.SystemInfoDao;
import secfox.soc.business.db.domain.SystemInfo;
import secfox.soc.business.db.service.SystemInfoService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

@Service
public class SystemInfoServiceImpl extends GenericServiceImpl<SystemInfo, Long>
		implements SystemInfoService {
	
	private SystemInfoDao systemInfoDao;
	
	@Inject
	public SystemInfoServiceImpl(SystemInfoDao systemInfoDao){
		this.systemInfoDao = systemInfoDao;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<SystemInfo, Long> getDao() {
		return systemInfoDao;
	}
	
}
