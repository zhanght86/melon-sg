/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import secfox.soc.melon.base.util.ApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 1.0 2014年9月17日,下午8:02:49
 * 将Java对象转换为JSON字符串
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ToJsonTag extends SimpleTagSupport {
	
	private Object value;
	
	/**
	 * 获取设置的对象值
	 * @return
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 设置Tag的value值
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		ObjectMapper mapper = ApplicationContextUtils.getBean(ObjectMapper.class);
		getJspContext().getOut().write(mapper.writeValueAsString(value));
	}
	
}
