package secfox.soc.melon.work.task.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.SecurityConstants;
import secfox.soc.melon.work.task.dao.TaskDao;
import secfox.soc.melon.work.task.domain.Task;
import secfox.soc.melon.work.task.service.TaskService;
/**
 * 
 * @author 熊超
 * 2014-11-6
 * @version 1.0
 */
@Service
public class TaskServiceImpl extends GenericServiceImpl<Task, Long> implements TaskService {
	
	private TaskDao taskDao;
	
	@Inject
	public TaskServiceImpl(TaskDao taskDao){
		super();
		this.taskDao = taskDao;
	}
	
	@Override
	protected GenericDao<Task, Long> getDao() {
		return taskDao;
	}
	
	/*
	 * 查询树的节点
	 * (non-Javadoc)
	 * @see secfox.soc.melon.plan.service.TaskService#findTree(java.lang.Long, boolean)
	 */
	@Override
	public List<Task> findTree(Long rootId, boolean choosen) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from Task a where 1=1 ");
		Task task = new Task();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//如果是默认节点
		if(rootId == BaseConstants.ROOT_ID){
			task = createRoot();
		} else {
			task = find(rootId); //根据id获取
			path = task.getPath();
		}
		//路径匹配
		qt.append(" and a.path like :path ");
		qt.addParameter("path", QueryTemplate.buildLeftLike(path));
		//是否包含通用路径
		if(choosen) {
			qt.append(" and a.path not like :notPath ");
			qt.addParameter("notPath", QueryTemplate.buildLeftLike(SecurityConstants.PATH_COMMON));
		}
		//排序
		qt.append(" order by a.order desc");
		List<Task> types = findDomains(qt);//获取孙子节点
		//如果包含根节点
		if(types.contains(task)){ 
			Task typeRoot = types.get(types.indexOf(task)); //获取这个对象的位置
			typeRoot.setAsRoot(true); //设置为根节点
			typeRoot.getState().setOpened(true); //默认打开节点
		}else{
			//不包含,就加进去
			types.add(task);
		}
		return types;
	}
	
	@Override
	public Task createRoot() {
		Task task = new Task();
		task.setName(MessageSourceUtils.getMessage("plan.task"));//创建根节点
		task.setId(BaseConstants.ROOT_ID);
		task.setParentId(null);
		task.getState().setOpened(true);
		return task;
	}
	

}
