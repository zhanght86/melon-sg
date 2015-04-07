/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.dao.PhyEnvironmentDao;
import secfox.soc.melon.asset.domain.PhyEnvironment;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * @since 2014-4-17,下午6:53:34
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class PhyEnvironmentDaoImpl extends GenericDaoImpl<PhyEnvironment, Long> implements
		PhyEnvironmentDao {

	 /**
     * @param persistentClass
     */
    public PhyEnvironmentDaoImpl() {
        super(PhyEnvironment.class);
    }

	@Override
	public void persist(PhyEnvironment phyEnvironment) {
		phyEnvironment.setUserInfo(SecurityContextUtils.getCurrentUserInfo());
		// 1.保存entity
		super.persist(phyEnvironment);
		Long parentId = phyEnvironment.getParentId();
		// 如果父节点不为根节点
		if (parentId != BaseConstants.ROOT_ID) {
			// 2.保存path路径
			PhyEnvironment reference = super.getReference(phyEnvironment.getParentId());
			if (reference != null) {
				String parPath = reference.getPath();
				// 如果path不为空就说明不是在根节点下建的
				if (StringUtils.isNotBlank(parPath)) {
					phyEnvironment.setPath(phyEnvironment.getId() + BaseConstants.SPLITER_FLAG + parPath);
				} else {
					phyEnvironment.setPath(phyEnvironment.getId().toString());
				}
			}
		} else {
			// 2.保存path路径
			phyEnvironment.setPath(phyEnvironment.getId() + "," + parentId.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Long pk) {
		PhyEnvironment phyEnvironment = find(pk);
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"phyEnvironment.remove");
		qt.addParameter("path", QueryTemplate.buildLeftLike(phyEnvironment.getPath()));
		execute(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.PhyEnvironmentDao#findByTypePath(java.lang.Long)
	 */
	@Override
	public List<PhyEnvironment> findByTypePath(Long typeId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "phyEnvironment.findByTypePath");
		qt.addParameter("path", QueryTemplate.buildAllLike(","+typeId+","));
		qt.addParameter("typeId", typeId);
		return findDomains(qt);
	}
}
