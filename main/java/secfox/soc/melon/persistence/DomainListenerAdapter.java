/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

/**
 * @since 1.0 2014年10月4日,上午9:42:16
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DomainListenerAdapter<T> implements DomainListener<T> {

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListener#postLoad(java.lang.Object)
	 */
	@Override
	public void postLoad(T t) {
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListener#prePersist(java.lang.Object)
	 */
	@Override
	public void prePersist(T t) {
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListener#postPersist(java.lang.Object)
	 */
	@Override
	public void postPersist(T t) {
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListener#preUpdate(java.lang.Object)
	 */
	@Override
	public void preUpdate(T t) {
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListener#postUpdate(java.lang.Object)
	 */
	@Override
	public void postUpdate(T t) {
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListener#preRemove(java.lang.Object)
	 */
	@Override
	public void preRemove(T t) {
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListener#postRemove(java.lang.Object)
	 */
	@Override
	public void postRemove(T t) {
		
	}

}
