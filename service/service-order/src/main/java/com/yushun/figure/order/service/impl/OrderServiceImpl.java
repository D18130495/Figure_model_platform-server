package com.yushun.figure.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.common.result.OrderStatusEnum;
import com.yushun.figure.company.feign.CompanyFeignClient;
import com.yushun.figure.model.order.OrderInfo;
import com.yushun.figure.order.mapper.OrderMapper;
import com.yushun.figure.order.service.OrderService;
import com.yushun.figure.user.feign.PeopleFeignClient;
import com.yushun.figure.vo.company.ScheduleOrderVo;
import com.yushun.figure.vo.user.People;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Autowired
    private PeopleFeignClient peopleFeignClient;

    @Override
    public OrderInfo saveOrder(String scheduleId, Long peopleId) {
        People peopleOrder = peopleFeignClient.getPeopleOrder(peopleId);
        ScheduleOrderVo scheduleOrderVo = companyFeignClient.getScheduleOrderVo(scheduleId);

        if(new DateTime(scheduleOrderVo.getStartTime()).isAfterNow() || new DateTime(scheduleOrderVo.getStopTime()).isBeforeNow())
            throw new NullPointerException();

        OrderInfo orderInfo = new OrderInfo();

        String outTradeNo = System.currentTimeMillis() + "" + new Random().nextInt(100);
        orderInfo.setOutTradeNo(outTradeNo);
        orderInfo.setScheduleId(scheduleId);
        orderInfo.setUserId(peopleOrder.getUserId());
        orderInfo.setPeopleId(peopleOrder.getId());
        orderInfo.setPeopleAddress(peopleOrder.getAddress());
        orderInfo.setPeoplePhone(peopleOrder.getPhone());
        orderInfo.setPeopleName(peopleOrder.getName());
        orderInfo.setOrderStatus(0);
        orderInfo.setIsDeleted(0);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());

        orderInfo.setCompanyName(scheduleOrderVo.getCompanyName());
        orderInfo.setCompanyCode(scheduleOrderVo.getCompanyCode());
        orderInfo.setSeriesCode(scheduleOrderVo.getSeriesCode());
        orderInfo.setSeriesName(scheduleOrderVo.getSeriesName());

        baseMapper.insert(orderInfo);
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("out_trade_no", outTradeNo);
        OrderInfo orderInfo1 = baseMapper.selectOne(wrapper);

        return orderInfo1;
    }

    @Override
    public OrderInfo getOrderById(Long orderId) {
        OrderInfo orderInfo = baseMapper.selectById(orderId);
        orderInfo.getParam().put("orderStatus", OrderStatusEnum.getStatusNameByStatus(orderInfo.getOrderStatus()));
        return orderInfo;
    }
}
