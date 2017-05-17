package com.coamctech.bxloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.entity.BaseEntity;

@Entity
@Table(name = "PRODUCT", schema = GlobalConstants.WD_SCHEMA)
public class Product extends BaseEntity {

	// Fields

	private Long productCd;
	private Long parentProductCd;
	private String productName;
	private String productLevel;
	private String creditProductRiskRateCd;
	private Double convertCoefficient;
	private String circleInd;
	private String termUnit;
	private Integer minimalTerm;
	private Integer maximalTerm;
	private String remarks;
	private String contractTypeCd;
	private String currencyDifferenceCd;
	private String discountInd;
	private Double outstandLimitRate;
	private String accountingAttachCd;
	private String payoutModeCd;
	private String signMultiContractsInd;
	private String batchBizInd;
	private String generalInspectInd;
	private String creditClassificationInd;
	private String flowDifference;
	private String calcInterestInd;
	private String lowRiskInd;
	private String batchContractsInd;
	private String contractDisbPlanInd;
	private String contractRepayPlanInd;
	private String limitLoginOccasionCd;
	private Byte limitLoginAmount;
	private String batchPayoutInd;
	private String payoutRepayPlanInd;
	private String exceedContractTmLimitInd;
	private Double exceedRate;
	private Integer exceedTmLimit;
	private String exceedTmLimitUnitCd;
	private String adjustIntRateTypeInd;
	private String applyMultiPayoutInd;
	private String postponementInd;
	private Byte mostPostponementTime;
	private String postponementModeCd;
	private Integer postponementTmLimit;
	private String postponementTmLimitUnitCd;
	private Double postponementRate;
	private String adjustIntRateInd;
	private String firstInspectInd;
	private Byte firstInspectFinishDayNum;
	private Integer aheadRemindDayNum;
	private String printMatureInformInd;
	private Byte aheadPrintInformDayNum;
	private String codeKey;
	private String creditSubitemTypeCd;
	private String tradeFinanceTypeCd;
	private String productControlTypeCd;
	private String bizType;
	private String orgId;
	private String partyTypeCd;
	private String productId;
	private Double minApplyAmt;
	private Double maxApplyAmt;
	
	private String customerType; //Customer类型
	

	public Product() {
	}

	public Product(Long parentProductCd, String productName,
			String productLevel, String creditProductRiskRateCd,
			Double convertCoefficient, String circleInd, String termUnit,
			Integer minimalTerm, Integer maximalTerm, String remarks,
			String contractTypeCd, String currencyDifferenceCd,
			String discountInd, Double outstandLimitRate,
			String accountingAttachCd, String payoutModeCd,
			String signMultiContractsInd, String batchBizInd,
			String generalInspectInd, String creditClassificationInd,
			String flowDifference, String calcInterestInd, String lowRiskInd,
			String batchContractsInd, String contractDisbPlanInd,
			String contractRepayPlanInd, String limitLoginOccasionCd,
			Byte limitLoginAmount, String batchPayoutInd,
			String payoutRepayPlanInd, String exceedContractTmLimitInd,
			Double exceedRate, Integer exceedTmLimit,
			String exceedTmLimitUnitCd, String adjustIntRateTypeInd,
			String applyMultiPayoutInd, String postponementInd,
			Byte mostPostponementTime, String postponementModeCd,
			Integer postponementTmLimit, String postponementTmLimitUnitCd,
			Double postponementRate, String adjustIntRateInd,
			String firstInspectInd, Byte firstInspectFinishDayNum,
			Integer aheadRemindDayNum, String printMatureInformInd,
			Byte aheadPrintInformDayNum, String codeKey,
			String creditSubitemTypeCd, String tradeFinanceTypeCd,
			String productControlTypeCd, String bizType, String orgId,
			String partyTypeCd, String productId, Double minApplyAmt,
			Double maxApplyAmt) {
		this.parentProductCd = parentProductCd;
		this.productName = productName;
		this.productLevel = productLevel;
		this.creditProductRiskRateCd = creditProductRiskRateCd;
		this.convertCoefficient = convertCoefficient;
		this.circleInd = circleInd;
		this.termUnit = termUnit;
		this.minimalTerm = minimalTerm;
		this.maximalTerm = maximalTerm;
		this.remarks = remarks;
		this.contractTypeCd = contractTypeCd;
		this.currencyDifferenceCd = currencyDifferenceCd;
		this.discountInd = discountInd;
		this.outstandLimitRate = outstandLimitRate;
		this.accountingAttachCd = accountingAttachCd;
		this.payoutModeCd = payoutModeCd;
		this.signMultiContractsInd = signMultiContractsInd;
		this.batchBizInd = batchBizInd;
		this.generalInspectInd = generalInspectInd;
		this.creditClassificationInd = creditClassificationInd;
		this.flowDifference = flowDifference;
		this.calcInterestInd = calcInterestInd;
		this.lowRiskInd = lowRiskInd;
		this.batchContractsInd = batchContractsInd;
		this.contractDisbPlanInd = contractDisbPlanInd;
		this.contractRepayPlanInd = contractRepayPlanInd;
		this.limitLoginOccasionCd = limitLoginOccasionCd;
		this.limitLoginAmount = limitLoginAmount;
		this.batchPayoutInd = batchPayoutInd;
		this.payoutRepayPlanInd = payoutRepayPlanInd;
		this.exceedContractTmLimitInd = exceedContractTmLimitInd;
		this.exceedRate = exceedRate;
		this.exceedTmLimit = exceedTmLimit;
		this.exceedTmLimitUnitCd = exceedTmLimitUnitCd;
		this.adjustIntRateTypeInd = adjustIntRateTypeInd;
		this.applyMultiPayoutInd = applyMultiPayoutInd;
		this.postponementInd = postponementInd;
		this.mostPostponementTime = mostPostponementTime;
		this.postponementModeCd = postponementModeCd;
		this.postponementTmLimit = postponementTmLimit;
		this.postponementTmLimitUnitCd = postponementTmLimitUnitCd;
		this.postponementRate = postponementRate;
		this.adjustIntRateInd = adjustIntRateInd;
		this.firstInspectInd = firstInspectInd;
		this.firstInspectFinishDayNum = firstInspectFinishDayNum;
		this.aheadRemindDayNum = aheadRemindDayNum;
		this.printMatureInformInd = printMatureInformInd;
		this.aheadPrintInformDayNum = aheadPrintInformDayNum;
		this.codeKey = codeKey;
		this.creditSubitemTypeCd = creditSubitemTypeCd;
		this.tradeFinanceTypeCd = tradeFinanceTypeCd;
		this.productControlTypeCd = productControlTypeCd;
		this.bizType = bizType;
		this.orgId = orgId;
		this.partyTypeCd = partyTypeCd;
		this.productId = productId;
		this.minApplyAmt = minApplyAmt;
		this.maxApplyAmt = maxApplyAmt;
	}

	@Id
	@SequenceGenerator(name = "PK_SEQ_TBL", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_TBL")
	@Column(name = "PRODUCT_CD", length = 20, nullable = false)
	public Long getProductCd() {
		return this.productCd;
	}

	public void setProductCd(Long productCd) {
		this.productCd = productCd;
	}

	@Column(name = "PARENT_PRODUCT_CD", precision = 8, scale = 0)
	public Long getParentProductCd() {
		return this.parentProductCd;
	}

	public void setParentProductCd(Long parentProductCd) {
		this.parentProductCd = parentProductCd;
	}

	@Column(name = "PRODUCT_NAME", length = 100)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "PRODUCT_LEVEL", length = 1)
	public String getProductLevel() {
		return this.productLevel;
	}

	public void setProductLevel(String productLevel) {
		this.productLevel = productLevel;
	}

	@Column(name = "CREDIT_PRODUCT_RISK_RATE_CD", length = 20)
	public String getCreditProductRiskRateCd() {
		return this.creditProductRiskRateCd;
	}

	public void setCreditProductRiskRateCd(String creditProductRiskRateCd) {
		this.creditProductRiskRateCd = creditProductRiskRateCd;
	}

	@Column(name = "CONVERT_COEFFICIENT", precision = 16, scale = 8)
	public Double getConvertCoefficient() {
		return this.convertCoefficient;
	}

	public void setConvertCoefficient(Double convertCoefficient) {
		this.convertCoefficient = convertCoefficient;
	}

	@Column(name = "CIRCLE_IND", length = 1)
	public String getCircleInd() {
		return this.circleInd;
	}

	public void setCircleInd(String circleInd) {
		this.circleInd = circleInd;
	}

	@Column(name = "TERM_UNIT", length = 20)
	public String getTermUnit() {
		return this.termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	@Column(name = "MINIMAL_TERM", precision = 6, scale = 0)
	public Integer getMinimalTerm() {
		return this.minimalTerm;
	}

	public void setMinimalTerm(Integer minimalTerm) {
		this.minimalTerm = minimalTerm;
	}

	@Column(name = "MAXIMAL_TERM", precision = 6, scale = 0)
	public Integer getMaximalTerm() {
		return this.maximalTerm;
	}

	public void setMaximalTerm(Integer maximalTerm) {
		this.maximalTerm = maximalTerm;
	}

	@Column(name = "REMARKS", length = 100)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "CONTRACT_TYPE_CD", length = 20)
	public String getContractTypeCd() {
		return this.contractTypeCd;
	}

	public void setContractTypeCd(String contractTypeCd) {
		this.contractTypeCd = contractTypeCd;
	}

	@Column(name = "CURRENCY_DIFFERENCE_CD", length = 20)
	public String getCurrencyDifferenceCd() {
		return this.currencyDifferenceCd;
	}

	public void setCurrencyDifferenceCd(String currencyDifferenceCd) {
		this.currencyDifferenceCd = currencyDifferenceCd;
	}

	@Column(name = "DISCOUNT_IND", length = 1)
	public String getDiscountInd() {
		return this.discountInd;
	}

	public void setDiscountInd(String discountInd) {
		this.discountInd = discountInd;
	}

	@Column(name = "OUTSTAND_LIMIT_RATE", precision = 8, scale = 6)
	public Double getOutstandLimitRate() {
		return this.outstandLimitRate;
	}

	public void setOutstandLimitRate(Double outstandLimitRate) {
		this.outstandLimitRate = outstandLimitRate;
	}

	@Column(name = "ACCOUNTING_ATTACH_CD", length = 1)
	public String getAccountingAttachCd() {
		return this.accountingAttachCd;
	}

	public void setAccountingAttachCd(String accountingAttachCd) {
		this.accountingAttachCd = accountingAttachCd;
	}

	@Column(name = "PAYOUT_MODE_CD", length = 20)
	public String getPayoutModeCd() {
		return this.payoutModeCd;
	}

	public void setPayoutModeCd(String payoutModeCd) {
		this.payoutModeCd = payoutModeCd;
	}

	@Column(name = "SIGN_MULTI_CONTRACTS_IND", length = 1)
	public String getSignMultiContractsInd() {
		return this.signMultiContractsInd;
	}

	public void setSignMultiContractsInd(String signMultiContractsInd) {
		this.signMultiContractsInd = signMultiContractsInd;
	}

	@Column(name = "BATCH_BIZ_IND", length = 1)
	public String getBatchBizInd() {
		return this.batchBizInd;
	}

	public void setBatchBizInd(String batchBizInd) {
		this.batchBizInd = batchBizInd;
	}

	@Column(name = "GENERAL_INSPECT_IND", length = 1)
	public String getGeneralInspectInd() {
		return this.generalInspectInd;
	}

	public void setGeneralInspectInd(String generalInspectInd) {
		this.generalInspectInd = generalInspectInd;
	}

	@Column(name = "CREDIT_CLASSIFICATION_IND", length = 1)
	public String getCreditClassificationInd() {
		return this.creditClassificationInd;
	}

	public void setCreditClassificationInd(String creditClassificationInd) {
		this.creditClassificationInd = creditClassificationInd;
	}

	@Column(name = "FLOW_DIFFERENCE", length = 100)
	public String getFlowDifference() {
		return this.flowDifference;
	}

	public void setFlowDifference(String flowDifference) {
		this.flowDifference = flowDifference;
	}

	@Column(name = "CALC_INTEREST_IND", length = 1)
	public String getCalcInterestInd() {
		return this.calcInterestInd;
	}

	public void setCalcInterestInd(String calcInterestInd) {
		this.calcInterestInd = calcInterestInd;
	}

	@Column(name = "LOW_RISK_IND", length = 1)
	public String getLowRiskInd() {
		return this.lowRiskInd;
	}

	public void setLowRiskInd(String lowRiskInd) {
		this.lowRiskInd = lowRiskInd;
	}

	@Column(name = "BATCH_CONTRACTS_IND", length = 1)
	public String getBatchContractsInd() {
		return this.batchContractsInd;
	}

	public void setBatchContractsInd(String batchContractsInd) {
		this.batchContractsInd = batchContractsInd;
	}

	@Column(name = "CONTRACT_DISB_PLAN_IND", length = 1)
	public String getContractDisbPlanInd() {
		return this.contractDisbPlanInd;
	}

	public void setContractDisbPlanInd(String contractDisbPlanInd) {
		this.contractDisbPlanInd = contractDisbPlanInd;
	}

	@Column(name = "CONTRACT_REPAY_PLAN_IND", length = 1)
	public String getContractRepayPlanInd() {
		return this.contractRepayPlanInd;
	}

	public void setContractRepayPlanInd(String contractRepayPlanInd) {
		this.contractRepayPlanInd = contractRepayPlanInd;
	}

	@Column(name = "LIMIT_LOGIN_OCCASION_CD", length = 20)
	public String getLimitLoginOccasionCd() {
		return this.limitLoginOccasionCd;
	}

	public void setLimitLoginOccasionCd(String limitLoginOccasionCd) {
		this.limitLoginOccasionCd = limitLoginOccasionCd;
	}

	@Column(name = "LIMIT_LOGIN_AMOUNT", precision = 2, scale = 0)
	public Byte getLimitLoginAmount() {
		return this.limitLoginAmount;
	}

	public void setLimitLoginAmount(Byte limitLoginAmount) {
		this.limitLoginAmount = limitLoginAmount;
	}

	@Column(name = "BATCH_PAYOUT_IND", length = 1)
	public String getBatchPayoutInd() {
		return this.batchPayoutInd;
	}

	public void setBatchPayoutInd(String batchPayoutInd) {
		this.batchPayoutInd = batchPayoutInd;
	}

	@Column(name = "PAYOUT_REPAY_PLAN_IND", length = 1)
	public String getPayoutRepayPlanInd() {
		return this.payoutRepayPlanInd;
	}

	public void setPayoutRepayPlanInd(String payoutRepayPlanInd) {
		this.payoutRepayPlanInd = payoutRepayPlanInd;
	}

	@Column(name = "EXCEED_CONTRACT_TM_LIMIT_IND", length = 1)
	public String getExceedContractTmLimitInd() {
		return this.exceedContractTmLimitInd;
	}

	public void setExceedContractTmLimitInd(String exceedContractTmLimitInd) {
		this.exceedContractTmLimitInd = exceedContractTmLimitInd;
	}

	@Column(name = "EXCEED_RATE", precision = 16, scale = 8)
	public Double getExceedRate() {
		return this.exceedRate;
	}

	public void setExceedRate(Double exceedRate) {
		this.exceedRate = exceedRate;
	}

	@Column(name = "EXCEED_TM_LIMIT", precision = 8, scale = 0)
	public Integer getExceedTmLimit() {
		return this.exceedTmLimit;
	}

	public void setExceedTmLimit(Integer exceedTmLimit) {
		this.exceedTmLimit = exceedTmLimit;
	}

	@Column(name = "EXCEED_TM_LIMIT_UNIT_CD", length = 20)
	public String getExceedTmLimitUnitCd() {
		return this.exceedTmLimitUnitCd;
	}

	public void setExceedTmLimitUnitCd(String exceedTmLimitUnitCd) {
		this.exceedTmLimitUnitCd = exceedTmLimitUnitCd;
	}

	@Column(name = "ADJUST_INT_RATE_TYPE_IND", length = 1)
	public String getAdjustIntRateTypeInd() {
		return this.adjustIntRateTypeInd;
	}

	public void setAdjustIntRateTypeInd(String adjustIntRateTypeInd) {
		this.adjustIntRateTypeInd = adjustIntRateTypeInd;
	}

	@Column(name = "APPLY_MULTI_PAYOUT_IND", length = 1)
	public String getApplyMultiPayoutInd() {
		return this.applyMultiPayoutInd;
	}

	public void setApplyMultiPayoutInd(String applyMultiPayoutInd) {
		this.applyMultiPayoutInd = applyMultiPayoutInd;
	}

	@Column(name = "POSTPONEMENT_IND", length = 1)
	public String getPostponementInd() {
		return this.postponementInd;
	}

	public void setPostponementInd(String postponementInd) {
		this.postponementInd = postponementInd;
	}

	@Column(name = "MOST_POSTPONEMENT_TIME", precision = 2, scale = 0)
	public Byte getMostPostponementTime() {
		return this.mostPostponementTime;
	}

	public void setMostPostponementTime(Byte mostPostponementTime) {
		this.mostPostponementTime = mostPostponementTime;
	}

	@Column(name = "POSTPONEMENT_MODE_CD", length = 20)
	public String getPostponementModeCd() {
		return this.postponementModeCd;
	}

	public void setPostponementModeCd(String postponementModeCd) {
		this.postponementModeCd = postponementModeCd;
	}

	@Column(name = "POSTPONEMENT_TM_LIMIT", precision = 8, scale = 0)
	public Integer getPostponementTmLimit() {
		return this.postponementTmLimit;
	}

	public void setPostponementTmLimit(Integer postponementTmLimit) {
		this.postponementTmLimit = postponementTmLimit;
	}

	@Column(name = "POSTPONEMENT_TM_LIMIT_UNIT_CD", length = 20)
	public String getPostponementTmLimitUnitCd() {
		return this.postponementTmLimitUnitCd;
	}

	public void setPostponementTmLimitUnitCd(String postponementTmLimitUnitCd) {
		this.postponementTmLimitUnitCd = postponementTmLimitUnitCd;
	}

	@Column(name = "POSTPONEMENT_RATE", precision = 16, scale = 8)
	public Double getPostponementRate() {
		return this.postponementRate;
	}

	public void setPostponementRate(Double postponementRate) {
		this.postponementRate = postponementRate;
	}

	@Column(name = "ADJUST_INT_RATE_IND", length = 1)
	public String getAdjustIntRateInd() {
		return this.adjustIntRateInd;
	}

	public void setAdjustIntRateInd(String adjustIntRateInd) {
		this.adjustIntRateInd = adjustIntRateInd;
	}

	@Column(name = "FIRST_INSPECT_IND", length = 1)
	public String getFirstInspectInd() {
		return this.firstInspectInd;
	}

	public void setFirstInspectInd(String firstInspectInd) {
		this.firstInspectInd = firstInspectInd;
	}

	@Column(name = "FIRST_INSPECT_FINISH_DAY_NUM", precision = 2, scale = 0)
	public Byte getFirstInspectFinishDayNum() {
		return this.firstInspectFinishDayNum;
	}

	public void setFirstInspectFinishDayNum(Byte firstInspectFinishDayNum) {
		this.firstInspectFinishDayNum = firstInspectFinishDayNum;
	}

	@Column(name = "AHEAD_REMIND_DAY_NUM", precision = 8, scale = 0)
	public Integer getAheadRemindDayNum() {
		return this.aheadRemindDayNum;
	}

	public void setAheadRemindDayNum(Integer aheadRemindDayNum) {
		this.aheadRemindDayNum = aheadRemindDayNum;
	}

	@Column(name = "PRINT_MATURE_INFORM_IND", length = 1)
	public String getPrintMatureInformInd() {
		return this.printMatureInformInd;
	}

	public void setPrintMatureInformInd(String printMatureInformInd) {
		this.printMatureInformInd = printMatureInformInd;
	}

	@Column(name = "AHEAD_PRINT_INFORM_DAY_NUM", precision = 2, scale = 0)
	public Byte getAheadPrintInformDayNum() {
		return this.aheadPrintInformDayNum;
	}

	public void setAheadPrintInformDayNum(Byte aheadPrintInformDayNum) {
		this.aheadPrintInformDayNum = aheadPrintInformDayNum;
	}

	@Column(name = "CODE_KEY", length = 40)
	public String getCodeKey() {
		return this.codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	@Column(name = "CREDIT_SUBITEM_TYPE_CD", length = 20)
	public String getCreditSubitemTypeCd() {
		return this.creditSubitemTypeCd;
	}

	public void setCreditSubitemTypeCd(String creditSubitemTypeCd) {
		this.creditSubitemTypeCd = creditSubitemTypeCd;
	}

	@Column(name = "TRADE_FINANCE_TYPE_CD", length = 20)
	public String getTradeFinanceTypeCd() {
		return this.tradeFinanceTypeCd;
	}

	public void setTradeFinanceTypeCd(String tradeFinanceTypeCd) {
		this.tradeFinanceTypeCd = tradeFinanceTypeCd;
	}

	@Column(name = "PRODUCT_CONTROL_TYPE_CD", length = 20)
	public String getProductControlTypeCd() {
		return this.productControlTypeCd;
	}

	public void setProductControlTypeCd(String productControlTypeCd) {
		this.productControlTypeCd = productControlTypeCd;
	}

	@Column(name = "BIZ_TYPE", length = 30)
	public String getBizType() {
		return this.bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	@Column(name = "ORG_ID", length = 2000)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "PARTY_TYPE_CD", length = 20)
	public String getPartyTypeCd() {
		return this.partyTypeCd;
	}

	public void setPartyTypeCd(String partyTypeCd) {
		this.partyTypeCd = partyTypeCd;
	}

	@Column(name = "PRODUCT_ID", length = 200)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "MIN_APPLY_AMT", precision = 20)
	public Double getMinApplyAmt() {
		return this.minApplyAmt;
	}

	public void setMinApplyAmt(Double minApplyAmt) {
		this.minApplyAmt = minApplyAmt;
	}

	@Column(name = "MAX_APPLY_AMT", precision = 20)
	public Double getMaxApplyAmt() {
		return this.maxApplyAmt;
	}

	public void setMaxApplyAmt(Double maxApplyAmt) {
		this.maxApplyAmt = maxApplyAmt;
	}

	@Column(name = "CUSTOMER_TYPE", length = 20)
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

}