/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.security.domain.Role;

/**
 * 角色分页查询类
 * @since 2014-9-23,上午19:34:09
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version  1.0
 */
public class RolePageQuery extends PageQuery<Role> {

	private static final long serialVersionUID = -4625252907870767925L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.PageQuery#initSearchForm()
	 */
	@Override
	protected Role initSearchForm() {
		Role role = new Role();
		//默认为角色适用于所有人
		role.setLevel(new Integer[] {0,1,2});
		return role;
	}

}
