package secfox.soc.melon.alarm.service;

import java.util.List;

import secfox.soc.melon.alarm.domain.AlarmType;
import secfox.soc.melon.indicator.domain.IndicatorDefine;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014-10-21,下午3:22:37
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public interface AlarmTypeService extends GenericService<AlarmType, Long> {
	
	/**
	 * 动态的根节点
	 * @param rootId 节点id
	 * @param choosen 是否包含通用路径
	 * @return
	 */
	List<AlarmType> findTree(Long rootId, boolean choosen);

	/**
	 * 获取类型
	 * @return
	 */
	List<AlarmType> findAll();

	/**
	 * 获取子节点个数
	 * @param parentId
	 * @return
	 */
	List<AlarmType> findByParentId(Long parentId);
	

}

