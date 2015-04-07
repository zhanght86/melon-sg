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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @since 1.0 2014年11月10日,下午4:58:48
 * 信息公开
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_PUB")
public class PublicInfo extends BaseDomain<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4906944056700427051L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//hidden域
	//类型,1:总局发文,2:安全动态,3:安全制度,4:安全法规,5:安全通知,6:安全策略,7:安全标准
	@Column(name="INFO_TYPE", length=200)
	private int type;
	
	//标题
	@Column(name="TITLE", length=200)
	private String title;
	
	//副标题
	@Column(name="SUB_TITLE",length=200)
	private String subTitle;
	
	//关键字
	@Column(name="KEY_WORDS",length=100)
	private String keywords;
	
	//已发布
    //状态：1:未发布,2:已发布,3:已废除
    @Column(name="INFO_STATE")
    private Long state;

	//排序标志,可以置顶
	@Column(name="A_ORDER")
	private Long order;
	
	//重要性
	@Column(name="A_IMP")
	private int important;
	
	//创建日期,页面上可选
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	//创建人
	@Embedded
	private UserInfo creator;
	
	//附件
	@Column(name="ATTACH_ID")
	private String attachId;
	
	//图片附件,安全动态时使用
	@Column(name="PICTURE_ID")
	private String pictureId;
	
	/**
	 * 内容
	 */
	@Lob
	@Column(name="A_CONTENT")
	@JsonIgnore
	private String content;
	
	@Transient
	private String shortContent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	

	public int getImportant() {
		return important;
	}

	public void setImportant(int important) {
		this.important = important;
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

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
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
		result = prime * result
				+ ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result
				+ ((pictureId == null) ? 0 : pictureId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((subTitle == null) ? 0 : subTitle.hashCode());
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
		PublicInfo other = (PublicInfo) obj;
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
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (pictureId == null) {
			if (other.pictureId != null)
				return false;
		} else if (!pictureId.equals(other.pictureId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (subTitle == null) {
			if (other.subTitle != null)
				return false;
		} else if (!subTitle.equals(other.subTitle))
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
		return "PublicInfo [id=" + id + ", type=" + type + ", title=" + title
				+ ", subTitle=" + subTitle + ", keywords=" + keywords
				+ ", state=" + state + ", order=" + order + ", important="
				+ important + ", createTime=" + createTime + ", creator="
				+ creator + ", attachId=" + attachId + ", pictureId="
				+ pictureId + ", content=" + content + "]";
	}

	
}
