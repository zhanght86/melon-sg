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
import secfox.soc.melon.asset.staff.domain.InterStaff;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

/**
 * @since 2014-11-20,下午12:09:21
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class InterStaffListener extends DomainListenerAdapter<InterStaff> {
	
 	@PostLoad
	public void afterLoad(InterStaff staff) {
 		
		//专职岗位
		String fullJobs = staff.getFullJob();
		if(StringUtils.isNotBlank(fullJobs)) {
			Iterable<String> fullJobsItem= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(fullJobs);
			//获取数据字典
			Map<String, String> dic = ApplicationContextUtils.getBean(DictionaryService.class).findByKey("interstaff.fulljob");
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
		
		//兼职岗位
		String partJobs = staff.getPartJob();
		if(StringUtils.isNotBlank(partJobs)) {
			Iterable<String> partJobsItem= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(partJobs);
			//
			staff.setPartJobs(Iterables.toArray(Ints.stringConverter().convertAll(partJobsItem), Integer.class));
		}
		//技能
		String techSkills = staff.getTechSkill();
		if(StringUtils.isNotBlank(techSkills)) {
			Iterable<String> techSkillsItem= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(techSkills);
			//
			staff.setTechSkills(Iterables.toArray(Ints.stringConverter().convertAll(techSkillsItem), Integer.class));
		}
		//证书
		/*String certificates = staff.getCertificate();
		if(StringUtils.isNotBlank(certificates)) {
			Iterable<String> certificatesItem= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(certificates);
			staff.setCertificates(Iterables.toArray(Ints.stringConverter().convertAll(certificatesItem), Integer.class));
		}*/
	}
 	
	/**
	 * 在保存数据之前,将Integer[]转换为字符串
	 */
	@PrePersist
	//
	public void prePersist(InterStaff staff) {
		//将适用范围转换为字符串,然后存储到数据库
		Integer[] fulljob2 = staff.getFullJobs();
		if(fulljob2 != null) {
			staff.setFullJob(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(fulljob2));
		}
		
		//将适用范围转换为字符串,然后存储到数据库
		/*Integer[] certificates2 = staff.getCertificates();
		if(certificates2 != null) {
			staff.setCertificate(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(certificates2));
		}*/
		
		//将适用范围转换为字符串,然后存储到数据库
		Integer[] partJobs2 = staff.getPartJobs();
		if(partJobs2 != null) {
			staff.setPartJob(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(partJobs2));
		}
		
		//将适用范围转换为字符串,然后存储到数据库
		Integer[] techSkills2 = staff.getTechSkills();
		if(techSkills2 != null) {
			staff.setTechSkill(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(techSkills2));
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListenerAdapter#preUpdate(java.lang.Object)
	 */
	@PreUpdate
	public void preUpdate(InterStaff staff) {
		prePersist(staff);
	}


}
