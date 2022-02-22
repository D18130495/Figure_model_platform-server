package com.yushun.figure.company.controller;

import com.yushun.figure.common.result.Result;
import com.yushun.figure.company.service.CompanyService;
import com.yushun.figure.model.company.Company;
import com.yushun.figure.vo.company.CompanyQueryVo;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/comp/company")
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

    @GetMapping("showCompanyDetailByCompanyCode/{companyCode}")
    public Result showCompanyDetailByCompanyCode(@PathVariable String companyCode) {
        Map<String, Object> map = companyService.getCompanyByCompanyCode(companyCode);
        return Result.ok(map);
    }

    @GetMapping("findCompanyByName/{companyName}")
    public Result findCompanyByName(@PathVariable String companyName) {
        List<Company> list = companyService.findCompanyByName(companyName);
        return Result.ok(list);
    }
}
