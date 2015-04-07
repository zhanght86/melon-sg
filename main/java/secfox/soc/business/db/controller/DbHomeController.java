/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @since 1.0 2014-10-22
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Controller
@RequestMapping(value="/db")
public class DbHomeController {
	
	/**
	 *等级保护主页
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String list() {
		return "db.basicInfo.home";
	}
	
}
