package com.gupao;
public class GpHelloImpl implements IGpHello{
    public String sayHello(String msg) {
    	String s=" RPC Hello， "+msg;
    	System.out.println("服务端被调用，返回:"+s);
        return s;
    }
}