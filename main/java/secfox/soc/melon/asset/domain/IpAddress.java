/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年9月25日,下午1:39:31
 * Ip类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_ASSET_IP")
@NamedQueries({
	@NamedQuery(name="ipAddress.findByDeviceId", 
			query = "select address from  IpAddress address where address.deviceId = :deviceId"),
	@NamedQuery(name="ipAddress.removeByDeviceId", 
			query = "delete from  IpAddress address where address.deviceId = :deviceId")
})

public class IpAddress extends BaseDomain<Long> {

	private static final long serialVersionUID = 247370544199001013L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	//对应的设备ID
	@Column(name="DEVICE_ID")
	private Long deviceId;
	
	//地址类型,false:有线,true:无线
	@Column(name="IP_WLAN")
	private Boolean wlan;
	
	//ipv4
	@Column(name="IP_IPV4" , length=15)
	private String ipv4;
	
	//ipv6
	@Column(name="IP_IPV6" , length=50)
	private String ipv6;
	
	//物理地址
	@Column(name="IP_MAC", length=50)
	private String mac;
	
	//子网掩码
	@Column(name="IP_MASK" , length=15)
	private String mask;
	
	//网关
	@Column(name="IP_GATE" , length=15)
	private String gate;
	
	//描述信息
	@Column(name="IP_REMARKS" , length=100)
	private String remarks;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * 
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the wlan
	 */
	public Boolean isWlan() {
		return wlan;
	}

	/**
	 * @param wlan the wlan to set
	 */
	public void setWlan(Boolean wlan) {
		this.wlan = wlan;
	}

	/**
	 * @return the ipv4
	 */
	public String getIpv4() {
		return ipv4;
	}

	/**
	 * @param ipv4 the ipv4 to set
	 */
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	/**
	 * @return the ipv6
	 */
	public String getIpv6() {
		return ipv6;
	}

	/**
	 * @param ipv6 the ipv6 to set
	 */
	public void setIpv6(String ipv6) {
		this.ipv6 = ipv6;
	}

	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * @return the mask
	 */
	public String getMask() {
		return mask;
	}

	/**
	 * @param mask the mask to set
	 */
	public void setMask(String mask) {
		this.mask = mask;
	}

	/**
	 * @return the gate
	 */
	public String getGate() {
		return gate;
	}

	/**
	 * @param gate the gate to set
	 */
	public void setGate(String gate) {
		this.gate = gate;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IpAddress [id=" + id + ", ipv4=" + ipv4
				+ ", ipv6=" + ipv6 + ", mask=" + mask + ", remarks=" + remarks
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipv4 == null) ? 0 : ipv4.hashCode());
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
		IpAddress other = (IpAddress) obj;
		if (ipv4 == null) {
			if (other.ipv4 != null)
				return false;
		} else if (!ipv4.equals(other.ipv4))
			return false;
		return true;
	}

	

}
