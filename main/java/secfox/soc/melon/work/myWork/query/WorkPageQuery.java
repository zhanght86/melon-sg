package secfox.soc.melon.work.myWork.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.work.myWork.domain.MyWork;

public class WorkPageQuery extends PageQuery<MyWork> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected MyWork initSearchForm() {
		return new MyWork();
	}

}
