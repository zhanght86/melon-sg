/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.oxm.xstream.XStreamMarshaller;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.base.template.StrTemplateManager;
import secfox.soc.melon.base.template.StrTemplates;
import secfox.soc.melon.base.template.StrTemplates.Template;

import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年10月2日,下午4:01:21
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class StrTemplateTest extends BaseTest {
	
	@Resource
	private XStreamMarshaller marshaller;

	@Test
	public void test() {
		StrTemplates tmpls = new StrTemplates();
		for(int i =1; i < 10; i++) {
			Template tmpl = new Template();
			tmpl.setId("10001" + i);
			tmpl.setDescription("YYYY");
			tmpl.setContent("xxxx" + i);
			tmpls.addTemplate(tmpl);
		}
		String result = marshaller.getXStream().toXML(tmpls);
		System.out.println(result);
	}
	
	@Test
	public void test2() {
		Map<String, Object> params = Maps.newHashMap();
		params.put("name", "甘焕");
		String result = StrTemplateManager.find("100011").format(params);
		System.out.println(result);
	}

}
