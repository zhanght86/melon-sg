/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.business.db.dao.DbLevelInfoDao;
import secfox.soc.business.db.domain.DbLevelInfo;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 *
 * @since 1.0 2014-10-27
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Repository
public class DbLevelInfoDaoImpl extends GenericDaoImpl<DbLevelInfo, Long>
		implements DbLevelInfoDao {

	/**
	 * @param persistentClass
	 */
	public DbLevelInfoDaoImpl() {
		super(DbLevelInfo.class);
	}

}
