/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.envers.AuditReaderFactory;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.Identifiable;
import secfox.soc.melon.persistence.JqlQueryTemplate;
import secfox.soc.melon.persistence.NamedQueryTemplate;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryConstants;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.SqlQueryTemplate;
import secfox.soc.melon.persistence.SqlResultMappingQueryTemplate;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @since 1.0 2013-4-1,上午11:06:23
 * 基于Hibernate的JPA实现
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class GenericDaoImpl<E extends Identifiable<PK>, PK extends Serializable> implements GenericDao<E, PK>  {

    private final Class<E> persistentClass;
    
    @PersistenceContext(name = QueryConstants.ENTITY_MANAGER_NAME,
    					unitName = QueryConstants.PERSIST_UNIT_NAME,
    					type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;
    
    /**
     * @param persistentClass
     */
    public GenericDaoImpl(final Class<E> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }
    
    /**
     * 根据查询模板创建查询对象
     * @param qt
     * @param clazz
     * @return
     */
    protected <R> Query createQuery(QueryTemplate qt, Class<R> clazz) {
    	Query query = null;
    	if(qt instanceof JqlQueryTemplate) {
    		if(clazz == null) {
    			query = entityManager.createQuery(qt.getQuery());
    		} else {
    			query = entityManager.createQuery(qt.getQuery(), clazz);
    		}
    	}
    	if(qt instanceof NamedQueryTemplate) {
    		if(clazz == null) {
    			query = entityManager.createNamedQuery(qt.getQuery());
    		} else {
    			query = entityManager.createNamedQuery(qt.getQuery(), clazz);
    		}
    	}
    	if(qt instanceof SqlQueryTemplate) {
    		query = entityManager.createNativeQuery(qt.getQuery());
    	}
    	if(qt instanceof SqlResultMappingQueryTemplate) {
    		SqlResultMappingQueryTemplate sqt = (SqlResultMappingQueryTemplate)qt;
    		query = entityManager.createNativeQuery(qt.getQuery(), sqt.getSqlResultMapping());
    	}
    	//拼接查询条件
        if(qt.getParameters() != null) {
            for(Entry<String, ?> paramEntry : qt.getParameters().entrySet()) {
                query.setParameter(paramEntry.getKey(), paramEntry.getValue());
            }
        }
        //构建查询缓存
        if(qt.isCachable()) {
            query.setHint("org.hibernate.cacheable", true);
            if(StringUtils.isNotBlank(qt.getCacheRegion())) {
                query.setHint("org.hibernate.cacheRegion", qt.getCacheRegion());
            } else {
                query.setHint("org.hibernate.cacheRegion", clazz.getName());
            }
        }
        //构建分页
        query.setFirstResult(qt.getFirstResult());
        query.setMaxResults(qt.getMaxResults());
     	return query;
    }
    
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#execute(secfox.soc.melon.framework.persistence.QueryTemplate)
	 */
	@Override
	public int execute(QueryTemplate qt) {
		Preconditions.checkArgument(!(qt instanceof SqlResultMappingQueryTemplate), "SqlResultMappingQueryTemplate can't execute update");
		return createQuery(qt, null).executeUpdate();
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#find(secfox.soc.melon.framework.persistence.QueryTemplate)
	 */
	@Override
	public List<Object[]> find(QueryTemplate qt) {
		Preconditions.checkArgument(!(qt instanceof SqlResultMappingQueryTemplate), "SqlResultMappingQueryTemplate can't return object[]");
		Query query = createQuery(qt, Object[].class);
		//获取返回的结果
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		//返回的结果
		List<Object[]> returnVals = Lists.newArrayListWithCapacity(results.size());
		//统一查询结果，都封装为List<Object[]>形式
		for(Object row : results) {
			if(row.getClass().isArray()) {
				returnVals.add((Object[])row);
			} else {
				//将返回的结果转换为数组
				returnVals.add(new Object[]{row});
			}
		}
		return returnVals;
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#find(secfox.soc.melon.framework.persistence.QueryTemplate, java.lang.String[])
	 */
	@Override
	public List<Map<String, Object>> find(QueryTemplate qt, final String... fileds) {
		List<Object[]> results = find(qt);
		//将数组转换为Map
		return Lists.transform(results, new Function<Object[], Map<String, Object>>() {
			
			/**
			 * 将数组转换为Map
			 */
			@Override
			public Map<String, Object> apply(Object[] arg0) {
				Map<String, Object> item = Maps.newHashMap();
				for(int i = 0; i < arg0.length; i++) {
					item.put(fileds[i], arg0[i]);
				}
				return item;
			}
			
		});
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findCount(secfox.soc.melon.framework.persistence.QueryTemplate)
	 */
	@Override
	public BigInteger findCount(QueryTemplate qt) {
		Preconditions.checkArgument(!(qt instanceof SqlResultMappingQueryTemplate), "SqlResultMappingQueryTemplate can't use for count");
		List<Object[]> results = find(qt);
		if(results.size() > 0) {
			Object count = results.get(0)[0];
			//JPA查询一般返回此结果
			if(count instanceof Long) {
				return BigInteger.valueOf((Long)count);
			}
			//原生SQL查询一般返回此结果
			if(count instanceof BigInteger) {
				return (BigInteger)count;
			}
		}
		return BigInteger.ZERO;
	}
    
    
    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#find(java.io.Serializable)
     */
    @Override
    public E find(PK pk) {
        return entityManager.find(persistentClass, pk);
    }
    
    /* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findDomains(secfox.soc.melon.framework.persistence.QueryTemplate)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> findDomains(QueryTemplate qt) {
		Preconditions.checkArgument(!(qt instanceof SqlQueryTemplate), "SqlgQueryTemplate can't find Generic domain");
		Query query = createQuery(qt, persistentClass);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findFirst(secfox.soc.melon.framework.persistence.QueryTemplate)
	 */
	@Override
	public E findFirstDomain(QueryTemplate qt) {
		Preconditions.checkArgument(!(qt instanceof SqlQueryTemplate), "SqlgQueryTemplate can't find Generic domain");
		qt.setMaxResults(1);
		List<E> results = findDomains(qt);
		if(results.size() > 0) {
			return results.get(0);
		}
		//默认返回为空
		return null;
	}
	
    
    /* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findDomainPage(secfox.soc.melon.framework.persistence.QueryType, secfox.soc.melon.framework.PageQuery, secfox.soc.melon.framework.persistence.PaginationBuilder)
	 */
	@Override
	public <S, P extends PageQuery<S>> Pagination<E> findDomainPage(QueryType qlType, PageQuery<S> queryPage, PaginationBuilder<S, P> builder) {
		Pagination<E> pagination = new Pagination<E>();
		//创建分页的总记录数
		pagination.setCount(findCountOfPages(qlType, queryPage, builder));
		QueryTemplate resultQt = buildResultOfPages(qlType, queryPage, builder);
		//设置分页返回结果
		pagination.setResults(findDomains(resultQt));
		pagination.setCurrPage(queryPage.getCurrPage());
		return pagination;
	}

	/**
	 * 构建分页查询的结果查询
	 * @param qlType
	 * @param queryPage
	 * @param builder
	 * @return
	 */
	private <S, P extends PageQuery<S>> QueryTemplate buildResultOfPages(
			QueryType qlType, PageQuery<S> queryPage,
			PaginationBuilder<S, P> builder) {
		//设置结果查询模板
		QueryTemplate resultQt = QueryTemplate.create(qlType);
		//设置分页信息
		resultQt.setFirstResult(queryPage.getFirstResult());
		resultQt.setMaxResults(queryPage.getMaxResults());
		//编译查询语句
		builder.buildSelect(resultQt);
		resultQt.append(QueryConstants.WHERE);
		builder.buildWhere(queryPage.getSearchForm(), resultQt);
		builder.buildBys(queryPage.getSortColumn(), queryPage.getOrder(), resultQt);
		return resultQt;
	}

	/**
	 * 构建分页计数的查询部分
	 * @param qlType
	 * @param queryPage
	 * @param builder
	 * @param pagination
	 */
	private <S, P extends PageQuery<S>> int findCountOfPages(
			QueryType qlType, PageQuery<S> queryPage,
			PaginationBuilder<S, P> builder) {
		//设置计数查询模板
		QueryTemplate countQt = QueryTemplate.create(qlType);
		builder.buildCount(countQt);
		//编译查询语句
		if(countQt.isEmpty()) {
			return 0;
		}
		countQt.append(QueryConstants.WHERE);
		builder.buildWhere(queryPage.getSearchForm(), countQt);
		builder.buildBys(queryPage.getSortColumn(), queryPage.getOrder(), countQt);
		//设置计数结果
		return findCount(countQt).intValue();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findArrayPage(secfox.soc.melon.framework.persistence.QueryType, secfox.soc.melon.framework.PageQuery, secfox.soc.melon.framework.persistence.PaginationBuilder)
	 */
	@Override
	public <S, P extends PageQuery<S>> Pagination<Object[]> findArrayPage(
			QueryType qlType, PageQuery<S> queryPage,
			PaginationBuilder<S, P> builder) {
		Pagination<Object[]> pagination = new Pagination<Object[]>();
		//设置查询的总记录条数
		pagination.setCount(findCountOfPages(qlType, queryPage, builder));
		//设置分页返回结果
		QueryTemplate resultQt = buildResultOfPages(qlType, queryPage, builder);
		pagination.setResults(find(resultQt));
		pagination.setCurrPage(queryPage.getCurrPage());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericDao#findMapPage(secfox.soc.melon.framework.persistence.QueryType, secfox.soc.melon.framework.PageQuery, secfox.soc.melon.framework.persistence.PaginationBuilder)
	 */
	@Override
	public <S, P extends PageQuery<S>> Pagination<Map<String, Object>> findMapPage(
			QueryType qlType, PageQuery<S> queryPage, String[] fields,
			PaginationBuilder<S, P> builder) {
		Pagination<Map<String, Object>> pagination = new Pagination<Map<String, Object>>();
		//设置查询的总记录条数
		pagination.setCount(findCountOfPages(qlType, queryPage, builder));
		//设置分页返回结果
		QueryTemplate resultQt = buildResultOfPages(qlType, queryPage, builder);
		pagination.setResults(find(resultQt, fields));
		pagination.setCurrPage(queryPage.getCurrPage());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.GenericDao#getAuditVersion(java.lang.Long, int)
	 */
	@Override
	public E getAuditVersion(Long id, int version) {
		return AuditReaderFactory.get(entityManager).find(persistentClass, id, version);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.GenericDao#getAuditVersions(java.lang.Long)
	 */
	@Override
	public List<Number> getAuditVersions(Long id) {
		return AuditReaderFactory.get(entityManager).getRevisions(persistentClass, id);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.GenericDao#getCurrentAuditision()
	 */
	@Override
	public E getCurrentAuditision() {
		return AuditReaderFactory.get(entityManager).getCurrentRevision(persistentClass, true);
	}
    
    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#getReference(java.io.Serializable)
     */
    @Override
    public E getReference(PK pk) {
        return entityManager.getReference(persistentClass, pk);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#getSession()
     */
    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#isExist(java.io.Serializable)
     */
    @Override
    public boolean isExist(PK pk) {
        return getReference(pk) != null;
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#merge(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    public E merge(E entity) {
        return entityManager.merge(entity);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#persist(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    public void persist(E entity) {
        if(!entity.hasId()) {
            entityManager.persist(entity);
        }
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#refresh(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    public void refresh(E entity) {
        if (entityManager.contains(entity)) {
            entityManager.refresh(entity);
        }
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#remove(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    public void remove(E entity) {
        if(entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            remove(entity.getId());
        }
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#remove(java.io.Serializable)
     */
    @Override
    public void remove(PK pk) {
        E entityRef = getReference(pk);
        if (entityRef != null) {
            entityManager.remove(entityRef);
        }
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#removeBatch(PK[])
     */
    @Override
    public boolean removeBatch(PK[] pks) {
        if(pks != null) {
            for(PK pk : pks) {
                remove(pk);
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericDao#removeBatch(java.util.List)
     */
    @Override
    public boolean removeBatch(Collection<E> entitys) {
        if(entitys != null) {
            for(E e : entitys) {
                remove(e);
            }
        }
        return false;
    }

}
