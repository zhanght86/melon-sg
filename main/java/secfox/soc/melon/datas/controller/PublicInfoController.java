package secfox.soc.melon.datas.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.datas.domain.PublicInfo;
import secfox.soc.melon.datas.query.PublicInfoPageQuery;
import secfox.soc.melon.datas.service.PublicInfoService;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;

@Controller
@RequestMapping("/datas/publicInfo")
public class PublicInfoController {

	private PublicInfoService pbService;
	private UUIDUtils uuid;
	
	@Inject
	public PublicInfoController(PublicInfoService pbService,UUIDUtils uuid) {
		super();
		this.pbService = pbService;
		this.uuid = uuid;
	}


	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(){
		return "datas.publicInfo.home";
	}
	
	//创建下发
	@RequestMapping(value="/create/{type}",method=RequestMethod.GET)
	public String create(@PathVariable int type,@ModelAttribute PublicInfo publicInfo,HttpSession session,Model model){
		Account account = SecurityContextUtils.getCurrentAccount(session);
		if(account!=null){
			UserInfo userInfo = account.getUserInfo();
			if(userInfo!=null){
				publicInfo.setCreator(userInfo);
			}
		}
		publicInfo.setCreateTime(new Date());
		publicInfo.setAttachId(uuid.generate(PublicInfo.class));
		publicInfo.setType(type);
		publicInfo.setOrder(0L);
		model.addAttribute("type", type);
		return "datas.publicInfo.edit";
	}
	//创建下发
	@RequestMapping(value="/create/{type}",method=RequestMethod.POST)
	public String create(@PathVariable int type,@Valid@ModelAttribute PublicInfo publicInfo,BindingResult result,RedirectAttributes attribute){
		if(result.hasErrors()){
			return "datas.publicInfo.edit";
		}
		pbService.persist(publicInfo);
		attribute.addFlashAttribute(publicInfo);
		ActionHint hint = ActionHint.create("datas.create.hint", publicInfo.getTitle());
	    attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
		return "redirect:/datas/publicInfo/list/"+type;
	}
	//查看下发
	@RequestMapping(value="/list/{type}",method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") PublicInfoPageQuery query,@PathVariable int type,Model model){
		
		model.addAttribute("type", type);
		return "datas.publicInfo.list";
	}
	//查看下发
	@RequestMapping(value="/list/{type}",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<PublicInfo> query(@PathVariable int type,@ModelAttribute("pageQuery") PublicInfoPageQuery query){
		query.getSearchForm().setType(type);
		return pbService.findPages(query);
	}
	
	@RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	public String show(@PathVariable Long id,Model model){
		PublicInfo article  = (PublicInfo)model.asMap().get("publicInfo");
		if(article==null){
			article = pbService.find(id);
		}
		model.addAttribute("publicInfo", article);
		return "datas.publicInfo.show";
	}
	
	//发文修改
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		PublicInfo article = pbService.find(id);
		model.addAttribute("publicInfo", article);
		return "datas.publicInfo.edit";
	}
	//发文修改
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Long id,@Valid@ModelAttribute PublicInfo article,BindingResult result,RedirectAttributes attribute){
		if(result.hasErrors()){
			return "datas.publicInfo.edit";
		}
		pbService.merge(article);
		attribute.addFlashAttribute(article);
		ActionHint hint = ActionHint.create("datas.update.hint", article.getTitle());
	    attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
		return "redirect:/datas/publicInfo/show/"+article.getId();
	}
	
	//发文删除
	@RequestMapping(value="/remove/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable Long id){
		pbService.remove(id);;
		return ActionStatus.SUCCESS;
	}
	
	//置顶
	@RequestMapping(value="/top/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus top(@PathVariable Long id){
		PublicInfo pbInfo = pbService.find(id);
		if(pbInfo.getOrder()!=null&&pbInfo.getOrder()!=1){
			pbInfo.setOrder(1L);
		}
		pbService.merge(pbInfo);
		return ActionStatus.SUCCESS;
	}
}
