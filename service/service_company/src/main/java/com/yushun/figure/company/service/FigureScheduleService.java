package com.yushun.figure.company.service;

import java.util.Date;
import java.util.Map;

public interface FigureScheduleService {
    void save(Map<String, Object> stringObjectMap, Date date);

    Map<String, Object> getFigureSchedule(long current, long limit, String companyCode, String seriesCode);
}
