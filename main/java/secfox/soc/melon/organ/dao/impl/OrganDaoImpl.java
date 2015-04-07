/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.organ.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.organ.dao.OrganDao;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 2014-9-18,上午11:37:36
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class OrganDaoImpl extends GenericDaoImpl<Organization, Long> implements OrganDao {

	public OrganDaoImpl(){
		super(Organization.class);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.impl.GenericDaoImpl#persist(secfox.soc.melon.framework.persistence.Identifiable)
	 */
	@Override
	public void persist(Organization entity) {
		super.persist(entity);
		Long parentId = entity.getParentId();
		int level = 0;//默认为总部
		//根节点路径
		String parentPath = String.valueOf(parentId);
		//如果不是第一层节点
		if(parentId == BaseConstants.ROOT_ID) {
			//只有行政区域叠加时才不一致
			if(entity.isArea()) {
				entity.setLevel(level + 1);
			} else {
				entity.setLevel(level);
			}
		} else {
			Organization parent = getReference(parentId);
			parent.setLeaf(false);//设置父节点为非叶子节点
			//如果子节点是单位,部门,则与父节点的级别保持一致
			if(entity.isCompany()||entity.isDepartMent()) {
				entity.setLevel(parent.getLevel());
			}
			parentPath = parent.getPath();
		}
		entity.setPath(entity.getId() + BaseConstants.SPLITER_FLAG + parentPath);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Long pk) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "organization.remove");
		qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(pk)));
		execute(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.dao.OrganDao#findByName(java.lang.String)
	 */
	@Override
	public Organization findByName(String name) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "organization.findByName");
		qt.addParameter("name", StringUtils.trim(name));
		return findFirstDomain(qt);
	}
	
	
	
}
