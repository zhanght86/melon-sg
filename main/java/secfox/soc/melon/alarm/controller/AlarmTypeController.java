package secfox.soc.melon.alarm.controller;

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

import secfox.soc.melon.alarm.domain.AlarmType;
import secfox.soc.melon.alarm.service.AlarmTypeService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;
/**
 * @since 1.0 2014-10-21,下午3:05:25
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */

@Controller
@RequestMapping(value="/alarm/type")
public class AlarmTypeController {

     private AlarmTypeService alarmTypeService;
	/**
	 * @param alarmTypeService
	 */
	@Inject
	public AlarmTypeController(AlarmTypeService alarmTypeService) {
		super();
		this.alarmTypeService = alarmTypeService;
	}
	
	
	/**
	 * 添加页面
	 * @param parentId 父节点id
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, @ModelAttribute AlarmType alarmType) {
		AlarmType alarmTypeTmp = alarmTypeService.find(parentId);
		Long cnt = 1l;
		List<AlarmType> indilist = alarmTypeService.findByParentId(parentId);
		if(indilist.size() > 0){
			cnt = indilist.size()+1l;
		}
		if(alarmTypeTmp != null){
			alarmType.setCode(alarmTypeTmp.getCode()*cnt*100);	
		}else{
			alarmType.setCode(cnt*100);
		}
		alarmType.setParentId(parentId);
		return "melon/alarm/alarmType/create";
	}
	
	/**
	 * 添加子节点
	 * @param parentId 父节点id
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/create/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute AlarmType alarmType, BindingResult result) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("alarm.type.create.hint", alarmType.getName());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		alarmTypeService.persist(alarmType);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(alarmType);
		return actionHint;
	}
	
	@RequestMapping("/tree")
	public String tree(){
		return "alarm.alarmType.tree";
	}
		
	/**
	 * 通过节点获取树
	 * @param id 节点id
	 * @return
	 */
	@RequestMapping(value="/find")
	public @ResponseBody List<AlarmType> findTree(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return alarmTypeService.findTree(rootId,false);
	}
	
	/**
	 * ajax获取指标
	 * @return
	 */
	@RequestMapping(value="/findAll" , method=RequestMethod.GET)
	@ResponseBody
	public List<AlarmType> findAlarmType (){
		return  alarmTypeService.findAll();
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
			return "melon/alarm/alarmType/root";
		}
		AlarmType alarmType = alarmTypeService.find(id);
		model.addAttribute("alarmType", alarmType);
		return "melon/alarm/alarmType/create";
	}
	
	/**
	 * 更新指标
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute AlarmType alarmType, BindingResult result) {
		//添加操作提示
		ActionHint actionHint = ActionHint.create("alarm.type.update.hint", alarmType.getName()); 
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		alarmTypeService.merge(alarmType);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(alarmType);//依赖于客户端代码，此句可不执行
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
		alarmTypeService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	
	
}

