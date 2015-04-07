/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;

import secfox.soc.melon.base.util.ApplicationContextUtils;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.report.domain.ReportFile;
import secfox.soc.melon.report.service.ReportFileService;
import secfox.soc.melon.system.domain.AjaxFile;
import secfox.soc.melon.system.service.AjaxFileService;

/**
 * @since 1.0 2014年9月29日,上午10:49:27
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AjaxFileUploadTag extends AbstractHtmlTag {
	
	//类型 区别报表文件上传和系统文件上传
	private String type;
	
	//业务id
	private String domainId;
	
	//是否支持多次文件上传
	private boolean multi = true;
	
	public void setDomainId(String domainId) {
		this.domainId = domainId;
		addAtrribute("domainId", domainId);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setMulti(Boolean multi) {
		this.multi = multi;
	}
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.web.tablib.AbstractHtmlTag#getTemplate()
	 */
	@Override
	protected String getTemplate() {
		//根据类型不同选择不同的模板
		if("report".equals(type)) {
			ReportFileService fileService = ApplicationContextUtils.getBean(ReportFileService.class);
			List<ReportFile> reportFiles = fileService.findFile(domainId);
			addAtrribute("reportFiles", reportFiles);
			addAtrribute("multi", multi);
			return "reportFileUploadTag";
		}
		AjaxFileService fileService = ApplicationContextUtils.getBean(AjaxFileService.class);
		List<AjaxFile> ajaxFiles = fileService.findFile(domainId);
		addAtrribute("ajaxFiles", ajaxFiles);
		return "ajaxFileUploadTag";
	}

	@Override
	public void doTag() throws JspException, IOException {
		addAtrribute("buttonText", MessageSourceUtils.getMessage("button.upload"));
		addAtrribute("contextPath", getContextPath());
		super.doTag();
	}
	
	

}
