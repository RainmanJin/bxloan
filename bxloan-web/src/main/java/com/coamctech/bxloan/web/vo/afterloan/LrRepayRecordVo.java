package com.coamctech.bxloan.web.vo.afterloan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

/**
 *  查看还款记录
 *
 */
public class LrRepayRecordVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 还款编号
	 */
	private String repayNum;
	/**
	 * 还款日期
	 */
	private Date repayDate;
	/**
	 * 还款金额
	 */
	private BigDecimal actualAmt;
	/**
	 * 资金来源
	 */
	private String fundsSource;
	/**
	 * 还款方式
	 */
	private String repaymentStatusCd;
	
	public LrRepayRecordVo(Object[] objs) {
		super();
		this.repayNum=CommonHelper.toStr(objs[0]);
		this.repayDate=(Date)objs[1];
		this.actualAmt=CommonHelper.toBigDecimal(objs[2]);
		this.fundsSource=CommonHelper.toStr(objs[3]);
		this.repaymentStatusCd=CommonHelper.toStr(objs[4]);
	}
	public String getRepayNum() {
		return repayNum;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public String getRepayDateStr() {
		return CommonHelper.date2Str(repayDate, CommonHelper.DF_DATE);
	}
	public BigDecimal getActualAmt() {
		return actualAmt;
	}
	public String getFundsSource() {
		return fundsSource;
	}
	public String getRepaymentStatusCd() {
		return repaymentStatusCd;
	}

}
