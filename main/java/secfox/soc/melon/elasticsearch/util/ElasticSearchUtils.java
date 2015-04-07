/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.elasticsearch.util;

import java.util.Iterator;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @since 2015-1-23,上午10:50:04
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class ElasticSearchUtils {
	
	private static Client client;
	
	private static Settings esConfig;
	
	@SuppressWarnings("resource")
	public static Client getClient(Map<String, Object> settings, String tcp, int port) {
		if(settings == null) {
			esConfig = ImmutableSettings.settingsBuilder()
					  .put("client.transport.sniff", true)
					  .put("client", true)
					  .put("cluster.name", "las")
					  .build();  
		} else {
			Iterator<String> it = settings.keySet().iterator();
			Builder builder = ImmutableSettings.settingsBuilder();
			while(it.hasNext()) {
				String key = it.next();
				builder.put(key, settings.get(key));
			}
			esConfig = builder.build();
		}
		client = new TransportClient(esConfig)  
        		.addTransportAddress(new InetSocketTransportAddress(tcp, port));
		return client;
	}
	
}
