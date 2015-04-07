/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.work.myWork.service;

import java.util.List;

import secfox.soc.melon.work.myWork.domain.MyWork;

/**
 * 获取任务
 * @since 2014-11-26,上午11:29:00
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface WorkScanService {

	/**
	 * 扫描待办任务
	 * 只需设置MyWork对象的标题、来源（2）、工作类型、执行人、截止时间、执行操作、完成状态
	 * @return
	 */
	public List<MyWork> scan();
	
	/**
	 * 扫描已下发的任务
	 * @return
	 */
	public List<MyWork> scanPassed();
}
