/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service.impl;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.optimizers.OptimizerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.system.MRuleType;
import secfox.soc.melon.system.dao.MRuleDao;
import secfox.soc.melon.system.domain.MRule;
import secfox.soc.melon.system.query.MRulePageQuery;
import secfox.soc.melon.system.service.MRuleService;

/**
 * @since 1.0 2013-4-15,上午10:48:52
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Service
public class MRuleServiceImpl extends GenericServiceImpl<MRule, Long> implements MRuleService{

    private MRuleDao dao;
    
    private final ParserContext context;
    
    /**
     * @param dao
     */
    @Inject
    public MRuleServiceImpl(MRuleDao dao) {
        super();
        this.dao = dao;
        context = new ParserContext();
    }
    
    @PostConstruct
    public void init() {
        OptimizerFactory.setDefaultOptimizer("ASM");
        //添加执行语句需要添加的方法与类
        //context.addImport("DTUtils", DateTimeUtils.class);
    }
    
    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
     */
    @Override
    protected GenericDao<MRule, Long> getDao() {
        return dao;
    }
    
    /* (non-Javadoc)
     * @see secfox.soc.melon.core.system.service.MRuleService#getCompiledRuleById(java.lang.Long)
     */
    @Override
    @Cacheable(value="ruleCache", key="#id")
    public Serializable getCompiledRuleById(Long id) {
        MRule rule = find(id);
        return MVEL.compileExpression(rule.buildTerm().toString(), context);
    }
    
    @Override
    public Serializable getCompiledRule(MRule rule) {
        return MVEL.compileExpression(rule.buildTerm().toString(), context);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.hibernate.GenericServiceImpl#merge(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    @Transactional(readOnly = false)
    public MRule merge(MRule entity) {
        return dao.merge(entity);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.hibernate.GenericServiceImpl#remove(java.io.Serializable)
     */
    @Override
    @Transactional(readOnly = false)
    public void remove(Long pk) {
    	dao.remove(pk);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.hibernate.GenericServiceImpl#remove(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    @Transactional(readOnly = false)
    public void remove(MRule entity) {
    	dao.remove(entity);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.core.era.service.MRuleService#findPages()
     */
    @Override
    public Pagination<MRule> findPages(MRulePageQuery pageQuery) {
        return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<MRule, MRulePageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select mRule from MRule mRule");
			}

			@Override
			public void buildWhere(MRule s, QueryTemplate qt) {
                //首先添加Type类型
                qt.append(" and mRule.type = :type");
                qt.addParameter("type", MRuleType.RULE);
                //
                String desc = s.getDesc();
                if(StringUtils.isNotBlank(desc)) {
                    qt.append(" and mRule.desc like :desc");
                    qt.addParameter("desc", QueryTemplate.buildAllLike(desc));
                }
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				if(StringUtils.isBlank(column)) {
                    column = "id";
                }
				
                qt.append(QueryTemplate.buildOrderBy("mRule", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(mRule) from MRule mRule");
			}});
    }
    
    
}
