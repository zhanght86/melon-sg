/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.knowledge.dao.KnowledgeTypeDao;
import secfox.soc.melon.knowledge.domain.KnowledgeType;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since @2014-10-28,@下午3:08:49
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
@Repository
public class KnowledgeTypeDaoImpl extends GenericDaoImpl<KnowledgeType,Long> implements
		KnowledgeTypeDao{

	public KnowledgeTypeDaoImpl() {
		super(KnowledgeType.class);
	}

	 @Override
		public void persist(KnowledgeType entity) {
			// 1.保存entity
			super.persist(entity);
			Long parentId = entity.getParentId();
			// 如果父节点不为根节点
			if (parentId != BaseConstants.ROOT_ID) {
				// 2.保存path路径
				KnowledgeType reference = super.getReference(entity.getParentId());
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
			KnowledgeType findType = find(pk);
			QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"knowledgeType.remove");
			qt.addParameter("path", QueryTemplate.buildLeftLike(findType.getPath()));
			execute(qt);
		}
}

