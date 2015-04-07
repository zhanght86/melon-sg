/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.gate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年10月28日,下午2:39:17
 * 门户首页
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/gate/home")
public class GateHomeController {
	
	private ObjectMapper objectMapper;
	
	/**
	 * @param objectMapper
	 */
	@Inject
	public GateHomeController(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	/**
	 * 门户的主页
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return "gate.home.index";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping("/import")
	public void importExl(HttpServletRequest request, HttpServletResponse response, @RequestParam("files[]") MultipartFile file) {
		String accept = request.getHeader("accept");
		//针对IE HACK
		if(StringUtils.contains(accept, "application/json") || StringUtils.contains(accept, "text/javascript")) {
			response.setContentType("application/json");
		} else {
			response.setContentType("text/plain");
		}
		//
		Map<String, Object> result = Maps.newHashMap();
		if(file != null) {
			try {
				HSSFWorkbook book = new HSSFWorkbook(file.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
				result.put("error", true);
			}
		}
		try {
			PrintWriter writer = response.getWriter();
			writer.write(objectMapper.writeValueAsString(result));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
