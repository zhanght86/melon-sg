package secfox.soc.melon.work.myWork.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;
import secfox.soc.melon.work.myWork.domain.MyWork;
import secfox.soc.melon.work.myWork.query.WorkPageQuery;
import secfox.soc.melon.work.myWork.service.MyWorkService;

/**
 * @author 曹贞杰
 * 2014年11月20日
 * @version 1.0
 */
@Controller
@RequestMapping("/work/myWork")
public class MyWorkController {
	
	private MyWorkService myWorkService;
	private UUIDUtils uuid;
	
	@Inject
	public MyWorkController(MyWorkService myWorkService, UUIDUtils uuid){
		super();
		this.myWorkService = myWorkService;
		this.uuid = uuid;
	}
	
	/**
	 * 我的工作portal页面
	 * @param myWork
	 * @return
	 */
	@RequestMapping(value="/portal", method=RequestMethod.GET)
	public String portal(Model model) {
		List<MyWork> undoWorks = myWorkService.findUndo(SecurityContextUtils.getCurrentUserInfo().getUserId());
		List<MyWork> passedWorks = myWorkService.findPassed(SecurityContextUtils.getCurrentUserInfo().getUserId());
		model.addAttribute("undoWorks", undoWorks);
		model.addAttribute("undoCount", undoWorks.size());
		model.addAttribute("passedWorks", passedWorks);
		model.addAttribute("passedCount", passedWorks.size());
		return "melon/work/myWork/myWork";
	}
	
	/**
	 * 提醒明细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notice/{id}", method=RequestMethod.GET)
	public String notice(@PathVariable("id") Long id, Model model) {
		model.addAttribute("work", myWorkService.find(id));
		return "work.myWork.notice";
	}
	
	/**
	 * 改变任务状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/know/{id}", method=RequestMethod.GET)
	public String know(@PathVariable("id") Long id) {
		myWorkService.changeState(id);
		return "redirect:/home/main";
	}
	
	/**
	 * 跳转到添加页面
	 * @param myWork
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@ModelAttribute MyWork myWork) {
		//附件
		myWork.setAttachId(uuid.generate(MyWork.class));
		
		return "work.myWork.edit";
	}
	
	/**
	 * 添加
	 * @param role
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute("myWork") MyWork myWork, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "work.myWork.edit";
        }
		myWorkService.persist(myWork);
        attribute.addFlashAttribute(myWork);
        //添加提示信息
        ActionHint hint = ActionHint.create("security.knowledge.create.hint", myWork.getTitle());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/work/myWork/list";
	}
	
	/**
	 * 跳转到list页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") WorkPageQuery pageQuery) {
		return "work.myWork.list";
	}
	
	/**
	 * 进行ajax查询获取知识库列表信息
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Pagination<MyWork> query(@ModelAttribute WorkPageQuery pageQuery) {
		return myWorkService.findPage(pageQuery);
		
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("myWork", myWorkService.find(id));
		return "work.myWork.edit";
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
	public String update(@PathVariable Long id,@Valid@ModelAttribute MyWork myWork, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "work.myWork.edit";
        }
		myWorkService.merge(myWork);
        attribute.addFlashAttribute(myWork);
        //添加提示信息
        //添加提示信息
        ActionHint hint = ActionHint.create("security.knowledge.update.hint", myWork.getTitle());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/work/myWork/show/"+myWork.getId();
	}
	
	/**
	 * 删除知识库
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		myWorkService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 内容详细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		MyWork myWork = (MyWork) model.asMap().get("myWork");
		if (null == myWork) {
			myWork = myWorkService.find(id);
		}
		model.addAttribute("myWork", myWork);
		return "work.myWork.show";
	}
	
	/**
	 * 导出数据
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/export",method=RequestMethod.GET, produces={"application/vnd.ms-excel"})
	public String export(Model model,HttpServletRequest req) {
		String wordIdString = req.getParameter("organId");
		//如果有参数
		if(StringUtils.isNotBlank(wordIdString)){
			Long organId = Long.parseLong(wordIdString);
			List<MyWork> findByMyWork = myWorkService.findByWork(organId); //获取数据集
			String [] name={findByMyWork.get(0).getTitle()}; //给国际化参数赋值
			String fileName = MessageSourceUtils.getMessage("work.myWork.organ.export",name );
			model.addAttribute("title",fileName); //设置标题
			model.addAttribute("results", findByMyWork); //获取导出数据
		}else{
			//设置标题
			model.addAttribute("title",MessageSourceUtils.getMessage("work.myWork.export"));
			//取到要导出的数据集
			model.addAttribute("results", myWorkService.findAll());
		}
		return "work.myWork.export";
	}
	
}



