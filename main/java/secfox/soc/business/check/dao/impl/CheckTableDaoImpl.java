/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.business.check.dao.CheckTableDao;
import secfox.soc.business.check.domain.CheckTable;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
*
* @since 1.0 2014年10月14日
* @author <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
* @version 1.0
*/
@Repository
public class CheckTableDaoImpl extends GenericDaoImpl<CheckTable, Long> implements
		CheckTableDao {

	public CheckTableDaoImpl() {
		super(CheckTable.class);
	}

}
