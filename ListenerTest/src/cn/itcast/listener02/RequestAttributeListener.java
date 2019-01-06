package cn.itcast.listener02;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * Application Lifecycle Listener implementation class RequestAttributeListener
 *
 */
public class RequestAttributeListener implements ServletRequestAttributeListener {

    /**
     * Default constructor. 
     */
    public RequestAttributeListener() {
    	System.out.println("RequestAttributeListener构造函数被执行了");
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent arg0) {
    	System.out.println("request中的属性被增加了");
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent arg0) {
    	System.out.println("request中的属性被移除了");
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent arg0) {
    	System.out.println("request中的属性被替换了");
    }
	
}
