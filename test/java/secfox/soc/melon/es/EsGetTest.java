/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.es;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @since 2015年1月27日,下午3:33:39
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class EsGetTest{

	@Ignore
	@Test
	public void testGet() {
		Node node = NodeBuilder.nodeBuilder()
							   .clusterName("las")
							   .client(true)
							   .data(false)
							   .node();
		Client client = node.client();
		//
		GetResponse response = client.prepareGet("las-syslog-2015.01.27", "las-syslog", "1390792014138000007")
		        					 .execute()
		        					 .actionGet();
		System.out.println(response.getSourceAsMap());
		//
		node.close();
	}
	
	@Ignore
	@Test
	public void testSearch() {
		Node node = NodeBuilder.nodeBuilder()
				   .clusterName("las")
				   .client(true)
				   .data(false)
				   .node();
		Client client = node.client();
		//SearchType.
		//
		//QueryBuilders.boolQuery().
		//QueryBuilders.f
		//
		SearchResponse response1 = client.prepareSearch("las-syslog-2015.01.27")
				  .setTypes("las-syslog")
			      .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			      .setQuery(QueryBuilders.termQuery("msg", "10.99.10.6"))
			      .setFrom(0)
			      .setSize(60)
			      .execute()
			      .actionGet();
		//response1.
		System.out.println(response1.getHits().getTotalHits());
		//hit hint
		//
		Iterator<SearchHit> results = response1.getHits().iterator();
		while(results.hasNext()) {
			SearchHit hit = results.next();
			//System.out.println(hit.getSource().get("devType"));
			System.out.println(hit.getScore());
		}
		//
		node.close();
	}
	
	@Test
	public void knowlege() {
		
	}

}
