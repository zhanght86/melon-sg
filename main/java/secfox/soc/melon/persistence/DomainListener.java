/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

/**
 * @since 1.0 2014年9月27日,上午10:23:52
 * 对注解的应用与拓展@PrePersist,@PostPersist, @PreUpdate, @PostUpdate, @PreRemove, @PostRemove, and @PostLoad
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface DomainListener<T> {

	/**
	 * 在将业务对象从数据中抓取后执行的操作,添加@PostLoad注解
	 * @param t 业务对象
	 */
	public void postLoad(T t);
	
	
	/**
	 * 在将业务对象保存到数据库之前执行的操作,添加@PrePersist注解
	 * @param t 业务对象
	 */
	public void prePersist(T t);
	
	/**
	 * 在将业务对象保存到数据库之后执行的操作,添加@PostPersist注解
	 * @param t
	 */
	public void postPersist(T t);
	
	/**
	 * 在将业务对象更新到数据库之前执行的操作,添加@PreUpdate注解
	 * @param t
	 */
	public void preUpdate(T t);
	
	/**
	 * 在将业务对象更新到数据库之后执行的操作,添加@PostUpdate注解
	 * @param t
	 */
	public void postUpdate(T t);
	
	/**
	 * 删除业务对象之前进行的操作,添加 @PreRemove注解
	 * @param t
	 */
	public void preRemove(T t);
	
	/**
	 * 删除业务对象之后进行的操作,添加 @PostRemove注解
	 * @param t
	 */
	public void postRemove(T t);
	
}
