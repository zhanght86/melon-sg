/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.field.controller;

import java.util.List;
import java.util.Map;

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

import secfox.soc.melon.asset.field.domain.AssetField;
import secfox.soc.melon.asset.field.domain.AssetFieldValue;
import secfox.soc.melon.asset.field.service.AssetFieldService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

/**
 * @since 1.0 2014年10月21日,上午11:02:24
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/asset/field")
public class AssetFieldController {
	
	private AssetFieldService assetFieldService;
	
	@Inject
	public AssetFieldController(AssetFieldService assetFieldService) {
		super();
		this.assetFieldService = assetFieldService;
		
	}
	
	/**
	 * 创建新的资产属性
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		AssetField field = new AssetField();
		field.setDeviceType(id);
		model.addAttribute("field", field);
		return "melon/asset/field/createField";
	}
	
	/**
	 * 属性保存
	 * @param field
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
	public @ResponseBody ActionHint create(@Valid@ModelAttribute("field") AssetField field, BindingResult result) {
		ActionHint actionHint = ActionHint.create("asset.assetField.create.hint", field.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		//保存业务对象
		assetFieldService.persist(field);
		//添加操作协助提示(本处因为树性操作结构)，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(field);
		return actionHint;
	}
	
	/**
	 * 
	 * @param id 资产类型ID
	 * @return
	 */
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public String list(@PathVariable("id") Long id, Model model) {
		List<AssetField> fields = assetFieldService.findFields(id);
		model.addAttribute("fields", fields);
		return "melon/asset/field/list";
	}
	
	/**
	 * 载入资产类型的动态属性
	 * @param id 资产类型的主键
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String load(HttpServletRequest request, Model model) {
		String typeId = request.getParameter("typeId");
		List<AssetField> fields = null;
		if(StringUtils.isNotBlank(typeId)) {
			fields = assetFieldService.findFields(Long.parseLong(typeId));
		} else {
			String deviceId = request.getParameter("deviceId");
			fields = assetFieldService.findFieldsWithValue(Long.parseLong(deviceId));
		}
		
		model.addAttribute("fields", fields);
		return "melon/asset/field/load";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public void saveOrUpdate(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> results = request.getParameterMap();
		List<AssetFieldValue> fields = AssetFieldValue.extractAssetField(100L, results);
		assetFieldService.saveOrUpdate(fields);
		System.out.println(fields);
	}
	/**
	 * 预览资产属性
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/preview/{id}", method = RequestMethod.GET)
	public String preview(@PathVariable("id") Long id, Model model) {
		List<AssetField> fields = assetFieldService.findFields(id);
		model.addAttribute("fields", fields);
		return "melon/asset/field/previewField";
	}
	
	/**
	 * 删除之后刷新数据
	 * @param id
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		assetFieldService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 动态属性维护树显示页面
	 * @return
	 */
	@RequestMapping(value="/tree", method = RequestMethod.GET)
	public String tree() {
		return "asset.field.tree";
	}
	
	/**
	 * 更新资产属性
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		AssetField field = assetFieldService.find(id);
		model.addAttribute("field", field);
		return "melon/asset/field/createField";
	}
	
	/**
	 * 属性保存
	 * @param field
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public @ResponseBody ActionHint update(@Valid@ModelAttribute("field") AssetField field, BindingResult result) {
		ActionHint actionHint = ActionHint.create("asset.assetField.create.hint", field.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		//保存业务对象
		assetFieldService.merge(field);
		//添加操作协助提示(本处因为树性操作结构)，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(field);
		return actionHint;
	}
	
}
