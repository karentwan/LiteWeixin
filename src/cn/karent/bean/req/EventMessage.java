package cn.karent.bean.req;

import cn.karent.bean.common.BaseMessage;

/**
 * 事件消息
 * @author wan
 */
public class EventMessage extends BaseMessage {
	
	private String Event;
	
	private String EventKey;

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	

}
