/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.BCodec;

/**
 * @since 2015-1-23,下午4:09:45
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class UrlCodeUtils {
	
	private static BCodec code = new BCodec();
	
	public static String encode(String param) throws EncoderException {
		return code.encode(param);
	}
	
	public static String decode(String result) throws DecoderException {
		return code.decode(result);
	}

}
