package secfox.soc.melon.datas.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.datas.dao.ArticleDao;
import secfox.soc.melon.datas.domain.Article;
import secfox.soc.melon.datas.domain.ArticleReceiver;
import secfox.soc.melon.datas.query.ArticlePageQuery;
import secfox.soc.melon.datas.query.ArticleSearchForm;
import secfox.soc.melon.datas.service.ArticleReceiverService;
import secfox.soc.melon.datas.service.ArticleService;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.NamedQueryTemplate;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
/**
 * 下发以及上传
 * @author 11
 *
 */
@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article,Long> 
	implements ArticleService{
	
	private ArticleDao dao;
	private ArticleReceiverService arreService;
	private OrganizationService orgService;
	
	
	@Inject
	public ArticleServiceImpl(ArticleDao dao,ArticleReceiverService arreService
			,OrganizationService orgService) {
		super();
		this.dao = dao;
		this.arreService = arreService;
		this.orgService = orgService;
	}

	@Override
	protected GenericDao<Article, Long> getDao() {
		return dao;
	}
	
	@Override
	@Transactional(readOnly=false)
	public void persist(Article article){
		super.persist(article);
		int type = article.getType();
		if(type==0){
			String receiverIds = article.getReceiverIds();
			if(StringUtils.isNotBlank(receiverIds)){
				if(",".indexOf(receiverIds)!=-1){
					receiverIds = receiverIds.substring(0,receiverIds.length()-1);
					String[] receiverIdsArray = receiverIds.split(",");
					if(receiverIdsArray!=null){
						for(String tempId:receiverIdsArray){
							ArticleReceiver receiver = new ArticleReceiver();
							UserInfo userInfo = new UserInfo();
							userInfo.setOrganId(Long.parseLong(tempId));
							receiver.setReceiver(userInfo);
							receiver.setArticle(article);
							arreService.persist(receiver);
						}
					}
				}else{
					ArticleReceiver receiver = new ArticleReceiver();
					UserInfo userInfo = new UserInfo();
					userInfo.setOrganId(Long.parseLong(receiverIds));
					receiver.setReceiver(userInfo);
					receiver.setArticle(article);
					arreService.persist(receiver);
				}
				
				
			}else{
				QueryTemplate qt = new NamedQueryTemplate("organization.findAll");
				List<Organization> orgList = orgService.findDomains(qt);
				for(Organization org:orgList){
					ArticleReceiver receiver = new ArticleReceiver();
					UserInfo userInfo = new UserInfo();
					userInfo.setOrganId(org.getId());
					receiver.setReceiver(userInfo);
					receiver.setArticle(article);
					arreService.persist(receiver);
				}
			}
		}
	}

	@Override
	public Pagination<Article> findPages(ArticlePageQuery quey) {
		
		return findDomainPage(QueryType.JQL, quey, new PaginationBuilder<ArticleSearchForm, ArticlePageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append(" select a from Article a ");
			}

			@Override
			public void buildWhere(ArticleSearchForm s, QueryTemplate qt) {
				String title = s.getTitle();
				if(StringUtils.isNotBlank(title)){
					qt.append(" and a.title like :title ");
					qt.addParameter("title", QueryTemplate.buildAllLike(title));
				}
				int type = s.getType();
				Long organId = s.getOrganId();
				String duty = s.getDuty();//岗位
				qt.append(" and a.type = :type ");
				qt.addParameter("type", type);
				if(StringUtils.isNotBlank(duty)&&!duty.equals("ADMIN")){
					qt.append(" and a.creator.organId = :organId ");
					qt.addParameter("organId",organId);	
				}
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				if(StringUtils.isBlank(column)){
					column = "createTime";
				}
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append(" select count(a) from Article a  ");
			}
		}); 
	}
}
