/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.workflow.controller;

import java.util.Date;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;
import secfox.soc.melon.workflow.domain.BpmProcess;
import secfox.soc.melon.workflow.query.BpmProcessPageQuery;
import secfox.soc.melon.workflow.service.BpmProcessService;

/**
 * @since 1.0 2014年9月22日,下午1:53:44
 * 帐户管理控制器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/workflow/bpmProcess")
public class BpmProcessController {

	private BpmProcessService bpmProcessService;
	
	
	/**
	 * 
	 * @param accountService
	 * @param roleService
	 */
	@Inject
	public BpmProcessController(BpmProcessService bpmProcessService){
		super();
		this.bpmProcessService = bpmProcessService;
	}
	
	/**
	 * 跳转到账号添加页面
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@ModelAttribute BpmProcess bpmProcess ) {
		bpmProcess.setCreateTime(new Date());
		return "workflow.bpmProcess.create";
	}
	
	/**
	 * 账号信息添加
	 * @param account
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute BpmProcess bpmProcess, BindingResult result, RedirectAttributes attribute,HttpServletRequest req) {
		if(result.hasErrors()) {
            return "workflow.bpmProcess.create";
        }
		bpmProcess = bpmProcessService.drawProps(bpmProcess,req);
		bpmProcessService.persist(bpmProcess);
        attribute.addFlashAttribute(bpmProcess);
        //添加提示信息
        ActionHint hint = ActionHint.create("workflow.bpmProcess.create.hint", bpmProcess.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/workflow/bpmProcess/list";
	}
	
	
	/**
	 * 跳转到list页面
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") BpmProcessPageQuery pageQuery) {
		return "workflow.bpmProcess.list";
	}

	/**
	 * ajax列表查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<BpmProcess> query(@ModelAttribute BpmProcessPageQuery pageQuery) {
		return bpmProcessService.findPage(pageQuery);
	}
	
	/**
	 * 跳转到编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id,Model model) {
		model.addAttribute("bpmProcess", bpmProcessService.find(id));
		return "workflow.bpmProcess.create";
	}
	
	/**
	 * 保存修改
	 * @param globalConfig
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@Valid@ModelAttribute("bpmProcess") BpmProcess bpmProcess, HttpServletRequest req, BindingResult result) {
		if(result.hasErrors()){
			//出错重新返回编辑页面;
			return "workflow.bpmProcess.create";
		}
		bpmProcess = bpmProcessService.drawProps(bpmProcess,req);
		bpmProcessService.merge(bpmProcess);
		return "redirect:/workflow/bpmProcess/list";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		bpmProcessService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 账号明细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		BpmProcess bpmProcess = (BpmProcess)model.asMap().get("bpmProcess");
        if (bpmProcess == null) {
        	bpmProcess = bpmProcessService.find(id);
        }
		model.addAttribute("bpmProcess", bpmProcess);
        return "workflow.bpmProcess.show";
	}
	
	
}
