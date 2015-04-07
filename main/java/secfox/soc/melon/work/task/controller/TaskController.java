package secfox.soc.melon.work.task.controller;

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
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;
import secfox.soc.melon.work.task.domain.Task;
import secfox.soc.melon.work.task.service.TaskService;

/**
 * 
 * @author 熊超
 * 2014-11-6
 * @version 1.0
 */
@Controller
@RequestMapping("/work/task")
public class TaskController {
	
	private TaskService taskService;
	
	private UUIDUtils uuidUtils;
	
	@Inject
	public TaskController(TaskService taskService, UUIDUtils uuidUtils){
		super();
		this.taskService = taskService;
		this.uuidUtils = uuidUtils;
	}
	
	/**
	 * 树页面
	 */
	@RequestMapping("/tree")
	public String tree(){
		
		return "work.task.tree";
	}
	
	/**
	 * 查找树的节点
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/find")
	public @ResponseBody List<Task> find(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return taskService.findTree(rootId, false);
	}
	
	/**
	 * 添加页面
	 * @param parentId 父节点id
	 * @param assetType
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String update(@PathVariable("parentId") Long parentId, @ModelAttribute Task task) {
		task.setParentId(parentId);
		task.setAttachId(uuidUtils.generate(Task.class));
		return "melon/work/task/create";
	}
	
	/**
	 * 创建节点
	 * @param menuResource
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.POST)
	@ResponseBody
	public  ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute Task task, BindingResult result){
		ActionHint actionHint = ActionHint.create("plan.task.create.hint", task.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		taskService.persist(task);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(task);
		return actionHint;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	public @ResponseBody ActionStatus remove(@PathVariable("id") Long id){
		taskService.remove(id);
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
			return "melon/work/task/root";
		}
		Task task = taskService.find(id);
		model.addAttribute("task", task);
		return "melon/work/task/create";
	}
	
	/**
	 * 修改
	 * @param id
	 * @param task
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public @ResponseBody ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute Task task , BindingResult result){
		ActionHint actionHint = ActionHint.create("plan.task.update.hint", task.getName());
		//如果错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		taskService.merge(task);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(task);//依赖于客户端代码，此句可不执行
		return actionHint;
	}
	
}
