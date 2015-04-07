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

import secfox.soc.business.db.dao.BasicInfoDao;
import secfox.soc.business.db.domain.BasicInfo;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 *
 * @since 1.0 2014-10-20 下午4:00:56
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Repository
public class BasicInfoDaoImpl extends GenericDaoImpl<BasicInfo, Long> implements
		BasicInfoDao {

	/**
	 * @param persistentClass
	 */
	public BasicInfoDaoImpl() {
		super(BasicInfo.class);
	}
}
