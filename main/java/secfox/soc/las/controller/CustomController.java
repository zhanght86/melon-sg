/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.las.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @since 1.0 2014年10月30日,下午5:36:41
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/las/custom")
public class CustomController {
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/show")
	public String show() {
		return "las.custom.show";
	}
	/**
	 * @return
	 */
	@RequestMapping("/view")
	public String view() {
		return "las/custom/view";
	}
	
}
