package secfox.soc.melon.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/show")
	public String show(){
		return "melon.system.test.show";
	}
	
	@RequestMapping("/list")
	public String list(){
		return "melon.system.test.list";
	}
}
