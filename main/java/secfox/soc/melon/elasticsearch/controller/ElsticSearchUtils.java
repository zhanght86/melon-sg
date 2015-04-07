/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.elasticsearch.controller;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @since 2015-1-23,上午10:50:04
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class ElsticSearchUtils {
	
	/*private Client client = new TransportClient()
    .addTransportAddress(new InetSocketTransportAddress("http://10.70.1.10", 9200));
	
	
	
	*//**
	 * @return the client
	 *//*
	public Client getClient() {
		return client;
	}



	*//**
	 * @param client the client to set
	 *//*
	public void setClient(Client client) {
		this.client = client;
	}

	public static void main(String[] args) {
		ElsticSearchUtils u = new ElsticSearchUtils();
		Client client = u.getClient();
		SearchResponse response = client.prepareSearch().execute().actionGet();
		System.out.println(response);
		
	}*/

}
