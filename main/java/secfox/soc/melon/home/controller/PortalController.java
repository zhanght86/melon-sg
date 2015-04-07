/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import secfox.soc.melon.base.UmsInfoUtils;
import secfox.soc.melon.home.domain.PortalLayout;
import secfox.soc.melon.home.domain.PortalView;
import secfox.soc.melon.home.service.PortalLayoutService;
import secfox.soc.melon.home.service.PortalViewService;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.service.MenuResourceService;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.system.SystemState;

/**
 * @since 1.0 2014年11月1日,下午1:16:03
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping(value="/home/portal")
public class PortalController {
	
	private PortalLayoutService layoutService;
	
	private PortalViewService viewService;
	
	private SystemState sysState;
	
	private MenuResourceService menuService;
	
	/**
	 * @param layoutService
	 * @param viewService
	 */
	@Inject
	public PortalController(PortalLayoutService layoutService,	PortalViewService viewService, MenuResourceService menuService, SystemState sysState) {
		super();
		this.layoutService = layoutService;
		this.viewService = viewService;
		this.menuService = menuService;
		this.sysState = sysState;
	}
	
	/**
	 * 创建视图
	 * @param layout
	 * @return
	 */
	@RequestMapping(value="/createLayout", method=RequestMethod.GET)
	public String createLayout(@ModelAttribute("layout") PortalLayout layout) {
		layout.setUserId(SecurityContextUtils.getCurrentAccount().getId());
		return "melon/home/portal/editLayout";
	}
	
	/**
	 * 
	 * @param layout
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/createLayout", method=RequestMethod.POST)
	@ResponseBody
	public PortalLayout createLayout(@Valid@ModelAttribute PortalLayout layout, BindingResult result) {
		if(result.hasErrors()) {
			return layout;
		}
		
		return layoutService.persist(layout, SecurityContextUtils.getCurrentUserInfo().getUserId());
	}
	
	/**
	 * 
	 * @param layoutId
	 * @param view
	 * @return
	 */
	@RequestMapping(value="/createView/{layoutId}", method=RequestMethod.GET)
	public String createView(@PathVariable("layoutId")Long layoutId, @ModelAttribute("view") PortalView view, Model model) {
		//设置布局
		PortalLayout layout = new PortalLayout();
		layout.setId(layoutId);
		view.setLayout(layout);
		List<MenuResource> menus =  menuService.findByType((short) 2);
		model.addAttribute("menus", menus);
		return "melon/home/portal/editView";
	}
	
	/**
	 * 
	 * @param layoutId
	 * @param view
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/createView/{layoutId}", method=RequestMethod.POST)
	@ResponseBody
	public PortalView createView(@PathVariable("layoutId")Long layoutId, @Valid@ModelAttribute("view") PortalView view, BindingResult result) {
		viewService.persist(view);
		return view;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/layout", method=RequestMethod.GET)
	public String layout(Model model) {
		//设置拥有者
		Long userId = SecurityContextUtils.getCurrentAccount().getId();
		model.addAttribute("layouts", layoutService.findLayout(userId));
		return "home.portal.layout";
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listView/{id}", method=RequestMethod.GET)
	public String list(@PathVariable("id") Long id, @RequestParam("ltr") boolean ltr, Model model) {
		List<PortalView> views = viewService.findViews(id);
		model.addAttribute("views", views);
		model.addAttribute("ltr", ltr);
		return "melon/home/portal/list";
	}
	
	/**
	 * 
	 * @param id
	 */
	@RequestMapping(value="/removeLayout/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeLayout(@PathVariable("id") Long id) {
		layoutService.remove(id);
	}
	
	/**
	 * 
	 * @param id
	 */
	@RequestMapping(value="/removeView/{id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void removeView(@PathVariable("id") Long id) {
		viewService.remove(id);
	}
	
	/**
	 * 对视图进行排序
	 */
	@RequestMapping(value="/sortLayout", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void sortLayout(@ModelAttribute("ids") String ids) {
		layoutService.sortLayout(ids);
	}
	
	/**
	 * 对仪表盘进行排序
	 * @param ids
	 */
	@RequestMapping(value="/sortView", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void sortView(@ModelAttribute("ids") String ids) {
		viewService.sortView(ids);
	}
	
	/**
	 * 更新Portal布局
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateLayout/{id}", method=RequestMethod.GET)
	public String updateLayout(@PathVariable("id") Long id, Model model) {
		PortalLayout layout = layoutService.find(id);
		model.addAttribute("layout", layout);
		return "melon/home/portal/editLayout";
	}
	
	/**
	 * 更新Portal布局
	 * @param id
	 * @param layout
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/updateLayout/{id}", method=RequestMethod.POST)
	@ResponseBody
	public PortalLayout updateLayout(@PathVariable("id") Long id, @Valid@ModelAttribute PortalLayout layout, BindingResult result) {
		layoutService.merge(layout);
		return layout;
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateView/{id}", method=RequestMethod.GET)
	public String updateView(@PathVariable("id") Long id, Model model) {
		PortalView view = viewService.find(id);
		List<MenuResource> menus =  menuService.findByType((short) 2);
		model.addAttribute("menus", menus);
		model.addAttribute("view", view);
		return "melon/home/portal/editView";
	}
	
	/**
	 * 
	 * @param id
	 * @param view
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/updateView/{id}", method=RequestMethod.POST)
	@ResponseBody
	public PortalView updateView(@PathVariable("id") Long id, @Valid@ModelAttribute PortalView view, BindingResult result) {
		viewService.merge(view);
		return view;
	}
	
	/**
	 * portal预览
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/preview/{id}", method=RequestMethod.GET)
	public String preview(@PathVariable("id") Long id, Model model, @RequestParam("ltr") boolean ltr) {
		List<PortalView> views = viewService.findViews(id);
		model.addAttribute("views", views);
		model.addAttribute("ltr", ltr);
		return "home.portal.preview";
	}
	
	/**
	 * 复制view
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/copyView/{id}", method=RequestMethod.GET)
	public String copyView(@PathVariable("id")Long portalId, Model model) {
		List<PortalLayout> layouts = layoutService.findPubViews();
		model.addAttribute("layouts", layouts);
		model.addAttribute("sourceId", portalId);
		return "melon/home/portal/copy";
	}

	/**
	 * 复制view
	 * @param sourceId
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/copyView/{id}", method=RequestMethod.POST)
	public @ResponseBody PortalLayout copyView(@PathVariable("id")Long sourceId, HttpServletRequest req) {
		String targetId = req.getParameter("targetId");
		return layoutService.copy(Long.parseLong(targetId), sourceId);
	}
	
	@RequestMapping(value="/event", method=RequestMethod.GET)
	public String event(Model model) {
		Calendar current = Calendar.getInstance();
		current.add(Calendar.DAY_OF_MONTH, -7);
		current.set(Calendar.HOUR_OF_DAY, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		Date start = current.getTime();
		current.add(Calendar.DAY_OF_MONTH, 7);
		current.set(Calendar.HOUR_OF_DAY, 23);
		current.set(Calendar.MINUTE, 59);
		current.set(Calendar.SECOND, 59);
		Date end = current.getTime();
		model.addAttribute("start", start.getTime());
		model.addAttribute("end", end.getTime());
		return "melon/home/portals/event";
	}
	
	@RequestMapping(value="/alert", method=RequestMethod.GET)
	public String alert(Model model) {
		Calendar current = Calendar.getInstance();
		current.add(Calendar.DAY_OF_MONTH, -7);
		current.set(Calendar.HOUR_OF_DAY, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		Date start = current.getTime();
		current.add(Calendar.DAY_OF_MONTH, 7);
		current.set(Calendar.HOUR_OF_DAY, 23);
		current.set(Calendar.MINUTE, 59);
		current.set(Calendar.SECOND, 59);
		Date end = current.getTime();
		model.addAttribute("start", start.getTime());
		model.addAttribute("end", end.getTime());
		return "melon/home/portals/alert";
	}
	
	//跳转到CPU监控页面
	@RequestMapping(value="/cpu", method=RequestMethod.GET)
	public String system(Model model) {
		model.addAttribute("result", sysState.getCpu());
		return "melon/home/portals/cpu";
		
	}
	
	//获取CPU值
	@RequestMapping(value="/cpu", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> sysDate(){
		return sysState.getCpu();
	}
	
	//跳转到内存监控页面
	@RequestMapping(value="/memory", method=RequestMethod.GET)
	public String memory(Model model) {
		model.addAttribute("result", sysState.getMemory());
		return "melon/home/portals/memory";
		
	}
	
	//获取内存值
	@RequestMapping(value="/memory", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> sysMemory(){
		return sysState.getMemory();
	}
	
	//跳转到硬盘监控页面
	@RequestMapping(value="/disk", method=RequestMethod.GET)
	public String disk(Model model) {
		model.addAttribute("result", sysState.getDisk());
		return "melon/home/portals/disk";
		
	}
	
	//跳转到硬盘监控页面
	@RequestMapping(value="/disk", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> sysDisk() {
		return sysState.getDisk();
		
	}
	
	@RequestMapping(value="/chart2", method=RequestMethod.GET)
	public String chart2(Model model) {
		return "melon/home/portals/chart2";
	}
	
	@RequestMapping(value="/chart3", method=RequestMethod.GET)
	public String chart3(Model model) {
		return "melon/home/portals/chart3";
	}
	@RequestMapping(value="/syslogLine", method=RequestMethod.GET)
	public String syslogLine(Model model) {
		return "melon/home/portals/syslogLine";
	}
	@RequestMapping(value="/alertlogLine", method=RequestMethod.GET)
	public String alertlogLine(Model model) {
		return "melon/home/portals/alertlogLine";
	}
}
