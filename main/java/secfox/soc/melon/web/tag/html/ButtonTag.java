/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @since 1.0 2014年9月15日,下午1:46:54
 * 兼容Bootstrap的Button,支持链接与按钮
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ButtonTag extends org.springframework.web.servlet.tags.form.ButtonTag{

	private static final long serialVersionUID = -7676319669883551127L;
	
	private String href;
	
	private String type = "button";
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		boolean hasHref = StringUtils.isNotBlank(href);
		String tagname = hasHref ? "a" : "button";
		tagWriter.startTag(tagname);
		writeDefaultAttributes(tagWriter);
		tagWriter.writeAttribute("type", getType());
		//添加href属性
		if(hasHref) {
			ServletRequest request = pageContext.getRequest();
			//默认添加当前程序的上下文
			if(request instanceof HttpServletRequest) {
				tagWriter.writeAttribute("href", ((HttpServletRequest)request).getContextPath() + "/" + href);
			}
		}
		//消除多余的属性
		//writeValue(tagWriter);
		if (isDisabled()) {
			tagWriter.writeAttribute(DISABLED_ATTRIBUTE, "disabled");
		}
		tagWriter.forceBlock();
		setTagWriter(tagWriter);
		return EVAL_BODY_INCLUDE;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	protected String getType() {
		return this.type;
	}

	@Override
	protected String getDefaultValue() {
		return null;
	}
	
}
