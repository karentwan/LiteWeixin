package cn.karent.bean.resp;

import cn.karent.bean.common.BaseMessage;

/**
 * 语音消息
 * @author wan
 */
public class VoiceMessage extends BaseMessage {
	
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
	
	

}
