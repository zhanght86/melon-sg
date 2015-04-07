/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.elasticsearch.controller;

import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.springframework.stereotype.Component;

import secfox.soc.melon.elasticsearch.util.ElasticSearchUtils;
import secfox.soc.melon.knowledge.domain.Knowledge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 2015年1月26日,下午2:14:04
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Component
public class EsQueryTest {
	
	private ObjectMapper objectMapper;
	
	@Inject
	public EsQueryTest(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//change();
		//aggregation();
		//getDoc();
		queryTest();
		//delete();
	}
	
	/**
	 * 
	 */
	private static void queryTest() {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		SearchResponse response1 = client.prepareSearch("secfox-knowlege")
				  .setTypes("knowlege")
				  //SCAN 执行了没有任何排序的检索，第一次查询如果查过size大小，会返回size条记录和scrollid
				  //COUNT 查询结果只包括数量，没有数据
			      .setSearchType(SearchType.QUERY_AND_FETCH)
			      //设置游标缓存时间
			      //.setScroll(new TimeValue(600000))
			      //基础查询
			      //.setQuery(QueryBuilders.termQuery("ID", "1390792009283054031"))
			      //在指定的type中按id查找，可指定多个id
			      //.setQuery(QueryBuilders.idsQuery("las-syslog").ids("1390792009283054031"))
			      //字符匹配  可以指定analyzer,field为字段
			      //.setQuery(QueryBuilders.queryString("\""+"跳板攻击"+"\"").field("content"))
			      //常用在全文匹配，进行分词，生成词条列表，可以指定analyzer，也可以对单个field查询 两个参数 field名称和内容
			      //.setQuery(QueryBuilders.matchQuery("content", "跳板 IP"))
			      //匹配所有Document的Query
			      //.setQuery(QueryBuilders.matchAllQuery())
			      //与matchQuery类似，但不进行分词处理，按短语查询，若是英文内容要求单词
			      //.setQuery(QueryBuilders.matchPhraseQuery("msg", "win").slop(1))
			      //中英，以字符串内容不处理
			      //.setQuery(QueryBuilders.matchPhrasePrefixQuery("sysType", "外部"))
			      //类似matchQuery，但可以指定多个field
			      //.setQuery(QueryBuilders.multiMatchQuery("外部事件", "category", "sysType"))
			      //可当做复合查询，把其余类型的查询包裹进来。支持以下三种逻辑关系 must(and)/mustNot(not)/should(or)
			      //.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("ID", "1390792009283054031")))
			      //范围查询 double/long/float/int/Object/String
			      //.setQuery(QueryBuilders.rangeQuery("receptTime").from("2015-01-27 11:08:19").to("2015-01-27 21:01:39"))
			      //过滤查询
			      //.setQuery(QueryBuilders.filteredQuery(QueryBuilders.termQuery("sysType", "外部事件"), FilterBuilders.rangeFilter("receptTime").from("2015-01-27 11:08:19").to("2015-01-27 21:01:39")))
			      //模糊查询 ，可指定多个field 
			      //.setQuery(QueryBuilders.fuzzyLikeThisQuery("content").likeText("跳板攻击IP").prefixLength(0))
			      //SpanNearQuery主要用来精确查询。比如某个term之后 是另一个term，term之间的距离也可以自己（slop）设定，从而来实现精确搜索。
			      /*.setQuery(QueryBuilders.spanNearQuery()
			    		  .clause(QueryBuilders.spanTermQuery("content","跳板攻击")) 
			    		  .clause(QueryBuilders.spanTermQuery("content","机器"))
			    		  .inOrder(true) //是否按指定顺序
			    		  .slop(4)//指定相隔多远时仍然将文档视为匹配，相隔多远指你需要移动一个词条多少次来让查询和文档匹配
			    		  )*/
			      .setSize(10)
			      .execute()
			      .actionGet();
		//配合SCAN使用，第二次查询使用上次的scrollId继续访问
		//response1 = client.prepareSearchScroll(response1.getScrollId()).execute().actionGet();
		System.out.println("----------数量：" +response1.getHits().getTotalHits());
		for (SearchHit hit : response1.getHits()) {
			//获取源数据
			//hit.getSource().get("sysType");
		    System.out.println(hit.getSourceAsString());
		}
		client.close();
	}
	
	//聚合查询 分组统计
	public static void aggregation() {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		SearchResponse response = client.prepareSearch().setTypes("las-syslog")
                .addAggregation(AggregationBuilders.terms("agg1").field("sysType"))
                .execute().actionGet();
        Terms terms = response.getAggregations().get("agg1");
        for(Bucket b:terms.getBuckets()){
        	System.out.println("name:"+b.getKey()+"     count:"+b.getDocCount());
        }
	}
	
	/*1.无法对最终结果进行分页，除非人工分页；
      2.有可能多个SearchRequest查询出来的结果中，存在重复的结果，但MultiSearch并不负责去重。*/
	public static void multiSearch() {
	}
	
	//获取单条记录，需要索引、类型、id
	public static void getDoc() {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		GetResponse response = client.prepareGet("las-syslog-2015.01.27", "las-syslog", "1390792009283054031")
		        .execute()
		        .actionGet();
		System.out.println(response.getSourceAsString());
		
	}
	
	public static void change() {
		Settings settings = ImmutableSettings.settingsBuilder()
				 .put("client.transport.sniff", true)  
	             .put("client", true)  
                .put("cluster.name", "las")
                .build();  
		Client	client = new TransportClient(settings)  
                         .addTransportAddress(new InetSocketTransportAddress("10.70.1.10", 9300));
		SearchResponse response1 = client.prepareSearch("las-syslog-2015.01.27")
								  .setTypes("las-syslog")
								  //SCAN 执行了没有任何排序的检索，第一次查询如果查过size大小，会返回size条记录和scrollid
							      .setSearchType(SearchType.SCAN)
							      //设置游标缓存时间
							      .setScroll(new TimeValue(600000))
							      .setQuery(QueryBuilders.termQuery("ID", "1390792009283054031"))
							      .execute()
							      .actionGet();
		//第二次查询使用上次的scrollId继续访问
		response1 = client.prepareSearchScroll(response1.getScrollId()).execute().actionGet();
		/*GetResponse response = client.prepareGet("las-syslog-2015.01.27", "las-syslog", "1390792014138000007")
							      .actionGet();
		GetResponse response = client.prepareGet("las-syslog-2015.01.27", "las-syslog", "1390792014138000007")
		        .execute()
		        .actionGet();*/
		/*for(SearchHit hit : response.getHits()) {
			Map<String, SearchHitField> fields = hit.getFields();
			Iterator<String> keys = fields.keySet().iterator();
			while(keys.hasNext()) {
				System.out.println(fields.get(keys.next()));
			}
		}*/
		System.out.println(response1.getHits().getTotalHits());
		for (SearchHit hit : response1.getHits()) {
			//获取源数据
			hit.getSource().get("sysType");
		    //System.out.println(hit.getSourceAsString());
		System.out.println(hit.getSourceAsString());
		}
		client.close();
	}
	
	public static void check() {
			Settings settings = ImmutableSettings.settingsBuilder()  
	                .put("client.transport.sniff", true)  
	                .put("client", true)  
	                .put("data",false)  
	                .put("clusterName","las")  
	                .build();  
			
			Client	client = new TransportClient(settings)  
            .addTransportAddress(new InetSocketTransportAddress("10.70.1.10", 9300));
			GetResponse response = client.prepareGet("las-syslog-2015.01.27", "las-syslog", "1390792014138000007")
			        .execute()
			        .actionGet();
			System.out.println(response.getSourceAsString());
			client.close();
	}
	
	public static void node() {
		Node node = NodeBuilder.nodeBuilder().clusterName("las").client(true).node();
		node.close();
	}
	
	/**
	 * 添加数据 prepareIndex
	 * @param knowledge
	 * @param id
	 * @throws ElasticsearchException
	 * @throws JsonProcessingException
	 */
	public IndexResponse addKnowledge(Knowledge knowledge) throws ElasticsearchException, JsonProcessingException {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		IndexResponse response = client.prepareIndex("secfox-knowlege", "knowlege")//在此指定的也是document ID
		        .setSource(objectMapper.writeValueAsString(knowledge))//也可直接传入Map
		        .setId(String.valueOf(knowledge.getId()))//指定document ID 
		        .execute()
		        .actionGet();
		return response;
	}
	
	/**
	 * 按字段删除数据  prepareDeleteByQuery
	 * @param id
	 */
	public DeleteByQueryResponse delete() {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		DeleteByQueryResponse response = client.prepareDeleteByQuery("secfox-knowlege")
		        .setQuery(QueryBuilders.termQuery("id", "100000"))
		        .execute()
		        .actionGet();
		return response;
	}
	
	/**
	 * 按文档id删除 prepareDelete
	 * @param id
	 */
	public DeleteResponse delete(String id) {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		DeleteResponse response = client.prepareDelete("secfox-knowlege", "knowlege", id)
		        .execute()
		        .actionGet();
		return response;
	}
	
	/**
	 * 更新操作 prepareUpdate
	 * @param knowledge
	 * @param id 文档id
	 * @throws ElasticsearchException
	 * @throws JsonProcessingException
	 */
	public UpdateResponse update(Knowledge knowledge) throws ElasticsearchException, JsonProcessingException {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		UpdateResponse response = client.prepareUpdate("secfox-knowlege", "knowlege", String.valueOf(knowledge.getId()))
				.setDoc(objectMapper.writeValueAsString(knowledge))
				.execute()
		        .actionGet();
		return response;
	}
	
	/**
	 * 批量添加
	 * @param knowledges
	 * @return
	 */
	public BulkResponse addBatch(List<Knowledge> knowledges) {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for(Knowledge knowledge : knowledges) {
			try {
				bulkRequest.add(client.prepareIndex("secfox-knowlege", "knowlege")
				        .setSource(objectMapper.writeValueAsString(knowledge))
				        .setId(String.valueOf(knowledge.getId()))
				        );
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}		
		}
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		return bulkResponse;
		
	}
	
	/**
	 * 批量修改
	 * @param knowledges
	 * @return
	 */
	public BulkResponse updateBatch(List<Knowledge> knowledges) {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for(Knowledge knowledge : knowledges) {
			try {
				bulkRequest.add(client.prepareUpdate("secfox-knowlege", "knowlege", String.valueOf(knowledge.getId()))
				        .setDoc(objectMapper.writeValueAsString(knowledge))
				        );
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}		
		}
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		return bulkResponse;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public BulkResponse deleteBatch(List<String> ids) {
		Client client = ElasticSearchUtils.getClient(null, "10.70.1.10", 9300);
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for(String id : ids) {
			bulkRequest.add(client.prepareDelete("secfox-knowlege", "knowlege", id)
			        );		
		}
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		return bulkResponse;
	}

}
