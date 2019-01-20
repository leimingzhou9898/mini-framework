package com.gupao;


public class ClientController {
	public static void main(String[] args) {
        RpcClientProxy rpcClientProxy=new RpcClientProxy();
 
        IGpHello hello=rpcClientProxy.clientProxy(IGpHello.class,"localhost",8080);
        System.out.println(hello.sayHello("wuzz"));
    }
}
