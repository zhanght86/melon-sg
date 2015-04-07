/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system;

import org.junit.Test;

import secfox.soc.melon.system.query.LoggerSearch;
import secfox.soc.melon.system.service.LoggerService;

/**
 *
 * @since 1.0 2014-10-10 上午10:35:17
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
public class loggerServiceTest {
	
	private LoggerService loggerService;
	
	@Test
	public void testFindFunction(){
		loggerService.findAllFunctionByUserId(1L); 
	}
	
	@Test
	public void TestLoggerSearch(){
		LoggerSearch search = new LoggerSearch();
		
		System.out.println(search.getStartTime());
	}
}
