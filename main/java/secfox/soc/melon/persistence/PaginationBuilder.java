/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

import secfox.soc.melon.base.PageQuery;


/**
 * @since 1.0 2014年8月29日,下午8:56:23
 * 分页查询构造器，依次构造count查询语句、list查询语句以及where查询条件
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface PaginationBuilder<S, P extends PageQuery<S>> extends QueryBuilder<S> {
	
	/**
	 * 构建count子句
	 * @return count子句
	 */ 
	void buildCount(QueryTemplate qt);
}
