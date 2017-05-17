package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 企业客户相关组织
 * @author xc
 */
@Entity
@Table(name = "CORP_CUSTOMER_RELA_CORP", schema = WD_SCHEMA)
public class CorpCustomerRelaCorp implements java.io.Serializable {

	private Long id;
	private Long partyId;
	private String corpName;
	private String certificateCd;
	private String certificateTypeCd;
	private String businessLicenseNum;
	private String taxRegistrationNumNational;
	private String taxRegistrationNumLocal;
	private String orgTypeCd;
	private String currencyCd;
	private BigDecimal registeredCapital;
	private BigDecimal actualRevAmt;
	private String loanCardNum;
	private Date corpEstablishDate;
	private String contactorName;
	private String contactorTelNum;
	private String addressRegist;
	private String addressContact;
	private String investmentTypeCd;
	private BigDecimal investmentAmt;
	private BigDecimal stockProportion;
	private String corpIsActController;
	private String corpIsStockHolder;
	private Date createTime;

	public CorpCustomerRelaCorp() {
	}
	public CorpCustomerRelaCorp(Long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_CORP_CUSTOMER_RELA_CORP", allocationSize = 1)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CORP_NAME", length = 200)
	public String getCorpName() {
		return this.corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	@Column(name = "CERTIFICATE_CD", length = 30)
	public String getCertificateCd() {
		return this.certificateCd;
	}
	public void setCertificateCd(String certificateCd) {
		this.certificateCd = certificateCd;
	}

	@Column(name = "CERTIFICATE_TYPE_CD", length = 30)
	public String getCertificateTypeCd() {
		return this.certificateTypeCd;
	}
	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

	@Column(name = "BUSINESS_LICENSE_NUM", length = 50)
	public String getBusinessLicenseNum() {
		return this.businessLicenseNum;
	}
	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}

	@Column(name = "TAX_REGISTRATION_NUM_NATIONAL", length = 30)
	public String getTaxRegistrationNumNational() {
		return this.taxRegistrationNumNational;
	}
	public void setTaxRegistrationNumNational(String taxRegistrationNumNational) {
		this.taxRegistrationNumNational = taxRegistrationNumNational;
	}

	@Column(name = "TAX_REGISTRATION_NUM_LOCAL", length = 30)
	public String getTaxRegistrationNumLocal() {
		return this.taxRegistrationNumLocal;
	}
	public void setTaxRegistrationNumLocal(String taxRegistrationNumLocal) {
		this.taxRegistrationNumLocal = taxRegistrationNumLocal;
	}

	@Column(name = "ORG_TYPE_CD", length = 30)
	public String getOrgTypeCd() {
		return this.orgTypeCd;
	}
	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}

	@Column(name = "CURRENCY_CD", length = 30)
	public String getCurrencyCd() {
		return this.currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	@Column(name = "REGISTERED_CAPITAL", precision = 20, scale = 0)
	public BigDecimal getRegisteredCapital() {
		return this.registeredCapital;
	}
	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	@Column(name = "ACTUAL_REV_AMT", precision = 20, scale = 0)
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

	@Column(name = "CORP_ESTABLISH_DATE", length = 7)
	public Date getCorpEstablishDate() {
		return this.corpEstablishDate;
	}
	public void setCorpEstablishDate(Date corpEstablishDate) {
		this.corpEstablishDate = corpEstablishDate;
	}

	@Column(name = "CONTACTOR_NAME", length = 20)
	public String getContactorName() {
		return this.contactorName;
	}
	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}

	@Column(name = "CONTACTOR_TEL_NUM", length = 20)
	public String getContactorTelNum() {
		return this.contactorTelNum;
	}
	public void setContactorTelNum(String contactorTelNum) {
		this.contactorTelNum = contactorTelNum;
	}

	@Column(name = "ADDRESS_REGIST", length = 200)
	public String getAddressRegist() {
		return this.addressRegist;
	}
	public void setAddressRegist(String addressRegist) {
		this.addressRegist = addressRegist;
	}

	@Column(name = "ADDRESS_CONTACT", length = 200)
	public String getAddressContact() {
		return this.addressContact;
	}
	public void setAddressContact(String addressContact) {
		this.addressContact = addressContact;
	}

	@Column(name = "INVESTMENT_TYPE_CD", length = 30)
	public String getInvestmentTypeCd() {
		return this.investmentTypeCd;
	}
	public void setInvestmentTypeCd(String investmentTypeCd) {
		this.investmentTypeCd = investmentTypeCd;
	}

	@Column(name = "INVESTMENT_AMT", precision = 20, scale = 6)
	public BigDecimal getInvestmentAmt() {
		return this.investmentAmt;
	}
	public void setInvestmentAmt(BigDecimal investmentAmt) {
		this.investmentAmt = investmentAmt;
	}

	@Column(name = "STOCK_PROPORTION", precision = 12, scale = 2)
	public BigDecimal getStockProportion() {
		return this.stockProportion;
	}
	public void setStockProportion(BigDecimal stockProportion) {
		this.stockProportion = stockProportion;
	}

	@Column(name = "CORP_IS_ACT_CONTROLLER", length = 1)
	public String getCorpIsActController() {
		return this.corpIsActController;
	}
	public void setCorpIsActController(String corpIsActController) {
		this.corpIsActController = corpIsActController;
	}

	@Column(name = "CORP_IS_STOCK_HOLDER", length = 1)
	public String getCorpIsStockHolder() {
		return this.corpIsStockHolder;
	}
	public void setCorpIsStockHolder(String corpIsStockHolder) {
		this.corpIsStockHolder = corpIsStockHolder;
	}
	
	@Column(name = "CREATE_TIME", length = 11)
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}