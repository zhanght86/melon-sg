/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.asset.dao.GroupRelationDao;
import secfox.soc.melon.asset.domain.GroupRelation;
import secfox.soc.melon.asset.service.GroupRelationService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.persistence.templates.QueryTemplateManager;

/**
 * @since @2014-10-20,@下午8:26:16
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Service
public class GroupRelationServiceImpl extends GenericServiceImpl<GroupRelation, Long> implements GroupRelationService {

	private GroupRelationDao dao;
	
	@Inject
	public GroupRelationServiceImpl(GroupRelationDao dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	protected GenericDao<GroupRelation, Long> getDao() {
		return this.dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.GroupRelationService#findByDeviceId(java.lang.Long)
	 */
	@Override
	public List<GroupRelation> findByDeviceId(Long deviceId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"relation.findByDI");
		qt.addParameter("type", 0);
		qt.addParameter("assetId", deviceId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.GroupRelationService#findBySysId(java.lang.Long)
	 */
	@Override
	public List<GroupRelation> findBySysId(Long sysId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"relation.findByDI");
		qt.addParameter("type", 1);
		qt.addParameter("assetId", sysId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.GroupRelationService#removeByDeviceId(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly  = false)
	public void removeByDeviceId(Long deviceId) {
		List<GroupRelation> relations = findByDeviceId(deviceId);
		
		if(relations !=null && relations.size()>0){
			for (int i = 0; i < relations.size(); i++) {
				remove(relations.get(i));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.GroupRelationService#removeBySysId(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly  = false)
	public void removeBySysId(Long sysId) {
		List<GroupRelation> relations = findBySysId(sysId);
		if(relations !=null && relations.size()>0){
			for (int i = 0; i < relations.size(); i++) {
				remove(relations.get(i));
			}
		}
	}

	@Override
	public List<Object[]> findByTypeAssetId(int type, Long assetId) {
		QueryTemplate qt = QueryTemplateManager.find("group.relationConn");
		qt.addParameter("type", type);
		qt.addParameter("assetId", assetId);
		return find(qt);
	}


}


