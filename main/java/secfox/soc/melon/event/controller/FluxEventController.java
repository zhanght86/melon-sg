package secfox.soc.melon.event.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.event.service.FluxEventService;

/**
 *
 * @since 1.0 2014年12月6日上午11:24:14
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/fluxEvent")
public class FluxEventController {

	private FluxEventService fluxEventService;

	@Inject
	public FluxEventController(FluxEventService fluxEventService) {
		super();
		this.fluxEventService = fluxEventService;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model){
		try{
			String ip = "172.16.8.21";
			List<Object[]> list= fluxEventService.findInAndOutFlux(ip);
			model.addAttribute("list", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "melon/event/create";
	}
	
	
}
