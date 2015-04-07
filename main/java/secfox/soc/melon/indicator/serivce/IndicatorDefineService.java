package secfox.soc.melon.indicator.serivce;

import java.util.List;

import secfox.soc.melon.indicator.domain.IndicatorDefine;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014-10-15,下午3:36:25
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public interface IndicatorDefineService extends	GenericService<IndicatorDefine, Long> {

	/**
	 * 动态的根节点
	 * @param rootId 节点id
	 * @param choosen 是否包含通用路径
	 * @return
	 */
	List<IndicatorDefine> findTree(Long rootId, boolean choosen);

	/**
	 * 获取类型
	 * @return
	 */
	List<IndicatorDefine> findAll();

	/**
	 * 获取子节点个数
	 * @param parentId
	 * @return
	 */
	List<IndicatorDefine> findByParentId(Long parentId);
	
}

