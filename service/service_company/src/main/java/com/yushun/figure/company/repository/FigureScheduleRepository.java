package com.yushun.figure.company.repository;

import com.yushun.figure.model.company.FigureSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FigureScheduleRepository extends MongoRepository<FigureSchedule, String> {
    FigureSchedule getFigureScheduleByCompanyCodeAndFigureCode(String companyCode, String figureCode);

    List<FigureSchedule> getFigureScheduleByCompanyCodeAndSeriesCodeAndOrderDate(String companyCode, String seriesCode, Date toDate);
}
