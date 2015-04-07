/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.system.dao.MRuleDao;
import secfox.soc.melon.system.domain.MRule;

/**
 * @since 1.0 2013-4-15,上午10:39:51
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Repository
public class MRuleDaoImpl extends GenericDaoImpl<MRule, Long> implements MRuleDao{

    /**
     * @param persistentClass
     */
    public MRuleDaoImpl() {
        super(MRule.class);
    }
    
}
