package com.yushun.figure.model.company;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BookingScheduleRuleVo {
	
	// order data
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;

	private Date workDateMd;

	// the day od the week
	private String dayOfWeek;

	// number of figure can be ordered in this day
	private Integer figureCount;

	// total reservedNumber for all figure
	private Integer reservedNumber;

	// total availableNumber for all figure
	private Integer availableNumber;

	// the current status of the figure module
	private Integer status;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getWorkDateMd() {
		return workDateMd;
	}

	public void setWorkDateMd(Date workDateMd) {
		this.workDateMd = workDateMd;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getFigureCount() {
		return figureCount;
	}

	public void setFigureCount(Integer figureCount) {
		this.figureCount = figureCount;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

