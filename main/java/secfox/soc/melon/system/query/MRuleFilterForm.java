/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.query;

import secfox.soc.melon.system.domain.MRuleFilter;


 /**
 * @since 1.0 2013-6-6,下午2:11:33
 *
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @version  1.0 
 */
public class MRuleFilterForm extends MRuleFilter{

    /**
     * @Fields serialVersionUID:TODO（用一句话描述这个变量表示什么）
     */
    private static final long serialVersionUID = 4512015901206745647L;
    
    private Long ruleId;

    private String ruleName;
    
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
    
}
