package secfox.soc.melon.base.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.base.dao.NodeDao;
import secfox.soc.melon.base.domain.Node;
import secfox.soc.melon.base.service.NodeService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

@Service
public class NodeServiceImpl extends GenericServiceImpl<Node, Long> implements NodeService {
	
	private NodeDao nodeDao;
	
	@Inject 
	public NodeServiceImpl(NodeDao nodeDao){
		super();
		this.nodeDao = nodeDao;
	}
	
	@Override
	protected GenericDao<Node, Long> getDao() {
		return nodeDao;
	}

}
