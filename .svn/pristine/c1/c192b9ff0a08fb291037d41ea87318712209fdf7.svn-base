package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.entity.BaseEntity;

@Entity
@Table(name = "CORPORATION_CUSTOMER", schema = WD_SCHEMA)
public class CorporationCustomer extends BaseEntity implements java.io.Serializable {

	private Long partyId;
	private String customerNum;
	private String customerName;
	/***行业类别*/
	private String industryLevelTwoCd;
	/***所有制类别*/
	private String orgTypeCd;
	/***营业执照号码 */
	private String businessLicenseNum;
	/**注册资本货币类型*/
	private String registerCapitalCurrencyCd;
	/**注册资本*/
	private BigDecimal registeredCapital;
	/***贷款卡号码*/
	private String loanCardNum;
	/***税务登记证号（国税）*/
	private String nationalTaxRegistrationNum;
	/***税务登记证号（地税）*/
	private String localTaxRegistrationCum;
	private String linkmanTel;
	/***员工人数*/
	private Integer staffNum;
	private Date createDate;
	private Date sysUpdateTime;
	/***组织机构代码*/
	private String certificateNum;
	private String linkmanName;
	private String states;
	/***资产规模*/
	private String capitalScale;
	private String linkmanFax;
	/***最新维护人名称*/
	private String lastUpdateUserNum;
	/***客户规模*/
	private String customerScale;
	/***公司成立时间*/
	private Date standedDate;
	private BigDecimal receivedRate;
	/***实收资本*/
	private BigDecimal actualRevAmt;
	/***最近维护日期*/
	private Date newlyMaintenanceDate;
	/***客户来源*/
	private String customerSource;
	/***评级行业类型*/
	private String ratingIndustryType;
	/***企业客户特征*/
	private String corCustomerFeature;
	/***结算帐户帐号1*/
	private String settlementAccountsFirst;
	/***结算帐户帐号2*/
	private String settlementAccountsSecond;
	/***结算帐户帐号开户行1*/
	private String settlementAccountsOne;
	/***结算帐户帐号开户行2*/
	private String settlementAccountsTwo;
	private String statusFlag;
	/***借款(01),保证人(02)*/
	private String markType;


	public CorporationCustomer() {
	}

	public CorporationCustomer(Long partyId) {
		this.partyId = partyId;
	}

	@Id
	@Column(name = "PARTY_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CUSTOMER_NUM", length = 30)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "CUSTOMER_NAME", length = 200)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "INDUSTRY_LEVEL_TWO_CD", length = 30)
	public String getIndustryLevelTwoCd() {
		return this.industryLevelTwoCd;
	}

	public void setIndustryLevelTwoCd(String industryLevelTwoCd) {
		this.industryLevelTwoCd = industryLevelTwoCd;
	}

	@Column(name = "ORG_TYPE_CD", length = 30)
	public String getOrgTypeCd() {
		return this.orgTypeCd;
	}

	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}

	@Column(name = "BUSINESS_LICENSE_NUM", length = 50)
	public String getBusinessLicenseNum() {
		return this.businessLicenseNum;
	}

	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
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

	@Column(name = "LOAN_CARD_NUM", length = 30)
	public String getLoanCardNum() {
		return this.loanCardNum;
	}

	public void setLoanCardNum(String loanCardNum) {
		this.loanCardNum = loanCardNum;
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

	@Column(name = "LINKMAN_TEL", length = 20)
	public String getLinkmanTel() {
		return this.linkmanTel;
	}

	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}

	@Column(name = "STAFF_NUM", precision = 8, scale = 0)
	public Integer getStaffNum() {
		return this.staffNum;
	}

	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}

	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "SYS_UPDATE_TIME", length = 7)
	public Date getSysUpdateTime() {
		return this.sysUpdateTime;
	}

	public void setSysUpdateTime(Timestamp sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	@Column(name = "CERTIFICATE_NUM", length = 30)
	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	@Column(name = "LINKMAN_NAME", length = 100)
	public String getLinkmanName() {
		return this.linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	@Column(name = "STATES", length = 30)
	public String getStates() {
		return this.states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	@Column(name = "CAPITAL_SCALE", length = 20)
	public String getCapitalScale() {
		return this.capitalScale;
	}

	public void setCapitalScale(String capitalScale) {
		this.capitalScale = capitalScale;
	}

	@Column(name = "LINKMAN_FAX", length = 18)
	public String getLinkmanFax() {
		return this.linkmanFax;
	}

	public void setLinkmanFax(String linkmanFax) {
		this.linkmanFax = linkmanFax;
	}

	@Column(name = "SETTLEMENT_ACCOUNTS_FIRST", length = 100)
	public String getSettlementAccountsFirst() {
		return this.settlementAccountsFirst;
	}

	public void setSettlementAccountsFirst(String settlementAccountsFirst) {
		this.settlementAccountsFirst = settlementAccountsFirst;
	}

	@Column(name = "SETTLEMENT_ACCOUNTS_SECOND", length = 100)
	public String getSettlementAccountsSecond() {
		return this.settlementAccountsSecond;
	}

	public void setSettlementAccountsSecond(String settlementAccountsSecond) {
		this.settlementAccountsSecond = settlementAccountsSecond;
	}

	@Column(name = "LAST_UPDATE_USER_NUM", length = 40)
	public String getLastUpdateUserNum() {
		return this.lastUpdateUserNum;
	}

	public void setLastUpdateUserNum(String lastUpdateUserNum) {
		this.lastUpdateUserNum = lastUpdateUserNum;
	}

	@Column(name = "CUSTOMER_SCALE", length = 20)
	public String getCustomerScale() {
		return this.customerScale;
	}

	public void setCustomerScale(String customerScale) {
		this.customerScale = customerScale;
	}

	@Column(name = "STANDED_DATE", length = 7)
	public Date getStandedDate() {
		return this.standedDate;
	}

	public void setStandedDate(Date standedDate) {
		this.standedDate = standedDate;
	}

	@Column(name = "RECEIVED_RATE", precision = 20)
	public BigDecimal getReceivedRate() {
		return this.receivedRate;
	}

	public void setReceivedRate(BigDecimal receivedRate) {
		this.receivedRate = receivedRate;
	}

	@Column(name = "ACTUAL_REV_AMT", precision = 20)
	public BigDecimal getActualRevAmt() {
		return this.actualRevAmt;
	}

	public void setActualRevAmt(BigDecimal actualRevAmt) {
		this.actualRevAmt = actualRevAmt;
	}

	@Column(name = "NEWLY_MAINTENANCE_DATE", length = 7)
	public Date getNewlyMaintenanceDate() {
		return this.newlyMaintenanceDate;
	}

	public void setNewlyMaintenanceDate(Date newlyMaintenanceDate) {
		this.newlyMaintenanceDate = newlyMaintenanceDate;
	}

	@Column(name = "CUSTOMER_SOURCE", length = 30)
	public String getCustomerSource() {
		return this.customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	@Column(name = "RATING_INDUSTRY_TYPE", length = 20)
	public String getRatingIndustryType() {
		return this.ratingIndustryType;
	}

	public void setRatingIndustryType(String ratingIndustryType) {
		this.ratingIndustryType = ratingIndustryType;
	}

	@Column(name = "COR_CUSTOMER_FEATURE", length = 20)
	public String getCorCustomerFeature() {
		return this.corCustomerFeature;
	}

	public void setCorCustomerFeature(String corCustomerFeature) {
		this.corCustomerFeature = corCustomerFeature;
	}

	@Column(name = "SETTLEMENT_ACCOUNTS_ONE", length = 100)
	public String getSettlementAccountsOne() {
		return this.settlementAccountsOne;
	}

	public void setSettlementAccountsOne(String settlementAccountsOne) {
		this.settlementAccountsOne = settlementAccountsOne;
	}

	@Column(name = "SETTLEMENT_ACCOUNTS_TWO", length = 100)
	public String getSettlementAccountsTwo() {
		return this.settlementAccountsTwo;
	}

	public void setSettlementAccountsTwo(String settlementAccountsTwo) {
		this.settlementAccountsTwo = settlementAccountsTwo;
	}

	@Column(name = "STATUS_FLAG", length = 20)
	public String getStatusFlag() {
		return this.statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	@Column(name = "MARK_TYPE", length = 20)
	public String getMarkType() {
		return this.markType;
	}

	public void setMarkType(String markType) {
		this.markType = markType;
	}

}