package com.yushun.figure.model.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yushun.figure.model.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

@TableName("order_info")
public class OrderInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Long userId;

    @TableField("out_trade_no")
    private String outTradeNo;

    @TableField("company_code")
    private String companyCode;

    @TableField("company_name")
    private String companyName;

    @TableField("series_code")
    private String seriesCode;

    @TableField("series_name")
    private String seriesName;

    @TableField("schedule_id")  //对应数据库添加一个schedule_id字段
//	@TableField("hos_schedule_id")
    private String scheduleId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("reserve_date")
    private Date reserveDate;

    @TableField("reserve_time")
    private Integer reserveTime;

    @TableField("people_id")
    private Long peopleId;

    @TableField("people_name")
    private String peopleName;

    @TableField("people_phone")
    private String peoplePhone;

    @TableField("people_address")
    private String peopleAddress;

    @TableField("company_record_id")
    private String companyRecordId;

    @TableField("number")
    private Integer number;

    @TableField("fetch_time")
    private String fetchTime;

    @TableField("fetch_address")
    private String fetchAddress;

    @TableField("amount")
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField("quit_time")
    private Date quitTime;

    @TableField("order_status")
    private Integer orderStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

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

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Integer getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Integer reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Long getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Long peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeoplePhone() {
        return peoplePhone;
    }

    public void setPeoplePhone(String peoplePhone) {
        this.peoplePhone = peoplePhone;
    }

    public String getPeopleAddress() {
        return peopleAddress;
    }

    public void setPeopleAddress(String peopleAddress) {
        this.peopleAddress = peopleAddress;
    }

    public String getCompanyRecordId() {
        return companyRecordId;
    }

    public void setCompanyRecordId(String companyRecordId) {
        this.companyRecordId = companyRecordId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(String fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getFetchAddress() {
        return fetchAddress;
    }

    public void setFetchAddress(String fetchAddress) {
        this.fetchAddress = fetchAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}


