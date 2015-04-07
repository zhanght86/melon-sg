package secfox.soc.business.message.query;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.business.message.domain.SysMailState;
import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;

/**
 * @since 1.0 2014-10-27,下午8:03:05
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public class SysMailStateSearch extends SysMailState {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6175603288952249912L;
	
	/**
	 * 查询的邮件类型   1:收件箱;2:发件箱;3:草稿箱;4:已删邮件 
	 */
	private int searchType;
	
	/**
	 * 模糊搜索类型  所有:ALL;主题:TITLE;发件人:SENDER;正文:CONTENT;收件人:RECEVIER
	 */
	private String simpleType;
	
	/**
	 * 模糊搜索内容
	 */
	private String simpleContent;
	
	/**
	 * 晚于时间
	 */
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	/**
	 * 早于时间
	 */
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	/**
	 * 当前用户
	 */
	private UserInfo currUser;
	

	/**
	 * @return the simpleType
	 */
	public String getSimpleType() {
		return simpleType;
	}

	/**
	 * @param simpleType the simpleType to set
	 */
	public void setSimpleType(String simpleType) {
		this.simpleType = simpleType;
	}

	/**
	 * @return the simpleContent
	 */
	public String getSimpleContent() {
		return simpleContent;
	}

	/**
	 * @param simpleContent the simpleContent to set
	 */
	public void setSimpleContent(String simpleContent) {
		this.simpleContent = simpleContent;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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
	 * @return the searchType
	 */
	public int getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the currUser
	 */
	public UserInfo getCurrUser() {
		return currUser;
	}

	/**
	 * @param currUser the currUser to set
	 */
	public void setCurrUser(UserInfo currUser) {
		this.currUser = currUser;
	}

	
	
}

