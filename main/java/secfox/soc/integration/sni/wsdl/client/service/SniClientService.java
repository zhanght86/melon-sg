/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.integration.sni.wsdl.client.service;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import secfox.soc.integration.sni.wsdl.client.DeviceObject;
import secfox.soc.integration.sni.wsdl.client.IpAddrObject;
import secfox.soc.integration.sni.wsdl.client.ServiceMBean;
import secfox.soc.integration.sni.wsdl.client.ServiceMBeanPortType;
import secfox.soc.melon.asset.domain.IpAddress;

import com.google.common.collect.Lists;

/**
 * @since @2014-11-27,@下午3:26:33
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public class SniClientService {
	
																
	private static final QName SERVICE_NAME = new QName("http://service.soa.system.soc.secfox/", "ServiceMBean");
	
	/**
	 * 获取处理过的sni设备对象集合
	 * @return 
	 */
	public List<Map<String, Object>> syncAssetFromLas() {
		
		URL wsdlURL = ServiceMBean.WSDL_LOCATION;
		ServiceMBean ss = new ServiceMBean(wsdlURL, SERVICE_NAME);
		ServiceMBeanPortType port = ss.getServiceMBeanPort();
		List<DeviceObject> dol = port.getDeviceObjectList();
		
		List<Map<String, Object>> lasDevices = Lists.newArrayList();

		for (DeviceObject device : dol) {
			String ip = device.getIP(); //主ip
			List<IpAddrObject> ipAddrList = device.getIpAddrList();
			if(ip == null || ipAddrList == null || ipAddrList.size()<0 ){
				break;
			}
			Map<String, Object> lasDev = new HashMap<String, Object>();
			Long lasDevicePK = device.getId(); //lasId
			String model = device.getModel(); // 型号
			String name = device.getName(); //设备名称
			String producer = device.getVendor(); //设备出厂
			String mac = device.getMac();	//MAC地址
			lasDev.put("lasDevicePK", lasDevicePK);
			lasDev.put("model", model);
			lasDev.put("name", name);
			lasDev.put("producer", producer);
			lasDev.put("mac", mac);
			lasDev.put("ip", ip);
			
			List<IpAddress> ipadd = Lists.newArrayList();
			IpAddress ipz = new IpAddress();
			ipz.setIpv4(ip);
			ipadd.add(ipz);
			
			for (int i = 1; i < ipAddrList.size(); i++) {
				IpAddress ipas = new IpAddress();
				String ips = ipAddrList.get(i).getIpAddr();
				ipas.setIpv4(ips);
				ipadd.add(ipas);
			}
			lasDev.put("ipAddrList", ipadd);
			lasDevices.add(lasDev);
		}

		return lasDevices;

	}
}


