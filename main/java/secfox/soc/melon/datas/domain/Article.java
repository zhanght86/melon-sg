/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年11月6日,下午4:17:07
 * 发文
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_ARTICLE")
public class Article extends BaseDomain<Long> {

	private static final long serialVersionUID = 4907572534709545232L;
	
	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//标题
	@Column(name="TITLE", length=200)
	private String title;
	
	//分类,隐藏字段
	//1-10
	//10-20,发文
	@Column(name="A_TYPE")
	private int type;
	
	//创建日期,页面上可选
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	//创建人
	@Embedded
	private UserInfo creator;
	
	//附件
	@Column(name="A_ATTACH")
	private String attachId;
	
	//接受人,如果为空,则面向所有人,以逗号分割
	@Column(name="RECE_VICERS", length=2000)
	private String recevicers;
	
	@Column(name="RECE_VICEIDS",length=2000)
	private String receiverIds;
	
	/**
	 * 内容
	 */
	@Lob
	@Column(name="A_CONTENT")
	private String content;
	
	//已发布
	@Column(name="A_PUB")
	private boolean published;

	//排序标志,可以置顶
	@Column(name="A_ORDER")
	private Long order;
	
	//重要性
	@Column(name="A_IMP")
	private int important;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserInfo getCreator() {
		return creator;
	}

	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getRecevicers() {
		return recevicers;
	}

	public void setRecevicers(String recevicers) {
		this.recevicers = recevicers;
	}

	public String getReceiverIds() {
		return receiverIds;
	}

	public void setReceiverIds(String receiverIds) {
		this.receiverIds = receiverIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public int getImportant() {
		return important;
	}

	public void setImportant(int important) {
		this.important = important;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attachId == null) ? 0 : attachId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + important;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + (published ? 1231 : 1237);
		result = prime * result
				+ ((receiverIds == null) ? 0 : receiverIds.hashCode());
		result = prime * result
				+ ((recevicers == null) ? 0 : recevicers.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Article other = (Article) obj;
		if (attachId == null) {
			if (other.attachId != null)
				return false;
		} else if (!attachId.equals(other.attachId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (important != other.important)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (published != other.published)
			return false;
		if (receiverIds == null) {
			if (other.receiverIds != null)
				return false;
		} else if (!receiverIds.equals(other.receiverIds))
			return false;
		if (recevicers == null) {
			if (other.recevicers != null)
				return false;
		} else if (!recevicers.equals(other.recevicers))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", type=" + type
				+ ", createTime=" + createTime + ", creator=" + creator
				+ ", attachId=" + attachId + ", recevicers=" + recevicers
				+ ", receiverIds=" + receiverIds + ", content=" + content
				+ ", published=" + published + ", order=" + order
				+ ", important=" + important + "]";
	}

	
}
