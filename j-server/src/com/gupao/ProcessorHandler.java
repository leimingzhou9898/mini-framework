package com.gupao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class ProcessorHandler implements Runnable{
 
	private Socket socket;
    private Object service; //服务端发布的服务
 
    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }
 
    @Override
    public void run() {
        //处理请求
        ObjectInputStream inputStream=null;
        try {
        	System.out.println("服务端收到消息");
            //获取客户端的输入流
            inputStream=new ObjectInputStream(socket.getInputStream());
            //反序列化远程传输的对象RpcRequest
            RpcRequest request=(RpcRequest) inputStream.readObject();
            Object result=invoke(request); //通过反射去调用本地的方法
 
            //通过输出流讲结果输出给客户端
            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            System.out.println("服务端返回消息");
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Object invoke(RpcRequest request) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    	System.out.println("服务端处理请求");
        //以下均为反射操作，目的是通过反射调用服务
        Object[] args=request.getParameters();
        Class<?>[] types=new Class[args.length];
        for(int i=0;i<args.length;i++){
            types[i]=args[i].getClass();
        }
        Method method=service.getClass().getMethod(request.getMethodName(),types);
        return method.invoke(service,args);
    }
}