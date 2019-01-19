package mytomcat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyTomcat {
	private int port=8080;
	private Map<String,String> urlServletMap=new HashMap<String, String>();
	public MyTomcat(int port) {
		super();
		this.port = port;
	}
	public void start(){
		initServletMapping();
		ServerSocket serverSocket=null;
		try {
			serverSocket=new ServerSocket(port);
			System.out.println("MyTomcat is start...");
			while(true){
				Socket socket=serverSocket.accept();
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				MyRequest myRequest=new MyRequest(inputStream);
				MyResponse myResponse=new MyResponse(outputStream);
				dispatch(myRequest,myResponse);
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(serverSocket!=null){
				try {
					serverSocket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	private void initServletMapping() {
		for(ServletMapping servletMapping:ServletMappingConfig.servletMappingList){
			urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
		}
	}
	private void dispatch(MyRequest myRequest, MyResponse myResponse) {
		String clazz=urlServletMap.get(myRequest.getUrl());
		try {
			Class<MyServlet> myServletClass = (Class<MyServlet>)Class.forName(clazz);
			MyServlet myServlet = myServletClass.newInstance();
			myServlet.service(myRequest, myResponse);//子类继承父类的非抽象方法，故能执行此方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new MyTomcat(8080).start();
	}
}
