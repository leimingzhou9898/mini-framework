package cn.itcast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class aaa {
	public static void main(String[] args) throws Exception {
		SimpleDateFormat f =new SimpleDateFormat("yyyyMMdd hhmmss");
		Date dd = f.parse("20180527 092020");
//		System.out.println(dd);
		System.out.println("执行定时任务的监听器创建了,时间为："+f.format(System.currentTimeMillis()));	}

}
