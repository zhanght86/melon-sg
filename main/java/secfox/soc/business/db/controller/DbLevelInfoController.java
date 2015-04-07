/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.business.db.domain.DbLevelInfo;
import secfox.soc.business.db.service.DbLevelInfoService;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.WebConstants;

/**
 *
 * @since 1.0 2014-10-27
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Controller
@RequestMapping("db/levelInfo")
public class DbLevelInfoController {
	
	private UUIDUtils uuidUtils;
	
	private DbLevelInfoService dbLevelInfoService;
	
	@Inject
	public DbLevelInfoController(DbLevelInfoService dbLevelInfoService,UUIDUtils uuidUtils){
		this.dbLevelInfoService = dbLevelInfoService;
		this.uuidUtils = uuidUtils;
	}
	
	/**
	 * 跳转到单位基本信息新建页面
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create( @ModelAttribute("dbLevelInfo") DbLevelInfo levelInfo ) {
		String attaches = uuidUtils.generate(DbLevelInfo.class);
		levelInfo.setAttachId(attaches);
		return "db.levelInfo.edit";
	}
	
	/**
	 * 保存单位基本信息
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("dbLevelInfo") DbLevelInfo levelInfo,BindingResult result, RedirectAttributes attribute) {
		
        if(result.hasErrors()) {
            return "db.levelInfo.edit";
        }
        levelInfo.setCreateTime(new Date());
        
        dbLevelInfoService.persist(levelInfo);
        attribute.addFlashAttribute(levelInfo);
        //添加提示信息
        ActionHint hint = ActionHint.create("db.basicInfo.create.hint");
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到列表页面
        return "redirect:/db/home";
	}
	
}
