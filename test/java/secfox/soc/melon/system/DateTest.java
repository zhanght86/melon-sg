/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 *
 * @since 1.0 2014-10-9 下午2:29:33
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */

public class DateTest {
	
	/**
	 * 测试返回的时间为今天的时间
	 */
	@Test
	public void testDate(){
		//获取到当前时间
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date d = cal.getTime();
		
		cal.add(Calendar.DAY_OF_WEEK, 1);
		Calendar end = cal;
		//cal.add(Calendar.DAY_OF_WEEK, 1);
		//cal.set(cal.YEAR, cal.MONTH, cal.DATE, 0, 0, 0);
//		System.out.println(start.getTime());
//		System.out.println(end.getTime());
	}
	
	@Test
	public void testSimpleDateFormat(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = format.format(date);
		System.out.println(s);
	}
}
