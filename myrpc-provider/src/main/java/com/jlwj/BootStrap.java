package com.jlwj;

import com.jlwj.rpc.RpcProvider;
import com.jlwj.service.OrderService;
import com.jlwj.service.impl.OrderServiceImpl;

/**
 * @author hehang on 2019-06-24
 * @description启动类
 */
public class BootStrap {

    public static void main(String[] args) throws Exception{
        OrderService orderService = new OrderServiceImpl();
        RpcProvider.publish(8088,orderService);
    }
}
