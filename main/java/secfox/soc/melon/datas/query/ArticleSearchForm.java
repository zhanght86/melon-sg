package secfox.soc.melon.datas.query;

import secfox.soc.melon.datas.domain.Article;

public class ArticleSearchForm extends Article{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long organId;

	//岗位
	private String duty;
	
	
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}
	
	
}
