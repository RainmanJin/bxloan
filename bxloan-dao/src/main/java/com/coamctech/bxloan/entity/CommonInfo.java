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
 * CommonInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMMON_INFO", schema = GlobalConstants.WD_SCHEMA)
public class CommonInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private BigDecimal price1;
	private BigDecimal price2;
	private BigDecimal price3;
	private BigDecimal price4;
	private BigDecimal price5;
	private String remarks1;
	private String remarks2;
	private String type;

	// Constructors

	/** default constructor */
	public CommonInfo() {
	}

	/** minimal constructor */
	public CommonInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CommonInfo(Long id, Long projectId, BigDecimal price1,
			BigDecimal price2, BigDecimal price3, BigDecimal price4, BigDecimal price5,
			String remarks1, String remarks2) {
		this.id = id;
		this.projectId = projectId;
		this.price1 = price1;
		this.price2 = price2;
		this.price3 = price3;
		this.price4 = price4;
		this.price5 = price5;
		this.remarks1 = remarks1;
		this.remarks2 = remarks2;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_COMMON_INFO", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_ID", precision = 20, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PRICE1", precision = 12, scale = 2)
	public BigDecimal getPrice1() {
		return this.price1;
	}

	public void setPrice1(BigDecimal price1) {
		this.price1 = price1;
	}

	@Column(name = "PRICE2", precision = 12, scale = 2)
	public BigDecimal getPrice2() {
		return this.price2;
	}

	public void setPrice2(BigDecimal price2) {
		this.price2 = price2;
	}

	@Column(name = "PRICE3", precision = 12, scale = 2)
	public BigDecimal getPrice3() {
		return this.price3;
	}

	public void setPrice3(BigDecimal price3) {
		this.price3 = price3;
	}

	@Column(name = "PRICE4", precision = 12, scale = 2)
	public BigDecimal getPrice4() {
		return this.price4;
	}

	public void setPrice4(BigDecimal price4) {
		this.price4 = price4;
	}

	@Column(name = "PRICE5", precision = 12, scale = 2)
	public BigDecimal getPrice5() {
		return this.price5;
	}

	public void setPrice5(BigDecimal price5) {
		this.price5 = price5;
	}

	@Column(name = "REMARKS1", length = 200)
	public String getRemarks1() {
		return this.remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	@Column(name = "REMARKS2", length = 200)
	public String getRemarks2() {
		return this.remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}