/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.home.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.home.domain.PortalView;
import secfox.soc.melon.home.service.PortalViewService;

/**
 * @since 2014-10-23,下午8:54:23
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping(value="/home/portalView")
public class PortalViewController {

	private PortalViewService viewService;
	
	@Inject
	public PortalViewController(PortalViewService viewService) {
		this.viewService = viewService;
		
	}
	
	/**
	 * 排列layout
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/sortView", method=RequestMethod.POST)
	public @ResponseBody boolean sortLayout(@ModelAttribute("ids")String ids) {
		viewService.sortView(ids);
		return true;
	}
	

	/**
	 * 添加view
	 * @param id
	 * @param view
	 * @return
	 */
	@RequestMapping(value="/addView/{id}", method=RequestMethod.POST)
	public @ResponseBody PortalView addView(@PathVariable("id")Long id, @ModelAttribute("view")PortalView view) {
		return viewService.addView(id, view);
	}
	
	/**
	 * 展示view视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/listView", method=RequestMethod.POST)
	public @ResponseBody List<PortalView> listView(@ModelAttribute("id")String id) {
		return viewService.findViews(Long.valueOf(id));
	}
	
	/**
	 * 删除视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	public @ResponseBody boolean remove(@PathVariable("id")Long id) {
		viewService.remove(id);
		return true;
				
	}
	
	/**
	 * 更新视图
	 * @param layoutId
	 * @param view
	 * @return
	 */
	@RequestMapping(value="/updateView/{id}", method=RequestMethod.POST)
	public @ResponseBody PortalView update(@PathVariable("id")Long layoutId, @ModelAttribute("view")PortalView view) {
		return viewService.updateView(layoutId, view);
	}
}
