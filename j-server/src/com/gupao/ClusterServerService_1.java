package com.gupao;


public class ClusterServerService_1 {
	public static void main(String[] args) {
        IGpHello iGpHello=new GpHelloImpl();
        RpcServer rpcServer=new RpcServer();
        rpcServer.publisher(iGpHello,8080);
    }
}
