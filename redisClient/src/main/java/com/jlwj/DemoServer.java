package com.jlwj;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hehang on 2019-06-25
 * @description模拟服务端
 */
public class DemoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6380);
        while (true){
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.println(new String(bytes).trim());
        }
    }
}
