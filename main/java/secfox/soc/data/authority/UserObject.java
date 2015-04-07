/**
 * 版权所有 ( c ) 北京网诺信息安全技术有限公司 2005。保留所有权利。
 *
 * 项目：	     secfoxserver
 * 文件名：	【UserObject.java】
 * 描述：	    【应用服务器-公共数据-用户对象】
 * 作者名：	【朱震】
 * 日期：	    【2005-12-23】
 * $Id
 * 修改历史：
 * 【时间】		【修改者】	【修改内容】
 */
package secfox.soc.data.authority;
import java.io.Serializable;
import java.util.HashMap;
/**
 *
 * @author zhuzhen
 * @version 2.0
 */
public class UserObject implements Serializable
{
	
	private long userDetailId;             //用户明细表id
	private long userID;
	private String userName;
	private String realName;
	private String password;
	private String email;
	private String phone;
	private String mobilePhone;
	private String sn;
	private int status ; //1 启用，2 禁用
	private String description;
	private int userclass;//1 超级用户，2普通用户
	private long createdTime;
	private long modifiedTime;
	private long lastLoginTime;
	private String userCard;
	private long lockTime;
	
	private long departmentId;
	private String faxNumber;
	private String position;
	private String titles;
	private String ranks;
	private long registFlat;
	private String districts;
	private String locality;
	
	private String foreignKey; //add by liunan 保存外部系统的用户ID
	//edit by wangxinbing add ip mac fields
	private String ip;
	private String mac;
	
	private String orderStatus;//待上报，待审核，审核通过，审核未通过
	/**
	 * 其它属性列列表。
	 */
	private HashMap properties = new HashMap();
	
	public long getCreatedTime()
	{
		return createdTime;
	}
	public void setCreatedTime(long createdTime)
	{
		this.createdTime = createdTime;
	}
	public long getUserDetailId() {
		return userDetailId;
	}
	public void setUserDetailId(long userDetailId) {
		this.userDetailId = userDetailId;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public long getLastLoginTime()
	{
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}
	public String getMobilePhone()
	{
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}
	public long getModifiedTime()
	{
		return modifiedTime;
	}
	public void setModifiedTime(long modifiedTime)
	{
		this.modifiedTime = modifiedTime;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getRealName()
	{
		return realName;
	}
	public void setRealName(String realName)
	{
		this.realName = realName;
	}
	public String getSn()
	{
		return sn;
	}
	public void setSn(String sn)
	{
		this.sn = sn;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public int getUserclass()
	{
		return userclass;
	}
	public void setUserclass(int userclass)
	{
		this.userclass = userclass;
	}
	public long getUserID()
	{
		return userID;
	}
	public void setUserID(long userID)
	{
		this.userID = userID;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public HashMap getProperties() {
		return properties;
	}
	public void setProperties(HashMap properties) {
		this.properties = properties;
	}
	public long getLockTime() {
		return lockTime;
	}
	public void setLockTime(long lockTime) {
		this.lockTime = lockTime;
	}
	public String getForeignKey()
	{
		return foreignKey;
	}
	public void setForeignKey(String foreignKey)
	{
		this.foreignKey = foreignKey;
	}
	public String getUserCard() {
		return userCard;
	}
	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getRanks() {
		return ranks;
	}
	public void setRanks(String ranks) {
		this.ranks = ranks;
	}
	public long getRegistFlat() {
		return registFlat;
	}
	public void setRegistFlat(long registFlat) {
		this.registFlat = registFlat;
	}
	public String getDistricts() {
		return districts;
	}
	public void setDistricts(String districts) {
		this.districts = districts;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	

}
