package com.yushun.figure.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.common.result.OrderStatusEnum;
import com.yushun.figure.company.feign.CompanyFeignClient;
import com.yushun.figure.model.order.OrderInfo;
import com.yushun.figure.order.mapper.OrderMapper;
import com.yushun.figure.order.service.OrderService;
import com.yushun.figure.user.feign.PeopleFeignClient;
import com.yushun.figure.vo.company.ScheduleOrderVo;
import com.yushun.figure.vo.order.OrderQueryVo;
import com.yushun.figure.vo.user.People;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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

//        if(new DateTime(scheduleOrderVo.getStartTime()).isAfterNow() || new DateTime(scheduleOrderVo.getStopTime()).isBeforeNow())
//            throw new NullPointerException();

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


        Calendar rightNow = Calendar.getInstance();
        int moa = rightNow.get(Calendar.AM_PM);
        orderInfo.setReserveTime(moa);
        orderInfo.setReserveDate(rightNow.getTime());
        rightNow.add(Calendar.DATE, 14);
        orderInfo.setFetchTime(rightNow.getTime());

        orderInfo.setAmount(scheduleOrderVo.getAmount());
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
        orderInfo.getParam().put("orderStatusString", "Please check the purchase information and please order. ");
        return orderInfo;
    }

    @Override
    public IPage<OrderInfo> selectPage(Page<OrderInfo> page, OrderQueryVo orderQueryVo) {
        String companyName = orderQueryVo.getCompanyName();
        String peopleName = orderQueryVo.getPeopleName();
        String orderStatus = orderQueryVo.getOrderStatus();
        String reserveDate = orderQueryVo.getReserveDate();
        String createTimeBegin = orderQueryVo.getCreateTimeBegin();
        String creatTimeEnd = orderQueryVo.getCreateTimeEnd();

        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(companyName)) {
            wrapper.like("company_name", companyName);
        }

        if(!StringUtils.isEmpty(peopleName)) {
            wrapper.like("people_name", peopleName);
        }

        if(!StringUtils.isEmpty(orderStatus)) {
            wrapper.like("order_status", orderStatus);
        }

        if(!StringUtils.isEmpty(reserveDate)) {
            wrapper.like("reserve_date", reserveDate);
        }

        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time", createTimeBegin);
        }

        if(!StringUtils.isEmpty(creatTimeEnd)) {
            wrapper.le("create_time", creatTimeEnd);
        }

        Page<OrderInfo> orderInfoPage = baseMapper.selectPage(page, wrapper);


        return orderInfoPage;
    }
}
