/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.IpAddress;
import secfox.soc.melon.asset.service.DeviceService;

/**
 * @since @2014-10-11,@下午1:50:24
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public class DeviceTest extends BaseTest{
	
	@Resource
	private DeviceService deviceService;

	@Ignore
	@Test
	public void test() {
		/*List<Device> findAll = deviceService.findAll();
		for (Device device : findAll) {
			System.out.println(device.getName());
		}*/
		//
		List<Object[]> counts = deviceService.listOrganWithCount();
		for(Object[] objs : counts) {
			System.out.print(objs[0]);
			System.out.print(objs[1]+ ",");
			System.out.print(objs[2] + "\n");
		}
		
	}
	
	@Ignore
	@Test
	@Rollback(false)
	@Transactional(readOnly=false)
	public void testRemoveIp() {
		Device device = deviceService.find(530L);
		IpAddress delete = new IpAddress();
		delete.setIpv4("128.9.0.1");
		device.getIps().remove(delete);
		for(IpAddress ip: device.getIps()) {
			System.out.println(ip);
		}
		deviceService.merge(device);
	}
	
	@Ignore
	@Test
	public void testfindRelation() {
		List<Device> findByHasIp = deviceService.findByHasIp(true);
		System.out.println(findByHasIp.size());
		
	}
	
	@Ignore
	@Test
	public void testfind() {
		List<Device> devices = deviceService.findByAssetGroup(221l);
		System.out.println("1");
	}
	
	@Test
	public void testimport() {
		 String str = "";
		 POIFSFileSystem fs;
		 HSSFWorkbook wb;
		 HSSFSheet sheet =null;
		 HSSFRow row;
		// 对读取Excel表格标题测试
        try {
        	InputStream is = new FileInputStream("d:\\cd\\设备导出.xls");
        	fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);
            row = sheet.getRow(0);
			// 得到总行数
	        int rowNum = sheet.getLastRowNum();	
	        int cells = row.getPhysicalNumberOfCells();
	        for (int i = 0; i < rowNum; i++) {
	        	row = sheet.getRow(i);
	        	int j = 0;
	        	while (j < cells) {
	        		HSSFCell cell = row.getCell(j);
	        		String stringCellValue = cell.getStringCellValue();
	        		System.out.println();
					//type type = (type) en.nextElement();
					 // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
	                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
	                // str += getStringCellValue(row.getCell((short) j)).trim() +
	                // "-";
	               // str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
	                j++;
				}
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}


