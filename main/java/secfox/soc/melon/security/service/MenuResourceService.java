/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
*/
package secfox.soc.melon.security.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.security.AntPathRequestMatcher;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.domain.MenuResource;

/**
 * 
 * @since 1.0 2014年10月3日,下午4:45:20
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0
 */
public interface MenuResourceService extends GenericService<MenuResource, Long> {
	
	/**
	 * 通过节点查询树
	 * @param rootId
	 * @return
	 */
	public List<MenuResource> findTree(Long rootId, boolean choosen);
	
	/**
	 * 获取所有的节点
	 * @return
	 */
	public List<MenuResource> findAll();
	
	/**
	 * 获取所有的资源角色配置,用于适配请求路径与角色,提供给权限管理使用
	 * @return
	 */
	public Map<AntPathRequestMatcher, List<ConfigAttribute>> findAllConfigs();
	
	/**
	 * 通过url获取访问路径
	 * @param url
	 * @return
	 */
	List<MenuResource> findPathByUrl(String url);

	/**
	 * 创建根节点
	 * @return
	 */
	public abstract MenuResource createRoot();

	/**
	 * 
	 * @param id
	 * @return
	 */
	public abstract MenuResource findInLocal(Long id);

	/**
	 * 
	 * @return
	 */
	public abstract List<AntPathRequestMatcher> createAllMatchers();

	/**
	 * 
	 * @param menu
	 */
	public abstract void initLocationPath(MenuResource menu);

	/**
	 * 
	 * @param account
	 * @param locations
	 * @return
	 */
	public abstract List<MenuResource> buildMyMenus(Account account, List<MenuResource> locations);
	
	/**
	 * 唯一性判断
	 */
	
	MenuResource checkUniqueUrl(String path);
	
	/**
	 * 按类型获取菜单
	 * @param type
	 * @return
	 */
	List<MenuResource> findByType(short type);
}
