/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.intergration.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import secfox.soc.melon.asset.domain.IpAddress;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since @2014-11-27,@下午2:12:02
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Entity
@Table(name = "T_ASSET_LASDEVICE")
public class LasDevices extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	@Column(name="LASDEVICE_PK", length=100)
	private Long lasDevicePK;

	
	//名称
	@Column(name="NAME", length=100)
	private String name;
	
	//型号
	@Column(name="MODEL", length=100)
	private String model;
	
	//ip
	@Transient
	private List<IpAddress> ips;
	
	//mac
	@Column(name="MAC", length=100)
	private String mac;
	
	//厂商
	@Column(name="PRODUCER", length=100)
	private String Producer;
	
	//1.未同步 2.已同步
	@Column(name = "STATE" , length =2)
	private int	state = 1;


	public Long getLasDevicePK() {
		return lasDevicePK;
	}

	public void setLasDevicePK(Long lasDevicePK) {
		this.lasDevicePK = lasDevicePK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public List<IpAddress> getIps() {
		return ips;
	}

	public void setIps(List<IpAddress> ips) {
		this.ips = ips;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getProducer() {
		return Producer;
	}

	public void setProducer(String producer) {
		Producer = producer;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LasDevices [id=" + id + ", lasDevicePK=" + lasDevicePK
				+ ", name=" + name + ", model=" + model + ", ips=" + ips
				+ ", mac=" + mac + ", Producer=" + Producer + ", state="
				+ state + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Producer == null) ? 0 : Producer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ips == null) ? 0 : ips.hashCode());
		result = prime * result
				+ ((lasDevicePK == null) ? 0 : lasDevicePK.hashCode());
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + state;
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
		LasDevices other = (LasDevices) obj;
		if (Producer == null) {
			if (other.Producer != null)
				return false;
		} else if (!Producer.equals(other.Producer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ips == null) {
			if (other.ips != null)
				return false;
		} else if (!ips.equals(other.ips))
			return false;
		if (lasDevicePK == null) {
			if (other.lasDevicePK != null)
				return false;
		} else if (!lasDevicePK.equals(other.lasDevicePK))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state != other.state)
			return false;
		return true;
	}



}


