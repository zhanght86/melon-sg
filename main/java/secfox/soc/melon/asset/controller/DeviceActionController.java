/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.DeviceAction;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.service.DeviceActionService;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.asset.service.InfoSystemService;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * @since @2014-11-5,@下午3:19:02
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/asset/action")
public class DeviceActionController {

	private DeviceActionService deviceActionService;
	private DeviceService deviceService;
	private InfoSystemService infoSystemService;
	
	

	@Inject
	public DeviceActionController(InfoSystemService infoSystemService,DeviceService deviceService,DeviceActionService deviceActionService) {
		this.deviceActionService = deviceActionService;
		this.deviceService = deviceService;
		this.infoSystemService = infoSystemService;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/create/{deviceType}", method = RequestMethod.GET)
	public String create(@ModelAttribute DeviceAction deviceAction,@PathVariable("deviceType") int deviceType, Model model,HttpServletRequest req) {
		String parameter = req.getParameter("deviceId"); //安全对象ID
		Long deviceId = null;
		
		if(parameter!=null){
			//如果是设备的运维
			if(deviceType == 0){
				deviceId = Long.valueOf(parameter);
				Device device = deviceService.find(deviceId);
				if(device != null){
					deviceAction.setType(device.getUsing());
				}
			}
			//如果是信息系统的运维
			if(deviceType == 1){
				deviceId = Long.valueOf(parameter);
				InfoSystem info = infoSystemService.find(deviceId);
				if(info != null){
					deviceAction.setType(info.getUsing());
				}
			}
			
		}
		return "asset.action.edit";
	}

	/**
	 * 新增运维
	 * @param deviceAction
	 * @param deviceType //0:代表设备, 1:代表信息系统
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/create/{deviceType}", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("deviceAction") DeviceAction deviceAction,@PathVariable("deviceType") int deviceType,HttpServletRequest req) {
		String parameter = req.getParameter("deviceId"); //安全对象ID
		Long deviceId = null;
		if(parameter!=null){
			deviceId = Long.valueOf(parameter);
			deviceAction.setDeviceId(deviceId);
		}
		
		UserInfo user = SecurityContextUtils.getCurrentUserInfo();
		deviceAction.setActor(user);  //创建人
		Date createDate = new Date();
		deviceAction.setActTime(createDate);// 创建时间赋值
		deviceAction.setDeviceType(deviceType);
		
		
		
		deviceActionService.persist(deviceAction);
		//如果是设备的运维
		if(deviceAction.isDevice()){
			return "redirect:/asset/device/show/"+parameter;
		}
		//如果是信息系统的运维
		if(deviceAction.isInfo()){
			return "redirect:/asset/infoSystem/show/"+parameter;
		}
		return "redirect:/asset/action/list";
	}

	
	/**
	 * 所有设备列表
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,HttpServletRequest req) {
		String parameter = req.getParameter("deviceId");
		List<DeviceAction> actions = Lists.newArrayList();
		if(parameter!=null){
			Long deviceId = Long.valueOf(parameter);
			actions = deviceActionService.findByDeviceId(deviceId);
		}
		model.addAttribute("actions", actions);
		return "melon/asset/action/list";
	}

}
