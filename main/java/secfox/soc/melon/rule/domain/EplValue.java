package secfox.soc.melon.rule.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name="T_RULE_EPLVALUE")
@NamedQueries({
	@NamedQuery(name="eplvalue.findByname", query="select value from EplValue value where value.name = :name")
})
public class EplValue extends BaseDomain<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Lob
	@JsonIgnore
	@Column(name="CONTENT")
	private String content;	
	
	@Column(name = "UPDATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	private Date updateTime;
	
	@Column(name = "ENABLE")
	private boolean enable;
	
	public EplValue() {
		super();
	}
	
	public EplValue(String name, String content, Date updateTime, boolean enable) {
		super();
		this.name = name;
		this.content = content;
		this.updateTime = updateTime;
		this.enable = enable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Dictionary("useState")
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		EplValue other = (EplValue) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "EplValue [id=" + id + ", name=" + name + "]";
	}

}
