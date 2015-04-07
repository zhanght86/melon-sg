/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.base.Pagination;

/**
 * @since 0.1 2013-3-12,下午10:29:55
 * 
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 0.1
 */
public interface GenericService<E extends Identifiable<PK>, PK extends Serializable> {
    
    /**
     * 根据主键获取对象
     * @param pk
     * @return 与主键关联的持久化对象
     */
    E find(PK pk);
    
    /**
     * 根据查询模板进行查询
     * @param qt 查询模板
     * @return 以Object[]表示的List对象
     */
    List<Object[]> find(QueryTemplate qt);
    
    /**
     * 根据查询模板进行查询
     * @param qt 查询模板
     * @return 以Map<String, Object>表示的List对象
     */
    List<Map<String, Object>> find(QueryTemplate qt, String... fileds);
    
    /**
     * 根据查询模板进行统计查询
     * @param qt 查询模板
     * @return 以Map<String, Object>表示的List对象
     */
    BigInteger findCount(QueryTemplate qt);
    
    /**
     * 根据查询模板进行查询
     * @param clazz
     * @param qt
     * @return 查询结果
     */
    List<E> findDomains(QueryTemplate qt);
    
    /**
     * 返回查询列表的第一个对象，如果List不为空则获取List的第一个值，否则返回null值
     * @param qt
     * @return
     */
    E findFirstDomain(QueryTemplate qt);
    
    /**
     * 根据分页对象进行分页查询,仅支持HQL\JQL查询
     * @param qlType 查询语言的类型
     * @param pageQuery 分页对象
     * @param builder 分页查询辅助对象
     * @return 业务实体的分页结果
     */
    <S, P extends PageQuery<S>> Pagination<E> findDomainPage(QueryType qlType, PageQuery<S> pageQuery, PaginationBuilder<S, P> builder);
    
    /**
     * 根据分页对象进行分页查询,仅支持HQL\JQL查询,采用数组作为分页,查询结果的第一个元素(Object[0])必须是ID或可以主键的元素
     * @param qlType 查询语言的类型
     * @param pageQuery 分页对象
     * @param builder 分页查询辅助对象
     * @return 数组的分页结果
     */
    <S, P extends PageQuery<S>> Pagination<Object[]> findArrayPage(QueryType qlType, PageQuery<S> pageQuery, PaginationBuilder<S, P> builder);
    
    /**
     * 根据分页对象进行分页查询,仅支持HQL\JQL查询
     * @param qlType 查询语言的类型
     * @param pageQuery 分页对象
     * @param builder 分页查询辅助对象
     * @return Map的分页结果
     */
    <S, P extends PageQuery<S>> Pagination<Map<String, Object>> findMapPage(QueryType qlType, PageQuery<S> pageQuery, String[] fields, PaginationBuilder<S, P> builder);

    
    /**
     * 获取对象引用，能延迟对象加载
     * @param pk
     * @return 对象引用
     */
    E getReference(PK pk);
    
    /**
     * 根据ID与版本获取审计对象
     * @param id
     * @param version
     * @return
     */
    public E getAuditVersion(Long id, int version); 
    
    /**
     * 获取当前对象的所有版本
     * @param id
     * @return
     */
    public List<Number> getAuditVersions(Long id);
    
    /**
     * 获取对象的当前审计对象
     * @return
     */
    public E getCurrentAuditision();
    
    /**
     * 是否存在此对象
     * @param pk
     * @return 对象是否存在
     */
    boolean isExist(PK pk);
    
    /**
     * 状态合并
     * @param entity
     * @return 状态合并后的持久化对象
     */
    E merge(E entity);
    
    /**
     * 持久化当前对象
     * 
     * @param entity
     * @return
     */
    void persist(E entity);
    
    /**
     * 刷新对象状态
     * 
     * @param entity
     */
    void refresh(E entity);
    
    /**
     * 删除当前对象
     * 
     * @param entity
     */
    void remove(E entity);
    
    /**
     * 删除主键为pk的实体对象
     * 
     * @param pk
     */
    void remove(PK pk);
    
    /**
     * 
     * @param pks
     * @return
     */
    boolean removeBatch(PK[] pks);
    
    /**
     * 
     * @param entitys
     * @return
     */
    boolean removeBatch(Collection<E> entitys);
    
}
