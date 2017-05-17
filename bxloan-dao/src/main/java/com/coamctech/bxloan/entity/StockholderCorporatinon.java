package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 持股人 (企业)
 * @author xc
 *
 */
@Entity
@Table(name = "STOCKHOLDER_CORPORATINON", schema = WD_SCHEMA)
public class StockholderCorporatinon implements java.io.Serializable {


	private Long correlativeRelationsId;
	private Long scorporatinonId;
	private String stockholderName;
	private String customerNum;
	private Long partyId;
	private String certificateNum;
	private String businessLicenseNum;
	private String nationalTaxRegistrationNum;
	private String localTaxRegistrationCum;
	private String orgTypeCd;
	private String registerCapitalCurrencyCd;
	private BigDecimal registeredCapital;
	private BigDecimal actualRevAmt;
	private String loanCardNum;
	private Date standedDate;
	private String linkmanName;
	private String mobileTelphone;
	private String registerAddress;
	private String messageAddress;
	private String investmentTypeCd;
	private BigDecimal investmentAmt;
	private BigDecimal stockProportion;
	private String corporatinonorone;
	private String organizationtype;

	// Constructors

	/** default constructor */
	public StockholderCorporatinon() {
	}

	/** minimal constructor */
	public StockholderCorporatinon(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}

	/** full constructor */
	public StockholderCorporatinon(Long correlativeRelationsId,
			Long scorporatinonId, String stockholderName, String customerNum,
			Long partyId, String certificateNum, String businessLicenseNum,
			String nationalTaxRegistrationNum, String localTaxRegistrationCum,
			String orgTypeCd, String registerCapitalCurrencyCd,
			BigDecimal registeredCapital, BigDecimal actualRevAmt, String loanCardNum,
			Date standedDate, String linkmanName, String mobileTelphone,
			String registerAddress, String messageAddress,
			String investmentTypeCd, BigDecimal investmentAmt,
			BigDecimal stockProportion, String corporatinonorone,
			String organizationtype) {
		this.correlativeRelationsId = correlativeRelationsId;
		this.scorporatinonId = scorporatinonId;
		this.stockholderName = stockholderName;
		this.customerNum = customerNum;
		this.partyId = partyId;
		this.certificateNum = certificateNum;
		this.businessLicenseNum = businessLicenseNum;
		this.nationalTaxRegistrationNum = nationalTaxRegistrationNum;
		this.localTaxRegistrationCum = localTaxRegistrationCum;
		this.orgTypeCd = orgTypeCd;
		this.registerCapitalCurrencyCd = registerCapitalCurrencyCd;
		this.registeredCapital = registeredCapital;
		this.actualRevAmt = actualRevAmt;
		this.loanCardNum = loanCardNum;
		this.standedDate = standedDate;
		this.linkmanName = linkmanName;
		this.mobileTelphone = mobileTelphone;
		this.registerAddress = registerAddress;
		this.messageAddress = messageAddress;
		this.investmentTypeCd = investmentTypeCd;
		this.investmentAmt = investmentAmt;
		this.stockProportion = stockProportion;
		this.corporatinonorone = corporatinonorone;
		this.organizationtype = organizationtype;
	}

	// Property accessors
	@Id
	@Column(name = "CORRELATIVE_RELATIONS_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getCorrelativeRelationsId() {
		return this.correlativeRelationsId;
	}

	public void setCorrelativeRelationsId(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}

	@Column(name = "SCORPORATINON_ID", precision = 12, scale = 0)
	public Long getScorporatinonId() {
		return this.scorporatinonId;
	}

	public void setScorporatinonId(Long scorporatinonId) {
		this.scorporatinonId = scorporatinonId;
	}

	@Column(name = "STOCKHOLDER_NAME", length = 100)
	public String getStockholderName() {
		return this.stockholderName;
	}

	public void setStockholderName(String stockholderName) {
		this.stockholderName = stockholderName;
	}

	@Column(name = "CUSTOMER_NUM", length = 30)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CERTIFICATE_NUM", length = 60)
	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	@Column(name = "BUSINESS_LICENSE_NUM", length = 50)
	public String getBusinessLicenseNum() {
		return this.businessLicenseNum;
	}

	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}

	@Column(name = "NATIONAL_TAX_REGISTRATION_NUM", length = 30)
	public String getNationalTaxRegistrationNum() {
		return this.nationalTaxRegistrationNum;
	}

	public void setNationalTaxRegistrationNum(String nationalTaxRegistrationNum) {
		this.nationalTaxRegistrationNum = nationalTaxRegistrationNum;
	}

	@Column(name = "LOCAL_TAX_REGISTRATION_CUM", length = 30)
	public String getLocalTaxRegistrationCum() {
		return this.localTaxRegistrationCum;
	}

	public void setLocalTaxRegistrationCum(String localTaxRegistrationCum) {
		this.localTaxRegistrationCum = localTaxRegistrationCum;
	}

	@Column(name = "ORG_TYPE_CD", length = 30)
	public String getOrgTypeCd() {
		return this.orgTypeCd;
	}

	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}

	@Column(name = "REGISTER_CAPITAL_CURRENCY_CD", length = 30)
	public String getRegisterCapitalCurrencyCd() {
		return this.registerCapitalCurrencyCd;
	}

	public void setRegisterCapitalCurrencyCd(String registerCapitalCurrencyCd) {
		this.registerCapitalCurrencyCd = registerCapitalCurrencyCd;
	}

	@Column(name = "REGISTERED_CAPITAL", precision = 20)
	public BigDecimal getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	@Column(name = "ACTUAL_REV_AMT", precision = 20)
	public BigDecimal getActualRevAmt() {
		return this.actualRevAmt;
	}

	public void setActualRevAmt(BigDecimal actualRevAmt) {
		this.actualRevAmt = actualRevAmt;
	}

	@Column(name = "LOAN_CARD_NUM", length = 30)
	public String getLoanCardNum() {
		return this.loanCardNum;
	}

	public void setLoanCardNum(String loanCardNum) {
		this.loanCardNum = loanCardNum;
	}

	@Column(name = "STANDED_DATE", length = 7)
	public Date getStandedDate() {
		return this.standedDate;
	}

	public void setStandedDate(Date standedDate) {
		this.standedDate = standedDate;
	}

	@Column(name = "LINKMAN_NAME", length = 100)
	public String getLinkmanName() {
		return this.linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	@Column(name = "MOBILE_TELPHONE", length = 20)
	public String getMobileTelphone() {
		return this.mobileTelphone;
	}

	public void setMobileTelphone(String mobileTelphone) {
		this.mobileTelphone = mobileTelphone;
	}

	@Column(name = "REGISTER_ADDRESS", length = 200)
	public String getRegisterAddress() {
		return this.registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	@Column(name = "MESSAGE_ADDRESS", length = 200)
	public String getMessageAddress() {
		return this.messageAddress;
	}

	public void setMessageAddress(String messageAddress) {
		this.messageAddress = messageAddress;
	}

	@Column(name = "INVESTMENT_TYPE_CD", length = 30)
	public String getInvestmentTypeCd() {
		return this.investmentTypeCd;
	}

	public void setInvestmentTypeCd(String investmentTypeCd) {
		this.investmentTypeCd = investmentTypeCd;
	}

	@Column(name = "INVESTMENT_AMT", precision = 20)
	public BigDecimal getInvestmentAmt() {
		return this.investmentAmt;
	}

	public void setInvestmentAmt(BigDecimal investmentAmt) {
		this.investmentAmt = investmentAmt;
	}

	@Column(name = "STOCK_PROPORTION", precision = 12, scale = 6)
	public BigDecimal getStockProportion() {
		return this.stockProportion;
	}

	public void setStockProportion(BigDecimal stockProportion) {
		this.stockProportion = stockProportion;
	}

	@Column(name = "CORPORATINONORONE", length = 20)
	public String getCorporatinonorone() {
		return this.corporatinonorone;
	}

	public void setCorporatinonorone(String corporatinonorone) {
		this.corporatinonorone = corporatinonorone;
	}

	@Column(name = "ORGANIZATIONTYPE", length = 20)
	public String getOrganizationtype() {
		return this.organizationtype;
	}

	public void setOrganizationtype(String organizationtype) {
		this.organizationtype = organizationtype;
	}

}