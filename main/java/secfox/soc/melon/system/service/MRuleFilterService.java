/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.system.domain.MRuleFilter;
import secfox.soc.melon.system.query.MRuleFilterPageQuery;

/**
 * @since 1.0 2013-5-9,下午2:07:00
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface MRuleFilterService extends GenericService<MRuleFilter, Long> {
    
    /**
     * 
     * @param className
     * @return 规则ID
     */
	Long findRulesByFilterClass(String className);
    
    /**
     * 
     * @param pageQuery
     * @return
     */
    Pagination<MRuleFilter> findPages(MRuleFilterPageQuery pageQuery);
}
