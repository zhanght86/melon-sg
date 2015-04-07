/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.dao.AssetTypeDao;
import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since @2014-9-22,@上午11:07:01
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Repository
public class AssetTypeDaoImpl extends GenericDaoImpl<AssetType, Long> implements
		AssetTypeDao {

	public AssetTypeDaoImpl() {
		super(AssetType.class);
	}

	@Override
	public void persist(AssetType entity) {
		// 1.保存entity
		super.persist(entity);
		Long parentId = entity.getParentId();
		// 如果父节点不为根节点
		if (parentId != BaseConstants.ROOT_ID) {
			// 2.保存path路径
			AssetType reference = super.getReference(entity.getParentId());
			if (reference != null) {
				String parPath = reference.getPath();
				// 如果path不为空就说明不是在根节点下建的
				if (StringUtils.isNotBlank(parPath)) {
					entity.setPath(entity.getId() + BaseConstants.SPLITER_FLAG
							+ parPath);
				} else {
					entity.setPath(entity.getId().toString());
				}
			}
		} else {
			// 2.保存path路径
			entity.setPath(entity.getId() + "," + parentId.toString());
		}
	}

	@Override
	public void remove(Long pk) {
		AssetType findType = find(pk);
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"assetType.remove");
		qt.addParameter("path", QueryTemplate.buildLeftLike(findType.getPath()));
		execute(qt);
	}
	

	@Override
	public AssetType findByName(String name) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"assetType.findByName");
		qt.addParameter("name", name);
		return findFirstDomain(qt);
	}

}
