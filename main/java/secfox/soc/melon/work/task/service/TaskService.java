package secfox.soc.melon.work.task.service;

import java.util.List;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.work.task.domain.Task;

/**
 * 
 * @author 熊超
 * 2014-11-6
 * @version 1.0
 */
public interface TaskService extends GenericService<Task, Long>{
	
	/**
	 * 通过节点查询树
	 * @param rootId
	 * @return
	 */
	public List<Task> findTree(Long rootId, boolean choosen);
	
	/**
	 * 创建根节点
	 * @return
	 */
	public abstract Task createRoot();
	
}
