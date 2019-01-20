package com.gupao.client;

import com.gupao.server.IGpHello;
import com.gupao.server.ZkConfig;

public class ClientController {
	public static void main(String[] args) throws InterruptedException {
        IServiceDiscovery serviceDiscovery=new
                ServiceDiscoveryImpl(ZkConfig.CONNNECTION_STR);
 
        for (int i = 0; i < 10; i++) {
        	RpcClientProxy rpcClientProxy=new RpcClientProxy(serviceDiscovery);
        	IGpHello hello = rpcClientProxy.clientProxy(IGpHello.class);
        	System.out.println(hello.sayHello("wuzz"));
        }

    }
}
