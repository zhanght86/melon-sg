/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.security.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.AntPathRequestMatcher;
import secfox.soc.melon.security.AntPathRequestMatcherComparator;
import secfox.soc.melon.security.SecurityConstants;
import secfox.soc.melon.security.dao.MenuResourceDao;
import secfox.soc.melon.security.dao.RoleDao;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.domain.MenuResourceComparator;
import secfox.soc.melon.security.domain.Role;
import secfox.soc.melon.security.service.MenuResourceService;

import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class MenuResourceServiceImpl extends GenericServiceImpl<MenuResource, Long>	implements MenuResourceService {
	
	private MenuResourceDao menuResourceDao;
	
	private Map<AntPathRequestMatcher, List<ConfigAttribute>> allConfigs;
	
	private LoadingCache<Long, MenuResource> menuCache;
	
	private List<MenuResource> allResources;
	
	private List<MenuResource> commonResources;
	
	private List<AntPathRequestMatcher> allMatchers;
	
	private RoleDao roleDao;
	
	@Inject
	public MenuResourceServiceImpl(MenuResourceDao menuResourceDao, RoleDao roleDao) {
		super();
		this.menuResourceDao = menuResourceDao;
		this.roleDao = roleDao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<MenuResource, Long> getDao() {
		return menuResourceDao;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public Map<Role, Set<MenuResource>> findRoleResources() {
		List<Role> roles = roleDao.findAll();
		ImmutableMap.Builder<Role, Set<MenuResource>> roleBuilder = ImmutableMap.builder();
		//存储在缓存中
		for(Role role : roles) {
			roleBuilder.put(role, role.getResources());
		}
		return roleBuilder.build();
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<MenuResource> findCommonMenus() {
		if(commonResources == null) {
			QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "menuResource.findCommons");
			qt.addParameter("path", QueryTemplate.buildLeftLike(SecurityConstants.PATH_COMMON));
			qt.setCachable(true);
			commonResources = findDomains(qt);
		}
		return commonResources;
	}
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public List<MenuResource> buildMyMenus(Account account, List<MenuResource> locations) {
		//
		Map<Role, Set<MenuResource>> roleResource = findRoleResources();
		//获取自定义菜单
		List<MenuResource> myMenus = Lists.newArrayList();
		for(Role role : account.getRoles()) {
			Set<MenuResource> roleMenus = roleResource.get(role);
			if(roleMenus != null) {
				for(MenuResource menu : roleMenus) {
					//添加所有菜单
					if(!myMenus.contains(menu)) {
						myMenus.add(menu);
					}
				}
			}
		}
		//添加通用菜单
		myMenus.addAll(findCommonMenus());
		//添加激活菜单位置
		if(locations != null) {
			//待优化,设置状态为未激活
			for(MenuResource menu : myMenus) {
				menu.setActive(false);
			}
			//设置菜单的激活位置
			for(MenuResource location : locations) {
				MenuResource current = findMenuById(myMenus, location.getId());
				if(current != null) {
					current.setActive(true);
				}
			}
		}
		//生成父子菜单关系
		for(MenuResource menu : myMenus) {
			//只添加菜单,刨除按钮与仪表盘
			if(menu.getType() == 0) {
				MenuResource parentMenu = findMenuById(myMenus, menu.getParentId());
				//刨除根节点
				if(parentMenu != null) {
					parentMenu.addItem(menu);
				}
			}
		}
		//组织菜单并排序
		List<MenuResource> sortedMenus = Lists.newArrayList();
		for(MenuResource menu : myMenus) {
			//只取第一层菜单
			if(menu.getDeep() == 1 && menu.getType() == 0) {
				sortedMenus.add(menu);
				//排序
				menu.sort();
			}
		}
		//对菜单排序
		Collections.sort(sortedMenus, new MenuResourceComparator());
		return sortedMenus;
	}
	
	/**
	 * 通过id从列表中寻找对象
	 * @param menus
	 * @param id
	 * @return
	 */
	private MenuResource findMenuById(List<MenuResource> menus, Long id) {
		for(MenuResource menu : menus) {
			//Long型比较
			if(menu.getId().equals(id)) {
				return menu;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param menu
	 */
	@Override
	public void initLocationPath(MenuResource menu) {
		String path = menu.getPath();
		if(StringUtils.isNotBlank(path)) {
			Iterable<String> menuIds = Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(path);
			List<MenuResource> locations = Lists.newArrayList();
			//获取访问路径
			for(String menuId : menuIds) {
				Long menuPk = Long.parseLong(menuId);
				if(menuPk != BaseConstants.ROOT_ID) {
					//从头部添加
					locations.add(0, findInLocal(menuPk));
				}
			}
			menu.setLocations(locations);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.security.service.MenuResourceService#findTree(java.lang.Long)
	 */
	@Override
	public List<MenuResource> findTree(Long rootId, boolean choosen) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from MenuResource a where 1=1 ");
		MenuResource menuResource = new MenuResource();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//如果是默认节点
		if(rootId == BaseConstants.ROOT_ID){
			menuResource = createRoot();
		} else {
			menuResource = find(rootId); //根据id获取
			path = menuResource.getPath();
		}
		//路径匹配
		qt.append(" and a.path like :path ");
		qt.addParameter("path", QueryTemplate.buildLeftLike(path));
		//是否包含通用路径
		if(choosen) {
			qt.append(" and a.path not like :notPath ");
			qt.addParameter("notPath", QueryTemplate.buildLeftLike(SecurityConstants.PATH_COMMON));
		}
		//排序
		qt.append(" order by a.order desc");
		qt.setMaxResults(1000);
		List<MenuResource> types = findDomains(qt);//获取孙子节点
		//如果包含根节点
		if(types.contains(menuResource)){ 
			MenuResource typeRoot = types.get(types.indexOf(menuResource)); //获取这个对象的位置
			typeRoot.setAsRoot(true); //设置为根节点
			typeRoot.getState().setOpened(true); //默认打开节点
		}else{
			//不包含,就加进去
			types.add(menuResource);
		}
		return types;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public List<AntPathRequestMatcher> createAllMatchers() {
		if(allMatchers == null) {
			allMatchers = Lists.newArrayList();
			List<MenuResource> resources = findAll();
			//ImmutableList.Builder<AntPathRequestMatcher> matcherBuilder = ImmutableList.builder();
			for(MenuResource menu : resources) {
				String url = menu.getUrl();
				if(StringUtils.isNotBlank(url)) {
					AntPathRequestMatcher matcher = new AntPathRequestMatcher(url, menu.getOrder());
					matcher.setMenu(menu);
					allMatchers.add(matcher);
					//matcherBuilder.add(matcher);
				}
			}
			//allMatchers = matcherBuilder.build();
		}
		return allMatchers;
	}
	
	/**
	 * 创建根节点
	 * @return
	 */
	@Override
	public MenuResource createRoot() {
		MenuResource resource = new MenuResource();
		resource.setShortName(MessageSourceUtils.getMessage("security.resource.root"));//创建根节点
		resource.setId(BaseConstants.ROOT_ID);
		resource.setParentId(null);
		resource.getState().setOpened(true);
		return resource;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.MenuResourceService#findAll()
	 */
	@Override
	@Cacheable("secfox.soc.melon.security.domain.MenuResource")
	@Transactional(readOnly = true)
	public List<MenuResource> findAll() {
		if(allResources == null) {
			QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "menuResource.findAll");
			qt.setMaxResults(2000);//最多支持2000条数据
			qt.setCachable(true);
			allResources = ImmutableList.copyOf(menuResourceDao.findDomains(qt));
		}
		return allResources;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public MenuResource findInLocal(Long id) {
		if(menuCache == null) {
			menuCache = CacheBuilder.newBuilder()
									.build(
					           new CacheLoader<Long, MenuResource>() {
						        	   public MenuResource load(Long id) {
						        		   for(MenuResource menu : allResources) {
						        			   //主键匹配即相等
						        			   if(id.equals(menu.getId())) {
						        				   return menu;
						        			   }
						        		   }
						        		   return null;
						        	   }
					    });
		}
		//从缓存中获取数据
		try {
			return menuCache.get(id);
		} catch (ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.MenuResourceService#findAllConfigs()
	 */
	@Override
	public Map<AntPathRequestMatcher, List<ConfigAttribute>> findAllConfigs() {
		if(allConfigs == null) {
			List<MenuResource> resources = findAll();
			//build configs
			Map<AntPathRequestMatcher, List<ConfigAttribute>> configMap = Maps.newTreeMap(new AntPathRequestMatcherComparator());
			//添加自定义资源
			for(MenuResource resource : resources) {
				//获取访问路径
				String url = resource.getUrl();
				if(StringUtils.isNotBlank(url)) {
					String path = resource.getPath();
					//创建路径访问适配器
					int order = resource.getOrder();//顺序
					AntPathRequestMatcher matcher = new AntPathRequestMatcher(url, order);
					List<ConfigAttribute> configAttrs = null;
					//首先添加公用角色与主页资源
					if(StringUtils.endsWith(path, SecurityConstants.PATH_COMMON)) {
						configAttrs = Lists.newArrayList();
						//关联公用角色
						configAttrs.add(new SecurityConfig(SecurityConstants.ROLE_COMMON));
						//添加到权限配置表中
						configMap.put(matcher, configAttrs);
					} else {
						Set<Role> roles = resource.getRoles();
						//添加自身
						addMatcherConfig(matcher, configMap, configAttrs, roles, order);
					}

				}
			}
			//返回只读Map
			allConfigs = ImmutableMap.copyOf(configMap);
		}
		return allConfigs;
	}
	
	/**
	 * 添加系统资源
	 * @param matcher
	 * @param configMap
	 * @param configAttrs
	 * @param roles
	 * @param order
	 */
	private void addMatcherConfig(AntPathRequestMatcher matcher, Map<AntPathRequestMatcher, List<ConfigAttribute>> configMap,
						  List<ConfigAttribute> configAttrs,
						  Set<Role> roles, int order) {
		//先判断是否存在对应的角色
		configAttrs = configMap.get(matcher);
		if(configAttrs == null) {
			configAttrs = Lists.newArrayList();
		}
		//将角色转换为SecurityConfig
		for(Role role : roles) {
			 configAttrs.add(new SecurityConfig(role.getAuthority()));
		}
		//添加到权限配置表中
		configMap.put(matcher, configAttrs);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.MenuResourceService#findPathByUrl(java.lang.String)
	 */
	@Override
	public List<MenuResource> findPathByUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.MenuResourceService#findByPath(java.lang.String)
	 */
	@Override
	public MenuResource checkUniqueUrl(String url) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "menuResource.findUrlEqual");
		qt.addParameter("url", url);
		return findFirstDomain(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.MenuResourceService#findByType(short)
	 */
	@Override
	public List<MenuResource> findByType(short type) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "menuResource.findByType");
		qt.addParameter("type", type);
		return findDomains(qt);
	}
}
