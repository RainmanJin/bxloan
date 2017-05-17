package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

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
 * GainDistribution entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "GAIN_DISTRIBUTION", schema = WD_SCHEMA)
public class GainDistribution implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -18339989453188494L;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_GAIN_DISTRIBUTION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	private Long id;
	@Column(name = "PROJECT_ID", precision = 22, scale = 0)
	private Long projectId;
	@Column(name = "TYPE", length = 20)
	private String type;//类型（区分农业与非农业）
	//1.农业     2.非农业
	@Temporal(TemporalType.DATE)
	@Column(name = "TIME", length = 7)
	private Date time;//时间
	@Column(name = "CONTENT", length = 20)
	private String content;//内容
	@Column(name = "AMOUNT", precision = 22, scale = 0)
	private BigDecimal amount;//金额
	@Column(name = "NAME", length = 20)
	private String name;//资金类型
	@Column(name = "GAIN_DIS_TYPE", length = 20)
	private String gainDisType;//其他项--具体内容
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
	public Date getTime() {
		return this.time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getAmount() {
		return this.amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGainDisType() {
		return gainDisType;
	}
	public void setGainDisType(String gainDisType) {
		this.gainDisType = gainDisType;
	}
	
}