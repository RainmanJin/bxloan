package com.coamctech.bxloan.entity;
// default package

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * SubContract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUBCONTRACT", schema = WD_SCHEMA)
public class SubContract implements java.io.Serializable {

	// Fields

	private Long subcontractId;
	private Long customerId;
	private String projectNo;
	private String contractNum;
	private String customerNum;
	private String subcontractNum;
	private BigDecimal guarantyAmt;
	private Date startDate;
	private Date expirationDate;
	private Integer term;
	private String termUnitCd;
	private String subcontractTypeCd;
	private String subcontractStatusCd;
	private Long handlingOrgCd;
	private String handlingUserNum;
	private String guarantCustomerNum;
	private BigDecimal guarantyRate;
	private String guaranteeTypeCd;
	private Date sysUpdateDate;
	private Date sysCreateDate;
	private Long contractId;
	private Long projectId;
	private Long guarantyId;
	private String guarantyNum;
	private Long assurerId;
	private String isTransDocument;
	private String documentNum;

	// Constructors

	/** default constructor */
	public SubContract() {
	}

	/** minimal constructor */
	public SubContract(Long subcontractId, Long contractId) {
		this.subcontractId = subcontractId;
		this.contractId = contractId;
	}

	/** full constructor */
	public SubContract(Long subcontractId, Long customerId, String projectNo,
			String contractNum, String customerNum, String subcontractNum,
			BigDecimal guarantyAmt, Date startDate, Date expirationDate,
			Integer term, String termUnitCd, String subcontractTypeCd,
			String subcontractStatusCd, Long handlingOrgCd,
			String handlingUserNum, String guarantCustomerNum,
			BigDecimal guarantyRate, String guaranteeTypeCd,
			Date sysUpdateDate, Date sysCreateDate, Long contractId,
			Long projectId, Long guarantyId, String guarantyNum,
			Long assurerId, String isTransDocument, String documentNum) {
		this.subcontractId = subcontractId;
		this.customerId = customerId;
		this.projectNo = projectNo;
		this.contractNum = contractNum;
		this.customerNum = customerNum;
		this.subcontractNum = subcontractNum;
		this.guarantyAmt = guarantyAmt;
		this.startDate = startDate;
		this.expirationDate = expirationDate;
		this.term = term;
		this.termUnitCd = termUnitCd;
		this.subcontractTypeCd = subcontractTypeCd;
		this.subcontractStatusCd = subcontractStatusCd;
		this.handlingOrgCd = handlingOrgCd;
		this.handlingUserNum = handlingUserNum;
		this.guarantCustomerNum = guarantCustomerNum;
		this.guarantyRate = guarantyRate;
		this.guaranteeTypeCd = guaranteeTypeCd;
		this.sysUpdateDate = sysUpdateDate;
		this.sysCreateDate = sysCreateDate;
		this.contractId = contractId;
		this.projectId = projectId;
		this.guarantyId = guarantyId;
		this.guarantyNum = guarantyNum;
		this.assurerId = assurerId;
		this.isTransDocument = isTransDocument;
		this.documentNum = documentNum;
	}

	@SequenceGenerator(name = "generator", sequenceName="SEQ_SUBCONTRACT", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "SUBCONTRACT_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getSubcontractId() {
		return this.subcontractId;
	}

	public void setSubcontractId(Long subcontractId) {
		this.subcontractId = subcontractId;
	}

	@Column(name = "CUSTOMER_ID", precision = 12, scale = 0)
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "PROJECT_NO", length = 40)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "CONTRACT_NUM", length = 40)
	public String getContractNum() {
		return this.contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	@Column(name = "CUSTOMER_NUM", length = 40)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "SUBCONTRACT_NUM", length = 30)
	public String getSubcontractNum() {
		return this.subcontractNum;
	}

	public void setSubcontractNum(String subcontractNum) {
		this.subcontractNum = subcontractNum;
	}

	@Column(name = "GUARANTY_AMT", precision = 20)
	public BigDecimal getGuarantyAmt() {
		return this.guarantyAmt;
	}

	public void setGuarantyAmt(BigDecimal guarantyAmt) {
		this.guarantyAmt = guarantyAmt;
	}

	@Column(name = "START_DATE", length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "EXPIRATION_DATE", length = 7)
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "TERM", precision = 6, scale = 0)
	public Integer getTerm() {
		return this.term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	@Column(name = "TERM_UNIT_CD", length = 20)
	public String getTermUnitCd() {
		return this.termUnitCd;
	}

	public void setTermUnitCd(String termUnitCd) {
		this.termUnitCd = termUnitCd;
	}

	@Column(name = "SUBCONTRACT_TYPE_CD", length = 20)
	public String getSubcontractTypeCd() {
		return this.subcontractTypeCd;
	}

	public void setSubcontractTypeCd(String subcontractTypeCd) {
		this.subcontractTypeCd = subcontractTypeCd;
	}

	@Column(name = "SUBCONTRACT_STATUS_CD", length = 20)
	public String getSubcontractStatusCd() {
		return this.subcontractStatusCd;
	}

	public void setSubcontractStatusCd(String subcontractStatusCd) {
		this.subcontractStatusCd = subcontractStatusCd;
	}

	@Column(name = "HANDLING_ORG_CD", precision = 12, scale = 0)
	public Long getHandlingOrgCd() {
		return this.handlingOrgCd;
	}

	public void setHandlingOrgCd(Long handlingOrgCd) {
		this.handlingOrgCd = handlingOrgCd;
	}

	@Column(name = "HANDLING_USER_NUM", length = 40)
	public String getHandlingUserNum() {
		return this.handlingUserNum;
	}

	public void setHandlingUserNum(String handlingUserNum) {
		this.handlingUserNum = handlingUserNum;
	}

	@Column(name = "GUARANT_CUSTOMER_NUM", length = 40)
	public String getGuarantCustomerNum() {
		return this.guarantCustomerNum;
	}

	public void setGuarantCustomerNum(String guarantCustomerNum) {
		this.guarantCustomerNum = guarantCustomerNum;
	}

	@Column(name = "GUARANTY_RATE", precision = 16, scale = 8)
	public BigDecimal getGuarantyRate() {
		return this.guarantyRate;
	}

	public void setGuarantyRate(BigDecimal guarantyRate) {
		this.guarantyRate = guarantyRate;
	}

	@Column(name = "GUARANTEE_TYPE_CD", length = 20)
	public String getGuaranteeTypeCd() {
		return this.guaranteeTypeCd;
	}

	public void setGuaranteeTypeCd(String guaranteeTypeCd) {
		this.guaranteeTypeCd = guaranteeTypeCd;
	}

	@Column(name = "SYS_UPDATE_DATE", length = 7)
	public Date getSysUpdateDate() {
		return this.sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	@Column(name = "SYS_CREATE_DATE", length = 7)
	public Date getSysCreateDate() {
		return this.sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	@Column(name = "CONTRACT_ID", nullable = false, precision = 12, scale = 0)
	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@Column(name = "PROJECT_ID", precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "GUARANTY_ID", precision = 12, scale = 0)
	public Long getGuarantyId() {
		return this.guarantyId;
	}

	public void setGuarantyId(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	@Column(name = "GUARANTY_NUM", length = 40)
	public String getGuarantyNum() {
		return this.guarantyNum;
	}

	public void setGuarantyNum(String guarantyNum) {
		this.guarantyNum = guarantyNum;
	}

	@Column(name = "ASSURER_ID", precision = 12, scale = 0)
	public Long getAssurerId() {
		return this.assurerId;
	}

	public void setAssurerId(Long assurerId) {
		this.assurerId = assurerId;
	}

	@Column(name = "IS_TRANS_DOCUMENT", length = 2)
	public String getIsTransDocument() {
		return this.isTransDocument;
	}

	public void setIsTransDocument(String isTransDocument) {
		this.isTransDocument = isTransDocument;
	}

	@Column(name = "DOCUMENT_NUM", length = 30)
	public String getDocumentNum() {
		return this.documentNum;
	}

	public void setDocumentNum(String documentNum) {
		this.documentNum = documentNum;
	}

}