package cn.itcast.listener01;

import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 *
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServletContextListener() {
    	System.out.println("ServletContextListener构造函数被执行了...");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
    	ServletContext sctx = event.getServletContext();
    	try {
			String webpath = sctx.getResource("/").getPath();
			System.out.println("webpath:"+webpath);
			String RealPath = sctx.getRealPath("/");
			System.out.println("RealPath:"+RealPath);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("ServletContext对象被创建了");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	System.out.println("ServletContext对象被销毁了");
    }
	
}
