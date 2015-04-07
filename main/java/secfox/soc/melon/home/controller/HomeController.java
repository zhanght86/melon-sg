/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.home.domain.PortalLayout;
import secfox.soc.melon.home.domain.PortalView;
import secfox.soc.melon.home.service.PortalLayoutService;
import secfox.soc.melon.home.service.PortalViewService;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * @since 1.0 2014年10月2日,下午5:25:58
 * 默认的登陆后的主页控制器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	private PortalLayoutService layoutService;
	
	private PortalViewService viewService;
	
	/**
	 * @param layoutService
	 */
	@Inject
	public HomeController(PortalLayoutService layoutService, PortalViewService viewService) {
		super();
		this.layoutService = layoutService;
		this.viewService = viewService;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(HttpServletRequest request) {
		//判断是否初次登陆
		Account account = SecurityContextUtils.getCurrentAccount(request);
		List<PortalLayout> layouts = layoutService.findLayout(account.getId());
		//判断是否配置视图
		if(layouts.isEmpty()) {
			//
			request.setAttribute("init", true);
			return "home.main";
			
		} else {
			//处理视图ID
			String layout = request.getParameter("layout");
			long layoutId = -1;
			//必定不是主页
			if(StringUtils.isNotBlank(layout)) {
				layoutId = Long.parseLong(layout);
			}
			//处理布局
			PortalLayout current = null;
			PortalLayout main = null;
			//找到主页
			for(PortalLayout lt : layouts) {
				if(lt.isDefaultHome()) {
					main = lt;
				}
				if(lt.getId().longValue() == layoutId) {
					current = lt;
					current.setActive(true);
				}
			}
			//主页
			if(current == null) {
				//判断是否设置过主页
				if(main != null) {
					//删除主页视图
					layouts.remove(main);
					//设置当前视图
					current = main;
				} else {
					request.setAttribute("homeLayouts", layouts);
					return "home.main";
				}
				
			} else {
				//非主页
				layouts.remove(main);
			}
			//获取视图
			List<PortalView> views = viewService.findViews(current.getId());
			//
			request.setAttribute("init", false);
			request.setAttribute("homeLayouts", layouts);
			request.setAttribute("ltr", current.isLeftToRight());
			request.setAttribute("views", views);
			//
			return "home.main";
		}
		
	}
	
	@RequestMapping(value="/welcome")
	public String welcome() {
		return "home.welcome";
	}
	
	@RequestMapping(value="/export",method=RequestMethod.GET, produces={"application/vnd.ms-word"})
	public String export(Model model,HttpServletRequest req) {
		model.addAttribute("title", "罚款通知单");
		model.addAttribute("code", "1101");
		model.addAttribute("organ", "北京疙瘩湾里派出所");
		return "homeDocx";
	}
	
	@RequestMapping(value="/portal")
	public String portal() {
		return "melon/home/bar";
	}
	
	@RequestMapping(value="/workPortal")
	public String workPortal() {
		return "melon/home/portals/myTask";
	}
}
