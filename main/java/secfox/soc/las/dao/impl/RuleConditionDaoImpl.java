/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.las.dao.RuleConditionDao;
import secfox.soc.las.domain.RuleCondition;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 2015-2-2,下午3:34:43
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class RuleConditionDaoImpl extends GenericDaoImpl<RuleCondition, Long> implements
		RuleConditionDao {
	
	public RuleConditionDaoImpl() {
		super(RuleCondition.class);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.impl.GenericDaoImpl#persist(secfox.soc.melon.framework.persistence.Identifiable)
	 */
	@Override
	public void persist(RuleCondition entity) {
		super.persist(entity);
		setPath(entity);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.impl.GenericDaoImpl#merge(secfox.soc.melon.framework.persistence.Identifiable)
	 */
	@Override
	public RuleCondition merge(RuleCondition entity) {
		setPath(entity);
		return super.merge(entity);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Long pk) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "condition.remove");
		qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(pk)));
		execute(qt);
	}
	
	/**
	 * 设置路径
	 * @param entity
	 */
	private void setPath(RuleCondition entity) {
		Long parentId = entity.getParentId();
		//根节点路径
		String parentPath = String.valueOf(parentId);
		//非根节点路径
		if(parentId != BaseConstants.ROOT_ID) {
			RuleCondition parent = getReference(parentId);
			parentPath = parent.getPath();
		}
		entity.setPath(entity.getId() + BaseConstants.SPLITER_FLAG + parentPath);
	}


}
