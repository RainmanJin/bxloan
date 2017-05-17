package com.coamctech.bxloan.service.model.statistics;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.service.enums.DataAuthorityType;

/**
 * 合同业务类，用来查询合同台账
 * 
 * @author gph
 * 
 */
public class BizContractVo implements Serializable {
	private static final long serialVersionUID = 257283700020933500L;

	private Long initProjectId;// 项目申请初始Id
	private Long initContractId;// 借款合同初始ID
	private Long projectId;// 项目申请Id
	private Long partyId;// 参与人Id
	private Long orgId;// 贷款机构Id
	private String applyOrgName;//贷款机构名
	private String customerName;// 客户名称
	private String customerType;// 客户类型
	private String customerNum;// 客户编号
	private String customerMng;// 客户经理
	private String contractId;// 合同Id
	private String contractNum;// 合同编号
	private String contractTermStr;//合同期限处理后的数据
	private String contractTerm;// 合同期限
	private String contractTermName;// 合同期限（带月份）
	private String contractTermUnit;// 合同期限单位
	private Date contractDueTime;// 合同到期日期
	private String contractStatus;// 合同状态
	private String contractStatusName;// 合同状态名称
	private BigDecimal contractAmt;// 合同金额
	private BigDecimal contractBalance;// 合同余额
	private String productType;// 贷款产品
	private String productName;//产品名称
	private String cdsGuarantMode;// 担保方式
	private String cdsGuarantModeName;// 担保方式名称
	private String isInsure;// 是否有保险
	private String isHeadcol;// 是否总部协同业务
	private Date loanTime;// 放款日期
	private Date loanStartTime;// 放款日期开始日期
	private Date loanEndTime;// 放款日期结束日期 
	
	
	/**
	 * 当前登录人id
	 */
	private Long loginUserId;
	/**
	 * 数据权限类型
	 */
	private DataAuthorityType dataAuthType;
	
	/**
	 * 数据权限-当前登录人拥有所有机构id集合
	 */
	private List<Long> dataAuthOrgIds;

	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public DataAuthorityType getDataAuthType() {
		return dataAuthType;
	}

	public void setDataAuthType(DataAuthorityType dataAuthType) {
		this.dataAuthType = dataAuthType;
	}

	public List<Long> getDataAuthOrgIds() {
		return dataAuthOrgIds;
	}

	public void setDataAuthOrgIds(List<Long> dataAuthOrgIds) {
		this.dataAuthOrgIds = dataAuthOrgIds;
	}

	public BizContractVo() {
	}

	public BizContractVo(Object[] objs) {
		super();
		int i = 0;
		this.initProjectId = CommonHelper.toLong(objs[i++]);
		this.projectId = CommonHelper.toLong(objs[i++]);
		this.initContractId = CommonHelper.toLong(objs[i++]);
		this.contractId = CommonHelper.toStr(objs[i++]);
		this.applyOrgName = CommonHelper.toStr(objs[i++]);
		this.partyId = CommonHelper.toLong(objs[i++]);
		this.contractNum = CommonHelper.toStr(objs[i++]);
		this.customerName = CommonHelper.toStr(objs[i++]);
		this.productType = CommonHelper.toStr(objs[i++]);
		this.contractStatus = CommonHelper.toStr(objs[i++]);
		this.contractAmt = CommonHelper.toBigDecimal(objs[i++]);
		this.contractTermStr = CommonHelper.toStr(objs[i++]);
		this.contractTerm = CommonHelper.toStr(objs[i++]);
		this.contractTermUnit = CommonHelper.toStr(objs[i++]);
		this.contractBalance = CommonHelper.toBigDecimal(objs[i++]);
		this.cdsGuarantMode = CommonHelper.toStr(objs[i++]);
		this.loanTime = CommonHelper.toDate(objs[i++]);
		this.contractDueTime = CommonHelper.toDate(objs[i++]);
		this.customerMng = CommonHelper.toStr(objs[i++]);
		this.productName = CommonHelper.toStr(objs[i++]);
	}

	public String getApplyOrgName() {
		return applyOrgName;
	}

	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}

	public Long getInitProjectId() {
		return initProjectId;
	}

	public void setInitProjectId(Long initProjectId) {
		this.initProjectId = initProjectId;
	}

	public Long getInitContractId() {
		return initContractId;
	}

	public void setInitContractId(Long initContractId) {
		this.initContractId = initContractId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCustomerMng() {
		return customerMng;
	}

	public void setCustomerMng(String customerMng) {
		this.customerMng = customerMng;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractTerm() {
		return contractTerm;
	}

	public void setContractTerm(String contractTerm) {
		this.contractTerm = contractTerm;
	}

	public Date getContractDueTime() {
		return contractDueTime;
	}

	public void setContractDueTime(Date contractDueTime) {
		this.contractDueTime = contractDueTime;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public BigDecimal getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}

	public BigDecimal getContractBalance() {
		return contractBalance;
	}

	public void setContractBalance(BigDecimal contractBalance) {
		this.contractBalance = contractBalance;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCdsGuarantMode() {
		return cdsGuarantMode;
	}

	public void setCdsGuarantMode(String cdsGuarantMode) {
		this.cdsGuarantMode = cdsGuarantMode;
	}

	public String getIsInsure() {
		return isInsure;
	}

	public void setIsInsure(String isInsure) {
		this.isInsure = isInsure;
	}

	public String getIsHeadcol() {
		return isHeadcol;
	}

	public void setIsHeadcol(String isHeadcol) {
		this.isHeadcol = isHeadcol;
	}

	public Date getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}

	public Date getLoanStartTime() {
		return loanStartTime;
	}

	public void setLoanStartTime(Date loanStartTime) {
		this.loanStartTime = loanStartTime;
	}

	public Date getLoanEndTime() {
		return loanEndTime;
	}

	public void setLoanEndTime(Date loanEndTime) {
		this.loanEndTime = loanEndTime;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getContractTermUnit() {
		return contractTermUnit;
	}

	public void setContractTermUnit(String contractTermUnit) {
		this.contractTermUnit = contractTermUnit;
	}

	public String getContractTermStr() {
		return contractTermStr;
	}

	public void setContractTermStr(String contractTermStr) {
		this.contractTermStr = contractTermStr;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getContractStatusName() {
		return contractStatusName;
	}

	public void setContractStatusName(String contractStatusName) {
		this.contractStatusName = contractStatusName;
	}

	public String getCdsGuarantModeName() {
		return cdsGuarantModeName;
	}

	public void setCdsGuarantModeName(String cdsGuarantModeName) {
		this.cdsGuarantModeName = cdsGuarantModeName;
	}

	public String getContractTermName() {
		return contractTermName;
	}

	public void setContractTermName(String contractTermName) {
		this.contractTermName = contractTermName;
	}
}
