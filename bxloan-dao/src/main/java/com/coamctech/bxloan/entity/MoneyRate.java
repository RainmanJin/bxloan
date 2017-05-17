package com.coamctech.bxloan.entity;
// default package

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MoneyRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MONEY_RATE", schema =WD_SCHEMA)
public class MoneyRate implements java.io.Serializable {

	// Fields

	private Long moneyRateId;
	private String timeLimit;
	private BigDecimal interestRate;
	private String valid;//利率是否有效；1有效 0无效
	private Date updateDate;
	private String lastmoduser;
	private Date endDate;
	private Date startDate;

	// Constructors

	/** default constructor */
	public MoneyRate() {
	}

	/** minimal constructor */
	public MoneyRate(Long moneyRateId) {
		this.moneyRateId = moneyRateId;
	}

	/** full constructor */
	public MoneyRate(Long moneyRateId, String timeLimit, BigDecimal interestRate,
			String valid, Date updateDate, String lastmoduser,
			Date endDate, Date startDate) {
		this.moneyRateId = moneyRateId;
		this.timeLimit = timeLimit;
		this.interestRate = interestRate;
		this.valid = valid;
		this.updateDate = updateDate;
		this.lastmoduser = lastmoduser;
		this.endDate = endDate;
		this.startDate = startDate;
	}

	// Property accessors
	@Id
	@Column(name = "MONEY_RATE_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getMoneyRateId() {
		return this.moneyRateId;
	}

	public void setMoneyRateId(Long moneyRateId) {
		this.moneyRateId = moneyRateId;
	}

	@Column(name = "TIME_LIMIT", length = 8)
	public String getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Column(name = "INTEREST_RATE", precision = 30, scale = 6)
	public BigDecimal getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	@Column(name = "VALID", length = 10)
	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column(name = "UPDATE_DATE", length = 11)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "LASTMODUSER")
	public String getLastmoduser() {
		return this.lastmoduser;
	}

	public void setLastmoduser(String lastmoduser) {
		this.lastmoduser = lastmoduser;
	}

	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "START_DATE", length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}