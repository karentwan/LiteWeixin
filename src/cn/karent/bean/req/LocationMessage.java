package cn.karent.bean.req;

import cn.karent.bean.common.BaseMessage;

/**
 * 地理位置的消息
 * @author wan
 */
public class LocationMessage extends BaseMessage{
	
	private String Location_X;
	
	private String Location_Y;
	
	private String Scale;
	
	private String Label;

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}
	
	
	
}