/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.dao.GroupRelationDao;
import secfox.soc.melon.asset.domain.GroupRelation;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since @2014-10-20,@下午8:24:02
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Repository
public class GroupRelationDaoImpl extends GenericDaoImpl<GroupRelation, Long> implements GroupRelationDao {

	public GroupRelationDaoImpl() {
		super(GroupRelation.class);
	}

}


