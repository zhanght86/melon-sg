/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.domain;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import secfox.soc.melon.persistence.BaseDomain;
import secfox.soc.melon.system.GlobalConfigListener;

import com.google.common.collect.Maps;


/**
 * @since 1.0 2014年9月24日,下午3:10:30
 * 全局配置
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_SYSTEM_CONFIG")
@EntityListeners(value= {GlobalConfigListener.class})
public class GlobalConfig extends BaseDomain<Long> {

	private static final long serialVersionUID = 4761544475362987512L;
	
	//只保证插入一条数据,ID为1
	@Id@Column(name="PK")
	private Long id;
	
	//行业信息
	//当前单位ID
	@Column(name="ORGAN_ID")
	private Long organId;
	
	//当前单位名称
	@Column(name="ORGAN_NAME")
	private String organName;
	
	//密码强度
	@Column(name="PASS_STR")
	private String passStrengths;
	
	//密码最小长度
	@Transient
	@Column(name="PASS_MIN")
	private int passMin = 8;
	
	//密码最大长度
	@Transient
	@Column(name="PASS_MAX")
	private int passMax = 20;
	
	@Transient
	private Integer[] passStrength;
	
	@Transient
	private String passStr;
	
	/*//10行业类别
	private int tradeType;
	
	//系统服务
	//网络监控服务地址
	private String sniUrl;
	
	//日志审计服务地址
	private String lasUrl;
	
	//漏扫服务地址
	private String scanUrl;
	
	//基线服务地址
	private String baselineUrl;
	
	//使用超级权限
	private boolean superadmin = true;*/
	
	//密码失效时间,设置为<0时,密码不失效,否则为设置的天数,过了失效的天数时,将会提醒进行密码修改
	private int expiredDays = 0;
	
	//账号锁定时间间隔,默认10分钟
	private int lockedTime = 10;
	
	//系统开放起始时间
	private int openStart = 9;
	
	//系统开放终止时间
	private int openEnd = 20;
	
	//允许访问IP地址(最小)
	private String ipLeftRange;
	
	//允许访问IP地址(最大)
	private String ipRightRange;
	
	//启用登陆失败验证
	//最大失败登陆次数,当次数为0时,用户可以无限登陆,否则超过一定的次数,将进行锁定
	//尝试登陆次数
	private int tryTimes = 0;
	
	//账号有效期
	private int validity = 0;
	
	//是否使用三权分立
	private boolean threePriority = false;
	
	//支持信息
	//支持人员
	@Column(name="SERVICER", length=30)
	private String servicer;
	
	//支持电话
	@Column(name="SERVICE_TEL", length=30)
	private String serviceTel;
	
	//支持邮件
	@Column(name="SERVICE_Mail", length=50)
	private String serviceMail;
	
	
	
	//支持邮件
	@Column(name="system_Host", length=50)
	private String systemHost;
	//支持邮件
	@Column(name="system_Mail", length=50)
	private String systemMail;
	//支持邮件
	@Column(name="systemMail_PassWord", length=50)
	private String systemMailPassWord;
	
	
	
	
	
	/**
	 * @return the systemHost
	 */
	public String getSystemHost() {
		return systemHost;
	}

	/**
	 * @param systemHost the systemHost to set
	 */
	public void setSystemHost(String systemHost) {
		this.systemHost = systemHost;
	}

	/**
	 * @return the systemMail
	 */
	public String getSystemMail() {
		return systemMail;
	}

	/**
	 * @param systemMail the systemMail to set
	 */
	public void setSystemMail(String systemMail) {
		this.systemMail = systemMail;
	}

	/**
	 * @return the systemMailPassWord
	 */
	public String getSystemMailPassWord() {
		return systemMailPassWord;
	}

	/**
	 * @param systemMailPassWord the systemMailPassWord to set
	 */
	public void setSystemMailPassWord(String systemMailPassWord) {
		this.systemMailPassWord = systemMailPassWord;
	}

	//附加的属性
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="T_SYSTEM_ATTRS")
	@MapKeyColumn(name="G_ATTR")
	@Column(name="G_VALUE")
	private Map<String, String> attributes;
	
	/**
	 * 默认的构造器
	 */
	public GlobalConfig() {
		super();
	}
	
	/**
	 * 
	 * @param id
	 * @param organId
	 * @param organName
	 */
	public GlobalConfig(Long id, Long organId, String organName) {
		super();
		this.id = id;
		this.organId = organId;
		this.organName = organName;
		
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 添加额外的属性
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key, String value) {
		if(attributes == null) {
			attributes = Maps.newHashMap();
		}
		attributes.put(key, value);
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getServicer() {
		return servicer;
	}

	public void setServicer(String servicer) {
		this.servicer = servicer;
	}

	public String getServiceTel() {
		return serviceTel;
	}

	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

	public String getServiceMail() {
		return serviceMail;
	}

	public void setServiceMail(String serviceMail) {
		this.serviceMail = serviceMail;
	}
	
	public String getPassStrengths() {
		return passStrengths;
	}

	public void setPassStrengths(String passStrengths) {
		this.passStrengths = passStrengths;
	}

	public int getPassMin() {
		return passMin;
	}

	public void setPassMin(int passMin) {
		this.passMin = passMin;
	}

	public int getPassMax() {
		return passMax;
	}

	public void setPassMax(int passMax) {
		this.passMax = passMax;
	}

	public Integer[] getPassStrength() {
		return passStrength;
	}

	public void setPassStrength(Integer[] passStrength) {
		this.passStrength = passStrength;
	}

	public String getPassStr() {
		return passStr;
	}

	public void setPassStr(String passStr) {
		this.passStr = passStr;
	}
	
	public int getExpiredDays() {
		return expiredDays;
	}

	public void setExpiredDays(int expiredDays) {
		this.expiredDays = expiredDays;
	}

	public int getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(int lockedTime) {
		this.lockedTime = lockedTime;
	}

	public int getOpenStart() {
		return openStart;
	}

	public void setOpenStart(int openStart) {
		this.openStart = openStart;
	}

	public int getOpenEnd() {
		return openEnd;
	}

	public void setOpenEnd(int openEnd) {
		this.openEnd = openEnd;
	}

	public String getIpLeftRange() {
		return ipLeftRange;
	}

	public void setIpLeftRange(String ipLeftRange) {
		this.ipLeftRange = ipLeftRange;
	}

	public String getIpRightRange() {
		return ipRightRange;
	}

	public void setIpRightRange(String ipRightRange) {
		this.ipRightRange = ipRightRange;
	}

	public int getTryTimes() {
		return tryTimes;
	}

	public void setTryTimes(int tryTimes) {
		this.tryTimes = tryTimes;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public boolean isThreePriority() {
		return threePriority;
	}

	public void setThreePriority(boolean threePriority) {
		this.threePriority = threePriority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + expiredDays;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + lockedTime;
		result = prime * result + ((organId == null) ? 0 : organId.hashCode());
		result = prime * result + tryTimes;
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
		GlobalConfig other = (GlobalConfig) obj;
		if (expiredDays != other.expiredDays)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lockedTime != other.lockedTime)
			return false;
		if (organId == null) {
			if (other.organId != null)
				return false;
		} else if (!organId.equals(other.organId))
			return false;
		if (tryTimes != other.tryTimes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GlobalConfig [id=" + id + ", organId=" + organId
				+ ", lockedTime=" + lockedTime + ", openStart=" + openStart
				+ ", openEnd=" + openEnd + ", tryTimes=" + tryTimes + "]";
	}
	
}
