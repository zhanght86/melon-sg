package secfox.soc.las.asset.dao.impl;


import org.springframework.stereotype.Repository;

import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.dao.VulnImportDao;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
@Repository
public class VulnImportDaoimpl extends GenericDaoImpl<T_VulnImport,Long> implements VulnImportDao{

	public VulnImportDaoimpl() {
		super(T_VulnImport.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addVulnImport(T_VulnImport obj) {
		// TODO Auto-generated method stub
		super.persist(obj);
	}
}
