package secfox.soc.melon.datas.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.datas.dao.PublicInfoDao;
import secfox.soc.melon.datas.domain.PublicInfo;
import secfox.soc.melon.datas.query.PublicInfoPageQuery;
import secfox.soc.melon.datas.query.PublicInfoSearchForm;
import secfox.soc.melon.datas.service.PublicInfoService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.JqlQueryTemplate;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

@Service
public class PublicInfoServiceImpl extends GenericServiceImpl<PublicInfo,Long>
	implements PublicInfoService{

	private PublicInfoDao dao;

	@Inject
	public PublicInfoServiceImpl(PublicInfoDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Pagination<PublicInfo> findPages(PublicInfoPageQuery query) {
		// TODO Auto-generated method stub
		return findDomainPage(QueryType.JQL, query, new PaginationBuilder<PublicInfoSearchForm,PublicInfoPageQuery>(){

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append(" select pb from PublicInfo pb ");
			}

			@Override
			public void buildWhere(PublicInfoSearchForm s, QueryTemplate qt) {
				String title = s.getTitle();
				if(StringUtils.isNotBlank(title)){
					qt.append(" and pb.title like :title ");
					qt.addParameter("title", QueryTemplate.buildAllLike(title));
				}
				int type = s.getType();
				qt.append(" and pb.type = :type ");
				qt.addParameter("type", type);
				Long order = s.getOrder();
				if(order!=null) {
					qt.append(" and pb.order = :order ");
					qt.addParameter("order", order);
				}
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				if(StringUtils.isBlank(column)){
					column = "createTime";
				}
				qt.append(QueryTemplate.buildOrderBy("pb", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append(" select count(pb) from PublicInfo pb ");
				
			}});
	}

	@Override
	protected GenericDao<PublicInfo, Long> getDao() {
		return this.dao;
	}

	@Override
	public List<PublicInfo> findPublicInfoByType(int type,int maxResult) {
		QueryTemplate qt = new JqlQueryTemplate("select p from PublicInfo p where p.type = :type and p.state = 2 order by p.createTime desc");
		qt.addParameter("type", type);
		//maxResult为0时查询所有的
		if(maxResult != 0){
			qt.setMaxResults(maxResult);
		}
		return dao.findDomains(qt);
	}
}
