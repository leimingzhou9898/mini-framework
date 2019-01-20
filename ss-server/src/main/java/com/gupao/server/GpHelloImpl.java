package com.gupao.server;
@RpcAnnotation(IGpHello.class)
public class GpHelloImpl implements IGpHello{
    public String sayHello(String msg) {
        return " RPC Hello， "+msg;
    }
}