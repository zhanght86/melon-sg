package secfox.soc.melon.base.controller;

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
import secfox.soc.melon.base.domain.Dictionary;
import secfox.soc.melon.base.service.DictionaryService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;


/**
 * 数据字典控制器
 * @author 熊超
 * 2014-10-9
 * @version 1.0
 */
@Controller
@RequestMapping("/base/dictionary")
public class DictionaryController {
	
	private DictionaryService dictionaryService;
	
	@Inject
	public DictionaryController(DictionaryService dictionaryService){
		super();
		this.dictionaryService = dictionaryService;
	}
	
	/**
	 * 跳转到树页面
	 * @return
	 */
	@RequestMapping("/tree")
	public String tree(){
		return "base.dictionary.tree";
	}
	
	/**
	 * 查询树节点
	 * @param id
	 * @return
	 */
	@RequestMapping("/find")
	public @ResponseBody List<Dictionary> find(@RequestParam String id){
		Long rootId = Long.parseLong(id);
		return dictionaryService.findTree(rootId);
	}
	
	/**
	 * 添加页面
	 * @param parentId
	 * @param menuResource
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, @ModelAttribute Dictionary dictionary){
		//设置父节点
		dictionary.setParentId(parentId);
		return "melon/base/dictionary/create";
	}
	
	/**
	 * 创建节点
	 * @param menuResource
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.POST)
	public @ResponseBody ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute Dictionary dictionary, BindingResult result){
		ActionHint actionHint = ActionHint.create("base.dictionary.create.hint", dictionary.getLabel());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		dictionaryService.persist(dictionary);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(dictionary);
		return actionHint;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	public @ResponseBody ActionStatus remove(@PathVariable("id") Long id){
		dictionaryService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model){
		//返回根节点说明页面
		if(id == BaseConstants.ROOT_ID) {
			return "melon/base/dictionary/root";
		}
		Dictionary menu = dictionaryService.find(id);
		model.addAttribute("dictionary", menu);
		return "melon/base/dictionary/create";
	}
	
	/**
	 * 修改
	 * @param id
	 * @param menuResource
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public @ResponseBody ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute Dictionary dictionary , BindingResult result){
		ActionHint actionHint = ActionHint.create("security.resource.update.hint", dictionary.getLabel());
		//如果错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		dictionaryService.merge(dictionary);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(dictionary);//依赖于客户端代码，此句可不执行
		return actionHint;
	}
	
}
