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

/**
 * @since @2014-10-11,@下午1:50:24
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public class AssetGroupTest extends BaseTest {

	@Resource
	private AssetGroupService assetGroupService;

	@Ignore
	@Test
	public void testfindRelation() {
		List<Object[]> groupDevice = assetGroupService.getGroupDevice();
		for (int i = 0; i < groupDevice.size(); i++) {
			Long groupId = Long.valueOf(groupDevice.get(i)[0].toString());
			String groupName = (String) groupDevice.get(i)[1];
			Long sumDevice = Long.valueOf(groupDevice.get(i)[2].toString());
			System.out.println("安全域id:" + groupId + ",安全域名称：" + groupName
					+ ",该安全域下有设备：" + sumDevice);
		}

	}

	@Test
	public void testfindHomeView() {
//		List<Map<String, Object>> homeView = assetGroupService.getHomeView();
//		for (int i = 0; i < homeView.size(); i++) {
//			System.out.println("groupId:" + homeView.get(i).get("groupId")
//					+ ",groupName:" + homeView.get(i).get("groupName")
//					+ ",deivceNum:" + homeView.get(i).get("deviceNum")
//					+ ",infoNum:" + homeView.get(i).get("infoNum"));
//		}
		/*
		 * viewDb.put("groupId", 0); viewDb.put("groupName", 0);
		 * viewDb.put("deviceNum", 0); viewDb.put("infoNum", 0); HashMap map=new
		 * HashMap(); map.put("groupId", "0"); map.put("groupName", "");
		 * map.put("deviceNum", "0"); map.put("infoNum", "0"); HashMap map1 =
		 * new HashMap(); map1.put("groupId", "1"); map1.put("groupName", "1");
		 * map.put("deviceNum", "10"); map.putAll(map1); HashMap map2 = new
		 * HashMap(); map2.put("groupId", "1"); map2.put("groupName", "1");
		 * map2.put("infoNum", "20"); map.putAll(map2); System.out.println(map);
		 * 
		 * List<String> lst1=new ArrayList<String>(); List<String> lst2=new
		 * ArrayList<String>(); lst1.add("北京"); lst1.add("上海"); lst2.add("南京");
		 * lst2.add("重庆"); lst2.add("上海"); lst1.addAll(lst2); for(int
		 * i=0;i<lst1.size();i++){ System.out.println(lst1.get(i));
		 * 
		 * }
		 */

	}

}
