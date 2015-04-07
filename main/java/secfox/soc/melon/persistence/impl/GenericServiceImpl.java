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

import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.persistence.Identifiable;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.QueryTemplate;

/**
 * @since 1.0 2013-4-1,上午11:11:29
 * 泛型服务的实现类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
//@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public abstract class GenericServiceImpl<E extends Identifiable<PK>, PK extends Serializable> implements GenericService<E, PK> {
    
    protected abstract GenericDao<E, PK> getDao();
    
    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#find(java.io.Serializable)
     */
    @Override
    @Transactional(readOnly = true)
    public E find(PK pk) {
        return getDao().find(pk);
    }
    
    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#find(java.io.Serializable)
     */
    @Override
    @Transactional(readOnly = true)
	public List<Object[]> find(QueryTemplate qt) {
		return getDao().find(qt);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> find(QueryTemplate qt, String... fileds) {
		return getDao().find(qt, fileds);
	}

	@Override
	@Transactional(readOnly = true)
	public BigInteger findCount(QueryTemplate qt) {
		return getDao().findCount(qt);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<E> findDomains(QueryTemplate qt) {
		return getDao().findDomains(qt);
	}

	@Override
	public E findFirstDomain(QueryTemplate qt) {
		return getDao().findFirstDomain(qt);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericService#findDomainPage(secfox.soc.melon.framework.persistence.QueryType, secfox.soc.melon.framework.PageQuery, secfox.soc.melon.framework.persistence.PaginationBuilder)
	 */
	@Override
	@Transactional(readOnly = true)
	public <S, P extends PageQuery<S>> Pagination<E> findDomainPage(
			QueryType qlType, PageQuery<S> queryPage,
			PaginationBuilder<S, P> builder) {
		return getDao().findDomainPage(qlType, queryPage, builder);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericService#findArrayPage(secfox.soc.melon.framework.persistence.QueryType, secfox.soc.melon.framework.PageQuery, secfox.soc.melon.framework.persistence.PaginationBuilder)
	 */
	@Override
	@Transactional(readOnly = true)
	public <S, P extends PageQuery<S>> Pagination<Object[]> findArrayPage(
			QueryType qlType, PageQuery<S> queryPage,
			PaginationBuilder<S, P> builder) {
		return getDao().findArrayPage(qlType, queryPage, builder);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.GenericService#findMapPage(secfox.soc.melon.framework.persistence.QueryType, secfox.soc.melon.framework.PageQuery, java.lang.String[], secfox.soc.melon.framework.persistence.PaginationBuilder)
	 */
	@Override
	@Transactional(readOnly = true)
	public <S, P extends PageQuery<S>> Pagination<Map<String, Object>> findMapPage(
			QueryType qlType, PageQuery<S> queryPage, String[] fields,
			PaginationBuilder<S, P> builder) {
		return getDao().findMapPage(qlType, queryPage, fields, builder);
	}

	/* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#getReference(secfox.soc.melon.framework.persistence.QueryTemplate)
     */
    @Override
    @Transactional(readOnly = true)
    public E getReference(PK pk) {
        return getDao().getReference(pk);
    }
    
    /* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.GenericService#getAuditVersion(java.lang.Long, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public E getAuditVersion(Long id, int version) {
		return getDao().getAuditVersion(id, version);
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.GenericService#getAuditVersions(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Number> getAuditVersions(Long id) {
		return getDao().getAuditVersions(id);
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.GenericService#getCurrentAuditision()
	 */
	@Override
	@Transactional(readOnly = true)
	public E getCurrentAuditision() {
		return getDao().getCurrentAuditision();
	}
	
	  /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#isExist(java.io.Serializable)
     */
    @Override
    @Transactional(readOnly = true)
    public boolean isExist(PK pk) {
        return getDao().isExist(pk);
    }

    
	/* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#merge(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    @Transactional(readOnly = false)
    public E merge(E entity) {
        return getDao().merge(entity);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#persist(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    @Transactional(readOnly = false)
    public void persist(E entity) {
        getDao().persist(entity);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#refresh(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    @Transactional(readOnly = true)
    public void refresh(E entity) {
        getDao().refresh(entity);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#remove(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    @Transactional(readOnly = false)
    public void remove(E entity) {
        getDao().remove(entity);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#remove(java.io.Serializable)
     */
    @Override
    @Transactional(readOnly = false)
    public void remove(PK pk) {
        getDao().remove(pk);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#removeBatch(PK[])
     */
    @Override
    @Transactional(readOnly = false)
    public boolean removeBatch(PK[] pks) {
        return getDao().removeBatch(pks);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.GenericService#removeBatch(java.util.List)
     */
    @Override
    @Transactional(readOnly = false)
    public boolean removeBatch(Collection<E> entitys) {
        return getDao().removeBatch(entitys);
    }

}
