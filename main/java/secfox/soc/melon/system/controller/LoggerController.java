/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.system.domain.Logger;
import secfox.soc.melon.system.query.LoggerPageQuery;
import secfox.soc.melon.system.service.LoggerService;

/**
 *
 * @since 1.0 2014-10-8 下午8:45:41
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Controller
@RequestMapping(value="system/logger")
public class LoggerController {
	
	private LoggerService loggerService;
	
	@Inject
	public LoggerController(LoggerService loggerService){
		this.loggerService = loggerService;
	}
	
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") LoggerPageQuery pageQuery){
		return "system.logger.list";
	}
	
	/**
	 * ajax列表查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Logger> query(@ModelAttribute LoggerPageQuery pageQuery) {
		return loggerService.findPage(pageQuery);
	}
	
	/**
	 * 跳转到明细页面
	 */
	@RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model){
		Logger logger = (Logger)model.asMap().get("logger");
		if(logger == null){
			logger = loggerService.find(id);
		}
		model.addAttribute("logger",logger);
		return "system.logger.show";
	}
	
	/**
	 * 跳转到统计列表页面
	 */
	@RequestMapping(value="/listUser",method=RequestMethod.GET)
	public String listUser(@ModelAttribute("pageQuery") LoggerPageQuery pageQuery){
		return "system.logger.listUser";
	}
	
	/**
	 * ajax列表按用户名分组查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/listUser", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Object[]> queryByUser(@ModelAttribute LoggerPageQuery pageQuery) {
		return loggerService.findPageByUserName(pageQuery);
	}
	
	/**
	 * 跳转到统计明细页面
	 */
	@RequestMapping(value="/showUser/{userId}",method=RequestMethod.GET)
	public String showUser(@PathVariable("userId") Long id, Model model){
		List<Logger> loggers = loggerService.findAllFunctionByUserId(id);
		model.addAttribute("username",loggers.get(0).getOperator().getUsername());
		model.addAttribute("userId",loggers.get(0).getOperator().getUserId());
		model.addAttribute("loggers",loggers);
		return "system.logger.showUser";
	}
	
	/**
	 * 跳转到统计明细页面
	 */
	@RequestMapping(value="/trackUser/{userId}",method=RequestMethod.GET)
	public String trackUser(@PathVariable("userId") Long id, Model model){
		List<Logger> loggers = loggerService.findAllFunctionByUserId(id);
		model.addAttribute("loggers",loggers);
		return "melon/system/logger/trackUser";
	}
	
	/**
	 * 系统活跃用户
	 */
	@RequestMapping(value="/activeUser",method=RequestMethod.GET)
	public String activeUserPage(@ModelAttribute("pageQuery") LoggerPageQuery pageQuery){
		return "system.logger.activeUser";
	}
	
	@RequestMapping(value="/activeUser",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Object[]> activeUser(@ModelAttribute LoggerPageQuery pageQuery){
		return loggerService.findActiveUser(pageQuery);
	}
	
	/**
	 * 导出某个用户今日日志记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/export",method=RequestMethod.GET, produces={"application/vnd.ms-excel"})
	public String export(@RequestParam ("userId") Long userId,Model model) {
		List<Logger> loggerlist = loggerService.findAllFunctionByUserId(userId);
		//String params[] = {loggerlist.get(0).getOperator().getUsername()};
		//设置标题
		model.addAttribute("title",MessageSourceUtils.getMessage("system.logger.export"));
		//取到要导出的数据集
		model.addAttribute("results", loggerlist);
		return "loggerXls";
	}
	
	
}
