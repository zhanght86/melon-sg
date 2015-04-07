/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.system.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.system.dao.GlobalConfigDao;
import secfox.soc.melon.system.domain.GlobalConfig;


/**
 * @since 2014-9-24,下午5:14:15
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class GlobalConfigDaoImpl extends GenericDaoImpl<GlobalConfig, Long> implements
		GlobalConfigDao {

	public GlobalConfigDaoImpl() {
		super(GlobalConfig.class);
	}
	
	private void preOperation(GlobalConfig config) {
		Integer[] passStrength = config.getPassStrength();
		if(passStrength != null) {
			config.setPassStrengths(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(passStrength));
		}
	}
	
	@Override
	@Transactional(readOnly=false)
	public void persist(GlobalConfig config) {
		preOperation(config);
		super.persist(config);
	}
	
	@Override
	@Transactional(readOnly=false)
	public GlobalConfig merge(GlobalConfig config) {
		preOperation(config);
		return super.merge(config);
	}

}
