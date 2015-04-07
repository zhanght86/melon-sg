/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.WebConstants;

/**
 * @since 1.0 2014年9月20日,上午11:47:13
 * 桌面视图预处理器,添加操作提示\菜单\登陆用户等信息
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component("desktopViewPreparer")
public class DesktopViewPreparer implements ViewPreparer {

	/* (non-Javadoc)
	 * @see org.apache.tiles.preparer.ViewPreparer#execute(org.apache.tiles.request.Request, org.apache.tiles.AttributeContext)
	 */
	@Override
	public void execute(Request request, AttributeContext attributeContext) {
		RequestAttributes attrs = RequestContextHolder.currentRequestAttributes();
        if(attrs instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletAttrs = (ServletRequestAttributes)attrs;
            HttpServletRequest httpRequest = servletAttrs.getRequest();
            //获取提示信息
            Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(httpRequest);
            if(flashMap != null) {
            	ActionHint hint = (ActionHint)flashMap.get(WebConstants.ACTION_HINT);
            	if(hint != null) {
            		httpRequest.setAttribute(WebConstants.ACTION_HINT, hint);
            	}
            }
        }
	}

}
