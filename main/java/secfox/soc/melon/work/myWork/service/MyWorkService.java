package secfox.soc.melon.work.myWork.service;

import java.util.List;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.work.myWork.domain.MyWork;
import secfox.soc.melon.work.myWork.query.WorkPageQuery;

/**
 * @author 曹贞杰
 *
 * 2014年11月20日
 * @version 1.0
 */
public interface MyWorkService extends GenericService<MyWork, Long> {

	Pagination<MyWork> findPage(WorkPageQuery pageQuery);
	
	List<MyWork> findAll();
	
	List<MyWork> findByWork(Long organId);
	
	/**
	 * 查询待完成的工作
	 * @param id
	 * @return
	 */
	List<MyWork> findUndo(Long id);
	
	/**
	 * 查询已下发的工作
	 * @param id
	 * @return
	 */
	List<MyWork> findPassed(Long id);
	
	/**
	 * 改变任务状态
	 * @param id
	 * @return
	 */
	MyWork changeState(Long id);
	
}
