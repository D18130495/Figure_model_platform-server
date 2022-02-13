package com.yushun.figure.company.repository;

import com.yushun.figure.model.company.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    Company getCompanyByCompanyCode(String companyCode);

    List<Company> findCompanyByCompanyNameLike(String companyName);
}
