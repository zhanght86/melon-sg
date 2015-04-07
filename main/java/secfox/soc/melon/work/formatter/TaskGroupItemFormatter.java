/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.formatter;

import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import secfox.soc.melon.work.task.domain.DutyTaskItem;
import secfox.soc.melon.work.task.service.DutyTaskItemService;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-12
 * @version  1.0
 */
@Component("taskGroupItemFormatter")
public class TaskGroupItemFormatter  implements Formatter<DutyTaskItem>{

	private DutyTaskItemService dutyTaskItemService;
	
	/**
	 * 
	 */
	@Inject
	public TaskGroupItemFormatter(DutyTaskItemService dutyTaskItemService) {
		super();
		this.dutyTaskItemService = dutyTaskItemService;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public String print(DutyTaskItem taskItem, Locale arg1) {
		if(taskItem != null) {
			return String.valueOf(taskItem.getId());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public DutyTaskItem parse(String taskItemId, Locale arg1) throws ParseException {
		if (StringUtils.isNotBlank(taskItemId)) {
			try {
				return dutyTaskItemService.find(Long.parseLong(taskItemId));
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		return null;
	}

	

}
