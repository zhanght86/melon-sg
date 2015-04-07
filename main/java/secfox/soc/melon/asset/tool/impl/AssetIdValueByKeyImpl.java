/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.tool.impl;

import java.io.IOException;
import java.util.Properties;

import secfox.soc.melon.asset.tool.AssetToolClass;

/**
 * @since @2014-12-11,@下午4:42:34
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@org.springframework.stereotype.Service
public class AssetIdValueByKeyImpl implements AssetToolClass {

	@Override
	public Object getValueByKey(String kay) {
		Properties properties = new Properties();
		 try {
			properties.load(this.getClass().getResourceAsStream("/application-props/assetConfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.get(kay);
	}

}


