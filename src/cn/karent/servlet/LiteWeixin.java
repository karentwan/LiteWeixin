package cn.karent.servlet;

import java.util.Date;
import cn.karent.bean.common.BaseMessage;
import cn.karent.bean.common.TextMessage;
import cn.karent.bean.req.ImageMessage;
import cn.karent.bean.req.LinkMessage;
import cn.karent.bean.req.LocationMessage;
import cn.karent.bean.req.VoiceMessage;
import cn.karent.bean.req.VideoMessage;

/**
 * @author wan
 * 开发者应该继承这个类，因为所有的消息都将发到这个类的相应方法
 */
public class LiteWeixin {
	
	/**
	 * 处理文本消息
	 * @param text TextMessage封装
	 * @return 想要向微信服务器返回的信息
	 */
	public BaseMessage textMessage(TextMessage text) {
		
		return null;
	}
	
	/**
	 * 处理图片信息
	 * @param image 图片消息的封装 
	 * @return 
	 */
	public BaseMessage imageMessage(ImageMessage image) {
		
		return null;
	}
	
	/**
	 * 处理语音消息
	 * @param voice 语音消息的封装
	 * @return
	 */
	public BaseMessage voiceMessage(VoiceMessage voice) {
		return null;
	}
	
	/**
	 * 处理连接消息
	 * @param link 连接消息的封装
	 * @return
	 */
	public BaseMessage linkMessage(LinkMessage link) {
		return null;
	}
	
	/**
	 * 处理地理位置消息
	 * @param location 地理位置消息的封装
	 * @return
	 */
	public BaseMessage locationMessage(LocationMessage location) {
		return null;
	}
	
	/**
	 * 处理视频消息
	 * @param video
	 * @return
	 */
	public BaseMessage videoMessage(VideoMessage video) {
		return null;
	}
	
	/**
	 * 创建一个响应的消息之前应该调用这个方法，否则就需要创建自己将信息放进去
	 * @param base 被封装了的请求的bean
	 * @param t    被封装了的响应的bean 
	 */
	protected <T extends BaseMessage> void initResponseData(BaseMessage base, T t) {
		t.setFromUserName(base.getToUserName());
		t.setCreateTime(new Date().getTime());
		t.setFuncFlag(0);
		t.setMsgType(base.getMsgType());
		t.setToUserName(base.getFromUserName());
	}

}
