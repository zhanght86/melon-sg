package secfox.soc.melon.datas.service;

import java.util.List;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.datas.domain.PublicInfo;
import secfox.soc.melon.datas.query.PublicInfoPageQuery;
import secfox.soc.melon.persistence.GenericService;

public interface PublicInfoService extends GenericService<PublicInfo,Long> {
	Pagination<PublicInfo> findPages(PublicInfoPageQuery query);
	
	/**
	 * 根据类型查找已发布的发文
	 */
	List<PublicInfo> findPublicInfoByType(int type,int maxResult);

}
