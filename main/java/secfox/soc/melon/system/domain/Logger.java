/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.LongDateSerializer;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.BaseDomain;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.util.RequestUtils;

/**
 * 日志审计
 * @since 1.0 2014-10-8 下午8:14:02
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Entity
@Table(name="T_SYSTEM_LOGGER")
public class Logger extends BaseDomain<Long> {

	private static final long serialVersionUID = -6335728308055318678L;
	
    @Id@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_LOGGER")
    private Long id;
    
    //操作人信息
    @Embedded
    private UserInfo operator;
    
    //发生时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	@JsonSerialize(using=LongDateSerializer.class)
	@Column(name="AUDIT_TIME")
	private Date occurTime;
	
	//操作结果
	@Column(name="AUDIT_RESULT", length=50)
	private String result;
	
	//所属模块
	@Column(name="AUDIT_MODULE", length=100)
	private String module;
	
	//所属功能
	@Column(name="AUDIT_CLAZZ", length=100)
	private String clazz;
	
	//具体操作
	@Column(name="AUDIT_FUNCTION", length=50)
	private String function;
	
	//操作的IP
	private String ip;
	
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public final static Logger create(Class<?> clazz) {
		HttpServletRequest request = RequestUtils.getRequest();
		if(request != null) {
			Logger logger = new Logger();
			logger.setClazz(clazz.getName());
			logger.setIp(request.getRemoteAddr());//设置IP地址
			logger.setOccurTime(new Date());
			logger.setOperator(SecurityContextUtils.getCurrentUserInfo());
			logger.setResult(MessageSourceUtils.getMessage("operation.success"));
			return logger;
		}
		return null;
	}
	
	public final static Logger create() {
		//自动添加登陆人信息以及IP地址信息
		return null;
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
	 * @return the occurTime
	 */
	public Date getOccurTime() {
		return occurTime;
	}

	/**
	 * @param occurTime the occurTime to set
	 */
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((occurTime == null) ? 0 : occurTime.hashCode());
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
		Logger other = (Logger) obj;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (occurTime == null) {
			if (other.occurTime != null)
				return false;
		} else if (!occurTime.equals(other.occurTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Logger [id=" + id + ", operator=" + operator + ", occurTime=" + occurTime
				+ ", result=" + result + ", module=" + module + ", clazz="
				+ clazz + ", function=" + function + ", ip=" + ip + "]";
	}
}