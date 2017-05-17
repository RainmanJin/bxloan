package com.coamctech.bxloan.web.vo.customermng;

import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class UniteGuNegoVO {
	
	private Long id;
	private String unGuId;
	private String unGroupName;
	private String unType;
	private BigDecimal unTrustAllAmt;
	private Date begDate;
	private Date endDate;
	private String unStatus;
	private String custManager;
	private String guaranWay;
	private Date unSigeDate;
	private String segiOrg;
	private String segiOrgName;
	private Date segiDate;
	private String segiMan;
	private String remark;
	private String customerQuantity;
	private String seqUniteGuNego;
	private String managerId;
	
	
	
	public UniteGuNegoVO() {
		super();
	}
	public UniteGuNegoVO(Long id, String unGuId, String unGroupName,
			String unType, BigDecimal unTrustAllAmt, Date begDate,
			Date endDate, String unStatus, String custManager,
			String guaranWay, Date unSigeDate, String segiOrg, Date segiDate,
			String segiMan, String remark, String customerQuantity,
			String seqUniteGuNego, String managerId) {
		super();
		this.id = id;
		this.unGuId = unGuId;
		this.unGroupName = unGroupName;
		this.unType = unType;
		this.unTrustAllAmt = unTrustAllAmt;
		this.begDate = begDate;
		this.endDate = endDate;
		this.unStatus = unStatus;
		this.custManager = custManager;
		this.guaranWay = guaranWay;
		this.unSigeDate = unSigeDate;
		this.segiOrg = segiOrg;
		this.segiDate = segiDate;
		this.segiMan = segiMan;
		this.remark = remark;
		this.customerQuantity = customerQuantity;
		this.seqUniteGuNego = seqUniteGuNego;
		this.managerId = managerId;
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
	public String getBegDateStr() {
		return CommonHelper.date2Str(begDate, CommonHelper.DF_DATE);
	}
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}
	public void setBegDateStr(String begDateStr) {
		this.begDate = CommonHelper.str2Date(begDateStr, CommonHelper.DF_DATE);
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getEndDateStr() {
		return CommonHelper.date2Str(endDate, CommonHelper.DF_DATE);
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDate = CommonHelper.str2Date(endDateStr, CommonHelper.DF_DATE);
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
	public String getGuaranWay() {
		return guaranWay;
	}
	public void setGuaranWay(String guaranWay) {
		this.guaranWay = guaranWay;
	}
	public Date getUnSigeDate() {
		return unSigeDate;
	}
	public String getUnSigeDateStr() {
		return CommonHelper.date2Str(unSigeDate, CommonHelper.DF_DATE);
	}
	public void setUnSigeDate(Date unSigeDate) {
		this.unSigeDate = unSigeDate;
	}
	public void setUnSigeDateStr(String unSigeDateStr) {
		this.unSigeDate = CommonHelper.str2Date(unSigeDateStr, CommonHelper.DF_DATE);
	}
	public String getSegiOrg() {
		return segiOrg;
	}
	public void setSegiOrg(String segiOrg) {
		this.segiOrg = segiOrg;
	}
	public Date getSegiDate() {
		return segiDate;
	}
	public String getSegiDateStr() {
		return CommonHelper.date2Str(segiDate, CommonHelper.DF_DATE);
	}
	public void setSegiDate(Date segiDate) {
		this.segiDate = segiDate;
	}
	public void setSegiDateStr(String sigeDateStr) {
		this.segiDate = CommonHelper.str2Date(sigeDateStr, CommonHelper.DF_DATE);
	}
	public String getSegiMan() {
		return segiMan;
	}
	public void setSegiMan(String segiMan) {
		this.segiMan = segiMan;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCustomerQuantity() {
		return customerQuantity;
	}
	public void setCustomerQuantity(String customerQuantity) {
		this.customerQuantity = customerQuantity;
	}
	public String getSeqUniteGuNego() {
		return seqUniteGuNego;
	}
	public void setSeqUniteGuNego(String seqUniteGuNego) {
		this.seqUniteGuNego = seqUniteGuNego;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getSegiOrgName() {
		return segiOrgName;
	}
	public void setSegiOrgName(String segiOrgName) {
		this.segiOrgName = segiOrgName;
	}
	
	
}
