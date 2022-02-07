package com.yushun.figure.company.controller;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.company.service.CompanyService;
import com.yushun.figure.model.company.Company;
import com.yushun.figure.vo.company.CompanyQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin/comp/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("list/{current}/{limit}")
    public Result findPaginationCompany(@PathVariable Integer current,
                                        @PathVariable Integer limit,
                                        CompanyQueryVo companyQueryVo) {
        Page page = companyService.selectCompanyPage(current, limit, companyQueryVo);
        return Result.ok(page);
    }

    @GetMapping("showCompanyDetail/{id}")
    public Result showCompanyDetail(@PathVariable String id) {
        HashMap<String, Object> map = companyService.getCompanyById(id);
        return Result.ok(map);
    }
}
