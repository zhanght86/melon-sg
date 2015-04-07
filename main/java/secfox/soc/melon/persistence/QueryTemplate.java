/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

import java.text.MessageFormat;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.SortOrder;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年8月29日,上午9:48:28
 * 查询模板的抽象类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public  abstract class QueryTemplate {
	
    private boolean cachable;
    
    private String cacheRegion;
    
    private int firstResult;
    
    private int maxResults = QueryConstants.MAX_RESULTS;
    
    private Map<String, Object> parameters;
    
    private StringBuilder query;
    
    /**
     * 添加SQL语句到query中
     * @param sql
     */
    public QueryTemplate append(String sql) {
    	Preconditions.checkArgument(!(this instanceof NamedQueryTemplate || this instanceof SqlResultMappingQueryTemplate), "this template can't add sql to query");
    	if(query == null) {
    		query = new StringBuilder();
    	}
    	query.append(sql);
    	return this;
    }
    
    /**
     * 添加in查询语句
     * @param sql
     * @param params
     */
    public void appendIn(String sql, Iterable<?> params) {
    	query.append(MessageFormat.format(QueryConstants.IN, new Object[]{sql, Joiner.on(BaseConstants.SPLITER_FLAG).join(params)}));
    }
    
    /**
     * 创建查询模板实例
     * @param type 查询模板类型
     * @return 模板实例
     */
    public static QueryTemplate create(QueryType type) {
    	QueryTemplate qt = null;
    	switch(type) {
    	case JQL:
    		qt = new JqlQueryTemplate();
    		break;
    	case NamedQuery:
    		qt = new NamedQueryTemplate();
    		break;
    	case SQL:
    		qt = new SqlQueryTemplate();
    		break;
    	case SQLResultMapping:
    		qt = new SqlResultMappingQueryTemplate();
    		break;
    	}
    	return qt;
    }
    
    /**
     * 创建查询模板实例
     * @param type 查询模板类型
     * @param query 查询语句
     * @return 模板实例
     */
    public static QueryTemplate create(QueryType type, String query) {
    	QueryTemplate qt = null;
    	switch(type) {
    	case JQL:
    		qt = new JqlQueryTemplate(query);
    		break;
    	case NamedQuery:
    		qt = new NamedQueryTemplate(query);
    		break;
    	case SQL:
    		qt = new SqlQueryTemplate(query);
    		break;
    	case SQLResultMapping:
    		qt = new SqlResultMappingQueryTemplate(query);
    		break;
    	}
    	return qt;
    }
    
    /**
     * 默认的构造函数
     */
    public QueryTemplate() {
    	
    }
    
    /**
     * 通过查询语句构造查询模板
     * @param query
     */
    public QueryTemplate(final String query) {
    	Preconditions.checkArgument(StringUtils.isNotBlank(query), "sql can't be null!");
    	this.setQuery(query);
    }
    
    
    
    /**
	 * 通过查询语句与查询参数构造查询模板
	 * @param parameters
	 * @param query
	 */
	public QueryTemplate(String query, Map<String, Object> parameters) {
		this(query);
		this.parameters = parameters;
	}

	
    /**
     * 是否包含查询语句
     * @return
     */
    public boolean isEmpty() {
    	return query == null || StringUtils.isBlank(query.toString());
    }
    
	/**
	 * 重新构造查询模板
	 * @param s
	 * @param builder
	 */
	public <S> void  rebuild(S s, String sortColumn, SortOrder order, QueryBuilder<S> builder) {
		builder.buildSelect(this);
		this.append(QueryConstants.WHERE);
		builder.buildWhere(s, this);
		this.append(" ");
		builder.buildBys(sortColumn, order, this);
	}
	
	/**
     * 添加查询参数
     * @param key
     * @param value
     */
    public void addParameter(String key, Object value) {
        if(parameters == null) {
            parameters = Maps.newHashMap();
        }
        parameters.put(key, value);
    }
    
    /**
     * 删除查询参数
     * @param key 查询参数
     */
    public void removeParameter(String key) {
        if(parameters != null) {
            parameters.remove(key);
        }
    }

    /**
     * 是否使用了查询缓存
     * @return 是否使用了查询缓存
     */
    public boolean isCachable() {
        return cachable;
    }
    
    /**
     * 启用查询缓存 ，一般不要缓存
     * @param cachable 是否启用查询缓存 
     */
    public void setCachable(boolean cachable) {
        this.cachable = cachable;
    }

    /**
     * 获取查询缓存区域
     * @return 查询缓存区域
     */
    public String getCacheRegion() {
        return cacheRegion;
    }

    /**
     * 设置查询缓存区域
     * @param cacheRegion 查询缓存区域
     */
    public void setCacheRegion(String cacheRegion) {
        this.cacheRegion = cacheRegion;
    }
    
    /**
     * 获取要抓取第一条记录的位置
     * @return 第一条记录的位置
     */
    public int getFirstResult() {
        return firstResult;
    }

    /**
     * 设置要抓取第一条记录的位置
     * @param firstResult 第一条记录的位置
     */
    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    /**
     * 获取最大记录的位置
     * @return 最大记录的位置
     */
    public int getMaxResults() {
        return maxResults;
    }

    /**
     * 设置最大记录的位置
     */
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * 获取查询参数
     * @return 查询参数
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * 设置查询参数
     * @param parameters 查询参数
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * 获取查询语句
     * @return 查询对象
     */
    public String getQuery() {
        return query.toString();
    }

    /**
     * 设置查询语句,目前只支持SQL\HQL\JQL
     * @param query
     */
    public void setQuery(String query) {
        this.query = new StringBuilder(query);
    }
    
    /**
     * 左模糊匹配
     * @param prop 需要匹配的字段名
     * @return 左模糊匹配
     */
    public static String buildLeftLike(String prop) {
        return QueryConstants.LIKE_FLAG + prop;
    }
    
    /**
     * 右模糊匹配
     * @param prop 需要匹配的字段名
     * @return 右模糊匹配
     */
    public static String buildRightLike(String prop) {
        return prop + QueryConstants.LIKE_FLAG;
    }
    
    /**
     * 全模糊匹配
     * @param prop 需要匹配的字段名
     * @return 全模糊匹配
     */
    public static String buildAllLike(String prop) {
        return QueryConstants.LIKE_FLAG + prop + QueryConstants.LIKE_FLAG;
    }
    
    /**
     * 构建order by子句
     * @param alias 别名
     * @param column 列名
     * @param order 顺序
     * @return order by子句
     */
    public static String buildOrderBy(String alias, String column, SortOrder order) {
        if(StringUtils.isNotBlank(column)) {
            return MessageFormat.format(QueryConstants.ORDER_BY, alias, column, order.name());
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String toString() {
    	return Objects.toStringHelper(this)
				  	  .add("query", this.query)
				  	  .add("parameters", this.parameters)
    				  .add("cachable", this.cachable)
    				  .add("cacheRegion", this.cacheRegion)
    				  .add("firstResult", this.firstResult)
    				  .add("maxResults", this.maxResults)
    				  .toString();
    }
}
