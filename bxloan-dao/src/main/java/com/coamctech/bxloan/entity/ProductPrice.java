package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_PRICE", schema = WD_SCHEMA)
public class ProductPrice {

	@Id
	@SequenceGenerator(name = "PK_SEQ_TBL", sequenceName = "SEQ_PRODUCTPRICE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_TBL")
	@Column(name = "id", length = 20, nullable = false)
	private Long id; // 唯一标识

	@Column(name = "loan_product", length = 50, nullable = false)
	private String loanProduct; // 贷款产品

	@Column(name = "loan_term", length = 20, nullable = false)
	private Integer loanTerm; // 贷款期限

	@Column(name = "rate", length = 20, nullable = false)
	private BigDecimal rate; // 利率

	@Column(name = "overdue_rate", length = 20, nullable = false)
	private BigDecimal overdueRate; // 逾期利率

	@Column(name = "early_repayment", length = 20, nullable = false)
	private BigDecimal earlyRepayment; // 提前还款违约

	@Column(name = "overdue_repayment", length = 20, nullable = false)
	private BigDecimal overdueRepayment; // 逾期偿还滞纳

	@Column(name = "repayment_type", length = 20, nullable = false)
	private String repaymentType; // 还款方式

	@Column(name = "repayment_cucle", length = 20, nullable = false)
	private Integer repaymentCucle; // 偿还周期

	@Column(name = "percul_nego_rate", precision = 20, scale = 6)
	private BigDecimal perculNegoRate; // 挪用利率偿还比例

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}

	public Integer getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getOverdueRate() {
		return overdueRate;
	}

	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}

	public BigDecimal getEarlyRepayment() {
		return earlyRepayment;
	}

	public void setEarlyRepayment(BigDecimal earlyRepayment) {
		this.earlyRepayment = earlyRepayment;
	}

	public BigDecimal getOverdueRepayment() {
		return overdueRepayment;
	}

	public void setOverdueRepayment(BigDecimal overdueRepayment) {
		this.overdueRepayment = overdueRepayment;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public Integer getRepaymentCucle() {
		return repaymentCucle;
	}

	public void setRepaymentCucle(Integer repaymentCucle) {
		this.repaymentCucle = repaymentCucle;
	}

	public BigDecimal getPerculNegoRate() {
		return perculNegoRate;
	}

	public void setPerculNegoRate(BigDecimal perculNegoRate) {
		this.perculNegoRate = perculNegoRate;
	}

}
