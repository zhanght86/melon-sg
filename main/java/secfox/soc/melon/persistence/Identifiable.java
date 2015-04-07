/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

import java.io.Serializable;

/**
 * @since 0.1 2012-12-11,上午10:19:28下午
 *        可持久化类接口
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 0.1
 */
public interface Identifiable<ID extends Serializable> extends Serializable {
    
    /**
     * 获取实体类的主键
     * 
     * @return 主键
     */
    ID getId();
    
    /**
     * 判断主键是否已设置
     * 
     * @return 主键是否已设置
     */
    boolean hasId();
    
    /**
     * 设置主键
     * 
     * @param id 主键
     */
    void setId(ID id);
    
}
