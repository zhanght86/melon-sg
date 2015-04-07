/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.listener;

import java.util.Iterator;
import java.util.Map;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.service.DictionaryService;
import secfox.soc.melon.base.util.ApplicationContextUtils;
import secfox.soc.melon.persistence.DomainListenerAdapter;
import secfox.soc.melon.asset.staff.domain.OuterStaff;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

/**
 * @since 2014-11-21,下午12:25:04
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class OuterStaffListener extends DomainListenerAdapter<OuterStaff> {

	@PostLoad
	public void afterLoad(OuterStaff staff) {
		//专职岗位
		String fullJobs = staff.getFullJob();
		if(StringUtils.isNotBlank(fullJobs)) {
			Iterable<String> fullJobsItem= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(fullJobs);
			//获取数据字典
			Map<String, String> dic = ApplicationContextUtils.getBean(DictionaryService.class).findByKey("outerstaff.fulljob");
			Iterator<String> ite = fullJobsItem.iterator();
			StringBuffer job = new StringBuffer();
			while(ite.hasNext()) {
				job.append(dic.get(ite.next())+",");
			}
			//设置专职岗位名称
			staff.setJob(job.toString());
			//
			staff.setFullJobs(Iterables.toArray(Ints.stringConverter().convertAll(fullJobsItem), Integer.class));
		}
		//证书
		String certificates = staff.getCertificate();
		if(StringUtils.isNotBlank(certificates)) {
			Iterable<String> certificatesItem= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(certificates);
			//
			staff.setCertificates
			(Iterables.toArray(Ints.stringConverter().convertAll(certificatesItem), Integer.class));
		}
		
		//维护权限
		String maintainAutors = staff.getMaintainAutor();
		if(StringUtils.isNotBlank(maintainAutors)) {
			Iterable<String> maintainAutorsItem= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(maintainAutors);
			//
			staff.setMaintainAutors(Iterables.toArray(Ints.stringConverter().convertAll(maintainAutorsItem), Integer.class));
		}
		//工作
		String works = staff.getWork();
		if(StringUtils.isNotBlank(works)) {
			Iterable<String> worksItem= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(works);
			//
			staff.setWorks(Iterables.toArray(Ints.stringConverter().convertAll(worksItem), Integer.class));
		}
	}
	
	
	/**
	 * 在保存数据之前,将Integer[]转换为字符串
	 */
	@PrePersist
	@PreUpdate
	public void prePersist(OuterStaff staff) {
		//将适用范围转换为字符串,然后存储到数据库
		Integer[] fulljob2 = staff.getFullJobs();
		if(fulljob2 != null) {
			staff.setFullJob(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(fulljob2));
		}
		
		//将适用范围转换为字符串,然后存储到数据库
		Integer[] certificates2 = staff.getCertificates();
		if(certificates2 != null) {
			staff.setCertificate(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(certificates2));
		}
		
		//将适用范围转换为字符串,然后存储到数据库
		Integer[] maintainAutors2 = staff.getMaintainAutors();
		if(maintainAutors2 != null) {
			staff.setMaintainAutor(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(maintainAutors2));
		}
		
		//将适用范围转换为字符串,然后存储到数据库
		Integer[] works2 = staff.getWorks();
		if(works2 != null) {
			staff.setWork(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(works2));
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListenerAdapter#preUpdate(java.lang.Object)
	 */
	public void preUpdate(OuterStaff staff) {
		prePersist(staff);
	}

}
