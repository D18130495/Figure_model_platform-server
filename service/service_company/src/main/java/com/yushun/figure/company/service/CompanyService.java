package com.yushun.figure.company.service;

import com.yushun.figure.vo.company.CompanyQueryVo;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public interface CompanyService {
    void save(Map<String, Object> stringObjectMap);

    Page selectCompanyPage(Integer current, Integer limit, CompanyQueryVo companyQueryVo);

    void updateCompanyStatus(String compCode, Integer status);

    HashMap<String, Object> getCompanyById(String id);
}
