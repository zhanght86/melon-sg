package secfox.soc.melon.alarm.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.alarm.dao.AlarmTypeDao;
import secfox.soc.melon.alarm.domain.AlarmType;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 1.0 2014-10-21,下午3:09:19
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Repository
public class AlarmTypeDaoImpl extends GenericDaoImpl<AlarmType, Long> implements
		AlarmTypeDao {

	   public AlarmTypeDaoImpl(){
		   super(AlarmType.class);
	   }
	   
	   
	   
	   @Override
		public void persist(AlarmType entity) {
			// 1.保存entity
			super.persist(entity);
			Long parentId = entity.getParentId();
			// 如果父节点不为根节点
			if (parentId != BaseConstants.ROOT_ID) {
				// 2.保存path路径
				AlarmType reference = super.getReference(entity.getParentId());
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
			AlarmType findType = find(pk);
			QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"alarm.remove");
			qt.addParameter("path", QueryTemplate.buildLeftLike(findType.getPath()));
			execute(qt);
		}
}

