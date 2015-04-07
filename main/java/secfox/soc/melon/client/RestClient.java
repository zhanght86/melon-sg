/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @since 2015-1-19,下午5:33:54
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class RestClient {
	
	private RestTemplate rest;
	
	public RestClient(RestTemplate rest) {
		this.rest = rest;
	}
	
	/**
	 * get方式发送请求
	 * @param url 请求绝对地址
	 * @param responseType 返回类型
	 * @return
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	public <T> T get(String url, Class<T> responseType) throws RestClientException, URISyntaxException {
		return rest.getForObject(new URI(url), responseType);
	}
	
	/**
	 * get方式发送请求，参数拼接到url上
	 * @param url 请求绝对地址 
	 * @param responseType 返回类型
	 * @param attrs 参数
	 * @return
	 */
	public <T> T get(String url, Class<T> responseType, Map<String, Object> attrs) {
		return rest.getForObject(url, responseType, attrs);
	}
	
	
	/**
	 * post方式发送请求，参数拼接到url
	 * @param url
	 * @param responseType
	 * @param attrs
	 * @return
	 */
	public <T> T post(String url, Class<T> responseType, Map<String, Object> attrs) {
		return rest.postForObject(url, null, responseType, attrs);
	}
	
	/**
	 * post方式发送请求
	 * @param url
	 * @param responseType
	 * @return
	 */
	public <T> T post(String url, Class<T> responseType) {
		return rest.postForObject(url, null, responseType);
	}
	
	/**
	 * 获取response对象
	 * @param url 请求绝对地址
	 * @param method 请求方法
	 * @param requestEntity
	 * @param responseType
	 * @return
	 */
	public <T> ResponseEntity<T> getResponse(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) {
		return rest.exchange(url, method, requestEntity, responseType);
	}
	
	/**
	 * get方式获取response对象
	 * @param url
	 * @param method
	 * @param requestEntity
	 * @param responseType
	 * @param args
	 * @return
	 */
	public <T> ResponseEntity<T> getResponse(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object[] args) {
		return rest.exchange(url, method, requestEntity, responseType, args);
	}
	
}
