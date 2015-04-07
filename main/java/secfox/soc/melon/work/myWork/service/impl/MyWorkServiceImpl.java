package secfox.soc.melon.work.myWork.service.impl;


import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.work.myWork.dao.MyWorkDao;
import secfox.soc.melon.work.myWork.domain.MyWork;
import secfox.soc.melon.work.myWork.query.WorkPageQuery;
import secfox.soc.melon.work.myWork.service.MyWorkService;

/**
 * @author 曹贞杰
 *
 * 2014年11月20日
 * @version 1.0
 */
@Service
public class MyWorkServiceImpl extends GenericServiceImpl<MyWork, Long> implements MyWorkService {
	
	private MyWorkDao myWorkDao;
	
	@Inject 
	public MyWorkServiceImpl(MyWorkDao myWorkDao){
		super();
		this.myWorkDao = myWorkDao;
	}
	
	@Override
	protected GenericDao<MyWork, Long> getDao() {
		return this.myWorkDao;
	}

	@Override
	public Pagination<MyWork> findPage(WorkPageQuery pageQuery) {
		
		return findDomainPage(QueryType.JQL, pageQuery,
				new PaginationBuilder<MyWork, WorkPageQuery>() {

					@Override
					public void buildSelect(QueryTemplate qt) {
						qt.append("select a from MyWork a ");
						
					}

					@Override
					public void buildWhere(MyWork s, QueryTemplate qt) {
						/*//此条件用于实现对比功能
						if(s.getId() != null){
							qt.append(" and a.id not in (:id) ");
							qt.addParameter("id", s.getId());
						}*/
						//按标题查询
						if (StringUtils.isNotBlank(s.getTitle())) {
							qt.append(" and a.title like :title ");
							qt.addParameter("title",QueryTemplate.buildAllLike(StringUtils.strip(s.getTitle())));
						}
												
					}

					@Override
					public void buildBys(String column, SortOrder order,
							QueryTemplate qt) {
						qt.append(QueryTemplate.buildOrderBy("a", column, order));
						
					}

					@Override
					public void buildCount(QueryTemplate qt) {
						qt.append("select count(a.id) from MyWork a ");
						
					}
					
				});
	}

	@Override
	public List<MyWork> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"myWork.findAll");
		return findDomains(qt);
	}

	@Override
	public List<MyWork> findByWork(Long organId) {
		//根据单位查信息系统
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "myWork.findByMyWork");
		qt.addParameter("organId", organId);
		return findDomains(qt);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.work.myWork.service.MyWorkService#findByAcc(java.lang.Long)
	 */
	@Override
	public List<MyWork> findUndo(Long id) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "mywork.findUndo");
		qt.addParameter("userId", QueryTemplate.buildAllLike(String.valueOf(id)+","));
		return findDomains(qt);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.work.myWork.service.MyWorkService#findPassed(java.lang.Long)
	 */
	@Override
	public List<MyWork> findPassed(Long id) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "mywork.findPassed");
		qt.addParameter("userId", QueryTemplate.buildAllLike(String.valueOf(id)+","));
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.work.myWork.service.MyWorkService#changeState(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=false)
	public MyWork changeState(Long id) {
		MyWork work = getReference(id);
		work.setCompleteState(3);
		return merge(work);
	}

}
