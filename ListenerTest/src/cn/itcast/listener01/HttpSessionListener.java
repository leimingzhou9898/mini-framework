package cn.itcast.listener01;

import javax.servlet.http.HttpSessionEvent;

/**
 * Application Lifecycle Listener implementation class HttpSessionListener
 *
 */
public class HttpSessionListener implements javax.servlet.http.HttpSessionListener {

    /**
     * Default constructor. 
     */
    public HttpSessionListener() {
    	System.out.println("HttpSessionListener构造函数被执行了");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
    	System.out.println("session对象被创建了,sessionid:"+arg0.getSession().getId());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event) {
    	//将失效会话从SH中删除
    	String sessionId = event.getSession().getId();
//    	SessionHolder.instance().removeSession(sessionId);高伟达
    	System.out.println("session对象被销毁了");
    }
	
}
