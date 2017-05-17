package com.coamctech.bxloan.entity;
// default package
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * ApprovalHistoryBxloan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APPROVAL_HISTORY_BXLOAN", schema = GlobalConstants.WD_SCHEMA)
public class ApprovalHistoryBxloan implements java.io.Serializable {
	// Fields
	private Long id;
	private Long projectId;
	private String projectNo;
	private BigDecimal approveAmt;
	private Long approveTerm;
	private String termUnit;
	private String approveRepayingMode;
	private String approveIrTypeCd;
	private BigDecimal approveRateValue;
	private String approveAdjustPeriod;
	private BigDecimal approveFloatRate;
	private Long workflowId;
	private Long taskId;
	private String taskStageCode;
	private Date createDate;
	private Date approveDate;
	private String approvalOpinion;
	/**授信类型*/
	private String creditType;
	// Constructors
	/** default constructor */
	public ApprovalHistoryBxloan() {
	}

	/** minimal constructor */
	public ApprovalHistoryBxloan(Long id) {
		this.id = id;
	}

	/** full constructor */
	public ApprovalHistoryBxloan(Long id, Long projectId,
			String projectNo, BigDecimal approveAmt, Long approveTerm,
			String termUnit, String approveRepayingMode,
			String approveIrTypeCd, BigDecimal approveRateValue,
			String approveAdjustPeriod, BigDecimal approveFloatRate,
			Long workflowId, Long taskId, String taskStageCode) {
		this.id = id;
		this.projectId = projectId;
		this.projectNo = projectNo;
		this.approveAmt = approveAmt;
		this.approveTerm = approveTerm;
		this.termUnit = termUnit;
		this.approveRepayingMode = approveRepayingMode;
		this.approveIrTypeCd = approveIrTypeCd;
		this.approveRateValue = approveRateValue;
		this.approveAdjustPeriod = approveAdjustPeriod;
		this.approveFloatRate = approveFloatRate;
		this.workflowId = workflowId;
		this.taskId = taskId;
		this.taskStageCode = taskStageCode;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_APPROVAL_HISTORY_BXLOAN",
    allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
		"generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_ID", precision = 20, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROJECT_NO", length = 50)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "APPROVE_AMT", precision = 20, scale = 5)
	public BigDecimal getApproveAmt() {
		return this.approveAmt;
	}

	public void setApproveAmt(BigDecimal approveAmt) {
		this.approveAmt = approveAmt;
	}

	@Column(name = "APPROVE_TERM", precision = 10, scale = 0)
	public Long getApproveTerm() {
		return this.approveTerm;
	}

	public void setApproveTerm(Long approveTerm) {
		this.approveTerm = approveTerm;
	}

	@Column(name = "TERM_UNIT", length = 2)
	public String getTermUnit() {
		return this.termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	@Column(name = "APPROVE_REPAYING_MODE", length = 2)
	public String getApproveRepayingMode() {
		return this.approveRepayingMode;
	}

	public void setApproveRepayingMode(String approveRepayingMode) {
		this.approveRepayingMode = approveRepayingMode;
	}

	@Column(name = "APPROVE_IR_TYPE_CD", length = 2)
	public String getApproveIrTypeCd() {
		return this.approveIrTypeCd;
	}

	public void setApproveIrTypeCd(String approveIrTypeCd) {
		this.approveIrTypeCd = approveIrTypeCd;
	}

	@Column(name = "APPROVE_RATE_VALUE", precision = 10, scale = 5)
	public BigDecimal getApproveRateValue() {
		return this.approveRateValue;
	}

	public void setApproveRateValue(BigDecimal approveRateValue) {
		this.approveRateValue = approveRateValue;
	}

	@Column(name = "APPROVE_ADJUST_PERIOD", length = 2)
	public String getApproveAdjustPeriod() {
		return this.approveAdjustPeriod;
	}

	public void setApproveAdjustPeriod(String approveAdjustPeriod) {
		this.approveAdjustPeriod = approveAdjustPeriod;
	}

	@Column(name = "APPROVE_FLOAT_RATE", precision = 10, scale = 5)
	public BigDecimal getApproveFloatRate() {
		return this.approveFloatRate;
	}

	public void setApproveFloatRate(BigDecimal approveFloatRate) {
		this.approveFloatRate = approveFloatRate;
	}

	@Column(name = "WORKFLOW_ID", precision = 10, scale = 0)
	public Long getWorkflowId() {
		return this.workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	@Column(name = "TASK_ID", precision = 10, scale = 0)
	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Column(name = "TASK_STAGE_CODE", length = 10)
	public String getTaskStageCode() {
		return this.taskStageCode;
	}

	public void setTaskStageCode(String taskStageCode) {
		this.taskStageCode = taskStageCode;
	}
	@Column(name = "CREATE_DATE", length = 10)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	@Column(name = "APPROVAL_OPINION", length = 200)
	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	@Column(name = "APPROVE_DATE", length = 10)
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	@Column(name = "CREDIT_TYPE", length = 30)
	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	
	
}
