package com.coamctech.bxloan.service.model.customermng;

import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class UniteCustomerVO {
	private Long id;
	private String unGuId;
	private String unGroupName;
	private String unType;
	private BigDecimal unTrustAllAmt;
	private Date begDate;
	private Date endDate;
	private String guaranWay;
	private String unStatus;
	private String custManagerId;
	private String custManager;
	private String customerQuantity;
	private String segiMan;
	private Long segiManId;
	private String sysCd;
	
	/**
	 * 是否编辑
	 */
	private boolean editFlag;
	
	
	
	public UniteCustomerVO() {
		super();
	}
	public UniteCustomerVO(Object[] input) {
		int i = 0;
		this.id = CommonHelper.toLong(input[i++]);
		this.unGuId = CommonHelper.toStr(input[i++]);
		this.unGroupName = CommonHelper.toStr(input[i++]);
		this.unType = CommonHelper.toStr(input[i++]);
		this.unTrustAllAmt = CommonHelper.toBigDecimal(input[i++]);
		this.begDate = CommonHelper.toDate(input[i++]);
		this.endDate = CommonHelper.toDate(input[i++]);
		this.guaranWay = CommonHelper.toStr(input[i++]);
		this.unStatus = CommonHelper.toStr(input[i++]);
		this.custManagerId = CommonHelper.toStr(input[i++]);
		this.custManager = CommonHelper.toStr(input[i++]);
		this.segiMan = CommonHelper.toStr(input[i++]);
		this.segiManId = CommonHelper.toLong(input[i++]);
		this.customerQuantity = CommonHelper.toStr(input[i++]);
		this.sysCd = CommonHelper.toStr(input[i++]);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUnGuId() {
		return unGuId;
	}
	public void setUnGuId(String unGuId) {
		this.unGuId = unGuId;
	}
	public String getUnGroupName() {
		return unGroupName;
	}
	public void setUnGroupName(String unGroupName) {
		this.unGroupName = unGroupName;
	}
	public String getUnType() {
		return unType;
	}
	public void setUnType(String unType) {
		this.unType = unType;
	}
	public BigDecimal getUnTrustAllAmt() {
		return unTrustAllAmt;
	}
	public void setUnTrustAllAmt(BigDecimal unTrustAllAmt) {
		this.unTrustAllAmt = unTrustAllAmt;
	}
	public Date getBegDate() {
		return begDate;
	}
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getGuaranWay() {
		return guaranWay;
	}
	public void setGuaranWay(String guaranWay) {
		this.guaranWay = guaranWay;
	}
	public String getUnStatus() {
		return unStatus;
	}
	public void setUnStatus(String unStatus) {
		this.unStatus = unStatus;
	}
	public String getCustManager() {
		return custManager;
	}
	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}
	public String getCustomerQuantity() {
		return customerQuantity;
	}
	public void setCustomerQuantity(String customerQuantity) {
		this.customerQuantity = customerQuantity;
	}
	public String getCustManagerId() {
		return custManagerId;
	}
	public void setCustManagerId(String custManagerId) {
		this.custManagerId = custManagerId;
	}
	public String getSegiMan() {
		return segiMan;
	}
	public void setSegiMan(String segiMan) {
		this.segiMan = segiMan;
	}
	public Long getSegiManId() {
		return segiManId;
	}
	public void setSegiManId(Long segiManId) {
		this.segiManId = segiManId;
	}
	public boolean isEditFlag() {
		return editFlag;
	}
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	public String getSysCd() {
		return sysCd;
	}
	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
	
	
	
}
