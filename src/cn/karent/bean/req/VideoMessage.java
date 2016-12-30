package cn.karent.bean.req;

import cn.karent.bean.common.BaseMessage;

public class VideoMessage extends BaseMessage {
	
	private String MediaId;
	
	private String ThumbMediaId;
	
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

}
