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

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.persistence.TreeDomain;

/**
 * @since 1.0 2014年10月10日,下午3:37:22
 * 安全域
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_ASSET_GROUP")
@NamedQueries({
	//获取所有安全域
	@NamedQuery(name="assetGroup.findAll", query="select a from AssetGroup a "),
	//根据parentId查找安全域
	@NamedQuery(name="assetGroup.findByParentId",query="select a from AssetGroup a where a.parentId = :parentId"),
	//根据单位查安全域
	@NamedQuery(name="assetGroup.findByOrgan", query="select a from AssetGroup a where a.organId =:organId order by a.order"),
	//删除安全域及子安全域
	@NamedQuery(name="assetGroup.remove", query="delete  from AssetGroup a where a.path like :path"),
	//通过名称获取安全域
	@NamedQuery(name="assetGroup.findByName", query="delete  from AssetGroup a where a.name =:name"),
	//获取到最大的numbersum
	@NamedQuery(name="assetGroup.findNumberSum", query="select a from AssetGroup a where a.organId = :organId order by a.numberSum desc"),
	//yb 根据typePath查询设备
	@NamedQuery(name = "assetGroup.findByTypePath", query = "select a from AssetGroup a where (a.typePath like :path or a.typePath like :rightPath) and (a.organPath like :organPath or a.organId=:organId)"),
	//根据type获取该type及以下type下的安全域
	@NamedQuery(name = "assetGroup.findByTypeChlid", query = "select a from AssetGroup a where a.typePath like :typePath or a.typeId = :typeId")
})
public class AssetGroup extends TreeDomain<Long>{
	
	private static final long serialVersionUID = -4779889562074388103L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;

	//名称
	@Column(name="T_NAME",length=100)
    private String name;
	
	//标签（编号改为标签）
	@Column(name="T_CODE",length=200)
	private String code;
	
	//顺序
	@Column(name="T_ORDER")
    private int order = 0;
    
	//路径
	@Column(name="PATH", length=100)
	private String path;
	
	//深度
	@Column(name="DEPTH")
	private int depth = 1;
	
    //所属单位ID
    @Column(name = "ORGAN_ID")
    private Long organId;
    
    //单位名称
  	@Column(name = "ORGAN_NAME",length=100)
  	private String organName;
  	
  	//单位path
  	@Column(name = "ORGAN_PATH")
  	private String organPath;
  	
    //负责人ID
  	@Column(name = "CH_ID")
  	private Long chargerId;
  	
  	//负责人
  	@Column(name = "CH_NAME",length=50)
  	private String chargerName;
  	
  	//类型id
  	@Column(name = "TYPE_ID", length=30)
  	private Long typeId;
  	
  	//类型名称
  	@Column(name = "TYPE_NAME", length=100)
  	private String typeName;
  	
  	//类型路径
  	@Column(name = "TYPE_PATH", length=30)
  	private String typePath;
  	
  	//物理环境id
  	@Column(name = "ENVI_ID")
  	private Long enviId;
  	
  	//物理环境name
  	@Column(name = "ENVI_NAME")
  	private String enviName;
  	
  	//物理环境路径
  	@Column(name = "ENVI_PATH")
  	private String enviPath;
  	
  	
  	//网络类型 0:业务专网 ,1:互联网, 2:外联网
  	@Column(name = "NET_TYPE")
  	private int netType = 0;
  	
  	//防护等级1:低,2:较低,3:中,4:较高,5:高
  	@Column(name = "GUARD_RANK")
  	private int guardRank=5;
  	
  	

	//创建人
	@Column(name="PERSON_MODIFIERID")
	private Long modifierId;
	@Column(name="PERSON_MODIFIERNAME")
	private Long modifierName;
  	//创建时间
  	@Column(name = "CREATE_TIME")
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
  	
  	//使用状态  0：停用,1：启用（默认）
  	@Column(name = "USE_STATE")
  	private boolean useState = true;
  	
  	//父节点
  	@Column(name="TYPE_PARENTID")
  	private Long parentId;
	
    //备注
    @Column(name="REMARKS",length=200)
    private String remarks;
    
    //自动生成标签的辅助字段,
    @Column(name="NUMBERSUM")
    private int numberSum=0;
    
    

	public int getNumberSum() {
		return numberSum;
	}

	public void setNumberSum(int numberSum) {
		this.numberSum = numberSum;
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
	public Long getModifierName() {
		return modifierName;
	}

	/**
	 * @param modifierName the modifierName to set
	 */
	public void setModifierName(Long modifierName) {
		this.modifierName = modifierName;
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
     * @return
     */
    @Dictionary("netType")
    public int getCategory() {
    	return this.netType;
    }
    
    @Dictionary("guardRank")
    public int getValue(){
    	return this.guardRank;
    }
    
    public int getWeight(){
    	return 1;
    }
    
    public String getSource(){
    	return this.name;
    }
    
    
    

	/**
	 * @return the organPath
	 */
	public String getOrganPath() {
		return organPath;
	}

	/**
	 * @param organPath the organPath to set
	 */
	public void setOrganPath(String organPath) {
		this.organPath = organPath;
	}

	/**
	 * @return the typeId
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the typePath
	 */
	public String getTypePath() {
		return typePath;
	}

	/**
	 * @param typePath the typePath to set
	 */
	public void setTypePath(String typePath) {
		this.typePath = typePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public Long getChargerId() {
		return chargerId;
	}

	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}

	public String getChargerName() {
		return chargerName;
	}

	public void setChargerName(String chargerName) {
		this.chargerName = chargerName;
	}

	@Dictionary("netType")
	public int getNetType() {
		return netType;
	}

	public void setNetType(int netType) {
		this.netType = netType;
	}

	public int getGuardRank() {
		return guardRank;
	}

	public void setGuardRank(int guardRank) {
		this.guardRank = guardRank;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Dictionary("useState")
	public boolean isUseState() {
		return useState;
	}

	public void setUseState(boolean useState) {
		this.useState = useState;
	}

	
	
	public String getRemarks() {
		return remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chargerId == null) ? 0 : chargerId.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organId == null) ? 0 : organId.hashCode());
		result = prime * result
				+ ((organName == null) ? 0 : organName.hashCode());
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
		AssetGroup other = (AssetGroup) obj;
		if (chargerId == null) {
			if (other.chargerId != null)
				return false;
		} else if (!chargerId.equals(other.chargerId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
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
		if (organName == null) {
			if (other.organName != null)
				return false;
		} else if (!organName.equals(other.organName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssetGroup [name=" + name + ", code=" + code + ", order="
				+ order + ", path=" + path + ", organId=" + organId
				+ ", organName=" + organName + ", chargerId=" + chargerId
				+ ", chargerName=" + chargerName + ", netType=" + netType
				+ ", guardRank=" + guardRank + ", createTime=" + createTime
				+ ", useState=" + useState + ", remarks=" + remarks + "]";
	}

	@Override
	public String getText() {
		return this.name;
	}

	public Object getParent() {
		if(this.isAsRoot()) {
			return "#";
		}
		return parentId == null ? "#" : parentId;
	}
    
	
}
