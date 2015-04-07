/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * @since 0.1 2012-12-11,上午10:22:15下午
 * 可持久化类的基本类, 建议所有的持久化类都从此类继承
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 0.1
 */
public abstract class BaseDomain<ID extends Serializable> implements Identifiable<ID> {
    /**
     * 
     */
    private static final long serialVersionUID = -8212663249037176387L;
    
    /*
     * (non-Javadoc)
     * 
     * @see secfox.soc.melon.persistence.Identifiable#hasId()
     */
    @Override
    public boolean hasId() {
        ID primaryKey = this.getId();
        if(primaryKey instanceof String) {
            return StringUtils.isNotBlank((String) primaryKey);
        }
        return primaryKey != null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public abstract boolean equals(Object obj);
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public abstract int hashCode();
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public abstract String toString();
}
