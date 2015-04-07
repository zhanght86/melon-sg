/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.system.domain.MRule;

/**
 * @since 1.0 2013-5-7,下午2:06:45
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class MRulePageQuery extends PageQuery<MRule> {

    private static final long serialVersionUID = 4322732682374728563L;

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.PageQuery#initSearchForm()
     */
    @Override
    protected MRule initSearchForm() {
        MRule rule = new MRule();
        //
        return rule;
    }
    
}
