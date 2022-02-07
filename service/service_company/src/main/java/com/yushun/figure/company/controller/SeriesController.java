package com.yushun.figure.company.controller;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.company.service.SeriesService;
import com.yushun.figure.vo.company.SeriesTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/comp/series")
@CrossOrigin
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    // get the series list
    @GetMapping("/getSeries/{companyCode}")
    public Result getSeriesByCompanyCode(@PathVariable String companyCode) {
        List<SeriesTreeVo> result = seriesService.getSeriesTree(companyCode);
        return Result.ok(result);
    }
}
