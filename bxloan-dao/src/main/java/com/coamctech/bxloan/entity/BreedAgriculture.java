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
import javax.persistence.Transient;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.utils.CommonHelper;

/**
 * BreedAgriculture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BREED_AGRICULTURE", schema = GlobalConstants.WD_SCHEMA)
public class BreedAgriculture implements java.io.Serializable {
	// Fields
	private Long id;
	private Long projectId;
	private String type;
	private String breedContent;
	private BigDecimal workingYears;
	private String breedMode;
	private String breedScale;
	private String breedScaleUnit;
	private BigDecimal saleNum;
	private String saleNumUnit;//年销售量单位
	private BigDecimal salePrice;
	private String salePriceUnit;
	private BigDecimal saleIncomeTotal;
	private BigDecimal costTotal;
	private String singleProduceUnit;
	private BigDecimal highest;
	private BigDecimal lowest;
	private BigDecimal lastyear;
	private BigDecimal predict;
	private String stockInitScale;
	private String stockInitScaleUnit;
	private BigDecimal breedStockValue;
	private BigDecimal forageValue;
	private BigDecimal predictProduceTotal;
	private String predictProduceTotalUnit;
	private String predictSaleTime;
	private String breedType;
	private Long relativeId;
	// Constructors
	/** default constructor */
	public BreedAgriculture() {
	}

	/** minimal constructor */
	public BreedAgriculture(Long id) {
		this.id = id;
	}

	/** full constructor */
	public BreedAgriculture(Long id, Long projectId, String type,
			String breedContent, BigDecimal workingYears, String breedMode,
			String breedScale, String breedScaleUnit, BigDecimal saleNum,
			String saleNumUnit, BigDecimal salePrice, String salePriceUnit,
			BigDecimal saleIncomeTotal, BigDecimal costTotal,
			String singleProduceUnit, BigDecimal highest, BigDecimal lowest,
			BigDecimal lastyear, BigDecimal predict, String stockInitScale,
			String stockInitScaleUnit, BigDecimal breedStockValue,
			BigDecimal forageValue, BigDecimal predictProduceTotal,
			String predictProduceTotalUnit, String predictSaleTime) {
		this.id = id;
		this.projectId = projectId;
		this.type = type;
		this.breedContent = breedContent;
		this.workingYears = workingYears;
		this.breedMode = breedMode;
		this.breedScale = breedScale;
		this.breedScaleUnit = breedScaleUnit;
		this.saleNum = saleNum;
		this.saleNumUnit = saleNumUnit;
		this.salePrice = salePrice;
		this.salePriceUnit = salePriceUnit;
		this.saleIncomeTotal = saleIncomeTotal;
		this.costTotal = costTotal;
		this.singleProduceUnit = singleProduceUnit;
		this.highest = highest;
		this.lowest = lowest;
		this.lastyear = lastyear;
		this.predict = predict;
		this.stockInitScale = stockInitScale;
		this.stockInitScaleUnit = stockInitScaleUnit;
		this.breedStockValue = breedStockValue;
		this.forageValue = forageValue;
		this.predictProduceTotal = predictProduceTotal;
		this.predictProduceTotalUnit = predictProduceTotalUnit;
		this.predictSaleTime = predictSaleTime;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_BREED_AGRICULTURE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_ID", precision = 22, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "BREED_CONTENT", length = 20)
	public String getBreedContent() {
		return this.breedContent;
	}

	public void setBreedContent(String breedContent) {
		this.breedContent = breedContent;
	}

	@Column(name = "WORKING_YEARS", precision = 22, scale = 0)
	public BigDecimal getWorkingYears() {
		return this.workingYears;
	}

	public void setWorkingYears(BigDecimal workingYears) {
		this.workingYears = workingYears;
	}

	@Column(name = "BREED_MODE", length = 20)
	public String getBreedMode() {
		return this.breedMode;
	}

	public void setBreedMode(String breedMode) {
		this.breedMode = breedMode;
	}

	@Column(name = "BREED_SCALE", length = 20)
	public String getBreedScale() {
		return this.breedScale;
	}

	public void setBreedScale(String breedScale) {
		this.breedScale = breedScale;
	}

	@Column(name = "BREED_SCALE_UNIT", length = 20)
	public String getBreedScaleUnit() {
		return this.breedScaleUnit;
	}

	public void setBreedScaleUnit(String breedScaleUnit) {
		this.breedScaleUnit = breedScaleUnit;
	}

	@Column(name = "SALE_NUM", precision = 22, scale = 0)
	public BigDecimal getSaleNum() {
		return this.saleNum;
	}

	public void setSaleNum(BigDecimal saleNum) {
		this.saleNum = saleNum;
	}

	@Column(name = "SALE_NUM_UNIT", precision = 22, scale = 0)
	public String getSaleNumUnit() {
		return this.saleNumUnit;
	}

	public void setSaleNumUnit(String saleNumUnit) {
		this.saleNumUnit = saleNumUnit;
	}

	@Column(name = "SALE_PRICE", precision = 22, scale = 0)
	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	@Column(name = "SALE_PRICE_UNIT", length = 20)
	public String getSalePriceUnit() {
		return this.salePriceUnit;
	}

	public void setSalePriceUnit(String salePriceUnit) {
		this.salePriceUnit = salePriceUnit;
	}

	@Column(name = "SALE_INCOME_TOTAL", precision = 22, scale = 0)
	public BigDecimal getSaleIncomeTotal() {
		return this.saleIncomeTotal;
	}

	public void setSaleIncomeTotal(BigDecimal saleIncomeTotal) {
		this.saleIncomeTotal = saleIncomeTotal;
	}

	@Column(name = "COST_TOTAL", precision = 22, scale = 0)
	public BigDecimal getCostTotal() {
		return this.costTotal;
	}

	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}

	@Column(name = "SINGLE_PRODUCE_UNIT", length = 20)
	public String getSingleProduceUnit() {
		return this.singleProduceUnit;
	}

	public void setSingleProduceUnit(String singleProduceUnit) {
		this.singleProduceUnit = singleProduceUnit;
	}

	@Column(name = "HIGHEST", precision = 22, scale = 0)
	public BigDecimal getHighest() {
		return this.highest;
	}

	public void setHighest(BigDecimal highest) {
		this.highest = highest;
	}

	@Column(name = "LOWEST", precision = 22, scale = 0)
	public BigDecimal getLowest() {
		return this.lowest;
	}

	public void setLowest(BigDecimal lowest) {
		this.lowest = lowest;
	}

	@Column(name = "LASTYEAR", precision = 22, scale = 0)
	public BigDecimal getLastyear() {
		return this.lastyear;
	}

	public void setLastyear(BigDecimal lastyear) {
		this.lastyear = lastyear;
	}

	@Column(name = "PREDICT", precision = 22, scale = 0)
	public BigDecimal getPredict() {
		return this.predict;
	}

	public void setPredict(BigDecimal predict) {
		this.predict = predict;
	}

	@Column(name = "STOCK_INIT_SCALE", length = 20)
	public String getStockInitScale() {
		return this.stockInitScale;
	}

	public void setStockInitScale(String stockInitScale) {
		this.stockInitScale = stockInitScale;
	}

	@Column(name = "STOCK_INIT_SCALE_UNIT", length = 20)
	public String getStockInitScaleUnit() {
		return this.stockInitScaleUnit;
	}

	public void setStockInitScaleUnit(String stockInitScaleUnit) {
		this.stockInitScaleUnit = stockInitScaleUnit;
	}

	@Column(name = "BREED_STOCK_VALUE", precision = 22, scale = 0)
	public BigDecimal getBreedStockValue() {
		return this.breedStockValue;
	}

	public void setBreedStockValue(BigDecimal breedStockValue) {
		this.breedStockValue = breedStockValue;
	}

	@Column(name = "FORAGE_VALUE", precision = 22, scale = 0)
	public BigDecimal getForageValue() {
		return this.forageValue;
	}

	public void setForageValue(BigDecimal forageValue) {
		this.forageValue = forageValue;
	}

	@Column(name = "PREDICT_PRODUCE_TOTAL", precision = 22, scale = 0)
	public BigDecimal getPredictProduceTotal() {
		return this.predictProduceTotal;
	}

	public void setPredictProduceTotal(BigDecimal predictProduceTotal) {
		this.predictProduceTotal = predictProduceTotal;
	}

	@Column(name = "PREDICT_PRODUCE_TOTAL_UNIT", length = 20)
	public String getPredictProduceTotalUnit() {
		return this.predictProduceTotalUnit;
	}

	public void setPredictProduceTotalUnit(String predictProduceTotalUnit) {
		this.predictProduceTotalUnit = predictProduceTotalUnit;
	}

	@Column(name = "PREDICT_SALE_TIME", length = 7)
	public String getPredictSaleTime() {
		return this.predictSaleTime;
	}

	public void setPredictSaleTime(String predictSaleTime) {
		this.predictSaleTime = predictSaleTime;
	}
	
	@Column(name = "BREED_TYPE", length = 10)
	public String getBreedType() {
		return breedType;
	}

	public void setBreedType(String breedType) {
		this.breedType = breedType;
	}
	@Column(name = "RELATIVE_ID", precision = 22, scale = 0)
	public Long getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(Long relativeId) {
		this.relativeId = relativeId;
	}
	
}
