/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.knowledge.domain.Knowledge;
import secfox.soc.melon.knowledge.query.KnowledgePageQuery;
import secfox.soc.melon.knowledge.query.TermPageQuery;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since @2014-10-28,@下午2:46:29
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
public interface KnowledgeService extends GenericService<Knowledge,Long>{

	/**
	 * 知识库分页列表
	 * @param pageQuery
	 * @return
	 */
	Pagination<Knowledge> findPage(KnowledgePageQuery pageQuery);
	
	/**
	 * 
	 * @param termPageQuery
	 * @return
	 */
	Pagination<Knowledge> queryPage(TermPageQuery termPageQuery);
	
	/**
	 * 查看次数统计
	 */
	Knowledge count(Knowledge konwledge);
}

