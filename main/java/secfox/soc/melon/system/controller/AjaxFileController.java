/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.controller;

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

import secfox.soc.melon.system.domain.AjaxFile;
import secfox.soc.melon.system.service.AjaxFileService;
import secfox.soc.melon.system.util.AjaxFileUtils;
import secfox.soc.melon.web.ActionStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 1.0 2014年9月9日,下午3:10:41
 * 文件上传及下载管理器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/system/ajaxfile")
public class AjaxFileController {
	
	private AjaxFileService ajaxfileService;
	
	private AjaxFileUtils ajaxFileUtils;
	
	private ObjectMapper objectMapper;
	
	/**
	 * @param ajaxfileService
	 */
	@Inject
	public AjaxFileController(AjaxFileService ajaxfileService, AjaxFileUtils utils, ObjectMapper objectMapper) {
		super();
		this.ajaxfileService = ajaxfileService;
		this.ajaxFileUtils = utils;
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
			AjaxFile ajaxFile = ajaxFileUtils.parseFromFile(domainId, file);
			ajaxfileService.persist(ajaxFile);
			ajaxFileUtils.writeToDisk(ajaxFile,file);
			try {
				PrintWriter writer = response.getWriter();
				writer.write(objectMapper.writeValueAsString(ajaxFile));
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
			AjaxFile ajaxFile = ajaxfileService.find(fileId);
			ajaxFileUtils.readFromDisk(ajaxFile, request, response);
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
		if(ajaxFileUtils.removeFromDisk(id)) {
			ajaxfileService.remove(id);
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
	public List<AjaxFile> list(@PathVariable("domainId") String businessId) {
		return ajaxfileService.findFile(businessId);
	}
	
}
