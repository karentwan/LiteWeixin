package cn.karent.bean.common;


/**
 * 文本消息
 * @author wan
 */
public class TextMessage extends BaseMessage{
	
	public TextMessage() {
		setMsgType("text");
	}
	
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}