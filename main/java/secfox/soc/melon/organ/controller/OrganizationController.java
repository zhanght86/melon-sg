/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.organ.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

/**
 * @since 1.0 2014年9月20日,下午4:37:18
 * 组织机构管理控制器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@RequestMapping("/organ/organization")
@Controller
public class OrganizationController {
	
	private OrganizationService organizationService;
	
	/**
	 * @param organizationService
	 */
	@Inject
	public OrganizationController(OrganizationService organizationService) {
		super();
		this.organizationService = organizationService;
	}
	
	/**
	 * 新建页面
	 * @param parentId 父节点ID
	 * @param type 节点类型
	 * @param organ 组织机构
	 * @return 新建页面
	 */
	@RequestMapping(value="/create/{parentId}", method = RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, @RequestParam("type") int type, @ModelAttribute("organ") Organization organ) {
		organ.setParentId(parentId);
		organ.setType(type);
		//设置辅助编码
		return "melon/organ/organization/create";
	}
	
	/**
	 * 保存到数据库
	 * @param parentId
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method = RequestMethod.POST)
	public @ResponseBody ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute("organ") Organization organ, BindingResult result) {
		ActionHint actionHint = ActionHint.create("organ.organization.create.hint", organ.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		//保存业务对象
		organizationService.persist(organ);
		//添加操作协助提示(本处因为树性操作结构)，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(organ);
		return actionHint;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home() {
		return "organ.organization.home";
	}
	
	/**
	 * 显示树性列表页面
	 * @return
	 */
	@RequestMapping(value="/tree", method = RequestMethod.GET)
	public String tree() {
		return "organ.organization.tree";
	}
	
	/**
	 * load方式更新组织机构
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//返回根节点说明页面
		if(id == BaseConstants.ROOT_ID) {
			return "melon/organ/organization/root";
		}
		Organization organization = organizationService.find(id);
		model.addAttribute("organ", organization);
		//直接返回jsp页面
		return "melon/organ/organization/create";
	}
	
	/**
	 * ajax更新信息
	 * @param id
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute("organ") Organization organ, BindingResult result) {
		ActionHint actionHint = ActionHint.create("organ.organization.update.hint", organ.getName());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		organizationService.merge(organ);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(organ);//依赖于客户端代码，此句可不执行
		return actionHint;
	}
	
	/**
	 * 删除组织机构
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		organizationService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 获得组织机构树
	 * @param id
	 * @param organ
	 * @return
	 */
	@RequestMapping(value="/find", method = RequestMethod.GET)
	@ResponseBody
	public List<Organization> findTree(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		boolean includeDepart = true;
		String departs = request.getParameter("includeDepart");
		if(StringUtils.isNotBlank(departs)) {
			includeDepart = Boolean.parseBoolean(departs);
		}
		return organizationService.findTree(id, includeDepart, true);
	}
	
	/**
	 * 获取子单位
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getSubOrgan", method = RequestMethod.GET)
	@ResponseBody
	public List<Organization> getSubOrgan(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		return organizationService.getSubOrgan(id);
	}
	
	/**
     * 根据名称判断帐号的唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/checkByName", method=RequestMethod.POST)
    public @ResponseBody boolean checkByName(HttpServletRequest request) {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        return organizationService.findByName(name, id, type);  
    }
    
    /**
     * 根据编码判断帐号的唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/checkByCode", method=RequestMethod.POST)
    public @ResponseBody boolean checkUnique(HttpServletRequest request) {
        String code = request.getParameter("code");
        String id = request.getParameter("id");
        return organizationService.findByCode(code, id);  
    }
	
	/**
	 * 统计查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/count", method = RequestMethod.GET)
	public String count(Model model) {
		List<Organization> organs = organizationService.countAcc();
		model.addAttribute("organs", organs);
		return "organ.organization.count";
	}
	
	/**
	 * 查询组织机构
	 * @param type 组织机构类型  0:行政区域,1:单位,2:部门
	 * @param parentId 查询单位时为上一级行政区域id,查询部门时为单位id
	 * @return
	 */
	@RequestMapping(value="/findOrgans", method = RequestMethod.GET)
	@ResponseBody
	public List<Organization> findCompany(HttpServletRequest req) {
		//组织机构类型默认值为1,父节点为0
		int type = 1;
		Long parentId = new Long(1l);
		//判断请求是否有参数
		if(StringUtils.isNotBlank(req.getParameter("type"))){
			type = Integer.parseInt(req.getParameter("type"));
		}
		if(StringUtils.isNotBlank(req.getParameter("parentId"))){
			parentId = Long.valueOf(req.getParameter("parentId")); 
		}
		if(type==0 || type==1 || type==2){
			return organizationService.findOrgans(type, parentId);
		}else{
			return null;
		}
		
	}
	
	/**
	 * 显示组织机构树的明细
	 */
	
	@RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model){
		Organization organization = (Organization)model.asMap().get("organization");
		if(organization == null){
			organization = organizationService.find(id);
		}
		model.addAttribute("organization",organization);
		return "organ.organization.show";
	}
	
	/**
	 * 自定义树
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/customTree",method=RequestMethod.GET)
	@ResponseBody
	public List<Organization> customTree(HttpServletRequest req){
		int type = 1;
		Long rootId = new Long(1l);
		//判断请求是否有参数
		if(StringUtils.isNotBlank(req.getParameter("type"))){
			type = Integer.parseInt(req.getParameter("type"));
		}
		if(StringUtils.isNotBlank(req.getParameter("rootId"))){
			rootId = Long.valueOf(req.getParameter("rootId")); 
		}
		return organizationService.customTree(rootId, type);
	}
	
}
