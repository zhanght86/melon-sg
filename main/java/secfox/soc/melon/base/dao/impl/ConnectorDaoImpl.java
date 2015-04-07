package secfox.soc.melon.base.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.dao.ConnectorDao;
import secfox.soc.melon.base.domain.Connector;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * 
 * @author 熊超
 * 2014-10-29
 * @version 1.0
 */
@Repository
public class ConnectorDaoImpl extends GenericDaoImpl<Connector, Long> implements ConnectorDao {

	public ConnectorDaoImpl() {
		super(Connector.class);
	}

}
