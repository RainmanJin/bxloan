package com.coamctech.bxloan.entity;
// default package

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * IndustryBiz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INDUSTRY_BIZ", schema = GlobalConstants.WD_SCHEMA)
public class IndustryBiz implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private String industry;
	private String primaryProject;
	private String slackPeakSeason;
	private String needFixedPlace;
	private BigDecimal areaFreedomPlace;
	private Date boughtBuiltDate;
	private BigDecimal boughtBuiltValue;
	private BigDecimal areaRentPlace;
	private String rentDate;

	// Constructors

	/** default constructor */
	public IndustryBiz() {
	}

	/** minimal constructor */
	public IndustryBiz(Long id) {
		this.id = id;
	}

	/** full constructor */
	public IndustryBiz(Long id, Long projectId, String industry,
			String primaryProject, String slackPeakSeason,
			String needFixedPlace, BigDecimal areaFreedomPlace,
			Date boughtBuiltDate, BigDecimal boughtBuiltValue,
			BigDecimal areaRentPlace, String rentDate) {
		this.id = id;
		this.projectId = projectId;
		this.industry = industry;
		this.primaryProject = primaryProject;
		this.slackPeakSeason = slackPeakSeason;
		this.needFixedPlace = needFixedPlace;
		this.areaFreedomPlace = areaFreedomPlace;
		this.boughtBuiltDate = boughtBuiltDate;
		this.boughtBuiltValue = boughtBuiltValue;
		this.areaRentPlace = areaRentPlace;
		this.rentDate = rentDate;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_INDUSTRY_BIZ", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_ID", precision = 22, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "INDUSTRY", length = 20)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "PRIMARY_PROJECT", length = 20)
	public String getPrimaryProject() {
		return this.primaryProject;
	}

	public void setPrimaryProject(String primaryProject) {
		this.primaryProject = primaryProject;
	}

	@Column(name = "SLACK_PEAK_SEASON", length = 20)
	public String getSlackPeakSeason() {
		return this.slackPeakSeason;
	}

	public void setSlackPeakSeason(String slackPeakSeason) {
		this.slackPeakSeason = slackPeakSeason;
	}

	@Column(name = "NEED_FIXED_PLACE", length = 20)
	public String getNeedFixedPlace() {
		return this.needFixedPlace;
	}

	public void setNeedFixedPlace(String needFixedPlace) {
		this.needFixedPlace = needFixedPlace;
	}

	@Column(name = "AREA_FREEDOM_PLACE", precision = 22, scale = 0)
	public BigDecimal getAreaFreedomPlace() {
		return this.areaFreedomPlace;
	}

	public void setAreaFreedomPlace(BigDecimal areaFreedomPlace) {
		this.areaFreedomPlace = areaFreedomPlace;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BOUGHT_BUILT_DATE", length = 7)
	public Date getBoughtBuiltDate() {
		return this.boughtBuiltDate;
	}

	public void setBoughtBuiltDate(Date boughtBuiltDate) {
		this.boughtBuiltDate = boughtBuiltDate;
	}

	@Column(name = "BOUGHT_BUILT_VALUE", precision = 22, scale = 0)
	public BigDecimal getBoughtBuiltValue() {
		return this.boughtBuiltValue;
	}

	public void setBoughtBuiltValue(BigDecimal boughtBuiltValue) {
		this.boughtBuiltValue = boughtBuiltValue;
	}

	@Column(name = "AREA_RENT_PLACE", precision = 22, scale = 0)
	public BigDecimal getAreaRentPlace() {
		return this.areaRentPlace;
	}

	public void setAreaRentPlace(BigDecimal areaRentPlace) {
		this.areaRentPlace = areaRentPlace;
	}

	@Column(name = "RENT_DATE", length = 20)
	public String getRentDate() {
		return this.rentDate;
	}

	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

}