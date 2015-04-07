/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.system.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.system.dao.GlobalConfigDao;
import secfox.soc.melon.system.domain.GlobalConfig;
import secfox.soc.melon.system.service.GlobalConfigService;

import com.google.common.collect.Maps;

/**
 * @since 2014-9-24,下午5:18:12
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class GlobalConfigServiceImpl extends GenericServiceImpl<GlobalConfig, Long>
		implements GlobalConfigService {

	private GlobalConfigDao dao;
	

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<GlobalConfig, Long> getDao() {
		return dao;
	}
	
	@Inject
	public GlobalConfigServiceImpl(GlobalConfigDao dao) {
		super();
		this.dao = dao;
	}
	
	

	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.service.GlobalConfigService#create()
	 */
	@Override
	public GlobalConfig create() {
		
		String organName = MessageSourceUtils.getMessage("system.globalConfig.organ");
		//创建默认节点
		GlobalConfig globalConfig = new GlobalConfig(BaseConstants.ROOT_ID, 1L, organName);
		merge(globalConfig);
		return globalConfig;
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.service.GlobalConfigService#find()
	 */
	@Override
	@Transactional(readOnly=false)
	public GlobalConfig find() {
		GlobalConfig globalConfig = find(1l);
		//判断是否有配置信息
		if(globalConfig == null){
			globalConfig = create();//不存在则创建数据
		}
		return globalConfig;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.service.GlobalConfigService#update()
	 */
	@Override
	@Transactional(readOnly=false)
	public GlobalConfig update(GlobalConfig globalConfig, HttpServletRequest request) {
		//获取附加属性的key值
		String keys[] = request.getParameterValues("attributesKey");
		//获取附件属性的value值
		String values[] =  request.getParameterValues("attributesValue");
		Map<String, String> attributes = Maps.newHashMap();
		//判断是添加附加属性
		if(keys!=null){
			for(int i = 0; i<keys.length; i++){
				//如果key值是否为空
				if(StringUtils.isNotBlank(keys[i])){
					attributes.put(keys[i], values[i]);
				}
			}
		}
		globalConfig.setAttributes(attributes);
		//设置ip字段
		String ipLeft = globalConfig.getIpLeftRange();
		String ipRight = globalConfig.getIpRightRange();
		//截取左ip
		globalConfig.setIpLeftRange(ipLeft.substring(0, ipLeft.length()-1));
		//截取右ip
		globalConfig.setIpRightRange(ipRight.substring(0, ipRight.length()-1));
		merge(globalConfig);
		return globalConfig;
	}

}
