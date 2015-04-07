/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import secfox.soc.melon.base.template.StrTemplateManager;
import secfox.soc.melon.web.util.RequestUtils;

import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年9月29日,上午9:25:49
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public abstract class AbstractHtmlTag extends SimpleTagSupport {
	
	private Map<String, Object> attributes = Maps.newHashMap();
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addAtrribute(String key, Object value) {
		attributes.put(key, value);
	}
	
	protected abstract String getTemplate();
	
	/**
	 * 解析内容
	 */
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter writer = getJspContext().getOut();
		String result = StrTemplateManager.find(getTemplate()).format(attributes);
		writer.write(result);
	}
	
	/**
	 * 
	 * @return
	 */
	protected  String getContextPath() {
        return RequestUtils.getContextPath(getJspContext());
	}
}
