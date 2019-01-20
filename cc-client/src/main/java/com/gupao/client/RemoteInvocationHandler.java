package com.gupao.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.gupao.server.RpcRequest;

public class RemoteInvocationHandler implements InvocationHandler {
    private IServiceDiscovery serviceDiscovery;
 
 
    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery=serviceDiscovery;
    }
 
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest request=new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
 
        String serviceAddress=serviceDiscovery.discover(request.getClassName()); //根据接口名称得到对应的服务地址
        //通过tcp传输协议进行传输
        TCPTransport tcpTransport=new TCPTransport(serviceAddress);
        //发送请求
        return tcpTransport.send(request);
    }
}
