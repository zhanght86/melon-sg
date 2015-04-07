/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.domain;

import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年9月23日,下午4:07:09
 * 上传的文件
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_SYSTEM_FILE")
@NamedQuery(name="ajaxFile.findFile" , query="select ajaxFile from AjaxFile ajaxFile where businessId = :businessId")
public class AjaxFile extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -7088476061446375293L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name="FILE_NAME", length=100)
	private String name;
	
	//业务ID
	@Column(name="BUSINESS_ID")
	private String businessId;
	
	//文件创建时间
	@Column(name="CREATE_TIME")
    @DateFormatter(DateTimeType.LONG_DATE)
	private Date createTime;
	
	@Column(name="FILE_SIZE")
	private Long total;
	
	@Transient
	private Long loaded;
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.setId(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getDownloadUrl() {
		return "/system/ajaxfile/download/" + id;
	}

	public String getRemoveUrl() {
		return "/system/ajaxfile/remove/" + id;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	/**
	 * 获取文件的大小
	 * @return
	 */
	public String getTotalHint() {
		double count = total.intValue();
		int index = 0;
		String[] units = new String[]{"B", "KB", "M", "G", "T", "P"};
		while(count >= 1024) {
			count = count / 1024;
			index ++;
		}
		return MessageFormat.format("[{0,number,#.##}{1}]", new Object[]{count, units[index]});
	}

	public Long getLoaded() {
		return loaded;
	}

	public void setLoaded(Long loaded) {
		this.loaded = loaded;
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AjaxFile other = (AjaxFile) obj;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
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
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "AjaxFile [id=" + id + ", name=" + name + ", businessId="
				+ businessId + ", createTime=" + createTime
				+ ", total=" + total + ", loaded=" + loaded
				+ "]";
	}

}
