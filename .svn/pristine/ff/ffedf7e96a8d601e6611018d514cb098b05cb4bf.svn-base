package com.coamctech.bxloan.service.model.approval;

import java.io.Serializable;
import java.math.BigDecimal;

import com.coamctech.bxloan.commons.utils.CommonHelper;


/**
 * 易贷申请信息VO
 *
 */
/**
 * @author Admin
 *
 */
public class ElProjApplVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3696079578486207748L;
	/**
	 * 借款人
	 */
	private String borrowerName;
	/**
	 * 共同借款人
	 */
	private String commonBorrowerName;
	/**
	 * 贷款金额
	 */
	private BigDecimal loanAmt;
	/**
	 * 贷款周期
	 */
	private Integer loanTerm;
	/**
	 * 贷款利率
	 */
	private BigDecimal loanInteRate;
	/**
	 * 经办员
	 */
	private String loanOfficer;
	/**
	 * 经办机构
	 * */
	private String loanOrg;
	
	public ElProjApplVo() {
		super();
	}
	public ElProjApplVo(Object[] objs) {
		super();
		this.borrowerName=CommonHelper.toStr(objs[0]);
		this.loanOfficer=CommonHelper.toStr(objs[1]);
		this.loanAmt=CommonHelper.toBigDecimal(objs[2]);
		this.loanTerm=CommonHelper.toInt(objs[3]);
		this.loanInteRate=CommonHelper.toBigDecimal(objs[5]);
		this.loanOrg=CommonHelper.toStr(objs[6]);
	}
	
	
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getCommonBorrowerName() {
		return commonBorrowerName;
	}
	public void setCommonBorrowerName(String commonBorrowerName) {
		this.commonBorrowerName = commonBorrowerName;
	}
	public BigDecimal getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}
	public Integer getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}
	public BigDecimal getLoanInteRate() {
		return loanInteRate;
	}
	public void setLoanInteRate(BigDecimal loanInteRate) {
		this.loanInteRate = loanInteRate;
	}
	public String getLoanOfficer() {
		return loanOfficer;
	}
	public void setLoanOfficer(String loanOfficer) {
		this.loanOfficer = loanOfficer;
	}
	public String getLoanOrg() {
		return loanOrg;
	}
	public void setLoanOrg(String loanOrg) {
		this.loanOrg = loanOrg;
	}
}
