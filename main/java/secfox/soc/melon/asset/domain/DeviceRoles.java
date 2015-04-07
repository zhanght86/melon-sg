/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年9月24日,下午4:46:28
 * 信息系统与设备关系类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_ASSET_CONN_DI")
public class DeviceRoles extends BaseDomain<Long> {

	private static final long serialVersionUID = -882111319684037032L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	//信息系统id
	@Column(name="D_INFOID")
	private Long infoId;
	
	//设备id
	@Column(name="D_DEVICEID")
	private Long deviceId;
	
	//用途,例如：用作WEB服务器还是数据库服务器
	@Column(name="D_ROLE", length=100)
	private String role;
	
	//0:代表设备与设备, 1:代表设备与信息系统
	@Column(name = "D_TYPE", length = 1)
	private int type;
	
	
	public static DeviceRoles createDeviceConnection(Long infoId, Long deviceId) {
		DeviceRoles ro = createInfoConnection(infoId,deviceId);
		ro.setType(0);
		return ro;
	}
	
	
	public static DeviceRoles createInfoConnection(Long infoId, Long deviceId) {
		DeviceRoles ro = new DeviceRoles();
		ro.setDeviceId(deviceId);
		ro.setInfoId(infoId);
		ro.setType(1);
		return ro;
	}
	
	public int getType() {
		return type;
	}
	
	
	public void setType(int type) {
		this.type = type;
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getInfoId() {
		return infoId;
	}
	
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	
	public Long getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((infoId == null) ? 0 : infoId.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceRoles other = (DeviceRoles) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (infoId == null) {
			if (other.infoId != null)
				return false;
		} else if (!infoId.equals(other.infoId))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceRoles [id=" + id + ", infoId=" + infoId + ", deviceId="
				+ deviceId + ", role=" + role + "]";
	}


}
