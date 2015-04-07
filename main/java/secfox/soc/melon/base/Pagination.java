/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Objects;

/**
 * @since 0.1 2013-3-12,下午10:37:25
 * 分页构造器，用于返回分页查询的结果
 * T代表返回的数据类型,可以是Object[]\POJO\MAP
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 0.1
 */
public class Pagination<T> implements Serializable {
	
	public static final int PAGE_SIZE = 15;
    
    private static final long serialVersionUID = -1957288153225519960L;
    
    private int count;
    
    private int currPage = 1;
    
    private int pageSize = PAGE_SIZE;
    
    private List<T> results;
    
    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }
    
    /**
     * @return the currPage
     */
    public int getCurrPage() {
        return currPage;
    }
    
    /**
     * 计算共有多少页
     * 
     * @return 页数
     */
    public int getPages() {
        if(this.count % this.pageSize == 0) {
            return this.count / this.pageSize;
        }
        return this.count / this.pageSize + 1;
    }
    
    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
    
    /**
     * @return the results
     */
    public List<T> getResults() {
        return results;
    }
    
    /**
     * @param count
     *            the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
    
    /**
     * @param currPage
     *            the currPage to set
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    
    /**
     * @param pageSize  the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * @param results
     *            the results to set
     */
    public void setResults(List<T> results) {
        this.results = results;
    }
    
    @Override
    public String toString() {
    	return Objects.toStringHelper(this)
    			      .add("count", this.count)
    			      .add("currPage", this.currPage)
    			      .add("pageSize", this.pageSize)
    			      .toString();
    }
    
}
