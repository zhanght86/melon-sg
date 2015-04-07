/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.las.dao.LasRuleDao;
import secfox.soc.las.domain.LasRule;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 2015-1-21,上午10:24:53
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class LasRuleDaoImpl extends GenericDaoImpl<LasRule, Long> implements
	LasRuleDao {
	
	public LasRuleDaoImpl() {
		super(LasRule.class);
	}

}
