package com.yushun.figure.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yushun.figure.company.repository.SeriesRepository;
import com.yushun.figure.company.service.SeriesService;
import com.yushun.figure.model.company.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Override
    public void save(Map<String, Object> stringObjectMap) {
        String string = JSONObject.toJSONString(stringObjectMap);
        Series series = JSONObject.parseObject(string, Series.class);

        Series seriesExist = seriesRepository.getSeriesByCompanyCodeAndSeriesCode(series.getCompanyCode(), series.getSeriesCode());

        if(seriesExist != null) {
            series.setId(seriesExist.getId());
            series.setUpdateTime(new Date());
        }else {
            series.setCreateTime(new Date());
            series.setUpdateTime(new Date());
            series.setIsDeleted(0);
        }

        seriesRepository.save(series);
    }
}
