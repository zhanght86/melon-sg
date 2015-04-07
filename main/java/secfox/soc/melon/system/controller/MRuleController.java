/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.system.MRuleType;
import secfox.soc.melon.system.domain.MRule;
import secfox.soc.melon.system.query.MRulePageQuery;
import secfox.soc.melon.system.service.MRuleService;

/**
 * @since 1.0 2013-4-16,下午2:09:12
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/era/rule")
public class MRuleController{
    
    private MRuleService mRuleService;
    
    /**
     * @param mRuleService
     */
    @Inject
    public MRuleController(MRuleService mRuleService) {
        super();
        this.mRuleService = mRuleService;
    }
    
    @RequestMapping(value = "/getmRule/{data}")
    public @ResponseBody MRule getRule(@PathVariable("data") Long data){
    	MRule mRule = mRuleService.find(data);
    	return mRule;
    }
    
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/create")
    public String create(@ModelAttribute("rule") MRule mRule) {
        return "melon.system.rule.edit";
    }
    
    /**
     * 创建规则子节点
     * @param parentId
     * @param rule
     * @return
     */
    @RequestMapping(value = "/createLeaf/{parentId}", method = RequestMethod.POST)
    public @ResponseBody MRule create(@PathVariable("parentId") Long parentId, @RequestBody MRule rule) {
        if(parentId != 0L) {
            rule.setParent(mRuleService.getReference(parentId));
        }
        if(rule.hasId()){
            mRuleService.merge(rule);
        }else{
            mRuleService.persist(rule);
        }
        return rule;
    }
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/tree/{ruleId}")
    public String tree(@PathVariable("ruleId") Long ruleId,Model model) {
        MRule mr = mRuleService.find(ruleId);
        model.addAttribute("rule", mr);

        return "melon.system.rule.ruleTree";
    }
    
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/tree/{ruleId}", method = RequestMethod.POST)
    public String tree(@PathVariable("ruleId") Long ruleId, @ModelAttribute("rule") MRule mRule) {

    	mRuleService.merge(mRule);

        return "redirect:/era/rule/list";
    }
    
    /**
     * 创建规则后跳转到tree页面
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST )
    public String createRule(@ModelAttribute("rule") MRule mRule) {
        if(mRule.hasId()){
            mRuleService.merge(mRule);
        }else{
            mRule.setType(MRuleType.RULE);
            mRuleService.persist(mRule);
        }
        return "redirect:/era/rule/tree/"+mRule.getId();
    }
    /**
     * 根据规则根节点id获取规则树
     * @param pageQuery
     * @return
     */
    @RequestMapping(value="/getRoot/{ruleId}", consumes="application/json")
    public @ResponseBody MRule root(@PathVariable("ruleId") Long ruleId) {
        return mRuleService.find(ruleId);
    }
    /**
     * 修改跳转
     * @param role
     * @return
     */
    @RequestMapping(value="/update/{ruleId}", method=RequestMethod.GET)
    public String update(@PathVariable("ruleId") Long ruleId,Model model) {
        MRule rule = mRuleService.find(ruleId);
        model.addAttribute("rule", rule);
        return "melon.system.rule.edit";
    }
    /**
     * 修改规则
     * @param role
     * @return
     */
    @RequestMapping(value="/update/{ruleId}", method=RequestMethod.POST)
    public String update(@PathVariable("ruleId") Long ruleId,@ModelAttribute("rule") MRule rule,Model model) {
        mRuleService.merge(rule);
        return "redirect:/era/rule/tree/"+rule.getId();
    }
    /**
     * 删除规则
     * @param role
     * @return
     */
    @RequestMapping(value="/remove/{ruleId}", method=RequestMethod.POST)
    public  @ResponseBody Boolean remove(@PathVariable("ruleId") Long ruleId) {
        mRuleService.remove(ruleId);
        return true;
    }
    /**
     * 批量删除 
     * @return boolean true 删除成功
     */
    @RequestMapping(value = "/removeBatch", method = RequestMethod.POST)
    public @ResponseBody Boolean removeBatch(@RequestBody Long[] ids) {
    	mRuleService.removeBatch(ids);
        return true;
    }
    /**
     * 
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("pageQuery") MRulePageQuery pageQuery) {
        return "melon.system.rule.list";
    }
    
    /**
     * 
     * @param pageQuery
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public @ResponseBody Pagination<MRule> query(@ModelAttribute("pageQuery") MRulePageQuery pageQuery) {
        return mRuleService.findPages(pageQuery);
    }
    /**
     * 
     * <p>Title: getExp</p>
     * <p>Description: TODO</p>
     * <p>&emsp;(获取规则表达式)</p>
     * <p>&emsp;适用条件：TODO(可选)</p>
     * <p>&emsp;执行流程：TODO(可选)</p>
     * <p>&emsp;使用方法：TODO(可选)</p>
     * <p>&emsp;注意事项：TODO(可选)</p>
     * @param nodeId
     * @return
     */
    @RequestMapping(value="/getExp/{nodeId}",method={RequestMethod.POST})
    public @ResponseBody String getExp(@PathVariable("nodeId") Long nodeId) {
        MRule rule = mRuleService.find(nodeId);
        return  rule.buildTerm().toString();
    }
    /**
     * 
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    public String selectList(@ModelAttribute("pageQuery") MRulePageQuery pageQuery) {
        return "melon.system.rule.select";
    }
    
    /**
     * 
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    public @ResponseBody Pagination<MRule> selectQuery(@ModelAttribute("pageQuery") MRulePageQuery pageQuery) {
        return mRuleService.findPages(pageQuery);
    }
    
    @RequestMapping(value = "/underscore")
    public String underscore() {
        return "melon/era/rule/underscore";
    }
    
    /**
     * show
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	public String show(@PathVariable Long id,Model model){
    	MRule mRule = mRuleService.find(id);
	    model.addAttribute("mRule",mRule);
	    return "melon.system.rule.show";
	}
}
