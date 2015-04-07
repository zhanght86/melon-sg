/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.organ.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.organ.domain.Organization;

/**
 * @since 2014-9-29,下午2:49:58
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class OrganizationPageQuery extends PageQuery<Organization> {

	private static final long serialVersionUID = -15357320699901020L;

	@Override
	protected Organization initSearchForm() {
		return new Organization();
	}

}
