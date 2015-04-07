/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.dao;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.knowledge.domain.Knowledge;
import secfox.soc.melon.knowledge.query.TermPageQuery;
import secfox.soc.melon.persistence.GenericDao;

/**
 * @since @2014-10-28,@下午2:42:57
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
public interface KnowledgeDao extends GenericDao<Knowledge,Long>{
	
	/**
	 * 
	 * @param termPageQuery
	 * @return
	 */
	Pagination<Knowledge> queryPage(TermPageQuery termPageQuery);
}

