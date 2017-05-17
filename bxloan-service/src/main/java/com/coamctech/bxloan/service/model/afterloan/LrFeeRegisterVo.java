package com.coamctech.bxloan.service.model.afterloan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

/**
 * 费用登记
 *	@since 2014-10-22
 */
public class LrFeeRegisterVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5728660042134834834L;
	private Long bizExpenseRateId;
	private Long contractId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 还款编号
	 */
	private String repayLoanNum;
	/**
	 * 贷款产品编号
	 */
	private String loanProductCd;
	/**
	 * 贷款产品名称
	 */
	private String loanProductName;
	/**
	 * 客户经理编号
	 */
	private String custMngNum;
	/**
	 * 客户经理名称
	 */
	private String custMngName;
	/**
	 * 贷款金额
	 */
	private BigDecimal loanAmt;
	/**
	 * 合同期限
	 */
	private Integer contractTerm;
	/**
	 * 合同期限单位
	 * */
	private String contractTermUnit;
	
	//*******填写内容******//
	
	/**
	 * 实还日期
	 * */
	private Date repayDate;	
	/**
	 * 备注
	 * */
	private String remark;	
	/**
	 * 资金来源
	 * */
	private String fundsSource;
	/**
	 * 费用金额
	 * */
	private String expenseAmt;
	/**
	 * 费用类型
	 * */
	private String feeType;
	
	
	
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getContractId() {
		return contractId;
	}
	public String getCustomerName() {
		return customerName;
	}
	
	public String getRepayLoanNum() {
		return repayLoanNum;
	}
	public void setRepayLoanNum(String repayLoanNum) {
		this.repayLoanNum = repayLoanNum;
	}
	public String getLoanProductCd() {
		return loanProductCd;
	}
	public void setLoanProductCd(String loanProductCd) {
		this.loanProductCd = loanProductCd;
	}
	public String getLoanProductName() {
		return loanProductName;
	}
	public void setLoanProductName(String loanProductName) {
		this.loanProductName = loanProductName;
	}
	public String getCustMngNum() {
		return custMngNum;
	}
	public void setCustMngNum(String custMngNum) {
		this.custMngNum = custMngNum;
	}
	public String getCustMngName() {
		return custMngName;
	}
	public void setCustMngName(String custMngName) {
		this.custMngName = custMngName;
	}
	public BigDecimal getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}
	public Integer getContractTerm() {
		return contractTerm;
	}
	public void setContractTerm(Integer contractTerm) {
		this.contractTerm = contractTerm;
	}
	public String getRepayDateStr() {
		return CommonHelper.date2Str(CommonHelper.getNow(), CommonHelper.DF_DATE);
	}
	public void setRepayDateStr(String repayDate){
		this.repayDate = CommonHelper.str2Date(repayDate, CommonHelper.DF_DATE);
	}
	public String getLoanAmtStr(){
		return MoneyUtil.formatMoney(loanAmt);
	}
	public String getRepayDate() {
		return CommonHelper.date2Str(this.repayDate, CommonHelper.DF_DATE);
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = CommonHelper.str2Date(repayDate, CommonHelper.DF_DATE);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFundsSource() {
		return fundsSource;
	}
	public void setFundsSource(String fundsSource) {
		this.fundsSource = fundsSource;
	}
	public String getExpenseAmt() {
		return expenseAmt;
	}
	public void setExpenseAmt(String expenseAmt) {
		this.expenseAmt = expenseAmt;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Long getBizExpenseRateId() {
		return bizExpenseRateId;
	}
	public void setBizExpenseRateId(Long bizExpenseRateId) {
		this.bizExpenseRateId = bizExpenseRateId;
	}
	public String getContractTermUnit() {
		return contractTermUnit;
	}
	public void setContractTermUnit(String contractTermUnit) {
		this.contractTermUnit = contractTermUnit;
	}

}
