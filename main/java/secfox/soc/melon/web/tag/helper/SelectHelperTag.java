/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @since 1.0 2014年9月26日,下午2:00:56
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class SelectHelperTag extends AbstractHelperTag {
	
	private String getContextPath() {
		PageContext context = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        return request.getContextPath() + "/";
	}
	
	/**
	 * 设置一次载入的地址
	 * @param url
	 */
	public void setOnceUrl(String url) {
		addConfig("onceUrl", getContextPath() + url);
	}
	
	/**
	 * 最多能容许选择的数量
	 * @param maxSize
	 */
	public void  setMaxSelectSize(int maxSize) {
		addConfig("maxSize", maxSize);
	}
	
	/**
	 * 是否容许多选
	 * @param multiSelect
	 */
	public void setMultiSelect(boolean multiSelect) {
		addConfig("multiSelect", multiSelect);
	}
	
	/**
	 * 设置初始化地址
	 * @param url
	 */
	public void setInitUrl(String url) {
		addConfig("initUrl", getContextPath() + url);
	}
	
	/**
	 * 
	 * @param queryUrl
	 */
	public void setQueryUrl(String queryUrl) {
		addConfig("queryUrl", getContextPath() + queryUrl);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.web.tablib.helper.AbstractHelperTag#getTmplId()
	 */
	@Override
	public String getTmplId() {
		return "ajaxSelectScripts";
	}

}
