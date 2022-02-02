package com.yushun.figure.company.controller.api;

import com.yushun.figure.common.helper.HttpRequestHelper;
import com.yushun.figure.common.result.Result;
import com.yushun.figure.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/company")
public class ApiController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("saveCompany")
    public Result saveCompany(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Map<String, Object> stringObjectMap = HttpRequestHelper.switchMap(parameterMap);

        companyService.save(stringObjectMap);
        return Result.ok();
    }
}
