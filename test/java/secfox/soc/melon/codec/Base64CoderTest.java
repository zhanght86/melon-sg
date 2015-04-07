/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.codec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.BCodec;

/**
 * @since 2015年1月23日,上午9:58:32
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class Base64CoderTest {

	/**
	 * @param args
	 * @throws EncoderException 
	 * @throws DecoderException 
	 */
	public static void main(String[] args) throws EncoderException, DecoderException {
		String x = "gan,10010,1";
		BCodec encoder = new BCodec();
		String result = encoder.encode(x);
		System.out.println(result);
		//
		String source = encoder.decode(result);
		System.out.println(source);
	}

}
