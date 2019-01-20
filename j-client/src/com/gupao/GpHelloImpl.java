package com.gupao;
public class GpHelloImpl implements IGpHello{
    public String sayHello(String msg) {
        return " RPC Hello， "+msg;
    }
}