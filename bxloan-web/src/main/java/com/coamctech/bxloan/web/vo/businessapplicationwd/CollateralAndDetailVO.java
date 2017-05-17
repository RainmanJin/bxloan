package com.coamctech.bxloan.web.vo.businessapplicationwd;

import java.math.BigDecimal;
import java.util.Date;

public class CollateralAndDetailVO {

	private Long guarantyId;
	private String guarantyNum;
	private String guarantyName;
	private String guarantyTypeCd;
	private BigDecimal marketValue;
	private BigDecimal evalValue;
	private String guarantyStatusCd;
	private String guarantyDispose;
	private String acquireWayOfGuarantyCd;
	private String commonAssetsInd;
	private String guarantySetupInd;
	private BigDecimal setGuarantyAmt;
	private String otherNote;
	private String rightCertTypeCd;
	private String rightCertificationNum;
	private Date sysUpdateDate;
	private Date sysCreateDate;
	private String guarantorName;
	private String guarantorCertificateNum;
	private String guarantorTypeCd;
	private String partOwnerName;
	private String registerUserNum;
	private Long registerOrgCd;
	private String propertyName;
	private String guaranteeType;
	private String collateralType;
	private BigDecimal lockingAmount;

	private String sysUpdateDateStr;
	
	
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
	
	
	private String modelType;
	private Double utilizedYears;
	private String purpose;
	private Integer equipMount;
	private String eliLicenseInd;
	private String safeCheckInd;
	private String fireCheckInd;
	private Integer deviceUseLife;
	private Double oriPurPrice;
	
	
	private String modelStyle;
	private Double runnedKm;
	private String troubleRecords;
	private String vehicleNumber;
	private Date registerDate;
	private String frameNumber;
	private String launchPlaneNumber;
	private String machineEnrol;
	private String machineSteamCard;

	private String registerDateStr;

	public Long getGuarantyId() {
		return guarantyId;
	}

	public void setGuarantyId(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	public String getGuarantyNum() {
		return guarantyNum;
	}

	public void setGuarantyNum(String guarantyNum) {
		this.guarantyNum = guarantyNum;
	}

	public String getGuarantyName() {
		return guarantyName;
	}

	public void setGuarantyName(String guarantyName) {
		this.guarantyName = guarantyName;
	}

	public String getGuarantyTypeCd() {
		return guarantyTypeCd;
	}

	public void setGuarantyTypeCd(String guarantyTypeCd) {
		this.guarantyTypeCd = guarantyTypeCd;
	}

	public BigDecimal getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	public BigDecimal getEvalValue() {
		return evalValue;
	}

	public void setEvalValue(BigDecimal evalValue) {
		this.evalValue = evalValue;
	}

	public String getGuarantyStatusCd() {
		return guarantyStatusCd;
	}

	public void setGuarantyStatusCd(String guarantyStatusCd) {
		this.guarantyStatusCd = guarantyStatusCd;
	}

	public String getGuarantyDispose() {
		return guarantyDispose;
	}

	public void setGuarantyDispose(String guarantyDispose) {
		this.guarantyDispose = guarantyDispose;
	}

	public String getAcquireWayOfGuarantyCd() {
		return acquireWayOfGuarantyCd;
	}

	public void setAcquireWayOfGuarantyCd(String acquireWayOfGuarantyCd) {
		this.acquireWayOfGuarantyCd = acquireWayOfGuarantyCd;
	}

	public String getCommonAssetsInd() {
		return commonAssetsInd;
	}

	public void setCommonAssetsInd(String commonAssetsInd) {
		this.commonAssetsInd = commonAssetsInd;
	}

	public String getGuarantySetupInd() {
		return guarantySetupInd;
	}

	public void setGuarantySetupInd(String guarantySetupInd) {
		this.guarantySetupInd = guarantySetupInd;
	}

	public BigDecimal getSetGuarantyAmt() {
		return setGuarantyAmt;
	}

	public void setSetGuarantyAmt(BigDecimal setGuarantyAmt) {
		this.setGuarantyAmt = setGuarantyAmt;
	}

	public String getOtherNote() {
		return otherNote;
	}

	public void setOtherNote(String otherNote) {
		this.otherNote = otherNote;
	}

	public String getRightCertTypeCd() {
		return rightCertTypeCd;
	}

	public void setRightCertTypeCd(String rightCertTypeCd) {
		this.rightCertTypeCd = rightCertTypeCd;
	}

	public String getRightCertificationNum() {
		return rightCertificationNum;
	}

	public void setRightCertificationNum(String rightCertificationNum) {
		this.rightCertificationNum = rightCertificationNum;
	}

	public Date getSysUpdateDate() {
		return sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	public Date getSysCreateDate() {
		return sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	public String getGuarantorCertificateNum() {
		return guarantorCertificateNum;
	}

	public void setGuarantorCertificateNum(String guarantorCertificateNum) {
		this.guarantorCertificateNum = guarantorCertificateNum;
	}

	public String getGuarantorTypeCd() {
		return guarantorTypeCd;
	}

	public void setGuarantorTypeCd(String guarantorTypeCd) {
		this.guarantorTypeCd = guarantorTypeCd;
	}

	public String getPartOwnerName() {
		return partOwnerName;
	}

	public void setPartOwnerName(String partOwnerName) {
		this.partOwnerName = partOwnerName;
	}

	public String getRegisterUserNum() {
		return registerUserNum;
	}

	public void setRegisterUserNum(String registerUserNum) {
		this.registerUserNum = registerUserNum;
	}

	public Long getRegisterOrgCd() {
		return registerOrgCd;
	}

	public void setRegisterOrgCd(Long registerOrgCd) {
		this.registerOrgCd = registerOrgCd;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getCollateralType() {
		return collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	public BigDecimal getLockingAmount() {
		return lockingAmount;
	}

	public void setLockingAmount(BigDecimal lockingAmount) {
		this.lockingAmount = lockingAmount;
	}

	public String getSysUpdateDateStr() {
		return sysUpdateDateStr;
	}

	public void setSysUpdateDateStr(String sysUpdateDateStr) {
		this.sysUpdateDateStr = sysUpdateDateStr;
	}

	public Date getConstructedDate() {
		return constructedDate;
	}

	public void setConstructedDate(Date constructedDate) {
		this.constructedDate = constructedDate;
	}

	public String getUtilizationType() {
		return utilizationType;
	}

	public void setUtilizationType(String utilizationType) {
		this.utilizationType = utilizationType;
	}

	public String getLandAcquiringWayCd() {
		return landAcquiringWayCd;
	}

	public void setLandAcquiringWayCd(String landAcquiringWayCd) {
		this.landAcquiringWayCd = landAcquiringWayCd;
	}

	public Double getLandArea() {
		return landArea;
	}

	public void setLandArea(Double landArea) {
		this.landArea = landArea;
	}

	public Double getBuildAllArea() {
		return buildAllArea;
	}

	public void setBuildAllArea(Double buildAllArea) {
		this.buildAllArea = buildAllArea;
	}

	public Integer getAssetTime() {
		return assetTime;
	}

	public void setAssetTime(Integer assetTime) {
		this.assetTime = assetTime;
	}

	public Integer getTotleFloor() {
		return totleFloor;
	}

	public void setTotleFloor(Integer totleFloor) {
		this.totleFloor = totleFloor;
	}

	public String getHouseLicenseOwner() {
		return houseLicenseOwner;
	}

	public void setHouseLicenseOwner(String houseLicenseOwner) {
		this.houseLicenseOwner = houseLicenseOwner;
	}

	public String getLandLicenseInd() {
		return landLicenseInd;
	}

	public void setLandLicenseInd(String landLicenseInd) {
		this.landLicenseInd = landLicenseInd;
	}

	public String getLandLicenseNum() {
		return landLicenseNum;
	}

	public void setLandLicenseNum(String landLicenseNum) {
		this.landLicenseNum = landLicenseNum;
	}

	public String getLandLicenseOwner() {
		return landLicenseOwner;
	}

	public void setLandLicenseOwner(String landLicenseOwner) {
		this.landLicenseOwner = landLicenseOwner;
	}

	public String getLandUseType() {
		return landUseType;
	}

	public void setLandUseType(String landUseType) {
		this.landUseType = landUseType;
	}

	public Integer getPropertyTerm() {
		return propertyTerm;
	}

	public void setPropertyTerm(Integer propertyTerm) {
		this.propertyTerm = propertyTerm;
	}

	public String getHouseQuitclaimCode() {
		return houseQuitclaimCode;
	}

	public void setHouseQuitclaimCode(String houseQuitclaimCode) {
		this.houseQuitclaimCode = houseQuitclaimCode;
	}

	public String getConstructedDateStr() {
		return constructedDateStr;
	}

	public void setConstructedDateStr(String constructedDateStr) {
		this.constructedDateStr = constructedDateStr;
	}

	public String getAbstractEmissarg() {
		return abstractEmissarg;
	}

	public void setAbstractEmissarg(String abstractEmissarg) {
		this.abstractEmissarg = abstractEmissarg;
	}

	public String getLandSit() {
		return landSit;
	}

	public void setLandSit(String landSit) {
		this.landSit = landSit;
	}

	public String getTerraNumber() {
		return terraNumber;
	}

	public void setTerraNumber(String terraNumber) {
		this.terraNumber = terraNumber;
	}

	public String getGeographyPurpose() {
		return geographyPurpose;
	}

	public void setGeographyPurpose(String geographyPurpose) {
		this.geographyPurpose = geographyPurpose;
	}

	public String getLandEsplees() {
		return landEsplees;
	}

	public void setLandEsplees(String landEsplees) {
		this.landEsplees = landEsplees;
	}

	public Date getLandEndDate() {
		return landEndDate;
	}

	public void setLandEndDate(Date landEndDate) {
		this.landEndDate = landEndDate;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Double getLandUseArea() {
		return landUseArea;
	}

	public void setLandUseArea(Double landUseArea) {
		this.landUseArea = landUseArea;
	}

	public Double getParticularArea() {
		return particularArea;
	}

	public void setParticularArea(Double particularArea) {
		this.particularArea = particularArea;
	}

	public Double getApportionArea() {
		return apportionArea;
	}

	public void setApportionArea(Double apportionArea) {
		this.apportionArea = apportionArea;
	}

	public String getLandEndDateStr() {
		return landEndDateStr;
	}

	public void setLandEndDateStr(String landEndDateStr) {
		this.landEndDateStr = landEndDateStr;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public Double getUtilizedYears() {
		return utilizedYears;
	}

	public void setUtilizedYears(Double utilizedYears) {
		this.utilizedYears = utilizedYears;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Integer getEquipMount() {
		return equipMount;
	}

	public void setEquipMount(Integer equipMount) {
		this.equipMount = equipMount;
	}

	public String getEliLicenseInd() {
		return eliLicenseInd;
	}

	public void setEliLicenseInd(String eliLicenseInd) {
		this.eliLicenseInd = eliLicenseInd;
	}

	public String getSafeCheckInd() {
		return safeCheckInd;
	}

	public void setSafeCheckInd(String safeCheckInd) {
		this.safeCheckInd = safeCheckInd;
	}

	public String getFireCheckInd() {
		return fireCheckInd;
	}

	public void setFireCheckInd(String fireCheckInd) {
		this.fireCheckInd = fireCheckInd;
	}

	public Integer getDeviceUseLife() {
		return deviceUseLife;
	}

	public void setDeviceUseLife(Integer deviceUseLife) {
		this.deviceUseLife = deviceUseLife;
	}

	public Double getOriPurPrice() {
		return oriPurPrice;
	}

	public void setOriPurPrice(Double oriPurPrice) {
		this.oriPurPrice = oriPurPrice;
	}

	public String getModelStyle() {
		return modelStyle;
	}

	public void setModelStyle(String modelStyle) {
		this.modelStyle = modelStyle;
	}

	public Double getRunnedKm() {
		return runnedKm;
	}

	public void setRunnedKm(Double runnedKm) {
		this.runnedKm = runnedKm;
	}

	public String getTroubleRecords() {
		return troubleRecords;
	}

	public void setTroubleRecords(String troubleRecords) {
		this.troubleRecords = troubleRecords;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public String getLaunchPlaneNumber() {
		return launchPlaneNumber;
	}

	public void setLaunchPlaneNumber(String launchPlaneNumber) {
		this.launchPlaneNumber = launchPlaneNumber;
	}

	public String getMachineEnrol() {
		return machineEnrol;
	}

	public void setMachineEnrol(String machineEnrol) {
		this.machineEnrol = machineEnrol;
	}

	public String getMachineSteamCard() {
		return machineSteamCard;
	}

	public void setMachineSteamCard(String machineSteamCard) {
		this.machineSteamCard = machineSteamCard;
	}

	public String getRegisterDateStr() {
		return registerDateStr;
	}

	public void setRegisterDateStr(String registerDateStr) {
		this.registerDateStr = registerDateStr;
	}
}
