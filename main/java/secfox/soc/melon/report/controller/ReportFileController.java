/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import secfox.soc.melon.report.domain.ReportFile;
import secfox.soc.melon.report.service.ReportFileService;
import secfox.soc.melon.report.util.ReportFileUtils;
import secfox.soc.melon.web.ActionStatus;

/**
 * @since 2015-3-6,上午10:20:59
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/report/file")
public class ReportFileController {
	
	private ReportFileService service;
	
	private ReportFileUtils reportFileUtils;
	
	private ObjectMapper objectMapper;
	
	@Inject
	public ReportFileController(ReportFileService service, ReportFileUtils reportFileUtils, ObjectMapper objectMapper) {
		this.service = service;
		this.reportFileUtils = reportFileUtils;
		this.objectMapper = objectMapper;
	}
	
	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping(value="/upload", method={RequestMethod.GET, RequestMethod.POST})
	public void upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("files[]") MultipartFile file) {
		String domainId = request.getParameter("domainId");
		String accept = request.getHeader("accept");
		//针对IE HACK
		if(StringUtils.contains(accept, "application/json") || StringUtils.contains(accept, "text/javascript")) {
			response.setContentType("application/json");
		} else {
			response.setContentType("text/plain");
		}
		if(file != null) {
			ReportFile reportFile = reportFileUtils.parseFromFile(domainId, file);
			service.persist(reportFile);
			reportFileUtils.writeToDisk(reportFile, file);
			try {
				PrintWriter writer = response.getWriter();
				writer.write(objectMapper.writeValueAsString(reportFile));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 文件下载功能
	 * @param request
	 * @param response
	 * @param fileId
	 */
	@RequestMapping(value="/download/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public void download(@PathVariable("id")Long fileId, HttpServletRequest request, HttpServletResponse response) {
		String accept = request.getHeader("accept");
		//针对IE HACK
		if(StringUtils.contains(accept, "application/json") || StringUtils.contains(accept, "text/javascript")) {
			response.setContentType("application/json;charset=UTF-8");
		} else {
			response.setContentType("text/plain;charset=UTF-8");
		}
		try {
			ReportFile reportFile = service.find(fileId);
			reportFileUtils.readFromDisk(reportFile, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除附件
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		if(reportFileUtils.removeFromDisk(id)) {
			service.remove(id);
		} else {
			return ActionStatus.FAILURE;
		}
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 获取domainId所对应的附件列表
	 * @param domainId
	 * @return
	 */
	@RequestMapping(value="/list/{businessId}", method=RequestMethod.GET)
	@ResponseBody
	public List<ReportFile> list(@PathVariable("domainId") String businessId) {
		return service.findFile(businessId);
	}
	
}
