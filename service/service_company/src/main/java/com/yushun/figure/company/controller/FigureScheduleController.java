package com.yushun.figure.company.controller;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.company.service.FigureScheduleService;
import com.yushun.figure.model.company.FigureSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/comp/schedule")
public class FigureScheduleController {

    @Autowired
    private FigureScheduleService figureScheduleService;

    @GetMapping("/getFigureSchedule/{current}/{limit}/{companyCode}/{seriesCode}")
    public Result getFigureSchedule(@PathVariable long current,
                                    @PathVariable long limit,
                                    @PathVariable String companyCode,
                                    @PathVariable String seriesCode) {
        Map<String, Object> result = figureScheduleService.getFigureSchedule(current, limit, companyCode, seriesCode);
        return Result.ok(result);
    }

    // select the figure by companyCode, seriesCode, date
    @GetMapping("/getFigureDetails/{companyCode}/{seriesCode}/{orderDate}")
    public Result getFigureDetails(@PathVariable String companyCode,
                                   @PathVariable String seriesCode,
                                   @PathVariable String orderDate) {
        List<FigureSchedule> resultList = figureScheduleService.getFigureDetails(companyCode, seriesCode, orderDate);
        return Result.ok(resultList);
    }
}
