package com.coamctech.bxloan.entity;

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

import com.coamctech.bxloan.commons.entity.BaseEntity;

/**
 * 借款担保人
 * @author xc
 * 
 * @Modify by Wangxy 20150623
 */
@Entity
@Table(name = "PROJECT_ASSURER_INFO", schema = WD_SCHEMA)
public class ProjectAssurerInfo extends BaseEntity implements java.io.Serializable {


	private Long projectAssurerInfoId;//项目担保人信息ID
	private Long partyId;//参与人ID
	private String projectNo;//项目编号
	private Long correlativeRelationsId;//客户编码
	private String customerNum;
	private String customerName;
	private String certificateTypeCd;//证件类型
	private String certificateNum;//证件号码
	private Date createDate;
	private Date sysUpdateTime;//系统更新时间
	private String createPerson;
	private String assurerType;//保证类型
	private Long projectId;
	private BigDecimal guaranteeAmt;//本次申请保证债权金额
	private String currency;//币种
	private BigDecimal actualGuaranteeAmt;//本次实际保证债权金额
	private String wifeGuarante;//配偶是否做为共同保证人
	private String assureState;//状态
	/**
	 * 保证人来源，如：个人客户、企业客户、配偶、联保体
	 */
	private String assurerSource;

	// Constructors

	/** default constructor */
	public ProjectAssurerInfo() {
	}

	/** minimal constructor */
	public ProjectAssurerInfo(Long projectAssurerInfoId) {
		this.projectAssurerInfoId = projectAssurerInfoId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_PROJECT_ASSURER_INFO", allocationSize = 1)
	@Column(name = "PROJECT_ASSURER_INFO_ID", precision = 12, scale = 0)
	public Long getProjectAssurerInfoId() {
		return projectAssurerInfoId;
	}

	public void setProjectAssurerInfoId(Long projectAssurerInfoId) {
		this.projectAssurerInfoId = projectAssurerInfoId;
	}
	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "PROJECT_NO", length = 30)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "CORRELATIVE_RELATIONS_ID", precision = 12, scale = 0)
	public Long getCorrelativeRelationsId() {
		return this.correlativeRelationsId;
	}

	public void setCorrelativeRelationsId(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}

	@Column(name = "CUSTOMER_NUM", length = 30)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "CUSTOMER_NAME", length = 100)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "CERTIFICATE_TYPE_CD", length = 30)
	public String getCertificateTypeCd() {
		return this.certificateTypeCd;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

	@Column(name = "CERTIFICATE_NUM", length = 30)
	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "SYS_UPDATE_TIME", length = 7)
	public Date getSysUpdateTime() {
		return this.sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	@Column(name = "CREATE_PERSON", length = 20)
	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	@Column(name = "ASSURER_TYPE", length = 20)
	public String getAssurerType() {
		return this.assurerType;
	}

	public void setAssurerType(String assurerType) {
		this.assurerType = assurerType;
	}

	@Column(name = "PROJECT_ID", nullable = false, precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "GUARANTEE_AMT", precision = 20)
	public BigDecimal getGuaranteeAmt() {
		return this.guaranteeAmt;
	}

	public void setGuaranteeAmt(BigDecimal guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
	}

	@Column(name = "CURRENCY", length = 30)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "ACTUAL_GUARANTEE_AMT", precision = 20)
	public BigDecimal getActualGuaranteeAmt() {
		return this.actualGuaranteeAmt;
	}

	public void setActualGuaranteeAmt(BigDecimal actualGuaranteeAmt) {
		this.actualGuaranteeAmt = actualGuaranteeAmt;
	}

	@Column(name = "WIFE_GUARANTE", length = 20)
	public String getWifeGuarante() {
		return this.wifeGuarante;
	}

	public void setWifeGuarante(String wifeGuarante) {
		this.wifeGuarante = wifeGuarante;
	}

	@Column(name = "ASSURE_STATE", length = 20)
	public String getAssureState() {
		return this.assureState;
	}

	public void setAssureState(String assureState) {
		this.assureState = assureState;
	}
	
	@Column(name = "ASSURER_SOURCE", length = 20)
	public String getAssurerSource() {
		return assurerSource;
	}

	public void setAssurerSource(String assurerSource) {
		this.assurerSource = assurerSource;
	}

}