/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.workflow;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.workflow.domain.BpmProcess;
import secfox.soc.melon.workflow.service.BpmProcessService;

/**
 * @since 1.0 2014年10月4日,下午3:50:41
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class BmpTest extends BaseTest {
	
	
	@Resource
	private BpmProcessService bpmService;

	@Test
	public void test() {
		BpmProcess bpm = new BpmProcess();
		bpm.addProp("name", "john");
		System.out.println(bpm.getProps());
	}
	
}
