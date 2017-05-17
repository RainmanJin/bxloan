package com.coamctech.bxloan.web.vo.collateral;

import java.io.Serializable;
import java.math.BigDecimal;

import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

public class CollateralItemVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long guarantyId;// ID
	private String guarantorName;// 抵质押人名称
	private String guarantyNum;// 抵质押物编号
	private String guarantyName;// 抵质押物名称
	private String guarantyStatusCd;//状态
	private String guarantyTypeCd;// 抵质押物类型
	private BigDecimal marketValue;// 市场价值
	private BigDecimal evalValue;// 评估价值
	private BigDecimal setGuarantyAmt;// 已设定担保额
	
	public CollateralItemVo(Object[] objs) {
		super();
		this.guarantyId=CommonHelper.toLong(objs[0]);
		this.guarantyNum=CommonHelper.toStr(objs[1]);
		this.guarantorName=CommonHelper.toStr(objs[2]);
		this.guarantyName=CommonHelper.toStr(objs[3]);
		this.guarantyTypeCd=CommonHelper.toStr(objs[4]);
		this.marketValue=CommonHelper.toBigDecimal(objs[5]);
		this.evalValue=CommonHelper.toBigDecimal(objs[6]);
		this.setGuarantyAmt=CommonHelper.toBigDecimal(objs[7]);
		this.guarantyStatusCd=CommonHelper.toStr(objs[8]);
	}
	public Long getGuarantyId() {
		return guarantyId;
	}
	public void setGuarantyId(Long guarantyId) {
		this.guarantyId = guarantyId;
	}
	public String getGuarantorName() {
		return guarantorName;
	}
	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}
	public String getGuarantyNum() {
		return guarantyNum;
	}
	public void setGuarantyNum(String guarantyNum) {
		this.guarantyNum = guarantyNum;
	}
	public String getGuarantyName() {
		return guarantyName;
	}
	public void setGuarantyName(String guarantyName) {
		this.guarantyName = guarantyName;
	}
	public String getGuarantyStatusCd() {
		return guarantyStatusCd;
	}
	public void setGuarantyStatusCd(String guarantyStatusCd) {
		this.guarantyStatusCd = guarantyStatusCd;
	}
	public String getGuarantyTypeCd() {
		return guarantyTypeCd;
	}
	public void setGuarantyTypeCd(String guarantyTypeCd) {
		this.guarantyTypeCd = guarantyTypeCd;
	}
	public BigDecimal getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}
	public BigDecimal getEvalValue() {
		return evalValue;
	}
	public void setEvalValue(BigDecimal evalValue) {
		this.evalValue = evalValue;
	}
	public BigDecimal getSetGuarantyAmt() {
		return setGuarantyAmt;
	}
	public void setSetGuarantyAmt(BigDecimal setGuarantyAmt) {
		this.setGuarantyAmt = setGuarantyAmt;
	}

}
