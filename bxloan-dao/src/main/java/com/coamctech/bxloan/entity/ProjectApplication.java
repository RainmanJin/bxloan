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
import javax.persistence.Transient;

/**
 * ProjectApplication entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROJECT_APPLICATION", schema = WD_SCHEMA)
public class ProjectApplication implements java.io.Serializable {
	// Fields

	private Long projectId;
	private String projectNo;
	private String businessType;
	private Long partyId; // 客户id
	private String customerNum; // 客户编号
	private String customerName;//客户姓名
	private String projectStatus;
	private String customerManagerNum;
	private String customerManagerName;
	private String applicant;
	private String applyRole;
	private String applyOrg;
	private Date applyDate;
	private String bpInfoStatus;
	private Long bpInfoId;
	private String currentApproveNum;
	private String currentApproveRole;
	private String currentApproveOrg;
	private String businessProcessType;
	private String productType; // 贷款产品
	private BigDecimal applyAmt; // 申请金额
	private String currency; // 币种
	private Integer term;
	private String termUnit;
	private String purpose;//还款說明
	private String payment;//还款来源
	private Long parentProjectId;
	private Date sysUpdateTime;
	private String certificateTypeCd;//证件类型
	private String certificateNum;//证件号码
	private Long waitAssignId;
	private String investmentIndustry;//投放行业
	private String agricultureInd;//是否涉农
	private String guaranteeMode;
	private String repayingMode;//还款方式
	private Integer replyPeriodNum;//还款周期月数
	private String riskManageUserNum;
	private String description;
	private BigDecimal approveAmt;
	private Date sysCreateTime; // 申请时间
	private Integer applyTerm;//申请期限
	private BigDecimal renewalAmt;
	private Integer renewalTerm;
	private String renewalTermUnit;
	private String applyTermUnit;//贷款期限单位
	private String blackListInd;
	private BigDecimal preRepaymentRate;//提前还款违约金比例
	private BigDecimal guaranteeAmt;
	private BigDecimal bizRate;//年利率
	private BigDecimal ovdueRate;
	private BigDecimal perculNegoRate;
	private Date approveDate;
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
	private Date renewStartDate;
	private String initContractNum;
	private String loanDateStyle;
	private String unId;//联保体id
	private String loanPurposeKind;
	private Long workflowId;
	private String ifInsure;// 是否保险
	private String insuranceOrgId;// 保险机构
	private BigDecimal prePremium;// 预收保费
	private String isheadcol;// 是否总部协同业务
	private String applyDateStr;// 申请日期字串
	private String sysCd;// 系统标识：空小贷、1微贷、2移动端
	private String projectBusinessType;// 项目业务类型 1：普通业务；2：授信业务
	private String argoBizType;// 农业非农业类型1：农业；2：非农业
	private String assistance;//协办客户经理id
	//add by mz 20150710 start
	/** 授信类型 */
	private String creditType;
	
	/** 授信类型-批复阶段反显上一环节批复信息使用(临时字段，不参与业务逻辑处理) */
	private String tempCreditType;
	//add by mz 20150710 end

	/** default constructor */
	public ProjectApplication() {
	}

	/** minimal constructor */
	public ProjectApplication(Long projectId) {
		this.projectId = projectId;
	}

	

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_PROJECT_APPLICATION", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
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
	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
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

	@Column(name = "TERM")
	public Integer getTerm() {
		return this.term;
	}

	public void setTerm(Integer term) {
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
	public Date getSysUpdateTime() {
		return this.sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
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

	@Column(name = "REPLY_PERIOD_NUM")
	public Integer getReplyPeriodNum() {
		return this.replyPeriodNum;
	}

	public void setReplyPeriodNum(Integer replyPeriodNum) {
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
	public Date getSysCreateTime() {
		return this.sysCreateTime;
	}

	public void setSysCreateTime(Date sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	@Column(name = "APPLY_TERM")
	public Integer getApplyTerm() {
		return this.applyTerm;
	}

	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}

	@Column(name = "RENEWAL_AMT", precision = 20)
	public BigDecimal getRenewalAmt() {
		return this.renewalAmt;
	}

	public void setRenewalAmt(BigDecimal renewalAmt) {
		this.renewalAmt = renewalAmt;
	}

	@Column(name = "RENEWAL_TERM")
	public Integer getRenewalTerm() {
		return this.renewalTerm;
	}

	public void setRenewalTerm(Integer renewalTerm) {
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
	public Date getApproveDate() {
		return this.approveDate;
	}

	public void setApproveDate(Date approveDate) {
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
	public Date getRenewStartDate() {
		return this.renewStartDate;
	}

	public void setRenewStartDate(Date renewStartDate) {
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

	@Column(name = "UN_ID", length = 20)
	public String getUnId() {
		return this.unId;
	}

	public void setUnId(String unId) {
		this.unId = unId;
	}

	@Column(name = "LOAN_PURPOSE_KIND", length = 1)
	public String getLoanPurposeKind() {
		return this.loanPurposeKind;
	}

	public void setLoanPurposeKind(String loanPurposeKind) {
		this.loanPurposeKind = loanPurposeKind;
	}

	@Column(name = "WORKFLOW_ID", precision = 12, scale = 0)
	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	@Column(name = "ISHEADCOL", length = 1)
	public String getIsheadcol() {
		return this.isheadcol;
	}

	public void setIsheadcol(String isheadcol) {
		this.isheadcol = isheadcol;
	}

	@Transient
	public String getApplyDateStr() {
		return applyDateStr;
	}

	public void setApplyDateStr(String applyDateStr) {
		this.applyDateStr = applyDateStr;
	}

	@Column(name = "SYS_CD", length = 1)
	public String getSysCd() {
		return sysCd;
	}

	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}

	@Column(name = "IF_INSURE", length = 2)
	public String getIfInsure() {
		return ifInsure;
	}

	public void setIfInsure(String ifInsure) {
		this.ifInsure = ifInsure;
	}

	@Column(name = "INSURANCE_ORG_ID", length = 30)
	public String getInsuranceOrgId() {
		return this.insuranceOrgId;
	}

	public void setInsuranceOrgId(String insuranceOrgId) {
		this.insuranceOrgId = insuranceOrgId;
	}

	@Column(name = "PRE_PREMIUM", precision = 20)
	public BigDecimal getPrePremium() {
		return this.prePremium;
	}

	public void setPrePremium(BigDecimal prePremium) {
		this.prePremium = prePremium;
	}

	@Column(name = "PROJECT_BUSINESS_TYPE", precision = 10)
	public String getProjectBusinessType() {
		return projectBusinessType;
	}

	public void setProjectBusinessType(String projectBusinessType) {
		this.projectBusinessType = projectBusinessType;
	}

	@Column(name = "ARGO_BIZ_TYPE", precision = 10)
	public String getArgoBizType() {
		return argoBizType;
	}

	public void setArgoBizType(String argoBizType) {
		this.argoBizType = argoBizType;
	}
	@Column(name = "ASSISTANCE", length = 20)
	public String getAssistance() {
		return assistance;
	}

	public void setAssistance(String assistance) {
		this.assistance = assistance;
	}
	@Column(name = "CREDIT_TYPE", length = 30)
	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	@Column(name = "TEMP_CREDIT_TYPE", length = 10)
	public String getTempCreditType() {
		return tempCreditType;
	}

	public void setTempCreditType(String tempCreditType) {
		this.tempCreditType = tempCreditType;
	}
}