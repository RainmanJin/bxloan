package com.coamctech.bxloan.web.vo.bizapply;

import java.math.BigDecimal;

public class IndividualAgroVO {
	
	private Long partyId;
	private String customerName;
	private String genderCd;
	private String permanentAddress; //户籍地
	private String marriageCd; //婚姻状况
	private String mobileTel;
	private String houseCondition;//本地有无房产
	private String inhabitancyStatus;  //居住状况
	private String familyAddress; //居住地址
	private String industryCd; 	//所属行业
	
	private String idCardNum; //身份证号
	
	private Integer age; //年龄
	private String agroPopulation; //农业人口
	private BigDecimal livingArea; //居住面积
	private String appliancesCase; //家电情况
	private String spouseCase; //配偶情况
	private String spouseEmployment; //配偶就业情况
	private Integer familySize; //家庭人口
	private Integer workForce; //劳动人口
	private Integer dependentPopulation; //供养人口
	private BigDecimal dependentPopulationRate; //供养人口比
	private String yearsInIndustry;  //现单位工作年限
	private String localInhabitancyYears; //本地居住年限
	private String agroBizType; //是否农业为主
	
	
	public IndividualAgroVO() {
		super();
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getGenderCd() {
		return genderCd;
	}
	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getMarriageCd() {
		return marriageCd;
	}
	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public String getHouseCondition() {
		return houseCondition;
	}
	public void setHouseCondition(String houseCondition) {
		this.houseCondition = houseCondition;
	}
	public String getInhabitancyStatus() {
		return inhabitancyStatus;
	}
	public void setInhabitancyStatus(String inhabitancyStatus) {
		this.inhabitancyStatus = inhabitancyStatus;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getIndustryCd() {
		return industryCd;
	}
	public void setIndustryCd(String industryCd) {
		this.industryCd = industryCd;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAgroPopulation() {
		return agroPopulation;
	}
	public void setAgroPopulation(String agroPopulation) {
		this.agroPopulation = agroPopulation;
	}
	public BigDecimal getLivingArea() {
		return livingArea;
	}
	public void setLivingArea(BigDecimal livingArea) {
		this.livingArea = livingArea;
	}
	public String getAppliancesCase() {
		return appliancesCase;
	}
	public void setAppliancesCase(String appliancesCase) {
		this.appliancesCase = appliancesCase;
	}
	public String getSpouseCase() {
		return spouseCase;
	}
	public void setSpouseCase(String spouseCase) {
		this.spouseCase = spouseCase;
	}
	public String getSpouseEmployment() {
		return spouseEmployment;
	}
	public void setSpouseEmployment(String spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}
	public Integer getFamilySize() {
		return familySize;
	}
	public void setFamilySize(Integer familySize) {
		this.familySize = familySize;
	}
	public Integer getWorkForce() {
		return workForce;
	}
	public void setWorkForce(Integer workForce) {
		this.workForce = workForce;
	}
	public Integer getDependentPopulation() {
		return dependentPopulation;
	}
	public void setDependentPopulation(Integer dependentPopulation) {
		this.dependentPopulation = dependentPopulation;
	}
	public BigDecimal getDependentPopulationRate() {
		return dependentPopulationRate;
	}
	public void setDependentPopulationRate(BigDecimal dependentPopulationRate) {
		this.dependentPopulationRate = dependentPopulationRate;
	}
	public String getYearsInIndustry() {
		return yearsInIndustry;
	}
	public void setYearsInIndustry(String yearsInIndustry) {
		this.yearsInIndustry = yearsInIndustry;
	}
	public String getLocalInhabitancyYears() {
		return localInhabitancyYears;
	}
	public void setLocalInhabitancyYears(String localInhabitancyYears) {
		this.localInhabitancyYears = localInhabitancyYears;
	}
	
	public String getAgroBizType() {
		return agroBizType;
	}
	public void setAgroBizType(String agroBizType) {
		this.agroBizType = agroBizType;
	}
	public String getIdCardNum() {
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	
	
	
}
