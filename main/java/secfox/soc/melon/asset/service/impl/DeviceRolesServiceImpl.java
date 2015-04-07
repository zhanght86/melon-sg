/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.asset.dao.DeviceRolesDao;
import secfox.soc.melon.asset.domain.DeviceRoles;
import secfox.soc.melon.asset.service.DeviceRolesService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since @2014-10-8,@下午3:09:36
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Service
public class DeviceRolesServiceImpl extends GenericServiceImpl<DeviceRoles, Long> implements DeviceRolesService{
	private DeviceRolesDao dao;
	
	@Inject
	public DeviceRolesServiceImpl(DeviceRolesDao dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	protected GenericDao<DeviceRoles, Long> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceRolesService#findByInfoId(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<DeviceRoles> findByInfoId(Long infoId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from DeviceRoles a where a.infoId =:infoId ");
		qt.addParameter("infoId", infoId);
		return findDomains(qt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceRolesService#findByDeviceId(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<DeviceRoles> findByDeviceId(Long deviceId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from DeviceRoles a where a.deviceId = :deviceId ");
		qt.addParameter("deviceId", deviceId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceRolesService#removeByInfoId(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean removeByInfoId(Long infoId) {
		List<DeviceRoles> roless = findByInfoId(infoId);
		//如果没有则不用删除
		if(roless.size()<1){
			return false;
		}
		for (int i = 0; i < roless.size(); i++) {
			this.remove(roless.get(i));
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceRolesService#removeByDeviceId(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean removeByDeviceId(Long deviceId) {
		List<DeviceRoles> roless = findByDeviceId(deviceId);
		//如果没有则不用删除
		if(roless.size()<1){
			return false;
		}
		for (int i = 0; i < roless.size(); i++) {
			this.remove(roless.get(i));
		}
		return true;
	}

	
}


