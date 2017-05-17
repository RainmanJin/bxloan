package com.coamctech.bxloan.entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 核销
 */


@Entity
@Table(name = "LOAN_CANCLE", schema = GlobalConstants.WD_SCHEMA)
public class LoanCancle implements java.io.Serializable {

	// Fields

	private Long loanCancleId;
	private Long projectId;
	private Long contractId;
	private Long partyId;
	private Double appCancleAmt;
	private Double appCancleAccural;
	private String cusLoanAmtUseCircs;
	private String expenseReason;
	private String loanAftermgrDunCircs;
	private String cleanChaseDutyCircs;
	private String cusmgrOpinion;
	private Timestamp createdTime;
	private Timestamp sysUpdateTime;
	private String loanCancleStateCd;
	private Long bpInfoId;
	private String applicant;
	private String applyOrg;
	private String hisConState;
	private String transactionNo;
	private String loanCancleNo;

	// Constructors

	/** default constructor */
	public LoanCancle() {
	}

	/** minimal constructor */
	public LoanCancle(Long loanCancleId) {
		this.loanCancleId = loanCancleId;
	}

	/** full constructor */
	public LoanCancle(Long loanCancleId, Long projectId, Long contractId,
			Long partyId, Double appCancleAmt, Double appCancleAccural,
			String cusLoanAmtUseCircs, String expenseReason,
			String loanAftermgrDunCircs, String cleanChaseDutyCircs,
			String cusmgrOpinion, Timestamp createdTime,
			Timestamp sysUpdateTime, String loanCancleStateCd, Long bpInfoId,
			String applicant, String applyOrg, String hisConState,
			String transactionNo, String loanCancleNo) {
		this.loanCancleId = loanCancleId;
		this.projectId = projectId;
		this.contractId = contractId;
		this.partyId = partyId;
		this.appCancleAmt = appCancleAmt;
		this.appCancleAccural = appCancleAccural;
		this.cusLoanAmtUseCircs = cusLoanAmtUseCircs;
		this.expenseReason = expenseReason;
		this.loanAftermgrDunCircs = loanAftermgrDunCircs;
		this.cleanChaseDutyCircs = cleanChaseDutyCircs;
		this.cusmgrOpinion = cusmgrOpinion;
		this.createdTime = createdTime;
		this.sysUpdateTime = sysUpdateTime;
		this.loanCancleStateCd = loanCancleStateCd;
		this.bpInfoId = bpInfoId;
		this.applicant = applicant;
		this.applyOrg = applyOrg;
		this.hisConState = hisConState;
		this.transactionNo = transactionNo;
		this.loanCancleNo = loanCancleNo;
	}

	// Property accessors
	@Id
	@Column(name = "LOAN_CANCLE_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getLoanCancleId() {
		return this.loanCancleId;
	}

	public void setLoanCancleId(Long loanCancleId) {
		this.loanCancleId = loanCancleId;
	}

	@Column(name = "PROJECT_ID", precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CONTRACT_ID", precision = 12, scale = 0)
	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "APP_CANCLE_AMT", precision = 20)
	public Double getAppCancleAmt() {
		return this.appCancleAmt;
	}

	public void setAppCancleAmt(Double appCancleAmt) {
		this.appCancleAmt = appCancleAmt;
	}

	@Column(name = "APP_CANCLE_ACCURAL", precision = 20)
	public Double getAppCancleAccural() {
		return this.appCancleAccural;
	}

	public void setAppCancleAccural(Double appCancleAccural) {
		this.appCancleAccural = appCancleAccural;
	}

	@Column(name = "CUS_LOAN_AMT_USE_CIRCS", length = 4000)
	public String getCusLoanAmtUseCircs() {
		return this.cusLoanAmtUseCircs;
	}

	public void setCusLoanAmtUseCircs(String cusLoanAmtUseCircs) {
		this.cusLoanAmtUseCircs = cusLoanAmtUseCircs;
	}

	@Column(name = "EXPENSE_REASON", length = 4000)
	public String getExpenseReason() {
		return this.expenseReason;
	}

	public void setExpenseReason(String expenseReason) {
		this.expenseReason = expenseReason;
	}

	@Column(name = "LOAN_AFTERMGR_DUN_CIRCS", length = 4000)
	public String getLoanAftermgrDunCircs() {
		return this.loanAftermgrDunCircs;
	}

	public void setLoanAftermgrDunCircs(String loanAftermgrDunCircs) {
		this.loanAftermgrDunCircs = loanAftermgrDunCircs;
	}

	@Column(name = "CLEAN_CHASE_DUTY_CIRCS", length = 4000)
	public String getCleanChaseDutyCircs() {
		return this.cleanChaseDutyCircs;
	}

	public void setCleanChaseDutyCircs(String cleanChaseDutyCircs) {
		this.cleanChaseDutyCircs = cleanChaseDutyCircs;
	}

	@Column(name = "CUSMGR_OPINION", length = 4000)
	public String getCusmgrOpinion() {
		return this.cusmgrOpinion;
	}

	public void setCusmgrOpinion(String cusmgrOpinion) {
		this.cusmgrOpinion = cusmgrOpinion;
	}

	@Column(name = "CREATED_TIME", length = 7)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "SYS_UPDATE_TIME", length = 7)
	public Timestamp getSysUpdateTime() {
		return this.sysUpdateTime;
	}

	public void setSysUpdateTime(Timestamp sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	@Column(name = "LOAN_CANCLE_STATE_CD", length = 1)
	public String getLoanCancleStateCd() {
		return this.loanCancleStateCd;
	}

	public void setLoanCancleStateCd(String loanCancleStateCd) {
		this.loanCancleStateCd = loanCancleStateCd;
	}

	@Column(name = "BP_INFO_ID", precision = 12, scale = 0)
	public Long getBpInfoId() {
		return this.bpInfoId;
	}

	public void setBpInfoId(Long bpInfoId) {
		this.bpInfoId = bpInfoId;
	}

	@Column(name = "APPLICANT", length = 50)
	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	@Column(name = "APPLY_ORG", length = 30)
	public String getApplyOrg() {
		return this.applyOrg;
	}

	public void setApplyOrg(String applyOrg) {
		this.applyOrg = applyOrg;
	}

	@Column(name = "HIS_CON_STATE", length = 20)
	public String getHisConState() {
		return this.hisConState;
	}

	public void setHisConState(String hisConState) {
		this.hisConState = hisConState;
	}

	@Column(name = "TRANSACTION_NO", length = 100)
	public String getTransactionNo() {
		return this.transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	@Column(name = "LOAN_CANCLE_NO", length = 30)
	public String getLoanCancleNo() {
		return this.loanCancleNo;
	}

	public void setLoanCancleNo(String loanCancleNo) {
		this.loanCancleNo = loanCancleNo;
	}

}