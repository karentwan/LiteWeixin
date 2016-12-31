package cn.karent.bean.resp;

import cn.karent.bean.common.BaseMessage;

/**
 * 回复图片消息
 * @author wan
 */
public class ImageMessage extends BaseMessage{
	
	public ImageMessage() {
		setMsgType("image");
	}
	
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
