/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 2015-1-21,上午10:13:47
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Entity
@Table(name = "T_LAS_ALERTRULE")
public class AlertRule extends BaseDomain<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7374460007512139732L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//las规则id
	@Column(name="RULE_ID", length=100)
	private String ruleId;
	
	//ums规则id
	@Column(name="URULE_ID")
	private Long rule;
	
	//规则名称
	@Column(name="RULE_NAME", length=50)
	private String name;
	
	//动作类型
	@Column(name="ACTION", length=50)
	private String types;
	
	@Transient
	private Integer[] type;
	
	//主题
	@Column(name="SUBJECT", length=100)
	private String subject;
	
	//地址
	@Column(name="ADDRESS", length=300)
	private String address;
	
	//内容
	@Column(name="CONTENT", length=300)
	private String content;
	
	//收信人
	@Column(name="RECEIVE", length=50)
	private String receive;
	
	//短信内容
	@Column(name="NOTE", length=300)
	private String note;
	
	public AlertRule() {
		super();
	}
	
	public AlertRule(String ruleId, String name) {
		super();
		this.ruleId = ruleId;
		this.name = name;
		
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
	 * @return the ruleId
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return the rule
	 */
	public Long getRule() {
		return rule;
	}

	/**
	 * @param rule the rule to set
	 */
	public void setRule(Long rule) {
		this.rule = rule;
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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @return the types
	 */
	public String getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(String types) {
		this.types = types;
	}

	/**
	 * @return the receive
	 */
	public String getReceive() {
		return receive;
	}

	/**
	 * @param receive the receive to set
	 */
	public void setReceive(String receive) {
		this.receive = receive;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer[] type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public Integer[] getType() {
		if(StringUtils.isNotBlank(types)) {
			Iterable<String> typeItems = Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(types);
			return Iterables.toArray(Ints.stringConverter().convertAll(typeItems), Integer.class);
		}
		return type;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
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
		AlertRule other = (AlertRule) obj;
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
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlertRule [id=" + id + ", ruleId=" + ruleId + ", name=" + name
				+ ", types=" + types + "]";
	}

}
