package com.yushun.figure.company.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yushun.figure.company.mapper.CompanySetMapper;
import com.yushun.figure.company.service.CompanySetService;
import com.yushun.figure.model.company.CompanySet;
import org.springframework.stereotype.Service;

@Service
public class CompanySetServiceImpl extends ServiceImpl<CompanySetMapper, CompanySet> implements CompanySetService {
}
