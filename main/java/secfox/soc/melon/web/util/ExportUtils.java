/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @since 1.0 2014年10月11日,下午2:20:22
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ExportUtils {
	
	public static final String XLS_SUFFIX = ".xls";

	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	
	public static final String ATTACH_FILE_NAME = "attachment;filename=";

	/**
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	public  final static void setAttachName(HttpServletRequest request, HttpServletResponse response, String name) {
		try {
			String userAgent = StringUtils.upperCase(request.getHeader("User-Agent"));
			
			if (StringUtils.contains(userAgent, "MSIE")	|| StringUtils.contains(userAgent, "TRIDENT")) {
				response.setHeader(CONTENT_DISPOSITION,
						ATTACH_FILE_NAME + java.net.URLEncoder.encode(name, "UTF-8"));
			} else {
				response.setHeader(CONTENT_DISPOSITION, 
						ATTACH_FILE_NAME + new String(name.getBytes("UTF-8"), "ISO8859-1"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	public final static void setExcelAttachName(HttpServletRequest request, HttpServletResponse response, String name) {
		if(!StringUtils.endsWith(name, XLS_SUFFIX)) {
			name = name + XLS_SUFFIX;
		}
		setAttachName(request, response, name);
	}
	
}
