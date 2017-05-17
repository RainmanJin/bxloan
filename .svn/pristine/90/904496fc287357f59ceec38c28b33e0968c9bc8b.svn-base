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

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * OtherIncomeCommon entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OTHER_INCOME_COMMON", schema = GlobalConstants.WD_SCHEMA)
public class OtherIncomeCommon implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private String type;
	private Date time;
	private String content;
	private BigDecimal amount;
	private String incomeCostType;

	// Constructors

	/** default constructor */
	public OtherIncomeCommon() {
	}

	/** minimal constructor */
	public OtherIncomeCommon(Long id) {
		this.id = id;
	}

	/** full constructor */
	public OtherIncomeCommon(Long id, Long projectId, String type,
			Date time, String content, BigDecimal amount) {
		this.id = id;
		this.projectId = projectId;
		this.type = type;
		this.time = time;
		this.content = content;
		this.amount = amount;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_OTHER_INCOME_COMMON", allocationSize = 1)
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

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TIME", length = 7)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "CONTENT", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "AMOUNT", precision = 22, scale = 0)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "INCOME_COST_TYPE", length = 20)
	public String getIncomeCostType() {
		return incomeCostType;
	}

	public void setIncomeCostType(String incomeCostType) {
		this.incomeCostType = incomeCostType;
	}

}