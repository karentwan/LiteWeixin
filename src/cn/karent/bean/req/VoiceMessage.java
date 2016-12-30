package cn.karent.bean.req;

import cn.karent.bean.common.BaseMessage;

/**
 * 声音消息
 * @author wan
 */
public class VoiceMessage extends BaseMessage{
	
	private String MediaId;
	
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
	
	

}
