/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

import secfox.soc.melon.base.SortOrder;

/**
 * @since 1.0 2014年8月29日,下午3:40:50
 * 查询构造器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface QueryBuilder<S> {
	
	 /**
     * 构建select子句
     * @return select子句
     */
    void buildSelect(QueryTemplate qt);
    
    /**
     * 构建where子句
     * @param s 查询对象
     * @param parameters
     * @return where子句
     */
    void buildWhere(S s, QueryTemplate qt);
    
    /**
     * 构建groupby与order子句
     * @param column 排序列
     * @param order 排序方向
     * @return groupby与order子句
     */
    void buildBys(String column, SortOrder order, QueryTemplate qt);
	
}
