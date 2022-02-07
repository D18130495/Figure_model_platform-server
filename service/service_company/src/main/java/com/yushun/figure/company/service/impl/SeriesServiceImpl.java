package com.yushun.figure.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yushun.figure.company.repository.SeriesRepository;
import com.yushun.figure.company.service.SeriesService;
import com.yushun.figure.model.company.Series;
import com.yushun.figure.vo.company.SeriesTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // form the tree of the series and the figure
    @Override
    public List<SeriesTreeVo> getSeriesTree(String companyCode) {
        List<SeriesTreeVo> result = new ArrayList<>();

        Series series = new Series();
        series.setCompanyCode(companyCode);

        Example<Series> example = Example.of(series);

        List<Series> seriesList = seriesRepository.findAll(example);

        // key: grouped bigCode
        Map<String, List<Series>> seriesMap = seriesList.stream().collect(Collectors.groupingBy(Series::getBigCode));

        for(Map.Entry<String, List<Series>> entry : seriesMap.entrySet()) {
            String bigCode = entry.getKey();
            List<Series> seriesListWithoutBigCode = entry.getValue();

            SeriesTreeVo seriesTreeVo = new SeriesTreeVo();
            seriesTreeVo.setSeriesCode(bigCode);
            seriesTreeVo.setSeriesName(seriesListWithoutBigCode.get(0).getBigName());

            List<SeriesTreeVo> children = new ArrayList<>();
            for(Series value : seriesListWithoutBigCode) {
                SeriesTreeVo seriesTreeVo1 = new SeriesTreeVo();
                seriesTreeVo1.setSeriesCode(value.getSeriesCode());
                seriesTreeVo1.setSeriesName(value.getSeriesName());
                children.add(seriesTreeVo1);
            }

            seriesTreeVo.setChildren(children);

            result.add(seriesTreeVo);
        }

        return result;
    }
}
