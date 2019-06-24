package com.jlwj.rpc;

import com.sun.deploy.net.proxy.ProxyHandler;

import java.lang.reflect.Proxy;

/**
 * @author hehang on 2019-06-24
 * @description获取代理service
 */
public class RpcConsumer {

    public static <T> T getService(Class<T> clazz,String ip,int port) {
        ProxyHander proxyHandler =new ProxyHander(ip,port);
        return (T)Proxy.newProxyInstance(RpcConsumer.class.getClassLoader(), new Class<?>[] {clazz}, proxyHandler);
    }
}
