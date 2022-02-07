package com.yushun.figure.company.service;

import com.yushun.figure.vo.company.SeriesTreeVo;

import java.util.List;
import java.util.Map;

public interface SeriesService {
    void save(Map<String, Object> stringObjectMap);

    List<SeriesTreeVo> getSeriesTree(String companyCode);
}
