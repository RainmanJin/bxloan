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
 * OtherIncome entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OTHER_INCOME", schema = WD_SCHEMA)
public class OtherIncome implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -172297994285048790L;
	
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_OTHER_INCOME", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long id;
	@Column(name = "PROJECT_ID", precision = 22, scale = 0)
	private Long projectId;
	@Column(name = "TYPE", length = 20)
	private String type;//类型（区分农业与非农业）
	@Column(name = "OTHER_INCOME_TYPE", precision = 22, scale = 0)
	private String otherIncomeType;//收入类型
	//1.工资  2.其他
	@Column(name = "NAME", precision = 22, scale = 0)
	private String name;//“其他”项--具体内容
	@Column(name = "YEAR_INCOME", precision = 22, scale = 0)
	private BigDecimal yearIncome;//年收入
	@Column(name = "REMARKS", precision = 22, scale = 0)
	private String remarks;//备注
	@Column(name = "FUTURE_PAST_TYPE", length = 20)
	private String futurePastType;//时间类型（区分过去和未来（12个月）

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return this.projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOtherIncomeType() {
		return otherIncomeType;
	}
	public void setOtherIncomeType(String otherIncomeType) {
		this.otherIncomeType = otherIncomeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getYearIncome() {
		return yearIncome;
	}
	public void setYearIncome(BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFuturePastType() {
		return this.futurePastType;
	}
	public void setFuturePastType(String futurePastType) {
		this.futurePastType = futurePastType;
	}

}