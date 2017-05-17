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

import com.coamctech.bxloan.commons.GlobalConstants;


/**
 * 企业客户相关人员表(股东,实际控制人,高管)
 * @author xc
 */
@Entity
@Table(name = "CORP_CUSTOMER_RELA_PERSON", schema = WD_SCHEMA)
public class CorpCustomerRelaPerson implements java.io.Serializable {
	

	private Long id;
	private Long partyId;
	
	private String name;
	private String certificateTypeCd;
	private String certificateCd;
	private String investmentCurrencyCd;
	private String investmentTypeCd;
	private BigDecimal investmentAmt;
	private String investmentCurrencyType;
	private BigDecimal stockProportion;
	private String familyTelNum;
	private String contactTelNum;
	private String sexCd;
	private Date birthDate;
	private String nation;
	private String degreeCd;
	private String marriageCd;
	private String inhabitancyStatus;
	private String mailQq;
	private String familyAddress;
	private String permanentAddress;
	private String workCompany;
	private String workCompanyType;
	private String workCompanyTel;
	private String professional;//岗位
	private String positionCd;//职务
	private String companyAddress;
	private BigDecimal incomeYear;
	private BigDecimal incomeBonusYear;
	private BigDecimal incomeMonthFact;
	private BigDecimal familyTotalAsset;
	private BigDecimal familyTotalOwes;
	private BigDecimal familyMonthlyTotalAmt;
	private BigDecimal familyMonthlyTotalPayout;
	private String creditRecord;
	private String creditRecordOverdue;
	private String creditRemark;
	private String residencePosition;
	private Long residenceArea;
	private BigDecimal residencePrimaryPrice;
	private BigDecimal residenceNowPrice;
	private String personIsStockHolder;
	private String personIsActController;
	private String personIsManager;
	private String personIsLegalRepresent;
	private Date createTime;
	
	public CorpCustomerRelaPerson() {
	}

	public CorpCustomerRelaPerson(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 12,scale=0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_CORP_CUSTOMER_RELA_PERSON", allocationSize = 1)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
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

	@Column(name = "STOCK_PROPORTION", precision = 12, scale = 2)
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

	@Column(name = "FAMILY_TEL_NUM", length = 30)
	public String getFamilyTelNum() {
		return this.familyTelNum;
	}
	public void setFamilyTelNum(String familyTelNum) {
		this.familyTelNum = familyTelNum;
	}

	@Column(name = "CONTACT_TEL_NUM", length = 30)
	public String getContactTelNum() {
		return this.contactTelNum;
	}
	public void setContactTelNum(String contactTelNum) {
		this.contactTelNum = contactTelNum;
	}

	@Column(name = "SEX_CD", length = 1)
	public String getSexCd() {
		return this.sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	@Column(name = "BIRTH_DATE", length = 7)
	public Date getBirthDate() {
		return this.birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "NATION", length = 30)
	public String getNation() {
		return this.nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
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

	@Column(name = "MAIL_QQ", length = 200)
	public String getMailQq() {
		return this.mailQq;
	}
	public void setMailQq(String mailQq) {
		this.mailQq = mailQq;
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

	@Column(name = "WORK_COMPANY_TYPE", length = 30)
	public String getWorkCompanyType() {
		return this.workCompanyType;
	}
	public void setWorkCompanyType(String workCompanyType) {
		this.workCompanyType = workCompanyType;
	}

	@Column(name = "WORK_COMPANY_TEL", length = 100)
	public String getWorkCompanyTel() {
		return this.workCompanyTel;
	}
	public void setWorkCompanyTel(String workCompanyTel) {
		this.workCompanyTel = workCompanyTel;
	}

	@Column(name = "PROFESSIONAL", length = 30)
	public String getProfessional() {
		return this.professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}

	@Column(name = "POSITION_CD", length = 30)
	public String getPositionCd() {
		return positionCd;
	}
	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}

	@Column(name = "COMPANY_ADDRESS", length = 200)
	public String getCompanyAddress() {
		return this.companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "INCOME_YEAR", precision = 20, scale = 2)
	public BigDecimal getIncomeYear() {
		return this.incomeYear;
	}
	public void setIncomeYear(BigDecimal incomeYear) {
		this.incomeYear = incomeYear;
	}

	@Column(name = "INCOME_BONUS_YEAR", precision = 20, scale = 2)
	public BigDecimal getIncomeBonusYear() {
		return this.incomeBonusYear;
	}
	public void setIncomeBonusYear(BigDecimal incomeBonusYear) {
		this.incomeBonusYear = incomeBonusYear;
	}

	@Column(name = "INCOME_MONTH_FACT", precision = 20, scale = 2)
	public BigDecimal getIncomeMonthFact() {
		return this.incomeMonthFact;
	}
	public void setIncomeMonthFact(BigDecimal incomeMonthFact) {
		this.incomeMonthFact = incomeMonthFact;
	}

	@Column(name = "FAMILY_TOTAL_ASSET",  precision = 20, scale = 2)
	public BigDecimal getFamilyTotalAsset() {
		return this.familyTotalAsset;
	}
	public void setFamilyTotalAsset(BigDecimal familyTotalAsset) {
		this.familyTotalAsset = familyTotalAsset;
	}

	@Column(name = "FAMILY_TOTAL_OWES", precision = 20, scale = 2)
	public BigDecimal getFamilyTotalOwes() {
		return this.familyTotalOwes;
	}
	public void setFamilyTotalOwes(BigDecimal familyTotalOwes) {
		this.familyTotalOwes = familyTotalOwes;
	}

	@Column(name = "FAMILY_MONTHLY_TOTAL_AMT", precision = 20, scale = 2)
	public BigDecimal getFamilyMonthlyTotalAmt() {
		return this.familyMonthlyTotalAmt;
	}
	public void setFamilyMonthlyTotalAmt(BigDecimal familyMonthlyTotalAmt) {
		this.familyMonthlyTotalAmt = familyMonthlyTotalAmt;
	}

	@Column(name = "FAMILY_MONTHLY_TOTAL_PAYOUT", precision = 20, scale = 2)
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

	@Column(name = "CREDIT_RECORD_OVERDUE", length = 1)
	public String getCreditRecordOverdue() {
		return this.creditRecordOverdue;
	}
	public void setCreditRecordOverdue(String creditRecordOverdue) {
		this.creditRecordOverdue = creditRecordOverdue;
	}

	@Column(name = "CREDIT_REMARK", length = 2000)
	public String getCreditRemark() {
		return this.creditRemark;
	}
	public void setCreditRemark(String creditRemark) {
		this.creditRemark = creditRemark;
	}

	@Column(name = "INVESTMENT_AMT", precision = 20, scale = 6)
	public BigDecimal getInvestmentAmt() {
		return this.investmentAmt;
	}
	public void setInvestmentAmt(BigDecimal investmentAmt) {
		this.investmentAmt = investmentAmt;
	}

	@Column(name = "INVESTMENT_CURRENCY_TYPE", length = 20)
	public String getInvestmentCurrencyType() {
		return this.investmentCurrencyType;
	}
	public void setInvestmentCurrencyType(String investmentCurrencyType) {
		this.investmentCurrencyType = investmentCurrencyType;
	}

	@Column(name = "RESIDENCE_POSITION", length = 200)
	public String getResidencePosition() {
		return this.residencePosition;
	}
	public void setResidencePosition(String residencePosition) {
		this.residencePosition = residencePosition;
	}

	@Column(name = "RESIDENCE_AREA", precision = 10, scale = 0)
	public Long getResidenceArea() {
		return this.residenceArea;
	}
	public void setResidenceArea(Long residenceArea) {
		this.residenceArea = residenceArea;
	}

	@Column(name = "RESIDENCE_PRIMARY_PRICE",  precision = 12, scale = 2)
	public BigDecimal getResidencePrimaryPrice() {
		return this.residencePrimaryPrice;
	}
	public void setResidencePrimaryPrice(BigDecimal residencePrimaryPrice) {
		this.residencePrimaryPrice = residencePrimaryPrice;
	}
	
	@Column(name = "RESIDENCE_NOW_PRICE",  precision = 12, scale = 2)
	public BigDecimal getResidenceNowPrice() {
		return this.residenceNowPrice;
	}
	public void setResidenceNowPrice(BigDecimal residenceNowPrice) {
		this.residenceNowPrice = residenceNowPrice;
	}
	
	@Column(name = "PERSON_IS_STOCK_HOLDER", length = 1)
	public String getPersonIsStockHolder() {
		return this.personIsStockHolder;
	}
	public void setPersonIsStockHolder(String personIsStockHolder) {
		this.personIsStockHolder = personIsStockHolder;
	}
	
	@Column(name = "PERSON_IS_ACT_CONTROLLER", length = 1)
	public String getPersonIsActController() {
		return this.personIsActController;
	}
	public void setPersonIsActController(String personIsActController) {
		this.personIsActController = personIsActController;
	}
	
	@Column(name = "PERSON_IS_MANAGER", length = 1)
	public String getPersonIsManager() {
		return this.personIsManager;
	}
	public void setPersonIsManager(String personIsManager) {
		this.personIsManager = personIsManager;
	}
	
	@Column(name = "PERSON_IS_LEGAL_REPRESENT", length = 1)
	public String getPersonIsLegalRepresent() {
		return this.personIsLegalRepresent;
	}
	public void setPersonIsLegalRepresent(String personIsLegalRepresent) {
		this.personIsLegalRepresent = personIsLegalRepresent;
	}
	
	@Column(name = "CREATE_TIME", length = 11)
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "MARRIAGE_STATUS", length = 2)
	public String getMarriageCd() {
		return marriageCd;
	}

	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}

}