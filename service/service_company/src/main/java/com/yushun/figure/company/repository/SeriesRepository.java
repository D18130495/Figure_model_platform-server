package com.yushun.figure.company.repository;

import com.yushun.figure.model.company.Series;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends MongoRepository<Series, String> {
    Series getSeriesByCompanyCodeAndSeriesCode(String companyCode, String seriesCode);
}
