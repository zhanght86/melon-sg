/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao;

import java.util.List;

import secfox.soc.melon.asset.domain.IpAddress;
import secfox.soc.melon.persistence.GenericDao;

/**
 * @since @2014-10-8,@下午2:55:42
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface IpAddressDao extends GenericDao<IpAddress, Long> {

	/**
	 * 
	 * @param deviceId
	 * @return
	 */
	List<IpAddress> findByDeviceId(Long deviceId);
	
	/**
	 * 
	 * @param deviceId
	 */
	void removeByDeviceId(Long deviceId);
	
	/**
	 * 
	 * @param ips
	 * @param deviceId
	 */
	void persist(List<IpAddress> ips, Long deviceId);
	/**
	 * 
	 * @param ip
	 * @return
	 */
	public List<IpAddress> findByIpv(String ip) ;
}
