/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.oxm.xstream.XStreamMarshaller;

import secfox.soc.melon.base.AuditConfig;
import secfox.soc.melon.base.AuditConfig.AuditClass;
import secfox.soc.melon.base.AuditConfig.AuditItem;
import secfox.soc.melon.base.AuditConfig.AuditModule;

import com.thoughtworks.xstream.XStream;

/**
 * @since 1.0 2014年9月30日,上午11:33:00
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AuditClassTest extends BaseTest {

	@Resource
	private XStreamMarshaller marshaller;
	
	@Test
	public void test() {
		AuditConfig config = new AuditConfig();
		for(int y =0; y < 5; y++) {
			AuditModule module = new AuditModule();
			module.setName("m" + y);
			module.setTitle("模块" + y);
			for(int x = 0; x < 4; x ++) {
				AuditClass clazz = new AuditClass();
				clazz.setName("auditClass" + x);
				clazz.setTitle("审计");
				for(int i = 0; i < 3; i ++) {
					AuditItem item = new AuditItem();
					item.setName("method" + i);
					item.setTitle("方法" + i);
					/*item.setValue("VALUE" + i);
					item.setResult("RESULT");*/
					clazz.addMethod(item);
				}
				module.addClass(clazz);
			}
			//添加默认的方法
			AuditItem item = new AuditItem();
			item.setName("defaultMethod" + y);
			item.setTitle("默认方法" + y);
			config.addDefaultMethod(item);
			config.addModule(module);
		}
	
		//
		XStream xstream = marshaller.getXStream();
		System.out.println(xstream.toXML(config));
	}

}
