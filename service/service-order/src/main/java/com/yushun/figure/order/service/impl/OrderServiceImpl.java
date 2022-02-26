package com.yushun.figure.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.model.order.OrderInfo;
import com.yushun.figure.order.mapper.OrderMapper;
import com.yushun.figure.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {
}
