package cn.itcast.listener02;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * Application Lifecycle Listener implementation class HttpSessionAttributeListener
 *
 */
public class ServletContextAttributeChangeListener implements ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public ServletContextAttributeChangeListener() {
        // TODO Auto-generated constructor stub
    	System.out.println("ServletContextAttributeChangeListener构造函数被执行了");
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0) {
    	System.out.println("ServlectContext中的属性被增加了");
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0) {
        // TODO Auto-generated method stub
    	System.out.println("ServlectContext中的属性被替换了");
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0) {
        // TODO Auto-generated method stub
    	System.out.println("ServlectContext中的属性被移除了");
    }
	
}
