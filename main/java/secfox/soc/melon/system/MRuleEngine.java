/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import org.mvel2.MVEL;
import org.springframework.stereotype.Component;

import secfox.soc.melon.system.domain.MRule;
import secfox.soc.melon.system.service.MRuleService;



/**
 * @since 1.0 2013-3-21,下午3:34:50
 *
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component("mRuleEngine")
public class MRuleEngine {
	
	private MRuleService mRuleSevice;
	
	/**
	 * @param mRuleSevice
	 */
	@Inject
	public MRuleEngine(MRuleService mRuleSevice) {
		super();
		this.mRuleSevice = mRuleSevice;
	}
	
	/**
	 * 
	 * @param ruleId 检验的规则ID
	 * @param context 待检验的对象
	 * @return
	 */
	public boolean checkValid(Long ruleId, Object context) {
		return MVEL.executeExpression(mRuleSevice.getCompiledRuleById(ruleId), context, Boolean.class);
	}
	
	public boolean checkValid(MRule rule, Object context) {
	    return MVEL.executeExpression(mRuleSevice.getCompiledRule(rule), context, Boolean.class);
	}
	
	public Map<String,Boolean> getRuleAction(int action){
	    Map<String,Boolean> map = new HashMap<String,Boolean>();
        if(action == 1){
           map.put("sendAlarm", true);
        }else if(action == 10){
            map.put("sendWorkOrder", true);
        }else if(action == 11){
            map.put("sendAlarm", true);
            map.put("sendWorkOrder", true);
        }else if(action == 100){
            map.put("sendMessage", true);
        }else if(action == 101){
            map.put("sendAlarm", true);
            map.put("sendAlarm", true);
        }else if(action == 110){
            map.put("sendWorkOrder", true);
            map.put("sendMessage", true);
        }else if(action == 111){
            map.put("sendAlarm", true);
            map.put("sendWorkOrder", true);
            map.put("sendMessage", true);
        }
        return map;
	}
}
