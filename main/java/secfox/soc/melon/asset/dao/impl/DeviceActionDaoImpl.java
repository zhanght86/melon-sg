/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.dao.DeviceActionDao;
import secfox.soc.melon.asset.domain.DeviceAction;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since @2014-11-5,@下午3:12:24
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Repository
public class DeviceActionDaoImpl extends GenericDaoImpl<DeviceAction, Long> implements	DeviceActionDao {

	public DeviceActionDaoImpl() {
		super(DeviceAction.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.DeviceActionDao#findByTypeAsset(int, java.lang.Long)
	 */
	@Override
	public List<DeviceAction> findByTypeAsset(int deviceType, Long assetId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"action.findByTypeAsset");
		qt.addParameter("deviceType", deviceType);
		qt.addParameter("deviceId", assetId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.DeviceActionDao#removeByTypeAsset(int, java.lang.Long)
	 */
	@Override
	public void removeByTypeAsset(int deviceType, Long assetId) {
		List<DeviceAction> findByTypeAsset = this.findByTypeAsset(deviceType,assetId);
		if(findByTypeAsset != null && findByTypeAsset.size()>0){
			for (int i = 0; i < findByTypeAsset.size(); i++) {
				findByTypeAsset.remove(findByTypeAsset.get(i));
			}
		}
		
	}
	
	
	
	
	
	
	
}


