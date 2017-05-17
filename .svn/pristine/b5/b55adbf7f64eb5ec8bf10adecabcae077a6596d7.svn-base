package com.coamctech.bxloan.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.entity.UuidEntity;

/**
 * 客户简要财务信息表
 * CustomerBrieflyFinace entity. 
 * @author MyEclipse Persistence Tools
 * @modifyer gph
 * @modify date 20150526 1439
 */
@Entity
@Table(name = "CUSTOMER_BRIEFLY_FINANCE", schema = GlobalConstants.WD_SCHEMA)
public class CustomerBrieflyFinace  implements java.io.Serializable {
	private static final long serialVersionUID = -381296395608952940L;
	
	/**编号:主键*/
	private BigDecimal customerFinanceId;
	/**本地房产情况</br>1：有，2：无*/
	private BigDecimal localHouseCondition;
	/**本地房产数量*/
	private String localHouseNum;
	/**房产总价值*/
	private BigDecimal houseesPrice;
	/**车辆情况</br>1：有，2：无*/
	private String carCondition;
	/**房产贷款*/
	private BigDecimal houseesLoan;
	/**车辆数量*/
	private BigDecimal carNum;
	/**贷款人贷款笔数*/
	private BigDecimal loanNum;
	/**贷款余额*/
	private BigDecimal loanBalance;
	/**月还款额*/
	private BigDecimal repaymentMonth;
	/**最高贷款额*/
	private BigDecimal highestLoanQuota;
	/**最高额度贷款行*/
	private String highestLoanBank;
	/**借款人税后月收入(元)*/
	private BigDecimal monthIncome;
	/**参与人ID*/
	private Long partyId;
	// Constructors

	/** default constructor */
	public CustomerBrieflyFinace() {
	}

	/** minimal constructor */
	public CustomerBrieflyFinace(BigDecimal customerFinanceId) {
		this.customerFinanceId = customerFinanceId;
	}

	/** full constructor */
	public CustomerBrieflyFinace(BigDecimal customerFinanceId,
			BigDecimal localHouseCondition, String localHouseNum,
			BigDecimal houseesPrice, String carCondition,
			BigDecimal houseesLoan, BigDecimal carNum, BigDecimal loanNum,
			BigDecimal loanBalance, BigDecimal repaymentMonth,
			BigDecimal highestLoanQuota, String highestLoanBank,
			BigDecimal monthIncome) {
		this.customerFinanceId = customerFinanceId;
		this.localHouseCondition = localHouseCondition;
		this.localHouseNum = localHouseNum;
		this.houseesPrice = houseesPrice;
		this.carCondition = carCondition;
		this.houseesLoan = houseesLoan;
		this.carNum = carNum;
		this.loanNum = loanNum;
		this.loanBalance = loanBalance;
		this.repaymentMonth = repaymentMonth;
		this.highestLoanQuota = highestLoanQuota;
		this.highestLoanBank = highestLoanBank;
		this.monthIncome = monthIncome;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_CUSTOMER_BRIEFLY_FINANCE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "CUSTOMER_FINANCE_ID", unique = true, nullable = false, precision = 20, scale = 0)
	public BigDecimal getCustomerFinanceId() {
		return this.customerFinanceId;
	}

	public void setCustomerFinanceId(BigDecimal customerFinanceId) {
		this.customerFinanceId = customerFinanceId;
	}

	@Column(name = "LOCAL_HOUSE_CONDITION", precision = 20, scale = 0)
	public BigDecimal getLocalHouseCondition() {
		return this.localHouseCondition;
	}

	public void setLocalHouseCondition(BigDecimal localHouseCondition) {
		this.localHouseCondition = localHouseCondition;
	}

	@Column(name = "LOCAL_HOUSE_NUM", length = 50)
	public String getLocalHouseNum() {
		return this.localHouseNum;
	}

	public void setLocalHouseNum(String localHouseNum) {
		this.localHouseNum = localHouseNum;
	}

	@Column(name = "HOUSEES_PRICE", precision = 20, scale = 0)
	public BigDecimal getHouseesPrice() {
		return this.houseesPrice;
	}

	public void setHouseesPrice(BigDecimal houseesPrice) {
		this.houseesPrice = houseesPrice;
	}

	@Column(name = "CAR_CONDITION", length = 50)
	public String getCarCondition() {
		return this.carCondition;
	}

	public void setCarCondition(String carCondition) {
		this.carCondition = carCondition;
	}

	@Column(name = "HOUSEES_LOAN", precision = 20, scale = 0)
	public BigDecimal getHouseesLoan() {
		return this.houseesLoan;
	}

	public void setHouseesLoan(BigDecimal houseesLoan) {
		this.houseesLoan = houseesLoan;
	}

	@Column(name = "CAR_NUM", precision = 20, scale = 0)
	public BigDecimal getCarNum() {
		return this.carNum;
	}

	public void setCarNum(BigDecimal carNum) {
		this.carNum = carNum;
	}

	@Column(name = "LOAN_NUM", precision = 20, scale = 0)
	public BigDecimal getLoanNum() {
		return this.loanNum;
	}

	public void setLoanNum(BigDecimal loanNum) {
		this.loanNum = loanNum;
	}

	@Column(name = "LOAN_BALANCE", precision = 20, scale = 0)
	public BigDecimal getLoanBalance() {
		return this.loanBalance;
	}

	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}

	@Column(name = "REPAYMENT_MONTH", precision = 20, scale = 0)
	public BigDecimal getRepaymentMonth() {
		return this.repaymentMonth;
	}

	public void setRepaymentMonth(BigDecimal repaymentMonth) {
		this.repaymentMonth = repaymentMonth;
	}

	@Column(name = "HIGHEST_LOAN_QUOTA", precision = 20, scale = 0)
	public BigDecimal getHighestLoanQuota() {
		return this.highestLoanQuota;
	}

	public void setHighestLoanQuota(BigDecimal highestLoanQuota) {
		this.highestLoanQuota = highestLoanQuota;
	}

	@Column(name = "HIGHEST_LOAN_BANK",length = 200)
	public String getHighestLoanBank() {
		return this.highestLoanBank;
	}

	public void setHighestLoanBank(String highestLoanBank) {
		this.highestLoanBank = highestLoanBank;
	}

	@Column(name = "MONTH_INCOME", precision = 20, scale = 0)
	public BigDecimal getMonthIncome() {
		return this.monthIncome;
	}

	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}
	
	@Column(name = "PARTY_ID", precision = 20, scale = 0)
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	
}