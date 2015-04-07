/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.asset.service.InfoSystemService;
import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.home.domain.Attention;
import secfox.soc.melon.home.query.MyPageQuery;
import secfox.soc.melon.home.query.MySearch;
import secfox.soc.melon.home.service.AttentionService;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.service.AccountService;
import secfox.soc.melon.security.util.SecurityContextUtils;

import com.google.common.collect.Lists;

/**
 * @since 1.0 2014年10月21日,下午4:11:34
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping(value="/home/attention")
public class AttentionController {
	
	private AttentionService attentionService;
	
	private AccountService accountService;
	
	private DeviceService deviceService;
	
	private InfoSystemService infoSystemService;
	
	/**
	 * @param attentionService
	 */
	@Inject
	public AttentionController(AttentionService attentionService, DeviceService deviceService, InfoSystemService infoSystemService,AccountService accountService) {
		super();
		this.attentionService = attentionService;
		this.deviceService = deviceService;
		this.infoSystemService = infoSystemService;
		this.accountService = accountService;
	}

	/**
	 * 创建关注
	 * @param attention
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	@ResponseBody
	public int create(HttpServletRequest request, Model model) {
		Long type  = Long.valueOf(request.getParameter("type"));
		Long businessId = Long.valueOf(request.getParameter("businessId"));
		Attention att = new Attention();
		att.setBusinessId(businessId);
		att.setType(type);
		att.setUser(SecurityContextUtils.getCurrentAccount().getUserInfo());
		att.setCreateTime(new Date());
		attentionService.persist(att);
		
		List<Attention> list = attentionService.findList(type, businessId);
		int count = list.size();
		
		Long userId = SecurityContextUtils.getCurrentAccount().getId();
		List<Attention> ownerList =	attentionService.isAttentioned(type, businessId, userId);
		
		if(ownerList.size()!= 0){
			model.addAttribute("attId", ownerList.get(0).getId());
		}
		return count;
	}
	
	/**
	 * 根据当前登录用户获取个人的关注内容
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/my")
	public String my(HttpServletRequest request, Model model) {
		Long userId = Long.valueOf(request.getParameter("userId"));
		if(userId == null){
			userId = SecurityContextUtils.getCurrentAccount().getId();
		}
		//当前用户关注的设备对象
		List<Device> deviceList = deviceService.findByUserBusiness(userId);
		model.addAttribute("deviceList", deviceList);
		
		List<InfoSystem> infoList = infoSystemService.findByUserBusiness(userId);
		model.addAttribute("infoList", infoList);
		return "home.attention.my";
	}
	
	
	@RequestMapping(value="/myAttention/{userId}")
	public String myAttention(@PathVariable("userId") Long userId, Model model) {
		
		//当前用户关注的设备对象
		List<Device> deviceList = deviceService.findByUserBusiness(userId);
		model.addAttribute("deviceList", deviceList);
		
		List<InfoSystem> infoList = infoSystemService.findByUserBusiness(userId);
		model.addAttribute("infoList", infoList);
		return "home.attention.myAttention";
	}
	
	/**
	 * 关注
	 * @param type
	 * @param businessId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show")
	public String show(@RequestParam("type") Long type, @RequestParam("businessId") Long businessId, Model model) {
		//查出有多少人关注
		List<Attention> list = attentionService.findList(type, businessId);
		model.addAttribute("attentionCount", list.size());
		//查出自己有没有关注
		Long userId = SecurityContextUtils.getCurrentAccount().getId();
		List<Attention> ownerList =	attentionService.isAttentioned(type, businessId, userId);
		if(ownerList.size()!= 0){
			model.addAttribute("attId", ownerList.get(0).getId());
		}
		model.addAttribute("isAttentioned", ownerList.size());
		return "melon/home/attention/show";
	}
	
	/**
	 * 查询关注的人
	 * @param type
	 * @param businessId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listUser")
	public String listUser(@RequestParam("type") Long type, @RequestParam("businessId") Long businessId, Model model) {
		List<Attention>	userList= attentionService.findList(type, businessId);
		List<Account> atts = Lists.newArrayList();
		for(int i=0;i<userList.size();i++){
			String userName = userList.get(i).getUser().getUsername();
			Account account = accountService.findByName(userName);
			atts.add(account);
		}
		model.addAttribute("atts", atts);
		
		return "melon/home/attention/listUser";
	}
	
	/**
	 * 取消关注
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove", method=RequestMethod.POST )
	@ResponseBody
	public int remove(HttpServletRequest req) {
		Long type = Long.valueOf(req.getParameter("type"));
		Long businessId = Long.valueOf(req.getParameter("businessId"));
		Long userId = SecurityContextUtils.getCurrentAccount().getId();
		List<Attention> ownerList =	attentionService.isAttentioned(type, businessId, userId);
		attentionService.remove(ownerList.get(0).getId());
		List<Attention> list = attentionService.findList(type, businessId);
		int count = list.size();
		return count;
	}
	
	/**
	 * 查找某一类的关注
	 * @param id
	 * @param currPage
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/find/{type}", method=RequestMethod.GET)
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
