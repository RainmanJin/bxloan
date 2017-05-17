package com.coamctech.bxloan.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * MachineEquipmentMortgage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MACHINE_EQUIPMENT_MORTGAGE", schema = WD_SCHEMA)
public class MachineEquipmentMortgage implements java.io.Serializable {

	// Fields

	private Long guarantyId;
	private String modelType;
	private Double utilizedYears;
	private String purpose;
	private Integer equipMount;
	private String eliLicenseInd;
	private String safeCheckInd;
	private String fireCheckInd;
	private Integer deviceUseLife;
	private Double oriPurPrice;

	// Constructors

	/** default constructor */
	public MachineEquipmentMortgage() {
	}

	/** minimal constructor */
	public MachineEquipmentMortgage(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	/** full constructor */
	public MachineEquipmentMortgage(Long guarantyId, String modelType,
			Double utilizedYears, String purpose, Integer equipMount,
			String eliLicenseInd, String safeCheckInd, String fireCheckInd,
			Integer deviceUseLife, Double oriPurPrice) {
		this.guarantyId = guarantyId;
		this.modelType = modelType;
		this.utilizedYears = utilizedYears;
		this.purpose = purpose;
		this.equipMount = equipMount;
		this.eliLicenseInd = eliLicenseInd;
		this.safeCheckInd = safeCheckInd;
		this.fireCheckInd = fireCheckInd;
		this.deviceUseLife = deviceUseLife;
		this.oriPurPrice = oriPurPrice;
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

	@Column(name = "MODEL_TYPE", length = 100)
	public String getModelType() {
		return this.modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	@Column(name = "UTILIZED_YEARS", precision = 20, scale = 6)
	public Double getUtilizedYears() {
		return this.utilizedYears;
	}

	public void setUtilizedYears(Double utilizedYears) {
		this.utilizedYears = utilizedYears;
	}

	@Column(name = "PURPOSE", length = 1000)
	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Column(name = "EQUIP_MOUNT", precision = 8, scale = 0)
	public Integer getEquipMount() {
		return this.equipMount;
	}

	public void setEquipMount(Integer equipMount) {
		this.equipMount = equipMount;
	}

	@Column(name = "ELI_LICENSE_IND", length = 1)
	public String getEliLicenseInd() {
		return this.eliLicenseInd;
	}

	public void setEliLicenseInd(String eliLicenseInd) {
		this.eliLicenseInd = eliLicenseInd;
	}

	@Column(name = "SAFE_CHECK_IND", length = 1)
	public String getSafeCheckInd() {
		return this.safeCheckInd;
	}

	public void setSafeCheckInd(String safeCheckInd) {
		this.safeCheckInd = safeCheckInd;
	}

	@Column(name = "FIRE_CHECK_IND", length = 1)
	public String getFireCheckInd() {
		return this.fireCheckInd;
	}

	public void setFireCheckInd(String fireCheckInd) {
		this.fireCheckInd = fireCheckInd;
	}

	@Column(name = "DEVICE_USE_LIFE", precision = 8, scale = 0)
	public Integer getDeviceUseLife() {
		return this.deviceUseLife;
	}

	public void setDeviceUseLife(Integer deviceUseLife) {
		this.deviceUseLife = deviceUseLife;
	}

	@Column(name = "ORI_PUR_PRICE", precision = 20)
	public Double getOriPurPrice() {
		return this.oriPurPrice;
	}

	public void setOriPurPrice(Double oriPurPrice) {
		this.oriPurPrice = oriPurPrice;
	}

}