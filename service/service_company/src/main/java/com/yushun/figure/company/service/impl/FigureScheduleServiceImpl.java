package com.yushun.figure.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yushun.figure.company.repository.FigureScheduleRepository;
import com.yushun.figure.company.service.FigureScheduleService;
import com.yushun.figure.model.company.FigureSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class FigureScheduleServiceImpl implements FigureScheduleService {

    @Autowired
    private FigureScheduleRepository figureScheduleRepository;

    @Override
    public void save(Map<String, Object> stringObjectMap, Date date) {
        stringObjectMap.remove("orderDate");
        String string = JSONObject.toJSONString(stringObjectMap);
        FigureSchedule figureSchedule = JSONObject.parseObject(string, FigureSchedule.class);

        FigureSchedule figureScheduleExist = figureScheduleRepository.getFigureScheduleByCompanyCodeAndFigureCode(figureSchedule.getCompanyCode(), figureSchedule.getFigureCode());

        if(figureScheduleExist != null) {
            figureSchedule.setId(figureScheduleExist.getId());
            figureSchedule.setOrderDate(date);
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
}
