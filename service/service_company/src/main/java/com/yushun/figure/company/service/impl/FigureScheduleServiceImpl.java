package com.yushun.figure.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yushun.figure.common.date.DayOfWeek;
import com.yushun.figure.company.repository.FigureScheduleRepository;
import com.yushun.figure.company.service.CompanyService;
import com.yushun.figure.company.service.FigureScheduleService;
import com.yushun.figure.model.company.BookingRule;
import com.yushun.figure.model.company.BookingScheduleRuleVo;
import com.yushun.figure.model.company.Company;
import com.yushun.figure.model.company.FigureSchedule;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FigureScheduleServiceImpl implements FigureScheduleService {

    @Autowired
    private FigureScheduleRepository figureScheduleRepository;

    @Autowired
    private CompanyService companyService;

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

    @Override
    public List<FigureSchedule> getFigureDetails(String companyCode, String seriesCode, String orderDate) {
        List<FigureSchedule> result = figureScheduleRepository.getFigureScheduleByCompanyCodeAndSeriesCodeAndOrderDate(companyCode, seriesCode, new DateTime(orderDate).toDate());
        return result;
    }

    @Override
    public Map<String, Object> getSchedule(Integer current, Integer limit, String companyCode, String seriesCode) {
        Map<String, Object> result = new HashMap<String, Object>();

        Company company = companyService.getCompanyByCode(companyCode);

        if(company == null) throw new NullPointerException();

        BookingRule bookingRule = company.getBookingRule();

        IPage page = this.getListDate(current, limit, bookingRule);

        List<Date> dateList = page.getRecords();

        Criteria criteria = Criteria.where("companyCode").is(companyCode).and("seriesCode").is(seriesCode).and("orderDate").in(dateList);

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("orderDate")
                        .first("orderDate").as("orderDate")
                        .count().as("figureCount")
                        .sum("reservedNumber").as("reservedNumber")
                        .sum("availableNumber").as("availableNumber")
        );

        AggregationResults<BookingScheduleRuleVo> aggregate = mongoTemplate.aggregate(agg, FigureSchedule.class, BookingScheduleRuleVo.class);

        List<BookingScheduleRuleVo> mappedResults = aggregate.getMappedResults();

        Map<Date, BookingScheduleRuleVo> scheduleMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(mappedResults)) {
            scheduleMap = mappedResults.stream().collect(Collectors.toMap(BookingScheduleRuleVo::getOrderDate,
                    BookingScheduleRuleVo -> BookingScheduleRuleVo));
        }

        List<BookingScheduleRuleVo> bookingScheduleRuleVos = new ArrayList<>();
        for(int i = 0; i < dateList.size(); i++) {
            Date date = dateList.get(i);
            BookingScheduleRuleVo bookingScheduleRuleVo = scheduleMap.get(date);
            if(bookingScheduleRuleVo == null) {
                bookingScheduleRuleVo = new BookingScheduleRuleVo();
                bookingScheduleRuleVo.setFigureCount(0);
                bookingScheduleRuleVo.setAvailableNumber(-1);
                bookingScheduleRuleVo.setStatus(1);
            }else {
                bookingScheduleRuleVo.setStatus(0);
            }
            bookingScheduleRuleVo.setOrderDate(date);
            bookingScheduleRuleVo.setWorkDateMd(date);
            String dayOfWeek = new DayOfWeek().getWeek(new DateTime(date));
            bookingScheduleRuleVo.setDayOfWeek(dayOfWeek);

            bookingScheduleRuleVos.add(bookingScheduleRuleVo);
        }

        result.put("bookingScheduleList", bookingScheduleRuleVos);
        result.put("total", page.getTotal());

        Map<String, String> baseMap = new HashMap<>();
        baseMap.put("CompanyName", company.getCompanyName());

        // TODO
        return result;
    }

    @Override
    public FigureSchedule getScheduleByFigureScheduleId(String scheduleId) {
        FigureSchedule figureSchedule = figureScheduleRepository.findById(scheduleId).get();
        return figureSchedule;
    }

    private IPage getListDate(Integer current, Integer limit, BookingRule bookingRule) {
        DateTime dateTime = this.getDateTime(new Date(), bookingRule.getReleaseTime());
        Integer cycle = bookingRule.getCycle();
        if(dateTime.isBeforeNow()) cycle++;

        List<Date> dateList = new ArrayList<>();
        DateTime dateTime1 = DateTime.parse("2022-3-13", DateTimeFormat.forPattern("yyyy-MM-dd"));
        for(int i = 0; i < cycle; i++) {
            DateTime cur = dateTime1.plusDays(i); // delete this line add 188
//            DateTime cur = new DateTime().plusDays(i);
            String time = cur.toString("yyyy-MM-dd");
            dateList.add(new DateTime(time).toDate());
        }

        List<Date> pageDateList = new ArrayList<>();
        int start = (current - 1) * limit;
        int end = (current - 1) * limit + limit;
        if(dateList.size() < end) {
            end = dateList.size();
        }

        for(int j = start; j < end; j++) {
            pageDateList.add(dateList.get(j));
        }

        IPage<Date> page = new Page<>(current, limit, dateList.size());
        page.setRecords(pageDateList);
        return page;
    }

    private DateTime getDateTime(Date date, String timeString) {
        String dateTimeString = new DateTime(date).toString("yyyy-MM-dd") + " " + timeString;
        DateTime dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").parseDateTime(dateTimeString);
        return dateTime;
    }
}
