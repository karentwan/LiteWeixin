package cn.karent.listener;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import cn.karent.util.ParserUtil;

/**
 * 全局监听器（容器初始化）
 * @author wan
 */
public class ContextListener implements ServletContextListener {
	
	/**
	 * 保存属性集合
	 */
	private Map<String, Object> map = new HashMap<String, Object>();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		//获取里面的配置下面的路径
		String url = ContextListener.class.getResource("").getPath().replaceAll("%20", " ");  
        String path = url.substring(0, url.indexOf("WEB-INF")) + "WEB-INF/classes/weixin.xml";
        if( path.startsWith("file:")) {
        	path = path.substring(5, path.length());
        }
        try {
			ParserUtil.xml2map(map, path);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
System.out.println("ContextListener Initialization!");
		//将自身放置在application容器下面，方便程序其他地方获取
		application.setAttribute("weixin", this);
	}
	
	public Object getAttribute(String key) {
		return map.get(key);
	}
	
}
