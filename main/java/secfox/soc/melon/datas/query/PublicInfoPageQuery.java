package secfox.soc.melon.datas.query;

import secfox.soc.melon.base.PageQuery;

public class PublicInfoPageQuery extends PageQuery<PublicInfoSearchForm>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected PublicInfoSearchForm initSearchForm() {
		return new PublicInfoSearchForm();
	}

}
