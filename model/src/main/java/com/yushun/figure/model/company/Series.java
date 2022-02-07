package com.yushun.figure.model.company;

import com.yushun.figure.model.base.BaseMongoEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("series")
public class Series extends BaseMongoEntity {
	
	private static final long serialVersionUID = 1L;

    // company code
	@Indexed
	private String companyCode;

    // series code
	@Indexed
	private String seriesCode;

    // series name
	private String seriesName;

    // series description
	private String desc;

	// series belong to which
	private String bigCode;

	// series belong to which
	private String bigName;

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

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBigCode() {
		return bigCode;
	}

	public void setBigCode(String bigCode) {
		this.bigCode = bigCode;
	}

	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}
}

