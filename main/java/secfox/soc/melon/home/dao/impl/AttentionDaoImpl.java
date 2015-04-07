/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.home.dao.AttentionDao;
import secfox.soc.melon.home.domain.Attention;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 1.0 2014年10月21日,下午4:09:54
 * 关注的数据访问实现类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Repository
public class AttentionDaoImpl extends GenericDaoImpl<Attention, Long> implements AttentionDao{

	/**
	 * @param persistentClass
	 */
	public AttentionDaoImpl() {
		super(Attention.class);
	}

}
