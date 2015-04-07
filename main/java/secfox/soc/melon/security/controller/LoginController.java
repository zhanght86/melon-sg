/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import secfox.soc.melon.integration.MessageChannelUtils;
import secfox.soc.melon.security.domain.LoginUser;
import secfox.soc.melon.security.service.AccountService;
import secfox.soc.melon.security.service.LoginRecordService;
import secfox.soc.melon.system.domain.Logger;
import static secfox.soc.melon.security.SecurityConstants.LOGIN_ERROR;

/**
 * @since 1.0 2013-11-5,下午1:56:46
 * 登录页面
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
	private LoginRecordService loginSerivce;
	private AccountService accountService;
	
	@Inject
	public LoginController(LoginRecordService loginSerivce, AccountService accountService) {
		this.loginSerivce = loginSerivce;
		this.accountService = accountService;
	}
    
    /**
     * POST方法必须提供（为Spring Security使用），否则错误
     * @param request
     * @param user
     * @param model
     * @return
     */
	@RequestMapping(value = {"/login"}, method={RequestMethod.GET,RequestMethod.POST})
    public String login(HttpServletRequest request, @ModelAttribute("loginUser") LoginUser user, Model model) {
        AuthenticationException exception = (AuthenticationException) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if(exception instanceof BadCredentialsException) {
        	//获取用户名
        	String username = (String) exception.getAuthentication().getPrincipal();
        	//记录用户登录失败
        	loginSerivce.insertRecord(username);
        	//创建登录失败日志
        	Logger logger = new Logger();
			logger.setClazz("用户登录");
			logger.setIp(request.getRemoteAddr());//设置IP地址
			logger.setOccurTime(new Date());
			logger.setOperator(accountService.findByUsername(username).getUserInfo());
			logger.setResult("用户登录失败");
			logger.setFunction("登录失败");
			MessageChannelUtils.send("loggerBufferChannel", logger);
            model.addAttribute(LOGIN_ERROR, "密码错误");
        }
        if(exception instanceof LockedException) {
            model.addAttribute(LOGIN_ERROR, "用户已锁定");
        }
        if(exception instanceof AuthenticationServiceException) {
            model.addAttribute(LOGIN_ERROR, "用户不存在");
        }
        if(exception instanceof AuthenticationCredentialsNotFoundException) {
            model.addAttribute(LOGIN_ERROR, "会话到期");
        }
        if(exception instanceof SessionAuthenticationException) {
            model.addAttribute(LOGIN_ERROR, "用户重复登录");
        }
        //根据主题挑选响应的登录页面
        return "security.login." + RequestContextUtils.getTheme(request).getName();
    }
	
	/**
	 * 系统无法使用提示页面
	 * @param request
	 * @param user
	 * @param model
	 * @return
	 */
    @RequestMapping(value = {"/logoPage"}, method={RequestMethod.GET,RequestMethod.POST})
    public String logoPage(HttpServletRequest request, @ModelAttribute("loginUser") LoginUser user, Model model) {
    	return "security.login.logoPage";
    }
}
