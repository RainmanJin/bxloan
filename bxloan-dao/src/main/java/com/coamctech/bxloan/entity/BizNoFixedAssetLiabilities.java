package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 业务中非固定资产及负债
 * @author AcoreHeng
 *
 */
@Entity
@Table(name = "BIZ_NO_FIXED_ASSET_LIABILITIES",schema=WD_SCHEMA)
public class BizNoFixedAssetLiabilities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -466506796580555085L;
	@Id
	@Column(name="ID",precision=20,scale=0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_BIZ_NO_FIXED_ASSETS_LIAB", allocationSize = 1)
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
	@Column(name="PRICE",precision=20,scale=2)
	private BigDecimal price;
	@Column(name="REMARKS",length=200)
	private String remark;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAssetNameCode() {
		return assetNameCode;
	}
	public void setAssetNameCode(String assetNameCode) {
		this.assetNameCode = assetNameCode;
	}

}
