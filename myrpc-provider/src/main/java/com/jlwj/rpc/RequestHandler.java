package com.jlwj.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

/**
 * @author hehang on 2019-06-23
 * @description请求处理线程
 */
public class RequestHandler  implements Runnable {

    private Socket client = null;

    private List<Object> serviceList = null;

    public RequestHandler(Socket client, List<Object> service) {
        this.client = client;
        this.serviceList = service;
    }


    private Object findService(Class serviceClass) {
        for (Object obj : serviceList) {
            boolean isFather = serviceClass.isAssignableFrom(obj.getClass());
            if (isFather) {
                return obj;
            }
        }
        return null;
    }


    public void run() {
        System.out.println("进入方法");
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
            // 读取客户端要访问那个service
            Class serviceClass = (Class) input.readObject();
            // 找到该服务类
            Object obj = findService(serviceClass);
            if (obj == null) {
                output.writeObject(serviceClass.getName() + "服务未发现");
            } else {
                //利用反射调用该方法，返回结果
                try {
                    String methodName = input.readUTF();
                    Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                    Object[] arguments = (Object[]) input.readObject();
                    Method method = obj.getClass().getMethod(methodName, parameterTypes);
                    Object result = method.invoke(obj, arguments);
                    output.writeObject(result);
                } catch (Throwable t) {
                    output.writeObject(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
