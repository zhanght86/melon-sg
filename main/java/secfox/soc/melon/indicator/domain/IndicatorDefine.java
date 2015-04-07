/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.indicator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import secfox.soc.melon.persistence.TreeDomain;

/**
 * @since 1.0 2014年10月14日,下午2:02:41
 * 指标定义,此类为开发人员使用,不向用户开放
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */

@Entity 
@Table(name = "T_INDICATOR_DEFINE")
@NamedQueries({
	@NamedQuery(name="indicator.findAll", query="select a from IndicatorDefine a  order by a.order"),
	@NamedQuery(name="indicator.remove", query="delete from IndicatorDefine a where a.path like :path"),
	@NamedQuery(name="indicator.findCountByParentId", query="select a from IndicatorDefine a where a.parentId =:parentId")
})
public class IndicatorDefine extends TreeDomain<Long>{ 

	
	private static final long serialVersionUID = 8716089301838421068L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_IND")
	private Long id;
	
	//指标名称
	@Column(name="I_NAME", length=100)
	private String name;
	
	//编码,编码方式必须父编码*100+自身体系中的顺序指,不能重复(保证唯一性)
	//获取指标值的时候会按照编码与类型的组合方式
	@Column(name="I_CODE",unique=true)
	private Long code;
	
	//0:默认,1:设备对象,2:信息系统,3:单位,4:个人
	//类型,决定了指标值的存储位置以及与安全对象的关联方式,如个人都将统计与自身相关的指标
	@Column(name="I_TYPE")
	private int type=0;
	
	//获取方式,0:从数据库获取,1:从服务中获取,3:从外部服务获取
	@Column(name="T_WAY")
	private int way=0;
	
	//外部服务地址,当获取方式大于0,使用此配置
	@Column(name="SER_ADDR")
	private String serviceAddress;
	
	//指标层次路径,用于数据钻取
	@Column(name="I_PATH", length=100)
	private String path;
	
	//父节点主键
	@Column(name="PARENT_ID")
	private Long parentId;
	
	//优先级 ,数字越小,级别越高,就越会优先执行
	@Column(name="M_ORDER")
	private int order = 0;
	
	//已启用
	@Column(name="M_ENABLED")
	private boolean enabled = true;
	
	//含参数的格式化字符串,必须包含{0},{1}字样
	@Column(name="FORMATTER")
	private String formatter;
	
	//指标说明
	@Column(name="REMARKS", length=1000)
	private String remarks;

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
	 * 
	 * @return the way
	 */
	public int getWay() {
		return way;
	}

	/**
	 * 
	 * @param way the way to set
	 */
	public void setWay(int way) {
		this.way = way;
	}

	/**
	 * 
	 * @return the serviceAddress
	 */
	public String getServiceAddress() {
		return serviceAddress;
	}

	/**
	 * 
	 * @param serviceAddress the serviceAddress to set
	 */
	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
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
	 * @return the code
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Long code) {
		this.code = code;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the formatter
	 */
	public String getFormatter() {
		return formatter;
	}

	/**
	 * @param formatter the formatter to set
	 */
	public void setFormatter(String formatter) {
		this.formatter = formatter;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((formatter == null) ? 0 : formatter.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		IndicatorDefine other = (IndicatorDefine) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (enabled != other.enabled)
			return false;
		if (formatter == null) {
			if (other.formatter != null)
				return false;
		} else if (!formatter.equals(other.formatter))
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
		if (order != other.order)
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IndicatorDefine [id=" + id + ", name=" + name + ", code="
				+ code + ", type=" + type + ", path=" + path + ", parentId="
				+ parentId + ", order=" + order + "]";
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
