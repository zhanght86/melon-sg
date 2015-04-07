/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.elasticsearch.controller;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @since 1.0 2014年11月14日
 * @author <a href="mailto:zhouqinga@legendsec.com">周青</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

	/**
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "elasticsearch.home";
	}
	/**
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/evilProgram", method = RequestMethod.GET)
	public String list() {
		return "elasticsearch.evilProgram";
	}
	
	/**
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/jiance", method = RequestMethod.GET)
	public String jiance() {
		return "elasticsearch.jiance";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/virusBreakOut", method = RequestMethod.GET)
	public String virusBreakOut() {
		return "elasticsearch.virusBreakOut";
	}
	/**
	 * 端口扫描
	 */
	@RequestMapping(value = "/portScan", method = RequestMethod.GET)
	public String portScan() {
		return "elasticsearch.portScan";
	}
	
	/**
	 * 访问目的
	 * @return
	 */
	@RequestMapping(value = "/visitPurpose", method = RequestMethod.GET)
	public String visitPurpose(){
		return "elasticsearch.visitPurose";
	}
	/**
	 * 口令探测
	 * @return
	 */
	@RequestMapping(value = "/pwdetectScan", method = RequestMethod.GET)
	public String pwdetectScan(){
		return "elasticsearch.pwdetectScan";
	}
	/**
	 * 口令探测
	 * @return
	 */
	@RequestMapping(value = "/pwdetectScanB", method = RequestMethod.GET)
	public String pwdetectScanB(){
		return "elasticsearch.pwdetectScanB";
	}
	/**
	 * 根据告警id查询
	 * @return
	 */
	@RequestMapping(value = "/queryAlertById", method = RequestMethod.GET)
	public String queryAlertById(@RequestParam String ids,Model model){
		model.addAttribute("ids", ids);
		return "melon/elasticsearch/alertList";
	}
	
	@RequestMapping(value = "/accessRelation" ,method = RequestMethod.GET)
	public String accessRelation(Model model){
		String newDate = DateFormat.getDateInstance().format(new Date());
		model.addAttribute("newDate", newDate);
		return "elasticsearch.accessRelation";
	}
}
