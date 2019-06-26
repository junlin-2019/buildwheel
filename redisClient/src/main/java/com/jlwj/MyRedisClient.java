package com.jlwj;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author hehang on 2019-06-25
 * @description自定义的redisClient
 */
public class MyRedisClient {
    private Socket socket;

    private InputStream inputStream;

    private OutputStream outputStream;

    public MyRedisClient(String host, int port) throws IOException {
        this.socket = new Socket(host,port);
        this.inputStream = this.socket.getInputStream();
        this.outputStream = this.socket.getOutputStream();
    }


    public String set(String key,String value) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("*3").append("\r\n");
        sb.append("$3").append("\r\n");
        sb.append("SET").append("\r\n");
        sb.append("$").append(key.length()).append("\r\n");
        sb.append(key).append("\r\n");
        sb.append("$").append(value.length()).append("\r\n");
        sb.append(value).append("\r\n");
        byte[] bytes= new byte[1024];
        outputStream.write(sb.toString().getBytes());
        inputStream.read(bytes);
        return new String(bytes).trim();
    }

    public static void main(String[] args) throws IOException {

        MyRedisClient redisClient = new MyRedisClient("127.0.0.1",6379);
        String result = redisClient.set("qwe","adsf");
        System.out.println(result);
    }
}
