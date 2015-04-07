package secfox.soc.melon.security;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.service.LoginRecordService;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.system.service.GlobalConfigService;

public class AuthenticationSuccess extends SavedRequestAwareAuthenticationSuccessHandler {
	
	//重置密码页面
	private String resetPassword = "/security/account/activate";
	private LoginRecordService recordService;
	private GlobalConfigService configService;
	
	public LoginRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(LoginRecordService recordService) {
		this.recordService = recordService;
	}

	public GlobalConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(GlobalConfigService configService) {
		this.configService = configService;
	}

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
		//获取用户信息
		Account account = SecurityContextUtils.getCurrentAccount();
		//登录成功清除记录
		recordService.removeRecord(account.getUsername());
		//判断是否该更新密码
		Calendar curCal = Calendar.getInstance();
		Calendar updateTime = DateUtils.toCalendar(account.getUpdatePassTime());
		//有效期30天
		updateTime.add(Calendar.DAY_OF_MONTH, configService.find().getValidity());
		//新建用户或被管理员重置密码
		if(!account.isActive()) {
			String url = resetPassword + BaseConstants.URL_PARA + "type=" + 1;
			//调整到密码激活页面
			getRedirectStrategy().sendRedirect(request, response, url);
			
			return;
		}
		//密码超期
		if(updateTime.compareTo(curCal) <= 0) {
			String url = resetPassword + BaseConstants.URL_PARA + "type=" + 2;
			//调整到密码激活页面
			getRedirectStrategy().sendRedirect(request, response, url);
			
			return;
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
