package secfox.soc.melon.base.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.dao.FlowLayoutDao;
import secfox.soc.melon.base.domain.FlowLayout;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * 
 * @author 熊超
 * 2014-10-28
 * @version 1.0
 */
@Repository
public class FlowLayoutDaoImpl extends GenericDaoImpl<FlowLayout, Long> implements FlowLayoutDao{

	public FlowLayoutDaoImpl() {
		super(FlowLayout.class);
	}

}
