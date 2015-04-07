/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.message.domain;

import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @since 1.0 2014年10月14日,上午10:51:44
 * 系统内部邮件,或称页面邮箱
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_MESSAGE_MAIL")
@NamedQueries({
	//根据登陆账号查询收件箱列表
	@NamedQuery(name="mail.findAll", query="select a from SysMail a "),
	//已发邮件列表
	@NamedQuery(name="mail.SendMail", query="select a.title,a.content from SysMail a where a.sender.userId =:userId"),
	//收件箱
	@NamedQuery(name="mail.ReciveMail", query="select a.title,a.content from SysMail a where a.sender.userId =:userId")
})
public class SysMail extends BaseDomain<Long> {

	private static final long serialVersionUID = -4076979159151627883L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_MAIL")
	private Long id;
	
	//回复的邮件ID
	@Column(name="REPLY_ID")
	private Long replyId;
	
	@Column(name="TITLE", length=100)
	private String title;
	
	//最多可写250字
	@Lob
	@Column(name="CON_TENT", length=500)
	private String content;
	
	//附件ID
	@Column(name="ATTACH_ID", length=50)
	private String attachId;
	
    //接收人,多个人以；分隔
	@Column(name="M_RECEIVNAMES",length=200)
	private String receivNames;
	
	@Column(name="M_RECEIVIDS")
	private String receivIds;
	
	//是：已发送，否：未发送（存入草稿箱）
	@Column(name="M_DRAFT")
	private boolean  draft = false;
	
	//紧急性：1：一般,2:紧急,3:非常紧急
	@Column(name="M_STATE")
	private int state =1;
	
	//邮件类型 1：普通邮件，2：主题邮件
	@Column(name="M_TYPE")
	private int type = 1;
	
	//收件人ID
	@Column(name="M_RECID")
	private Long userId;
		
	//紧急
	@Column(name="URGENCY")
	private boolean urgency = false;
	
	//已读回执
	@Column(name="RECEIPT")
	private boolean receipt = false;
	
	//删除标记
	@Column(name="DEL_STATE")
	private boolean delState = false;
	
	//回收站删除标记
	@Column(name="DEL_REFUSE")
	private boolean delRefuse = false;
	
	//回复邮件
    @Transient
	@JsonIgnore
	private List<SysMailReply> replys ;
	
	//邮件状态
    @Transient
    @JsonIgnore
	private List<SysMailState> states;
	
	//消息状态id
	@Column(name="M_STATEID")
	private Long StateId;
	
    //发送人信息
	@Embedded
	private UserInfo sender;
	
	//发送时间
	@Column(name="SEND_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	private Date sendTime;

	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getReplyId() {
		return replyId;
	}
	
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAttachId() {
		return attachId;
	}
	
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public UserInfo getSender() {
		return sender;
	}

	public void setSender(UserInfo sender) {
		this.sender = sender;
	}
	
	public Date getSendTime() {
		return sendTime;
	}
	
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getReceivNames() {
		return receivNames;
	}
	
	public void setReceivNames(String receivNames) {
		this.receivNames = receivNames;
	}
	
	public String getReceivIds() {
		return receivIds;
	}
	
	public void setReceivIds(String receivIds) {
		this.receivIds = receivIds;
	}
	public boolean isDraft() {
		return draft;
	}
	
	public void setDraft(boolean draft) {
		this.draft = draft;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public boolean isUrgency() {
		return urgency;
	}
	
	public void setUrgency(boolean urgency) {
		this.urgency = urgency;
	}
	
	public boolean isReceipt() {
		return receipt;
	}
	
	public void setReceipt(boolean receipt) {
		this.receipt = receipt;
	}
	
	public boolean isDelState() {
		return delState;
	}
	
	public void setDelState(boolean delState) {
		this.delState = delState;
	}
	
	public boolean isDelRefuse() {
		return delRefuse;
	}
	
	public void setDelRefuse(boolean delRefuse) {
		this.delRefuse = delRefuse;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public Long getStateId() {
		return StateId;
	}

	public void setStateId(Long stateId) {
		StateId = stateId;
	}
	public List<SysMailReply> getReplys() {
		return replys;
	}
	
	public void setReplys(List<SysMailReply> replys) {
		this.replys = replys;
	}
	
	public List<SysMailState> getStates() {
		return states;
	}
	
	public void setStates(List<SysMailState> states) {
		this.states = states;
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
		SysMail other = (SysMail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SysMail [id=" + id + "]";
	}
}
