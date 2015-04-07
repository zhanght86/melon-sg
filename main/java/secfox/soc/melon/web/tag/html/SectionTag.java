/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

/**
 * @since 1.0 2014年9月28日,下午8:46:25
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class SectionTag extends AbstractHtmlTag {
	
	private JspFragment title;
	
	private JspFragment body;
	
	private JspFragment remarks;
	
	/**
	 * 
	 * @param title the title to set
	 */
	public void setTitle(JspFragment title) {
		this.title = title;
	}

	/**
	 * 
	 * @param body the body to set
	 */
	public void setBody(JspFragment body) {
		this.body = body;
	}

	/**
	 * 
	 * @param remarks the remarks to set
	 */
	public void setRemarks(JspFragment remarks) {
		this.remarks = remarks;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		addAtrribute("id", id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.web.tag.html.AbstractHtmlTag#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		StringWriter writer = new StringWriter();
		//首先解析头部
		title.invoke(writer);
		addAtrribute("title", writer.toString());
		//
		addAtrribute("remarks", null);
		//解析描述
		if(remarks != null){
			writer = new StringWriter();
			remarks.invoke(writer);
			addAtrribute("remarks", writer.toString());
		}
		
		//然后解析内容区
		writer = new StringWriter(); 
		
		body.invoke(writer);
		addAtrribute("body", writer.toString());
		//生成内容
		super.doTag();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.web.tablib.AbstractHtmlTag#getTemplate()
	 */
	@Override
	protected String getTemplate() {
		return "sectionTag";
	}
	
}