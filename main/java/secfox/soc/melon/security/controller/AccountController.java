/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.domain.Password;
import secfox.soc.melon.security.query.AccountPageQuery;
import secfox.soc.melon.security.service.AccountService;
import secfox.soc.melon.security.service.RoleService;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.system.service.GlobalConfigService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;
import secfox.soc.melon.web.WebConstants;

import com.google.common.collect.Lists;

/**
 * @since 1.0 2014年9月22日,下午1:53:44
 * 帐户管理控制器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/security/account")
public class AccountController {

	private AccountService accountService;
	
	private RoleService roleService;
	
	private GlobalConfigService configService;
	
	 /**
     * 初始化所有角色
     * @param model
     */
    @ModelAttribute
    public void init(Model model) {
        model.addAttribute("roles", roleService.findAll());
    }
	
	/**
	 * 注入service
	 * @param accountService
	 * @param roleService
	 */
	@Inject
	public AccountController(AccountService accountService,RoleService roleService, GlobalConfigService configService){
		super();
		this.accountService = accountService;
		this.roleService = roleService;
		this.configService = configService;
	}
	
	/**
	 * 跳转到账号添加页面
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@ModelAttribute Account account,Model model) {
		account.setCreateTime(new Date());
		account.setUpdatePassTime(new Date());
		String newDate = DateFormat.getDateInstance().format(new Date()); //格式化时间为yyyy-MM-dd
		model.addAttribute("newDate", newDate); //为了限制选择日期范围
		return "security.account.create";
	}
	
	/**
	 * 账号信息添加
	 * @param account
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute Account account, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "security.account.create";
        }
		accountService.persist(account);
        attribute.addFlashAttribute(account);
        //添加提示信息
        ActionHint hint = ActionHint.create("security.role.create.hint", account.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/security/account/list";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("account", accountService.find(id) );
		return "security.account.update";
	}
	
	/**
	 * 账号信息修改
	 * @param account
	 * @param result
	 * @param model
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@Valid@ModelAttribute Account account, BindingResult result, RedirectAttributes attribute) {
		ActionHint actionHint = ActionHint.create("security.account.update.hint");
		if(result.hasErrors()) {
			actionHint.setKey("operation.input.error");
			actionHint.setLevel(HintLevel.ERROR);
            return "security.account.update";
        }
		accountService.merge(account);
        //添加提示信息
		actionHint.setParams(account.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, actionHint);
        //导向到明细页面
        return "redirect:/security/account/list";
	}
	
	/**
	 * 跳转到修改密码页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resetPass", method=RequestMethod.GET)
	public String reset(@RequestParam("id") Long id, Model model) {
		model.addAttribute("passwordForm", new Password(id));
		model.addAttribute("pattern", configService.find().getPassStr());
		return "security.account.resetPass";
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/resetPass", method=RequestMethod.POST)
	public String reset(@ModelAttribute Password pass, HttpServletRequest request, BindingResult result, RedirectAttributes attribute) {
		Long id = Long.valueOf(request.getParameter("id"));
		boolean active = false;
		if(result.hasErrors()) {
            return "security.account.resetPass";
        }
		if(SecurityContextUtils.getCurrentAccount().getId().compareTo(id) != 0) {
			active = true;
		}
		accountService.resetPassword(pass, active);
        //添加提示信息
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, new ActionHint("security.account.resetPassword.hint"));
        //导向到明细页面
        return "redirect:/security/account/show/"+id;
	}
	
	/**
	 * 重置验证密码
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resetSecPass", method=RequestMethod.GET)
	public String reseSecPass(Model model) {
		Long id = SecurityContextUtils.getCurrentUserInfo().getUserId();
		model.addAttribute("passwordForm", new Password(id));
		return "security.account.resetSecPass";
	}
	
	/**
	 * 重置验证密码
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resetSecPass", method=RequestMethod.POST)
	public String reseSecPass(@Valid@ModelAttribute("passwordForm") Password pass, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "security.account.resetPass";
        }
		accountService.resetSecPassword(pass);
		return "redirect:/asset/secpass/list?inline=1";
	}
	
	/**
	 * 跳转到激活密码页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/activate", method=RequestMethod.GET)
	public String activate(@RequestParam("type") int type, Model model) {
		//获取当前登录人id
		Long id = SecurityContextUtils.getCurrentAccount().getId();
		if(type == 1) {
			model.addAttribute("title", MessageSourceUtils.getMessage("security.activate.title1"));
		} else {
			model.addAttribute("title", MessageSourceUtils.getMessage("security.activate.title2"));
		}
		model.addAttribute("passwordForm", new Password(id));
		model.addAttribute("pattern", configService.find().getPassStr());
		return "melon/security/account/activate";
	}
	
	/**
	 * 激活密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/activate", method=RequestMethod.POST)
	public String activate(@ModelAttribute Password pass, HttpServletRequest request, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "melon/security/account/activate";
        }
		accountService.activateAccount(pass);
        return "redirect:/login";
	}
	
	/**
	 * 获取账号组织机构树
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/accTree", method=RequestMethod.GET)
	public @ResponseBody List<Organization> accTree(HttpServletRequest req) {
		String rootId = req.getParameter("id");
		return accountService.orgAccTree(Long.valueOf(rootId));
		
	}
	
	/***
	 * 导出所有账户或某单位下账户列表
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/export",method=RequestMethod.GET, produces={"application/vnd.ms-excel"})
	public String export(HttpServletRequest req,Model model) {
		List<Account> accountlist = Lists.newArrayList();
		if(StringUtils.isBlank(req.getParameter("organId"))){
			//设置标题
			model.addAttribute("title",MessageSourceUtils.getMessage("security.account.exportAllUser"));
			accountlist = accountService.findUsers(-1l);
		}else{
			accountlist = accountService.findAccountByOrgan(Long.valueOf(req.getParameter("organId")));
			String params[] = {accountlist.get(0).getCompanyName()};
			//设置标题
			model.addAttribute("title",MessageSourceUtils.getMessage("security.account.export", params, ""));
		} 
		//取到要导出的数据集
		model.addAttribute("results", accountlist);
		return "accountXls";
	}
	
	/**
	 * 跳转到list页面
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") AccountPageQuery pageQuery) {
		return "security.account.list";
	}

	/**
	 * ajax列表查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Account> query(@ModelAttribute AccountPageQuery pageQuery) {
		return accountService.findPage(pageQuery);
	}
	
	/**
	 * 子表查询测试
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/sublist", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Object[]> subQuery(@ModelAttribute AccountPageQuery pageQuery) {
		Pagination<Object[]> subPagination = new Pagination<Object[]>();
		subPagination.setResults(accountService.getSubAcc());
		return subPagination;
	}
	
	/**
	 * 获取各个单位的人员列表
	 * @return
	 */
	@RequestMapping(value="/listOrgans", method=RequestMethod.GET)
	public String listOrgan(Model model) {
		List<Object[]> organs = accountService.listOrganWithCount();
		model.addAttribute("organs", organs);
		return "security.account.listOrgans";
	}
	
	/**
	 * 按照指定单位获取人员列表
	 * @return
	 */
	@RequestMapping(value="/listUsers/{organId}", method=RequestMethod.GET)
	public String listAccountByOrgan(@PathVariable("organId") Long organId, Model model) {
		List<Account> accounts = accountService.findAccountByOrgan(organId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("users", accounts.size());
		model.addAttribute("organName",accounts.get(0).getCompanyName());
		model.addAttribute("organId", accounts.get(0).getCompanyId());
		return "security.account.listAccountByOrgan";
	}
	
	/**
	 * 提供查询所有用户和指定单位用户接口
	 * @param organId
	 * @return
	 */
	@RequestMapping(value="/findUsers",method=RequestMethod.GET)
	@ResponseBody
	public List<Account> findUsers(HttpServletRequest req){
		String organId = req.getParameter("organId");
		List<Account> accounts = Lists.newArrayList();
		if(StringUtils.isBlank(organId)){
			accounts = accountService.findUsers(-1l);	
		}else{
			accounts = accountService.findUsers(Long.valueOf(organId));
		
		}
		return accounts;
	}
	
	@RequestMapping(value="/findUsers",method=RequestMethod.POST)
	@ResponseBody
	public List<Account> findUsersS(HttpServletRequest req){
		String organId = req.getParameter("organId");
		List<Account> accounts = Lists.newArrayList();
		if(StringUtils.isBlank(organId)){
			accounts = accountService.findUsers(-1l);	
		}else{
			accounts = accountService.findUsers(Long.valueOf(organId));
		
		}
		return accounts;
	}
	
	@RequestMapping(value="/listRoleUsers",method=RequestMethod.GET)
	@ResponseBody
	public List<Object[]> listRoleByCount(){
		return accountService.listRoleWithCount();
	}
	
    /**
	 * 给系统页面提供在线用户
	 * @return
	 */
	@RequestMapping(value="/listOnlineUsers",method=RequestMethod.GET)
	@ResponseBody
	public List<Object[]> listOnlineUsers(){
		//获取所有的在线用户
		List<Account> users  = accountService.findOnlineAccounts();
		//返回结果
		List<Object[]> results = Lists.newArrayList();
		
		for(Account account : users) {
			String organName = account.getCompanyName();
			Object[] contained = null;//临时list
			//首先判断是否包含了此元素
			for(Object[] result : results) {
				if(organName.equals(result[0])){
					contained = result;
				}
			}
			//
			if(contained == null||contained.length==0) {
				contained = new Object[2];
				contained[0]=organName;
				contained[1]=1;
				results.add(contained);
			} else {
				contained[1]=Integer.parseInt(String.valueOf(contained[1]))+1;
			}
		}
		return results;
	}
	
	/**
	 * 显示在线用户
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/online", method=RequestMethod.GET)
	public String online(Model model) {
		List<Account> accounts = accountService.findOnlineAccounts();
		model.addAttribute("onlines", accounts);
		model.addAttribute("users", accounts.size());
		return "security.account.online";
	}
	

	/**
     * 判断帐号的唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/checkUnique", method=RequestMethod.POST)
    public @ResponseBody boolean checkUnique(@RequestParam("username") String username) {
        Account acct = accountService.findByUsername(username);       
        return acct == null;
    }
	
    /**
	 * 删除账户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		accountService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 账号明细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Account account = (Account)model.asMap().get("account");
        if (account == null) {
        	account = accountService.find(id);
        }
		model.addAttribute("account", account);
        return "security.account.show";
	}
	
	/**
	 * 验证密码有效性
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkPass", method=RequestMethod.POST)
    public @ResponseBody boolean checkUnique(HttpServletRequest request) {
        String accountId = request.getParameter("accountId");
        String password = request.getParameter("password");
        return accountService.checkPass(accountId, password, 3);
	 }
	
}
