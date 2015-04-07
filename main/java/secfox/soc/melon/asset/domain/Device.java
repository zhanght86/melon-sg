/*

 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import secfox.soc.melon.asset.field.domain.AssetField;
import secfox.soc.melon.base.json.Dictionary;

/**
 * @since 1.0 2014年9月25日,下午7:50:19 设备类
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 1.0
 */
@Entity
@Table(name = "T_ASSET_DEVICE")
@NamedQueries({
		//通过登录人获取批注的设备
		@NamedQuery(name = "device.findByQuickNote", query = "select device from QuickNote quickNote, Device device where quickNote.businessId = device.id and quickNote.creator.userId = :userId "),
		//通过登录人id与Attention中的外键id 获取设备
		@NamedQuery(name = "device.findByUserBusiness", query = "select device from Attention attention, Device device where attention.businessId = device.id and attention.user.userId = :userId "),
		//查出系统中所有的设备
		@NamedQuery(name = "device.findByEnviId", query = "select a from Device a where a.enviPath like :enviId"),
		//查出系统中所有的设备
		@NamedQuery(name = "device.findAll", query = "select a from Device a"),
		//根据名称查设备 没有用到
		@NamedQuery(name = "device.findByName", query = "select a from Device a where a.name = :name "),
		//根据编码查设备
		@NamedQuery(name = "device.findByCode", query = "select a from Device a where a.code = :code "),
		//根据单位id查设备 没有用到
		@NamedQuery(name = "device.findByOrgan", query = "select a from Device a where a.organId =:organId"),
		//根据是否网络设备查设备
		@NamedQuery(name = "device.findByHasIp", query = "select a from Device a where a.hasIp = :hasIp "),
		//根据是否虚拟设备查设备
		@NamedQuery(name = "device.findByVirtual", query = "select a from Device a where a.virtual = :virtual "),
		//根据负责人id查设备
		@NamedQuery(name = "device.findByCharger", query = "select a from Device a where a.chargerId = :chargerId "),
		//根据名称和型号查设备
		@NamedQuery(name = "device.findByNameModel", query = "select a from Device a where a.name = :name and a.model = :model "),
		//获取到最大的numbersum
		@NamedQuery(name = "device.findNumberSum", query = "select a from Device a where a.organId = :organId order by a.numberSum desc "),
		//yb 根据typePath查询设备
		@NamedQuery(name = "device.findByTypePath", query = "select a from Device a where (a.typePath like :path or a.typePath like :rightPath) and (a.organPath like :organPath or a.organId=:organId) "),
		//yb 根据操作系统查询设备
		@NamedQuery(name = "device.findByOs", query = "select a from Device a where a.hasOs=true and (a.organPath like :organPath or a.organId=:organId) "),
		//yb 根据机房查询设备
		@NamedQuery(name = "device.findByEnviPath", query = "select a from Device a where (a.enviPath like :path or a.enviPath like :rightPath) and (a.organPath like :organPath or a.organId=:organId) "),
		//yb 根据组织查询设备
		@NamedQuery(name = "device.findByOrganPath", query = "select a from Device a where a.organPath like :path or a.organPath like :rightPath "),
		
		@NamedQuery(name ="device.findCountByPath", query = "select count(a.id) from Device a where a.organPath like :path and a.typeName = :typeName")
})
public class Device extends AbstractDevice {

	private static final long serialVersionUID = -704078214339911160L;

	//lasDevices主键
	@Column(name = "LAS_DEVICEID")
	private Long lasDeviceId;
	
	// 型号
	@Column(name = "MODEL", length = 100)
	private String model;

	// 是否有操作系统 whetherSYSTEM
	@Column(name = "OS_HAS")
	private boolean hasOs;

	// 操作系统名称 1:Windows系列操作系统,2:Unix类操作系统,3:Linux类操作系统,4:Mac操作系统
	@Column(name = "OS_NAME", length = 2)
	private int osName = 1;

	// 厂商
	@Column(name = "PRODUCER", length = 200)
	private String producer;

	// 厂商联系人
	@Column(name = "PRODUCER_TEL", length = 100)
	private String producerTel;
	
	//保修开始日期
	@Column(name = "CAT_TIME")
	@Temporal(TemporalType.DATE)
	private Date catTime;
	
	//保修到期时间
	@Column(name = "DEADLINE_TIME")
	@Temporal(TemporalType.DATE)
	private Date deadline;
	
	// 出厂日期
	@Column(name = "PRODUCE_TIME")
	@Temporal(TemporalType.DATE)
	private Date produceTime;

	// 是否具有IP
	@Column(name = "HAS_IP")
	private boolean hasIp;

	// 是否虚拟
	@Column(name = "VIR_TUAL")
	private boolean virtual;

	// 虚拟机宿主
	@Column(name = "MASTER_ID")
	private Long masterId;
	
	//物理环境id
	@Column(name = "ENVI_ID")
	private Long enviId;
	
	//物理环境name
	@Column(name = "ENVI_NAME")
	private String enviName;
	
	//物理环境路径
	@Column(name = "ENVI_PATH")
	private String enviPath;
	
//	备份设备
	@Column(name = "BACKUPS_DEVICE")
	private Long deviceId;
	/**
	 * 
	 */
	@Transient
	@JsonIgnore
	private List<IpAddress> ips;
	
	//用于Excle导入ip使用
	@Transient
	private String elsIps;
	
	//用于Excle导入Mac使用
	@Transient
	private String elsMac;
	
	//用于Excle导入安全域使用
	@Transient
	private String elsDomain;
	
	//用于Excle导入宿主使用
	@Transient
	private String elsMasterName;
	
	//动态属性
	@Transient
	private List<AssetField> field;
	
	/**
	 * 
	 */
	
	//设备硬件配置
	@Column(name = "WARE_HARD")
	private String hardWare;
	
	//主要软件
	@Column(name = "WARE_SOFT")
	private String softWare;
	
	//设备CIA价值 1:1,2:2,3:3,4:4,5:5
	@Column(name = "CIAVALUE" , length =2)
	private int CIAValue = 1;

	
//	设备维护人
	@Column(name = "PERSON_KEEPID")
	private Long keepPersonId;
	@Column(name = "PERSON_KEEPNAME")
	private String keepPersonName;
	
//	设备使用人
	@Column(name = "PERSON_USEID")
	private Long usePersonId;
	@Column(name = "PERSON_USENAME")
	private String usePersonName;
	
	@Transient
	private Long[] deviceIds;
	
	@Transient
	private String[] deviceNames;
	
	@Transient
	private List<DeviceRoles> devices;
	
	@Transient
	private String infoNames;

	public Device(Long id,String deviceName,String elsIps,String infoNames) {
		super();
		super.setId(id);
		super.setName(deviceName);
		this.elsIps = elsIps;
		this.infoNames = infoNames;
	}
	
	public Device() {
		super();
	}
	
	
	/**
	 * @return the infoNames
	 */
	public String getInfoNames() {
		return infoNames;
	}

	/**
	 * @param infoNames the infoNames to set
	 */
	public void setInfoNames(String infoNames) {
		this.infoNames = infoNames;
	}

	public Long[] getDeviceIds() {
		return deviceIds;
	}


	public Long getLasDeviceId() {
		return lasDeviceId;
	}


	public void setLasDeviceId(Long lasDeviceId) {
		this.lasDeviceId = lasDeviceId;
	}


	public void setDeviceIds(Long[] deviceIds) {
		this.deviceIds = deviceIds;
	}


	public String[] getDeviceNames() {
		return deviceNames;
	}


	public void setDeviceNames(String[] deviceNames) {
		this.deviceNames = deviceNames;
	}


	public List<DeviceRoles> getDevices() {
		return devices;
	}


	public void setDevices(List<DeviceRoles> devices) {
		this.devices = devices;
	}


	/**
	 * @return the keepPersonId
	 */
	public Long getKeepPersonId() {
		return keepPersonId;
	}


	/**
	 * @param keepPersonId the keepPersonId to set
	 */
	public void setKeepPersonId(Long keepPersonId) {
		this.keepPersonId = keepPersonId;
	}


	/**
	 * @return the keepPersonName
	 */
	public String getKeepPersonName() {
		return keepPersonName;
	}


	/**
	 * @param keepPersonName the keepPersonName to set
	 */
	public void setKeepPersonName(String keepPersonName) {
		this.keepPersonName = keepPersonName;
	}


	/**
	 * @return the usePersonId
	 */
	public Long getUsePersonId() {
		return usePersonId;
	}


	/**
	 * @param usePersonId the usePersonId to set
	 */
	public void setUsePersonId(Long usePersonId) {
		this.usePersonId = usePersonId;
	}


	/**
	 * @return the usePersonName
	 */
	public String getUsePersonName() {
		return usePersonName;
	}


	/**
	 * @param usePersonName the usePersonName to set
	 */
	public void setUsePersonName(String usePersonName) {
		this.usePersonName = usePersonName;
	}


	/**
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}


	/**
	 * @return the catTime
	 */
	public Date getCatTime() {
		return catTime;
	}


	/**
	 * @param catTime the catTime to set
	 */
	public void setCatTime(Date catTime) {
		this.catTime = catTime;
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
	 * @param deadline the deadline to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	
	/**
	 * @return the hardWare
	 */
	public String getHardWare() {
		return hardWare;
	}


	/**
	 * @param hardWare the hardWare to set
	 */
	public void setHardWare(String hardWare) {
		this.hardWare = hardWare;
	}


	/**
	 * @return the softWare
	 */
	public String getSoftWare() {
		return softWare;
	}


	/**
	 * @param softWare the softWare to set
	 */
	public void setSoftWare(String softWare) {
		this.softWare = softWare;
	}


	/**
	 * @return the cIAValue
	 */
	public int getCIAValue() {
		return CIAValue;
	}


	/**
	 * @param cIAValue the cIAValue to set
	 */
	public void setCIAValue(int cIAValue) {
		CIAValue = cIAValue;
	}


	/**
	 * @return the field
	 */
	public List<AssetField> getField() {
		return field;
	}


	/**
	 * @param field the field to set
	 */
	public void setField(List<AssetField> field) {
		this.field = field;
	}


	/**
	 * @return the elsMasterName
	 */
	public String getElsMasterName() {
		return elsMasterName;
	}


	/**
	 * @param elsMasterName the elsMasterName to set
	 */
	public void setElsMasterName(String elsMasterName) {
		this.elsMasterName = elsMasterName;
	}


	/**
	 * @return the elsMac
	 */
	public String getElsMac() {
		return elsMac;
	}


	/**
	 * @param elsMac the elsMac to set
	 */
	public void setElsMac(String elsMac) {
		this.elsMac = elsMac;
	}


	/**
	 * @return the elsIps
	 */
	public String getElsIps() {
		return elsIps;
	}


	/**
	 * @param elsIps the elsIps to set
	 */
	public void setElsIps(String elsIps) {
		this.elsIps = elsIps;
	}


	/**
	 * @return the elsDomain
	 */
	public String getElsDomain() {
		return elsDomain;
	}


	/**
	 * @param elsDomain the elsDomain to set
	 */
	public void setElsDomain(String elsDomain) {
		this.elsDomain = elsDomain;
	}


	public String getText() {
		return super.getName();
	}
	
	
	/**
	 * @return the enviPath
	 */
	public String getEnviPath() {
		return enviPath;
	}


	/**
	 * @param enviPath the enviPath to set
	 */
	public void setEnviPath(String enviPath) {
		this.enviPath = enviPath;
	}


	/**
	 * 
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * 
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	

	/**
	 * @return the enviId
	 */
	public Long getEnviId() {
		return enviId;
	}

	/**
	 * @param enviId the enviId to set
	 */
	public void setEnviId(Long enviId) {
		this.enviId = enviId;
	}

	/**
	 * @return the enviName
	 */
	public String getEnviName() {
		return enviName;
	}

	/**
	 * @param enviName the enviName to set
	 */
	public void setEnviName(String enviName) {
		this.enviName = enviName;
	}

	/**
	 * 
	 * @return the hasOs
	 */
	public boolean isHasOs() {
		return hasOs;
	}

	/**
	 * 
	 * @param hasOs
	 *            the hasOs to set
	 */
	public void setHasOs(boolean hasOs) {
		this.hasOs = hasOs;
	}


	/**
	 * @return the osName
	 */
	public int getOsName() {
		return osName;
	}

	/**
	 * @param osName the osName to set
	 */
	public void setOsName(int osName) {
		this.osName = osName;
	}


	/**
	 * 
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * 
	 * @param producer
	 *            the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/**
	 * 
	 * @return the producerTel
	 */
	public String getProducerTel() {
		return producerTel;
	}

	/**
	 * 
	 * @param producerTel
	 *            the producerTel to set
	 */
	public void setProducerTel(String producerTel) {
		this.producerTel = producerTel;
	}

	/**
	 * 
	 * @return the produceTime
	 */
	public Date getProduceTime() {
		return produceTime;
	}

	/**
	 * 
	 * @param produceTime
	 *            the produceTime to set
	 */
	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}

	/**
	 * 
	 * @return the hasIp
	 */
	public boolean isHasIp() {
		return hasIp;
	}

	/**
	 * 
	 * @param hasIp
	 *            the hasIp to set
	 */
	public void setHasIp(boolean hasIp) {
		this.hasIp = hasIp;
	}

	/**
	 * 
	 * @return the virtual
	 */
	@Dictionary("bool")
	public boolean isVirtual() {
		return virtual;
	}

	/**
	 * 
	 * @param virtual
	 *            the virtual to set
	 */
	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}

	/**
	 * 
	 * @return the masterId
	 */
	public Long getMasterId() {
		return masterId;
	}

	/**
	 * 
	 * @param masterId
	 *            the masterId to set
	 */
	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	/**
	 * 
	 * @return the ips
	 */
	public List<IpAddress> getIps() {
		return ips;
	}

	/**
	 * 
	 * @param ips
	 *            the ips to set
	 */
	public void setIps(List<IpAddress> ips) {
		this.ips = ips;
	}

	@Override
	public String toString() {
		return "Device [model=" + model + ", hasOs=" + hasOs + ", osName="
				+ osName + ", producer="
				+ producer + ", producerTel=" + producerTel + ", produceTime="
				+ produceTime + ", hasIp=" + hasIp + ", virtual=" + virtual
				+ ", masterId=" + masterId + "]";
	}

}
