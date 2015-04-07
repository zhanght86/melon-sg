/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.json;

import javax.annotation.Resource;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.security.domain.Role;

/**
 * @since 1.0 2014年10月3日,上午11:20:14
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class JsonSerializer extends BaseTest {

	@Resource
	private ObjectMapper mapper;
	
	@Test
	public void test() throws JsonProcessingException {
		Role role = new Role();
		role.setMutex(true);
		role.setLevel(new Integer[]{0,1});
		System.out.println(mapper.writeValueAsString(role));
	}

}
