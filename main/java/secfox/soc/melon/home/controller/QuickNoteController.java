package secfox.soc.melon.home.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.home.domain.QuickNote;
import secfox.soc.melon.home.query.MyPageQuery;
import secfox.soc.melon.home.query.MySearch;
import secfox.soc.melon.home.service.QuickNoteService;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * @since 1.0 2014-10-31,下午4:25:16
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Controller
@RequestMapping(value="/home/quicknote")
public class QuickNoteController {
	
	private QuickNoteService quickService;

	/**
	 * 
	 * @param quickService
	 */
	@Inject
	public QuickNoteController(QuickNoteService quickService){
		this.quickService = quickService;
	}
	
	/**
	 * 创建关注
	 * @param attention
	 * @return
	 */
	@RequestMapping(value="/countNotes", method=RequestMethod.POST)
	@ResponseBody
	public int countNotes(HttpServletRequest req) {
		Long type  = Long.valueOf(req.getParameter("type"));
		Long businessId = Long.valueOf(req.getParameter("businessId"));
		quickService.saveNotes(req,type,businessId);
		return quickService.count(type, businessId);
	}
	
	/**
	 * 
	 * @param type
	 * @param businessId
	 * @param quickNote
	 * @return
	 */
	@RequestMapping(value="/createNote/{type}/{businessId}", method=RequestMethod.GET)
	public String createNote(@PathVariable("type") Long type, @PathVariable("businessId") Long businessId,@ModelAttribute("quickNote") QuickNote quickNote) {
		return "melon/home/quicknote/note";
	}


	/**
	 * 返回页面按钮信息
	 * @param type
	 * 
	 * @param domainId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show")
	public String show(@RequestParam("type") Long type, @RequestParam("businessId") Long businessId, Model model) {
		int noteCounts = quickService.count(type, businessId);
		model.addAttribute("noteCounts", noteCounts);
		return "melon/home/quicknote/show";
	}
	
	
	//显示当前资产类型对应的所有批注
	@RequestMapping(value="/listNotes/{type}/{businessId}")
	public String listUser(@PathVariable("type") Long type, @PathVariable("businessId") Long businessId,Model model) {
		List<QuickNote> quickNotes = quickService.find(type, businessId);
		model.addAttribute("quickNotes", quickNotes);
		return "melon/home/quicknote/listNotes";
	}
	
	/**
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	//显示当前用户所有批注
	@RequestMapping(value="/findNotesByUserId/{userId}")
	public String listNotesByUserId(@PathVariable("userId") Long userId,Model model) {
		List<QuickNote> quickNotes = quickService.findByUserId(userId);
		model.addAttribute("quickNotes", quickNotes);
		return "melon/home/quicknote/listNotes";
	}
	
	/**
	 * 根据当前登录用户获取个人的关注内容
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/my")
	public String my(Model model) {
		Long userId = SecurityContextUtils.getCurrentAccount().getId();
		//查看当前用户批注的设备对象
		List<QuickNote> deviceList = quickService.findNotedDevices(userId);
		if(deviceList.size()>0){
			model.addAttribute("deviceList", deviceList);
		}
		//查看当前用户批注的信息系统
		List<QuickNote> infoList = quickService.findNotedInfoSystems(userId);
		if(infoList.size()>0){
			model.addAttribute("infoList", infoList);
		}
		return "home.quicknote.my";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseBody
	public int remove(HttpServletRequest request) {
		Long type  = Long.valueOf(request.getParameter("type"));
		Long businessId = Long.valueOf(request.getParameter("businessId"));
		Long userId = SecurityContextUtils.getCurrentAccount().getId();
		Long id = quickService.delByBussId(type,businessId,userId);
		quickService.remove(id);
		return quickService.count(type, businessId);
	}
	
	/**
	 * 查找某一类的关注
	 * @param id
	 * @param currPage
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find/{id}", method=RequestMethod.GET)
	public String find(@PathVariable("type") Long type, @RequestParam("currPage") Long currPage, Model model) {
		//构造查询条件
		MySearch search = new MySearch();
		search.setType(type);
		search.setUserId(SecurityContextUtils.getCurrentAccount().getId());
		//
		PageQuery<MySearch> myPage = new MyPageQuery();
		myPage.setCurrPage(currPage.intValue());
		myPage.setSearchForm(search);
		//
		return "";
	}
	
}

