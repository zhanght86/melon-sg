/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system;

import java.net.URI;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import secfox.soc.las.domain.AlertRule;
import secfox.soc.las.service.AlertRuleService;
import secfox.soc.melon.BaseTest;
import secfox.soc.melon.mail.MailManager;
import secfox.soc.melon.system.domain.GlobalConfig;
import secfox.soc.melon.system.service.GlobalConfigService;

/**
 *
 * @since 1.0 2014-10-9 下午2:29:33
 * @author <a href="mailto:zhangdi@legendsec.com">张棣</a>
 * @version 1.0
 */
public class Mail extends BaseTest {
	
	@Resource
	private MailManager manager;
	
	@Resource
	private AlertRuleService alertRuleService;
	
	@Resource
	private GlobalConfigService service;
	
	@Test
	public void test(){
		/*AlertRule rule = alertRuleService.findByRuleId("05d86539-3b74-40dc-aa3f-4a479da65385");
		GlobalConfig config = service.find();
		manager.sendMail(rule, config);*/
        try {
        	 HttpClient client = new DefaultHttpClient();  
             // 实例化HTTP方法  
             HttpGet request = new HttpGet();  
         
			 request.setURI(new URI("http://10.70.63.58:8080/ums/alert/handle?ruleId=05d86539-3b74-40dc-aa3f-4a479da65385&alertId=1"));
			 HttpResponse response = client.execute(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
