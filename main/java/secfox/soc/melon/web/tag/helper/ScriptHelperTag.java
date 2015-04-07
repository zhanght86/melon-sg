/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.helper;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @since 1.0 2014年9月28日,下午3:30:50
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ScriptHelperTag extends SimpleTagSupport {
	
	private JspFragment fragment;
	
	public void setFragment(JspFragment fragment) {
		this.fragment = fragment;
	}

	@Override
	public void doTag() throws JspException, IOException {
		StringWriter writer = new StringWriter();
		fragment.invoke(writer);//首先解析
		JspWriter out = getJspContext().getOut();
		writer.append("cnima");
		out.write(writer.toString());
		
	}


}
