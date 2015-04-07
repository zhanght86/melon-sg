/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月23日,下午8:31:31
 * 系统运维事件
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_ASSET_ACTION")
@NamedQueries({
	@NamedQuery(name = "action.findByDevice" , query = "select a from DeviceAction a where a.deviceId = :deviceId order by id desc"),
	@NamedQuery(name = "action.findByTypeAsset" , query ="select a from DeviceAction a where a.deviceType =:deviceType and a.deviceId =:deviceId")
	
})
public class DeviceAction extends BaseDomain<Long> {

	private static final long serialVersionUID = 489116065184951783L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	//设备ID或信息系统ID
	@Column(name="DEVICE_ID")
	private Long deviceId;
	
	//0:代表设备, 1:代表信息系统
	@Column(name = "DEVICE_TYPE")
	private int deviceType;
	
	//操作原因 1:购置,2:安装,3:运行,4:维护,5:维修,6:改造,7:报废
	@Column(name="TYPE")
	private int type;
	
	//操作时间
	@Column(name="ACT_TIME")
	@Temporal(TemporalType.DATE)
	private Date actTime;
	
	//操作人
	@Column(name="ACTOR")
	private UserInfo actor;
	
	//操作说明
	@Column(name="REMARKS")
	private String remarks;

	
	public boolean isDevice(){
		if(this.deviceType == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isInfo(){
		if(this.deviceType == 1){
			return true;
		}else{
			return false;
		}
	}
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
	 * @return the deviceType
	 */
	public int getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the actTime
	 */
	public Date getActTime() {
		return actTime;
	}

	/**
	 * @param actTime the actTime to set
	 */
	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	/**
	 * @return the actor
	 */
	public UserInfo getActor() {
		return actor;
	}

	/**
	 * @param actor the actor to set
	 */
	public void setActor(UserInfo actor) {
		this.actor = actor;
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
		return "DeviceAction [id=" + id + ", deviceId=" + deviceId + ", type="
				+ type + ", actTime=" + actTime + ", actor=" + actor
				+ ", remarks=" + remarks + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actTime == null) ? 0 : actTime.hashCode());
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + type;
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
		DeviceAction other = (DeviceAction) obj;
		if (actTime == null) {
			if (other.actTime != null)
				return false;
		} else if (!actTime.equals(other.actTime))
			return false;
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
		if (type != other.type)
			return false;
		return true;
	}
}
