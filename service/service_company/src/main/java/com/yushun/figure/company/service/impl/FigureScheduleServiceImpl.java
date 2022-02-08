package com.yushun.figure.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yushun.figure.common.date.DayOfWeek;
import com.yushun.figure.company.repository.FigureScheduleRepository;
import com.yushun.figure.company.service.FigureScheduleService;
import com.yushun.figure.model.company.BookingScheduleRuleVo;
import com.yushun.figure.model.company.FigureSchedule;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FigureScheduleServiceImpl implements FigureScheduleService {

    @Autowired
    private FigureScheduleRepository figureScheduleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Map<String, Object> stringObjectMap, Date date) {
        stringObjectMap.remove("orderDate");
        String string = JSONObject.toJSONString(stringObjectMap);
        FigureSchedule figureSchedule = JSONObject.parseObject(string, FigureSchedule.class);

        FigureSchedule figureScheduleExist = figureScheduleRepository.getFigureScheduleByCompanyCodeAndFigureCode(figureSchedule.getCompanyCode(), figureSchedule.getFigureCode());

        if(figureScheduleExist != null) {
            figureSchedule.setId(figureScheduleExist.getId());
            figureSchedule.setOrderDate(date);
            figureSchedule.setCreateTime(figureScheduleExist.getCreateTime());
            figureSchedule.setUpdateTime(new Date());
            figureSchedule.setIsDeleted(0);
            figureSchedule.setStatus(1);
        }else {
            figureSchedule.setOrderDate(date);
            figureSchedule.setCreateTime(new Date());
            figureSchedule.setUpdateTime(new Date());
            figureSchedule.setIsDeleted(0);
        }

        figureScheduleRepository.save(figureSchedule);
    }

    @Override
    public Map<String, Object> getFigureSchedule(long current, long limit, String companyCode, String seriesCode) {
        // select by the companyCode and seriesCode
        Criteria criteria = Criteria.where("companyCode").is(companyCode).and("seriesCode").is(seriesCode);

        // group by the orderDate
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria), // get the list
                Aggregation.group("orderDate") //
                .first("orderDate").as("orderDate")
                // account the data
                        .count().as("figureCount")
                .sum("reservedNumber").as("reservedNumber")
                .sum("availableNumber").as("availableNumber"),
                // sort
                Aggregation.sort(Sort.Direction.ASC, "orderDate"),
                // pagination
                Aggregation.skip(current * limit),
                Aggregation.limit(limit)
        );

        AggregationResults<BookingScheduleRuleVo> aggResult = mongoTemplate.aggregate(agg, FigureSchedule.class, BookingScheduleRuleVo.class);
        List<BookingScheduleRuleVo> bookingScheduleRuleVoList = aggResult.getMappedResults();

        // total count of the result
        Aggregation totalAgg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("orderDate")
        );

        AggregationResults<BookingScheduleRuleVo> totalAggResult = mongoTemplate.aggregate(totalAgg, FigureSchedule.class, BookingScheduleRuleVo.class);
        int total = totalAggResult.getMappedResults().size();

        for(BookingScheduleRuleVo bookingScheduleRuleVo : bookingScheduleRuleVoList) {
            Date orderData = bookingScheduleRuleVo.getOrderDate();
            String dayOfWeek = new DayOfWeek().getWeek(new DateTime(orderData));
            bookingScheduleRuleVo.setDayOfWeek(dayOfWeek);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("bookingScheduleRule", bookingScheduleRuleVoList);
        result.put("total", total);

        return result;
    }
}
