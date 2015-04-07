/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.knowledge.domain.Knowledge;
import secfox.soc.melon.knowledge.query.KnowledgePageQuery;
import secfox.soc.melon.knowledge.query.TermPageQuery;
import secfox.soc.melon.knowledge.service.KnowledgeService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;

/**知识库
 * @since @2014-10-28,@下午3:17:24
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/knowledge/base")
public class KnowledgeController {

	private KnowledgeService service;

	@Inject
	public KnowledgeController(KnowledgeService service) {
		this.service = service;
	}
	
	/**
	 * 跳转到知识库添加页面
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@ModelAttribute Knowledge knowledge) {
		return "knowledge.base.create";
	}
	
	/**
	 * 知识库添加
	 * @param role
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute("knowledge") Knowledge knowledge, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "knowledge.base.edit";
        }
		service.persist(knowledge);
        attribute.addFlashAttribute(knowledge);
        //添加提示信息
        ActionHint hint = ActionHint.create("security.knowledge.create.hint", knowledge.getTitle());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/knowledge/base/list";
	}
	
	/**
	 * 跳转到list页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") KnowledgePageQuery pageQuery) {
		return "knowledge.base.list";
	}
	
	/**
	 * 进行ajax查询获取知识库列表信息
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Pagination<Knowledge> query(@ModelAttribute("pageQuery") KnowledgePageQuery pageQuery) {
		Pagination<Knowledge> page = service.findPage(pageQuery);
		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
	public String query(@ModelAttribute("termQuery") TermPageQuery termQuery, Model model) {
		Pagination<Knowledge> pages = service.queryPage(termQuery);
		model.addAttribute("pages", pages);
		return "knowledge.query";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("knowledge", service.find(id));
		return "knowledge.base.create";
	}
	
	/**
	 * 保存角色修改
	 * @param role
	 * @param result
	 * @param model
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@PathVariable Long id,@Valid@ModelAttribute Knowledge knowledge, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "knowledge.base.edit";
        }
		service.merge(knowledge);
        attribute.addFlashAttribute(knowledge);
        //添加提示信息
        //添加提示信息
        ActionHint hint = ActionHint.create("security.knowledge.update.hint", knowledge.getTitle());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/knowledge/base/show/"+knowledge.getId();
	}
	
	/**
	 * 删除知识库
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		service.remove(id);
		return ActionStatus.SUCCESS;
	}
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		Knowledge knowledge = (Knowledge) model.asMap().get("knowledge");
		if (null == knowledge) {
			knowledge = service.find(id);
		}
		knowledge = service.count(knowledge);
		model.addAttribute("knowledge", knowledge);
		return "knowledge.base.show";
	}
	/**
	 * 知识库主页
	 */
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(){
		return "knowledge.home";
	}
}

