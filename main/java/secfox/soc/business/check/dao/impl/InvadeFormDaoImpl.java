/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.business.check.dao.InvadeFormDao;
import secfox.soc.business.check.domain.Invade;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * 信息系统情况
 * @since @2014-5-21,@下午4:40:06
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version 1.0
 */
@Repository
public class InvadeFormDaoImpl extends GenericDaoImpl<Invade, Long>  implements InvadeFormDao  {
	
	public InvadeFormDaoImpl() {
		super(Invade.class);
	}

}
