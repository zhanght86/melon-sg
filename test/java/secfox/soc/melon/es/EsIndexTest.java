/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.es;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.Maps;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.junit.Ignore;
import org.junit.Test;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.elasticsearch.controller.EsQueryTest;
import secfox.soc.melon.knowledge.domain.Knowledge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 2015年1月27日,下午3:22:07
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class EsIndexTest extends BaseTest {
	
	@Resource
	private EsQueryTest esTest;

	@Ignore
	@Test
	public void testToJson() throws IOException{
		XContentBuilder builder = XContentFactory.jsonBuilder()
												 .startObject()
										         .field("user", "kimchy")
										         .field("postDate", new Date())
										         .field("message", "trying out Elasticsearch")
										         .endObject();
		String json = builder.string();
		System.out.println(json);
		Map<String, Object> map = Maps.newHashMap();
		map.put("a", 100);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(map));
	}
	
	@Ignore
	@Test
	public void testCreateClient() throws ElasticsearchException, IOException {
		Node node = NodeBuilder.nodeBuilder().clusterName("las").client(true).node();
		Client client = node.client();
		//
		IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
		        .setSource(XContentFactory.jsonBuilder()
		                    .startObject()
		                        .field("user", "kimchy")
		                        .field("postDate", new Date())
		                        .field("message", "trying out Elasticsearch")
		                    .endObject()
		                  )
		        .execute()
		        .actionGet();
		String _index = response.getIndex();
		long _version = response.getVersion();
		System.out.println(_index);
		System.out.println(_version);
		//
		node.close();
	}
	
	
	@Test
	public void knowledge() throws ElasticsearchException, JsonProcessingException {
		Knowledge knowledge = new Knowledge();
		/*knowledge.setId(100002l);
		knowledge.setTitle("引导区病毒");
		knowledge.setContent("引导型病毒是一种在ROM BIOS之后，引导区病毒，它先于操作系统，依托的环境是BIOS中断服务程序。"
				+"引导型病毒是利用操作系统的引导模块放在某个固定的位置，并且控制权的转交方式是以物理位置为依据，而不是以操作系统引导区的内容为依据，"
				+"因而病毒占据该物理位置即可获得控制权，而将真正的引导区内容搬家转移或替换，待病毒程序执行后，将控制权交给真正的引导区内容，使得这个带病毒的系统看似正常运转，而病毒已隐藏在系统中并伺机传染、发作");
		*/
		/*knowledge.setId(100001l);
		knowledge.setTitle("跳板攻击");
		knowledge.setContent("P跳板攻击是利用别人的机器做中转站来攻击你的机器的,最实用的方法是根据攻击IP的地址一个一个去寻找,"
		+"攻击者往往在跳板中加入路由器或者交换机(比如Cisco的产品)，虽然路由器的日志文件会记录登陆IP，但是可以通过相关的命令清除该日志。"
		+"并且这些日志清除后将永远消失，因为路由器的日志保存在flash中，一旦删除将无法恢复。如果跳板全部用主机的话，虽然也可以清除日志，"
		+"但是现在的恢复软件往往可以恢复这些日志，就会留下痕迹，网络安全人员可以通过这些蛛丝马迹可能找到自己。");*/
		
		knowledge.setId(100003l);
		knowledge.setTitle("口令攻击2");
		knowledge.setContent("口令攻击是黑客最喜欢采用的入侵网络的方法。黑客通过获取系统管理员或其他殊用户的口令，获得系统的管理权，窃取系统信息、磁盘中的文件甚至对系统进行破坏");
		
		//esTest.addKnowledge(knowledge, "100003");
		esTest.delete("100003");
		//esTest.update(knowledge, "100003");
	}

}
