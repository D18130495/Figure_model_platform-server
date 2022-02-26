package com.yushun.figure.company.service;

import com.yushun.figure.model.company.FigureSchedule;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FigureScheduleService {

    void save(Map<String, Object> stringObjectMap, Date date);

    Map<String, Object> getFigureSchedule(long current, long limit, String companyCode, String seriesCode);

    List<FigureSchedule> getFigureDetails(String companyCode, String seriesCode, String orderDate);

    Map<String, Object> getSchedule(Integer current, Integer limit, String companyCode, String seriesCode);

    FigureSchedule getScheduleByFigureScheduleId(String scheduleId);
}
