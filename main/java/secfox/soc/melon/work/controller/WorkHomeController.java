/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @since 1.0 2014年11月10日,下午2:20:35
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/work/home")
public class WorkHomeController {

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		return "work.home.main";
	}
	
}
