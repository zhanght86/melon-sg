/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.knowledge.service.KnowledgeService;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.SqlQueryTemplate;
import secfox.soc.melon.persistence.templates.QueryTemplateManager;

/**
 * @since 1.0 2014年10月29日,上午11:41:39
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeHomeController {
	
	private KnowledgeService service;
	
	
	@Inject
	public KnowledgeHomeController(KnowledgeService service) {
		super();
		this.service = service;
	}



	/**
	 * 显示知识库主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		/*
		 * 知识库类型统计
		 */
		/*String sql = "select KN_TYPE,count(t.pk) from t_know_ledge t group by t.KN_TYPE";
		QueryTemplate qt = new SqlQueryTemplate(sql);
		List<Object[]> list = service.find(qt);*/
		/*for(Object[] temp:list){
			if(temp[0].toString().trim().equals("11")){
				model.addAttribute("s1", temp[1].toString());
			}else if(temp[0].toString().trim().equals("12")){
				model.addAttribute("s2", temp[1].toString());
			}else{
				model.addAttribute("s3", temp[1].toString());
			}
		}*/
		/**
		 * 知识库地点击率最高的5条
		 */
		QueryTemplate qt1 = QueryTemplateManager.find("knowledge.count");
		List<Object[]> knowgList = service.find(qt1);
		model.addAttribute("list", knowgList);
		/**
		 * 知识库最近更新的五条
		 */
		QueryTemplate qt2 = QueryTemplateManager.find("knowledge.update");
		List<Object[]> knowgList1 = service.find(qt2);
		model.addAttribute("listUpdate", knowgList1);
		return "knowledge.home";
	}
	
	/**
	 * 显示不同类型的知识库的数量
	 */
	@RequestMapping(value="/type/count",method=RequestMethod.POST)
	public String findTypeCount(Model model){
		String sql = "select KN_TYPE,count(t.pk) from t_know_ledge t group by t.KN_TYPE";
		QueryTemplate qt = new SqlQueryTemplate(sql);
		List<Object[]> list = service.find(qt);
		String str = null;
		for(Object[] temp:list){
			if(temp[0].equals("11")){
				System.out.println("事件");
				str = "事件";
			}else if(temp[0].equals("12")){
				str = "漏洞";
				System.out.println("漏洞");
			}else{
				str = "攻击";
				System.out.println("攻击");
			}
			model.addAttribute(str, temp[1]);
		}
		return "knowledge.home";
	}
}
