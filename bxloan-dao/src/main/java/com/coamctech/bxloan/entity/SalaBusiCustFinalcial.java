package com.coamctech.bxloan.entity;

// default package

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * SalaBusiCustFinalcial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SALA_BUSI_CUST_FINALCIAL", schema = WD_SCHEMA)
public class SalaBusiCustFinalcial implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private BigDecimal assetsTotalAmt;
	private BigDecimal liabilitiesTotalAmt;
	private BigDecimal assCashAmt;
	private BigDecimal assReceivablesAmt;
	private BigDecimal assStockAmt;
	private BigDecimal assHousePropertyAmt;
	private BigDecimal assCarAmt;
	private BigDecimal assOtherAmt;
	private BigDecimal liaBusinessLoanAmt;
	private BigDecimal liaHouseloanAmt;
	private BigDecimal liaCarloanAmt;
	private BigDecimal liaCreditcard;
	private BigDecimal incomeMonthBussAmt;
	private BigDecimal incomeOtherAmt;
	private BigDecimal grossprofitAmt;
	private BigDecimal familypayAmt;
	private BigDecimal netprofitAmt;
	private BigDecimal otherloanMonthrepayAmt;
	private BigDecimal monthdominatIncomeAmt;
	private String finalcialType;
	private BigDecimal liaOtherAmt;
	private BigDecimal familymonthwageAmt;
	private BigDecimal familyPayAmt;
	private Date createDtate;
	private BigDecimal yearBussiAmt;
	private BigDecimal proprietorship;

	// Constructors

	/** default constructor */
	public SalaBusiCustFinalcial() {
	}

	/** minimal constructor */
	public SalaBusiCustFinalcial(Long id) {
		this.id = id;
	}

	/** full constructor */
	public SalaBusiCustFinalcial(Long id, Long projectId,
			BigDecimal assetsTotalAmt, BigDecimal liabilitiesTotalAmt,
			BigDecimal assCashAmt, BigDecimal assReceivablesAmt,
			BigDecimal assStockAmt, BigDecimal assHousePropertyAmt,
			BigDecimal assCarAmt, BigDecimal assOtherAmt,
			BigDecimal liaBusinessLoanAmt, BigDecimal liaHouseloanAmt,
			BigDecimal liaCarloanAmt, BigDecimal liaCreditcard,
			BigDecimal incomeMonthBussAmt, BigDecimal incomeOtherAmt,
			BigDecimal grossprofitAmt, BigDecimal familypayAmt,
			BigDecimal netprofitAmt, BigDecimal otherloanMonthrepayAmt,
			BigDecimal monthdominatIncomeAmt, String finalcialType,
			BigDecimal liaOtherAmt, BigDecimal familymonthwageAmt,
			BigDecimal familyPayAmt, Date createDtate, BigDecimal yearBussiAmt) {
		this.id = id;
		this.projectId = projectId;
		this.assetsTotalAmt = assetsTotalAmt;
		this.liabilitiesTotalAmt = liabilitiesTotalAmt;
		this.assCashAmt = assCashAmt;
		this.assReceivablesAmt = assReceivablesAmt;
		this.assStockAmt = assStockAmt;
		this.assHousePropertyAmt = assHousePropertyAmt;
		this.assCarAmt = assCarAmt;
		this.assOtherAmt = assOtherAmt;
		this.liaBusinessLoanAmt = liaBusinessLoanAmt;
		this.liaHouseloanAmt = liaHouseloanAmt;
		this.liaCarloanAmt = liaCarloanAmt;
		this.liaCreditcard = liaCreditcard;
		this.incomeMonthBussAmt = incomeMonthBussAmt;
		this.incomeOtherAmt = incomeOtherAmt;
		this.grossprofitAmt = grossprofitAmt;
		this.familypayAmt = familypayAmt;
		this.netprofitAmt = netprofitAmt;
		this.otherloanMonthrepayAmt = otherloanMonthrepayAmt;
		this.monthdominatIncomeAmt = monthdominatIncomeAmt;
		this.finalcialType = finalcialType;
		this.liaOtherAmt = liaOtherAmt;
		this.familymonthwageAmt = familymonthwageAmt;
		this.familyPayAmt = familyPayAmt;
		this.createDtate = createDtate;
		this.yearBussiAmt = yearBussiAmt;
	}

	// Property accessors

	@Id
	@SequenceGenerator(name = "PK_SEQ_TBL", sequenceName = "SEQ_FINALCIAL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_TBL")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_ID", precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "ASSETS_TOTAL_AMT", precision = 18, scale = 2)
	public BigDecimal getAssetsTotalAmt() {
		return this.assetsTotalAmt;
	}

	public void setAssetsTotalAmt(BigDecimal assetsTotalAmt) {
		this.assetsTotalAmt = assetsTotalAmt;
	}

	@Column(name = "LIABILITIES_TOTAL_AMT", precision = 18, scale = 2)
	public BigDecimal getLiabilitiesTotalAmt() {
		return this.liabilitiesTotalAmt;
	}

	public void setLiabilitiesTotalAmt(BigDecimal liabilitiesTotalAmt) {
		this.liabilitiesTotalAmt = liabilitiesTotalAmt;
	}

	@Column(name = "ASS_CASH_AMT", precision = 18, scale = 2)
	public BigDecimal getAssCashAmt() {
		return this.assCashAmt;
	}

	public void setAssCashAmt(BigDecimal assCashAmt) {
		this.assCashAmt = assCashAmt;
	}

	@Column(name = "ASS_RECEIVABLES_AMT", precision = 18, scale = 2)
	public BigDecimal getAssReceivablesAmt() {
		return this.assReceivablesAmt;
	}

	public void setAssReceivablesAmt(BigDecimal assReceivablesAmt) {
		this.assReceivablesAmt = assReceivablesAmt;
	}

	@Column(name = "ASS_STOCK_AMT", precision = 18, scale = 2)
	public BigDecimal getAssStockAmt() {
		return this.assStockAmt;
	}

	public void setAssStockAmt(BigDecimal assStockAmt) {
		this.assStockAmt = assStockAmt;
	}

	@Column(name = "ASS_HOUSE_PROPERTY_AMT", precision = 18, scale = 2)
	public BigDecimal getAssHousePropertyAmt() {
		return this.assHousePropertyAmt;
	}

	public void setAssHousePropertyAmt(BigDecimal assHousePropertyAmt) {
		this.assHousePropertyAmt = assHousePropertyAmt;
	}

	@Column(name = "ASS_CAR_AMT", precision = 18, scale = 2)
	public BigDecimal getAssCarAmt() {
		return this.assCarAmt;
	}

	public void setAssCarAmt(BigDecimal assCarAmt) {
		this.assCarAmt = assCarAmt;
	}

	@Column(name = "ASS_OTHER_AMT", precision = 18, scale = 2)
	public BigDecimal getAssOtherAmt() {
		return this.assOtherAmt;
	}

	public void setAssOtherAmt(BigDecimal assOtherAmt) {
		this.assOtherAmt = assOtherAmt;
	}

	@Column(name = "LIA_BUSINESS_LOAN_AMT", precision = 18, scale = 2)
	public BigDecimal getLiaBusinessLoanAmt() {
		return this.liaBusinessLoanAmt;
	}

	public void setLiaBusinessLoanAmt(BigDecimal liaBusinessLoanAmt) {
		this.liaBusinessLoanAmt = liaBusinessLoanAmt;
	}

	@Column(name = "LIA_HOUSELOAN_AMT", precision = 18, scale = 2)
	public BigDecimal getLiaHouseloanAmt() {
		return this.liaHouseloanAmt;
	}

	public void setLiaHouseloanAmt(BigDecimal liaHouseloanAmt) {
		this.liaHouseloanAmt = liaHouseloanAmt;
	}

	@Column(name = "LIA_CARLOAN_AMT", precision = 18, scale = 2)
	public BigDecimal getLiaCarloanAmt() {
		return this.liaCarloanAmt;
	}

	public void setLiaCarloanAmt(BigDecimal liaCarloanAmt) {
		this.liaCarloanAmt = liaCarloanAmt;
	}

	@Column(name = "LIA_CREDITCARD", precision = 18, scale = 2)
	public BigDecimal getLiaCreditcard() {
		return this.liaCreditcard;
	}

	public void setLiaCreditcard(BigDecimal liaCreditcard) {
		this.liaCreditcard = liaCreditcard;
	}

	@Column(name = "INCOME_MONTH_BUSS_AMT", precision = 18, scale = 2)
	public BigDecimal getIncomeMonthBussAmt() {
		return this.incomeMonthBussAmt;
	}

	public void setIncomeMonthBussAmt(BigDecimal incomeMonthBussAmt) {
		this.incomeMonthBussAmt = incomeMonthBussAmt;
	}

	@Column(name = "INCOME_OTHER_AMT", precision = 18, scale = 2)
	public BigDecimal getIncomeOtherAmt() {
		return this.incomeOtherAmt;
	}

	public void setIncomeOtherAmt(BigDecimal incomeOtherAmt) {
		this.incomeOtherAmt = incomeOtherAmt;
	}

	@Column(name = "GROSSPROFIT_AMT", precision = 18, scale = 2)
	public BigDecimal getGrossprofitAmt() {
		return this.grossprofitAmt;
	}

	public void setGrossprofitAmt(BigDecimal grossprofitAmt) {
		this.grossprofitAmt = grossprofitAmt;
	}

	@Column(name = "FAMILYPAY_AMT", precision = 18, scale = 2)
	public BigDecimal getFamilypayAmt() {
		return this.familypayAmt;
	}

	public void setFamilypayAmt(BigDecimal familypayAmt) {
		this.familypayAmt = familypayAmt;
	}

	@Column(name = "NETPROFIT_AMT", precision = 18, scale = 2)
	public BigDecimal getNetprofitAmt() {
		return this.netprofitAmt;
	}

	public void setNetprofitAmt(BigDecimal netprofitAmt) {
		this.netprofitAmt = netprofitAmt;
	}

	@Column(name = "OTHERLOAN_MONTHREPAY_AMT", precision = 18, scale = 2)
	public BigDecimal getOtherloanMonthrepayAmt() {
		return this.otherloanMonthrepayAmt;
	}

	public void setOtherloanMonthrepayAmt(BigDecimal otherloanMonthrepayAmt) {
		this.otherloanMonthrepayAmt = otherloanMonthrepayAmt;
	}

	@Column(name = "MONTHDOMINAT_INCOME_AMT", precision = 18, scale = 2)
	public BigDecimal getMonthdominatIncomeAmt() {
		return this.monthdominatIncomeAmt;
	}

	public void setMonthdominatIncomeAmt(BigDecimal monthdominatIncomeAmt) {
		this.monthdominatIncomeAmt = monthdominatIncomeAmt;
	}

	@Column(name = "FINALCIAL_TYPE", length = 2)
	public String getFinalcialType() {
		return this.finalcialType;
	}

	public void setFinalcialType(String finalcialType) {
		this.finalcialType = finalcialType;
	}

	@Column(name = "LIA_OTHER_AMT", precision = 18, scale = 2)
	public BigDecimal getLiaOtherAmt() {
		return this.liaOtherAmt;
	}

	public void setLiaOtherAmt(BigDecimal liaOtherAmt) {
		this.liaOtherAmt = liaOtherAmt;
	}

	@Column(name = "FAMILYMONTHWAGE_AMT", precision = 18, scale = 2)
	public BigDecimal getFamilymonthwageAmt() {
		return this.familymonthwageAmt;
	}

	public void setFamilymonthwageAmt(BigDecimal familymonthwageAmt) {
		this.familymonthwageAmt = familymonthwageAmt;
	}

	@Column(name = "FAMILY_PAY_AMT", precision = 18, scale = 2)
	public BigDecimal getFamilyPayAmt() {
		return this.familyPayAmt;
	}

	public void setFamilyPayAmt(BigDecimal familyPayAmt) {
		this.familyPayAmt = familyPayAmt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DTATE", length = 7)
	public Date getCreateDtate() {
		return this.createDtate;
	}

	public void setCreateDtate(Date createDtate) {
		this.createDtate = createDtate;
	}

	@Column(name = "YEAR_BUSSI_AMT", precision = 18, scale = 2)
	public BigDecimal getYearBussiAmt() {
		return this.yearBussiAmt;
	}

	public void setYearBussiAmt(BigDecimal yearBussiAmt) {
		this.yearBussiAmt = yearBussiAmt;
	}

	@Column(name = "PROPRIETORSHIP", precision = 18, scale = 2)
	public BigDecimal getProprietorship() {
		return proprietorship;
	}

	public void setProprietorship(BigDecimal proprietorship) {
		this.proprietorship = proprietorship;
	}

}