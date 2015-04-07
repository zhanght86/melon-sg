/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.message.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
 * @author  <a href="mailto:wangxba@legendsec.com">王新兵</a>
 * @since   2014-2-20
 * @version  1.0
 */
@Entity
@Table(name="T_MESSAGE_REPLY")
@NamedQueries({
		@NamedQuery(name="reply.findReplyByMailId",query="select a from SysMailReply a where a.mailId =:mailId order by a.replyTime desc")
})
public class SysMailReply extends BaseDomain<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -283955932598398354L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//对应的邮件ID
	@Column(name="MAIL_ID")
	private Long mailId;
	
	//回复人
	@Embedded
	private UserInfo replyer;
	
	//回复时间
	@Column(name="REPLY_TIME")
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyTime;
	
	//回复内容
	@Lob
	@Column(name="REPLY_CONTENT")
	private String replyContent;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserInfo getReplyer() {
		return replyer;
	}

	public void setReplyer(UserInfo replyer) {
		this.replyer = replyer;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	
	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SysMailReply other = (SysMailReply) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mailId != other.mailId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReplyContent [id=" + id + "mailId =" + mailId + "]";
	}

	
	
}
