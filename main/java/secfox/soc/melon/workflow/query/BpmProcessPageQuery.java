/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.workflow.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.workflow.domain.BpmProcess;

/**
 * @since 1.0 2014年9月19日,下午3:52:09
 * 用户分页查询
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class BpmProcessPageQuery extends PageQuery<BpmProcess> {

	private static final long serialVersionUID = -4625252907870767925L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.PageQuery#initSearchForm()
	 */
	@Override
	protected BpmProcess initSearchForm() {
		BpmProcess bpmProcess = new BpmProcess();
		return bpmProcess;
	}

}
