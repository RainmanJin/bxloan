package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 持股人
 * @author xc
 */
@Entity
@Table(name = "STOCKHOLDER", schema = WD_SCHEMA)
public class Stockholder implements java.io.Serializable {


	private Long correlativeRelationsId;
	private String stockholderName;
	private String certificateTypeCd;
	private String certificateCd;
	private String investmentCurrencyCd;
	private String investmentTypeCd;
	private BigDecimal stockProportion;
	private Long partyId;
	private String telNum;
	private String mobileNum;
	private String address;
	private String principalBiz;
	private Long stockholderId;
	private String genderCd;
	private Date birthdate;
	private String nation;
	private String marriageCd;
	private String degreeCd;
	private String inhabitancyStatus;
	private String qq;
	private String familyAddress;
	private String permanentAddress;
	private String workCompany;
	private String companyType;
	private String companyTel;
	private String professional;
	private String profess;
	private String companyAddress;
	private BigDecimal yearIncome;
	private BigDecimal bonusAndOtherIncome;
	private BigDecimal factMonIncome;
	private BigDecimal familyTotalAsset;
	private BigDecimal familyTotalOwes;
	private BigDecimal familyMonthlyTotalAmt;
	private BigDecimal familyMonthlyTotalPayout;
	private String creditRecord;
	private String beforeCreditHistory;
	private String detailRemark;
	private BigDecimal area;
	private String position;
	private BigDecimal nowPrice;
	private BigDecimal primaryPrice;
	private String corporatinonorone;
	private String currencyType;
	private BigDecimal investmentAmt;

	/** default constructor */
	public Stockholder() {
	}
	/** minimal constructor */
	public Stockholder(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}

	@Id
	@Column(name = "CORRELATIVE_RELATIONS_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getCorrelativeRelationsId() {
		return this.correlativeRelationsId;
	}

	public void setCorrelativeRelationsId(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}

	@Column(name = "STOCKHOLDER_NAME", length = 100)
	public String getStockholderName() {
		return this.stockholderName;
	}

	public void setStockholderName(String stockholderName) {
		this.stockholderName = stockholderName;
	}

	@Column(name = "CERTIFICATE_TYPE_CD", length = 30)
	public String getCertificateTypeCd() {
		return this.certificateTypeCd;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

	@Column(name = "CERTIFICATE_CD", length = 30)
	public String getCertificateCd() {
		return this.certificateCd;
	}

	public void setCertificateCd(String certificateCd) {
		this.certificateCd = certificateCd;
	}

	@Column(name = "INVESTMENT_CURRENCY_CD", length = 30)
	public String getInvestmentCurrencyCd() {
		return this.investmentCurrencyCd;
	}

	public void setInvestmentCurrencyCd(String investmentCurrencyCd) {
		this.investmentCurrencyCd = investmentCurrencyCd;
	}

	@Column(name = "INVESTMENT_TYPE_CD", length = 30)
	public String getInvestmentTypeCd() {
		return this.investmentTypeCd;
	}

	public void setInvestmentTypeCd(String investmentTypeCd) {
		this.investmentTypeCd = investmentTypeCd;
	}

	@Column(name = "STOCK_PROPORTION", precision = 12, scale = 6)
	public BigDecimal getStockProportion() {
		return this.stockProportion;
	}

	public void setStockProportion(BigDecimal stockProportion) {
		this.stockProportion = stockProportion;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "TEL_NUM", length = 30)
	public String getTelNum() {
		return this.telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	@Column(name = "MOBILE_NUM", length = 20)
	public String getMobileNum() {
		return this.mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	@Column(name = "ADDRESS", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PRINCIPAL_BIZ", length = 100)
	public String getPrincipalBiz() {
		return this.principalBiz;
	}

	public void setPrincipalBiz(String principalBiz) {
		this.principalBiz = principalBiz;
	}

	@Column(name = "STOCKHOLDER_ID", precision = 12, scale = 0)
	public Long getStockholderId() {
		return this.stockholderId;
	}

	public void setStockholderId(Long stockholderId) {
		this.stockholderId = stockholderId;
	}

	@Column(name = "GENDER_CD", length = 1)
	public String getGenderCd() {
		return this.genderCd;
	}

	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	@Column(name = "BIRTHDATE", length = 7)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "NATION", length = 30)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "MARRIAGE_CD", length = 30)
	public String getMarriageCd() {
		return this.marriageCd;
	}

	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}

	@Column(name = "DEGREE_CD", length = 30)
	public String getDegreeCd() {
		return this.degreeCd;
	}

	public void setDegreeCd(String degreeCd) {
		this.degreeCd = degreeCd;
	}

	@Column(name = "INHABITANCY_STATUS", length = 30)
	public String getInhabitancyStatus() {
		return this.inhabitancyStatus;
	}

	public void setInhabitancyStatus(String inhabitancyStatus) {
		this.inhabitancyStatus = inhabitancyStatus;
	}

	@Column(name = "QQ", length = 30)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "FAMILY_ADDRESS", length = 200)
	public String getFamilyAddress() {
		return this.familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	@Column(name = "PERMANENT_ADDRESS", length = 200)
	public String getPermanentAddress() {
		return this.permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	@Column(name = "WORK_COMPANY", length = 100)
	public String getWorkCompany() {
		return this.workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	@Column(name = "COMPANY_TYPE", length = 30)
	public String getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name = "COMPANY_TEL", length = 100)
	public String getCompanyTel() {
		return this.companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	@Column(name = "PROFESSIONAL", length = 30)
	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	@Column(name = "PROFESS", length = 30)
	public String getProfess() {
		return this.profess;
	}

	public void setProfess(String profess) {
		this.profess = profess;
	}

	@Column(name = "COMPANY_ADDRESS", length = 100)
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "YEAR_INCOME", precision = 20)
	public BigDecimal getYearIncome() {
		return this.yearIncome;
	}

	public void setYearIncome(BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}

	@Column(name = "BONUS_AND_OTHER_INCOME", precision = 20)
	public BigDecimal getBonusAndOtherIncome() {
		return this.bonusAndOtherIncome;
	}

	public void setBonusAndOtherIncome(BigDecimal bonusAndOtherIncome) {
		this.bonusAndOtherIncome = bonusAndOtherIncome;
	}

	@Column(name = "FACT_MON_INCOME", precision = 20)
	public BigDecimal getFactMonIncome() {
		return this.factMonIncome;
	}

	public void setFactMonIncome(BigDecimal factMonIncome) {
		this.factMonIncome = factMonIncome;
	}

	@Column(name = "FAMILY_TOTAL_ASSET", precision = 20)
	public BigDecimal getFamilyTotalAsset() {
		return this.familyTotalAsset;
	}

	public void setFamilyTotalAsset(BigDecimal familyTotalAsset) {
		this.familyTotalAsset = familyTotalAsset;
	}

	@Column(name = "FAMILY_TOTAL_OWES", precision = 20)
	public BigDecimal getFamilyTotalOwes() {
		return this.familyTotalOwes;
	}

	public void setFamilyTotalOwes(BigDecimal familyTotalOwes) {
		this.familyTotalOwes = familyTotalOwes;
	}

	@Column(name = "FAMILY_MONTHLY_TOTAL_AMT", precision = 20)
	public BigDecimal getFamilyMonthlyTotalAmt() {
		return this.familyMonthlyTotalAmt;
	}

	public void setFamilyMonthlyTotalAmt(BigDecimal familyMonthlyTotalAmt) {
		this.familyMonthlyTotalAmt = familyMonthlyTotalAmt;
	}

	@Column(name = "FAMILY_MONTHLY_TOTAL_PAYOUT", precision = 20)
	public BigDecimal getFamilyMonthlyTotalPayout() {
		return this.familyMonthlyTotalPayout;
	}

	public void setFamilyMonthlyTotalPayout(BigDecimal familyMonthlyTotalPayout) {
		this.familyMonthlyTotalPayout = familyMonthlyTotalPayout;
	}

	@Column(name = "CREDIT_RECORD", length = 1)
	public String getCreditRecord() {
		return this.creditRecord;
	}

	public void setCreditRecord(String creditRecord) {
		this.creditRecord = creditRecord;
	}

	@Column(name = "BEFORE_CREDIT_HISTORY", length = 1)
	public String getBeforeCreditHistory() {
		return this.beforeCreditHistory;
	}

	public void setBeforeCreditHistory(String beforeCreditHistory) {
		this.beforeCreditHistory = beforeCreditHistory;
	}

	@Column(name = "DETAIL_REMARK", length = 2000)
	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	@Column(name = "AREA", precision = 20)
	public BigDecimal getArea() {
		return this.area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	@Column(name = "POSITION", length = 200)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "NOW_PRICE", precision = 20)
	public BigDecimal getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}

	@Column(name = "PRIMARY_PRICE", precision = 20)
	public BigDecimal getPrimaryPrice() {
		return this.primaryPrice;
	}

	public void setPrimaryPrice(BigDecimal primaryPrice) {
		this.primaryPrice = primaryPrice;
	}

	@Column(name = "CORPORATINONORONE", length = 20)
	public String getCorporatinonorone() {
		return this.corporatinonorone;
	}

	public void setCorporatinonorone(String corporatinonorone) {
		this.corporatinonorone = corporatinonorone;
	}

	@Column(name = "CURRENCY_TYPE", length = 20)
	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	@Column(name = "INVESTMENT_AMT", precision = 20, scale = 6)
	public BigDecimal getInvestmentAmt() {
		return this.investmentAmt;
	}

	public void setInvestmentAmt(BigDecimal investmentAmt) {
		this.investmentAmt = investmentAmt;
	}

}