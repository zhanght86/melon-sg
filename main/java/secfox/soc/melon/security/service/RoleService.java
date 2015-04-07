/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.service;

import java.util.List;
import java.util.Set;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.domain.Role;
import secfox.soc.melon.security.query.RolePageQuery;

/**
 * @since 1.0 2014年9月18日,上午10:03:34
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface RoleService extends GenericService<Role, Long> {

	
    /**
     * 返回所有的角色
     * @return
     */
    List<Role> findAll();
	
	/**
	 * 获取角色/岗位的分页查询
	 * @param pageQuery
	 * @return
	 */
	Pagination<Role> findPage(RolePageQuery pageQuery);

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public abstract Set<MenuResource> findResources(Long roleId);
	
	/**
	 * 唯一性判断
	 */
	 Role findByCode(String code);

}