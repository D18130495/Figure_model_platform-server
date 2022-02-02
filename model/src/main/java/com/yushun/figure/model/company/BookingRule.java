package com.yushun.figure.model.company;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.List;

@Document("BookingRule")
public class BookingRule {

//"预约周期"
	private Integer cycle;

//"放号时间"
	private String releaseTime;

//"停挂时间"
	private String stopTime;

//"退号截止天数（如：就诊前一天为-1，当天为0）"
	private Integer quitDay;

//"退号时间"
	private String quitTime;

//"预约规则"
	private String rule;

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public Integer getQuitDay() {
		return quitDay;
	}

	public void setQuitDay(Integer quitDay) {
		this.quitDay = quitDay;
	}

	public String getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(String quitTime) {
		this.quitTime = quitTime;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
}

