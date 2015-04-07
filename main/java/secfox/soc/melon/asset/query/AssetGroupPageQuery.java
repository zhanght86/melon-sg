/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.query;

import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.base.PageQuery;

/**
 * yb
 */
public class AssetGroupPageQuery  extends PageQuery<AssetGroup> {

	private int num=3;
	
	private Long infoSystemId;

	/**
	 * @return the infoSystemId
	 */
	public Long getInfoSystemId() {
		return infoSystemId;
	}

	/**
	 * @param infoSystemId the infoSystemId to set
	 */
	public void setInfoSystemId(Long infoSystemId) {
		this.infoSystemId = infoSystemId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected AssetGroup initSearchForm() {
		return new AssetGroup();
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
	

}


