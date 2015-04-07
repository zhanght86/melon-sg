/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service.impl;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import secfox.soc.melon.system.service.SystemHealthService;

/**
 * @since 1.0 2014年10月15日,下午5:44:46
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Service
public class SystemHealthServiceImpl implements SystemHealthService {
	
	 private OperatingSystemMXBean systemBean;
	 
	 /**
	  * 
	  */
	 @PostConstruct
	 public void init() {
		 systemBean = ManagementFactory.getOperatingSystemMXBean();
	 }

	/*
	 * (non-Javadoc)
	 * 
	 * @see secfox.soc.melon.system.service.SystemHealthService#getMemoryUsage()
	 */
	@Override
	@SuppressWarnings("restriction")
	public double getMemoryUsage() {
		if (systemBean instanceof com.sun.management.OperatingSystemMXBean) {
			com.sun.management.OperatingSystemMXBean systemMB = (com.sun.management.OperatingSystemMXBean) systemBean;
			return (systemMB.getFreePhysicalMemorySize()/systemMB.getTotalPhysicalMemorySize()) * 100;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.system.service.SystemHealthService#getCpuUsage()
	 */
	@Override
	public double getCpuUsage() {
		return  Math.random() * 40 + 10;
	}

}
