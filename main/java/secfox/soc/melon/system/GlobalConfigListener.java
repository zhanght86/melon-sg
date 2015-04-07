package secfox.soc.melon.system;

import javax.persistence.PostLoad;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.DomainListenerAdapter;
import secfox.soc.melon.system.domain.GlobalConfig;

public class GlobalConfigListener extends DomainListenerAdapter<GlobalConfig> {

	@PostLoad
	public void afterLoad(GlobalConfig config) {
		//获取密码强度
		String passStrengths = config.getPassStrengths();
		if(StringUtils.isNotBlank(passStrengths)) {
			Iterable<String> strengthItems= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(passStrengths);
			//将字符串转换为整数数组
			Integer[] tmp = Iterables.toArray(Ints.stringConverter().convertAll(strengthItems), Integer.class);
			config.setPassStrength(tmp);
			config.setPassStr(patternFormat(tmp));
			
		}
	}
	
	private String patternFormat(Integer[] patterns) {
		String pattern = new String();
		for(int i=0;i<patterns.length;i++) {
			switch(patterns[i].intValue()) {
			case 1 :
				pattern += "aa";
				break;
			case 2 :
				pattern += "AA";
				break;
			case 3 :
				pattern += "$$";
				break;
			case 4 :
				pattern += "11";
				break;
			default :
				pattern = "aaAA$$11";
				break;
			}
		}
		//补全缺省
		int miss = 8-pattern.length();
		if(miss>0) {
			String holder = new String();
			for(int j=0;j<miss;j++) {
				holder += pattern.substring(0, 1);
			}
			return pattern.concat(holder);
		}
		return pattern;
	}
}
