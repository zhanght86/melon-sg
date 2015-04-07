/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.query;

import secfox.soc.melon.asset.domain.Device;

/**
 * @since @2014-12-9,@下午9:15:09
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public class DeviceSearch extends Device{

	private static final long serialVersionUID = 1L;

	
	public DeviceSearch() {
		super();
	}
	
	//信息系统idPath，用于信息系统设备视图
	private Long infoPath;


	/**
	 * @return the infoPath
	 */
	public Long getInfoPath() {
		return infoPath;
	}


	/**
	 * @param infoPath the infoPath to set
	 */
	public void setInfoPath(Long infoPath) {
		this.infoPath = infoPath;
	}
	
	
	
}


