package cn.itcast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
//		test01();
		test02();
//		test03();
//		test04();
	}

	private static void test04() {
		Timer timer=new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println(new Date().toLocaleString());
			}
			
		}, 2000);
		
	}

	private static void test03() {
		Date date=new Date(114,23,33,250,88);
		Timer timer=new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(new Date().toLocaleString());
			}
			
		}, date, 2000);
		
	}

	private static void test02() {
		Date date=new Date(System.currentTimeMillis()+6000);
		Timer timer=new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				System.out.println("我被定时任务执行");
			}
			
		}, date, 1000);
	}

	private static void test01() {
		System.out.println("开始："+new Date().toLocaleString());
		Timer timer=new Timer();
		//三个参数：1.定时任务，2.开始时间，3.循环时间
		timer.schedule(new TimerTask(){
			public void run(){
				System.out.println(new Date().toLocaleString());
			}
		}, 5000, 3000);
		
		
	}

}
