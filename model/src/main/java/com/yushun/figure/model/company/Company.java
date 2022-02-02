package com.yushun.figure.model.company;


import com.alibaba.fastjson.JSONObject;
import com.yushun.figure.model.base.BaseMongoEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("figure_model")
public class Company extends BaseMongoEntity {
	
	private static final long serialVersionUID = 1L;

	@Indexed(unique = true)
	private String companyCode;

	@Indexed
	private String companyName;

	private String companyType;

	private String countryCode;

	private String cityCode;

	private String address;

	private String logoData;

	private String intro;

	// status = "0: offline, 1: online"
	private Integer status;

	private BookingRule bookingRule;

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogoData() {
		return logoData;
	}

	public void setLogoData(String logoData) {
		this.logoData = logoData;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BookingRule getBookingRule() {
		return bookingRule;
	}

	public void setBookingRule(BookingRule bookingRule) {
		this.bookingRule = bookingRule;
	}
}

