package secfox.soc.melon.work.task.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.work.task.dao.TaskDao;
import secfox.soc.melon.work.task.domain.Task;

/**
 * 
 * @author 熊超
 * 2014-11-6
 * @version 1.0
 */
@Repository
public class TaskDaoImpl extends GenericDaoImpl<Task, Long> implements TaskDao {

	public TaskDaoImpl() {
		super(Task.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#persist(secfox.soc.melon.persistence.Identifiable)
	 */
	@Override
	public void persist(Task entity) {
		// 1.保存entity
		super.persist(entity);
		Long parentId = entity.getParentId();
		// 如果父节点不为根节点
		if (parentId != BaseConstants.ROOT_ID) {
			// 2.保存path路径
			Task parent = super.getReference(entity.getParentId());
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
		Task current = getReference(pk);
		//先找到所有的子孙节点
		QueryTemplate childrenQt = QueryTemplate.create(QueryType.NamedQuery, "task.findChildren");
		childrenQt.addParameter("path", QueryTemplate.buildLeftLike(current.getPath()));
		List<Task> resources = findDomains(childrenQt);
		//对节点依次迭代
		for(Task task : resources) {
			super.remove(task);
		}
	}
	

}
