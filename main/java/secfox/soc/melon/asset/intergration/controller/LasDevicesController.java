package secfox.soc.melon.asset.intergration.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.intergration.domain.LasDevices;
import secfox.soc.melon.asset.intergration.query.LasDevicesPageQuery;
import secfox.soc.melon.asset.intergration.service.LasDevicesService;
import secfox.soc.melon.base.Pagination;


/**
 * 同步设备Controller
 * @author lif
 *
 */
@Controller
@RequestMapping("/asset/lasDevice")
public class LasDevicesController {
	
	private LasDevicesService lasDevicesService;
	
	@Inject
	public LasDevicesController(LasDevicesService lasDevicesService) {
		this.lasDevicesService = lasDevicesService;
	}
	
	
	/**
	 * 所有设备列表分页查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public  Pagination<LasDevices> query(@ModelAttribute LasDevicesPageQuery pageQuery) {
		return lasDevicesService.findPages(pageQuery);
	}

	/**
	 * 所有设备列表
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") LasDevicesPageQuery pageQuery) {
		return "asset.lasDevice.list";
	}
	
	@RequestMapping(value = "/importList")
	public String importList() {
		lasDevicesService.saveAsLasDevices();
		return  "redirect:/asset/lasDevice/list";
	}


	/**
	 * 
	 * @param id
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@RequestParam("id") Long id, RedirectAttributes attributes) {
		LasDevices lasDevices = lasDevicesService.find(id);
		Device device=  new Device();
		//在LasDevice中的属性存入Device中
		if(lasDevices.getIps() != null){
			lasDevices.getIps().get(0).setMac(lasDevices.getMac());
		}
		device.setIps(lasDevices.getIps());
		device.setProducer(lasDevices.getProducer());
		device.setModel(lasDevices.getModel());
		device.setName(lasDevices.getName());
		device.setHasIp(true);
		device.setLasDeviceId(lasDevices.getId());
		attributes.addFlashAttribute(device);
		return "redirect:/asset/device/create";
	}
	
	/**
	 * 内容详细页面
	 * @param id 设备id
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		
		LasDevices device = (LasDevices)model.asMap().get("device");
		if (device == null) {
        	device = lasDevicesService.find(id);
        }
		model.addAttribute("device", device);
		return "asset.lasDevice.show";
	}
}
	

