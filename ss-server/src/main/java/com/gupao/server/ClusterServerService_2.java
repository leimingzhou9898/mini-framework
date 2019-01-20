package com.gupao.server;

public class ClusterServerService_2 {
    public static void main(String[] args) {
        IGpHello iGpHello=new GpHelloImpl();
        IRegisterCenter registerCenter=new RegisterCenterImpl();
  
        RpcServer rpcServer=new RpcServer(registerCenter,"127.0.0.1:8082");
        rpcServer.bind(iGpHello);
        rpcServer.publisher();
        System.out.println("服务发布成功");
    }
}
