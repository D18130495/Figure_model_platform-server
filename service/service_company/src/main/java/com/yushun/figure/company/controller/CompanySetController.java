package com.yushun.figure.company.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yushun.figure.common.result.Result;
import com.yushun.figure.common.utils.MD5;
import com.yushun.figure.company.service.CompanySetService;
import com.yushun.figure.model.company.CompanySet;
import com.yushun.figure.vo.CompanySetQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/admin/comp/companySet")
public class CompanySetController {

    @Autowired
    private CompanySetService companySetService;

    //http://localhost:8021/admin/comp/companySet/findAll
    // get all the company information
    @GetMapping("findAll")
    public Result findAllCompanySet() {
        List<CompanySet> list = companySetService.list();
        return Result.ok(list);
    }

    // get company information pagination
    @GetMapping("findPage/{current}/{limit}")
    public Result findPaginationCompanySet(@PathVariable long current,
                                           @PathVariable long limit,
                                           @RequestBody(required = false) CompanySetQueryVo companySetQueryVo) {
        Page<CompanySet> page = new Page<>(current, limit);
        QueryWrapper<CompanySet> wrapper = new QueryWrapper<>();

        String compName = companySetQueryVo.getCompName();
        String compCode = companySetQueryVo.getCompCode();

        if(!StringUtils.isEmpty(compName)) {
            wrapper.like("comp_name", compName);
        }

        if(!StringUtils.isEmpty(compCode)) {
            wrapper.eq("comp_code", compCode);
        }

        Page<CompanySet> pageCompanySet = companySetService.page(page, wrapper);
        return Result.ok(pageCompanySet);
    }

    // delete the company(logic)
    @DeleteMapping("{id}")
    public Result removeCompanySet(@PathVariable long id) {
        Boolean flag = companySetService.removeById(id);

        if(flag) {
            return  Result.ok();
        }else {
            return Result.fail();
        }
    }

    // add company set
    @PostMapping("saveCompanySet")
    public Result addCompanySet(@RequestBody CompanySet companySet) {
        // status 1 can be use, status can not be use
        companySet.setStatus(1);
        // sign key
        Random random = new Random();
        companySet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));

        Boolean flag = companySetService.save(companySet);
        if(flag) {
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}
