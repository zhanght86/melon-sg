/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.persistence.BaseDomain;


/**
 * @since 1.0 2014年9月24日,下午4:12:19
 * 资产的抽象类,覆盖了所有的公共属性
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@MappedSuperclass
public abstract class AbstractDevice extends BaseDomain<Long>{

	private static final long serialVersionUID = 4450727876262488795L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	//编码
	@Column(name = "A_CODE",length=100)
	private String code;
	
	//名称
	@Column(name = "A_NAME",length=100)
	private String name;
	
	//类型id
	@Column(name = "TYPE_ID", length=30)
	private Long typeId;
	
	//类型名称
	@Column(name = "TYPE_NAME", length=100)
	private String typeName;
	
	//类型路径
	@Column(name = "TYPE_PATH", length=30)
	private String typePath;
	
	//标示信息系统状态
	//1:已安装,2:测试中,3:使用中,4:维护中,5:报废,6:备用（默认）
	@Column(name="US_ING")
	private int using = 6;
	
	//系统的重要性,1:一级,2:二级,3:三级,4:四级
	@Column(name="IMP_LEVEL")
	private int important=1;
	
	//单位id
	@Column(name = "ORGAN_ID")
	private Long organId;
	
	//所属单位
	@Column(name = "ORGAN_NAME",length=100)
	private String organName;
	
	//单位路径
	@Column(name = "ORGAN_PATH", length=100)
	private String organPath;
	
	//创建时间
	@Column(name = "INFO_CREATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	private Date createTime;
	
	//负责人ID
	@Column(name="CHARGER_ID")
	private Long chargerId;
	
	//负责人NAME
	@Column(name="CHARGER_NAME")
	private String chargeName;
	
	//创建人
	@Column(name="PERSON_MODIFIERID")
	private Long modifierId;
	@Column(name="PERSON_MODIFIERNAME")
	private String modifierName;
	
	//备注
	@Column(name="REMARKS", length=500)
	@JsonIgnore
	private String remarks;
	
	//安全域ids
	@Transient
	private Long[] domainIds;
	
	//安全域names
	@Transient
	private String[] domainNames;
	
	
	//是否是正确数据默认正确true
	@Column(name="ISCORRECT")
	private boolean isCorrect = true;
	
	//自动生成标签的辅助字段,
    @Column(name="NUMBERSUM")
    private int numberSum=0;
	
	
	
	
	public int getNumberSum() {
		return numberSum;
	}

	public void setNumberSum(int numberSum) {
		this.numberSum = numberSum;
	}


	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	/**
	 * @return the modifierId
	 */
	public Long getModifierId() {
		return modifierId;
	}

	/**
	 * @param modifierId the modifierId to set
	 */
	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	
	
	/**
	 * @return the modifierName
	 */
	public String getModifierName() {
		return modifierName;
	}

	/**
	 * @param modifierName the modifierName to set
	 */
	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	/**
	 * 
	 * @return the domainIds
	 */
	public Long[] getDomainIds() {
		return domainIds;
	}

	/**
	 * 
	 * @param domainIds the domainIds to set
	 */
	public void setDomainIds(Long[] domainIds) {
		this.domainIds = domainIds;
	}

	/**
	 * 
	 * @return the domainNames
	 */
	public String[] getDomainNames() {
		return domainNames;
	}

	/**
	 * 
	 * @param domainNames the domainNames to set
	 */
	public void setDomainNames(String[] domainNames) {
		this.domainNames = domainNames;
	}
	
	/**
	 * 
	 * @return the important
	 */
	public int getImportant() {
		return important;
	}

	/**
	 * @return the typeId
	 */
	public Long getTypeId() {
		return typeId;
	}
	/**
	 * 
	 * @param important the important to set
	 */
	public void setImportant(int important) {
		this.important = important;
	}


	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Dictionary("usingState")
	public int getUsing() {
		return using;
	}
	
	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setUsing(int using) {
		this.using = using;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypePath() {
		return typePath;
	}

	public void setTypePath(String typePath) {
		this.typePath = typePath;
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

	public String getOrganPath() {
		return organPath;
	}

	public void setOrganPath(String organPath) {
		this.organPath = organPath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organId == null) ? 0 : organId.hashCode());
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
		AbstractDevice other = (AbstractDevice) obj;
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
		if (organId == null) {
			if (other.organId != null)
				return false;
		} else if (!organId.equals(other.organId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractDevice [id=" + id + ", code=" + code + ", name=" + name
				+ ", typePath=" + typePath + ", organId=" + organId
				+ ", organName=" + organName + ", organPath=" + organPath
				+"]";
	}
	
}
