package secfox.soc.melon.base.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.base.dao.ConnectorDao;
import secfox.soc.melon.base.domain.Connector;
import secfox.soc.melon.base.service.ConnectorService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * 
 * @author 熊超
 * 2014-10-29
 * @version 1.0
 */
@Service
public class ConnectorServiceImpl extends GenericServiceImpl<Connector, Long> implements ConnectorService {

	private ConnectorDao connectorDao;
	
	@Inject
	public ConnectorServiceImpl(ConnectorDao connectorDao){
		super();
		this.connectorDao = connectorDao;
	}
	
	@Override
	protected GenericDao<Connector, Long> getDao() {
		return connectorDao;
	}

}
