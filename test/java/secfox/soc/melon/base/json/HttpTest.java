/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.base.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @since 2015年1月19日,下午2:38:08
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class HttpTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws RestClientException 
	 */
	//@RequestMapping(method=RequestMethod.)
	public static void main(String[] args) throws IOException, RestClientException, URISyntaxException {
		//testUrl();
		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject(new URI("http://localhost:8800/index.php"), String.class);
		System.out.println(result);
		//HttpClient
		
	}

	/**
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void testUrl() throws MalformedURLException, IOException,
			UnsupportedEncodingException {
		URL url = new URL("http://localhost:8800/index.php");
		InputStream in = url.openStream();
		Reader reader = new InputStreamReader(in, "UTF-8");
		BufferedReader bufferReader = new BufferedReader(reader);
		//
		String line = null;
		while(StringUtils.isNotBlank(line=bufferReader.readLine())) {
			System.out.println(line);
		}
		//
		reader.close();
	}
	
	

}
