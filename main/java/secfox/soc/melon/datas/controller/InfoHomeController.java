package secfox.soc.melon.datas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 信息管理主页
 * @author 11
 *
 */
@Controller
@RequestMapping("/info/home")
public class InfoHomeController {

	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String home(){
		return "gate.article.home";
	}
}
