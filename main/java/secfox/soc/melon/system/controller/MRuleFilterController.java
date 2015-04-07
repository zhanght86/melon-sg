/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.system.domain.MRule;
import secfox.soc.melon.system.domain.MRuleFilter;
import secfox.soc.melon.system.query.MRuleFilterForm;
import secfox.soc.melon.system.query.MRuleFilterPageQuery;
import secfox.soc.melon.system.service.MRuleFilterService;
import secfox.soc.melon.system.service.MRuleService;

/**
 * @since 1.0 2013-5-9,下午2:10:54
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/era/filter")
public class MRuleFilterController{
    
    private MRuleFilterService filterService;
    
    private MRuleService ruleService;
    
    /**
     * @param filterService
     */
    @Inject
    public MRuleFilterController(MRuleFilterService filterService,MRuleService ruleService) {
        super();
        this.filterService = filterService;
        this.ruleService = ruleService;
    }
    
    
   
    /**
     * 
     * @param filter
     * @return
     */
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String create(@ModelAttribute("filter") MRuleFilterForm filter) {
        return "melon.system.filter.edit";
    }
    
    /**
     * 
     * @param filter
     * @param result
     * @param redirectAttr
     * @return
     */
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String create(@Valid MRuleFilterForm filter, BindingResult result, RedirectAttributes redirectAttr){
        /*if(result.hasErrors()) {
            return getMapping("edit");
        }*/
        MRuleFilter f = new MRuleFilter();
        List<MRule> rules = new ArrayList<MRule>();
        MRule rule = ruleService.find(filter.getRuleId());
        rules.add(rule);
        f.setDesc(filter.getDesc());
        f.setEnabled(filter.isEnabled());
        f.setFilterClass(filter.getFilterClass());
        f.setFilterEntity(filter.getFilterEntity());
        f.setFilterName(filter.getFilterName());
        f.setRuleId(filter.getRuleId());
        filterService.persist(f);
        redirectAttr.addFlashAttribute("filter", f);
        return "redirect:/era/filter/show/" + f.getId();
    }

    /**
     * 
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("pageQuery") MRuleFilterPageQuery pageQuery) {
        return  "melon.system.filter.list";
    }
    
    /**
     * 
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody Pagination<MRuleFilter> query(@ModelAttribute("pageQuery") MRuleFilterPageQuery pageQuery) {
        return filterService.findPages(pageQuery);
    }
    
    /**
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id,  Model model) {
        MRuleFilter filter = (MRuleFilter)model.asMap().get("filter");
        if(filter == null) {
            filter = filterService.find(id);
        }
        MRuleFilterForm f = new MRuleFilterForm();
        f.setId(filter.getId());
        f.setDesc(filter.getDesc());
        f.setEnabled(filter.isEnabled());
        f.setFilterClass(filter.getFilterClass());
        f.setFilterEntity(filter.getFilterEntity());
        f.setFilterName(filter.getFilterName());
        f.setRuleId((filter.getRuleId()));
        long ruleId = filter.getRuleId();
        MRule rule = ruleService.find(ruleId);
        f.setRuleName(rule.getName());
        model.addAttribute("filter", f);
        return "melon.system.filter.show";
    }
    
    /**
     * 删除规则
     * @param role
     * @return
     */
    @RequestMapping(value="/remove/{ruleId}", method=RequestMethod.POST)
    public  @ResponseBody Boolean remove(@PathVariable("ruleId") Long ruleId) {
    	filterService.remove(ruleId);
        return true;
    }
    /**
     * 批量删除 
     * @return boolean true 删除成功
     */
    @RequestMapping(value = "/removeBatch", method = RequestMethod.POST)
    public @ResponseBody Boolean removeBatch(@RequestBody Long[] ids) {
    	filterService.removeBatch(ids);
        return true;
    }
    /**
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id,  Model model) {
        MRuleFilter filter = filterService.find(id);
        MRuleFilterForm f = new MRuleFilterForm();
        f.setId(filter.getId());
        f.setDesc(filter.getDesc());
        f.setEnabled(filter.isEnabled());
        f.setFilterClass(filter.getFilterClass());
        f.setFilterEntity(filter.getFilterEntity());
        f.setFilterName(filter.getFilterName());
        MRule rule = ruleService.find(filter.getRuleId());
        String desc = rule.getDesc();
        model.addAttribute("desc", desc);
        model.addAttribute("filter", f);
        return "melon.system.filter.edit";
    }
    
    /**
     * 
     * @param id
     * @param filter
     * @param result
     * @param redirectAttr
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id,  @Valid MRuleFilterForm filter, BindingResult result, RedirectAttributes redirectAttr,Model model) {
        /*if(result.hasErrors()) {
            return getMapping("edit");
        }*/
        MRuleFilter f = new MRuleFilter();
        f.setId(filter.getId());
        f.setDesc(filter.getDesc());
        f.setEnabled(filter.isEnabled());
        f.setFilterClass(filter.getFilterClass());
        f.setFilterEntity(filter.getFilterEntity());
        f.setFilterName(filter.getFilterName());
        f.setRuleId(filter.getRuleId());
        
        filterService.merge(f);
        redirectAttr.addFlashAttribute("filter", f);
        return "redirect:/era/filter/show/" + f.getId();
    }
    
    
}
