package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * AssTranProjectApplication entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ASS_TRAN_PROJECT_APPLICATION", schema=WD_SCHEMA)
public class AssTranProjectApplication implements java.io.Serializable {

	// Fields

	private Long projectId;
	private String projectNo;
	private String businessType;
	private Long partyId;
	private String customerNum;
	private String customerName;
	private String projectStatus;
	private String customerManagerNum;
	private String customerManagerName;
	private String applicant;
	private String applyRole;
	private String applyOrg;
	private Timestamp applyDate;
	private String bpInfoStatus;
	private Long bpInfoId;
	private String currentApproveNum;
	private String currentApproveRole;
	private String currentApproveOrg;
	private String businessProcessType;
	private String productType;
	private BigDecimal applyAmt;
	private String currency;
	private BigDecimal term;
	private String termUnit;
	private String purpose;
	private String payment;
	private Long parentProjectId;
	private Timestamp sysUpdateTime;
	private String certificateTypeCd;
	private String certificateNum;
	private Long waitAssignId;
	private String investmentIndustry;
	private String agricultureInd;
	private String guaranteeMode;
	private String repayingMode;
	private BigDecimal replyPeriodNum;
	private String riskManageUserNum;
	private String description;
	private BigDecimal approveAmt;
	private Timestamp sysCreateTime;
	private BigDecimal applyTerm;
	private BigDecimal renewalAmt;
	private BigDecimal renewalTerm;
	private String renewalTermUnit;
	private String applyTermUnit;
	private String blackListInd;
	private BigDecimal preRepaymentRate;
	private BigDecimal guaranteeAmt;
	private BigDecimal bizRate;
	private BigDecimal ovdueRate;
	private BigDecimal perculNegoRate;
	private Timestamp approveDate;
	private String orientedCustomerType;
	private String loanInvestFeature;
	private String loanPurposeFeature;
	private String areaType;
	private Long latestWorkitemid;
	private String approveRepayingMode;
	private String mateBorrower;
	private Long initProjectId;
	private Long projectIndex;
	private String renewalReason;
	private Integer arrangeRepayDay;
	private Timestamp renewStartDate;
	private String initContractNum;
	private String loanDateStyle;
	private String agreementNo;
	private String rateValue;
	private String assetPackageName;
	private BigDecimal assetPackageScale;
	private BigDecimal transferPrice;
	private String commissionedParty;
	private String acquiringParty;
	private String transferor;
	private BigDecimal utilizationOfFunds;
	private String guarantor;
	private String assetsOperationType;
	private BigDecimal paymentCycle;
	private String assetPackageNo;
	private String status;
	private String transferorPayCycle;
	private String paymentCycleUnit;
	private String commissionway;
	private String entrustbank;
	private String assetNotion;
	private String fpoolProjectContractopinion;
	private BigDecimal fpoolAmt;
	private String conclusionOpinion;
	private String ifWhileBuy;
	private String buyTerm;
	private String buyDate;
	private String bussNature;
	private String whetherPremium;
	private BigDecimal orgrepayamt;

	// Constructors

	/** default constructor */
	public AssTranProjectApplication() {
	}

	/** minimal constructor */
	public AssTranProjectApplication(Long projectId) {
		this.projectId = projectId;
	}

	

	// Property accessors
	@Id
	@Column(name = "PROJECT_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROJECT_NO", length = 30)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "BUSINESS_TYPE", length = 30)
	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
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

	@Column(name = "CUSTOMER_NAME", length = 100)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "PROJECT_STATUS", length = 30)
	public String getProjectStatus() {
		return this.projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	@Column(name = "CUSTOMER_MANAGER_NUM", length = 20)
	public String getCustomerManagerNum() {
		return this.customerManagerNum;
	}

	public void setCustomerManagerNum(String customerManagerNum) {
		this.customerManagerNum = customerManagerNum;
	}

	@Column(name = "CUSTOMER_MANAGER_NAME", length = 100)
	public String getCustomerManagerName() {
		return this.customerManagerName;
	}

	public void setCustomerManagerName(String customerManagerName) {
		this.customerManagerName = customerManagerName;
	}

	@Column(name = "APPLICANT", length = 50)
	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	@Column(name = "APPLY_ROLE", length = 30)
	public String getApplyRole() {
		return this.applyRole;
	}

	public void setApplyRole(String applyRole) {
		this.applyRole = applyRole;
	}

	@Column(name = "APPLY_ORG", length = 30)
	public String getApplyOrg() {
		return this.applyOrg;
	}

	public void setApplyOrg(String applyOrg) {
		this.applyOrg = applyOrg;
	}

	@Column(name = "APPLY_DATE", length = 7)
	public Timestamp getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}

	@Column(name = "BP_INFO_STATUS", length = 30)
	public String getBpInfoStatus() {
		return this.bpInfoStatus;
	}

	public void setBpInfoStatus(String bpInfoStatus) {
		this.bpInfoStatus = bpInfoStatus;
	}

	@Column(name = "BP_INFO_ID", precision = 12, scale = 0)
	public Long getBpInfoId() {
		return this.bpInfoId;
	}

	public void setBpInfoId(Long bpInfoId) {
		this.bpInfoId = bpInfoId;
	}

	@Column(name = "CURRENT_APPROVE_NUM", length = 30)
	public String getCurrentApproveNum() {
		return this.currentApproveNum;
	}

	public void setCurrentApproveNum(String currentApproveNum) {
		this.currentApproveNum = currentApproveNum;
	}

	@Column(name = "CURRENT_APPROVE_ROLE", length = 30)
	public String getCurrentApproveRole() {
		return this.currentApproveRole;
	}

	public void setCurrentApproveRole(String currentApproveRole) {
		this.currentApproveRole = currentApproveRole;
	}

	@Column(name = "CURRENT_APPROVE_ORG", length = 30)
	public String getCurrentApproveOrg() {
		return this.currentApproveOrg;
	}

	public void setCurrentApproveOrg(String currentApproveOrg) {
		this.currentApproveOrg = currentApproveOrg;
	}

	@Column(name = "BUSINESS_PROCESS_TYPE", length = 30)
	public String getBusinessProcessType() {
		return this.businessProcessType;
	}

	public void setBusinessProcessType(String businessProcessType) {
		this.businessProcessType = businessProcessType;
	}

	@Column(name = "PRODUCT_TYPE", length = 30)
	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(name = "APPLY_AMT", precision = 20)
	public BigDecimal getApplyAmt() {
		return this.applyAmt;
	}

	public void setApplyAmt(BigDecimal applyAmt) {
		this.applyAmt = applyAmt;
	}

	@Column(name = "CURRENCY", length = 30)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "TERM", precision = 22, scale = 0)
	public BigDecimal getTerm() {
		return this.term;
	}

	public void setTerm(BigDecimal term) {
		this.term = term;
	}

	@Column(name = "TERM_UNIT", length = 30)
	public String getTermUnit() {
		return this.termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	@Column(name = "PURPOSE", length = 500)
	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Column(name = "PAYMENT", length = 500)
	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Column(name = "PARENT_PROJECT_ID", precision = 12, scale = 0)
	public Long getParentProjectId() {
		return this.parentProjectId;
	}

	public void setParentProjectId(Long parentProjectId) {
		this.parentProjectId = parentProjectId;
	}

	@Column(name = "SYS_UPDATE_TIME", length = 7)
	public Timestamp getSysUpdateTime() {
		return this.sysUpdateTime;
	}

	public void setSysUpdateTime(Timestamp sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	@Column(name = "CERTIFICATE_TYPE_CD", length = 30)
	public String getCertificateTypeCd() {
		return this.certificateTypeCd;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

	@Column(name = "CERTIFICATE_NUM", length = 30)
	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	@Column(name = "WAIT_ASSIGN_ID", precision = 12, scale = 0)
	public Long getWaitAssignId() {
		return this.waitAssignId;
	}

	public void setWaitAssignId(Long waitAssignId) {
		this.waitAssignId = waitAssignId;
	}

	@Column(name = "INVESTMENT_INDUSTRY", length = 20)
	public String getInvestmentIndustry() {
		return this.investmentIndustry;
	}

	public void setInvestmentIndustry(String investmentIndustry) {
		this.investmentIndustry = investmentIndustry;
	}

	@Column(name = "AGRICULTURE_IND", length = 1)
	public String getAgricultureInd() {
		return this.agricultureInd;
	}

	public void setAgricultureInd(String agricultureInd) {
		this.agricultureInd = agricultureInd;
	}

	@Column(name = "GUARANTEE_MODE", length = 20)
	public String getGuaranteeMode() {
		return this.guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	@Column(name = "REPAYING_MODE", length = 20)
	public String getRepayingMode() {
		return this.repayingMode;
	}

	public void setRepayingMode(String repayingMode) {
		this.repayingMode = repayingMode;
	}

	@Column(name = "REPLY_PERIOD_NUM", precision = 22, scale = 0)
	public BigDecimal getReplyPeriodNum() {
		return this.replyPeriodNum;
	}

	public void setReplyPeriodNum(BigDecimal replyPeriodNum) {
		this.replyPeriodNum = replyPeriodNum;
	}

	@Column(name = "RISK_MANAGE_USER_NUM", length = 20)
	public String getRiskManageUserNum() {
		return this.riskManageUserNum;
	}

	public void setRiskManageUserNum(String riskManageUserNum) {
		this.riskManageUserNum = riskManageUserNum;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "APPROVE_AMT", precision = 20)
	public BigDecimal getApproveAmt() {
		return this.approveAmt;
	}

	public void setApproveAmt(BigDecimal approveAmt) {
		this.approveAmt = approveAmt;
	}

	@Column(name = "SYS_CREATE_TIME", length = 7)
	public Timestamp getSysCreateTime() {
		return this.sysCreateTime;
	}

	public void setSysCreateTime(Timestamp sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	@Column(name = "APPLY_TERM", precision = 22, scale = 0)
	public BigDecimal getApplyTerm() {
		return this.applyTerm;
	}

	public void setApplyTerm(BigDecimal applyTerm) {
		this.applyTerm = applyTerm;
	}

	@Column(name = "RENEWAL_AMT", precision = 20)
	public BigDecimal getRenewalAmt() {
		return this.renewalAmt;
	}

	public void setRenewalAmt(BigDecimal renewalAmt) {
		this.renewalAmt = renewalAmt;
	}

	@Column(name = "RENEWAL_TERM", precision = 22, scale = 0)
	public BigDecimal getRenewalTerm() {
		return this.renewalTerm;
	}

	public void setRenewalTerm(BigDecimal renewalTerm) {
		this.renewalTerm = renewalTerm;
	}

	@Column(name = "RENEWAL_TERM_UNIT", length = 20)
	public String getRenewalTermUnit() {
		return this.renewalTermUnit;
	}

	public void setRenewalTermUnit(String renewalTermUnit) {
		this.renewalTermUnit = renewalTermUnit;
	}

	@Column(name = "APPLY_TERM_UNIT")
	public String getApplyTermUnit() {
		return this.applyTermUnit;
	}

	public void setApplyTermUnit(String applyTermUnit) {
		this.applyTermUnit = applyTermUnit;
	}

	@Column(name = "BLACK_LIST_IND", length = 1)
	public String getBlackListInd() {
		return this.blackListInd;
	}

	public void setBlackListInd(String blackListInd) {
		this.blackListInd = blackListInd;
	}

	@Column(name = "PRE_REPAYMENT_RATE", precision = 20, scale = 6)
	public BigDecimal getPreRepaymentRate() {
		return this.preRepaymentRate;
	}

	public void setPreRepaymentRate(BigDecimal preRepaymentRate) {
		this.preRepaymentRate = preRepaymentRate;
	}

	@Column(name = "GUARANTEE_AMT", precision = 20)
	public BigDecimal getGuaranteeAmt() {
		return this.guaranteeAmt;
	}

	public void setGuaranteeAmt(BigDecimal guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
	}

	@Column(name = "BIZ_RATE", precision = 20, scale = 6)
	public BigDecimal getBizRate() {
		return this.bizRate;
	}

	public void setBizRate(BigDecimal bizRate) {
		this.bizRate = bizRate;
	}

	@Column(name = "OVDUE_RATE", precision = 20, scale = 6)
	public BigDecimal getOvdueRate() {
		return this.ovdueRate;
	}

	public void setOvdueRate(BigDecimal ovdueRate) {
		this.ovdueRate = ovdueRate;
	}

	@Column(name = "PERCUL_NEGO_RATE", precision = 20, scale = 6)
	public BigDecimal getPerculNegoRate() {
		return this.perculNegoRate;
	}

	public void setPerculNegoRate(BigDecimal perculNegoRate) {
		this.perculNegoRate = perculNegoRate;
	}

	@Column(name = "APPROVE_DATE", length = 7)
	public Timestamp getApproveDate() {
		return this.approveDate;
	}

	public void setApproveDate(Timestamp approveDate) {
		this.approveDate = approveDate;
	}

	@Column(name = "ORIENTED_CUSTOMER_TYPE", length = 20)
	public String getOrientedCustomerType() {
		return this.orientedCustomerType;
	}

	public void setOrientedCustomerType(String orientedCustomerType) {
		this.orientedCustomerType = orientedCustomerType;
	}

	@Column(name = "LOAN_INVEST_FEATURE", length = 20)
	public String getLoanInvestFeature() {
		return this.loanInvestFeature;
	}

	public void setLoanInvestFeature(String loanInvestFeature) {
		this.loanInvestFeature = loanInvestFeature;
	}

	@Column(name = "LOAN_PURPOSE_FEATURE", length = 20)
	public String getLoanPurposeFeature() {
		return this.loanPurposeFeature;
	}

	public void setLoanPurposeFeature(String loanPurposeFeature) {
		this.loanPurposeFeature = loanPurposeFeature;
	}

	@Column(name = "AREA_TYPE", length = 20)
	public String getAreaType() {
		return this.areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	@Column(name = "LATEST_WORKITEMID", precision = 12, scale = 0)
	public Long getLatestWorkitemid() {
		return this.latestWorkitemid;
	}

	public void setLatestWorkitemid(Long latestWorkitemid) {
		this.latestWorkitemid = latestWorkitemid;
	}

	@Column(name = "APPROVE_REPAYING_MODE", length = 20)
	public String getApproveRepayingMode() {
		return this.approveRepayingMode;
	}

	public void setApproveRepayingMode(String approveRepayingMode) {
		this.approveRepayingMode = approveRepayingMode;
	}

	@Column(name = "MATE_BORROWER", length = 1)
	public String getMateBorrower() {
		return this.mateBorrower;
	}

	public void setMateBorrower(String mateBorrower) {
		this.mateBorrower = mateBorrower;
	}

	@Column(name = "INIT_PROJECT_ID", precision = 12, scale = 0)
	public Long getInitProjectId() {
		return this.initProjectId;
	}

	public void setInitProjectId(Long initProjectId) {
		this.initProjectId = initProjectId;
	}

	@Column(name = "PROJECT_INDEX", precision = 12, scale = 0)
	public Long getProjectIndex() {
		return this.projectIndex;
	}

	public void setProjectIndex(Long projectIndex) {
		this.projectIndex = projectIndex;
	}

	@Column(name = "RENEWAL_REASON", length = 4000)
	public String getRenewalReason() {
		return this.renewalReason;
	}

	public void setRenewalReason(String renewalReason) {
		this.renewalReason = renewalReason;
	}

	@Column(name = "ARRANGE_REPAY_DAY", precision = 6, scale = 0)
	public Integer getArrangeRepayDay() {
		return this.arrangeRepayDay;
	}

	public void setArrangeRepayDay(Integer arrangeRepayDay) {
		this.arrangeRepayDay = arrangeRepayDay;
	}

	@Column(name = "RENEW_START_DATE", length = 7)
	public Timestamp getRenewStartDate() {
		return this.renewStartDate;
	}

	public void setRenewStartDate(Timestamp renewStartDate) {
		this.renewStartDate = renewStartDate;
	}

	@Column(name = "INIT_CONTRACT_NUM", length = 30)
	public String getInitContractNum() {
		return this.initContractNum;
	}

	public void setInitContractNum(String initContractNum) {
		this.initContractNum = initContractNum;
	}

	@Column(name = "LOAN_DATE_STYLE", length = 20)
	public String getLoanDateStyle() {
		return this.loanDateStyle;
	}

	public void setLoanDateStyle(String loanDateStyle) {
		this.loanDateStyle = loanDateStyle;
	}

	@Column(name = "AGREEMENT_NO", length = 20)
	public String getAgreementNo() {
		return this.agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	@Column(name = "RATE_VALUE", length = 20)
	public String getRateValue() {
		return this.rateValue;
	}

	public void setRateValue(String rateValue) {
		this.rateValue = rateValue;
	}

	@Column(name = "ASSET_PACKAGE_NAME", length = 20)
	public String getAssetPackageName() {
		return this.assetPackageName;
	}

	public void setAssetPackageName(String assetPackageName) {
		this.assetPackageName = assetPackageName;
	}

	@Column(name = "ASSET_PACKAGE_SCALE", precision = 20)
	public BigDecimal getAssetPackageScale() {
		return this.assetPackageScale;
	}

	public void setAssetPackageScale(BigDecimal assetPackageScale) {
		this.assetPackageScale = assetPackageScale;
	}

	@Column(name = "TRANSFER_PRICE", precision = 20)
	public BigDecimal getTransferPrice() {
		return this.transferPrice;
	}

	public void setTransferPrice(BigDecimal transferPrice) {
		this.transferPrice = transferPrice;
	}

	@Column(name = "COMMISSIONED_PARTY", length = 100)
	public String getCommissionedParty() {
		return this.commissionedParty;
	}

	public void setCommissionedParty(String commissionedParty) {
		this.commissionedParty = commissionedParty;
	}

	@Column(name = "ACQUIRING_PARTY", length = 1000)
	public String getAcquiringParty() {
		return this.acquiringParty;
	}

	public void setAcquiringParty(String acquiringParty) {
		this.acquiringParty = acquiringParty;
	}

	@Column(name = "TRANSFEROR", length = 1000)
	public String getTransferor() {
		return this.transferor;
	}

	public void setTransferor(String transferor) {
		this.transferor = transferor;
	}

	@Column(name = "UTILIZATION_OF_FUNDS", precision = 20, scale = 6)
	public BigDecimal getUtilizationOfFunds() {
		return this.utilizationOfFunds;
	}

	public void setUtilizationOfFunds(BigDecimal utilizationOfFunds) {
		this.utilizationOfFunds = utilizationOfFunds;
	}

	@Column(name = "GUARANTOR", length = 20)
	public String getGuarantor() {
		return this.guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}

	@Column(name = "ASSETS_OPERATION_TYPE", length = 20)
	public String getAssetsOperationType() {
		return this.assetsOperationType;
	}

	public void setAssetsOperationType(String assetsOperationType) {
		this.assetsOperationType = assetsOperationType;
	}

	@Column(name = "PAYMENT_CYCLE", precision = 22, scale = 0)
	public BigDecimal getPaymentCycle() {
		return this.paymentCycle;
	}

	public void setPaymentCycle(BigDecimal paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	@Column(name = "ASSET_PACKAGE_NO", length = 20)
	public String getAssetPackageNo() {
		return this.assetPackageNo;
	}

	public void setAssetPackageNo(String assetPackageNo) {
		this.assetPackageNo = assetPackageNo;
	}

	@Column(name = "STATUS", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "TRANSFEROR_PAY_CYCLE", length = 30)
	public String getTransferorPayCycle() {
		return this.transferorPayCycle;
	}

	public void setTransferorPayCycle(String transferorPayCycle) {
		this.transferorPayCycle = transferorPayCycle;
	}

	@Column(name = "PAYMENT_CYCLE_UNIT", length = 30)
	public String getPaymentCycleUnit() {
		return this.paymentCycleUnit;
	}

	public void setPaymentCycleUnit(String paymentCycleUnit) {
		this.paymentCycleUnit = paymentCycleUnit;
	}

	@Column(name = "COMMISSIONWAY", length = 20)
	public String getCommissionway() {
		return this.commissionway;
	}

	public void setCommissionway(String commissionway) {
		this.commissionway = commissionway;
	}

	@Column(name = "ENTRUSTBANK", length = 120)
	public String getEntrustbank() {
		return this.entrustbank;
	}

	public void setEntrustbank(String entrustbank) {
		this.entrustbank = entrustbank;
	}

	@Column(name = "ASSET_NOTION", length = 1200)
	public String getAssetNotion() {
		return this.assetNotion;
	}

	public void setAssetNotion(String assetNotion) {
		this.assetNotion = assetNotion;
	}

	@Column(name = "FPOOL_PROJECT_CONTRACTOPINION", length = 1200)
	public String getFpoolProjectContractopinion() {
		return this.fpoolProjectContractopinion;
	}

	public void setFpoolProjectContractopinion(
			String fpoolProjectContractopinion) {
		this.fpoolProjectContractopinion = fpoolProjectContractopinion;
	}

	@Column(name = "FPOOL_AMT", precision = 20, scale = 6)
	public BigDecimal getFpoolAmt() {
		return this.fpoolAmt;
	}

	public void setFpoolAmt(BigDecimal fpoolAmt) {
		this.fpoolAmt = fpoolAmt;
	}

	@Column(name = "CONCLUSION_OPINION", length = 1200)
	public String getConclusionOpinion() {
		return this.conclusionOpinion;
	}

	public void setConclusionOpinion(String conclusionOpinion) {
		this.conclusionOpinion = conclusionOpinion;
	}

	@Column(name = "IF_WHILE_BUY", length = 20)
	public String getIfWhileBuy() {
		return this.ifWhileBuy;
	}

	public void setIfWhileBuy(String ifWhileBuy) {
		this.ifWhileBuy = ifWhileBuy;
	}

	@Column(name = "BUY_TERM", length = 20)
	public String getBuyTerm() {
		return this.buyTerm;
	}

	public void setBuyTerm(String buyTerm) {
		this.buyTerm = buyTerm;
	}

	@Column(name = "BUY_DATE", length = 20)
	public String getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	@Column(name = "BUSS_NATURE", length = 20)
	public String getBussNature() {
		return this.bussNature;
	}

	public void setBussNature(String bussNature) {
		this.bussNature = bussNature;
	}

	@Column(name = "WHETHER_PREMIUM", length = 20)
	public String getWhetherPremium() {
		return this.whetherPremium;
	}

	public void setWhetherPremium(String whetherPremium) {
		this.whetherPremium = whetherPremium;
	}

	@Column(name = "ORGREPAYAMT", precision = 20, scale = 6)
	public BigDecimal getOrgrepayamt() {
		return this.orgrepayamt;
	}

	public void setOrgrepayamt(BigDecimal orgrepayamt) {
		this.orgrepayamt = orgrepayamt;
	}


}