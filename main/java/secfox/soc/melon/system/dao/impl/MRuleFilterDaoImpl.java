/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.system.dao.MRuleFilterDao;
import secfox.soc.melon.system.domain.MRuleFilter;

/**
 * @since 1.0 2013-5-9,下午2:05:47
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Repository
public class MRuleFilterDaoImpl extends GenericDaoImpl<MRuleFilter, Long> implements MRuleFilterDao{

    /**
     * @param persistentClass
     */
    public MRuleFilterDaoImpl() {
        super(MRuleFilter.class);
    }
    
}
