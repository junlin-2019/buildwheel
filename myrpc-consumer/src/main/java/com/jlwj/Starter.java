package com.jlwj;

import com.jlwj.rpc.RpcConsumer;
import com.jlwj.service.OrderService;

/**
 * @author hehang on 2019-06-24
 * @description启动类
 */
public class Starter {

    public static void main(String[] args) {
        OrderService orderService = RpcConsumer.getService(OrderService.class,"127.0.0.1",8088);
        String result = orderService.orderHandler("001");
        System.out.println(result);
    }
}
