package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.entity.BaseEntity;

@Entity
@Table(name = "INDUSTRY_TYPE", schema = WD_SCHEMA)
public class IndustryType extends BaseEntity {
	
	@Id
	@Column(name = "INDUSTRY_TYPE_CD")
	private String industryTypeCd;
	
	@Column(name = "INDUSTRY_TYPE_NAME")
	private String industryTypeName;
	
	@Column(name = "PARENT_INDUSTRY_TYPE_CD")
	private String parentIndustryTypeCd;
	
	public IndustryType() {
		super();
	}

	public String getIndustryTypeCd() {
		return industryTypeCd;
	}

	public void setIndustryTypeCd(String industryTypeCd) {
		this.industryTypeCd = industryTypeCd;
	}

	public String getIndustryTypeName() {
		return industryTypeName;
	}

	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
	}

	public String getParentIndustryTypeCd() {
		return parentIndustryTypeCd;
	}

	public void setParentIndustryTypeCd(String parentIndustryTypeCd) {
		this.parentIndustryTypeCd = parentIndustryTypeCd;
	}
	
}
