package secfox.soc.melon.home.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.home.domain.QuickNote;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014-10-31,下午4:12:08
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public interface QuickNoteService extends GenericService<QuickNote, Long> {

	/**
	 * 统计有多少人添加了注解
	 * @param menuId
	 * @param domainId
	 * @return
	 */
	int count(Long type, Long businessId);
	
	
	
	void saveNotes(HttpServletRequest request,Long type,Long businessId);
	/**
	 * 获取给定资产批注列表
	 * @param menuId
	 * @param domainId
	 * @return
	 */
	List<QuickNote> find(Long type, Long businessId);
	/**
	 * 获取给定用户批注列表
	 * @param menuId
	 * @param domainId
	 * @return
	 */
	List<QuickNote> findByUserId(Long userId);
	
	/**
	 * 获取给定用户批注设备列表
	 * @param menuId
	 * @param domainId
	 * @return
	 */
	List<QuickNote> findNotedDevices(Long userId);
	
	/**
	 * 获取给定用户批注设备列表
	 * @param menuId
	 * @param domainId
	 * @return
	 */
	List<QuickNote> findNotedInfoSystems(Long userId);
	
	
	
	Long delByBussId(Long type, Long businessId, Long userId);
}

