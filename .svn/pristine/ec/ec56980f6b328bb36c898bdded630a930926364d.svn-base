package com.coamctech.bxloan.web.vo.bizapply;

import java.math.BigDecimal;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class ExpectCashFlowDetailsAgroVo {
	
	private Long id;
	private Long expectCashFlowId;
	private String type;
	private String name;
	private BigDecimal money;
	private boolean isCount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getExpectCashFlowId() {
		return expectCashFlowId;
	}
	public void setExpectCashFlowId(Long expectCashFlowId) {
		this.expectCashFlowId = expectCashFlowId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public boolean isCount() {
		return isCount;
	}
	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}
	private ExpectCashFlowDetailsAgroVo(Long id, Long expectCashFlowId,
			String type, String name, BigDecimal money, boolean isCount) {
		super();
		this.id = id;
		this.expectCashFlowId = expectCashFlowId;
		this.type = type;
		this.name = name;
		this.money = money;
		this.isCount = isCount;
	}
	public ExpectCashFlowDetailsAgroVo() {
		super();
	}
	
	public ExpectCashFlowDetailsAgroVo(String name, BigDecimal money, boolean isCount) {
		super();
		this.name = name;
		this.money = money;
		this.isCount = isCount;
	}
	public ExpectCashFlowDetailsAgroVo(Object[] input) {
		int i = 0;
		this.id = CommonHelper.toLong(input[i++]);
		this.expectCashFlowId = CommonHelper.toLong(input[i++]);
		this.type = CommonHelper.toStr(input[i++]);
		this.name = CommonHelper.toStr(input[i++]);
		this.money = CommonHelper.toBigDecimal(input[i++]);
		this.isCount = false;
	}
	
}
