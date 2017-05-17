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
 * ExpectCashFlowDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EXPECT_CASH_FLOW_DETAIL", schema = GlobalConstants.WD_SCHEMA)
public class ExpectCashFlowDetail implements java.io.Serializable {
	// Fields
	private Long id;
	private Long expectCashFlowId;
	private String type;
	private String name;
	private BigDecimal money;

	// Constructors
	/** default constructor */
	public ExpectCashFlowDetail() {
	}

	/** minimal constructor */
	public ExpectCashFlowDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public ExpectCashFlowDetail(Long id, Long expectCashFlowId,
			String type, String name, BigDecimal money) {
		this.id = id;
		this.expectCashFlowId = expectCashFlowId;
		this.type = type;
		this.name = name;
		this.money = money;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_EXPECT_CASH_FLOW_DETAIL", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "EXPECT_CASH_FLOW_ID", precision = 20, scale = 0)
	public Long getExpectCashFlowId() {
		return this.expectCashFlowId;
	}

	public void setExpectCashFlowId(Long expectCashFlowId) {
		this.expectCashFlowId = expectCashFlowId;
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

	@Column(name = "MONEY", precision = 20, scale = 0)
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
}
