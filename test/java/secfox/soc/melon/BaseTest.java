/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thoughtworks.xstream.XStream;

import secfox.soc.melon.base.AuditConfig;

/**
 * @since 1.0 2014年8月29日,上午10:29:55
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/META-INF/springContext/application-global-context.xml"
})
public class BaseTest {
	
	@Resource
	private ApplicationContext applicationContext;
	
	@Resource
	private XStreamMarshaller marshaller;
	
	@Ignore
	@Test
	public void readAuditConfig() throws IOException {
		XStream xstream = marshaller.getXStream();
		AuditConfig config = (AuditConfig)xstream.fromXML((new ClassPathResource("/META-INF/audit-config/melon-audit.xml")).getInputStream());
		System.out.println(config);
	}
	
}
