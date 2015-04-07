package secfox.soc.melon.rest;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;

import secfox.soc.las.query.AlertPageQuery;
import secfox.soc.las.service.AlertService;
import secfox.soc.melon.BaseTest;
import secfox.soc.melon.base.UmsInfoUtils;
import secfox.soc.melon.client.RestClient;

public class RestTest extends BaseTest {

	@Resource
	private RestClient client;
	
	@Test
	public void test() throws URISyntaxException {
		try {
			System.out.println(client.get("http://10.70.1.10:9200/las-syslog-2015.01.25,las-syslog-2015.01.26/_aliases?ignore_unavailable=true&ignore_missing=true", Map.class));
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
