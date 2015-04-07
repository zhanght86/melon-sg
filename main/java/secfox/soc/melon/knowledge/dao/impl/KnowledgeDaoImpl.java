/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.dao.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.knowledge.dao.KnowledgeDao;
import secfox.soc.melon.knowledge.domain.Knowledge;
import secfox.soc.melon.knowledge.query.TermPageQuery;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since @2014-10-28,@下午2:44:36
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
@Repository
public class KnowledgeDaoImpl extends GenericDaoImpl<Knowledge,Long> implements KnowledgeDao{
	
	public KnowledgeDaoImpl() {
		super(Knowledge.class);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.knowledge.dao.KnowledgeDao#queryPage(secfox.soc.melon.knowledge.query.TermPageQuery)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Pagination<Knowledge> queryPage(TermPageQuery termPageQuery) {
		/*//获取全文entityManager
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		String term = termPageQuery.getSearchForm();
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		//获取查询构造器
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
														 .buildQueryBuilder()
														 .forEntity(Knowledge.class)
														 .get();
		//准备查询条件
		Query query = queryBuilder.keyword()
								  .onFields("title", "issueOrgan", "keywords", "content")//需要检索的字段
								  .matching(term)
								  .createQuery();
		//准备分页
		FullTextQuery persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Knowledge.class);
		persistenceQuery.setFirstResult(termPageQuery.getFirstResult());
		persistenceQuery.setMaxResults(termPageQuery.getMaxResults());
		//获取查询结果
		List<Knowledge> results = persistenceQuery.getResultList();
		//添加检索字段强调标示
		QueryScorer queryScorer = new QueryScorer(query);
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<strong>", "</strong>");
		Highlighter highlighter = new Highlighter(formatter, queryScorer);
		highlighter.setTextFragmenter(new SimpleFragmenter(20));
		//迭代检索到的内容
		for(Knowledge know : results) {
			String title = know.getTitle();
			String content = know.getContent();
			TokenStream titleToken = analyzer.tokenStream("title", new StringReader(title));
			TokenStream contentToken = analyzer.tokenStream("content", new StringReader(content));
			try {
				String contentResult = highlighter.getBestFragments(contentToken, content, 20, "...");
				String titleResult = highlighter.getBestFragments(titleToken, title, 20, "...");
				know.setContent(contentResult);
				know.setTitle(titleResult);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidTokenOffsetsException e) {
				e.printStackTrace();
			}
		}
		//关闭分词器
		analyzer.close();
		//返回结果
		Pagination<Knowledge> pagination = new Pagination<Knowledge>();
		pagination.setCurrPage(termPageQuery.getCurrPage());
		pagination.setResults(persistenceQuery.getResultList());
		pagination.setCount(1000);//待改进
		return pagination;*/
		return null;
	}

}

