/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

/**
 *等级保护-定级备案 等级保护第三张表,只与第一张表相联系
 * @since 1.0 2014-10-27
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Entity(name="T_DB_LEVELINFO")
public class DbLevelInfo extends BaseDomain<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_DB")
	private Long id;
	
	//单位基本信息ID
	@Column(name="BASIC_ID")
	private Long basicId;
	
	//所属信息系统名称
	@Column(name="SYS_NAME",length=50)
	private String sysName;
	
	//所属信息系统编码
	@Column(name="SYS_CODE",length=20)
	private String sysCode;
	
	//操作人信息
    @Embedded
	private UserInfo operator;
	
	//填表日期
    @Column(name="CREATE_TIME")
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	//01确定业务信息安全保护等级
    @Column(name="PROLEVEL")
	private int proLevel;
	
	//02确定系统服务安全保护等级
    @Column(name="SAFE_LEVEL")
	private int safeLevel;
	
	//03信息系统安全保护等级 
    @Column(name="SYS_LEVEL")
	private int sysLevel;

	//04 定级时间
	@Column(name="GRADE_TIME")
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date gradeTime;
	
	//05专家评审情况	 true：已评审 false:未评审
	@Column(name="REVIEW")
	private boolean review;
	
	//06是否有主管部门 	true:有 false:无
	@Column(name="LEADER_DEPT")
	private boolean leaderDept;
	
	//07主管部门名称
	@Column(name="LEADER_DEPT_NAME",length=60)
	private String leaderDeptName;
	
	//08主管部门审批定级情况	true:已审批 false:未审批
	@Column(name="LEADER_REV")
	private boolean leaderRev;
	
	//09系统定级报告	 true:有 false:无
	@Column(name="SER_USERED_OTHER")
	private boolean levelReport;
	
	//附件ID
	@Column(name="ATTACH_ID", length=50)
	private String attachId;
	
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
	 * @return the sysName
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * @param sysName the sysName to set
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * @return the sysCode
	 */
	public String getSysCode() {
		return sysCode;
	}

	/**
	 * @param sysCode the sysCode to set
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * @return the basicId
	 */
	public Long getBasicId() {
		return basicId;
	}

	/**
	 * @param basicId the basicId to set
	 */
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}

	/**
	 * @return the operator
	 */
	public UserInfo getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(UserInfo operator) {
		this.operator = operator;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the proLevel
	 */
	public int getProLevel() {
		return proLevel;
	}

	/**
	 * @param proLevel the proLevel to set
	 */
	public void setProLevel(int proLevel) {
		this.proLevel = proLevel;
	}

	/**
	 * @return the safeLevel
	 */
	public int getSafeLevel() {
		return safeLevel;
	}

	/**
	 * @param safeLevel the safeLevel to set
	 */
	public void setSafeLevel(int safeLevel) {
		this.safeLevel = safeLevel;
	}

	/**
	 * @return the sysLevel
	 */
	public int getSysLevel() {
		return sysLevel;
	}

	/**
	 * @param sysLevel the sysLevel to set
	 */
	public void setSysLevel(int sysLevel) {
		this.sysLevel = sysLevel;
	}

	/**
	 * @return the gradeTime
	 */
	public Date getGradeTime() {
		return gradeTime;
	}

	/**
	 * @param gradeTime the gradeTime to set
	 */
	public void setGradeTime(Date gradeTime) {
		this.gradeTime = gradeTime;
	}

	/**
	 * @return the review
	 */
	public boolean isReview() {
		return review;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(boolean review) {
		this.review = review;
	}

	/**
	 * @return the leaderDept
	 */
	public boolean isLeaderDept() {
		return leaderDept;
	}

	/**
	 * @param leaderDept the leaderDept to set
	 */
	public void setLeaderDept(boolean leaderDept) {
		this.leaderDept = leaderDept;
	}

	/**
	 * @return the leaderDeptName
	 */
	public String getLeaderDeptName() {
		return leaderDeptName;
	}

	/**
	 * @param leaderDeptName the leaderDeptName to set
	 */
	public void setLeaderDeptName(String leaderDeptName) {
		this.leaderDeptName = leaderDeptName;
	}

	/**
	 * @return the leaderRev
	 */
	public boolean isLeaderRev() {
		return leaderRev;
	}

	/**
	 * @param leaderRev the leaderRev to set
	 */
	public void setLeaderRev(boolean leaderRev) {
		this.leaderRev = leaderRev;
	}

	/**
	 * @return the levelReport
	 */
	public boolean isLevelReport() {
		return levelReport;
	}

	/**
	 * @param levelReport the levelReport to set
	 */
	public void setLevelReport(boolean levelReport) {
		this.levelReport = levelReport;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basicId == null) ? 0 : basicId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
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
		DbLevelInfo other = (DbLevelInfo) obj;
		if (basicId == null) {
			if (other.basicId != null)
				return false;
		} else if (!basicId.equals(other.basicId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DbLevelInfo [id=" + id + ", basicId=" + basicId + ", operator="
				+ operator + ", createTime=" + createTime + ", proLevel="
				+ proLevel + ", safeLevel=" + safeLevel + ", sysLevel="
				+ sysLevel + ", gradeTime=" + gradeTime + ", isReview="
				+ review + ", isLeaderDept=" + leaderDept
				+ ", leaderDeptName=" + leaderDeptName + ", isLeaderRev="
				+ leaderRev + ", levelReport=" + levelReport + ", fileName="
				+ attachId + "]";
	}
}
