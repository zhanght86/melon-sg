/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

/**
 * 安全对象主页安全对象分布图工具类
 * @since @2014-12-9,@上午9:34:36
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public class AssetHome {
	
	//二级安全域数量
	private Integer twoGroup;
	
	private Integer thGroup;
	
	//设备总数
	private Integer deviceCount;
	
	//网络设备数量
	private Integer deviceNetCount;
	
	//安全设备数量
	private Integer deviceSeftCount;
	
	//信息系统数量
	private Integer infoCount;
	
	//安全域id
	private Long groupId;
	
	//安全域name
	private String groupName;

	

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the twoGroup
	 */
	public Integer getTwoGroup() {
		return twoGroup;
	}

	/**
	 * @param twoGroup the twoGroup to set
	 */
	public void setTwoGroup(Integer twoGroup) {
		this.twoGroup = twoGroup;
	}

	/**
	 * @return the thGroup
	 */
	public Integer getThGroup() {
		return thGroup;
	}

	/**
	 * @param thGroup the thGroup to set
	 */
	public void setThGroup(Integer thGroup) {
		this.thGroup = thGroup;
	}

	/**
	 * @return the deviceCount
	 */
	public Integer getDeviceCount() {
		return deviceCount;
	}

	/**
	 * @param deviceCount the deviceCount to set
	 */
	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	/**
	 * @return the deviceNetCount
	 */
	public Integer getDeviceNetCount() {
		return deviceNetCount;
	}

	/**
	 * @param deviceNetCount the deviceNetCount to set
	 */
	public void setDeviceNetCount(Integer deviceNetCount) {
		this.deviceNetCount = deviceNetCount;
	}

	/**
	 * @return the deviceSeftCount
	 */
	public Integer getDeviceSeftCount() {
		return deviceSeftCount;
	}

	/**
	 * @param deviceSeftCount the deviceSeftCount to set
	 */
	public void setDeviceSeftCount(Integer deviceSeftCount) {
		this.deviceSeftCount = deviceSeftCount;
	}

	/**
	 * @return the infoCount
	 */
	public Integer getInfoCount() {
		return infoCount;
	}

	/**
	 * @param infoCount the infoCount to set
	 */
	public void setInfoCount(Integer infoCount) {
		this.infoCount = infoCount;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssetHome [twoGroup=" + twoGroup + ", thGroup=" + thGroup
				+ ", deviceCount=" + deviceCount + ", deviceNetCount="
				+ deviceNetCount + ", deviceSeftCount=" + deviceSeftCount
				+ ", infoCount=" + infoCount + ", groupName=" + groupName + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceCount == null) ? 0 : deviceCount.hashCode());
		result = prime * result
				+ ((deviceNetCount == null) ? 0 : deviceNetCount.hashCode());
		result = prime * result
				+ ((deviceSeftCount == null) ? 0 : deviceSeftCount.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result
				+ ((infoCount == null) ? 0 : infoCount.hashCode());
		result = prime * result + ((thGroup == null) ? 0 : thGroup.hashCode());
		result = prime * result
				+ ((twoGroup == null) ? 0 : twoGroup.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetHome other = (AssetHome) obj;
		if (deviceCount == null) {
			if (other.deviceCount != null)
				return false;
		} else if (!deviceCount.equals(other.deviceCount))
			return false;
		if (deviceNetCount == null) {
			if (other.deviceNetCount != null)
				return false;
		} else if (!deviceNetCount.equals(other.deviceNetCount))
			return false;
		if (deviceSeftCount == null) {
			if (other.deviceSeftCount != null)
				return false;
		} else if (!deviceSeftCount.equals(other.deviceSeftCount))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (infoCount == null) {
			if (other.infoCount != null)
				return false;
		} else if (!infoCount.equals(other.infoCount))
			return false;
		if (thGroup == null) {
			if (other.thGroup != null)
				return false;
		} else if (!thGroup.equals(other.thGroup))
			return false;
		if (twoGroup == null) {
			if (other.twoGroup != null)
				return false;
		} else if (!twoGroup.equals(other.twoGroup))
			return false;
		return true;
	}
}


