/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.secpass.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.asset.secpass.domain.SecPass;
import secfox.soc.melon.asset.secpass.query.SecPassPageQuery;
import secfox.soc.melon.asset.secpass.service.SecPassService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.security.domain.Password;
import secfox.soc.melon.security.service.AccountService;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

/**
 * @since 2014-11-18,上午9:56:19
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/asset/secpass")
public class SecPassController {
	
	private SecPassService secPassService;
	private AccountService accountService;
	
	@Inject
	public SecPassController(SecPassService secPassService, AccountService accountService) {
		this.secPassService = secPassService;
		this.accountService = accountService;
	}
	
	/**
	 * 验证口令正确性
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest req) {
		Password pass = new Password(SecurityContextUtils.getCurrentUserInfo().getUserId());
		model.addAttribute("passwordForm", pass);
		model.addAttribute("inline", req.getParameter("inline"));
		return "asset.secpass.list";
	}
	
	/**
	 * 口令验证
	 * @param pass
	 * @return
	 */
	@RequestMapping(value="/verify", method = RequestMethod.POST)
	public @ResponseBody boolean verify(@ModelAttribute("passwordForm") Password pass) {
		return accountService.checkOriPass(pass.getPassword(), pass.getId());
	}
	
	/**
	 * 数据查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public @ResponseBody Pagination<SecPass> query(@ModelAttribute("pageQuery") SecPassPageQuery pageQuery) {
		pageQuery.getSearchForm().setUserId(SecurityContextUtils.getCurrentUserInfo().getUserId());
		return secPassService.findPagination(pageQuery);
	}
	
	/**
	 * 加载数据
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/load", method = RequestMethod.GET)
	public String load(@ModelAttribute("pageQuery") SecPassPageQuery pageQuery, Model model) {
		String curStr= DateFormatUtils.format(new Date(), BaseConstants.SHORT_DATE_FORMAT);
		model.addAttribute("now", curStr);
		return "melon/asset/secpass/load";
	}
	
	/**
	 * 跳转到新建页面
	 * @param secPass
	 * @return
	 */
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String create(@ModelAttribute SecPass secPass) {
		return "asset.secpass.edit";
	}
	
	/**
	 * 保存新建数据
	 * @param secPass
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String create(@Valid@ModelAttribute SecPass secPass, BindingResult result) {
		ActionHint actionHint = ActionHint.create("asset.secPass.create.hint", secPass.getAssetName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
			return "asset.secpass.edit";
		}
		secPass.setUser(SecurityContextUtils.getCurrentUserInfo());
		secPassService.persist(secPass);
		//添加操作协助提示(本处因为树性操作结构)，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(secPass);
		return "redirect:/asset/secpass/list?inline=1";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id")Long id, Model model) {
		SecPass secPass = secPassService.find(id);
		
		Date now = new Date();
		Date curDate = new Date();
		try {
			SimpleDateFormat df = new SimpleDateFormat(BaseConstants.SHORT_DATE_FORMAT);
			String curStr = df.format(now);
			curDate = df.parse(curStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int tmp = curDate.compareTo(secPass.getDueDate());
		if(tmp >= 0) {
			model.addAttribute("adPass", true);
		}
		model.addAttribute("secPass", secPass);
		return "asset.secpass.edit";
	}
	
	/**
	 * 保存修改
	 * @param secPass
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String update(@Valid@ModelAttribute SecPass secPass, BindingResult result) {
		ActionHint actionHint = ActionHint.create("asset.secPass.update.hint", secPass.getAssetName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
			return "asset.secpass.edit";
		}
		//保存业务对象
		secPassService.merge(secPass);
		//添加操作协助提示(本处因为树性操作结构)，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(secPass);
		return "redirect:/asset/secpass/list?inline=1";
		
	}
	
	/**
	 * 明细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id")Long id, Model model) {
		model.addAttribute("secPass", secPassService.find(id));
		model.addAttribute("histories", secPassService.findHistory(id));
		return "asset.secpass.show";
	}
	
	/**
	 * 逻辑删除
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	public @ResponseBody ActionStatus remove(@PathVariable("id") Long id) {
		secPassService.remove(id);
		return ActionStatus.SUCCESS;
	}

}
