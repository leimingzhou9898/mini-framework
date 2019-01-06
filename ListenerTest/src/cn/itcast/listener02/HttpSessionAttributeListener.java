package cn.itcast.listener02;

import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class HttpSessionAttributeListener
 *
 */
public class HttpSessionAttributeListener implements javax.servlet.http.HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public HttpSessionAttributeListener() {
    	System.out.println("HttpSessionAttributeListener构造函数被执行了");
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
    	System.out.println("session中的属性被移除了");
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0) {
    	System.out.println("session中的属性被增加了");
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
    	System.out.println("session中的属性被替换了");
    }
	
}
