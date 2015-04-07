/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.datas.dao.DataShareLoggerDao;
import secfox.soc.melon.datas.domain.DataShareLogger;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 *
 * @since 1.0 2014-11-10
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Repository
public class DataShareLoggerDaoImpl extends
		GenericDaoImpl<DataShareLogger, Long> implements DataShareLoggerDao {

	/**
	 * @param persistentClass
	 */
	public DataShareLoggerDaoImpl() {
		super(DataShareLogger.class);
	}

}
