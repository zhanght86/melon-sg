package secfox.soc.melon.datas.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.datas.dao.PublicInfoDao;
import secfox.soc.melon.datas.domain.PublicInfo;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

@Repository
public class PublicInfoDaoImpl extends GenericDaoImpl<PublicInfo,Long>
	implements PublicInfoDao{

	public PublicInfoDaoImpl() {
		super(PublicInfo.class);
	}

}
