package com.coamctech.bxloan.service.model.afterloan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.google.common.collect.Lists;

/**
 *	还款信息汇总，用于正常还款和逾期还款
 */
public class LrRepayInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5416196222139221505L;
	/**
	 * 合同id
	 */
	private Long contractId;
	/**
	 * 还款计划id
	 */
	private Long repayingPlanId;
	/**
	 * 还款计划明细id
	 */
	private Long repayingPlanDetailId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 合同编号
	 */
	private String contractNum;
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
	/**
	 * 合同余额
	 */
	private BigDecimal contractBalance;
	/**
	 * 合同利率
	 */
	private BigDecimal contractInterestRate;
	/**
	 * 还款方式
	 */
	private String repayModeCd;
	/**
	 * 还款方式名称
	 */
	private String repayModeName;
	/**
	 * 还款周期月数
	 */
	private  Integer repayPrincipalMonthes;
	
	/**
	 * 贷款发放日期
	 */
	private Date payloanDate;
	/**
	 * 应还日期(yyyy-MM-dd)
	 */
	private String payloanDateStr;
	/**
	 * 上一期应还日期
	 */
	private Date prevRpdPayableDate;
	/**
	 * 下一期应还日期
	 */
	private Date nextRpdPayableDate;
	
	/**
	 * 最近一次还款日期
	 */
	private Date lastRepayDate;
	
	/**
	 * 应还总额
	 */
	private BigDecimal payableTotalAmt;
	/**
	 * 应还日期
	 */
	private Date payableDate;
	/**
	 * 应还日期(yyyy-MM-dd)
	 */
	private String payableDateStr;
	/**
	 * 应还本金
	 */
	private BigDecimal payablePrincipalAmt;
	/**
	 *  拖欠本息
	 */
	private BigDecimal overduePi;
	/**
	 * 逾期利息
	 */
	private BigDecimal overdueInt;
	
	/**
	 * 默认还款日期
	 */
	private Date repayDate;
	private String repayDateStr;
	
	//TODO 辅助参数
	/**
	 * 逾期标记 0：正常，未逾期；1：逾期
	 */
	private int overdue=0;
	/**
	 * 正常转逾期	1：自动将未还的期次转换为逾期未还，进行逾期还款
	 */
	private int normal2Overdue=0;
	/**
	 * 还款条目（对应还款计划）
	 */
	private List<LrRepayItem> repayItems=Lists.newArrayList();
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRepayLoanNum() {
		return repayLoanNum;
	}

	public void setRepayLoanNum(String repayLoanNum) {
		this.repayLoanNum = repayLoanNum;
	}

	public List<LrRepayItem> getRepayItems() {
		return repayItems;
	}

	public void setRepayItems(List<LrRepayItem> repayItems) {
		this.repayItems = repayItems;
	}
	
	public String getLoanProductName() {
		return loanProductName;
	}

	public void setLoanProductName(String loanProductName) {
		this.loanProductName = loanProductName;
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

	public BigDecimal getContractBalance() {
		return contractBalance;
	}

	public void setContractBalance(BigDecimal contractBalance) {
		this.contractBalance = contractBalance;
	}

	public BigDecimal getContractInterestRate() {
		return contractInterestRate;
	}

	public void setContractInterestRate(BigDecimal contractInterestRate) {
		this.contractInterestRate = contractInterestRate;
	}

	public String getRepayModeCd() {
		return repayModeCd;
	}

	public void setRepayModeCd(String repayModeCd) {
		this.repayModeCd = repayModeCd;
	}

	public Integer getRepayPrincipalMonthes() {
		return repayPrincipalMonthes;
	}

	public void setRepayPrincipalMonthes(Integer repayPrincipalMonthes) {
		this.repayPrincipalMonthes = repayPrincipalMonthes;
	}

	public Date getPayloanDate() {
		return payloanDate;
	}

	public void setPayloanDate(Date payloanDate) {
		this.payloanDate = payloanDate;
		this.payloanDateStr=CommonHelper.date2Str(payloanDate, "yyyy-MM-dd");
	}

	public BigDecimal getPayableTotalAmt() {
		return payableTotalAmt;
	}

	public void setPayableTotalAmt(BigDecimal payableTotalAmt) {
		this.payableTotalAmt = payableTotalAmt;
	}

	public Date getPayableDate() {
		return payableDate;
	}

	public void setPayableDate(Date payableDate) {
		this.payableDate = payableDate;
		this.payableDateStr=CommonHelper.date2Str(payableDate, "yyyy-MM-dd");
	}

	public BigDecimal getOverduePi() {
		return overduePi;
	}

	public void setOverduePi(BigDecimal overduePi) {
		this.overduePi = overduePi;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
		this.repayDateStr=CommonHelper.date2Str(repayDate, "yyyy-MM-dd");
	}

	public BigDecimal getPayablePrincipalAmt() {
		return payablePrincipalAmt;
	}

	public void setPayablePrincipalAmt(BigDecimal payablePrincipalAmt) {
		this.payablePrincipalAmt = payablePrincipalAmt;
	}


	public String getPayloanDateStr() {
		return payloanDateStr;
	}

	public String getPayableDateStr() {
		return payableDateStr;
	}

	public String getRepayDateStr() {
		return repayDateStr;
	}

	public BigDecimal getOverdueInt() {
		return overdueInt;
	}

	public void setOverdueInt(BigDecimal overdueInt) {
		this.overdueInt = overdueInt;
	}

	public String getLoanProductCd() {
		return loanProductCd;
	}

	public void setLoanProductCd(String loanProductCd) {
		this.loanProductCd = loanProductCd;
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

	public Date getPrevRpdPayableDate() {
		return prevRpdPayableDate;
	}

	public void setPrevRpdPayableDate(Date prevRpdPayableDate) {
		this.prevRpdPayableDate = prevRpdPayableDate;
	}

	public Date getNextRpdPayableDate() {
		return nextRpdPayableDate;
	}

	public void setNextRpdPayableDate(Date nextRpdPayableDate) {
		this.nextRpdPayableDate = nextRpdPayableDate;
	}

	public Date getLastRepayDate() {
		return lastRepayDate;
	}

	public void setLastRepayDate(Date lastRepayDate) {
		this.lastRepayDate = lastRepayDate;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Long getRepayingPlanId() {
		return repayingPlanId;
	}

	public void setRepayingPlanId(Long repayingPlanId) {
		this.repayingPlanId = repayingPlanId;
	}
	public Long getRepayingPlanDetailId() {
		return repayingPlanDetailId;
	}

	public void setRepayingPlanDetailId(Long repayingPlanDetailId) {
		this.repayingPlanDetailId = repayingPlanDetailId;
	}
	public int getOverdue() {
		return overdue;
	}

	public void setOverdue(int overdue) {
		this.overdue = overdue;
	}


	public int getNormal2Overdue() {
		return normal2Overdue;
	}

	public void setNormal2Overdue(int normal2Overdue) {
		this.normal2Overdue = normal2Overdue;
	}


	public String getRepayModeName() {
		return repayModeName;
	}

	public void setRepayModeName(String repayModeName) {
		this.repayModeName = repayModeName;
	}


	public  class LrRepayItem{
		/**
		 * 期次
		 */
		private Integer period;
		/**
		 * 计划还款日期
		 */
		private String repayingDate;
		
		/**
		 * 应还本金
		 */
		private BigDecimal repayingPricipal;
		
		/**
		 * 应还利息
		 */
		private BigDecimal repayingInterest;

		/**
		 * 应还本息
		 */
		private BigDecimal repayingPricipalInterest;
		
		/**
		 * 已还本金
		 */
		private BigDecimal repayedPricipal = BigDecimal.ZERO;
		
		/**
		 * 已还利息
		 */
		private BigDecimal repayedInterest = BigDecimal.ZERO;
		
		/**
		 * 未还罚息
		 */
		private BigDecimal notyetImposeInterest = BigDecimal.ZERO;
		
		/**
		 * 逾期天数
		 */
		private Integer overdueDay;
		/**
		 * 上次计息时间
		 * */
		private Date lastDate;
		/**
		 * 逾期利率
		 * */
		private BigDecimal overdueRate;
		/**
		 * 状态
		 */
		private String status;

		public  LrRepayItem(Object[] objs) {
			int i = 1;
			this.period = CommonHelper.toInt(objs[i++]);//期次
			this.repayingDate = CommonHelper.toStr(objs[i++]);//应还日期
			this.repayingPricipalInterest =  CommonHelper.toBigDecimal(objs[i++]);//本期应还金额
			this.repayingPricipal = CommonHelper.toBigDecimal(objs[i++]);//本期应还本金
			this.repayingInterest = CommonHelper.toBigDecimal(objs[i++]);//本期应还利息
			this.repayedPricipal = CommonHelper.toBigDecimal(objs[i++]);//已还本金
			this.repayedInterest = CommonHelper.toBigDecimal(objs[i++]);//已还利率
			this.overdueRate = CommonHelper.toBigDecimal(objs[i++]);//逾期利率
			i++;i++;
			this.status = CommonHelper.toStr(objs[i++]);
			this.lastDate = CommonHelper.toDate(objs[i++]);//上次计息时间
			this.notyetImposeInterest = CommonHelper.toBigDecimal(objs[i++]);
		}

		public LrRepayItem() {
			// TODO Auto-generated constructor stub
		}

		public Integer getPeriod() {
			return period;
		}

		public void setPeriod(Integer period) {
			this.period = period;
		}

		public String getRepayingDate() {
			return repayingDate;
		}

		public void setRepayingDate(String repayingDate) {
			this.repayingDate = repayingDate;
		}

		public BigDecimal getRepayingPricipal() {
			return repayingPricipal;
		}

		public void setRepayingPricipal(BigDecimal repayingPricipal) {
			this.repayingPricipal = repayingPricipal;
		}

		public BigDecimal getRepayingInterest() {
			return repayingInterest;
		}

		public void setRepayingInterest(BigDecimal repayingInterest) {
			this.repayingInterest = repayingInterest;
		}

		public BigDecimal getRepayingPricipalInterest() {
			return repayingPricipalInterest;
		}

		public void setRepayingPricipalInterest(BigDecimal repayingPricipalInterest) {
			this.repayingPricipalInterest = repayingPricipalInterest;
		}

		public BigDecimal getRepayedPricipal() {
			return repayedPricipal;
		}

		public void setRepayedPricipal(BigDecimal repayedPricipal) {
			this.repayedPricipal = repayedPricipal;
		}

		public BigDecimal getRepayedInterest() {
			return repayedInterest;
		}

		public void setRepayedInterest(BigDecimal repayedInterest) {
			this.repayedInterest = repayedInterest;
		}

		public BigDecimal getNotyetImposeInterest() {
			return notyetImposeInterest;
		}

		public void setNotyetImposeInterest(BigDecimal notyetImposeInterest) {
			this.notyetImposeInterest = notyetImposeInterest;
		}

		public Integer getOverdueDay() {
			return overdueDay;
		}

		public void setOverdueDay(Integer overdueDay) {
			this.overdueDay = overdueDay;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Date getLastDate() {
			return lastDate;
		}

		public void setLastDate(Date lastDate) {
			this.lastDate = lastDate;
		}

		public BigDecimal getOverdueRate() {
			return overdueRate;
		}

		public void setOverdueRate(BigDecimal overdueRate) {
			this.overdueRate = overdueRate;
		}
		
	}


	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getContractTermUnit() {
		return contractTermUnit;
	}

	public void setContractTermUnit(String contractTermUnit) {
		this.contractTermUnit = contractTermUnit;
	}
}
