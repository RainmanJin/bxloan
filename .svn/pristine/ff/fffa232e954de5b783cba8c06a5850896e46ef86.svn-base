package com.coamctech.bxloan.service.model.afterloan;

import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：NormalRepaymentDetailVo<br/>
 * 类描述 ：<br/>
 * 创建人: hwl<br/>
 * 创建时间：2015年5月19日 下午4:27:23  <br/>
 * 修改人：hwl
 * 修改时间：2015年5月19日 下午4:27:23  <br/>
 * 修改备注：<br/>
 * 版本： V1.0<br/>
 */

public class NormalRepaymentDetailVo extends NormalRepaymentVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 64300231671469608L;
	
	/**
	 * 还款编号
	 */
	private String repayLoanNum;
	
	/**
	 * 客户经理编号
	 */
	private String custMngNum;
	/**
	 * 客户经理名称
	 */
	private String custMngName;
	
	/**
	 * 放款日期
	 */
	private Date payloanDate;
	
	/**
	 * 到期日期
	 */
	private Date expirationDate;
	/**
	 * 合同期限
	 */
	private Integer contractTerm;
	/**
	 * 期限单位
	 */
	private String contractTermUnit;
	/**
	 * 还款方式
	 */
	private String repayModeCd;
	/**
	 * 合同利率
	 */
	private BigDecimal contractRate;
	
	/**
	 * 累计还款金额
	 */
	private BigDecimal cumulativeRepayAmt;
	
	/**
	 * 约定还款日
	 */
	private Integer arrangeRepayDay;
	/**
	 * 币种
	 */
	private String currency;

	public String getRepayLoanNum() {
		return repayLoanNum;
	}

	public void setRepayLoanNum(String repayLoanNum) {
		this.repayLoanNum = repayLoanNum;
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

	public Date getPayloanDate() {
		return payloanDate;
	}
	public String getPayloanDateStr() {
		return CommonHelper.date2Str(payloanDate, CommonHelper.DF_DATE);
	}

	public void setPayloanDate(Date payloanDate) {
		this.payloanDate = payloanDate;
	}

	public Integer getContractTerm() {
		return contractTerm;
	}

	public void setContractTerm(Integer contractTerm) {
		this.contractTerm = contractTerm;
	}

	public String getContractTermUnit() {
		return contractTermUnit;
	}

	public void setContractTermUnit(String contractTermUnit) {
		this.contractTermUnit = contractTermUnit;
	}

	public String getRepayModeCd() {
		return repayModeCd;
	}

	public void setRepayModeCd(String repayModeCd) {
		this.repayModeCd = repayModeCd;
	}

	public BigDecimal getContractRate() {
		return contractRate;
	}

	public void setContractRate(BigDecimal contractRate) {
		this.contractRate = contractRate;
	}

	public Integer getArrangeRepayDay() {
		return arrangeRepayDay;
	}

	public void setArrangeRepayDay(Integer arrangeRepayDay) {
		this.arrangeRepayDay = arrangeRepayDay;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	public String getExpirationDateStr() {
		return CommonHelper.date2Str(expirationDate, CommonHelper.DF_DATE);
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public BigDecimal getCumulativeRepayAmt() {
		return cumulativeRepayAmt;
	}

	public void setCumulativeRepayAmt(BigDecimal cumulativeRepayAmt) {
		this.cumulativeRepayAmt = cumulativeRepayAmt;
	}

}
