/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.query;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.base.PageQuery;


/**
 * 
 * @since 2014-9-26
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version  1.0
 */
public class DevicePageQuery  extends PageQuery<Device> {

	private static final long serialVersionUID = 119537627776814447L;

	//安全域路径
	private String domainPath;
	
	//多个信息系统id
	private String infoIds;
	
	//-1全部，0没有操作系统，1有操作系统
	private int osFlag=-1;
	

	/**
	 * @return the infoIds
	 */
	public String getInfoIds() {
		return infoIds;
	}

	/**
	 * @param infoIds the infoIds to set
	 */
	public void setInfoIds(String infoIds) {
		this.infoIds = infoIds;
	}

	/**
	 * @return the osFlag
	 */
	public int getOsFlag() {
		return osFlag;
	}

	/**
	 * @param osFlag the osFlag to set
	 */
	public void setOsFlag(int osFlag) {
		this.osFlag = osFlag;
	}

	@Override
	protected Device initSearchForm() {
		return new Device();
	}

	public String getDomainPath() {
		return domainPath;
	}

	public void setDomainPath(String domainPath) {
		this.domainPath = domainPath;
	}

	

}


