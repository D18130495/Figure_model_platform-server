package com.yushun.figure.order.controller.api;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.model.order.OrderInfo;
import com.yushun.figure.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/orderInfo")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    // submit order
    @PostMapping("/auth/submitOrder/{scheduleId}/{peopleId}")
    public Result submitOrder(@PathVariable String scheduleId,
                              @PathVariable Long peopleId) {
        OrderInfo orderInfo = orderService.saveOrder(scheduleId, peopleId);
        return Result.ok(orderInfo);
    }

    // get order by orderId
    @GetMapping("/auth/getOrderById/{orderId}")
    public Result getOrdersById(@PathVariable Long orderId) {
        OrderInfo order = orderService.getOrderById(orderId);
        return Result.ok(order);
    }

    @DeleteMapping("/auth/deleteOrderById/{orderId}")
    public Result deleteOrderById(@PathVariable Long orderId) {
        orderService.removeById(orderId);
        return Result.ok();
    }
}