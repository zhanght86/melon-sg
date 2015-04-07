/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.service;

import java.util.Date;
import java.util.List;

import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.alarm.domain.AlarmHandle;
import secfox.soc.melon.alarm.query.AlarmPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.persistence.QueryTemplate;

/**
 *
 * @author yb
 */
public interface AlarmHandleService extends GenericService<AlarmHandle, Long> {
}
