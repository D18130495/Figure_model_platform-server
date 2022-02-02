package com.yushun.figure.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yushun.figure.company.repository.CompanyRepository;
import com.yushun.figure.company.service.CompanyService;
import com.yushun.figure.model.company.BookingRule;
import com.yushun.figure.model.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void save(Map<String, Object> stringObjectMap) {
        String string = JSONObject.toJSONString(stringObjectMap);
        BookingRule bookingRule = JSONObject.parseObject(string, BookingRule.class);
        Company company = JSONObject.parseObject(string, Company.class);
        company.setBookingRule(bookingRule);

        Company companyExist = companyRepository.getCompanyByCompanyCode(company.getCompanyCode());

        if(companyExist != null) {
            company.setId(companyExist.getId());
            company.setStatus(companyExist.getStatus());
            company.setCreateTime(companyExist.getCreateTime());
        }else {
            company.setStatus(0);
            company.setCreateTime(new Date());
        }

        company.setUpdateTime(new Date());
        company.setIsDeleted(0);

        companyRepository.save(company);
    }
}
