/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.dao.IpAddressDao;
import secfox.soc.melon.asset.domain.IpAddress;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since @2014-10-8,@下午2:56:40
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Repository
public class IpAddressDaoImpl extends GenericDaoImpl<IpAddress, Long> implements IpAddressDao{

	public IpAddressDaoImpl() {
		super(IpAddress.class);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.IpAddressDao#findByDeviceId(java.lang.Long)
	 */
	@Override
	public List<IpAddress> findByDeviceId(Long deviceId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "ipAddress.findByDeviceId");
		qt.addParameter("deviceId", deviceId);
		return findDomains(qt);
	}
	public List<IpAddress> findByIpv(String ip) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select address from  IpAddress address where address.ipv4  in ( "+ip+")");
		return findDomains(qt);
	}
	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.IpAddressDao#removeByDeviceId(java.lang.Long)
	 */
	@Override
	public void removeByDeviceId(Long deviceId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "ipAddress.removeByDeviceId");
		qt.addParameter("deviceId", deviceId);
		execute(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.IpAddressDao#persist(java.util.List, java.lang.Long)
	 */
	@Override
	public void persist(List<IpAddress> ips, Long deviceId) {
		if(ips != null) {
			for(IpAddress address :ips) {
				address.setDeviceId(deviceId);
				persist(address);
			}
		}
	}

}

