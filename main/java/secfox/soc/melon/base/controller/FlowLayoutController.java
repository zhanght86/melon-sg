package secfox.soc.melon.base.controller;

import java.util.Date;

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
import secfox.soc.melon.base.domain.FlowLayout;
import secfox.soc.melon.base.query.FlowLayoutPageQuery;
import secfox.soc.melon.base.service.FlowLayoutService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;
import secfox.soc.melon.web.WebConstants;

/**
 * 
 * @author 熊超
 * 2014-10-29
 * @version 1.0
 */
@Controller
@RequestMapping("/base/flowLayout")
public class FlowLayoutController {
	
	private FlowLayoutService flowLayoutService;
	
	@Inject
	public FlowLayoutController(FlowLayoutService flowLayoutService){
		super();
		this.flowLayoutService = flowLayoutService;
	}
	
	/**
	 * 跳转到添加页面
	 * @param flowLayout
	 * @return
	 */
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String toCreate(@ModelAttribute FlowLayout flowLayout){
		flowLayout.setCreateTime(new Date());
		return "base.flowLayout.edit";
	}
	
	/**
	 * 添加
	 * @param flowLayout
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String create(@Valid@ModelAttribute("flowLayout") FlowLayout flowLayout, BindingResult result){
		ActionHint actionHint = ActionHint.create("base.dictionary.create.hint", flowLayout.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		flowLayoutService.persist(flowLayout);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(flowLayout);
		return "redirect:/base/flowLayout/list";
	}
	
	/**
	 * 
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") FlowLayoutPageQuery pageQuery) {
		return "base.flowLayout.list";
	}

	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<FlowLayout> query(@ModelAttribute FlowLayoutPageQuery pageQuery) {
		return flowLayoutService.findPages(pageQuery); 
	}
	
	/**
	 * 明细
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		FlowLayout flowLayout = flowLayoutService.find(id);
        if (flowLayout == null) {
        	flowLayout = flowLayoutService.find(id);
        }
		model.addAttribute("flowLayout", flowLayout);
		return "base.flowLayout.show";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("flowLayout", flowLayoutService.find(id));
		return "base.flowLayout.edit";
	}
	
	/**
	 * 修改
	 * @param id
	 * @param flowLayout
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@PathVariable("id") Long id,@Valid@ModelAttribute FlowLayout flowLayout, BindingResult result, RedirectAttributes attribute) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("asset.infoSystem.update.hint", flowLayout.getName());
		if(result.hasErrors()) {
            return "asset.infoSystem.edit";
        }
		flowLayoutService.merge(flowLayout);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为修改
		actionHint.setDomain(flowLayout);
		attribute.addFlashAttribute(WebConstants.ACTION_HINT, actionHint);
		
        return "redirect:/base/flowLayout/list";
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	public @ResponseBody ActionStatus remove(@PathVariable Long id) {
		flowLayoutService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
}
