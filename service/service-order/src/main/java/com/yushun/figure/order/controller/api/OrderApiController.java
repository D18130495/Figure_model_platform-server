package com.yushun.figure.order.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yushun.figure.common.result.Result;
import com.yushun.figure.common.utils.AuthContextHolder;
import com.yushun.figure.model.order.OrderInfo;
import com.yushun.figure.model.user.UserInfo;
import com.yushun.figure.order.service.OrderService;
import com.yushun.figure.vo.order.OrderQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/auth/{current}/{limit}")
    public Result getOrderList(@PathVariable Long current,
                               @PathVariable Long limit,
                               OrderQueryVo orderQueryVo,
                               HttpServletRequest httpServletRequest) {
        orderQueryVo.setUserId(AuthContextHolder.getUserId(httpServletRequest));

        Page<OrderInfo> page = new Page<>(current, limit);
        IPage<OrderInfo> pageModel = orderService.selectPage(page, orderQueryVo);
        return Result.ok(pageModel);
    }
}