package cn.karent.bean.req;

import cn.karent.bean.common.BaseMessage;

/**
 * 链接消息
 * @author wan
 */
public class LinkMessage extends BaseMessage {
	
	private String Title;
	
	private String Description;
	
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
	

}
