/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.datas.domain.DataShareLogger;
import secfox.soc.melon.datas.query.DataShareLoggerPageQuery;
import secfox.soc.melon.datas.query.DataShareLoggerSearch;
import secfox.soc.melon.datas.service.DataShareLoggerService;

/**
 *
 * @since 1.0 2014-11-10
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/datas/share")
public class DataShareLoggerController {
	
	//注入service
	private DataShareLoggerService dataShareLoggerService;
	
	@Inject
	public DataShareLoggerController(DataShareLoggerService dataShareLoggerService){
		this.dataShareLoggerService = dataShareLoggerService;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		List<Object[]> data = dataShareLoggerService.shareDatas();
		model.addAttribute("shareDatas", data);
		return "datas.share.list";
	} 
	
	/**
	 * 跳转到list页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/showList/{organId}/{type}", method = RequestMethod.GET)
	public String list(@PathVariable Long organId,@PathVariable Integer type,@Valid@ModelAttribute("pageQuery") DataShareLoggerPageQuery pageQuery) {
		DataShareLoggerSearch dsls = new DataShareLoggerSearch();
		dsls.setOrganId(organId);
		dsls.setType(type);
		pageQuery.setSearchForm(dsls);
		return "datas.share.showList";
	}
	
	/**
	 * 进行ajax查询获取告警列表信息
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/showList/{organId}/{type}", method = RequestMethod.POST)
	public @ResponseBody Pagination<DataShareLogger> query(@ModelAttribute("pageQuery") DataShareLoggerPageQuery pageQuery) {
		return dataShareLoggerService.findPage(pageQuery);
	}
	
}
