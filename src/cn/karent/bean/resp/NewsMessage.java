package cn.karent.bean.resp;

import java.util.List;
import cn.karent.bean.common.BaseMessage;

/**
 * 图文消息
 * @author wan
 */
public class NewsMessage extends BaseMessage{
	
	public NewsMessage() {
		setMsgType("news");
	}
	
	private int ArticleCount;
	
	private List<Article> Articles;
	
	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

}
