/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.las.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @since 1.0 2014年12月5日,下午2:12:41
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/las/kibana")
public class KibanaController {
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value= {"/config", "/default"})
	public String config() {
		return "las/kibana/default";
	}

}
