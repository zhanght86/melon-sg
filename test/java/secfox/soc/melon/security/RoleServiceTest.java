/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.domain.Role;
import secfox.soc.melon.security.service.MenuResourceService;
import secfox.soc.melon.security.service.RoleService;

/**
 * @since 1.0 2014年10月4日,下午3:50:41
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class RoleServiceTest extends BaseTest {
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private MenuResourceService menuService;

	//@Ignore
	@Test
	@Rollback(false)
	@Transactional
	public void test() {
		Role role = roleService.find(510L);
		for(int i = 520; i < 530; i++) {
			role.addResource(menuService.getReference(new Long(i)));
		}
		roleService.merge(role);
	}
	
	@Ignore
	@Test
	@Rollback(false)
	@Transactional
	public void testMerge() {
		MenuResource menu = menuService.getReference(210L);
		menu.setRoles(null);
		menu.setShortName("你们好吗");
		menuService.merge(menu);
	}

	@Ignore
	@Test
	public void testDelete() {
		menuService.remove(11L);
	}
	
	@Ignore
	@Test
	public void testPersist() {
		for(int i=0; i<10; i++) {
			MenuResource resource = new MenuResource();
			resource.setParentId(310L);
			resource.setShortName("菜单测试" + i);
			menuService.persist(resource);
		}

	}
	
	@Ignore
	@Test
	public void testRemoveRole() {
		roleService.remove(1L);
	}
	
	@Ignore
	@Test
	public void persistRole() {
		Role role = new Role();
		role.setName("系统管理员");
		role.setCode("SYSTEM");
		roleService.persist(role);
	}
}
