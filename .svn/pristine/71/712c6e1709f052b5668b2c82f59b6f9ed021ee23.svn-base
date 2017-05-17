package com.coamctech.bxloan.service.model.statistics;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**
 * 审批台账列表
 * @author AcoreHeng
 *
 */
public class BizAppAccItemVo {
	private Long workflowId;
	private Long projectId;
	private String projectNo;
	private String customerName;
	private String bizType;
	private BigDecimal applyAmt;
	private Date createTime;
	private String customerManagerName;
	private String orgName;
	private String orgDesc;
	private String productTypeName;
	private String productName;
	
	private BigDecimal yearIrRate;
	private Integer applyTerm;
	private Integer applyTermUnit;
	private String guaranteeMode;
	private String repayingMode;
	
	public BizAppAccItemVo(Object[] objs) {
		super();
		int i=0;
		this.projectId=CommonHelper.toLong(objs[i++]);
		this.workflowId=CommonHelper.toLong(objs[i++]);
		this.projectNo=CommonHelper.toStr(objs[i++]);
		this.customerName=CommonHelper.toStr(objs[i++]);
		this.bizType=CommonHelper.toStr(objs[i++]);
		this.applyAmt=CommonHelper.toBigDecimal(objs[i++]);
		this.createTime=CommonHelper.toDate(objs[i++]);
		this.customerManagerName=CommonHelper.toStr(objs[i++]);
		this.orgName=CommonHelper.toStr(objs[i++]);
		this.orgDesc=CommonHelper.toStr(objs[i++]);
		this.productName=CommonHelper.toStr(objs[i++]);
		this.productTypeName=CommonHelper.toStr(objs[i++]);
		this.yearIrRate=CommonHelper.toBigDecimal(objs[i++]);
		this.applyTerm=CommonHelper.toInt(objs[i++]);
		this.applyTermUnit=CommonHelper.toInt(objs[i++]);
		this.guaranteeMode=CommonHelper.toStr(objs[i++]);
		this.repayingMode=CommonHelper.toStr(objs[i++]);
	}
	
	public Long getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getApplyAmt() {
		return applyAmt;
	}
	public void setApplyAmt(BigDecimal applyAmt) {
		this.applyAmt = applyAmt;
	}
	public String getCreateTimeStr() {
		return CommonHelper.date2Str(createTime, CommonHelper.DF_DATE_SHORT_TIME);
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCustomerManagerName() {
		return customerManagerName;
	}
	public void setCustomerManagerName(String customerManagerName) {
		this.customerManagerName = customerManagerName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public BigDecimal getYearIrRate() {
		return yearIrRate;
	}

	public void setYearIrRate(BigDecimal yearIrRate) {
		this.yearIrRate = yearIrRate;
	}

	public Integer getApplyTerm() {
		return applyTerm;
	}

	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}

	public Integer getApplyTermUnit() {
		return applyTermUnit;
	}

	public void setApplyTermUnit(Integer applyTermUnit) {
		this.applyTermUnit = applyTermUnit;
	}

	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	public String getRepayingMode() {
		return repayingMode;
	}

	public void setRepayingMode(String repayingMode) {
		this.repayingMode = repayingMode;
	}
}
