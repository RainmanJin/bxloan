package com.coamctech.bxloan.service.model;

import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;


public class PayLoanInfoVO {
	private Long payLoanId;
	private String customerNum;//客户编号
	private String customerName;//客户名称
	private String customerManager;//客户经理 同经办人-mask
	private String contractNum;//贷款合同编号
	
	private String productTypeMask;//贷款产品-mask
	private String productType;//贷款产品
	private String payLoanNum;//放款编号
	private BigDecimal contractAmt;//贷款合同金额
	private String contractTermUnit;//贷款合同期限
	
	private BigDecimal contractRate;//贷款年利率
	private BigDecimal contractRateMask;//贷款年利率-%
	private String repayModeCd;//还款方式
	private String repayModeCdMask;//还款方式-mask
	private Integer repayPrincipalMonthes;//还款周期月数
	private Integer arrangeRepayDay;//约定还款日
	private String bankName;//开户行名称
	private String loanNum;//贷款账号
	private String loanDateStyle;//约定方式
	private String loanDateStyleMask;//约定方式-mask
	private BigDecimal contractAvailableAmt;//本次可发放金额
	private BigDecimal cumulativePayoutAmt;//合同已发放金额
	private BigDecimal cumulativeRepayAmt;//合同已还金额
	private BigDecimal loanAmt;//本次发放金额
	private BigDecimal freePayLoanAmtCnt;//累计收取手续费及佣金
	private BigDecimal sumAmt;//手续费及佣金收入
	private String sourceType;//费用来源
	private BigDecimal freeamtcnt;//申请费用金额
	private String ifInsure;//是否有保险-new
	private String loanRegistTime;//放款日期
	
	private String loanActulTime;
	private String fillInDate; //填写日期
	private String createDate;//经办日期
	private Long applyOrgId;  //经办机构
	private String applyOrgIdMask;  //经办机构-mask
	private String applyUserNum; //经办人
	private String applyUserNumMask;//经办人-mask
	private String remark;//备注
	//其他
	private Long partyId;
	private Long contractId;
	//到期日期
	private Date arriveDate;
	//币种
	private String currency;
	private String currencyMask;
	
	//两个状态标识
	private String isMultipleLoan;//是否允许多次放款
	private String isAllowLoan;//控制3个输入框的状态
	
	//add by wangyawei 20150423 start
	private String payStatusCd;		//放款状态
	private String custMangerName;	//客户经理
	//add by wangyawei 20150423 end
	
	public PayLoanInfoVO() {
		super();
	}
	
	public PayLoanInfoVO(Object[] objs) {
		super();
		this.payLoanId=CommonHelper.toLong(objs[0]);
		this.payLoanNum=CommonHelper.toStr(objs[1]);
		this.loanRegistTime=CommonHelper.date2Str((Date) objs[2], CommonHelper.DF_DATE);
		this.loanAmt=CommonHelper.toBigDecimal(objs[3]);
		this.sumAmt=CommonHelper.toBigDecimal(objs[4]);
		this.payStatusCd=CommonHelper.toStr(objs[5]);
		this.loanActulTime=CommonHelper.date2Str((Date) objs[6], CommonHelper.DF_DATE);
		this.applyUserNumMask=CommonHelper.toStr(objs[7]);
		this.createDate=CommonHelper.date2Str((Date) objs[8], CommonHelper.DF_DATE);
		this.applyOrgIdMask=CommonHelper.toStr(objs[9]);
		this.custMangerName=CommonHelper.toStr(objs[10]);
	}
	
	public Long getPayLoanId() {
		return payLoanId;
	}
	public void setPayLoanId(Long payLoanId) {
		this.payLoanId = payLoanId;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getProductTypeMask() {
		return productTypeMask;
	}
	public void setProductTypeMask(String productTypeMask) {
		this.productTypeMask = productTypeMask;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getPayLoanNum() {
		return payLoanNum;
	}
	public void setPayLoanNum(String payLoanNum) {
		this.payLoanNum = payLoanNum;
	}
	public BigDecimal getContractAmt() {
		return contractAmt;
	}
	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}
	public BigDecimal getContractRate() {
		return contractRate;
	}
	public void setContractRate(BigDecimal contractRate) {
		this.contractRate = contractRate;
	}
	public BigDecimal getContractRateMask() {
		return contractRateMask;
	}
	public void setContractRateMask(BigDecimal contractRateMask) {
		this.contractRateMask = contractRateMask;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getLoanNum() {
		return loanNum;
	}
	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}
	public BigDecimal getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}
	public Long getApplyOrgId() {
		return applyOrgId;
	}
	public void setApplyOrgId(Long applyOrgId) {
		this.applyOrgId = applyOrgId;
	}
	public String getApplyOrgIdMask() {
		return applyOrgIdMask;
	}
	public void setApplyOrgIdMask(String applyOrgIdMask) {
		this.applyOrgIdMask = applyOrgIdMask;
	}
	public String getApplyUserNum() {
		return applyUserNum;
	}
	public void setApplyUserNum(String applyUserNum) {
		this.applyUserNum = applyUserNum;
	}
	public String getApplyUserNumMask() {
		return applyUserNumMask;
	}
	public void setApplyUserNumMask(String applyUserNumMask) {
		this.applyUserNumMask = applyUserNumMask;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public Date getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencyMask() {
		return currencyMask;
	}
	public void setCurrencyMask(String currencyMask) {
		this.currencyMask = currencyMask;
	}
	public String getRepayModeCd() {
		return repayModeCd;
	}
	public void setRepayModeCd(String repayModeCd) {
		this.repayModeCd = repayModeCd;
	}
	public String getRepayModeCdMask() {
		return repayModeCdMask;
	}
	public void setRepayModeCdMask(String repayModeCdMask) {
		this.repayModeCdMask = repayModeCdMask;
	}
	public Integer getArrangeRepayDay() {
		return arrangeRepayDay;
	}
	public void setArrangeRepayDay(Integer arrangeRepayDay) {
		this.arrangeRepayDay = arrangeRepayDay;
	}
	public Integer getRepayPrincipalMonthes() {
		return repayPrincipalMonthes;
	}
	public void setRepayPrincipalMonthes(Integer repayPrincipalMonthes) {
		this.repayPrincipalMonthes = repayPrincipalMonthes;
	}
	public String getLoanRegistTime() {
		return loanRegistTime;
	}
	public void setLoanRegistTime(String loanRegistTime) {
		this.loanRegistTime = loanRegistTime;
	}
	public String getLoanActulTime() {
		return loanActulTime;
	}
	public void setLoanActulTime(String loanActulTime) {
		this.loanActulTime = loanActulTime;
	}
	public String getFillInDate() {
		return fillInDate;
	}
	public void setFillInDate(String fillInDate) {
		this.fillInDate = fillInDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getContractAvailableAmt() {
		return contractAvailableAmt;
	}
	public void setContractAvailableAmt(BigDecimal contractAvailableAmt) {
		this.contractAvailableAmt = contractAvailableAmt;
	}
	public BigDecimal getCumulativePayoutAmt() {
		return cumulativePayoutAmt;
	}
	public void setCumulativePayoutAmt(BigDecimal cumulativePayoutAmt) {
		this.cumulativePayoutAmt = cumulativePayoutAmt;
	}
	public BigDecimal getCumulativeRepayAmt() {
		return cumulativeRepayAmt;
	}
	public void setCumulativeRepayAmt(BigDecimal cumulativeRepayAmt) {
		this.cumulativeRepayAmt = cumulativeRepayAmt;
	}
	public String getContractTermUnit() {
		return contractTermUnit;
	}
	public void setContractTermUnit(String contractTermUnit) {
		this.contractTermUnit = contractTermUnit;
	}
	public BigDecimal getFreeamtcnt() {
		return freeamtcnt;
	}
	public void setFreeamtcnt(BigDecimal freeamtcnt) {
		this.freeamtcnt = freeamtcnt;
	}
	public String getLoanDateStyle() {
		return loanDateStyle;
	}
	public void setLoanDateStyle(String loanDateStyle) {
		this.loanDateStyle = loanDateStyle;
	}
	public String getLoanDateStyleMask() {
		return loanDateStyleMask;
	}
	public void setLoanDateStyleMask(String loanDateStyleMask) {
		this.loanDateStyleMask = loanDateStyleMask;
	}
	public BigDecimal getFreePayLoanAmtCnt() {
		return freePayLoanAmtCnt;
	}
	public void setFreePayLoanAmtCnt(BigDecimal freePayLoanAmtCnt) {
		this.freePayLoanAmtCnt = freePayLoanAmtCnt;
	}
	public BigDecimal getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getIsMultipleLoan() {
		return isMultipleLoan;
	}
	public void setIsMultipleLoan(String isMultipleLoan) {
		this.isMultipleLoan = isMultipleLoan;
	}
	public String getIsAllowLoan() {
		return isAllowLoan;
	}
	public void setIsAllowLoan(String isAllowLoan) {
		this.isAllowLoan = isAllowLoan;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIfInsure() {
		return ifInsure;
	}
	public void setIfInsure(String ifInsure) {
		this.ifInsure = ifInsure;
	}

	public String getPayStatusCd() {
		return payStatusCd;
	}

	public void setPayStatusCd(String payStatusCd) {
		this.payStatusCd = payStatusCd;
	}

	public String getCustMangerName() {
		return custMangerName;
	}

	public void setCustMangerName(String custMangerName) {
		this.custMangerName = custMangerName;
	}
}
