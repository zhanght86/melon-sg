/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @since 1.0 2014年10月23日,下午8:47:59
 * 知识库,所有的知识统一管理
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_KNOW_LEDGE")
//@Indexed
public class Knowledge extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7719938595110168973L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	//@DocumentId
	private Long id;
	
	//知识的分类,可以扩展属性
	@Column(name="KN_TYPE")
	private Long type ;
	
	//发布时间
	@Column(name="ISS_DATE")
	@Temporal(TemporalType.DATE)
	private Date issueDate;
	
	//发布单位
	@Column(name="ISS_ORGAN", length=200)
	//@Field(analyze=Analyze.YES, store=Store.YES, index=Index.YES, boost=@Boost(2))
	//@Boost(4)
	private String issueOrgan;
	
	//名称
	@Column(name="KN_TITLE", length=200)
	//@Field(analyze=Analyze.YES, store=Store.YES, index=Index.YES, boost=@Boost(2))
	//@Boost(4)
	//Boost数字越大优先级越高 相乘2*2=4
	private String title;
	
	//关键字
	@Column(name="KEY_WORDS", length=200)
	//@Field(analyze=Analyze.YES, store=Store.YES, index=Index.YES, boost=@Boost(2))
	//@Boost(2)
	private String keywords;
	
	//内容
	@Lob
	@JsonIgnore
	@Column(name="CON_TENT")
	//@Field(analyze=Analyze.YES, store=Store.NO, index=Index.YES)
	private String content;
	
	//访问次数
	@Column(name="KNG_COUNT")
	private int count=0;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssueOrgan() {
		return issueOrgan;
	}

	public void setIssueOrgan(String issueOrgan) {
		this.issueOrgan = issueOrgan;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result
				+ ((issueOrgan == null) ? 0 : issueOrgan.hashCode());
		result = prime * result
				+ ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Knowledge other = (Knowledge) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (issueOrgan == null) {
			if (other.issueOrgan != null)
				return false;
		} else if (!issueOrgan.equals(other.issueOrgan))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Knowledge [id=" + id + ", type=" + type + ", issueDate="
				+ issueDate + ", issueOrgan=" + issueOrgan + ", title=" + title
				+ ", keywords=" + keywords + ", content=" + content + "]";
	}

	

}
