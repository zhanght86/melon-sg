/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2013-4-15,上午11:23:48
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_MRule_FILTER")
public class MRuleFilter extends BaseDomain<Long> {
    
    private static final long serialVersionUID = 2177228147461411201L;

    @Id@Column(name="PK")
    @GeneratedValue(generator = "GEN_SEQ_APP")
    private Long id;
    
    //过滤器名称
    @Column(name="FILTER_NAME")
    private String filterName;
    
    //需要过滤的实体类
    @Column(name="FILTER_ENTITY")
    private String filterEntity;
    
    //过滤器类
    @Column(name="FILTER_CLASS")
    private String filterClass;
    
    //是否启用
    @Column(name="FILTER_ENABLED")
    private boolean enabled = true;
    
    //过滤器描述信息
    @Column(name="FILTER_DESC")
    private String desc;
    
    @Column(name="FILTER_RULEID")
    private Long ruleId;
    
    /**
     * 添加规则到过滤器
     * @param rule
     */
    
    public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	/**
     * @return the filterName
     */
    public String getFilterName() {
        return filterName;
    }
    
    /**
     * @return the filterEntity
     */
    public String getFilterEntity() {
        return filterEntity;
    }

    /**
     * @param filterEntity the filterEntity to set
     */
    public void setFilterEntity(String filterEntity) {
        this.filterEntity = filterEntity;
    }


    /**
     * @param filterName the filterName to set
     */
    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    /**
     * @return the filterClass
     */
    public String getFilterClass() {
        return filterClass;
    }

    /**
     * @param filterClass the filterClass to set
     */
    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    /**
     * @return the enabled
     */
    @Dictionary("bool")
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.Identifiable#getId()
     */
    @Override
    public Long getId() {
        return id;
    }
    
    /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        MRuleFilter other = (MRuleFilter) obj;
        if(desc == null) {
            if(other.desc != null)
                return false;
        } else if(!desc.equals(other.desc))
            return false;
        if(enabled != other.enabled)
            return false;
        if(filterClass == null) {
            if(other.filterClass != null)
                return false;
        } else if(!filterClass.equals(other.filterClass))
            return false;
        if(filterName == null) {
            if(other.filterName != null)
                return false;
        } else if(!filterName.equals(other.filterName))
            return false;
        if(id == null) {
            if(other.id != null)
                return false;
        } else if(!id.equals(other.id))
            return false;
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + ((filterClass == null) ? 0 : filterClass.hashCode());
        result = prime * result + ((filterName == null) ? 0 : filterName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MRuleFilter [id=" + id + ", filterName=" + filterName + ", filterClass=" + filterClass + ", enabled=" + enabled + ", desc=" + desc + "]";
    }
    
}
