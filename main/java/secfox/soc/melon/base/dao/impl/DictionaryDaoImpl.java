package secfox.soc.melon.base.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.dao.DictionaryDao;
import secfox.soc.melon.base.domain.Dictionary;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @author 熊超
 * 2014-10-9
 * @version 1.0
 */
@Repository
public class DictionaryDaoImpl extends GenericDaoImpl<Dictionary, Long> implements DictionaryDao {

	public DictionaryDaoImpl() {
		super(Dictionary.class);
	}
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#persist(secfox.soc.melon.persistence.Identifiable)
	 */
	@Override
	public void persist(Dictionary entity) {
		// 1.保存entity
		super.persist(entity);
		Long parentId = entity.getParentId();
		// 如果父节点不为根节点
		if (parentId != BaseConstants.ROOT_ID) {
			// 2.保存path路径
			Dictionary parent = super.getReference(entity.getParentId());
			if (parent != null) {
				String parPath = parent.getPath();
				//如果path不为空就说明不是在根节点下建的
				if (StringUtils.isNotBlank(parPath)) {
					entity.setPath(entity.getId() + BaseConstants.SPLITER_FLAG + parPath);
				} else {
					entity.setPath(entity.getId().toString());
				}
			}
		} else {
			// 2.保存path路径
			entity.setPath(entity.getId() + BaseConstants.SPLITER_FLAG + parentId.toString());
		}
		//super.merge(entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Long pk) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "dictionary.remove");
		qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(pk)));
		execute(qt);
	}
}
