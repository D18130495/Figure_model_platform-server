package com.yushun.figure.company.repository;

import com.yushun.figure.model.company.FigureSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureScheduleRepository extends MongoRepository<FigureSchedule, String> {
    FigureSchedule getFigureScheduleByCompanyCodeAndFigureCode(String companyCode, String figureCode);
}
