/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;

/**
 *
 * @since 1.0 2014年11月19日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public interface JdbcGenericDao {
	/**
     * 批量更新、插入、删除数据，推荐优先使用
     * @param sql
     * @param pss
     * @return 更新成功影响的记录数
     */
    int[] batchUpdate(String sql, BatchPreparedStatementSetter pss); 
    
    /**
     * 批量更新、插入、删除数据
     * @param sql
     * @param params
     * @return 更新成功影响的记录数
     */
    int[] batchUpdate(String sql, Map<String,?>[] params);
    

    /**
     * 执行DDL语句
     * @param sql
     */
    void execute(String sql); 
    
    /**
     * 基于底层Statement
     * @param action
     * @return statement返回的对象
     */
    <T> T execute(StatementCallback<T> action); 
    
    /**
     * 根据Sql获取查询结果（Map）
     * @param sql
     * @param params
     * @return 查询结果
     */
    Map<String, Object> findForMap(String sql, Map<String, ?> params);
    
    /**
     * 查询返回Map对象
     * @param sql
     * @param params
     * @return 查询结果
     */
    List<Map<String, Object>> find(String sql, Map<String, ?> params);
    
    /**
     * 根据Sql获取查询对象
     * @param clazz
     * @param sql
     * @param params
     * @return 查询结果
     */
    <T> T findForObject(String sql, Map<String, ?> params, Class<T> clazz);
    
    /**
     * 根据Sql获取查询对象
     * @param clazz
     * @param sql
     * @param rowMapper
     * @return 查询结果
     */
    <T> T findForObject(String sql, Map<String, ?> params, RowMapper<T> rowMapper);
    
    /**
     * 根据Sql获取查询结果
     * @param sql sql语句
     * @param params 参数
     * @return 查询结果
     */
   <T> List<T> find(String sql, Map<String, ?> params, Class<T> clazz);
   
   /**
    * 根据RowMapper映射获取查询结果
    * @param sql
    * @param params
    * @param rowMapper
    * @return 查询结果
    */
   <T> List<T> find(String sql, Map<String, ?> params, RowMapper<T> rowMapper);
    
   /**
    * 统计查询的记录条数
    * @param sql sql语句
    * @param params 参数
    * @return 查询的记录条数
    */
    int findCount(String sql, Map<String, ?> params);
    
    /**
     * 插入数据
     * @param sql
     * @param params
     * @return 插入成功的记录数
     */
    int insert(String sql, Map<String, ?> params);
    
    /**
     * 更新数据
     * @param sql
     * @param params
     * @return 更新影响的记录数数
     */
    int update(String sql, Map<String, ?> params);
    
    /**
     * 
     * @param sql
     * @param params
     * @return 删除影响的记录数
     */
    int remove(String sql, Map<String, ?> params);
    
    /**
     * 根据key获取Sql语句
     * @param key
     * @return Sql语句
     */
    String getSql(String key);
}
