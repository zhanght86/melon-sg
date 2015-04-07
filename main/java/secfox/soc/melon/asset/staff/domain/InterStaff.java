/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.base.json.deserializer.ShortDateDeserializer;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;
import secfox.soc.melon.asset.staff.listener.InterStaffListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @since 2014-11-20,上午10:40:31
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Entity
@Table(name="T_STAFF_INTER")
@EntityListeners(value= {InterStaffListener.class})
@NamedQueries({
	@NamedQuery(name="interstaff.findAll", query="select staff from InterStaff staff order by staff.code desc"),
	@NamedQuery(name="interStaff.findOrder", query="select staff from InterStaff staff where staff.organId = :organId order by staff.code desc"),
	@NamedQuery(name="interStaff.findCount", query="select count(staff.id) from InterStaff staff where staff.organId = :organId")
})
public class InterStaff extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -5561294646290035437L;

	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//编号
	@Column(name = "S_CODE")
	private String code;
	
	//姓名
	@Column(name = "S_NAME", length = 20)
	private String name;
	
	//工号
	@Column(name = "S_NUMBER", length = 20)
	private String number;
	
	//性别
	@Column(name = "S_SEX")
	private int sex;
	
	//出生日期
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = ShortDateSerializer.class)
	@JsonDeserialize(using = ShortDateDeserializer.class)
	@Column(name = "S_BIRTHDAY")
	private Date birthday;
	
	//专职岗位
	@Column(name = "S_FULL", length = 40)
	private String fullJob;
	
	@Transient
	private String job;
	
	@Transient
	@JsonIgnore
	private Integer[] fullJobs;
	
	//兼职岗位
	@Column(name = "S_PART", length = 40)
	private String partJob;
	
	@Transient
	@JsonIgnore
	private Integer[] partJobs;
	
	//单位
	@Column(name = "S_ORGAN")
	private Long organId;
	
	//单位名称
	@Column(name = "S_ORGANNAME")
	private String organName;
	
	//岗位
	@Column(name = "S_DEPART")
	private Long departId;
	
	//部门名称
	@Column(name = "S_DEPARTNAME")
	private String departName;
	
	//上级负责人
	@Column(name = "S_CHARGE")
	private Long chargePerson;
	
	//负责人姓名
	@Column(name = "S_CHARGENAME")
	private String chargeName;
	
	//任职时间
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = ShortDateSerializer.class)
	@JsonDeserialize(using = ShortDateDeserializer.class)
	@Column(name = "S_OFFICEDATE")
	private Date officeDate;
	
	//职称
	@Column(name = "S_TITLE")
	private int title;
	
	//技术职称
	@Column(name = "S_TECHTITLE")
	private int techTitle;
	
	//固话
	@Column(name = "S_TEL", length = 20)
	private String tel;
	
	//手机
	@Column(name = "S_PHONE", length = 20)
	private String phone;
	
	//邮箱
	@Column(name = "S_MAIL", length = 20)
	private String mail;
	
	//讲师
	@Column(name = "S_LECTURER")
	private int lecturer=1; 
	
	//安全专家
	@Column(name = "S_EXPERT")
	private int expert=1;
	
	//项目组
	@Column(name = "S_TEAM")
	private int projectTeam=1;
	
	//技能
	@Column(name = "S_SKILL", length = 40)
	private String techSkill;
	
	@Transient
	@JsonIgnore
	private Integer[] techSkills;
	
	//其他技能
	@Column(name = "S_OTSKILL", length = 40)
	private String otherSkill;
	
	//保密性
	@Column(name = "S_SEC")
	private boolean security;
	
	//历史岗位
	@Column(name = "S_HISTORY")
	private int historyJob;
	
	//任职考核
	@Column(name = "S_EXAMINE")
	private boolean examine;
	
	@Transient
	@JsonIgnore
	List<Certificate> certs ;
	
	//证书
	@Column(name = "S_CERTIFICATE", length = 40)
	private String certificate;
	
	@Transient
	@JsonIgnore
	private Integer[] certificates;
	
	//其他证书
	@Column(name = "S_OTCER", length = 40)
	private String otherCertificate;
	
	//论文数量
	@Column(name = "S_PAPER")
	private int paperNum;
	
	//第一学历
	@Column(name = "S_EDU")
	private String education;
	
	//专业
	@Column(name = "S_PROFESS")
	private String profession;
	
	//备注
	@Column(name = "S_REMARK", length = 256)
	@JsonIgnore
	private String remark;
	
	//修改人
	@Embedded
	private UserInfo editor;
	
	//保密协议业务id
	@Column(name = "S_SECBUSINESS", length = 100)
	private String secProtocolBusiness;
	
	//培训证书业务id
	@Column(name = "S_CERBUSINESS", length = 100)
	private String certificateBusiness;
	
	//论文业务id
	@Column(name = "S_PAPERBUSINESS", length = 100)
	private String paperBusiness;
	
	//顺序
	@Column(name = "S_ORDER")
	private int order;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the fullJob
	 */
	public String getFullJob() {
		return fullJob;
	}

	/**
	 * @param fullJob the fullJob to set
	 */
	public void setFullJob(String fullJob) {
		this.fullJob = fullJob;
	}

	/**
	 * @return the partJob
	 */
	public String getPartJob() {
		return partJob;
	}

	/**
	 * @param partJob the partJob to set
	 */
	public void setPartJob(String partJob) {
		this.partJob = partJob;
	}

	/**
	 * @return the organId
	 */
	public Long getOrganId() {
		return organId;
	}

	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	/**
	 * @return the departId
	 */
	public Long getDepartId() {
		return departId;
	}

	/**
	 * @param departId the departId to set
	 */
	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	/**
	 * @return the chargePerson
	 */
	public Long getChargePerson() {
		return chargePerson;
	}

	/**
	 * @param chargePerson the chargePerson to set
	 */
	public void setChargePerson(Long chargePerson) {
		this.chargePerson = chargePerson;
	}

	/**
	 * @return the officeDate
	 */
	public Date getOfficeDate() {
		return officeDate;
	}

	/**
	 * @param officeDate the officeDate to set
	 */
	public void setOfficeDate(Date officeDate) {
		this.officeDate = officeDate;
	}

	/**
	 * @return the title
	 */
	public int getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(int title) {
		this.title = title;
	}

	/**
	 * @return the techTitle
	 */
	public int getTechTitle() {
		return techTitle;
	}

	/**
	 * @param techTitle the techTitle to set
	 */
	public void setTechTitle(int techTitle) {
		this.techTitle = techTitle;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the lecturer
	 */
	@Dictionary("boolInt")
	public int getLecturer() {
		return lecturer;
	}

	/**
	 * @param lecturer the lecturer to set
	 */
	public void setLecturer(int lecturer) {
		this.lecturer = lecturer;
	}

	/**
	 * @return the expert
	 */
	@Dictionary("boolInt")
	public int getExpert() {
		return expert;
	}

	/**
	 * @param expert the expert to set
	 */
	public void setExpert(int expert) {
		this.expert = expert;
	}

	/**
	 * @return the projectTeam
	 */
	@Dictionary("boolInt")
	public int getProjectTeam() {
		return projectTeam;
	}

	/**
	 * @param projectTeam the projectTeam to set
	 */
	public void setProjectTeam(int projectTeam) {
		this.projectTeam = projectTeam;
	}

	/**
	 * @return the techSkill
	 */
	public String getTechSkill() {
		return techSkill;
	}

	/**
	 * @param techSkill the techSkill to set
	 */
	public void setTechSkill(String techSkill) {
		this.techSkill = techSkill;
	}

	/**
	 * @return the otherSkill
	 */
	public String getOtherSkill() {
		return otherSkill;
	}

	/**
	 * @param otherSkill the otherSkill to set
	 */
	public void setOtherSkill(String otherSkill) {
		this.otherSkill = otherSkill;
	}

	/**
	 * @return the security
	 */
	public boolean isSecurity() {
		return security;
	}

	/**
	 * @param security the security to set
	 */
	public void setSecurity(boolean security) {
		this.security = security;
	}

	/**
	 * @return the historyJob
	 */
	public int getHistoryJob() {
		return historyJob;
	}

	/**
	 * @param historyJob the historyJob to set
	 */
	public void setHistoryJob(int historyJob) {
		this.historyJob = historyJob;
	}

	/**
	 * @return the examine
	 */
	public boolean isExamine() {
		return examine;
	}

	/**
	 * @param examine the examine to set
	 */
	public void setExamine(boolean examine) {
		this.examine = examine;
	}

	/**
	 * @return the certificate
	 */
	public String getCertificate() {
		return certificate;
	}

	/**
	 * @param certificate the certificate to set
	 */
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	/**
	 * @return the otherCertificate
	 */
	public String getOtherCertificate() {
		return otherCertificate;
	}

	/**
	 * @param otherCertificate the otherCertificate to set
	 */
	public void setOtherCertificate(String otherCertificate) {
		this.otherCertificate = otherCertificate;
	}

	/**
	 * @return the paperNum
	 */
	public int getPaperNum() {
		return paperNum;
	}

	/**
	 * @param paperNum the paperNum to set
	 */
	public void setPaperNum(int paperNum) {
		this.paperNum = paperNum;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the editor
	 */
	public UserInfo getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(UserInfo editor) {
		this.editor = editor;
	}

	/**
	 * @return the secProtocolBusiness
	 */
	public String getSecProtocolBusiness() {
		return secProtocolBusiness;
	}

	/**
	 * @param secProtocolBusiness the secProtocolBusiness to set
	 */
	public void setSecProtocolBusiness(String secProtocolBusiness) {
		this.secProtocolBusiness = secProtocolBusiness;
	}

	/**
	 * @return the certificateBusiness
	 */
	public String getCertificateBusiness() {
		return certificateBusiness;
	}

	/**
	 * @param certificateBusiness the certificateBusiness to set
	 */
	public void setCertificateBusiness(String certificateBusiness) {
		this.certificateBusiness = certificateBusiness;
	}

	/**
	 * @return the paperBusiness
	 */
	public String getPaperBusiness() {
		return paperBusiness;
	}

	/**
	 * @param paperBusiness the paperBusiness to set
	 */
	public void setPaperBusiness(String paperBusiness) {
		this.paperBusiness = paperBusiness;
	}

	/**
	 * @return the fullJobs
	 */
	@Dictionary("interstaff.fulljob")
	public Integer[] getFullJobs() {
		return fullJobs;
	}

	/**
	 * @param fullJobs the fullJobs to set
	 */
	public void setFullJobs(Integer[] fullJobs) {
		this.fullJobs = fullJobs;
	}

	/**
	 * @return the partJobs
	 */
	@Dictionary("interstaff.partjob")
	public Integer[] getPartJobs() {
		return partJobs;
	}

	/**
	 * @param partJobs the partJobs to set
	 */
	public void setPartJobs(Integer[] partJobs) {
		this.partJobs = partJobs;
	}

	/**
	 * @return the techSkills
	 */
	@Dictionary("interstaff.techskill")
	public Integer[] getTechSkills() {
		return techSkills;
	}

	/**
	 * @param techSkills the techSkills to set
	 */
	public void setTechSkills(Integer[] techSkills) {
		this.techSkills = techSkills;
	}

	/**
	 * @return the certificates
	 */
	@Dictionary("interstaff.certificate")
	public Integer[] getCertificates() {
		return certificates;
	}

	/**
	 * @param certificates the certificates to set
	 */
	public void setCertificates(Integer[] certificates) {
		this.certificates = certificates;
	}

	/**
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

	/**
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return the chargeName
	 */
	public String getChargeName() {
		return chargeName;
	}

	/**
	 * @param chargeName the chargeName to set
	 */
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the certs
	 */
	public List<Certificate> getCerts() {
		return certs;
	}

	/**
	 * @param certs the certs to set
	 */
	public void setCerts(List<Certificate> certs) {
		this.certs = certs;
	}
	
	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + sex;
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		InterStaff other = (InterStaff) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (sex != other.sex)
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InterStaff [id=" + id + ", code=" + code + ", name=" + name
				+ ", number=" + number + ", sex=" + sex + ", birthday="
				+ birthday + ", tel=" + tel + ", phone=" + phone + ", mail="
				+ mail + "]";
	}

}
