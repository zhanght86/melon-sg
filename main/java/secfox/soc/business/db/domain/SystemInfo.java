/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @since 1.0 2014年10月22日,上午11:31:32
 * 等级保护第二张表,只与第一张表相联系
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity(name="T_DB_SYSINFO")
public class SystemInfo extends BaseDomain<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_DB")
	private Long id;
	
	//单位基本信息ID
	@Column(name="BASIC_ID")
	private Long basicId;
	
	//信息系统名称
	@Column(name="SYS_NAME",length=50)
	private String sysName;
	
	//信息系统编码
	@Column(name="SYS_CODE",length=20)
	private String sysCode;
	
	//业务类型
	@Column(name="BUS_TYPE")
	private int busType;
	
	//业务描述
	@Column(name="BUS_DESC",length=200)
	private String busDesc;
	
	//服务范围
	@Column(name="SER_SCOPE")
	private int serScope;
	
	//跨省（区、市）、跨地（市、区）个数
	@Column(name="SER_COUNT")
	private int serCount;
	
	//服务对象
	@Column(name="SER_OBJECT")
	private int serObject;
	
	//覆盖范围
	@Column(name="NET_SCOPE")
	private int netScope;
	
	//网络性质
	@Column(name="NET_TYPE")
	private int netType;
	
	//网络性质-其他
	@Column(name="NET_OTHER")
	private String netOther;
	
	//系统互联情况
	@Column(name="CONN_TYPE")
	private int connType;
	
	//关键产品使用情况-安全专用产品数量
	@Column(name="DEV_COUNT_1")
	private int devCount_1;
	
	//关键产品使用情况-安全专用产品使用国产品率
	@Column(name="DEV_USERED_1")
	private int devUsed_1;
	
	//关键产品使用情况-安全专用产品使用国产品率-部分使用率 
	@Column(name="DEV_RATIO_1")
	private double devRatio_1;
	
	//关键产品使用情况-网络产品数量
	@Column(name="DEV_COUNT_2")
	private int devCount_2;
	
	//关键产品使用情况-网络产品使用国产品率
	@Column(name="DEV_USERED_2")
	private int devUsed_2;
	
	//关键产品使用情况-网络产品使用国产品率-部分使用率 
	@Column(name="DEV_RATIO_2")
	private double devRatio_2;
	
	//关键产品使用情况-操作系统数量
	@Column(name="DEV_COUNT_3")
	private int devCount_3;
	
	//关键产品使用情况-操作系统使用国产品率
	@Column(name="DEV_USERED_3")
	private int devUsed_3;
	
	//关键产品使用情况-操作系统使用国产品率-部分使用率 
	@Column(name="DEV_RATIO_3")
	private double devRatio_3;
	
	//关键产品使用情况-数据库数量
	@Column(name="DEV_COUNT_4")
	private int devCount_4;
	
	//关键产品使用情况-数据库使用国产品率
	@Column(name="DEV_USERED_4")
	private int devUsed_4;
	
	//关键产品使用情况-数据库使用国产品率-部分使用率 
	@Column(name="DEV_RATIO_4")
	private double devRatio_4;
	
	//关键产品使用情况-服务器数量
	@Column(name="DEV_COUNT_5")
	private int devCount_5;
	
	//关键产品使用情况-服务器使用国产品率
	@Column(name="DEV_USERED_5")
	private int devUsed_5;
	
	//关键产品使用情况-服务器使用国产品率-部分使用率 
	@Column(name="DEV_RATIO_5")
	private double devRatio_5;
	
	//关键产品使用情况-其他
	@Column(name="DEV_OTHER_6")
	private String devOther_6;
	
	//关键产品使用情况-其他数量
	@Column(name="DEV_COUNT_6")
	private int devCount_6;
	
	//关键产品使用情况-其他使用国产品率
	@Column(name="DEV_USERED_6")
	private int devUsed_6;
	
	//关键产品使用情况-其他使用国产品率-部分使用率 
	@Column(name="DEV_RATIO_6")
	private double devRatio_6;
	
	//系统采用服务情况-等级测评服务类型
	@Column(name="SER_USERED_1")
	private boolean serUsered_1;
	
	//系统采用服务情况-等级测评服务责任方类型
	@Column(name="SER_BELONG_1")
	private int serBelong_1;
	
	//系统采用服务情况-风险评估服务类型
	@Column(name="SER_USERED_2")
	private boolean serUsered_2;
	
	//系统采用服务情况-风险评估服务责任方类型
	@Column(name="SER_BELONG_2")
	private int serBelong_2;
	
	//系统采用服务情况-灾难恢复服务类型
	@Column(name="SER_USERED_3")
	private boolean serUsered_3;
	
	//系统采用服务情况-灾难恢复服务责任方类型
	@Column(name="SER_BELONG_3")
	private int serBelong_3;
	
	//系统采用服务情况-应急响应服务类型
	@Column(name="SER_USERED_4")
	private boolean serUsered_4;
	
	//系统采用服务情况-应急响应服务责任方类型
	@Column(name="SER_BELONG_4")
	private int serBelong_4;
	
	//系统采用服务情况-系统集成服务类型
	@Column(name="SER_USERED_5")
	private boolean serUsered_5;
	
	//系统采用服务情况-系统集成服务责任方类型
	@Column(name="SER_BELONG_5")
	private int serBelong_5;
	
	//系统采用服务情况-安全咨询服务类型
	@Column(name="SER_USERED_6")
	private boolean serUsered_6;
	
	//系统采用服务情况-安全咨询服务责任方类型
	@Column(name="SER_BELONG_6")
	private int serBelong_6;
	
	//系统采用服务情况-安全培训服务类型
	@Column(name="SER_USERED_7")
	private boolean serUsered_7;
	
	//系统采用服务情况-安全培训服务责任方类型
	@Column(name="SER_BELONG_7")
	private int serBelong_7;
	
	//系统采用服务情况-其他服务类型
	@Column(name="SER_USERED_8")
	private boolean serUsered_8;
	
	//系统采用服务情况-其他
	@Column(name="SER_USERED_OTHER")
	private String serUsered_other;
	
	//系统采用服务情况-其他服务责任方类型
	@Column(name="SER_BELONG_8")
	private int serBelong_8;
	
	//等级测评单位名称
	@Column(name="EX_ORGAN_NAME",length=50)
	private String exOrganName;
	
	//何时投入运行使用
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	@Column(name="EX_TIME")
	private Date exTime;
	
	//系统是否是分系统
	@Column(name="SON_SYSTEM")
	private boolean sonSystem;
	
	//上级系统名称
	@Column(name="PAR_SYS",length=50)
	private String parentSys;
	
	//上级系统所属单位
	@Column(name="PARSYS_ORGAN",length=50)
	private String parentSysOrgan;
	
	//创建时间
	@Column(name="CREATE_TIME")
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

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
	 * @return the basicId
	 */
	public Long getBasicId() {
		return basicId;
	}

	/**
	 * @param basicId the basicId to set
	 */
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}

	/**
	 * @return the sysName
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * @param sysName the sysName to set
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * @return the sysCode
	 */
	public String getSysCode() {
		return sysCode;
	}

	/**
	 * @param sysCode the sysCode to set
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * @return the busType
	 */
	public int getBusType() {
		return busType;
	}

	/**
	 * @param busType the busType to set
	 */
	public void setBusType(int busType) {
		this.busType = busType;
	}

	/**
	 * @return the busDesc
	 */
	public String getBusDesc() {
		return busDesc;
	}

	/**
	 * @param busDesc the busDesc to set
	 */
	public void setBusDesc(String busDesc) {
		this.busDesc = busDesc;
	}

	/**
	 * @return the serScope
	 */
	public int getSerScope() {
		return serScope;
	}

	/**
	 * @param serScope the serScope to set
	 */
	public void setSerScope(int serScope) {
		this.serScope = serScope;
	}

	/**
	 * @return the serCount
	 */
	public int getSerCount() {
		return serCount;
	}

	/**
	 * @param serCount the serCount to set
	 */
	public void setSerCount(int serCount) {
		this.serCount = serCount;
	}

	/**
	 * @return the serObject
	 */
	public int getSerObject() {
		return serObject;
	}

	/**
	 * @param serObject the serObject to set
	 */
	public void setSerObject(int serObject) {
		this.serObject = serObject;
	}

	/**
	 * @return the netScope
	 */
	public int getNetScope() {
		return netScope;
	}

	/**
	 * @param netScope the netScope to set
	 */
	public void setNetScope(int netScope) {
		this.netScope = netScope;
	}

	/**
	 * @return the netType
	 */
	public int getNetType() {
		return netType;
	}

	/**
	 * @param netType the netType to set
	 */
	public void setNetType(int netType) {
		this.netType = netType;
	}

	/**
	 * @return the netOther
	 */
	public String getNetOther() {
		return netOther;
	}

	/**
	 * @param netOther the netOther to set
	 */
	public void setNetOther(String netOther) {
		this.netOther = netOther;
	}

	/**
	 * @return the connType
	 */
	public int getConnType() {
		return connType;
	}

	/**
	 * @param connType the connType to set
	 */
	public void setConnType(int connType) {
		this.connType = connType;
	}

	/**
	 * @return the devCount_1
	 */
	public int getDevCount_1() {
		return devCount_1;
	}

	/**
	 * @param devCount_1 the devCount_1 to set
	 */
	public void setDevCount_1(int devCount_1) {
		this.devCount_1 = devCount_1;
	}

	/**
	 * @return the devUsed_1
	 */
	public int getDevUsed_1() {
		return devUsed_1;
	}

	/**
	 * @param devUsed_1 the devUsed_1 to set
	 */
	public void setDevUsed_1(int devUsed_1) {
		this.devUsed_1 = devUsed_1;
	}

	/**
	 * @return the devRatio_1
	 */
	public double getDevRatio_1() {
		return devRatio_1;
	}

	/**
	 * @param devRatio_1 the devRatio_1 to set
	 */
	public void setDevRatio_1(double devRatio_1) {
		this.devRatio_1 = devRatio_1;
	}

	/**
	 * @return the devCount_2
	 */
	public int getDevCount_2() {
		return devCount_2;
	}

	/**
	 * @param devCount_2 the devCount_2 to set
	 */
	public void setDevCount_2(int devCount_2) {
		this.devCount_2 = devCount_2;
	}

	/**
	 * @return the devUsed_2
	 */
	public int getDevUsed_2() {
		return devUsed_2;
	}

	/**
	 * @param devUsed_2 the devUsed_2 to set
	 */
	public void setDevUsed_2(int devUsed_2) {
		this.devUsed_2 = devUsed_2;
	}

	/**
	 * @return the devRatio_2
	 */
	public double getDevRatio_2() {
		return devRatio_2;
	}

	/**
	 * @param devRatio_2 the devRatio_2 to set
	 */
	public void setDevRatio_2(double devRatio_2) {
		this.devRatio_2 = devRatio_2;
	}

	/**
	 * @return the devCount_3
	 */
	public int getDevCount_3() {
		return devCount_3;
	}

	/**
	 * @param devCount_3 the devCount_3 to set
	 */
	public void setDevCount_3(int devCount_3) {
		this.devCount_3 = devCount_3;
	}

	/**
	 * @return the devUsed_3
	 */
	public int getDevUsed_3() {
		return devUsed_3;
	}

	/**
	 * @param devUsed_3 the devUsed_3 to set
	 */
	public void setDevUsed_3(int devUsed_3) {
		this.devUsed_3 = devUsed_3;
	}

	/**
	 * @return the devRatio_3
	 */
	public double getDevRatio_3() {
		return devRatio_3;
	}

	/**
	 * @param devRatio_3 the devRatio_3 to set
	 */
	public void setDevRatio_3(double devRatio_3) {
		this.devRatio_3 = devRatio_3;
	}

	/**
	 * @return the devCount_4
	 */
	public int getDevCount_4() {
		return devCount_4;
	}

	/**
	 * @param devCount_4 the devCount_4 to set
	 */
	public void setDevCount_4(int devCount_4) {
		this.devCount_4 = devCount_4;
	}

	/**
	 * @return the devUsed_4
	 */
	public int getDevUsed_4() {
		return devUsed_4;
	}

	/**
	 * @param devUsed_4 the devUsed_4 to set
	 */
	public void setDevUsed_4(int devUsed_4) {
		this.devUsed_4 = devUsed_4;
	}

	/**
	 * @return the devRatio_4
	 */
	public double getDevRatio_4() {
		return devRatio_4;
	}

	/**
	 * @param devRatio_4 the devRatio_4 to set
	 */
	public void setDevRatio_4(double devRatio_4) {
		this.devRatio_4 = devRatio_4;
	}

	/**
	 * @return the devCount_5
	 */
	public int getDevCount_5() {
		return devCount_5;
	}

	/**
	 * @param devCount_5 the devCount_5 to set
	 */
	public void setDevCount_5(int devCount_5) {
		this.devCount_5 = devCount_5;
	}

	/**
	 * @return the devUsed_5
	 */
	public int getDevUsed_5() {
		return devUsed_5;
	}

	/**
	 * @param devUsed_5 the devUsed_5 to set
	 */
	public void setDevUsed_5(int devUsed_5) {
		this.devUsed_5 = devUsed_5;
	}

	/**
	 * @return the devRatio_5
	 */
	public double getDevRatio_5() {
		return devRatio_5;
	}

	/**
	 * @param devRatio_5 the devRatio_5 to set
	 */
	public void setDevRatio_5(double devRatio_5) {
		this.devRatio_5 = devRatio_5;
	}

	/**
	 * @return the devOther_6
	 */
	public String getDevOther_6() {
		return devOther_6;
	}

	/**
	 * @param devOther_6 the devOther_6 to set
	 */
	public void setDevOther_6(String devOther_6) {
		this.devOther_6 = devOther_6;
	}

	/**
	 * @return the devCount_6
	 */
	public int getDevCount_6() {
		return devCount_6;
	}

	/**
	 * @param devCount_6 the devCount_6 to set
	 */
	public void setDevCount_6(int devCount_6) {
		this.devCount_6 = devCount_6;
	}

	/**
	 * @return the devUsed_6
	 */
	public int getDevUsed_6() {
		return devUsed_6;
	}

	/**
	 * @param devUsed_6 the devUsed_6 to set
	 */
	public void setDevUsed_6(int devUsed_6) {
		this.devUsed_6 = devUsed_6;
	}

	/**
	 * @return the devRatio_6
	 */
	public double getDevRatio_6() {
		return devRatio_6;
	}

	/**
	 * @param devRatio_6 the devRatio_6 to set
	 */
	public void setDevRatio_6(double devRatio_6) {
		this.devRatio_6 = devRatio_6;
	}

	/**
	 * @return the serUsered_1
	 */
	public boolean isSerUsered_1() {
		return serUsered_1;
	}

	/**
	 * @param serUsered_1 the serUsered_1 to set
	 */
	public void setSerUsered_1(boolean serUsered_1) {
		this.serUsered_1 = serUsered_1;
	}

	/**
	 * @return the serBelong_1
	 */
	public int getSerBelong_1() {
		return serBelong_1;
	}

	/**
	 * @param serBelong_1 the serBelong_1 to set
	 */
	public void setSerBelong_1(int serBelong_1) {
		this.serBelong_1 = serBelong_1;
	}

	/**
	 * @return the serUsered_2
	 */
	public boolean isSerUsered_2() {
		return serUsered_2;
	}

	/**
	 * @param serUsered_2 the serUsered_2 to set
	 */
	public void setSerUsered_2(boolean serUsered_2) {
		this.serUsered_2 = serUsered_2;
	}

	/**
	 * @return the serBelong_2
	 */
	public int getSerBelong_2() {
		return serBelong_2;
	}

	/**
	 * @param serBelong_2 the serBelong_2 to set
	 */
	public void setSerBelong_2(int serBelong_2) {
		this.serBelong_2 = serBelong_2;
	}

	/**
	 * @return the serUsered_3
	 */
	public boolean isSerUsered_3() {
		return serUsered_3;
	}

	/**
	 * @param serUsered_3 the serUsered_3 to set
	 */
	public void setSerUsered_3(boolean serUsered_3) {
		this.serUsered_3 = serUsered_3;
	}

	/**
	 * @return the serBelong_3
	 */
	public int getSerBelong_3() {
		return serBelong_3;
	}

	/**
	 * @param serBelong_3 the serBelong_3 to set
	 */
	public void setSerBelong_3(int serBelong_3) {
		this.serBelong_3 = serBelong_3;
	}

	/**
	 * @return the serUsered_4
	 */
	public boolean isSerUsered_4() {
		return serUsered_4;
	}

	/**
	 * @param serUsered_4 the serUsered_4 to set
	 */
	public void setSerUsered_4(boolean serUsered_4) {
		this.serUsered_4 = serUsered_4;
	}

	/**
	 * @return the serBelong_4
	 */
	public int getSerBelong_4() {
		return serBelong_4;
	}

	/**
	 * @param serBelong_4 the serBelong_4 to set
	 */
	public void setSerBelong_4(int serBelong_4) {
		this.serBelong_4 = serBelong_4;
	}

	/**
	 * @return the serUsered_5
	 */
	public boolean isSerUsered_5() {
		return serUsered_5;
	}

	/**
	 * @param serUsered_5 the serUsered_5 to set
	 */
	public void setSerUsered_5(boolean serUsered_5) {
		this.serUsered_5 = serUsered_5;
	}

	/**
	 * @return the serBelong_5
	 */
	public int getSerBelong_5() {
		return serBelong_5;
	}

	/**
	 * @param serBelong_5 the serBelong_5 to set
	 */
	public void setSerBelong_5(int serBelong_5) {
		this.serBelong_5 = serBelong_5;
	}

	/**
	 * @return the serUsered_6
	 */
	public boolean isSerUsered_6() {
		return serUsered_6;
	}

	/**
	 * @param serUsered_6 the serUsered_6 to set
	 */
	public void setSerUsered_6(boolean serUsered_6) {
		this.serUsered_6 = serUsered_6;
	}

	/**
	 * @return the serBelong_6
	 */
	public int getSerBelong_6() {
		return serBelong_6;
	}

	/**
	 * @param serBelong_6 the serBelong_6 to set
	 */
	public void setSerBelong_6(int serBelong_6) {
		this.serBelong_6 = serBelong_6;
	}

	/**
	 * @return the serUsered_7
	 */
	public boolean isSerUsered_7() {
		return serUsered_7;
	}

	/**
	 * @param serUsered_7 the serUsered_7 to set
	 */
	public void setSerUsered_7(boolean serUsered_7) {
		this.serUsered_7 = serUsered_7;
	}

	/**
	 * @return the serBelong_7
	 */
	public int getSerBelong_7() {
		return serBelong_7;
	}

	/**
	 * @param serBelong_7 the serBelong_7 to set
	 */
	public void setSerBelong_7(int serBelong_7) {
		this.serBelong_7 = serBelong_7;
	}

	/**
	 * @return the serUsered_8
	 */
	public boolean isSerUsered_8() {
		return serUsered_8;
	}
	
	/**
	 * @return the serUsered_other
	 */
	public String getSerUsered_other() {
		return serUsered_other;
	}

	/**
	 * @param serUsered_other the serUsered_other to set
	 */
	public void setSerUsered_other(String serUsered_other) {
		this.serUsered_other = serUsered_other;
	}

	/**
	 * @param serUsered_8 the serUsered_8 to set
	 */
	public void setSerUsered_8(boolean serUsered_8) {
		this.serUsered_8 = serUsered_8;
	}

	/**
	 * @return the serBelong_8
	 */
	public int getSerBelong_8() {
		return serBelong_8;
	}

	/**
	 * @param serBelong_8 the serBelong_8 to set
	 */
	public void setSerBelong_8(int serBelong_8) {
		this.serBelong_8 = serBelong_8;
	}

	/**
	 * @return the exOrganName
	 */
	public String getExOrganName() {
		return exOrganName;
	}

	/**
	 * @param exOrganName the exOrganName to set
	 */
	public void setExOrganName(String exOrganName) {
		this.exOrganName = exOrganName;
	}

	/**
	 * @return the exTime
	 */
	public Date getExTime() {
		return exTime;
	}

	/**
	 * @param exTime the exTime to set
	 */
	public void setExTime(Date exTime) {
		this.exTime = exTime;
	}

	/**
	 * @return the sonSystem
	 */
	public boolean isSonSystem() {
		return sonSystem;
	}

	/**
	 * @param sonSystem the sonSystem to set
	 */
	public void setSonSystem(boolean sonSystem) {
		this.sonSystem = sonSystem;
	}

	/**
	 * @return the parentSys
	 */
	public String getParentSys() {
		return parentSys;
	}

	/**
	 * @param parentSys the parentSys to set
	 */
	public void setParentSys(String parentSys) {
		this.parentSys = parentSys;
	}

	/**
	 * @return the parentSysOrgan
	 */
	public String getParentSysOrgan() {
		return parentSysOrgan;
	}

	/**
	 * @param parentSysOrgan the parentSysOrgan to set
	 */
	public void setParentSysOrgan(String parentSysOrgan) {
		this.parentSysOrgan = parentSysOrgan;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basicId == null) ? 0 : basicId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sysCode == null) ? 0 : sysCode.hashCode());
		result = prime * result + ((sysName == null) ? 0 : sysName.hashCode());
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
		SystemInfo other = (SystemInfo) obj;
		if (basicId == null) {
			if (other.basicId != null)
				return false;
		} else if (!basicId.equals(other.basicId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sysCode == null) {
			if (other.sysCode != null)
				return false;
		} else if (!sysCode.equals(other.sysCode))
			return false;
		if (sysName == null) {
			if (other.sysName != null)
				return false;
		} else if (!sysName.equals(other.sysName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SystemInfo [id=" + id + ", basicId=" + basicId + ", sysName="
				+ sysName + ", sysCode=" + sysCode + ", busType=" + busType
				+ ", busDesc=" + busDesc + ", serScope=" + serScope
				+ ", serCount=" + serCount + ", serObject=" + serObject
				+ ", netScope=" + netScope + ", netType=" + netType
				+ ", netOther=" + netOther + ", connType=" + connType
				+ ", devCount_1=" + devCount_1 + ", devUsed_1=" + devUsed_1
				+ ", devRatio_1=" + devRatio_1 + ", devCount_2=" + devCount_2
				+ ", devUsed_2=" + devUsed_2 + ", devRatio_2=" + devRatio_2
				+ ", devCount_3=" + devCount_3 + ", devUsed_3=" + devUsed_3
				+ ", devRatio_3=" + devRatio_3 + ", devCount_4=" + devCount_4
				+ ", devUsed_4=" + devUsed_4 + ", devRatio_4=" + devRatio_4
				+ ", devCount_5=" + devCount_5 + ", devUsed_5=" + devUsed_5
				+ ", devRatio_5=" + devRatio_5 + ", devCount_6=" + devCount_6
				+ ", devUsed_6=" + devUsed_6 + ", devRatio_6=" + devRatio_6
				+ ", serUsered_1=" + serUsered_1 + ", serBelong_1="
				+ serBelong_1 + ", serUsered_2=" + serUsered_2
				+ ", serBelong_2=" + serBelong_2 + ", serUsered_3="
				+ serUsered_3 + ", serBelong_3=" + serBelong_3
				+ ", serUsered_4=" + serUsered_4 + ", serBelong_4="
				+ serBelong_4 + ", serUsered_5=" + serUsered_5
				+ ", serBelong_5=" + serBelong_5 + ", serUsered_6="
				+ serUsered_6 + ", serBelong_6=" + serBelong_6
				+ ", serUsered_7=" + serUsered_7 + ", serBelong_7="
				+ serBelong_7 + ", serUsered_8=" + serUsered_8
				+ ", serBelong_8=" + serBelong_8 + ", exOrganName="
				+ exOrganName + ", exTime=" + exTime + ", sonSystem="
				+ sonSystem + ", parentSys=" + parentSys + ", parentSysOrgan="
				+ parentSysOrgan + "]";
	}
}
