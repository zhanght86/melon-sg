package secfox.soc.melon.asset.intergration.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.asset.intergration.dao.DeviceLogStatusDao;
import secfox.soc.melon.asset.intergration.domain.DeviceLogStatus;
import secfox.soc.melon.asset.intergration.service.DeviceLogStatusService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * 设备日志状态Service实现
 * @author yb
 *
 */
@Service
public class DeviceLogStatusServiceImpl extends GenericServiceImpl<DeviceLogStatus, Long> implements
	DeviceLogStatusService {

	private DeviceLogStatusDao dao;
	
	@Inject
	public DeviceLogStatusServiceImpl(DeviceLogStatusDao dao) {
		super();
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.intergration.service.DeviceLogStatusService#findAll()
	 */
	@Override
	public List<DeviceLogStatus> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"deviceLogStatus.findAll");
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<DeviceLogStatus, Long> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.intergration.service.DeviceLogStatusService#findByIp(java.lang.String)
	 */
	@Override
	public DeviceLogStatus findByIp(String ip) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from DeviceLogStatus a where a.ip =:ip");
		qt.addParameter("ip", ip);
		return findFirstDomain(qt);
	}
	


}
