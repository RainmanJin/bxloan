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

/**
 * 客户经营信息表
 * CustomerBusinessInfo entity. 
 * @author MyEclipse Persistence Tools
 * @modifyer gph
 * @modify date 20150526 1431
 */
@Entity
@Table(name = "CUSTOMER_BUSINESS_INFO", schema = GlobalConstants.WD_SCHEMA)
public class CustomerBusinessInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1365129720161187101L;
	/**编号：主键*/
	private Long id;
	/**参与人Id*/
	private Long partyId;
	/**经营内容*/
	private String busiContent;
	/**经营年限*/
	private Float busiAge;
	/**商品名*/
	private String merchantName;
	/**地址*/
	private String address;
	/**年销售额*/
	private BigDecimal yearSellAmt;
	/**年净利润*/
	private BigDecimal yearNetprofitAmt;
	/**资产总额*/
	private BigDecimal assetsTotalAmt;
	/**负债总额*/
	private BigDecimal liabilitiesTotalAmt;
	/**其他经营项目*/
	private String otherProject;
	/**其他项目资产总额*/
	private BigDecimal otherAssetsTotalAmt;
	/**其他项目年收入*/
	private BigDecimal otherYearIncome;

	// Constructors

	/** default constructor */
	public CustomerBusinessInfo() {
	}

	/** minimal constructor */
	public CustomerBusinessInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CustomerBusinessInfo(Long id, Long partyId,
			String busiContent, Float busiAge, String merchantName,
			String address, BigDecimal yearSellAmt, BigDecimal yearNetprofitAmt,
			BigDecimal assetsTotalAmt, BigDecimal liabilitiesTotalAmt,
			String otherProject, BigDecimal otherAssetsTotalAmt,
			BigDecimal otherYearIncome) {
		this.id = id;
		this.partyId = partyId;
		this.busiContent = busiContent;
		this.busiAge = busiAge;
		this.merchantName = merchantName;
		this.address = address;
		this.yearSellAmt = yearSellAmt;
		this.yearNetprofitAmt = yearNetprofitAmt;
		this.assetsTotalAmt = assetsTotalAmt;
		this.liabilitiesTotalAmt = liabilitiesTotalAmt;
		this.otherProject = otherProject;
		this.otherAssetsTotalAmt = otherAssetsTotalAmt;
		this.otherYearIncome = otherYearIncome;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_CUSTOMER_BUSINESS_INFO", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PARTY_ID", precision = 20, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "BUSI_CONTENT", length = 200)
	public String getBusiContent() {
		return this.busiContent;
	}

	public void setBusiContent(String busiContent) {
		this.busiContent = busiContent;
	}

	@Column(name = "BUSI_AGE", precision = 4, scale = 0)
	public Float getBusiAge() {
		return this.busiAge;
	}

	public void setBusiAge(Float busiAge) {
		this.busiAge = busiAge;
	}

	@Column(name = "MERCHANT_NAME", length = 200)
	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "YEAR_SELL_AMT", precision = 18)
	public BigDecimal getYearSellAmt() {
		return this.yearSellAmt;
	}

	public void setYearSellAmt(BigDecimal yearSellAmt) {
		this.yearSellAmt = yearSellAmt;
	}

	@Column(name = "YEAR_NETPROFIT_AMT", precision = 18)
	public BigDecimal getYearNetprofitAmt() {
		return this.yearNetprofitAmt;
	}

	public void setYearNetprofitAmt(BigDecimal yearNetprofitAmt) {
		this.yearNetprofitAmt = yearNetprofitAmt;
	}

	@Column(name = "ASSETS_TOTAL_AMT", precision = 18)
	public BigDecimal getAssetsTotalAmt() {
		return this.assetsTotalAmt;
	}

	public void setAssetsTotalAmt(BigDecimal assetsTotalAmt) {
		this.assetsTotalAmt = assetsTotalAmt;
	}

	@Column(name = "LIABILITIES_TOTAL_AMT", precision = 18)
	public BigDecimal getLiabilitiesTotalAmt() {
		return this.liabilitiesTotalAmt;
	}

	public void setLiabilitiesTotalAmt(BigDecimal liabilitiesTotalAmt) {
		this.liabilitiesTotalAmt = liabilitiesTotalAmt;
	}
	
	@Column(name = "OTHER_PROJECT", length = 200)
	public String getOtherProject() {
		return this.otherProject;
	}

	public void setOtherProject(String otherProject) {
		this.otherProject = otherProject;
	}

	@Column(name = "OTHER_ASSETS_TOTAL_AMT", precision = 18)
	public BigDecimal getOtherAssetsTotalAmt() {
		return this.otherAssetsTotalAmt;
	}

	public void setOtherAssetsTotalAmt(BigDecimal otherAssetsTotalAmt) {
		this.otherAssetsTotalAmt = otherAssetsTotalAmt;
	}

	@Column(name = "OTHER_YEAR_INCOME", precision = 18)
	public BigDecimal getOtherYearIncome() {
		return this.otherYearIncome;
	}

	public void setOtherYearIncome(BigDecimal otherYearIncome) {
		this.otherYearIncome = otherYearIncome;
	}

}