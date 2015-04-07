/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import java.io.Serializable;

import com.google.common.base.Objects;

/**
 * @since 0.1 2013-3-12,下午10:14:35
 * 分页查询的查询表单, 包含分页查询的所需的所有信息，<T>代表需要查询的表单
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 0.1
 */
public abstract class PageQuery<T> implements Serializable {
    
    private static final long serialVersionUID = 4560520853385167442L;
    
    private int currPage = 1;
    
    private SortOrder order = SortOrder.asc;
    
    private int pageSize = Pagination.PAGE_SIZE;
    
    private T searchForm;
    
    private String sortColumn;
    
    /**
     * 
     */
    public PageQuery() {
        this.searchForm = initSearchForm();
    }

    /**
     * 初始化默认的查询表单
     */
    protected abstract T initSearchForm();
    
    /**
     * @return the currPage
     */
    public int getCurrPage() {
        return currPage;
    }
    
    /**
     * 计算需要获取的第一条记录
     * 
     * @return 需要获取的第一条记录
     */
    public int getFirstResult() {
        return (currPage - 1) * pageSize;
    }
    
    /**
     * 计算需要获取的最后一条记录
     * 
     * @return 需要获取的最后一条记录
     */
    public int getMaxResults() {
        return pageSize;
    }
    
    /**
     * @return the order
     */
    public SortOrder getOrder() {
        return order;
    }
    
    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
    
    /**
     * @return the searchForm
     */
    public T getSearchForm() {
        return searchForm;
    }
    
    /**
     * @return the sortColumn
     */
    public String getSortColumn() {
        return sortColumn;
    }
    
    /**
     * @param currPage
     *            the currPage to set
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    
    /**
     * @param order
     *            the order to set
     */
    public void setOrder(SortOrder order) {
        this.order = order;
    }
    
    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * @param searchForm
     *            the searchForm to set
     */
    public void setSearchForm(T searchForm) {
        this.searchForm = searchForm;
    }
    
    /**
     * @param sortColumn
     *            the sortColumn to set
     */
    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    @Override
    public String toString() {
    	return Objects.toStringHelper(this)
    				  .add("currPage", currPage)
    				  .add("pageSize", pageSize)
    				  .add("sortColumn", sortColumn)
    				  .add("order", order)
    				  .add("searchForm", searchForm)
    				  .toString();
    }
    
}
