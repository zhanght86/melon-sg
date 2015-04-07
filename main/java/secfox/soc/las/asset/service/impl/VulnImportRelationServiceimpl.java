package secfox.soc.las.asset.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.data.audit.risk.DeviceVulnModel;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.bean.T_VulnImportRelationBean;
import secfox.soc.las.asset.dao.VulnImportRelationDao;
import secfox.soc.las.asset.service.VulnImportRelationService;
import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

@Service
public class VulnImportRelationServiceimpl extends GenericServiceImpl<T_VulnImportRelationBean,Long> implements VulnImportRelationService {
	private VulnImportRelationDao dao;
	@Override
	protected GenericDao<T_VulnImportRelationBean, Long> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}
	@Inject
	public VulnImportRelationServiceimpl(VulnImportRelationDao dao){
		this.dao = dao;
	}
	@Override
	@Transactional
	public ArrayList<DeviceVulnModel> getVulnListByDevID(long devid,
			long importTime) {
		// TODO Auto-generated method stub
		return dao.getVulnListByDevID(devid, importTime);
	}
	@Override
	@Transactional
	public void addVulnImportRelation(T_VulnImportRelationBean model) {
		// TODO Auto-generated method stub
		dao.addVulnImportRelation(model);
	}
	@Override
	public int[] getImportInfo(long id) {
		// TODO Auto-generated method stub
		return dao.getImportInfo(id);
	}

}
