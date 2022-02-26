package com.yushun.figure.vo.company;

import java.math.BigDecimal;
import java.util.Date;

public class ScheduleOrderVo {

    private String companyCode;

    private String companyName;

    private String seriesCode;

    private String seriesName;

    private String companyScheduleId;

    private Date reserveDate;

    private Integer availableNumber;

    private Integer reserveTime;

    private String amount;

    private Date quitTime;

    private Date startTime;

    private Date endTime;

    private Date stopTime;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getCompanyScheduleId() {
        return companyScheduleId;
    }

    public void setCompanyScheduleId(String companyScheduleId) {
        this.companyScheduleId = companyScheduleId;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Integer getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(Integer availableNumber) {
        this.availableNumber = availableNumber;
    }

    public Integer getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Integer reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }
}