/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.service;

import java.util.List;

import secfox.soc.melon.knowledge.domain.KnowledgeType;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since @2014-10-28,@下午3:12:19
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
public interface KnowledgeTypeService extends GenericService<KnowledgeType,Long>{
	/**
	 * 动态的根节点
	 * @param rootId 节点id
	 * @param choosen 是否包含通用路径
	 * @return
	 */
	List<KnowledgeType> findTree(Long rootId, boolean choosen);

	/**
	 * 获取类型
	 * @return
	 */
	List<KnowledgeType> findAll();

	/**
	 * 获取子节点个数
	 * @param parentId
	 * @return
	 */
	List<KnowledgeType> findByParentId(Long parentId);
	
}

