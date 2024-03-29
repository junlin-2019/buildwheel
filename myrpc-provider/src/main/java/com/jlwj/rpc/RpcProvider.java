package com.jlwj.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * @author hehang on 2019-06-23
 * @description rpc服务提供方
 */
public class RpcProvider {

    //存储注册的服务列表
    private static List<Object> serviceList;

    public static void publish(int port,Object... services) throws Exception {
        serviceList=Arrays.asList(services);
        ServerSocket server = new ServerSocket(port);
        Socket client = null;
        while (true) {
            //阻塞等待输入
            client = server.accept();
            //每一个请求，启动一个线程处理
            new Thread(new RequestHandler(client,serviceList)).start();
        }
    }
}
