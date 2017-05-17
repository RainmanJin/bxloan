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
 * FamilyAssetsDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FAMILY_ASSETS_DETAIL", schema = WD_SCHEMA)
public class FamilyAssetsDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private String premisesPermitHouseone; // 按揭房产房产证号
	private Double areaHouseone;           // 按揭房产面积
	private BigDecimal evalValueHouseone;  // 按揭房产估价
	private String typeHouseone;           // 按揭房产类型
	private String landSitHouseone;        // 按揭房产房屋坐落
	private BigDecimal loanBalanceHouseone;// 按揭房产贷款余额
	private String ownerHouseone;          // 按揭房产房屋所有人
	private String premisesPermitHousetwo; // 房产二房产证号
	private Double areaHousetwo;           // 房产二面积
	private BigDecimal evalValueHousetwo;  // 房产二估价
	private String typeHousetwo;           // 房产二类型
	private String landSitHousetwo;        // 房产二房屋坐落
	private BigDecimal loanBalanceHousetwo;// 房产二贷款余额
	private String ownerHousetwo;          // 房产二房屋所有人
	private String typeCarone;             // 车产一车型
	private String plateNumberCarone;      // 车产一车牌号
	private Short boughtYearCarone;        // 车产一购买年限
	private BigDecimal evalValueCarone;    // 车产一估价
	private String ownerCarone;            // 车产一所有人
	private BigDecimal loanBalanceCarone;  // 车产一贷款余额

	// Constructors

	/** default constructor */
	public FamilyAssetsDetail() {
	}

	/** minimal constructor */
	public FamilyAssetsDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public FamilyAssetsDetail(Long id, Long projectId,
			String premisesPermitHouseone, Double areaHouseone,
			BigDecimal evalValueHouseone, String typeHouseone,
			String landSitHouseone, BigDecimal loanBalanceHouseone,
			String ownerHouseone, String premisesPermitHousetwo,
			Double areaHousetwo, BigDecimal evalValueHousetwo,
			String typeHousetwo, String landSitHousetwo,
			BigDecimal loanBalanceHousetwo, String ownerHousetwo,
			String typeCarone, String plateNumberCarone,
			Short boughtYearCarone, BigDecimal evalValueCarone,
			String ownerCarone, BigDecimal loanBalanceCarone) {
		this.id = id;
		this.projectId = projectId;
		this.premisesPermitHouseone = premisesPermitHouseone;
		this.areaHouseone = areaHouseone;
		this.evalValueHouseone = evalValueHouseone;
		this.typeHouseone = typeHouseone;
		this.landSitHouseone = landSitHouseone;
		this.loanBalanceHouseone = loanBalanceHouseone;
		this.ownerHouseone = ownerHouseone;
		this.premisesPermitHousetwo = premisesPermitHousetwo;
		this.areaHousetwo = areaHousetwo;
		this.evalValueHousetwo = evalValueHousetwo;
		this.typeHousetwo = typeHousetwo;
		this.landSitHousetwo = landSitHousetwo;
		this.loanBalanceHousetwo = loanBalanceHousetwo;
		this.ownerHousetwo = ownerHousetwo;
		this.typeCarone = typeCarone;
		this.plateNumberCarone = plateNumberCarone;
		this.boughtYearCarone = boughtYearCarone;
		this.evalValueCarone = evalValueCarone;
		this.ownerCarone = ownerCarone;
		this.loanBalanceCarone = loanBalanceCarone;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PK_SEQ_TBL", sequenceName = "SEQ_FAMILY_ASSETS_DETAIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_TBL")
	@Column(name = "ID", unique = true, nullable = false, precision = 20, scale = 0)
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

	@Column(name = "PREMISES_PERMIT_HOUSEONE", length = 100)
	public String getPremisesPermitHouseone() {
		return this.premisesPermitHouseone;
	}

	public void setPremisesPermitHouseone(String premisesPermitHouseone) {
		this.premisesPermitHouseone = premisesPermitHouseone;
	}

	@Column(name = "AREA_HOUSEONE", precision = 16, scale = 2)
	public Double getAreaHouseone() {
		return this.areaHouseone;
	}

	public void setAreaHouseone(Double areaHouseone) {
		this.areaHouseone = areaHouseone;
	}

	@Column(name = "EVAL_VALUE_HOUSEONE", precision = 18, scale = 2)
	public BigDecimal getEvalValueHouseone() {
		return this.evalValueHouseone;
	}

	public void setEvalValueHouseone(BigDecimal evalValueHouseone) {
		this.evalValueHouseone = evalValueHouseone;
	}

	@Column(name = "TYPE_HOUSEONE", length = 20)
	public String getTypeHouseone() {
		return this.typeHouseone;
	}

	public void setTypeHouseone(String typeHouseone) {
		this.typeHouseone = typeHouseone;
	}

	@Column(name = "LAND_SIT_HOUSEONE", length = 200)
	public String getLandSitHouseone() {
		return this.landSitHouseone;
	}

	public void setLandSitHouseone(String landSitHouseone) {
		this.landSitHouseone = landSitHouseone;
	}

	@Column(name = "LOAN_BALANCE_HOUSEONE", precision = 18, scale = 2)
	public BigDecimal getLoanBalanceHouseone() {
		return this.loanBalanceHouseone;
	}

	public void setLoanBalanceHouseone(BigDecimal loanBalanceHouseone) {
		this.loanBalanceHouseone = loanBalanceHouseone;
	}

	@Column(name = "OWNER_HOUSEONE", length = 20)
	public String getOwnerHouseone() {
		return this.ownerHouseone;
	}

	public void setOwnerHouseone(String ownerHouseone) {
		this.ownerHouseone = ownerHouseone;
	}

	@Column(name = "PREMISES_PERMIT_HOUSETWO", length = 100)
	public String getPremisesPermitHousetwo() {
		return this.premisesPermitHousetwo;
	}

	public void setPremisesPermitHousetwo(String premisesPermitHousetwo) {
		this.premisesPermitHousetwo = premisesPermitHousetwo;
	}

	@Column(name = "AREA_HOUSETWO", precision = 16, scale = 2)
	public Double getAreaHousetwo() {
		return this.areaHousetwo;
	}

	public void setAreaHousetwo(Double areaHousetwo) {
		this.areaHousetwo = areaHousetwo;
	}

	@Column(name = "EVAL_VALUE_HOUSETWO", precision = 18, scale = 2)
	public BigDecimal getEvalValueHousetwo() {
		return this.evalValueHousetwo;
	}

	public void setEvalValueHousetwo(BigDecimal evalValueHousetwo) {
		this.evalValueHousetwo = evalValueHousetwo;
	}

	@Column(name = "TYPE_HOUSETWO", length = 20)
	public String getTypeHousetwo() {
		return this.typeHousetwo;
	}

	public void setTypeHousetwo(String typeHousetwo) {
		this.typeHousetwo = typeHousetwo;
	}

	@Column(name = "LAND_SIT_HOUSETWO", length = 200)
	public String getLandSitHousetwo() {
		return this.landSitHousetwo;
	}

	public void setLandSitHousetwo(String landSitHousetwo) {
		this.landSitHousetwo = landSitHousetwo;
	}

	@Column(name = "LOAN_BALANCE_HOUSETWO", precision = 18, scale = 2)
	public BigDecimal getLoanBalanceHousetwo() {
		return this.loanBalanceHousetwo;
	}

	public void setLoanBalanceHousetwo(BigDecimal loanBalanceHousetwo) {
		this.loanBalanceHousetwo = loanBalanceHousetwo;
	}

	@Column(name = "OWNER_HOUSETWO", length = 20)
	public String getOwnerHousetwo() {
		return this.ownerHousetwo;
	}

	public void setOwnerHousetwo(String ownerHousetwo) {
		this.ownerHousetwo = ownerHousetwo;
	}

	@Column(name = "TYPE_CARONE", length = 20)
	public String getTypeCarone() {
		return this.typeCarone;
	}

	public void setTypeCarone(String typeCarone) {
		this.typeCarone = typeCarone;
	}

	@Column(name = "PLATE_NUMBER_CARONE", length = 20)
	public String getPlateNumberCarone() {
		return this.plateNumberCarone;
	}

	public void setPlateNumberCarone(String plateNumberCarone) {
		this.plateNumberCarone = plateNumberCarone;
	}

	@Column(name = "BOUGHT_YEAR_CARONE", precision = 3, scale = 0)
	public Short getBoughtYearCarone() {
		return this.boughtYearCarone;
	}

	public void setBoughtYearCarone(Short boughtYearCarone) {
		this.boughtYearCarone = boughtYearCarone;
	}

	@Column(name = "EVAL_VALUE_CARONE", precision = 18, scale = 2)
	public BigDecimal getEvalValueCarone() {
		return this.evalValueCarone;
	}

	public void setEvalValueCarone(BigDecimal evalValueCarone) {
		this.evalValueCarone = evalValueCarone;
	}

	@Column(name = "OWNER_CARONE", length = 20)
	public String getOwnerCarone() {
		return this.ownerCarone;
	}

	public void setOwnerCarone(String ownerCarone) {
		this.ownerCarone = ownerCarone;
	}

	@Column(name = "LOAN_BALANCE_CARONE", precision = 18, scale = 2)
	public BigDecimal getLoanBalanceCarone() {
		return this.loanBalanceCarone;
	}

	public void setLoanBalanceCarone(BigDecimal loanBalanceCarone) {
		this.loanBalanceCarone = loanBalanceCarone;
	}

}