package com.yushun.figure.company.controller;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.company.service.FigureScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/company/Schedule")
@CrossOrigin
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
}
