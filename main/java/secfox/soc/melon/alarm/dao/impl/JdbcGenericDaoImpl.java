/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import secfox.soc.melon.alarm.dao.JdbcGenericDao;

/**
 *
 * @since 1.0 2014年11月19日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public class JdbcGenericDaoImpl extends NamedParameterJdbcDaoSupport implements JdbcGenericDao {
private Map<String, String> sqlQuerys;
    
    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#batchUpdate(java.lang.String, java.util.Map<java.lang.String,?>[])
     */
    @Override
    public int[] batchUpdate(String sql, Map<String, ?>[] params) {
        return getNamedParameterJdbcTemplate().batchUpdate(sql, params);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#findForMap(java.lang.String, java.util.Map)
     */
    @Override
    public Map<String, Object> findForMap(String sql, Map<String, ?> params) {
        return getNamedParameterJdbcTemplate().queryForMap(sql, params);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#find(java.lang.String, java.util.Map)
     */
    @Override
    public List<Map<String, Object>> find(String sql, Map<String, ?> params) {
        return getNamedParameterJdbcTemplate().queryForList(sql, params);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#findForObject(java.lang.Class, java.lang.String, java.util.Map)
     */
    @Override
    public <T> T findForObject(String sql, Map<String, ?> params, Class<T> clazz) {
        return getNamedParameterJdbcTemplate().queryForObject(sql, params, clazz);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#find(java.lang.Class, java.lang.String, java.util.Map)
     */
    @Override
    public <T>List<T> find(String sql, Map<String, ?> params, Class<T> clazz) {
        return getNamedParameterJdbcTemplate().queryForList(sql, params, clazz);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#findCount(java.lang.String, java.util.Map)
     */
    @Override
    public int findCount(String sql, Map<String, ?> params) {
        List<Integer> count = find(sql, params, Integer.class);
        if(count.size() > 0) {
            return count.get(0);
        }
        return 0;
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#insert(java.lang.String, java.lang.Object[])
     */
    @Override
    public int insert(String sql, Map<String, ?> params) {
        return getNamedParameterJdbcTemplate().update(sql, params);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#update(java.lang.String, java.util.Map)
     */
    @Override
    public int update(String sql, Map<String, ?> params) {
        return getNamedParameterJdbcTemplate().update(sql, params);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#remove(java.lang.String, java.lang.Object[])
     */
    @Override
    public int remove(String sql, Map<String, ?> params) {
        return getNamedParameterJdbcTemplate().update(sql, params);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#find(java.lang.String, java.util.Map, org.springframework.jdbc.core.RowMapper)
     */
    @Override
    public <T>List<T> find(String sql, Map<String, ?> params, RowMapper<T> rowMapper) {
        return getNamedParameterJdbcTemplate().query(sql, params, rowMapper);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#findForObject(java.lang.String, java.util.Map, org.springframework.jdbc.core.RowMapper)
     */
    @Override
    public <T>T findForObject(String sql, Map<String, ?> params, RowMapper<T> rowMapper) {
        return getNamedParameterJdbcTemplate().queryForObject(sql, params, rowMapper);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#getSql(java.lang.String)
     */
    @Override
    public String getSql(String key) {
        return sqlQuerys.get(key);
    }

    /**
     * 设置查询语句集合
     * @param sqlQuerys
     */
    public void setSqlQuerys(Map<String, String> sqlQuerys) {
        this.sqlQuerys = sqlQuerys;
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#batchUpdate(java.lang.String, org.springframework.jdbc.core.BatchPreparedStatementSetter)
     */
    @Override
    public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss) {
        return getJdbcTemplate().batchUpdate(sql, pss);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#execute(java.lang.String)
     */
    @Override
    public void execute(String sql) {
        getJdbcTemplate().execute(sql);
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.JdbcGenericDao#execute(org.springframework.jdbc.core.StatementCallback)
     */
    @Override
    public <T>T execute(StatementCallback<T> action) {
        return getJdbcTemplate().execute(action);
    }
}
