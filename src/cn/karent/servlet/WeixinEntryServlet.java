package cn.karent.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.karent.bean.common.BaseMessage;
import cn.karent.bean.common.TextMessage;
import cn.karent.bean.req.EventMessage;
import cn.karent.bean.req.ImageMessage;
import cn.karent.bean.req.LinkMessage;
import cn.karent.bean.req.LocationMessage;
import cn.karent.bean.req.VideoMessage;
import cn.karent.bean.req.VoiceMessage;
import cn.karent.exception.ConfigException;
import cn.karent.listener.ContextListener;
import cn.karent.util.MessageUtil;
import cn.karent.util.ParserUtil;

/**
 * 微信开发的入口，get代表验证，post代表消息的回应
 * @author wan
 */
@WebServlet(name="entry",
urlPatterns={"/entry"})
public class WeixinEntryServlet extends HttpServlet {

	/**
	 * 接入微信入口
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext application = getServletContext();
		ContextListener cl = (ContextListener) application.getAttribute("weixin");
		String token = (String) cl.getAttribute("token");
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		PrintWriter out = resp.getWriter();
		if(checkSignature(signature, timestamp, nonce, token)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	
	private boolean checkSignature(String signature, String timestamp, String nonce, String token) {
		String[] arrs = {token, timestamp, nonce};
		//将这三个按照字典序排序
		Arrays.sort(arrs);
		//将这三个字符串拼接成一个长字符串
		StringBuilder content = new StringBuilder();
		for(int i = 0; i < arrs.length; i++) {
			content.append(arrs[i]);
		}
		//将拼接好了的字符串按照sha1算法加密
		MessageDigest md = null;
		String tempStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			tempStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		content = null;
		return tempStr != null ? tempStr.equals(signature.toUpperCase()):false;
	}

	private String byteToStr(byte[] bytes) {
		String strDigest = "";
		for(int i = 0; i < bytes.length; i++) {
			strDigest += byteToHexStr(bytes[i]);
		}
		return strDigest;
	}
	
	private String byteToHexStr(byte mByte) {
		char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0x0F];
		tempArr[1] = Digit[mByte & 0x0F];
		String s = new String(tempArr);
		return s;
	}
	
	/**
	 * 消息入口
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		InputStream in = req.getInputStream();
		try {
			String type = ParserUtil.findMsgType(in);
			//创建要处理的核心类
			ServletContext application = getServletContext();
			ContextListener context = (ContextListener)application.getAttribute("weixin");
			String clazzStr = (String)context.getAttribute("core");
			if( clazzStr == null) {
				throw new ConfigException("请配置core元素");
			}
			Class clazz = Class.forName(clazzStr);
			Object obj = clazz.newInstance();
			String respStr = null;
			BaseMessage respBean = null;
			//处理请求
			if( type.equals("text")) {
				Method m = clazz.getMethod("textMessage", TextMessage.class);
				TextMessage text = new TextMessage();
				ParserUtil.parseTextRequest(in, text);
				respBean =(BaseMessage) m.invoke(obj, text);
			} else if( type.equals("image")) {
				Method m = clazz.getMethod("imageMessage", ImageMessage.class);
				ImageMessage image = new ImageMessage();
				ParserUtil.parseImageRequest(in, image);
				respBean = (BaseMessage)m.invoke(obj, image);
			} else if( type.equals("voice")) {
				Method m = clazz.getMethod("voiceMessage", VoiceMessage.class);
				VoiceMessage voice = new VoiceMessage();
				ParserUtil.parseVoiceRequest(in, voice);
				respBean = (BaseMessage)m.invoke(obj, voice);
			} else if( type.equals("video")) {
				Method m = clazz.getMethod("videoMessage", VideoMessage.class);
				VideoMessage video = new VideoMessage();
				ParserUtil.parseVideoRequest(in, video);
				respBean = (BaseMessage)m.invoke(obj, video);
			} else if( type.equals("location")) {
				Method m = clazz.getMethod("locationMessage", LocationMessage.class);
				LocationMessage location = new LocationMessage();
				ParserUtil.parseLocationRequest(in, location);
				respBean = (BaseMessage)m.invoke(obj, location);
			} else if( type.equals("link")) {
				Method m = clazz.getMethod("linkMessage", LinkMessage.class);
				LinkMessage link = new LinkMessage();
				ParserUtil.parseLinkRequest(in, link);
				respBean = (BaseMessage)m.invoke(obj, link);
				//事件消息
			} else if( type.equals("event")) {
				Method m = clazz.getMethod("eventMessage", EventMessage.class);
				EventMessage event = new EventMessage();
				ParserUtil.parseEventRequest(in, event);
				respBean = (BaseMessage)m.invoke(obj, event);
			} else {
				//TODO nothing
			}
System.out.println("type：" + type + "\n\n");
			//向微信服务器响应
			if( respBean != null) {
				respStr = MessageUtil.clazzToXml(respBean);
System.out.println("resp:" + respStr);
			}
			if( respStr != null) {
				PrintWriter out = resp.getWriter();
				out.print(respStr);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
