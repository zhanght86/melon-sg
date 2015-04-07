/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.tool;

/**
 * 安全对象的工具类（本类提供开发帮助）
 * @since @2014-12-11,@下午4:12:36
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface AssetToolClass {

	/**
	 * 读取assetConfig.properties
	 * @param key properties文件中的key
	 * @return
	 */
	Object getValueByKey(String key);
	
}


