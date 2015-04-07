/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.asset.dao.DeviceActionDao;
import secfox.soc.melon.asset.dao.DeviceDao;
import secfox.soc.melon.asset.dao.InfoSystemDao;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.DeviceAction;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.service.DeviceActionService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since @2014-11-5,@下午3:16:24
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Service
public class DeviceActionServiceImpl extends GenericServiceImpl<DeviceAction, Long> implements DeviceActionService {
	
	private DeviceActionDao dao;
	
	private DeviceDao deviceDao;
	private InfoSystemDao infoSystemDao;
	
	@Inject
	public DeviceActionServiceImpl(InfoSystemDao infoSystemDao,DeviceDao deviceDao,DeviceActionDao dao) {
		super();
		this.dao = dao;
		this.deviceDao = deviceDao;
		this.infoSystemDao = infoSystemDao;
	}
	
	@Override
	protected GenericDao<DeviceAction, Long> getDao() {
		return this.dao;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#persist(secfox.soc.melon.persistence.Identifiable)
	 */
	@Override
	@Transactional(readOnly = false)
	public void persist(DeviceAction entity) {
		int deviceType = entity.getDeviceType(); //获取是与设备还是与信息系统
		Long deviceId = entity.getDeviceId();  //获取设备或信息系统id
		Device device = null;
		InfoSystem infoSystem = null;
		
		//如果是更改设备状态
		if(deviceType == 0){
			device = deviceDao.find(deviceId);
			//更改设备的对象
			if(device != null){
				device.setUsing(entity.getType());
				deviceDao.merge(device);
			}
		}
		
		//如果是更改信息系统状态
		if(deviceType == 1){
			infoSystem = infoSystemDao.find(deviceId);
			//更改信息信息系统状态
			if(infoSystem !=null){
				infoSystem.setUsing(entity.getType());
				infoSystemDao.merge(infoSystem);
			}
		}
		
		super.persist(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceActionService#findByDeviceId(java.lang.Long)
	 */
	@Override
	public List<DeviceAction> findByDeviceId(Long deviceId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"action.findByDevice");
		qt.addParameter("deviceId", deviceId);
		return findDomains(qt);
	}
	
}


