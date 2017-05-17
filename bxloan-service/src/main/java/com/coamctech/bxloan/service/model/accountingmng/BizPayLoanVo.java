package com.coamctech.bxloan.service.model.accountingmng;

import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

/**
 * 放款信息
 *
 */
public class BizPayLoanVo extends BizBaseVo{
	/**
	 * 放款编号
	 */
	private String payLoanNum;
	/**
	 * 客户编号
	 */
	private String customerNum;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 产品名称
	 */
	private String productTypeCd;
	private String productTypeName;
	private String contrNum;
	private BigDecimal contrAmt;
	private String currency;
	private String currencyStr;
	private String contrTerm;
	private String contrTermUnit;
	private String contrTermUnitStr;
	private Integer repayPrincipalMonthes;
	/**
	 * 合同利率
	 */
	private BigDecimal contrIr;
	private String contrRepaymentModeCd;
	private String contrRepaymentModeName;
	/**
	 * 约定还款日
	 */
	private Integer promiseRepayDay;
	private String bankName;
	private String bankAccountNum;
	
	private Long handleOrgId;
	private String handleOrgName;
	private Long handlePersonId;
	private String handlePersonName;
	private BigDecimal payLoanAmt;
	/**
	 * 手续费及佣金
	 */
	private BigDecimal feesComsn;
	private Date payLoanDate;
	private Date actualPayLoanDate;
	/**
	 * 备注
	 */
	private String remark;
	
	
	public BizPayLoanVo(Object[] objs) {
		super();
		int i=0;
		this.payLoanNum=CommonHelper.toStr(objs[i++]);
		this.customerNum=CommonHelper.toStr(objs[i++]);
		this.customerName=CommonHelper.toStr(objs[i++]);
		this.productTypeCd=CommonHelper.toStr(objs[i++]);
		this.payLoanAmt=CommonHelper.toBigDecimal(objs[i++]);
		this.feesComsn=CommonHelper.toBigDecimal(objs[i++]);
		this.handleOrgId=CommonHelper.toLong(objs[i++]);
		this.handlePersonId=CommonHelper.toLong(objs[i++]);
		this.remark=CommonHelper.toStr(objs[i++]);
		this.bankName=CommonHelper.toStr(objs[i++]);
		this.bankAccountNum=CommonHelper.toStr(objs[i++]);
		this.payLoanDate=(Date)objs[i++];
		this.actualPayLoanDate=(Date)objs[i++];
		this.contrNum=CommonHelper.toStr(objs[i++]);
		this.contrAmt=CommonHelper.toBigDecimal(objs[i++]);
		this.currency=CommonHelper.toStr(objs[i++]);
		this.contrIr=CommonHelper.toBigDecimal(objs[i++]);
		this.contrTerm=CommonHelper.toStr(objs[i++]);
		this.contrTermUnit=CommonHelper.toStr(objs[i++]);
		this.contrRepaymentModeCd=CommonHelper.toStr(objs[i++]);
		this.repayPrincipalMonthes=CommonHelper.toInt(objs[i++]);
		this.promiseRepayDay=CommonHelper.toInt(objs[i++]);
	}
	public String getPayLoanNum() {
		return payLoanNum;
	}
	public void setPayLoanNum(String payLoanNum) {
		this.payLoanNum = payLoanNum;
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
	public String getContrNum() {
		return contrNum;
	}
	public void setContrNum(String contrNum) {
		this.contrNum = contrNum;
	}
	public BigDecimal getContrAmt() {
		return contrAmt;
	}
	public void setContrAmt(BigDecimal contrAmt) {
		this.contrAmt = contrAmt;
	}
	public String getContrTerm() {
		return contrTerm;
	}
	public void setContrTerm(String contrTerm) {
		this.contrTerm = contrTerm;
	}
	public BigDecimal getContrIr() {
		return contrIr;
	}
	public void setContrIr(BigDecimal contrIr) {
		this.contrIr = contrIr;
	}
	public String getContrRepaymentModeCd() {
		return contrRepaymentModeCd;
	}
	public void setContrRepaymentModeCd(String contrRepaymentModeCd) {
		this.contrRepaymentModeCd = contrRepaymentModeCd;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccountNum() {
		return bankAccountNum;
	}
	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}
	public String getHandleOrgName() {
		return handleOrgName;
	}
	public void setHandleOrgName(String handleOrgName) {
		this.handleOrgName = handleOrgName;
	}
	public String getHandlePersonName() {
		return handlePersonName;
	}
	public void setHandlePersonName(String handlePersonName) {
		this.handlePersonName = handlePersonName;
	}
	public BigDecimal getPayLoanAmt() {
		return payLoanAmt;
	}
	public void setPayLoanAmt(BigDecimal payLoanAmt) {
		this.payLoanAmt = payLoanAmt;
	}
	public BigDecimal getFeesComsn() {
		return feesComsn;
	}
	public void setFeesComsn(BigDecimal feesComsn) {
		this.feesComsn = feesComsn;
	}
	public Date getPayLoanDate() {
		return payLoanDate;
	}
	public void setPayLoanDate(Date payLoanDate) {
		this.payLoanDate = payLoanDate;
	}
	public Date getActualPayLoanDate() {
		return actualPayLoanDate;
	}
	public void setActualPayLoanDate(Date actualPayLoanDate) {
		this.actualPayLoanDate = actualPayLoanDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getHandleOrgId() {
		return handleOrgId;
	}
	public void setHandleOrgId(Long handleOrgId) {
		this.handleOrgId = handleOrgId;
	}
	public Long getHandlePersonId() {
		return handlePersonId;
	}
	public void setHandlePersonId(Long handlePersonId) {
		this.handlePersonId = handlePersonId;
	}
	public String getProductTypeCd() {
		return productTypeCd;
	}
	public void setProductTypeCd(String productTypeCd) {
		this.productTypeCd = productTypeCd;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getContrTermUnit() {
		return contrTermUnit;
	}
	public void setContrTermUnit(String contrTermUnit) {
		this.contrTermUnit = contrTermUnit;
	}
	public Integer getPromiseRepayDay() {
		return promiseRepayDay;
	}
	public void setPromiseRepayDay(Integer promiseRepayDay) {
		this.promiseRepayDay = promiseRepayDay;
	}
	public Integer getRepayPrincipalMonthes() {
		return repayPrincipalMonthes;
	}
	public void setRepayPrincipalMonthes(Integer repayPrincipalMonthes) {
		this.repayPrincipalMonthes = repayPrincipalMonthes;
	}
	public String getContrRepaymentModeName() {
		return contrRepaymentModeName;
	}
	public void setContrRepaymentModeName(String contrRepaymentModeName) {
		this.contrRepaymentModeName = contrRepaymentModeName;
	}
	public String getCurrencyStr() {
		return currencyStr;
	}
	public void setCurrencyStr(String currencyStr) {
		this.currencyStr = currencyStr;
	}
	public String getContrTermUnitStr() {
		return contrTermUnitStr;
	}
	public void setContrTermUnitStr(String contrTermUnitStr) {
		this.contrTermUnitStr = contrTermUnitStr;
	}
}
