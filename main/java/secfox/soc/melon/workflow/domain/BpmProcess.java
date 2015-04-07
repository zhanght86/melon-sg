/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.workflow.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年1月18日,下午8:50:49
 * 流程定义
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_BPM_PROCESSA")
public class BpmProcess extends BaseDomain<Long> {
	
	private static final long serialVersionUID = 8697483107196496517L;

	//主键
	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//流程编码
	@Column(name = "B_CODE",length=60)
	private String code;
	
	//流程名称
	@Column(name = "B_NAME",length=100)
	private String name;
	
	//流程类型
	@Column(name = "B_TYPE")
	private Long type; 
	
	//创建人
	@Embedded
	private UserInfo creater;
	
	//创建时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	/**
	 * 流程变量
	 */
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="T_BPM_PROC_MAP", joinColumns= {@JoinColumn(name="PK")})
    @MapKeyColumn(name="FIELD_NAME")
    @Column(name="FIELD_VALUE")
    private Map<String, String> props;
	
	//备注
	@Column(name = "REMARKS",length=1000)
	private String remarks;
	
	//版本号
	@Version
	@Column(name = "B_VERSION")
	private int version;
	
	/**
	 * 添加流程变量
	 */
	public void addProp(String key, String value) {
		if(props == null) {
			props = Maps.newHashMap();
		}
		props.put(key, value);
	}
	

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**
	 * @return the creater
	 */
	public UserInfo getCreater() {
		return creater;
	}

	/**
	 * @param creater the creater to set
	 */
	public void setCreater(UserInfo creater) {
		this.creater = creater;
	}

	
	
	/**
	 * @return the props
	 */
	public Map<String, String> getProps() {
		return props;
	}


	/**
	 * @param props the props to set
	 */
	public void setProps(Map<String, String> props) {
		this.props = props;
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

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * @return the remark
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemarks(String remark) {
		this.remarks = remark;
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
		BpmProcess other = (BpmProcess) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + version;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BpmProcess [id=" + id + ", code=" + code + ", name=" + name
				+ ", creater=" + creater + ", createTime=" + createTime
				+ ", version=" + version + "]";
	}

}
