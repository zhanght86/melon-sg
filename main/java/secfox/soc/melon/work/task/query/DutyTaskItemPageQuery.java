/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.work.task.domain.DutyTaskItem;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-11
 * @version  1.0
 */
public class DutyTaskItemPageQuery  extends PageQuery<DutyTaskItem>{

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	protected DutyTaskItem initSearchForm() {
		DutyTaskItem taskItem = new DutyTaskItem();
		return taskItem;
	}

}
