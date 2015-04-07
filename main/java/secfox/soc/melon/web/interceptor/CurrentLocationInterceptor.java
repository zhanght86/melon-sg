/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.interceptor;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import secfox.soc.melon.security.AntPathRequestMatcher;
import secfox.soc.melon.security.AntPathRequestMatcherComparator;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.service.MenuResourceService;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.WebConstants;

/**
 * @since 1.0 2014年9月29日,下午4:44:03
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class CurrentLocationInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

	private MenuResourceService menuService;
	
	private List<AntPathRequestMatcher> menuMatchers;
	
	/**
	 * @param menuService
	 */
	public CurrentLocationInterceptor(MenuResourceService menuService) {
		super();
		this.menuService = menuService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String method = StringUtils.upperCase(request.getMethod());
		String accept = StringUtils.upperCase(request.getHeader("accept"));//获取请求的类型
		Account account = SecurityContextUtils.getCurrentAccount(request);//获取当前帐户
		//只有HTML才过滤
		boolean canFilter = StringUtils.equals(RequestMethod.GET.name(), method) 
							&& (StringUtils.contains(accept, WebConstants.HTML) || 
									StringUtils.contains(accept, WebConstants.ALL_RESOURCE));//兼容IE8
		//准备菜单的访问路径
		if(canFilter && account != null) {
			//request.setAttribute("CURR_LOCATION", path);
			for(AntPathRequestMatcher matcher : menuMatchers) {
				if(matcher.matches(request)) {
					MenuResource menu = matcher.getMenu();
					//初始化访问路径
					menuService.initLocationPath(menu);
					request.setAttribute(WebConstants.CURR_LOCATION, menu);
					//生成菜单
					List<MenuResource> menus = menuService.buildMyMenus(account, menu.getLocations());
					//设置菜单到用户的范围
					account.setMenus(menus);
					break;
				}
			}
			//设置帐户信息
			request.setAttribute(WebConstants.ACCOUNT, account);
		}
		//设置帐户信息到request中
		
		return super.preHandle(request, response, handler);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		menuMatchers = menuService.createAllMatchers();
		//排序
		Collections.sort(menuMatchers, new AntPathRequestMatcherComparator());
	}
	
	
	
}
