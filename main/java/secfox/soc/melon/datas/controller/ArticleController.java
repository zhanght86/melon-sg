package secfox.soc.melon.datas.controller;

import java.util.Date;
import java.util.List;

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
import secfox.soc.melon.datas.domain.Article;
import secfox.soc.melon.datas.domain.ArticleReceiver;
import secfox.soc.melon.datas.query.ArticlePageQuery;
import secfox.soc.melon.datas.query.ArticleReceiverPageQuery;
import secfox.soc.melon.datas.service.ArticleReceiverService;
import secfox.soc.melon.datas.service.ArticleService;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.domain.Role;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;
/**
 * 信息管理(待办，下发，上传，总局发文)
 * @author 11
 *
 */
@Controller
@RequestMapping("/info/article")
public class ArticleController {
	private ArticleService arService;

	private ArticleReceiverService arreService;
	
	private UUIDUtils uuid;
	
	@Inject
	public ArticleController(ArticleService arService,UUIDUtils uuid, ArticleReceiverService arreService)	 {
		super();
		this.arService = arService;
		this.uuid  = uuid;
		this.arreService = arreService;
	}
	//创建下发
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(@ModelAttribute Article article,HttpSession session){
		Account account = SecurityContextUtils.getCurrentAccount(session);
		if(account!=null){
			UserInfo userInfo = account.getUserInfo();
			if(userInfo!=null){
				article.setCreator(userInfo);
			}
		}
		article.setCreateTime(new Date());
		article.setAttachId(uuid.generate(Article.class));
		return "gate.article.edit";
	}
	//创建下发
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute Article article,BindingResult result,RedirectAttributes attribute){
		if(result.hasErrors()){
			return "gate.article.edit";
		}
		arService.persist(article);
		attribute.addFlashAttribute(article);
		ActionHint hint = ActionHint.create("gate.article.create.hint", article.getTitle());
	    attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
		return "redirect:/info/article/list";
	}
	//查看下发
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") ArticlePageQuery query){
		return "gate.article.list";
	}
	//查看下发
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Article> query(@ModelAttribute("pageQuery") ArticlePageQuery query){
		return arService.findPages(query);
	}
	
	//查看待办
	@RequestMapping(value="/listSend",method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") ArticleReceiverPageQuery query) {
		return "gate.article.listSend";
	}
	//查看待办
	@RequestMapping(value="/listSend",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<ArticleReceiver> query(@ModelAttribute("pageQuery") ArticleReceiverPageQuery query,HttpSession session) {
		Account account = SecurityContextUtils.getCurrentAccount(session);
		if(account!=null){
			UserInfo userInfo = account.getUserInfo();
			query.getSearchForm().setReceiver(userInfo);
		}
		return arreService.findPages(query);
	}
	//待办明细
	@RequestMapping(value="/sendShow/{id}",method=RequestMethod.GET)
	public String sendShow(@PathVariable Long id,Model model){
		ArticleReceiver receiver = (ArticleReceiver)model.asMap().get("articleReceiver");
		if(receiver==null){
			receiver = arreService.find(id);
		}
		model.addAttribute("receiver", receiver);
		return "gate.article.sendShow";
	}
	
	//上传列表
	@RequestMapping(value="/listUpload",method=RequestMethod.GET)
	public String listUpload(@ModelAttribute("pageQuery") ArticlePageQuery query) {
		return "gate.article.listUpload";
	}
	//上传列表
	@RequestMapping(value="/listUpload",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Article> upload(@ModelAttribute("pageQuery") ArticlePageQuery query,HttpSession session) {
		//下级上传2
		query.getSearchForm().setType(2);
		Account account = SecurityContextUtils.getCurrentAccount(session);
		if(account!=null){
			UserInfo userInfo = account.getUserInfo();
			if(userInfo!=null){
				query.getSearchForm().setOrganId(userInfo.getOrganId());
			}
		}
		List<Role> roles = account.getRoles();
		for(Role role:roles){
			if(role.getCode().equals("ADMIN")){
				query.getSearchForm().setDuty("ADMIN");
			}
		}
		Pagination<Article> list = arService.findPages(query);
		return list;
	}
	//创建上传
	@RequestMapping(value="/sendCreate",method=RequestMethod.GET)
	public String toCreate(@ModelAttribute Article article,HttpSession session){
		Account account = SecurityContextUtils.getCurrentAccount(session);
		if(account!=null){
			UserInfo userInfo = account.getUserInfo();
			if(userInfo!=null){
				article.setCreator(userInfo);
			}
		}
		article.setCreateTime(new Date());
		article.setAttachId(uuid.generate(Article.class));
		return "gate.article.create";
	}
	//创建上传
	@RequestMapping(value="/sendCreate",method=RequestMethod.POST)
	public String toCreate(@Valid@ModelAttribute Article article,BindingResult result,RedirectAttributes attritue){
		if(result.hasErrors()) {
			return "gate.article.create";
		}
		arService.persist(article);
		attritue.addFlashAttribute(article);
		ActionHint hint = ActionHint.create("gate.article.upload.hint", article.getTitle());
		attritue.addFlashAttribute(WebConstants.ACTION_HINT, hint);
		return "redirect:/info/article/listUpload";
	}
	
	@RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	public String show(@PathVariable Long id,Model model){
		Article article  = (Article)model.asMap().get("article");
		if(article==null){
			article = arService.find(id);
		}
		model.addAttribute("article", article);
		return "gate.article.show";
	}
	
	/*
	 * 上传明细
	 */
	@RequestMapping(value="/showUpload/{id}",method=RequestMethod.GET)
	public String showUpload(@PathVariable Long id,Model model){
		Article article  = (Article)model.asMap().get("article");
		if(article==null){
			article = arService.find(id);
		}
		model.addAttribute("article", article);
		return "gate.article.show";
	}
	//发文修改
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		Article article = arService.find(id);
		model.addAttribute("article", article);
		if(article.getType()==2){//上传
			return "gate.article.create";
		}
		return "gate.article.edit";
	}
	//发文修改
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Long id,@Valid@ModelAttribute Article article,BindingResult result,RedirectAttributes attribute){
		if(result.hasErrors()){
			return "gate.article.edit";
		}
		arService.merge(article);
		attribute.addFlashAttribute(article);
		ActionHint hint = ActionHint.create("gate.article.update.hint", article.getTitle());
	    attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
		return "redirect:/gate/article/show/"+article.getId();
	}
	//发文删除
	@RequestMapping(value="/remove/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable Long id){
		arService.remove(id);;
		return ActionStatus.SUCCESS;
	}
}