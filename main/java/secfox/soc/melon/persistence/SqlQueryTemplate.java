/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

/**
 * @since 0.1 2013-9-7,下午8:51:32
 * SQL查询模板,支持用于JPA的SQL查询
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 */
public class SqlQueryTemplate extends QueryTemplate{
	
	public SqlQueryTemplate() {
		super();
	}
	
	/**
	 * @param query
	 */
	public SqlQueryTemplate(String query) {
		super(query);
	}

}
