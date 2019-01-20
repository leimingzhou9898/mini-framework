package com.gupao.server;


public class ClusterServerService_1 {
    public static void main(String[] args) {
        IGpHello iGpHello=new GpHelloImpl();
        IRegisterCenter registerCenter=new RegisterCenterImpl();
  
        RpcServer rpcServer=new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(iGpHello);
        rpcServer.publisher();
        System.out.println("服务发布成功");
    }
}
