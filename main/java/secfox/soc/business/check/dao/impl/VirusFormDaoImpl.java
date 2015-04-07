/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.business.check.dao.VirusFormDao;
import secfox.soc.business.check.domain.Virus;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * 信息系统情况
 * @since @2014-5-21,@下午4:40:06
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version 1.0
 */
@Repository
public class VirusFormDaoImpl extends GenericDaoImpl<Virus, Long>  implements VirusFormDao  {
	
	public VirusFormDaoImpl() {
		super(Virus.class);
	}

}
