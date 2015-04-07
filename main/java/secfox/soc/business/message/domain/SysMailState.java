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
 * @since 1.0 2014年1月17日,上午10:26:23
 * 站内信状态
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_MESSAGE_STATE")
@NamedQueries({
	    @NamedQuery(name="mailState.finAll",query="select a from SysMailState a where 1=1"),
		@NamedQuery(name="mailState.finMailStatesById",query="select a from SysMailState a where a.mailId =:mailId"),
		@NamedQuery(name="mailState.findByMailIdAndUserId",query="select a from SysMailState a where a.mailId =:mailId and a.receiver.userId =:userId")
})

public class SysMailState extends BaseDomain<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7890704205218294151L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;

	//对应的邮件ID
	@Column(name="MAIL_ID")
	private Long mailId;
	
	//阅读状态
	@Column(name="READ_STATE")
	private boolean readState = false;

    //删除状态
	@Column(name="DEL_STATE")
	private boolean delState = false;
	
    //回复状态
	@Column(name="REPLY_STATE")
	private boolean replyState = false;
	
	//回执状态
	@Column(name="RECEIPT_STATE")
	private boolean receiptState = false;
	
	//转发状态
	@Column(name="FORWARD_STATE")
	private boolean forwardState = false;
	
	//接收人
	@Embedded
	private UserInfo receiver;
	
	//接收时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	@Column(name="RECEIVED_TIME")
	private Date receivedTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isReadState() {
		return readState;
	}

	public void setReadState(boolean readState) {
		this.readState = readState;
	}

	public UserInfo getReceiver() {
		return receiver;
	}

	public void setReceiver(UserInfo receiver) {
		this.receiver = receiver;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public boolean isDelState() {
		return delState;
	}

	public void setDelState(boolean delState) {
		this.delState = delState;
	}
	
	public boolean isReplyState() {
		return replyState;
	}

	public void setReplyState(boolean replyState) {
		this.replyState = replyState;
	}
	
	public boolean isReceiptState() {
		return receiptState;
	}

	public void setReceiptState(boolean receiptState) {
		this.receiptState = receiptState;
	}

	public boolean isForwardState() {
		return forwardState;
	}

	public void setForwardState(boolean forwardState) {
		this.forwardState = forwardState;
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
		result = prime * result + (readState ? 1231 : 1237);
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
		SysMailState other = (SysMailState) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (readState != other.readState)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MailMessageState [id=" + id + ", readState=" + readState + "]";
	}
	
	

}
