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

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * IndustryBizStock entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INDUSTRY_BIZ_STOCK", schema = GlobalConstants.WD_SCHEMA)
public class IndustryBizStock implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private String type;
	private String name;
	private BigDecimal price;
	private String unit;
	private Long num;
	private BigDecimal total;

	// Constructors

	/** default constructor */
	public IndustryBizStock() {
	}

	/** minimal constructor */
	public IndustryBizStock(Long id) {
		this.id = id;
	}

	/** full constructor */
	public IndustryBizStock(Long id, Long projectId, String type,
			String name, BigDecimal price, String unit, Long num,
			BigDecimal total) {
		this.id = id;
		this.projectId = projectId;
		this.type = type;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.num = num;
		this.total = total;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_INDUSTRY_BIZ_STOCK", allocationSize = 1)
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

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PRICE", precision = 22, scale = 0)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "UNIT", length = 20)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "NUM", precision = 22, scale = 0)
	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	@Column(name = "TOTAL", precision = 22, scale = 0)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}