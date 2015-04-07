package secfox.soc.melon.filter;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.GenericFilterBean;

import secfox.soc.melon.security.FormAuthenticationFilter;
import secfox.soc.melon.system.domain.GlobalConfig;
import secfox.soc.melon.system.service.GlobalConfigService;
import secfox.soc.melon.system.service.impl.GlobalConfigServiceImpl;

public class IpAndTimeFilter extends GenericFilterBean{
	
	private GlobalConfigService service ;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		GlobalConfig config = service.find();
		
		
		String loginIp = req.getRemoteAddr();
		String ip = config.getIpLeftRange()+"-"+config.getIpRightRange();
		
		boolean result = true;
		if(!StringUtils.isBlank(loginIp) && loginIp.indexOf(":")==-1){
			result = !ipIsValid(ip,loginIp);
		}
		
		int start = config.getOpenStart();
		int end = config.getOpenEnd();
		if(start!=0 && end!=0){
			int hour = Calendar.getInstance().HOUR_OF_DAY;
			if(hour>=end || hour<=start){
				result = false;//在锁定范围内
			}
		}
		if(result){
			chain.doFilter(request, response);
		}else{
			res.sendRedirect(req.getContextPath()+"/app/logoPage");
		}
		return ;
	}
	public static boolean ipIsValid(String ipSection, String ip) {   
        ipSection = ipSection.trim();   
        ip = ip.trim();   
        int idx = ipSection.indexOf('-'); 
        if(idx==-1){
        	return ipSection.equals(ip);
        }
        String[] sips = ipSection.substring(0, idx).split("\\.");   
        String[] sipe = ipSection.substring(idx + 1).split("\\.");   
        String[] sipt = ip.split("\\.");   
        long ips = 0L, ipe = 0L, ipt = 0L;   
        for (int i = 0; i < 4; ++i) {   
            ips = ips << 8 | Integer.parseInt(sips[i]);   
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);   
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);   
        }   
        if (ips > ipe) {   
            long t = ips;   
            ips = ipe;   
            ipe = t;   
        }   
        return ips <= ipt && ipt <= ipe;   
    } 
	public GlobalConfigService getService() {
		return service;
	}
	public void setService(GlobalConfigService service) {
		this.service = service;
	}
}
