/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import java.io.Serializable;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.system.domain.MRule;
import secfox.soc.melon.system.query.MRulePageQuery;

/**
 * @since 1.0 2013-4-15,上午10:48:02
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface MRuleService extends GenericService<MRule, Long> {
    
    /**
     * 获取编译过的规则
     * @param id
     * @return
     */
    Serializable getCompiledRuleById(Long id);
    
    /**
     * 查找分页
     * @return
     */
    Pagination<MRule> findPages(MRulePageQuery pageQuery);

    public abstract Serializable getCompiledRule(MRule rule);
}
