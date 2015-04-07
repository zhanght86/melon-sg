/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.system.dao.LoggerDao;
import secfox.soc.melon.system.domain.Logger;

/**
 *
 * @since 1.0 2014-10-8 下午8:27:22
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Repository
public class LoggerDaoImpl extends GenericDaoImpl<Logger, Long> implements LoggerDao {

	public LoggerDaoImpl() {
		super(Logger.class);
	}
	
}
