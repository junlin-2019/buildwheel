package com.jlwj.service.impl;

import com.jlwj.service.OrderService;

/**
 * @author hehang on 2019-06-24
 * @description服务提供者
 */
public class OrderServiceImpl  implements OrderService {
    public String orderHandler(String orderNo) {
        return orderNo + "处理完毕";
    }
}
