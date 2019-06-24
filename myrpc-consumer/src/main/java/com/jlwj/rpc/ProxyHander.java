package com.jlwj.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProxyHander implements InvocationHandler {


    private String host;

    private int port;

    public ProxyHander(String host,int port){
        this.host =host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理方法");
        Socket socket = new Socket(host,port);
        //注意ObjectInputStream的顺序要和服务端对应
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        try {
            objectOutputStream.writeObject(proxy.getClass().getInterfaces()[0]);
            objectOutputStream.writeUTF(method.getName());
            objectOutputStream.writeObject(method.getParameterTypes());
            objectOutputStream.writeObject(args);
            objectOutputStream.flush();
            Object result = objectInputStream.readObject();
            System.out.println(result);
            if(result instanceof Throwable){
                throw (Throwable) result;
            }
            return  result;
        } finally {
            socket.shutdownOutput();
        }

    }
}
