package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
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

/**
 * 业务固定资产
 * @author AcoreHeng
 *
 */
@Entity
@Table(name = "BIZ_FIXED_ASSETS",schema=WD_SCHEMA)
public class BizFixedAssets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5019976612462347494L;
	@Id
	@Column(name="ID",precision=20,scale=0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_BIZ_FIXED_ASSETS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	private Long id;
	@Column(name="BIZ_TYPE",length=1)
	private String bizType;
	@Column(name="ASSET_TYPE",length=10)
	private String assetType;
	@Column(name="ASSET_NAME",length=50)
	private String assetName;
	@Column(name="ASSET_NAME_CODE",length=10)
	private String assetNameCode;
	@Column(name="ESTIMATED_PRICE",precision=20,scale=2)
	private BigDecimal estimatedPrice;
	@Column(name="ORIGINAL_ACQUISITION_PRICE",precision=20,scale=2)
	private BigDecimal originalAcquisitionPrice;
	@Column(name="SIZE_OR_QUANTITY",precision=20,scale=0)
	private BigDecimal sizeOrQuantity;
	@Column(name="BUY_OR_BUILD_YEAR")
	@Temporal(TemporalType.DATE)
	private Date buyOrBuildYear;
	@Column(name="STRUCTURE",length=200)
	private String structure;
	@Column(name="DESCRIPTION",length=200)
	private String description;
	@Column(name="PROJECT_ID",precision=20,scale=0)
	private Long projectId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public BigDecimal getEstimatedPrice() {
		return estimatedPrice;
	}
	public void setEstimatedPrice(BigDecimal estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}
	public BigDecimal getOriginalAcquisitionPrice() {
		return originalAcquisitionPrice;
	}
	public void setOriginalAcquisitionPrice(BigDecimal originalAcquisitionPrice) {
		this.originalAcquisitionPrice = originalAcquisitionPrice;
	}
	public BigDecimal getSizeOrQuantity() {
		return sizeOrQuantity;
	}
	public void setSizeOrQuantity(BigDecimal sizeOrQuantity) {
		this.sizeOrQuantity = sizeOrQuantity;
	}
	public Date getBuyOrBuildYear() {
		return buyOrBuildYear;
	}
	public void setBuyOrBuildYear(Date buyOrBuildYear) {
		this.buyOrBuildYear = buyOrBuildYear;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getAssetNameCode() {
		return assetNameCode;
	}
	public void setAssetNameCode(String assetNameCode) {
		this.assetNameCode = assetNameCode;
	}
	
	
}
