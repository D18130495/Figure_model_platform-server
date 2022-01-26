package com.yushun.figure.company.controller;

import com.yushun.figure.company.service.CompanySetService;
import com.yushun.figure.model.company.CompanySet;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/comp/companySet")
public class CompanySetController {

    @Autowired
    private CompanySetService companySetService;

    //http://localhost:8021/admin/comp/companySet/findAll
    // get all the company information
    @GetMapping("findAll")
    public List<CompanySet> findAllCompanySet() {
        List<CompanySet> list = companySetService.list();
        return list;
    }

    // delete the company(logic)
    @DeleteMapping("{id}")
    public boolean removeCompanySet(@PathVariable Long id) {
        Boolean flag = companySetService.removeById(id);
        return flag;
    }
}
