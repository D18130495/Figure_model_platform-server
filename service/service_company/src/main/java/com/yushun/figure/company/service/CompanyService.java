package com.yushun.figure.company.service;

import com.yushun.figure.model.company.Company;
import com.yushun.figure.vo.company.CompanyQueryVo;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CompanyService {
    void save(Map<String, Object> stringObjectMap);

    Page selectCompanyPage(Integer current, Integer limit, CompanyQueryVo companyQueryVo);

    void updateCompanyStatus(String compCode, Integer status);

    HashMap<String, Object> getCompanyById(String id);

    List<Company> findCompanyByName(String companyName);

    Map<String, Object> getCompanyByCompanyCode(String companyCode);
}
