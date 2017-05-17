package com.coamctech.bxloan.entity;

// default package

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WfRoule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WF_ROULE", schema = WD_SCHEMA)
public class WfRoule implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private String orgId;
	private String productId;
	private String routhepath;
	private String processid;
	private Double maxamt;
	private Double minamt;
	private String isusing;

	// Constructors

	/** default constructor */
	public WfRoule() {
	}

	/** minimal constructor */
	public WfRoule(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public WfRoule(BigDecimal id, String orgId, String productId,
			String routhepath, String processid, Double maxamt, Double minamt,
			String isusing) {
		this.id = id;
		this.orgId = orgId;
		this.productId = productId;
		this.routhepath = routhepath;
		this.processid = processid;
		this.maxamt = maxamt;
		this.minamt = minamt;
		this.isusing = isusing;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name = "ORG_ID")
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "PRODUCT_ID")
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "ROUTHEPATH")
	public String getRouthepath() {
		return this.routhepath;
	}

	public void setRouthepath(String routhepath) {
		this.routhepath = routhepath;
	}

	@Column(name = "PROCESSID")
	public String getProcessid() {
		return this.processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	@Column(name = "MAXAMT", precision = 20)
	public Double getMaxamt() {
		return this.maxamt;
	}

	public void setMaxamt(Double maxamt) {
		this.maxamt = maxamt;
	}

	@Column(name = "MINAMT", precision = 20)
	public Double getMinamt() {
		return this.minamt;
	}

	public void setMinamt(Double minamt) {
		this.minamt = minamt;
	}

	@Column(name = "ISUSING")
	public String getIsusing() {
		return this.isusing;
	}

	public void setIsusing(String isusing) {
		this.isusing = isusing;
	}

}