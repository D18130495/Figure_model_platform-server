package com.yushun.figure.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yushun.figure.model.order.OrderInfo;

public interface OrderService extends IService<OrderInfo> {
    OrderInfo saveOrder(String scheduleId, Long peopleId);

    OrderInfo getOrderById(Long orderId);
}
