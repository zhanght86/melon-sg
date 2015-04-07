/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.IpAddress;
import secfox.soc.melon.asset.service.AssetGroupService;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.asset.service.GroupRelationService;

/**
 * @since @2014-10-11,@下午1:50:24
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public class GroupRelationTest extends BaseTest{
	
	@Resource
	private GroupRelationService service;
	
	@Ignore
	@Test
	public void testfindRelation() {
		List<Object[]> findByTypeAssetId = service.findByTypeAssetId(0,950l);
		System.out.println(findByTypeAssetId);
		
		
		
	}
	@Test
	public void testfindgroup() {
		
	}

}


