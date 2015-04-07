/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.business.db.dao.DbLevelInfoDao;
import secfox.soc.business.db.domain.DbLevelInfo;
import secfox.soc.business.db.service.DbLevelInfoService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 *
 * @since 1.0 2014-10-27
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Service
public class DbLevelInfoServiceImpl extends	GenericServiceImpl<DbLevelInfo, Long> implements DbLevelInfoService {
	
	private DbLevelInfoDao dbLevelInfoDao;
	
	@Inject
	public DbLevelInfoServiceImpl(DbLevelInfoDao dbLevelInfoDao){
		this.dbLevelInfoDao = dbLevelInfoDao;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<DbLevelInfo, Long> getDao() {
		return dbLevelInfoDao;
	}

}
