/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.security.service.impl;


import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.dao.RoleDao;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.domain.Role;
import secfox.soc.melon.security.query.RolePageQuery;
import secfox.soc.melon.security.service.MenuResourceService;
import secfox.soc.melon.security.service.RoleService;

/**
 * @since 2014-9-18,上午11:34:09
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

	private RoleDao dao;
	
	private MenuResourceService menuService;
	
	@Inject
	public RoleServiceImpl(MenuResourceService menuService, RoleDao dao){
		super();
		this.menuService = menuService;
		this.dao = dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Role, Long> getDao() {
		return dao;
	}
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public Set<MenuResource> findResources(Long roleId) {
		Role role = find(roleId);
		Set<MenuResource> resources = role.getResources();
		resources.add(menuService.createRoot());
		return resources;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.RoleService#findAll()
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Role> findAll() {
        return dao.findAll();
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.RoleService#findPage(secfox.soc.melon.security.controller.query.RolePageQuery)
	 */
	@Override
	public Pagination<Role> findPage(RolePageQuery pageQuery) {
		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<Role, RolePageQuery>() {
			
			/*
			 * (non-Javadoc)
			 * @see secfox.soc.melon.persistence.QueryBuilder#buildSelect(secfox.soc.melon.persistence.QueryTemplate)
			 */
			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select role from Role role");
			}
			
			/*
			 * (non-Javadoc)
			 * @see secfox.soc.melon.persistence.QueryBuilder#buildWhere(java.lang.Object, secfox.soc.melon.persistence.QueryTemplate)
			 */
			@Override
			public void buildWhere(Role s, QueryTemplate qt) {
				//按名称查询
				String name = s.getName();
				if(StringUtils.isNotBlank(name)) {
					qt.append(" and role.name like :name ");
					qt.addParameter("name", QueryTemplate.buildAllLike(name));
				}
			}
			
			/*
			 * (non-Javadoc)
			 * @see secfox.soc.melon.persistence.QueryBuilder#buildBys(java.lang.String, secfox.soc.melon.base.SortOrder, secfox.soc.melon.persistence.QueryTemplate)
			 */
			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				if(StringUtils.isBlank(column)) {
					column = "createTime";
				}
				qt.append(QueryTemplate.buildOrderBy("role", column, order));
			}
			
			/*
			 * (non-Javadoc)
			 * @see secfox.soc.melon.persistence.PaginationBuilder#buildCount(secfox.soc.melon.persistence.QueryTemplate)
			 */
			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(role) from Role role ");
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.RoleService#findByCode(java.lang.String)
	 */
	@Override
	public Role findByCode(String code) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "Role.findByCode");
		qt.addParameter("code", code);
		return findFirstDomain(qt);
	}
}
