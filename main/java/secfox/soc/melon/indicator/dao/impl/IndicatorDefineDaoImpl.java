package secfox.soc.melon.indicator.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.indicator.dao.IndicatorDefineDao;
import secfox.soc.melon.indicator.domain.IndicatorDefine;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 1.0 2014-10-15,下午3:29:02
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Repository
public class IndicatorDefineDaoImpl extends	GenericDaoImpl<IndicatorDefine, Long> implements IndicatorDefineDao {

	/**
	 * @param persistentClass
	 */
	public IndicatorDefineDaoImpl() {
		super(IndicatorDefine.class);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void persist(IndicatorDefine entity) {
		// 1.保存entity
		super.persist(entity);
		Long parentId = entity.getParentId();
		// 如果父节点不为根节点
		if (parentId != BaseConstants.ROOT_ID) {
			// 2.保存path路径
			IndicatorDefine reference = super.getReference(entity.getParentId());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * secfox.soc.melon.framework.persistence.impl.GenericDaoImpl#remove(java
	 * .io.Serializable)
	 */
	@Override
	public void remove(Long pk) {
		IndicatorDefine findType = find(pk);
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"indicator.remove");
		qt.addParameter("path", QueryTemplate.buildLeftLike(findType.getPath()));
		execute(qt);
	}
}

