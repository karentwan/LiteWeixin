package cn.karent.util;

import java.io.Writer;
import cn.karent.bean.resp.Article;
import cn.karent.bean.resp.NewsMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 信息处理的工具类
 * @author wan
 */
public class MessageUtil {
	
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = true;
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}
				
				public void writeText(QuickWriter writer, String text) {
					if(cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	/**
	 * class
	 * @param t
	 * @return
	 */
	public static <T> String clazzToXml(T t) {
		xstream.alias("xml", t.getClass());
		if( t.getClass() == NewsMessage.class) {
			xstream.alias("item", Article.class);
		}
		return xstream.toXML(t);
	}
	
}
