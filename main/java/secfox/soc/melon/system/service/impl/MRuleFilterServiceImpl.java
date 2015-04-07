/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.JqlQueryTemplate;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.system.dao.MRuleFilterDao;
import secfox.soc.melon.system.domain.MRuleFilter;
import secfox.soc.melon.system.query.MRuleFilterPageQuery;
import secfox.soc.melon.system.service.MRuleFilterService;

/**
 * @since 1.0 2013-5-9,下午2:07:34
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Service
public class MRuleFilterServiceImpl extends GenericServiceImpl<MRuleFilter, Long> implements MRuleFilterService{

    private MRuleFilterDao dao;
    
    /**
     * @param dao
     */
    @Inject
    public MRuleFilterServiceImpl(MRuleFilterDao dao) {
        super();
        this.dao = dao;
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
     */
    @Override
    protected GenericDao<MRuleFilter, Long> getDao() {
        return dao;
    }

     
    @Override
    @Transactional(readOnly = true)
    public Long findRulesByFilterClass(String className) {
        QueryTemplate qt = new JqlQueryTemplate();
        qt.setQuery("select filter from MRuleFilter filter where filter.filterClass = :filterClass");
        qt.addParameter("filterClass", className);
        MRuleFilter filter = this.findFirstDomain(qt);
        if(filter != null) {
            return filter.getRuleId();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.core.era.service.MRuleFilterService#findPages(secfox.soc.melon.core.era.query.MRuleFilterPageQuery)
     */
    @Override
    public Pagination<MRuleFilter> findPages(MRuleFilterPageQuery pageQuery) {
        return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<MRuleFilter, MRuleFilterPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select filter from MRuleFilter filter");
			}

			@Override
			public void buildWhere(MRuleFilter s, QueryTemplate qt) {
                String name = s.getFilterName();
                //
                if(StringUtils.isNotBlank(name)) {
                    qt.append(" and filter.filterName like :name");
                    qt.addParameter("name", QueryTemplate.buildAllLike(name));
                }
                //
                String clazz = s.getFilterClass();
                if(StringUtils.isNotBlank(clazz)) {
                    qt.append(" and filter.filterClass like :clazz");
                    qt.addParameter("clazz", QueryTemplate.buildAllLike(clazz));
                }
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				if(StringUtils.isBlank(column)) {
					column = "id";
                }
                qt.append(QueryTemplate.buildOrderBy("filter", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(filter) from MRuleFilter filter");
			}
            
        });
    }
    
}
