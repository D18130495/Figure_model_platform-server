package com.yushun.figure.model.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yushun.figure.model.base.BaseMongoEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document("figure_schedule")
public class FigureSchedule extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    @Indexed
    private String companyCode;

    @Indexed
    private String seriesCode;

    private String figureCode;

    private String figureName;

    private String desc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    private Integer reservedNumber;

    private Integer availableNumber;

    private String preorderFee;

    // 0：close pre-order, 1：open pre-order
    private Integer status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getFigureCode() {
        return figureCode;
    }

    public void setFigureCode(String figureCode) {
        this.figureCode = figureCode;
    }

    public String getFigureName() {
        return figureName;
    }

    public void setFigureName(String figureName) {
        this.figureName = figureName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getReservedNumber() {
        return reservedNumber;
    }

    public void setReservedNumber(Integer reservedNumber) {
        this.reservedNumber = reservedNumber;
    }

    public Integer getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(Integer availableNumber) {
        this.availableNumber = availableNumber;
    }

    public String getPreorderFee() {
        return preorderFee;
    }

    public void setPreorderFee(String preorderFee) {
        this.preorderFee = preorderFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
