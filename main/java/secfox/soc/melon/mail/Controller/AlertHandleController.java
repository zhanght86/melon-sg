/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.mail.Controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Lists;

import secfox.soc.las.domain.AlertRule;
import secfox.soc.las.service.AlertRuleService;
import secfox.soc.melon.base.UrlCodeUtils;
import secfox.soc.melon.mail.MailManager;
import secfox.soc.melon.system.domain.GlobalConfig;
import secfox.soc.melon.system.service.GlobalConfigService;

/**
 * @since 2015-1-19,下午3:07:30
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
public class AlertHandleController {
	
	private MailManager mailManange;
	private AlertRuleService alertRuleService;
	private GlobalConfigService service ;
	
	@Inject
	public AlertHandleController(@Named("mailManager") MailManager mailManange,AlertRuleService alertRuleService, GlobalConfigService service) {
		this.mailManange = mailManange;
		this.alertRuleService = alertRuleService;
		this.service = service;
	}

	/**
	 * 告警动作
	 * @param alertId
	 * @param ruleId
	 */
	@RequestMapping("/alert/handle")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handle(@RequestParam("alertId") String alertId, @RequestParam("ruleId") String ruleId) {
		AlertRule rule = alertRuleService.findByRuleId(ruleId);
		GlobalConfig config = service.find();
		if(!StringUtils.isBlank(rule.getTypes())){
			/**
			 * 目前只有邮件
			 */
			mailManange.sendMail(rule,config);
		}
	}
	
	@RequestMapping("/alert/handle/json")
	public @ResponseBody List<String> json() {
		List<String> result = Lists.newArrayList();
		result.add("hello");
		result.add("world");
		return result;
	}
	
	@RequestMapping("/alert/handle/test")
	public String test(@RequestParam("sid")String param, Model model) {
		try {
			model.addAttribute("encodeparam", UrlCodeUtils.decode(param));
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "las/test";
	}
	
}
