package secfox.soc.melon.rule.service;

import java.util.List;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.rule.domain.EplValue;
import secfox.soc.melon.rule.query.EplValuePageQuery;

public interface EplValueService extends GenericService<EplValue, Long> {
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	Pagination<EplValue> findPage(EplValuePageQuery pageQuery);
	
	/**
	 * 改变模板状态
	 * @param id
	 */
	void changeState(Long id);
	
	/**
	 * 按配置文件名称找阈值
	 * @param name
	 * @return
	 */
	public EplValue findByName(String name); 
	
	/**
	 * 从数据库获得模板信息
	 * @param name
	 * @return
	 */
	public List<EplValue> findTemplate();

}
