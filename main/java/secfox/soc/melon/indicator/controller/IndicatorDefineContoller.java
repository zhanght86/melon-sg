package secfox.soc.melon.indicator.controller;

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
import secfox.soc.melon.indicator.domain.IndicatorDefine;
import secfox.soc.melon.indicator.serivce.IndicatorDefineService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

/**
 * @since 1.0 2014-10-15,下午3:21:52
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@RequestMapping("/indicator/indicatorDefine")
@Controller
public class IndicatorDefineContoller {

private IndicatorDefineService indicatorDefineService;
	
	/**
	 * @param indicatorDefineService
	 */
	@Inject
	public IndicatorDefineContoller(IndicatorDefineService indicatorDefineService) {
		super();
		this.indicatorDefineService = indicatorDefineService;
	}
	
	
	/**
	 * 添加页面
	 * @param parentId 父节点id
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, @ModelAttribute IndicatorDefine indicatorDefine) {
		IndicatorDefine indicatorTmp = indicatorDefineService.find(parentId);
		Long cnt = 1l;
		List<IndicatorDefine> indilist = indicatorDefineService.findByParentId(parentId);
		if(indilist.size() > 0){
			cnt = indilist.size()+1l;
		}
		if(indicatorTmp != null){
			indicatorDefine.setCode(indicatorTmp.getCode()*cnt*100);	
		}else{
			indicatorDefine.setCode(cnt*100);
		}
		indicatorDefine.setParentId(parentId);
		return "melon/indicator/indicatorDefine/create";
	}
	
	/**
	 * 添加子节点
	 * @param parentId 父节点id
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/create/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute IndicatorDefine indicatorDefine, BindingResult result) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("indicator.defined.create.hint", indicatorDefine.getName());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		indicatorDefineService.persist(indicatorDefine);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(indicatorDefine);
		return actionHint;
	}
	
	@RequestMapping("/tree")
	public String tree(){
		return "indicator.indicatorDefine.tree";
	}
		
	/**
	 * 通过节点获取树
	 * @param id 节点id
	 * @return
	 */
	@RequestMapping(value="/find")
	public @ResponseBody List<IndicatorDefine> findTree(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return indicatorDefineService.findTree(rootId,false);
	}
	
	/**
	 * ajax获取指标
	 * @return
	 */
	@RequestMapping(value="/findAll" , method=RequestMethod.GET)
	@ResponseBody
	public List<IndicatorDefine> findIndicator (){
		return  indicatorDefineService.findAll();
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
			return "melon/indicator/indicatorDefine/root";
		}
		IndicatorDefine indicatorDefine = indicatorDefineService.find(id);
		model.addAttribute("indicatorDefine", indicatorDefine);
		return "melon/indicator/indicatorDefine/create";
	}
	
	/**
	 * 更新指标
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute IndicatorDefine indicatorDefine, BindingResult result) {
		//添加操作提示
		ActionHint actionHint = ActionHint.create("indicator.defined.update.hint", indicatorDefine.getName()); 
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		indicatorDefineService.merge(indicatorDefine);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(indicatorDefine);//依赖于客户端代码，此句可不执行
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
		indicatorDefineService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	
}

