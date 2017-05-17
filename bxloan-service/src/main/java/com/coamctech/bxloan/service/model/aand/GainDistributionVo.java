package com.coamctech.bxloan.service.model.aand;

import java.math.BigDecimal;

public class GainDistributionVo {
	
	private Long id;
	private Long projectId;
	private String type;//类型（区分农业与非农业）
	//1.农业     2.非农业
	private String time;//时间
	private String content;//内容
	private BigDecimal amount;//金额
	private String name;//其他项--具体内容
	private String gainDisType;//资金类型 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
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
