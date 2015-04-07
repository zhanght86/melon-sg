package secfox.soc.melon.work.myWork.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * 
 * @author 曹贞杰 我的工作实体类 2014年11月25日
 * @version 1.0
 */
@Entity
@Table(name = "T_WORK_WORK")
@NamedQueries({
	@NamedQuery(name="mywork.findUndo", query="select work from MyWork work where work.executiveIds like :userId and work.workType = 2 and work.completeState = 1"),
	@NamedQuery(name="mywork.findPassed", query="select work from MyWork work where work.executiveIds like :userId and work.workType = 1 and work.completeState = 1")
})
public class MyWork extends BaseDomain<Long> {

	private static final long serialVersionUID = 1L;

	// 主键
	@Id
	@Column(name = "PK")
	@GeneratedValue(generator = "GEN_SEQ_APP")
	private Long id;

	// 工作标题
	@Column(name = "W_TITLE", length = 80)
	private String title;

	// 工作来源 1内部2外部3提醒
	@Column(name = "W_RESOURCE")
	private int resource;
	
	//工作类型 1下发2待办
	@Column(name="W_WORK_TYPE")
	private int workType;
	
	// 任务执行单位
	@Column(name="W_COMPANY_ID")
	private Long companyId;
	
	@Column(name="W_COMPANY_NAME", length = 100)
	private String companyName;
	
	//执行人
	@Column(name="W_EXCUTIVE_IDS", length = 4000)
	private String executiveIds;
	
	@Column(name="W_EXECUTIVE_NAMES", length = 4000)
	private String executiveNames;

	// 工作内容
	@Column(name = "W_CONTENT", length = 2000)
	private String content;

	// 附件
	@Column(name = "W_ATTACHID")
	private String attachId;

	// 截止时间
	@Column(name = "W_ENDTIME")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	private Date endTime;

	// 父工作ID
	@Column(name = "W_PARENTID")
	private Long parentId;

	// 执行操作链接
	@Column(name = "W_URL")
	private String url;

	// 完成情况
	@Column(name = "COMPLETECONTENT", length = 800)
	private String completeContent;

	// 完成时间
	@Column(name = "completeTime")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.LONG_DATE)
	private Date completeTime;

	// 完成状态 0:未下发;1:待完成;2:已完成
	@Column(name = "completeState")
	private int completeState;

	// 备注
	@Column(name = "remarks", length = 400)
	private String remarks;

	// 修改人 姓名 修改人ID
	@Embedded
	private UserInfo updatePerson;

	// 修改时间
	@Column(name = "updateTime")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	private Date updateTime;
	
	/**
	 * 默认构造函数
	 */
	public MyWork() {
		super();
	}
	
	/**
	 * 
	 * @param title 标题
	 * @param resource 来源
	 * @param type 类型
	 * @param execute 执行人
	 * @param companyId 单位id
	 * @param companyName 单位名称
	 * @param endTime 截止时间
	 * @param content 内容
	 * @param url 操作
	 * @param state 状态
	 */
	public MyWork(String title, int resource, int type, String execute, Long companyId, String companyName, 
			Date endTime, String content, String url, int state) {
		super();
		this.title = title;
		this.resource = resource;
		this.workType = type;
		this.executiveIds = execute;
		this.companyId = companyId;
		this.companyName = companyName;
		this.endTime = endTime;
		this.content = content;
		this.url = url;
		this.completeState = state;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the resource
	 */
	public int getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(int resource) {
		this.resource = resource;
	}

	/**
	 * @return the workType
	 */
//	@Dictionary
	public int getWorkType() {
		return workType;
	}

	/**
	 * @param workType the workType to set
	 */
	public void setWorkType(int workType) {
		this.workType = workType;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the executiveIds
	 */
	public String getExecutiveIds() {
		return executiveIds;
	}

	/**
	 * @param executiveIds the executiveIds to set
	 */
	public void setExecutiveIds(String executiveIds) {
		this.executiveIds = executiveIds;
	}

	/**
	 * @return the executiveNames
	 */
	public String getExecutiveNames() {
		return executiveNames;
	}

	/**
	 * @param executiveNames the executiveNames to set
	 */
	public void setExecutiveNames(String executiveNames) {
		this.executiveNames = executiveNames;
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
	 * @return the attachId
	 */
	public String getAttachId() {
		return attachId;
	}

	/**
	 * @param attachId the attachId to set
	 */
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * @return the completeContent
	 */
	public String getCompleteContent() {
		return completeContent;
	}

	/**
	 * @param completeContent the completeContent to set
	 */
	public void setCompleteContent(String completeContent) {
		this.completeContent = completeContent;
	}

	/**
	 * @return the completeTime
	 */
	public Date getCompleteTime() {
		return completeTime;
	}

	/**
	 * @param completeTime the completeTime to set
	 */
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	/**
	 * @return the completeState
	 */
	public int getCompleteState() {
		return completeState;
	}

	/**
	 * @param completeState the completeState to set
	 */
	public void setCompleteState(int completeState) {
		this.completeState = completeState;
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

	/**
	 * @return the updatePerson
	 */
	public UserInfo getUpdatePerson() {
		return updatePerson;
	}

	/**
	 * @param updatePerson the updatePerson to set
	 */
	public void setUpdatePerson(UserInfo updatePerson) {
		this.updatePerson = updatePerson;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attachId == null) ? 0 : attachId.hashCode());
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((executiveIds == null) ? 0 : executiveIds.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MyWork other = (MyWork) obj;
		if (attachId == null) {
			if (other.attachId != null)
				return false;
		} else if (!attachId.equals(other.attachId))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (executiveIds == null) {
			if (other.executiveIds != null)
				return false;
		} else if (!executiveIds.equals(other.executiveIds))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyWork [id=" + id + ", title=" + title + ", resource="
				+ resource + ", workType=" + workType + ", companyId="
				+ companyId + ", companyName=" + companyName
				+ ", executiveIds=" + executiveIds + ", executiveNames="
				+ executiveNames + ", content=" + content + ", attachId="
				+ attachId + ", endTime=" + endTime + ", parentId=" + parentId
				+ ", completeContent=" + completeContent + ", completeTime="
				+ completeTime + ", completeState=" + completeState
				+ ", remarks=" + remarks + ", updatePerson=" + updatePerson
				+ ", updateTime=" + updateTime + "]";
	}
	
}
