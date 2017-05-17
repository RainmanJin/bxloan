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
 * LandProduce entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LAND_PRODUCE", schema = WD_SCHEMA)
public class LandProduce implements java.io.Serializable {

	// Fields

	private Long guarantyId;
	private String abstractEmissarg;
	private String landSit;
	private String terraNumber;
	private String geographyPurpose;
	private String landEsplees;
	private Date landEndDate;
	private String accessType;
	private Double landUseArea;
	private Double particularArea;
	private Double apportionArea;

	private String landEndDateStr;

	// Constructors

	/** default constructor */
	public LandProduce() {
	}

	/** minimal constructor */
	public LandProduce(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	/** full constructor */
	public LandProduce(Long guarantyId, String abstractEmissarg,
			String landSit, String terraNumber, String geographyPurpose,
			String landEsplees, Date landEndDate, String accessType,
			Double landUseArea, Double particularArea, Double apportionArea) {
		this.guarantyId = guarantyId;
		this.abstractEmissarg = abstractEmissarg;
		this.landSit = landSit;
		this.terraNumber = terraNumber;
		this.geographyPurpose = geographyPurpose;
		this.landEsplees = landEsplees;
		this.landEndDate = landEndDate;
		this.accessType = accessType;
		this.landUseArea = landUseArea;
		this.particularArea = particularArea;
		this.apportionArea = apportionArea;
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

	@Column(name = "ABSTRACT_EMISSARG", length = 200)
	public String getAbstractEmissarg() {
		return this.abstractEmissarg;
	}

	public void setAbstractEmissarg(String abstractEmissarg) {
		this.abstractEmissarg = abstractEmissarg;
	}

	@Column(name = "LAND_SIT", length = 200)
	public String getLandSit() {
		return this.landSit;
	}

	public void setLandSit(String landSit) {
		this.landSit = landSit;
	}

	@Column(name = "TERRA_NUMBER", length = 40)
	public String getTerraNumber() {
		return this.terraNumber;
	}

	public void setTerraNumber(String terraNumber) {
		this.terraNumber = terraNumber;
	}

	@Column(name = "GEOGRAPHY_PURPOSE", length = 200)
	public String getGeographyPurpose() {
		return this.geographyPurpose;
	}

	public void setGeographyPurpose(String geographyPurpose) {
		this.geographyPurpose = geographyPurpose;
	}

	@Column(name = "LAND_ESPLEES", length = 40)
	public String getLandEsplees() {
		return this.landEsplees;
	}

	public void setLandEsplees(String landEsplees) {
		this.landEsplees = landEsplees;
	}

	@Column(name = "LAND_END_DATE", length = 7)
	public Date getLandEndDate() {
		return this.landEndDate;
	}

	public void setLandEndDate(Date landEndDate) {
		this.landEndDate = landEndDate;
	}

	@Column(name = "ACCESS_TYPE", length = 20)
	public String getAccessType() {
		return this.accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	@Column(name = "LAND_USE_AREA", precision = 16)
	public Double getLandUseArea() {
		return this.landUseArea;
	}

	public void setLandUseArea(Double landUseArea) {
		this.landUseArea = landUseArea;
	}

	@Column(name = "PARTICULAR_AREA", precision = 20)
	public Double getParticularArea() {
		return this.particularArea;
	}

	public void setParticularArea(Double particularArea) {
		this.particularArea = particularArea;
	}

	@Column(name = "APPORTION_AREA", precision = 20)
	public Double getApportionArea() {
		return this.apportionArea;
	}

	public void setApportionArea(Double apportionArea) {
		this.apportionArea = apportionArea;
	}

	@Transient
	public String getLandEndDateStr() {
		return landEndDateStr;
	}

	public void setLandEndDateStr(String landEndDateStr) {
		this.landEndDateStr = landEndDateStr;
	}

}