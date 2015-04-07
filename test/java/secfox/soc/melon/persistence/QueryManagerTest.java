/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.oxm.xstream.XStreamMarshaller;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.persistence.templates.QueryTemplateManager;
import secfox.soc.melon.persistence.templates.QueryTemplates;
import secfox.soc.melon.persistence.templates.QueryTemplates.Query;
import secfox.soc.melon.security.service.AccountService;

/**
 * @since 1.0 2014年10月17日,下午1:56:24
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class QueryManagerTest extends BaseTest{
	
	@Resource
	private XStreamMarshaller marshaller;
	
	@Resource
	private AccountService accountService;

	@Ignore
	@Test
	public void test() {
		QueryTemplates qts = new QueryTemplates();
		for(int i=0; i < 3; i++) {
			Query query = new QueryTemplates.JqlQuery();
			query.setDescription("DESC" + i);
			query.setQuery("SQL" + i);
			query.setId("ID" + i);
			qts.addQuery(query);
		}
		//
		for(int j=0; j < 5; j++) {
			Query query = new QueryTemplates.SqlQuery();
			query.setDescription("DESC" + j);
			query.setQuery("SQL" + j);
			query.setId("ID" + j);
			qts.addQuery(query);
		}
		//
		String result = marshaller.getXStream().toXML(qts);
		System.out.println(result);
	}
	
	@Test
	public void testGet() {
		QueryTemplate qt = QueryTemplateManager.find(null);
		qt.addParameter("username", "ganhuan");
		List<Object[]> results = accountService.find(qt);
		for(Object[] result : results) {
			System.out.println(result[0]);
		}
	}

}
