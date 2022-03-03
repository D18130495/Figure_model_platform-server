package com.yushun.figure.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yushun.figure.model.order.OrderInfo;
import com.yushun.figure.vo.order.OrderQueryVo;

public interface OrderService extends IService<OrderInfo> {
    OrderInfo saveOrder(String scheduleId, Long peopleId);

    OrderInfo getOrderById(Long orderId);

    IPage<OrderInfo> selectPage(Page<OrderInfo> page, OrderQueryVo orderQueryVo);

    OrderInfo cancelOrderById(Long orderId);

    OrderInfo placeOrderById(Long orderId);
}
