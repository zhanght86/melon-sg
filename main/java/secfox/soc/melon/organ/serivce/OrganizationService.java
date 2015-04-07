/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.organ.serivce;

import java.util.List;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.query.OrganizationPageQuery;
import secfox.soc.melon.persistence.GenericService;

/**
 * 
 * @since 1.0 2014年9月3日,下午5:26:17
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface OrganizationService extends GenericService<Organization, Long>{
	
	/**
	 * 创建组织机构节点
	 * @param parentId
	 * @param type
	 * @return
	 */
	Organization create(String parentId, String type);
	
	/**
	 * 创建组织机构的根节点,虚拟节点,不存储在数据库
	 * @return
	 */
	Organization createRoot();
	
	/**
	 * 根据根节点创建树性结构
	 * @param rootId 根节点 如果为0,则加载整棵树
	 * @param includeDepartment 是否加载部门,默认只加载单位
	 * @param full 是否加载此节点下的所有节点,默认只加载2层,例如总局值加载省部节点,如果为true,则一直加载到地市节点
	 * @return
	 */
	List<Organization> findTree(Long rootId, boolean includeDepartment, boolean full);
	
	/**
	 * 根据type类型和父节点查找
	 * @param type
	 * @param parentId
	 * @return
	 */
	List<Organization> findOrgans(int type, Long parentId);
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	Pagination<Organization> findPages(OrganizationPageQuery pageQuery);
	
	/**
	 * 统计单位人员信息
	 * @return
	 */
	List<Organization> countAcc();
	
	/**
	 * 名称唯一性
	 * @param name
	 * @param id
	 * @param type
	 * @return
	 */
	boolean findByName(String name, String id, String type);
	
	/**
	 * 按名字查找
	 * @param name
	 * @return
	 */
	Organization findByName(String name);
	
	/**
	 * 编码唯一性
	 * @param code
	 * @param id
	 * @return
	 */
	boolean findByCode(String code, String id);
	
	/**
	 * 获取直属单位、省级单位、市级单位的数量
	 * @return
	 */
	List<Object[]> getProvinceCount();
	
	/**
	 * 查询子单位
	 * @param organId
	 * @param selfOrgan 是否包含自身单位
	 * @return
	 */
	List<Organization> getSubOrgan(Long organId, boolean selfOrgan);
	
	/**
	 * 树形结构子单位
	 * @param organId
	 * @return
	 */
	List<Organization> getSubOrgan(Long organId);
	
	/**
	 * 自定义树
	 * @param rootId
	 * @param type
	 * @return
	 */
	List<Organization> customTree(Long rootId, int type);
	
}
