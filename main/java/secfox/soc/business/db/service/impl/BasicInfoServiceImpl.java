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

import secfox.soc.business.db.dao.BasicInfoDao;
import secfox.soc.business.db.domain.BasicInfo;
import secfox.soc.business.db.service.BasicInfoService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 *
 * @since 1.0 2014-10-20 下午4:06:49
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Service
public class BasicInfoServiceImpl extends GenericServiceImpl<BasicInfo, Long> implements
		BasicInfoService {
	
	private BasicInfoDao basicInfoDao;
	
	@Inject
	public BasicInfoServiceImpl(BasicInfoDao basicInfoDao){
		this.basicInfoDao = basicInfoDao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<BasicInfo, Long> getDao() {
		return basicInfoDao;
	}
}
