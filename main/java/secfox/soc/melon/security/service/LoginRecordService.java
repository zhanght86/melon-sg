package secfox.soc.melon.security.service;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.security.domain.LoginRecord;

public interface LoginRecordService extends GenericService<LoginRecord, Long> {

	/**
	 * 按账号名称查找
	 * @param name
	 * @return
	 */
	public LoginRecord findByName(String name);
	
	/**
	 * 登录出错插入数据
	 * @param name
	 */
	public void insertRecord(String name);
	
	/**
	 * 删除数据
	 * @param name
	 */
	public void removeRecord(String name);
}
