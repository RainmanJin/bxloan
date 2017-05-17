package com.coamctech.bxloan.web.vo.businessapplicationwd;

import java.math.BigDecimal;

public class FinanceVO {

	private Long id;
	private Long projectId;// 业务ID
	private BigDecimal assetsTotalAmt;// 资产总金额
	private BigDecimal liabilitiesTotalAmt;// 负债总金额
	private BigDecimal assCashAmt;// 现金/存款
	private BigDecimal assReceivablesAmt;// 应收款
	private BigDecimal assStockAmt;// 存货
	private BigDecimal assHousePropertyAmt;// 房产
	private BigDecimal assCarAmt;// 车辆
	private BigDecimal assOtherAmt;// 其他资产
	private BigDecimal liaBusinessLoanAmt;// 经营型贷款
	private BigDecimal liaHouseloanAmt;// 房贷
	private BigDecimal liaCarloanAmt;// 车贷
	private BigDecimal liaCreditcard;// 信用卡
	private BigDecimal incomeMonthBussAmt;// 月营业额
	private BigDecimal incomeOtherAmt;// 其它收入
	private BigDecimal grossprofitAmt;// 毛利润
	private BigDecimal familypayAmt;// 家庭开支
	private BigDecimal netprofitAmt;// 净利润
	private BigDecimal otherloanMonthrepayAmt;// 其他贷款月还款额
	private BigDecimal monthdominatIncomeAmt;// 月可支配收入
	private String finalcialType;// 1:受薪2：经营、企业
	private BigDecimal liaOtherAmt;// 其他负债
	private BigDecimal familymonthwageAmt;// 家庭月工资收入
	private BigDecimal familyPayAmt;// 家庭开资
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public BigDecimal getAssetsTotalAmt() {
		return assetsTotalAmt;
	}
	public void setAssetsTotalAmt(BigDecimal assetsTotalAmt) {
		this.assetsTotalAmt = assetsTotalAmt;
	}
	public BigDecimal getLiabilitiesTotalAmt() {
		return liabilitiesTotalAmt;
	}
	public void setLiabilitiesTotalAmt(BigDecimal liabilitiesTotalAmt) {
		this.liabilitiesTotalAmt = liabilitiesTotalAmt;
	}
	public BigDecimal getAssCashAmt() {
		return assCashAmt;
	}
	public void setAssCashAmt(BigDecimal assCashAmt) {
		this.assCashAmt = assCashAmt;
	}
	public BigDecimal getAssReceivablesAmt() {
		return assReceivablesAmt;
	}
	public void setAssReceivablesAmt(BigDecimal assReceivablesAmt) {
		this.assReceivablesAmt = assReceivablesAmt;
	}
	public BigDecimal getAssStockAmt() {
		return assStockAmt;
	}
	public void setAssStockAmt(BigDecimal assStockAmt) {
		this.assStockAmt = assStockAmt;
	}
	public BigDecimal getAssHousePropertyAmt() {
		return assHousePropertyAmt;
	}
	public void setAssHousePropertyAmt(BigDecimal assHousePropertyAmt) {
		this.assHousePropertyAmt = assHousePropertyAmt;
	}
	public BigDecimal getAssCarAmt() {
		return assCarAmt;
	}
	public void setAssCarAmt(BigDecimal assCarAmt) {
		this.assCarAmt = assCarAmt;
	}
	public BigDecimal getAssOtherAmt() {
		return assOtherAmt;
	}
	public void setAssOtherAmt(BigDecimal assOtherAmt) {
		this.assOtherAmt = assOtherAmt;
	}
	public BigDecimal getLiaBusinessLoanAmt() {
		return liaBusinessLoanAmt;
	}
	public void setLiaBusinessLoanAmt(BigDecimal liaBusinessLoanAmt) {
		this.liaBusinessLoanAmt = liaBusinessLoanAmt;
	}
	public BigDecimal getLiaHouseloanAmt() {
		return liaHouseloanAmt;
	}
	public void setLiaHouseloanAmt(BigDecimal liaHouseloanAmt) {
		this.liaHouseloanAmt = liaHouseloanAmt;
	}
	public BigDecimal getLiaCarloanAmt() {
		return liaCarloanAmt;
	}
	public void setLiaCarloanAmt(BigDecimal liaCarloanAmt) {
		this.liaCarloanAmt = liaCarloanAmt;
	}
	public BigDecimal getLiaCreditcard() {
		return liaCreditcard;
	}
	public void setLiaCreditcard(BigDecimal liaCreditcard) {
		this.liaCreditcard = liaCreditcard;
	}
	public BigDecimal getIncomeMonthBussAmt() {
		return incomeMonthBussAmt;
	}
	public void setIncomeMonthBussAmt(BigDecimal incomeMonthBussAmt) {
		this.incomeMonthBussAmt = incomeMonthBussAmt;
	}
	public BigDecimal getIncomeOtherAmt() {
		return incomeOtherAmt;
	}
	public void setIncomeOtherAmt(BigDecimal incomeOtherAmt) {
		this.incomeOtherAmt = incomeOtherAmt;
	}
	public BigDecimal getGrossprofitAmt() {
		return grossprofitAmt;
	}
	public void setGrossprofitAmt(BigDecimal grossprofitAmt) {
		this.grossprofitAmt = grossprofitAmt;
	}
	public BigDecimal getFamilypayAmt() {
		return familypayAmt;
	}
	public void setFamilypayAmt(BigDecimal familypayAmt) {
		this.familypayAmt = familypayAmt;
	}
	public BigDecimal getNetprofitAmt() {
		return netprofitAmt;
	}
	public void setNetprofitAmt(BigDecimal netprofitAmt) {
		this.netprofitAmt = netprofitAmt;
	}
	public BigDecimal getOtherloanMonthrepayAmt() {
		return otherloanMonthrepayAmt;
	}
	public void setOtherloanMonthrepayAmt(BigDecimal otherloanMonthrepayAmt) {
		this.otherloanMonthrepayAmt = otherloanMonthrepayAmt;
	}
	public BigDecimal getMonthdominatIncomeAmt() {
		return monthdominatIncomeAmt;
	}
	public void setMonthdominatIncomeAmt(BigDecimal monthdominatIncomeAmt) {
		this.monthdominatIncomeAmt = monthdominatIncomeAmt;
	}
	public String getFinalcialType() {
		return finalcialType;
	}
	public void setFinalcialType(String finalcialType) {
		this.finalcialType = finalcialType;
	}
	public BigDecimal getLiaOtherAmt() {
		return liaOtherAmt;
	}
	public void setLiaOtherAmt(BigDecimal liaOtherAmt) {
		this.liaOtherAmt = liaOtherAmt;
	}
	public BigDecimal getFamilymonthwageAmt() {
		return familymonthwageAmt;
	}
	public void setFamilymonthwageAmt(BigDecimal familymonthwageAmt) {
		this.familymonthwageAmt = familymonthwageAmt;
	}
	public BigDecimal getFamilyPayAmt() {
		return familyPayAmt;
	}
	public void setFamilyPayAmt(BigDecimal familyPayAmt) {
		this.familyPayAmt = familyPayAmt;
	}
}
