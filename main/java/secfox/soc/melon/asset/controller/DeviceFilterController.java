/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.controller;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.DeviceFilter;
import secfox.soc.melon.asset.service.DeviceFilterService;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;

/**
 *
 * @since 1.0 2014年11月18日下午7:54:49
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/asset/deviceFilter")
public class DeviceFilterController {
	
	private DeviceFilterService deviceFilterService;
	
	private DeviceService deviceService;
	
	@Inject
	public DeviceFilterController(DeviceFilterService deviceFilterService, DeviceService deviceService){
		this.deviceFilterService = deviceFilterService;
		this.deviceService = deviceService;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(@ModelAttribute DeviceFilter deviceFilter,Model model){
		deviceFilter.setCreator(SecurityContextUtils.getCurrentUserInfo());
		model.addAttribute("organId",deviceFilter.getCreator().getOrganId());
		System.out.println(deviceFilter.getCreator().getOrganId());
		List<DeviceFilter> deviceFilters = deviceFilterService.findList();
		if(deviceFilters.size() != 0){
			//初始化任务提醒列表
			model.addAttribute("deviceFilters",deviceFilters);
			//初始化查询条件框
			model.addAttribute("deviceFilter",deviceFilters.get(0));
			//
			int countDevice = deviceService.findRemindDevice(deviceFilters.get(0)).size();
			if(countDevice == 0){
				model.addAttribute("countDevice",0);
			}else{
				model.addAttribute("countDevice",countDevice);
			}
			
		}
		return "asset.deviceFilter.home";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String create(@ModelAttribute DeviceFilter deviceFilter,Model model){
		//处理时间 
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//获取年份
		cal.set(year, 8, 1);
		//设置默认时间为当前的9月1日
		deviceFilter.setRemindTime(cal.getTime());
		//设置默认标题
		deviceFilter.setOrderName(year+MessageSourceUtils.getMessage("asset.devicefilter.title"));
		model.addAttribute("organId",SecurityContextUtils.getCurrentUserInfo().getOrganId());
		return "melon/asset/deviceFilter/edit";
	}
	
	/**
	 * 保存到期提醒信息
	 * @param deviceFilter
	 * @param result
	 * @param request
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
	public  String home(@Valid @ModelAttribute("deviceFilter") DeviceFilter deviceFilter, BindingResult result, RedirectAttributes attribute) {
		deviceFilter.setCreator(SecurityContextUtils.getCurrentUserInfo());
	 	if(result.hasErrors()) {
            return "asset.deviceFilter.home";
        }
	 	if(deviceFilter.getId() == null){
	 		deviceFilterService.persist(deviceFilter);
	 	}else{
	 		deviceFilterService.merge(deviceFilter);
	 	}
        attribute.addFlashAttribute(deviceFilter);
        //添加提示信息
        ActionHint hint = ActionHint.create("asset.device.create.hint", deviceFilter.getOrderName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        return "redirect:/asset/deviceFilter/home";
	}
	
	/**
	 * 明细   修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		DeviceFilter deviceFilter = deviceFilterService.find(id);
		model.addAttribute("deviceFilter", deviceFilter);
		return "melon/asset/deviceFilter/edit";
	}
	
	/**
	 * 根据任务查找相应的设备
	 */
	@RequestMapping(value="/list/{id}", method=RequestMethod.POST)
	public @ResponseBody List<Device> list(@PathVariable("id") Long id, Model model, RedirectAttributes attribute){
		DeviceFilter deviceFilter = deviceFilterService.getReference(id);
		List<Device> devices = deviceService.findRemindDevice(deviceFilter);
		model.addAttribute("findDevices",devices);
		return devices;
	}
	
	/**
	 * 没有保存任务时点击查询，查找相应的设备
	 * @param deviceFilter
	 * @return
	 */
	@RequestMapping(value="/select", method=RequestMethod.POST)
	public @ResponseBody List<Device> select(@ModelAttribute DeviceFilter deviceFilter){
		List<Device> devices = deviceService.findRemindDevice(deviceFilter);
		return devices;
	}
	
	/**
	 * 删除任务
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	public @ResponseBody ActionStatus remove(@PathVariable Long id) {
		deviceFilterService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
}

