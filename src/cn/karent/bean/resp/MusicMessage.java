package cn.karent.bean.resp;

import cn.karent.bean.common.BaseMessage;

/**
 * 音乐消息
 * @author wan
 */
public class MusicMessage extends BaseMessage{
	
	public MusicMessage() {
		setMsgType("music");
	}
	
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
	
}
