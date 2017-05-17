package com.coamctech.bxloan.entity;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * RealEstateMortgage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REAL_ESTATE_MORTGAGE", schema = WD_SCHEMA)
public class RealEstateMortgage implements java.io.Serializable {

	// Fields

	private Long guarantyId;
	private Date constructedDate;
	private String utilizationType;
	private String landAcquiringWayCd;
	private Double landArea;
	private Double buildAllArea;
	private Integer assetTime;
	private Integer totleFloor;
	private String houseLicenseOwner;
	private String landLicenseInd;
	private String landLicenseNum;
	private String landLicenseOwner;
	private String landUseType;
	private Integer propertyTerm;
	private String houseQuitclaimCode;

	private String constructedDateStr;

	// Constructors

	/** default constructor */
	public RealEstateMortgage() {
	}

	/** minimal constructor */
	public RealEstateMortgage(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	/** full constructor */
	public RealEstateMortgage(Long guarantyId, Date constructedDate,
			String utilizationType, String landAcquiringWayCd, Double landArea,
			Double buildAllArea, Integer assetTime, Integer totleFloor,
			String houseLicenseOwner, String landLicenseInd,
			String landLicenseNum, String landLicenseOwner, String landUseType,
			Integer propertyTerm, String houseQuitclaimCode) {
		this.guarantyId = guarantyId;
		this.constructedDate = constructedDate;
		this.utilizationType = utilizationType;
		this.landAcquiringWayCd = landAcquiringWayCd;
		this.landArea = landArea;
		this.buildAllArea = buildAllArea;
		this.assetTime = assetTime;
		this.totleFloor = totleFloor;
		this.houseLicenseOwner = houseLicenseOwner;
		this.landLicenseInd = landLicenseInd;
		this.landLicenseNum = landLicenseNum;
		this.landLicenseOwner = landLicenseOwner;
		this.landUseType = landUseType;
		this.propertyTerm = propertyTerm;
		this.houseQuitclaimCode = houseQuitclaimCode;
	}

	// Property accessors
	@Id
	@Column(name = "GUARANTY_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getGuarantyId() {
		return this.guarantyId;
	}

	public void setGuarantyId(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	@Column(name = "CONSTRUCTED_DATE", length = 7)
	public Date getConstructedDate() {
		return this.constructedDate;
	}

	public void setConstructedDate(Date constructedDate) {
		this.constructedDate = constructedDate;
	}

	@Column(name = "UTILIZATION_TYPE", length = 20)
	public String getUtilizationType() {
		return this.utilizationType;
	}

	public void setUtilizationType(String utilizationType) {
		this.utilizationType = utilizationType;
	}

	@Column(name = "LAND_ACQUIRING_WAY_CD", length = 20)
	public String getLandAcquiringWayCd() {
		return this.landAcquiringWayCd;
	}

	public void setLandAcquiringWayCd(String landAcquiringWayCd) {
		this.landAcquiringWayCd = landAcquiringWayCd;
	}

	@Column(name = "LAND_AREA", precision = 20, scale = 6)
	public Double getLandArea() {
		return this.landArea;
	}

	public void setLandArea(Double landArea) {
		this.landArea = landArea;
	}

	@Column(name = "BUILD_ALL_AREA", precision = 20, scale = 6)
	public Double getBuildAllArea() {
		return this.buildAllArea;
	}

	public void setBuildAllArea(Double buildAllArea) {
		this.buildAllArea = buildAllArea;
	}

	@Column(name = "ASSET_TIME", precision = 8, scale = 0)
	public Integer getAssetTime() {
		return this.assetTime;
	}

	public void setAssetTime(Integer assetTime) {
		this.assetTime = assetTime;
	}

	@Column(name = "TOTLE_FLOOR", precision = 8, scale = 0)
	public Integer getTotleFloor() {
		return this.totleFloor;
	}

	public void setTotleFloor(Integer totleFloor) {
		this.totleFloor = totleFloor;
	}

	@Column(name = "HOUSE_LICENSE_OWNER", length = 50)
	public String getHouseLicenseOwner() {
		return this.houseLicenseOwner;
	}

	public void setHouseLicenseOwner(String houseLicenseOwner) {
		this.houseLicenseOwner = houseLicenseOwner;
	}

	@Column(name = "LAND_LICENSE_IND", length = 100)
	public String getLandLicenseInd() {
		return this.landLicenseInd;
	}

	public void setLandLicenseInd(String landLicenseInd) {
		this.landLicenseInd = landLicenseInd;
	}

	@Column(name = "LAND_LICENSE_NUM", length = 40)
	public String getLandLicenseNum() {
		return this.landLicenseNum;
	}

	public void setLandLicenseNum(String landLicenseNum) {
		this.landLicenseNum = landLicenseNum;
	}

	@Column(name = "LAND_LICENSE_OWNER", length = 100)
	public String getLandLicenseOwner() {
		return this.landLicenseOwner;
	}

	public void setLandLicenseOwner(String landLicenseOwner) {
		this.landLicenseOwner = landLicenseOwner;
	}

	@Column(name = "LAND_USE_TYPE", length = 40)
	public String getLandUseType() {
		return this.landUseType;
	}

	public void setLandUseType(String landUseType) {
		this.landUseType = landUseType;
	}

	@Column(name = "PROPERTY_TERM", precision = 8, scale = 0)
	public Integer getPropertyTerm() {
		return this.propertyTerm;
	}

	public void setPropertyTerm(Integer propertyTerm) {
		this.propertyTerm = propertyTerm;
	}

	@Column(name = "HOUSE_QUITCLAIM_CODE", length = 100)
	public String getHouseQuitclaimCode() {
		return this.houseQuitclaimCode;
	}

	public void setHouseQuitclaimCode(String houseQuitclaimCode) {
		this.houseQuitclaimCode = houseQuitclaimCode;
	}

	@Transient
	public String getConstructedDateStr() {
		return constructedDateStr;
	}

	public void setConstructedDateStr(String constructedDateStr) {
		this.constructedDateStr = constructedDateStr;
	}

}