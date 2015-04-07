package secfox.soc.melon.datas.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name="ARTI_RECE")
public class ArticleReceiver extends BaseDomain<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//接收人
	@Embedded
	private UserInfo receiver;
	
	//反馈状态
	@Column(name="FAD_STA")
	private boolean fadeState;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true,targetEntity=Article.class)
	@JoinColumn(name="ART_PK")
	private Article article;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserInfo getReceiver() {
		return receiver;
	}

	public void setReceiver(UserInfo receiver) {
		this.receiver = receiver;
	}

	public boolean isFadeState() {
		return fadeState;
	}

	public void setFadeState(boolean fadeState) {
		this.fadeState = fadeState;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + (fadeState ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((receiver == null) ? 0 : receiver.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleReceiver other = (ArticleReceiver) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (fadeState != other.fadeState)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleReceiver [id=" + id + ", receiver=" + receiver
				+ ", fadeState=" + fadeState + ", article=" + article + "]";
	}
	
	
	
	
}
