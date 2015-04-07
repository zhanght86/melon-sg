/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.report.domain.Report;
import secfox.soc.melon.report.domain.ReportFile;
import secfox.soc.melon.report.query.ReportPageQuery;
import secfox.soc.melon.report.service.ReportFileService;
import secfox.soc.melon.report.service.ReportService;
import secfox.soc.melon.report.util.ReportFileUtils;

/**
 * @since 2015-3-6,上午10:20:59
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/report")
public class ReportController {
	
	private ReportService service;
	
	private ReportFileService fileService;
	
	private ReportFileUtils reportFileUtils;
	
	private UUIDUtils uuidUtils;
	
	@Inject
	public ReportController(ReportService service, ReportFileService fileService, ReportFileUtils reportFileUtils, UUIDUtils uuidUtils) {
		this.service = service;
		this.fileService = fileService;
		this.reportFileUtils = reportFileUtils;
		this.uuidUtils = uuidUtils;
	}

	/**
	 * 报表首页
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest req, Model model) {
		int sum = 0;
		List<Object[]> count = service.countByType();
		String url = req.getRequestURL().toString();
		String context = req.getContextPath();
		int index = url.indexOf(context);
		model.addAttribute("birt", url.substring(0, index)+"/melon-birt/report/frameset?__report=");
		model.addAttribute("sum", sum);
		model.addAttribute("counts", count);
		model.addAttribute("sys", service.findByType(1));
		model.addAttribute("log", service.findByType(2));
		model.addAttribute("syn", service.findByType(3));
		return "report.home";
	}
	
	/**
	 * 列表页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") ReportPageQuery pageQuery, @PathVariable("type")int type, Model model) {
		model.addAttribute("type", type);
		return "report.list";
	}
	
	/**
	 * 查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list/{type}", method = RequestMethod.POST)
	public @ResponseBody Pagination<Report> query(@PathVariable("type")int type, @ModelAttribute("pageQuery") ReportPageQuery pageQuery) {
		pageQuery.getSearchForm().setType(type);
		Pagination<Report> result = service.findPages(pageQuery);
		return result; 
	}
	
	/** * 跳转报表注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/create")
	public String create(Model model) {
		Report report = new Report();
		String businessId = uuidUtils.generate(report);
		report.setBusinessId(businessId);
		model.addAttribute("report", report);
		return "report.edit";
	}
	
	/**
	 * 报表注册
	 * @param report
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute("report") Report report, BindingResult result) {
		if(result.hasErrors()){
			return "report.edit";
		}
		report.setCreateDate(new Date());
		service.persist(report);
		return "redirect:/report/home ";
		
	}
	
	/**
	 * 跳转到更新页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id")Long id, Model model) {
		Report report = service.find(id);
		model.addAttribute("report", report);
		return "report.edit";
	}
	
	/**
	 * 保存更新
	 * @param report
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@Valid@ModelAttribute("report") Report report, BindingResult result) {
		if(result.hasErrors()){
			return "report.edit";
		}
		service.merge(report);
		return "redirect:/report/home ";
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	public @ResponseBody boolean remove(@PathVariable("id") Long id) {
		Report report = service.getReference(id);
		ReportFile file = fileService.findByBusinessId(report.getBusinessId());
		//删除报表信息
		service.remove(id);
		//删除报表文件
		reportFileUtils.removeFromDisk(file.getId());
		//删除报表文件信息
		fileService.remove(file);
		return true;
	}
	
	/**
	 * 报表预览
	 * @param req
	 * @param id
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest req, @RequestParam("id") Long id) {
		String url = req.getRequestURL().toString();
		String context = req.getContextPath();
		int index = url.indexOf(context);
		String href = url.substring(0, index)+"/melon-birt/report/frameset?__report="+service.getFileName(id);
		return "redirect:"+href;
	}
	
}
