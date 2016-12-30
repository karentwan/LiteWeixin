package cn.karent.bean.req;

import cn.karent.bean.common.BaseMessage;

/**
 * 请求之图片消息
 * @author wan
 */
public class ImageMessage extends BaseMessage {
	
	private String PicUrl;
	
	private String MediaId;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
