package com.coamctech.bxloan.service.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.coamctech.bxloan.entity.ProjectApplication;

/**
 * 贷款试算Vo
 * @author WallenHeng
 *
 */
public class LoanTrialVo {
	/**
	 * 贷款金额
	 */
	private BigDecimal applyAmt;
	/**
	 * 期限（月）
	 */
	private String applyTerm;
	/**
	 * 还款方式
	 */
	private String repayingMode;
	
	/**
	 * 还款日
	 */
	private String repayDay;
	
	/**
	 * 年利率（%）
	 */
	private BigDecimal bizRate;
	
	/**
	 * 还款周期月数
	 */
	private String replyPeriodNum;
	
	

	public LoanTrialVo() {
		super();
	}

	public LoanTrialVo(ProjectApplication projApp) {
		super();
		if(projApp==null){
			return;
		}
		this.applyAmt = projApp.getApplyAmt();
		this.applyTerm=String.valueOf(projApp.getApplyTerm());
		this.repayingMode=projApp.getRepayingMode();
		this.repayDay=String.valueOf(getDayOfMonth(projApp.getApplyDate()));
		this.bizRate=projApp.getBizRate();
		this.replyPeriodNum=String.valueOf(projApp.getReplyPeriodNum()==null?"":projApp.getReplyPeriodNum());
	}
	private int getDayOfMonth(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	public BigDecimal getApplyAmt() {
		return applyAmt;
	}

	public String getApplyTerm() {
		return applyTerm;
	}

	public String getRepayingMode() {
		return repayingMode;
	}

	public String getRepayDay() {
		return repayDay;
	}

	public BigDecimal getBizRate() {
		return bizRate;
	}

	public String getReplyPeriodNum() {
		return replyPeriodNum;
	}

}
