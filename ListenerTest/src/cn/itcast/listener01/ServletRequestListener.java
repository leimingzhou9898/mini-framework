package cn.itcast.listener01;

import javax.servlet.ServletRequestEvent;

/**
 * Application Lifecycle Listener implementation class ServletRequestListener
 *
 */
public class ServletRequestListener implements javax.servlet.ServletRequestListener {

    /**
     * Default constructor. 
     */
    public ServletRequestListener() {
    	System.out.println("ServletRequestListener构造函数被创建了");
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0) {
    	System.out.println("request被销毁了");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0) {
    	System.out.println("request被创建了");
    }
   
}
