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

import org.hibernate.Session;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.base.Pagination;

/**
 * @since 0.1 2013-3-12,下午10:17:23
 *        泛型数据库访问接口
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 0.1
 */
public interface GenericDao<E extends Identifiable<PK>, PK extends Serializable> {
    
	/**
     * 执行各类QL语句,SqlResultMappingQueryTemplate不支持此操作
     * @param qt
     */
    int execute(QueryTemplate qt); 
	
    /**
     * 根据主键获取对象
     * 
     * @param pk
     * @return pk
     */
    E find(PK pk);
    
    /**
     * 根据查询模板进行查询,SqlResultMappingQueryTemplate不支持此操作
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
     * 根据查询模板进行统计查询，SqlResultMappingQueryTemplate不支持此操作
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
     * @param queryPage 分页对象
     * @param builder 分页查询辅助对象
     * @return 业务实体的分页结果
     */
    <S, P extends PageQuery<S>> Pagination<E> findDomainPage(QueryType qlType, PageQuery<S> queryPage, PaginationBuilder<S, P> builder);
    
    /**
     * 根据分页对象进行分页查询,仅支持HQL\JQL查询
     * @param qlType 查询语言的类型
     * @param queryPage 分页对象
     * @param builder 分页查询辅助对象
     * @return 数组的分页结果
     */
    <S, P extends PageQuery<S>> Pagination<Object[]> findArrayPage(QueryType qlType, PageQuery<S> queryPage, PaginationBuilder<S, P> builder);
    
    /**
     * 根据分页对象进行分页查询,仅支持HQL\JQL查询
     * @param qlType 查询语言的类型
     * @param queryPage 分页对象
     * @param builder 分页查询辅助对象
     * @return Map的分页结果
     */
    <S, P extends PageQuery<S>> Pagination<Map<String, Object>> findMapPage(QueryType qlType, PageQuery<S> queryPage, String[] fields, PaginationBuilder<S, P> builder);
    
    /**
     * 根据ID与版本获取对象的历史版本
     * @param id 对象主键
     * @param version 对象的历史版本号
     * @return 对象的历史版本
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
     * 获取对象引用，能延迟对象加载
     * @param pk 主键
     * @return 对象的引用
     */
    E getReference(PK pk);
    
    /**
     * 获取Hibernate Session
     * @return 返回Hibernate Session
     */
    Session getSession();
    
    /**
     * 根据主键判断是否存在此对象
     * @param pk 主键
     * @return pk 是否存在此主键
     */
    boolean isExist(PK pk);
    
    /**
     * 状态合并与更新
     * @param entity 需要更新的实体类
     * @return entity 更新的实体类
     */
    E merge(E entity);
    
    /**
     * 持久化当前对象
     * @param entity 实体
     * @return 
     */
    void persist(E entity);
    
    /**
     * 刷新对象状态
     * 
     * @param entity 待更新的实体
     */
    void refresh(E entity);
    
    /**
     * 删除当前对象，可以通过getReference获取
     * @param entity 待删除的对象
     */
    void remove(E entity);
    
    /**
     * 删除主键为pk的实体对象
     * @param pk 主键
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
