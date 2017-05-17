package com.coamctech.bxloan.web.vo.afterloan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

/**
 * 还款计划明细
 *
 */
public class LrRepayPlanItemVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer period;
	private Date endDate;
	private BigDecimal principal;
	private BigDecimal interest;
	private BigDecimal principalInterest;
	private String status;
	
	
	public LrRepayPlanItemVo(Object[] objs) {
		super();
		this.period=CommonHelper.toInt(objs[0]);
		this.endDate=(Date)(objs[1]);
		this.principal=CommonHelper.toBigDecimal(objs[2]);
		this.interest=CommonHelper.toBigDecimal(objs[3]);
		this.principalInterest=CommonHelper.toBigDecimal(objs[4]);
		this.status=CommonHelper.toStr(objs[5]);
	}
	public Integer getPeriod() {
		return period;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getEndDateStr() {
		return CommonHelper.date2Str(endDate, CommonHelper.DF_DATE);
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public BigDecimal getPrincipalInterest() {
		return principalInterest;
	}
	public String getStatus() {
		return status;
	}

}
