package secfox.soc.las.domain;

import java.util.Date;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.base.util.DictionaryUtils;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * 规则实体类
 * @since 2015年2月2日,上午11:33:58
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Entity
@Table(name = "T_Alert")
public class Alert extends BaseDomain<String> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 规则id
	 */
	@Id@Column(name="ID", length = 125)
	@GeneratedValue(generator="GEN_SEQ_APP")
	private String id;

	/**
	 * 规则名称
	 */
	@Column(name = "name", length = 125)
	private String name;

	/**
	 * 规则内容xml
	 */
	@Column(name = "Rule_content", length = 256)
	private String condition;

	/**
	 * 描述
	 */
	@Column(name = "Description", length = 256)
	private String description;

	/**
	 * 创建时间
	 */
	@Column(name = "Createtime", length = 256)
	private Date createTime;

	/**
	 * 创建人
	 */
	@Column(name = "creator", length = 4)
	private Long creator;

	/**
	 * 更新时间
	 */
	@Column(name = "updateTime", length = 256)
	private Date updateTime;

	/**
	 * 
	 */
	@Column(name = "Mender", length = 20)
	private Long Mender;

	/**
	 * 
	 */
	@Column(name = "type", length = 4)
	private int type;
	
	/**
	 * 父id
	 */
	@Column(name = "ParentId", length = 10)
	private int ParentId;
	
	/**
	 * 配置动作
	 */
	@Column(name = "action", length = 256)
	private String action;
	
	/**
	 * 是否 启用
	 */
	@Column(name = "enabled", length = 4)
	private int enabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getMender() {
		return Mender;
	}

	public void setMender(Long mender) {
		Mender = mender;
	}

	@Dictionary("las.filter.type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getParentId() {
		return ParentId;
	}

	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	/**
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		String typeStr = action;
		String des = "";
		if(StringUtils.isNotBlank(typeStr)) {
			Iterable<String> typeItems= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(typeStr);
			Iterator<String> it = typeItems.iterator();
			while(it.hasNext()) {
				String value = DictionaryUtils.filterValue("las.filter.action", it.next());
				des += value + ",";
			}
			String value = des.substring(0, des.length()-1);
			return value;
			
		} else {
			return "未设置";
		}
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ParentId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + type;
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
		Alert other = (Alert) obj;
		if (ParentId != other.ParentId)
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
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Filter [id=" + id + ", name=" + name + ", type=" + type
				+ ", ParentId=" + ParentId + "]";
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
}
