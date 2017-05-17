package com.coamctech.bxloan.service.pettyloan.bo;

import java.util.Date;

public class AccountingBo {

	/**
	 * 项目ID
	 */
	private Integer itemId;
	/**
	 * 交易形态
	 */
	private Integer eventType;
	/**
	 * 业务类别
	 */
	private Integer bussType;
	private String txrefNo;// 交易编号
	private Long partyId;// 参与人ID
	private Long contractId;// 合同ID
	private Long projectId;// 业务ID
	private Long loanId;// 放款ID
	private String ccyId;// 币别
	private String bchId;// 机构号
	private Object bussObject;// 业务对象
	private String lastModUser;//最后操作者
	private Date txDate;//交易日期
	private Date vchDate;//账务入账日期
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getEventType() {
		return eventType;
	}
	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}
	public Integer getBussType() {
		return bussType;
	}
	public void setBussType(Integer bussType) {
		this.bussType = bussType;
	}
	public String getTxrefNo() {
		return txrefNo;
	}
	public void setTxrefNo(String txrefNo) {
		this.txrefNo = txrefNo;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public String getCcyId() {
		return ccyId;
	}
	public void setCcyId(String ccyId) {
		this.ccyId = ccyId;
	}
	public String getBchId() {
		return bchId;
	}
	public void setBchId(String bchId) {
		this.bchId = bchId;
	}
	public Object getBussObject() {
		return bussObject;
	}
	public void setBussObject(Object bussObject) {
		this.bussObject = bussObject;
	}
	public String getLastModUser() {
		return lastModUser;
	}
	public void setLastModUser(String lastModUser) {
		this.lastModUser = lastModUser;
	}
	public Date getTxDate() {
		return txDate;
	}
	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}
	public Date getVchDate() {
		return vchDate;
	}
	public void setVchDate(Date vchDate) {
		this.vchDate = vchDate;
	}
}