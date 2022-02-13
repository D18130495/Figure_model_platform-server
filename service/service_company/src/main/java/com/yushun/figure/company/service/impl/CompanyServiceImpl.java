package com.yushun.figure.company.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yushun.figure.cmn.feign.DictFeignClient;
import com.yushun.figure.company.repository.CompanyRepository;
import com.yushun.figure.company.service.CompanyService;
import com.yushun.figure.model.company.BookingRule;
import com.yushun.figure.model.company.Company;
import com.yushun.figure.vo.company.CompanyQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DictFeignClient dictFeignClient;

    // upload or update company by remote request
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

    // get company list page with mongoDB
    @Override
    public Page selectCompanyPage(Integer current, Integer limit, CompanyQueryVo companyQueryVo) {
        Pageable pageable = PageRequest.of(current, limit);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        Company company = new Company();
        BeanUtils.copyProperties(companyQueryVo, company);

        Example<Company> example = Example.of(company, matcher);

        Page<Company> pages = companyRepository.findAll(example, pageable);

        pages.getContent().stream().forEach(item -> {
            this.setCompany(item);
        });

        return pages;
    }

    private Company setCompany(Company item) {
        String company_type = dictFeignClient.getDictValue("Company Type", item.getCompanyType());
        String country = dictFeignClient.getDictValue("abc", item.getCountryCode());
        String city = dictFeignClient.getDictValue("abc", item.getCityCode());
        item.getParam().put("companyType", company_type);
        item.getParam().put("country", country);
        item.getParam().put("city", city);
        return item;
    }

    // update the status of company in MongoDB, called by CompanySetController
    @Override
    public void updateCompanyStatus(String compCode, Integer status) {
        Company companyExist = companyRepository.getCompanyByCompanyCode(compCode);

        if(companyExist != null) {
            companyExist.setId(companyExist.getId());
            companyExist.setStatus(status);
            companyExist.setUpdateTime(new Date());
            companyRepository.save(companyExist);
        }
    }

    // get the detail of company with MongoDB
    @Override
    public HashMap<String, Object> getCompanyById(String id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Company company = companyRepository.findById(id).get();
        Company finalCompany = this.setCompany(company);

        result.put("company", finalCompany);
        result.put("bookingRule", finalCompany.getBookingRule());

        return result;
    }

    @Override
    public List<Company> findCompanyByName(String companyName) {
        return companyRepository.findCompanyByCompanyNameLike(companyName);
    }

    // get company detail by companyCode
    @Override
    public Map<String, Object> getCompanyByCompanyCode(String companyCode) {
        Map<String, Object> result = new HashMap<>();

        Company company = this.setCompany(companyRepository.getCompanyByCompanyCode(companyCode));
        result.put("company", company);
        result.put("bookingRule", company.getBookingRule());
        company.setBookingRule(null);

        return result;
    }
}
