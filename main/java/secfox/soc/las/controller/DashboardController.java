/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.las.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @since 1.0 2014年10月30日,上午9:39:18
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/las/dashboard")
public class DashboardController {

	/**
	 * 默认的DashBoard
	 * @return
	 */
	@RequestMapping("/default")
	public String defaultDashBoard() {
		return "las/dashboard/default";
	}
}
