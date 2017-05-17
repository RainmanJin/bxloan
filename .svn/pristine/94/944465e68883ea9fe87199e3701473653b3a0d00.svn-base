package com.coamctech.bxloan.web.vo.sysmng;

import java.io.Serializable;
import java.math.BigDecimal;

import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

public class ProductMngVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pcId; //配置表主键
	private Long productCd; //产品code
	private String productName; //产品名称
	private Long departmenId; //部门主键
	private String departmenNames; //部门名称
	private String customerType; //Customer类型
	private String isStart; //是否启用
	private String loanTermMode; //贷款期限模式
	private String minLoanTerm; //最小贷款期限
	private String specialLoanTerm; //特殊贷款期限
	private String minAmt; //最小申请金额
	private String maxAmt; //最大申请金额
	
	
	

	public ProductMngVO() {
		super();
	}
	
	public ProductMngVO(Object[] objs) {
		super();
		this.productCd=CommonHelper.toLong(objs[0]);
		this.productName=CommonHelper.toStr(objs[2]);
//		this.departmenId=CommonHelper.toLong(objs[3]);
		this.departmenNames=CommonHelper.toStr(objs[3]);
		this.customerType=CommonHelper.toStr(objs[1]);
		this.isStart=CommonHelper.toStr(objs[4]);
	}
	
	public Long getPcId() {
		return pcId;
	}
	public void setPcId(Long pcId) {
		this.pcId = pcId;
	}
	public Long getProductCd() {
		return productCd;
	}
	public void setProductCd(Long productCd) {
		this.productCd = productCd;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getDepartmenId() {
		return departmenId;
	}
	public void setDepartmenId(Long departmenId) {
		this.departmenId = departmenId;
	}
	public String getDepartmenNames() {
		return departmenNames;
	}
	public void setDepartmenNames(String departmenNames) {
		this.departmenNames = departmenNames;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getIsStart() {
		return isStart;
	}
	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}

	public String getLoanTermMode() {
		return loanTermMode;
	}

	public void setLoanTermMode(String loanTermMode) {
		this.loanTermMode = loanTermMode;
	}

	public String getMinLoanTerm() {
		return minLoanTerm;
	}

	public void setMinLoanTerm(String minLoanTerm) {
		this.minLoanTerm = minLoanTerm;
	}

	public String getSpecialLoanTerm() {
		return specialLoanTerm;
	}

	public void setSpecialLoanTerm(String specialLoanTerm) {
		this.specialLoanTerm = specialLoanTerm;
	}

	public String getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(String minAmt) {
		this.minAmt = minAmt;
	}

	public String getMaxAmt() {
		return maxAmt;
	}

	public void setMaxAmt(String maxAmt) {
		this.maxAmt = maxAmt;
	}
}
