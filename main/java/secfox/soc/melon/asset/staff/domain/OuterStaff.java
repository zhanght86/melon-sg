/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.domain;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.base.json.deserializer.ShortDateDeserializer;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;
import secfox.soc.melon.asset.staff.listener.OuterStaffListener;

/**
 * @since 2014-11-21,上午11:56:31
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Entity
@Table(name="T_STAFF_OUTER")
@EntityListeners(value= {OuterStaffListener.class})
@NamedQueries({
	@NamedQuery(name="outerStaff.findAll", query="select staff from OuterStaff staff order by staff.code desc"),
	@NamedQuery(name="outerStaff.findOrder", query="select staff from OuterStaff staff where staff.organId = :organId order by staff.code desc"),
	@NamedQuery(name="outerStaff.findCount", query="select count(staff.id) from OuterStaff staff where staff.organId = :organId")
})
public class OuterStaff extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	//岗位
	@Column(name = "S_FULL", length = 40)
	private String fullJob;
	

	@Transient
	private String job;
	
	@Transient
	@JsonIgnore
	private Integer[] fullJobs;
	
	//单位
	@Column(name = "S_ORGAN")
	private Long organId;
	
	//单位名称
	@Column(name = "S_ORGANNAME")
	private String organName;
	
	//所辖公司
	@Column(name = "S_COMPANY")
	private String CompanyName;
	
	//部门ID
	@Column(name = "S_DEPART")
	private Long departId;
	
	//所属部门
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
	
	//固话
	@Column(name = "S_TEL", length = 20)
	private String tel;
	
	//手机
	@Column(name = "S_PHONE", length = 20)
	private String phone;
	
	//邮箱
	@Column(name = "S_MAIL", length = 20)
	private String mail;
	
	//保密性
	@Column(name = "S_SEC")
	private boolean security;
	
	//任职考核
	@Column(name = "S_EXAMINE")
	private boolean examine;
	
	//证书
	@Column(name = "S_CERTIFICATE", length = 40)
	private String certificate;
	
	@Transient
	@JsonIgnore
	private Integer[] certificates;
	
	//其他证书
	@Column(name = "S_OTCER", length = 40)
	private String otherCertificate;
	
	//备注
	@Column(name = "S_REMARK", length = 256)
	@JsonIgnore
	private String remark;
	
	//维护权限
	@Column(name = "S_AUTOR", length = 256)
	private String maintainAutor;
	
	@Transient
	@JsonIgnore
	private Integer[] maintainAutors;
	
	//参与项目
	@Column(name = "S_PROJECT", length = 256)
	@JsonIgnore
	private String project;
	
	//主要工作
	@Column(name = "S_WORK", length = 256)
	private String work;
	
	@Transient
	@JsonIgnore
	private Integer[] works;
	
	//其他工作
	@Column(name = "S_OTWORK", length = 256)
	@JsonIgnore
	private String otherWork;
	
	//修改人
	@Embedded
	private UserInfo editor;
	
	//保密协议业务id
	@Column(name = "S_SECBUSINESS", length = 100)
	private String secProtocolBusiness;
	
	//培训证书业务id
	@Column(name = "S_CERBUSINESS", length = 100)
	private String certificateBusiness;
	
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
	 * @return the fullJobs
	 */
	@Dictionary("outerstaff.fulljob")
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
	 * @return the certificates
	 */
	@Dictionary("outerstaff.certificate")
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
	 * @return the maintainAutor
	 */
	public String getMaintainAutor() {
		return maintainAutor;
	}

	/**
	 * @param maintainAutor the maintainAutor to set
	 */
	public void setMaintainAutor(String maintainAutor) {
		this.maintainAutor = maintainAutor;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the work
	 */
	public String getWork() {
		return work;
	}

	/**
	 * @return the otherWork
	 */
	public String getOtherWork() {
		return otherWork;
	}

	/**
	 * @param otherWork the otherWork to set
	 */
	public void setOtherWork(String otherWork) {
		this.otherWork = otherWork;
	}
	/**
	 * @param work the work to set
	 */
	public void setWork(String work) {
		this.work = work;
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
	 * @return the companyName
	 */
	public String getCompanyName() {
		return CompanyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	
	/**
	 * @return the maintainAutors
	 */
	@Dictionary("outerstaff.maintainAutor")
	public Integer[] getMaintainAutors() {
		return maintainAutors;
	}

	/**
	 * @param maintainAutors the maintainAutors to set
	 */
	public void setMaintainAutors(Integer[] maintainAutors) {
		this.maintainAutors = maintainAutors;
	}

	/**
	 * @return the works
	 */
	@Dictionary("outerstaff.work")
	public Integer[] getWorks() {
		return works;
	}

	/**
	 * @param works the works to set
	 */
	public void setWorks(Integer[] works) {
		this.works = works;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((departId == null) ? 0 : departId.hashCode());
		result = prime * result + ((editor == null) ? 0 : editor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + order;
		result = prime * result + ((organId == null) ? 0 : organId.hashCode());
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
		OuterStaff other = (OuterStaff) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (departId == null) {
			if (other.departId != null)
				return false;
		} else if (!departId.equals(other.departId))
			return false;
		if (editor == null) {
			if (other.editor != null)
				return false;
		} else if (!editor.equals(other.editor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (order != other.order)
			return false;
		if (organId == null) {
			if (other.organId != null)
				return false;
		} else if (!organId.equals(other.organId))
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
		return "OuterStaff [id=" + id + ", code=" + code + ", name=" + name
				+ ", number=" + number + ", sex=" + sex + ", birthday="
				+ birthday + ", organId=" + organId + ", tel=" + tel
				+ ", phone=" + phone + ", mail=" + mail + ", editor=" + editor
				+ ", order=" + order + "]";
	}

}
