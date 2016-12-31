package cn.karent.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.karent.bean.common.BaseMessage;
import cn.karent.bean.common.TextMessage;
import cn.karent.bean.req.EventMessage;
import cn.karent.bean.req.ImageMessage;
import cn.karent.bean.req.LinkMessage;
import cn.karent.bean.req.LocationMessage;
import cn.karent.bean.req.VideoMessage;
import cn.karent.bean.req.VoiceMessage;

/**
 * 解析xml的工具类
 * @author wan
 */
public class ParserUtil {
	/**
	 * xpath解析实例
	 */
	private static XPath xpath;
	
	/**
	 * 请求的doc
	 */
	private static Document doc;
	
	static {
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
	}
	
	/**
	 * xml文件转map集合
	 * @param map 要存放的内容
	 * @param fis 文件输入流
	 */
	public static void xml2map(Map<String, Object> map, String path) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		FileInputStream fis = new FileInputStream(new File(path));
		Document doc = builder.parse(fis);
		//获取weixin节点下面的所有节点
		NodeList nodeList = (NodeList)xpath.evaluate("//weixin/*", doc, XPathConstants.NODESET);
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			map.put(n.getNodeName(), n.getTextContent());
		}
		fis.close();
	}
	
	/**
	 * 根据IO流查询MsgType类型
	 * @param in 输入流
	 * @return 当前返回消息的类型
	 */
	public static String findMsgType(InputStream in) throws Exception {
		String type = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		doc = builder.parse(in);
		Node node =(Node)xpath.evaluate("//MsgType", doc, XPathConstants.NODE);
		type = node.getTextContent();
		return type;
	}
	
	private static void parseBaseMessage(Node n, BaseMessage base) {
		String name = n.getNodeName();
		if( name.equals("ToUserName")) {
			base.setToUserName(n.getTextContent());
		} else if( name.equals("FromUserName")){
			base.setFromUserName(n.getTextContent());
		} else if( name.equals("CreateTime")) {
			base.setCreateTime(Long.parseLong(n.getTextContent()));
		} else if( name.equals("MsgType")) {
			base.setMsgType(n.getTextContent());
		} 
	}
	
	/**
	 * 将服务器发过来的xml请求解析成响应的bean
	 * @param in 输入流
	 * @param text 要封装的文本消息
	 */
	public static void parseTextRequest(InputStream in, TextMessage text) throws Exception{
		//获取weixin节点下面的所有节点
		NodeList nodeList = (NodeList)xpath.evaluate("//xml/*", doc, XPathConstants.NODESET);
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			String name = n.getNodeName();
			if( name.equals("Content")) {
				text.setContent(n.getTextContent());
			} else {
				parseBaseMessage(n, text);
			}
		}
	}
	
	
	/**
	 * 解析请求
	 * @param in 输入流
	 * @param message 封装的消息bean 
	 */
	public static void parseEventRequest(InputStream in, EventMessage message) throws Exception{
		NodeList nodeList = (NodeList)xpath.evaluate("//xml/*", doc, XPathConstants.NODESET);
		Class clazz = message.getClass();
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			String name = n.getNodeName();
			if( name.equals("Event")) {
				message.setEvent(n.getTextContent());
			} else if( name.equals("EventKey")) {
				message.setEventKey(n.getTextContent());
			} else {
				parseBaseMessage(n, message);
			}
		}
	}
	
	/**
	 * 解析视频消息
	 * @param in 输入流
	 * @param video 要封装的视频消息
	 */
	public static void parseVideoRequest(InputStream in, VideoMessage video) throws Exception{
		//获取weixin节点下面的所有节点
		NodeList nodeList = (NodeList)xpath.evaluate("//xml/*", doc, XPathConstants.NODESET);
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			String name = n.getNodeName();
			if( name.equals("MediaId")) {
				video.setMediaId(n.getTextContent());
			} else if( name.equals("Format")) {
				video.setFormat(n.getTextContent());
			} else {
				parseBaseMessage(n, video);
			}
		}
	}
	
	/**
	 * 解析图片消息
	 * @param in 输入流
	 * @param image 要封装的图片bean
	 */
	public static void parseImageRequest(InputStream in, ImageMessage image) throws Exception{
		NodeList nodeList = (NodeList)xpath.evaluate("//xml/*", doc, XPathConstants.NODESET);
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			String name = n.getNodeName();
			if( name.equals("PicUrl")) {
				image.setPicUrl(n.getTextContent());
			} else if( name.equals("MediaId")) {
				image.setMediaId(n.getTextContent());
			} else {
				parseBaseMessage(n, image);
			}
		}
	}
	
	/**
	 * 解析语音消息
	 * @param in 输入流
	 * @param message 要封装的语音bean
	 */
	public static void parseVoiceRequest(InputStream in, VoiceMessage message) throws Exception{
		NodeList nodeList = (NodeList)xpath.evaluate("//xml/*", doc, XPathConstants.NODESET);
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			String name = n.getNodeName();
			if( name.equals("Format")) {
				message.setFormat(n.getTextContent());
			} else if( name.equals("MediaId")) {
				message.setMediaId(n.getTextContent());
			} else {
				parseBaseMessage(n, message);
			}
		}
	}
	
	/**
	 * 解析链接消息
	 * @param in 输入流
	 * @param message 要封装链接bean
	 */
	public static void parseLinkRequest(InputStream in, LinkMessage message) throws Exception{
		NodeList nodeList = (NodeList)xpath.evaluate("//xml/*", doc, XPathConstants.NODESET);
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			String name = n.getNodeName();
			if( name.equals("Title")) {
				message.setTitle(n.getTextContent());
			} else if( name.equals("Description")) {
				message.setDescription(n.getTextContent());
			} else if( name.equals("Url")) {
				message.setUrl(n.getTextContent());
			} else {
				parseBaseMessage(n, message);
			}
		}
	}
	
	/**
	 * 解析地理位置消息
	 * @param in
	 * @param message
	 */
	public static void parseLocationRequest(InputStream in, LocationMessage message) throws Exception{
		NodeList nodeList = (NodeList)xpath.evaluate("//xml/*", doc, XPathConstants.NODESET);
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			String name = n.getNodeName();
			if( name.equals("Location_X")) {
				message.setLocation_X(n.getTextContent());
			} else if( name.equals("Location_Y")) {
				message.setLocation_Y(n.getTextContent());
			} else if( name.equals("Scale")) {
				message.setScale(n.getTextContent());
			} else if( name.equals("Label")) {
				message.setLabel(n.getTextContent());
			} else {
				parseBaseMessage(n, message);
			}
		}
	}
	
}
