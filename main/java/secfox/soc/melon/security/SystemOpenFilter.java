package secfox.soc.melon.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import secfox.soc.melon.system.service.GlobalConfigService;

public class SystemOpenFilter extends GenericFilterBean {
	
	private GlobalConfigService configService;
	
	@Inject
	public SystemOpenFilter(GlobalConfigService configService) {
		super();
		this.configService = configService;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		configService.find();
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
		chain.doFilter(request, response);
	}

}
