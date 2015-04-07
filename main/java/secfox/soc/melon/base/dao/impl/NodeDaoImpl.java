package secfox.soc.melon.base.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.dao.NodeDao;
import secfox.soc.melon.base.domain.Node;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * 
 * @author 熊超
 * 2014-10-28
 * @version 1.0
 */
@Repository
public class NodeDaoImpl extends GenericDaoImpl<Node, Long> implements NodeDao {

	public NodeDaoImpl() {
		super(Node.class);
	}

}
