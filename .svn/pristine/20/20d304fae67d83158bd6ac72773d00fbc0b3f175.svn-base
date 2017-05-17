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

import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.utils.CommonHelper;

/**
 * CultivateAgriculture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CULTIVATE_AGRICULTURE", schema = GlobalConstants.WD_SCHEMA)
public class CultivateAgriculture implements java.io.Serializable {
	// Fields
	private Long id;
	private Long projectId;
	private String type;
	private String cultivateContent;
	private BigDecimal workingYears;
	private String cultivateScale;
	private String cultivateScaleUnit;
	private BigDecimal output;
	private String outputUnit;
	private BigDecimal familyConsume;
	private BigDecimal livestockConsume;
	private BigDecimal saleNum;
	private BigDecimal salePrice;
	private String salePriceUnit;
	private BigDecimal saleIncomeTotal;
	private BigDecimal costTotal;
	private String singleProduceUnit;
	private String maxSingleProduce;
	private String minSingleProduce;
	private String lastYearSingleProduce;
	private String forecast;
	private BigDecimal cropValue;
	private BigDecimal agricultureCapitalValue;
	private BigDecimal predictSingleProduce;
	private String predictSingleProduceUnit;
	private BigDecimal predictTotalProduce;
	private String predictTotalProduceUnit;
	private String predictSaleTime;
	private String predictSaleTimeUnit;
	private BigDecimal predictCostTotal;
	private String cultivateType;
	private Long relativeId;
	// Constructors
	/** default constructor */
	public CultivateAgriculture() {
	}

	/** minimal constructor */
	public CultivateAgriculture(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CultivateAgriculture(Long id, Long projectId,
			String type, String cultivateContent, BigDecimal workingYears,
			String cultivateScale, String cultivateScaleUnit,
			BigDecimal output, String outputUnit, BigDecimal familyConsume,
			BigDecimal livestockConsume, BigDecimal saleNum,
			BigDecimal salePrice, String salePriceUnit,
			BigDecimal saleIncomeTotal, BigDecimal costTotal,
			String singleProduceUnit, String maxSingleProduce,
			String minSingleProduce, String lastYearSingleProduce,
			String forecast, BigDecimal cropValue,
			BigDecimal agricultureCapitalValue,
			BigDecimal predictSingleProduce, String predictSaleTime,
			String predictSingleProduceUnit) {
		this.id = id;
		this.projectId = projectId;
		this.type = type;
		this.cultivateContent = cultivateContent;
		this.workingYears = workingYears;
		this.cultivateScale = cultivateScale;
		this.cultivateScaleUnit = cultivateScaleUnit;
		this.output = output;
		this.outputUnit = outputUnit;
		this.familyConsume = familyConsume;
		this.livestockConsume = livestockConsume;
		this.saleNum = saleNum;
		this.salePrice = salePrice;
		this.salePriceUnit = salePriceUnit;
		this.saleIncomeTotal = saleIncomeTotal;
		this.costTotal = costTotal;
		this.singleProduceUnit = singleProduceUnit;
		this.maxSingleProduce = maxSingleProduce;
		this.minSingleProduce = minSingleProduce;
		this.lastYearSingleProduce = lastYearSingleProduce;
		this.forecast = forecast;
		this.cropValue = cropValue;
		this.agricultureCapitalValue = agricultureCapitalValue;
		this.predictSingleProduce = predictSingleProduce;
		this.predictSaleTime = predictSaleTime;
		this.predictSingleProduceUnit = predictSingleProduceUnit;
	}

	@SequenceGenerator(name = "generator", sequenceName = "SEQ_CULTIVATE_AGRICULTURE", allocationSize = 1)
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

	@Column(name = "CULTIVATE_CONTENT", length = 20)
	public String getCultivateContent() {
		return this.cultivateContent;
	}

	public void setCultivateContent(String cultivateContent) {
		this.cultivateContent = cultivateContent;
	}

	@Column(name = "WORKING_YEARS", precision = 22, scale = 0)
	public BigDecimal getWorkingYears() {
		return this.workingYears;
	}

	public void setWorkingYears(BigDecimal workingYears) {
		this.workingYears = workingYears;
	}

	@Column(name = "CULTIVATE_SCALE", length = 20)
	public String getCultivateScale() {
		return this.cultivateScale;
	}
	
	public void setCultivateScale(String cultivateScale) {
		this.cultivateScale = cultivateScale;
	}

	@Column(name = "CULTIVATE_SCALE_UNIT", length = 20)
	public String getCultivateScaleUnit() {
		return this.cultivateScaleUnit;
	}

	public void setCultivateScaleUnit(String cultivateScaleUnit) {
		this.cultivateScaleUnit = cultivateScaleUnit;
	}

	@Column(name = "OUTPUT", precision = 22, scale = 0)
	public BigDecimal getOutput() {
		return this.output;
	}

	public void setOutput(BigDecimal output) {
		this.output = output;
	}

	@Column(name = "OUTPUT_UNIT", length = 20)
	public String getOutputUnit() {
		return this.outputUnit;
	}

	public void setOutputUnit(String outputUnit) {
		this.outputUnit = outputUnit;
	}

	@Column(name = "FAMILY_CONSUME", precision = 22, scale = 0)
	public BigDecimal getFamilyConsume() {
		return this.familyConsume;
	}

	public void setFamilyConsume(BigDecimal familyConsume) {
		this.familyConsume = familyConsume;
	}

	@Column(name = "LIVESTOCK_CONSUME", precision = 22, scale = 0)
	public BigDecimal getLivestockConsume() {
		return this.livestockConsume;
	}

	public void setLivestockConsume(BigDecimal livestockConsume) {
		this.livestockConsume = livestockConsume;
	}

	@Column(name = "SALE_NUM", precision = 22, scale = 0)
	public BigDecimal getSaleNum() {
		return this.saleNum;
	}

	public void setSaleNum(BigDecimal saleNum) {
		this.saleNum = saleNum;
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

	@Column(name = "MAX_SINGLE_PRODUCE", length = 20)
	public String getMaxSingleProduce() {
		return this.maxSingleProduce;
	}

	public void setMaxSingleProduce(String maxSingleProduce) {
		this.maxSingleProduce = maxSingleProduce;
	}

	@Column(name = "MIN_SINGLE_PRODUCE", length = 20)
	public String getMinSingleProduce() {
		return this.minSingleProduce;
	}

	public void setMinSingleProduce(String minSingleProduce) {
		this.minSingleProduce = minSingleProduce;
	}

	@Column(name = "LAST_YEAR_SINGLE_PRODUCE", length = 20)
	public String getLastYearSingleProduce() {
		return this.lastYearSingleProduce;
	}

	public void setLastYearSingleProduce(String lastYearSingleProduce) {
		this.lastYearSingleProduce = lastYearSingleProduce;
	}

	@Column(name = "FORECAST", length = 20)
	public String getForecast() {
		return this.forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	@Column(name = "CROP_VALUE", precision = 22, scale = 0)
	public BigDecimal getCropValue() {
		return this.cropValue;
	}

	public void setCropValue(BigDecimal cropValue) {
		this.cropValue = cropValue;
	}

	@Column(name = "AGRICULTURE_CAPITAL_VALUE", precision = 22, scale = 0)
	public BigDecimal getAgricultureCapitalValue() {
		return this.agricultureCapitalValue;
	}

	public void setAgricultureCapitalValue(BigDecimal agricultureCapitalValue) {
		this.agricultureCapitalValue = agricultureCapitalValue;
	}

	@Column(name = "PREDICT_SINGLE_PRODUCE", precision = 22, scale = 0)
	public BigDecimal getPredictSingleProduce() {
		return this.predictSingleProduce;
	}

	public void setPredictSingleProduce(BigDecimal predictSingleProduce) {
		this.predictSingleProduce = predictSingleProduce;
	}

	@Column(name = "PREDICT_SALE_TIME", length = 100)
	public String getPredictSaleTime() {
		return this.predictSaleTime;
	}

	public void setPredictSaleTime(String predictSaleTime) {
		this.predictSaleTime = predictSaleTime;
	}
	
	@Column(name = "PREDICT_SINGLE_PRODUCE_UNIT", length = 20)
	public String getPredictSingleProduceUnit() {
		return this.predictSingleProduceUnit;
	}

	public void setPredictSingleProduceUnit(String predictSingleProduceUnit) {
		this.predictSingleProduceUnit = predictSingleProduceUnit;
	}
	@Column(name = "CULTIVATE_TYPE", length = 10)
	public String getCultivateType() {
		return cultivateType;
	}

	public void setCultivateType(String cultivateType) {
		this.cultivateType = cultivateType;
	}
	@Column(name = "PREDICT_SALE_TIME_UNIT", length = 10)
	public String getPredictSaleTimeUnit() {
		return predictSaleTimeUnit;
	}

	public void setPredictSaleTimeUnit(String predictSaleTimeUnit) {
		this.predictSaleTimeUnit = predictSaleTimeUnit;
	}
	@Column(name = "PREDICT_COST_TOTAL", precision = 22, scale = 0)
	public BigDecimal getPredictCostTotal() {
		return predictCostTotal;
	}

	public void setPredictCostTotal(BigDecimal predictCostTotal) {
		this.predictCostTotal = predictCostTotal;
	}
	@Column(name = "PREDICT_TOTAL_PRODUCE", precision = 22, scale = 0)
	public BigDecimal getPredictTotalProduce() {
		return predictTotalProduce;
	}

	public void setPredictTotalProduce(BigDecimal predictTotalProduce) {
		this.predictTotalProduce = predictTotalProduce;
	}
	@Column(name = "PREDICT_TOTAL_PRODUCE_UNIT", length = 10)
	public String getPredictTotalProduceUnit() {
		return predictTotalProduceUnit;
	}

	public void setPredictTotalProduceUnit(String predictTotalProduceUnit) {
		this.predictTotalProduceUnit = predictTotalProduceUnit;
	}
	@Column(name = "RELATIVE_ID", precision = 22, scale = 0)
	public Long getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(Long relativeId) {
		this.relativeId = relativeId;
	}
	
}
