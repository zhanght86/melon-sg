/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.system.domain.MRuleFilter;

/**
 * @since 1.0 2013-5-9,下午2:36:43
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class MRuleFilterPageQuery extends PageQuery<MRuleFilter> {

    private static final long serialVersionUID = 2976344319020688003L;

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.PageQuery#initSearchForm()
     */
    @Override
    protected MRuleFilter initSearchForm() {
        MRuleFilter filter = new MRuleFilter();
        return filter;
    }
    
}
