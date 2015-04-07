package secfox.soc.melon.knowledge.controller;

import java.util.List;

import javax.inject.Inject;
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

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.knowledge.domain.KnowledgeType;
import secfox.soc.melon.knowledge.service.KnowledgeTypeService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;
/**
 * @since 1.0 2014-10-28
 * @author  <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
 * @version  1.0 
 */

@Controller
@RequestMapping(value="/knowledge/type")
public class KnowledgeTypeController {

     private KnowledgeTypeService knowledgeTypeService;
	/**
	 * @param alarmTypeService
	 */
	@Inject
	public KnowledgeTypeController(KnowledgeTypeService knowledgeTypeService) {
		super();
		this.knowledgeTypeService = knowledgeTypeService;
	}
	
	
	/**
	 * 添加页面
	 * @param parentId 父节点id
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, @ModelAttribute KnowledgeType knowledgeType) {
		knowledgeType.setParentId(parentId);
		//JSP视图
		return "melon/knowledge/type/create";
	}
	
	/**
	 * 添加子节点
	 * @param parentId 父节点id
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/create/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute KnowledgeType knowledgeType, BindingResult result) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("knowledge.type.create.hint", knowledgeType.getTitle());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		knowledgeTypeService.persist(knowledgeType);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(knowledgeType);
		return actionHint;
	}
	
	/**
	 * 这是访问的根路径
	 * @return
	 */
	@RequestMapping("/tree")
	public String tree(){
		return "knowledge.type.tree";
	}
		
	/**
	 * 通过节点获取树
	 * @param id 节点id
	 * @return
	 */
	@RequestMapping(value="/find")
	public @ResponseBody List<KnowledgeType> findTree(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return knowledgeTypeService.findTree(rootId,false);
	}
	
	/**
	 * ajax获取指标
	 * @return
	 */
	@RequestMapping(value="/findAll" , method=RequestMethod.GET)
	@ResponseBody
	public List<KnowledgeType> findKnowledgeType (){
		return  knowledgeTypeService.findAll();
	}
	
	/**
	 * 更新页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//返回根节点说明页面
		if(id == BaseConstants.ROOT_ID) {
			return "melon/knowledge/type/root";
		}
		KnowledgeType knowledgeType = knowledgeTypeService.find(id);
		model.addAttribute("knowledgeType", knowledgeType);
		return "melon/knowledge/type/create";
	}
	
	/**
	 * 更新指标
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute KnowledgeType knowledgeType, BindingResult result) {
		//添加操作提示
		ActionHint actionHint = ActionHint.create("knowledge.type.update.hint", knowledgeType.getTitle()); 
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		knowledgeTypeService.merge(knowledgeType);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(knowledgeType);//依赖于客户端代码，此句可不执行
		return actionHint;
	}
	
	/**
	 * 删除指标
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		knowledgeTypeService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
}

