package com.yushun.figure.company.controller.api;

import com.yushun.figure.common.helper.HttpRequestHelper;
import com.yushun.figure.common.result.Result;
import com.yushun.figure.company.service.CompanyService;
import com.yushun.figure.company.service.FigureScheduleService;
import com.yushun.figure.company.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/company")
public class ApiController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private FigureScheduleService figureScheduleService;

    // upload company api
    @PostMapping("saveCompany")
    public Result saveCompany(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, Object> stringObjectMap = HttpRequestHelper.switchMap(parameterMap);

        // TODO sign key 76

        companyService.save(stringObjectMap);
        return Result.ok();
    }

    // upload series api
    @PostMapping("saveSeries")
    public Result saveSeries(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, Object> stringObjectMap = HttpRequestHelper.switchMap(parameterMap);

        seriesService.save(stringObjectMap);
        return Result.ok();
    }

    // upload figure schedule api
    @PostMapping("saveFigureSchedule")
    public Result saveFigureSchedule(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, Object> stringObjectMap = HttpRequestHelper.switchMap(parameterMap);

        Date date = new Date();
        String dateStr = httpServletRequest.getParameter("orderDate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        figureScheduleService.save(stringObjectMap, date);
        return Result.ok();
    }

    // TODO 77 79
}
