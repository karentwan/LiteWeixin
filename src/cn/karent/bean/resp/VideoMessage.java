package cn.karent.bean.resp;

import cn.karent.bean.common.BaseMessage;

/**
 * 视频消息
 * @author wan
 */
public class VideoMessage extends BaseMessage {

	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
