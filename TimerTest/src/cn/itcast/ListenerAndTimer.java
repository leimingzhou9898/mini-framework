package cn.itcast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ListenerAndTimer
 *
 */
public class ListenerAndTimer implements ServletContextListener {
	SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * Default constructor. 
     */
    public ListenerAndTimer() {
    	System.out.println("执行定时任务的监听器创建了,时间为："+f.format(System.currentTimeMillis()));
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	
		Date date = null;
		try {
			date = f.parse("2018-05-27 09:20:20");
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		System.out.println("设置定时器第一次执行的时间为：2018-05-27 09:20:20");
		System.out.println("设置定时器第一次执行的时间为：当前时间+2秒"+f.format(System.currentTimeMillis()));
		//定时器核心类 Timer
    	Timer timer=new Timer();
    	timer.schedule(new TimerTask(){
			public void run() {
				System.out.println("定时任务执行的当前时间为："+f.format(System.currentTimeMillis()));
			}
    		
//    	}, date, 6000);
    	}, 2000, 6000);
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("定时器停止执行了");
    }
	
}
