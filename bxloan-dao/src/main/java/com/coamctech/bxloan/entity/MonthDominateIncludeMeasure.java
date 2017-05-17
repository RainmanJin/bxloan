package com.coamctech.bxloan.entity;

// default package

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * MonthDominateIncludeMeasure entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MONTH_DOMINATE_INCLUDE_MEASURE", schema = WD_SCHEMA)
public class MonthDominateIncludeMeasure implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private BigDecimal borrowerSalary;// 借款人工资
	private BigDecimal borrowerBonus;// 借款人奖金
	private BigDecimal borrowerWelfare;// 借款人福利
	private BigDecimal borrowerIncome;// 借款人月收入合计
	private BigDecimal borrowerHousingloan;// 借款人房贷
	private BigDecimal borrowerCarloan;// 借款人车贷
	private BigDecimal borrowerCredit;// 借款人信用卡
	private BigDecimal borrowerOthers;// 借款人其他支出
	private BigDecimal borrowerPay;// 借款人月还款额合计
	private BigDecimal borrowerSpouseSalary;// 借款人配偶工资
	private BigDecimal borrowerSpouseBonus;// 借款人配偶奖金
	private BigDecimal borrowerSpouseWelfare;// 借款人配偶福利
	private BigDecimal borrowerSpouseIncome;// 借款人配偶月收入合计
	private BigDecimal borrowerSpouseHousingloan;// 借款人配偶房贷
	private BigDecimal borrowerSpouseCarloan;// 借款人配偶车贷
	private BigDecimal borrowerSpouseCredit;// 借款人配偶信用卡
	private BigDecimal borrowerSpouseOthers;// 借款人配偶其他支出
	private BigDecimal borrowerSpousePay;// 借款人配偶月还款额合计
	private BigDecimal familyIncome;// 家庭月收入合计
	private BigDecimal familyPay;// 家庭月还款额合计
	private BigDecimal familyControllableIncome;// 家庭月可支配收入

	// Constructors

	/** default constructor */
	public MonthDominateIncludeMeasure() {
	}

	/** minimal constructor */
	public MonthDominateIncludeMeasure(Long id) {
		this.id = id;
	}

	/** full constructor */
	public MonthDominateIncludeMeasure(Long id, Long projectId,
			BigDecimal borrowerSalary, BigDecimal borrowerBonus,
			BigDecimal borrowerWelfare, BigDecimal borrowerIncome,
			BigDecimal borrowerHousingloan, BigDecimal borrowerCarloan,
			BigDecimal borrowerCredit, BigDecimal borrowerOthers,
			BigDecimal borrowerPay, BigDecimal borrowerSpouseSalary,
			BigDecimal borrowerSpouseBonus, BigDecimal borrowerSpouseWelfare,
			BigDecimal borrowerSpouseIncome,
			BigDecimal borrowerSpouseHousingloan,
			BigDecimal borrowerSpouseCarloan, BigDecimal borrowerSpouseCredit,
			BigDecimal borrowerSpouseOthers, BigDecimal borrowerSpousePay,
			BigDecimal familyIncome, BigDecimal familyPay,
			BigDecimal familyControllableIncome) {
		this.id = id;
		this.projectId = projectId;
		this.borrowerSalary = borrowerSalary;
		this.borrowerBonus = borrowerBonus;
		this.borrowerWelfare = borrowerWelfare;
		this.borrowerIncome = borrowerIncome;
		this.borrowerHousingloan = borrowerHousingloan;
		this.borrowerCarloan = borrowerCarloan;
		this.borrowerCredit = borrowerCredit;
		this.borrowerOthers = borrowerOthers;
		this.borrowerPay = borrowerPay;
		this.borrowerSpouseSalary = borrowerSpouseSalary;
		this.borrowerSpouseBonus = borrowerSpouseBonus;
		this.borrowerSpouseWelfare = borrowerSpouseWelfare;
		this.borrowerSpouseIncome = borrowerSpouseIncome;
		this.borrowerSpouseHousingloan = borrowerSpouseHousingloan;
		this.borrowerSpouseCarloan = borrowerSpouseCarloan;
		this.borrowerSpouseCredit = borrowerSpouseCredit;
		this.borrowerSpouseOthers = borrowerSpouseOthers;
		this.borrowerSpousePay = borrowerSpousePay;
		this.familyIncome = familyIncome;
		this.familyPay = familyPay;
		this.familyControllableIncome = familyControllableIncome;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PK_SEQ_TBL", sequenceName = "SEQ_MONTH_MEASURE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_TBL")
	@Column(name = "ID", unique = true, nullable = false, precision = 20)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_ID", precision = 12)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "BORROWER_SALARY", precision = 18, scale = 2)
	public BigDecimal getBorrowerSalary() {
		return this.borrowerSalary;
	}

	public void setBorrowerSalary(BigDecimal borrowerSalary) {
		this.borrowerSalary = borrowerSalary;
	}

	@Column(name = "BORROWER_BONUS", precision = 18, scale = 2)
	public BigDecimal getBorrowerBonus() {
		return this.borrowerBonus;
	}

	public void setBorrowerBonus(BigDecimal borrowerBonus) {
		this.borrowerBonus = borrowerBonus;
	}

	@Column(name = "BORROWER_WELFARE", precision = 18, scale = 2)
	public BigDecimal getBorrowerWelfare() {
		return borrowerWelfare;
	}

	public void setBorrowerWelfare(BigDecimal borrowerWelfare) {
		this.borrowerWelfare = borrowerWelfare;
	}

	@Column(name = "BORROWER_INCOME", precision = 18, scale = 2)
	public BigDecimal getBorrowerIncome() {
		return this.borrowerIncome;
	}

	public void setBorrowerIncome(BigDecimal borrowerIncome) {
		this.borrowerIncome = borrowerIncome;
	}

	@Column(name = "BORROWER_HOUSINGLOAN", precision = 18, scale = 2)
	public BigDecimal getBorrowerHousingloan() {
		return this.borrowerHousingloan;
	}

	public void setBorrowerHousingloan(BigDecimal borrowerHousingloan) {
		this.borrowerHousingloan = borrowerHousingloan;
	}

	@Column(name = "BORROWER_CARLOAN", precision = 18, scale = 2)
	public BigDecimal getBorrowerCarloan() {
		return this.borrowerCarloan;
	}

	public void setBorrowerCarloan(BigDecimal borrowerCarloan) {
		this.borrowerCarloan = borrowerCarloan;
	}

	@Column(name = "BORROWER_CREDIT", precision = 18, scale = 2)
	public BigDecimal getBorrowerCredit() {
		return this.borrowerCredit;
	}

	public void setBorrowerCredit(BigDecimal borrowerCredit) {
		this.borrowerCredit = borrowerCredit;
	}

	@Column(name = "BORROWER_OTHERS", precision = 18, scale = 2)
	public BigDecimal getBorrowerOthers() {
		return this.borrowerOthers;
	}

	public void setBorrowerOthers(BigDecimal borrowerOthers) {
		this.borrowerOthers = borrowerOthers;
	}

	@Column(name = "BORROWER_PAY", precision = 18, scale = 2)
	public BigDecimal getBorrowerPay() {
		return this.borrowerPay;
	}

	public void setBorrowerPay(BigDecimal borrowerPay) {
		this.borrowerPay = borrowerPay;
	}

	@Column(name = "BORROWER_SPOUSE_SALARY", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpouseSalary() {
		return this.borrowerSpouseSalary;
	}

	public void setBorrowerSpouseSalary(BigDecimal borrowerSpouseSalary) {
		this.borrowerSpouseSalary = borrowerSpouseSalary;
	}

	@Column(name = "BORROWER_SPOUSE_BONUS", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpouseBonus() {
		return this.borrowerSpouseBonus;
	}

	public void setBorrowerSpouseBonus(BigDecimal borrowerSpouseBonus) {
		this.borrowerSpouseBonus = borrowerSpouseBonus;
	}

	@Column(name = "BORROWER_SPOUSE_WELFARE", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpouseWelfare() {
		return this.borrowerSpouseWelfare;
	}

	public void setBorrowerSpouseWelfare(BigDecimal borrowerSpouseWelfare) {
		this.borrowerSpouseWelfare = borrowerSpouseWelfare;
	}

	@Column(name = "BORROWER_SPOUSE_INCOME", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpouseIncome() {
		return this.borrowerSpouseIncome;
	}

	public void setBorrowerSpouseIncome(BigDecimal borrowerSpouseIncome) {
		this.borrowerSpouseIncome = borrowerSpouseIncome;
	}

	@Column(name = "BORROWER_SPOUSE_HOUSINGLOAN", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpouseHousingloan() {
		return this.borrowerSpouseHousingloan;
	}

	public void setBorrowerSpouseHousingloan(
			BigDecimal borrowerSpouseHousingloan) {
		this.borrowerSpouseHousingloan = borrowerSpouseHousingloan;
	}

	@Column(name = "BORROWER_SPOUSE_CARLOAN", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpouseCarloan() {
		return this.borrowerSpouseCarloan;
	}

	public void setBorrowerSpouseCarloan(BigDecimal borrowerSpouseCarloan) {
		this.borrowerSpouseCarloan = borrowerSpouseCarloan;
	}

	@Column(name = "BORROWER_SPOUSE_CREDIT", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpouseCredit() {
		return this.borrowerSpouseCredit;
	}

	public void setBorrowerSpouseCredit(BigDecimal borrowerSpouseCredit) {
		this.borrowerSpouseCredit = borrowerSpouseCredit;
	}

	@Column(name = "BORROWER_SPOUSE_OTHERS", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpouseOthers() {
		return this.borrowerSpouseOthers;
	}

	public void setBorrowerSpouseOthers(BigDecimal borrowerSpouseOthers) {
		this.borrowerSpouseOthers = borrowerSpouseOthers;
	}

	@Column(name = "BORROWER_SPOUSE_PAY", precision = 18, scale = 2)
	public BigDecimal getBorrowerSpousePay() {
		return this.borrowerSpousePay;
	}

	public void setBorrowerSpousePay(BigDecimal borrowerSpousePay) {
		this.borrowerSpousePay = borrowerSpousePay;
	}

	@Column(name = "FAMILY_INCOME", precision = 18, scale = 2)
	public BigDecimal getFamilyIncome() {
		return this.familyIncome;
	}

	public void setFamilyIncome(BigDecimal familyIncome) {
		this.familyIncome = familyIncome;
	}

	@Column(name = "FAMILY_PAY", precision = 18, scale = 2)
	public BigDecimal getFamilyPay() {
		return this.familyPay;
	}

	public void setFamilyPay(BigDecimal familyPay) {
		this.familyPay = familyPay;
	}

	@Column(name = "FAMILY_CONTROLLABLE_INCOME", precision = 18, scale = 2)
	public BigDecimal getFamilyControllableIncome() {
		return this.familyControllableIncome;
	}

	public void setFamilyControllableIncome(BigDecimal familyControllableIncome) {
		this.familyControllableIncome = familyControllableIncome;
	}

}