package com.coamctech.bxloan.entity;
// default package
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 联保体小组成员表
 * 
 * @ Modify by wangxy 20150617
 */
@Entity
@Table(name = "UN_CUST_TAB", schema = GlobalConstants.WD_SCHEMA)
public class UnCustTab implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7163132892098575831L;
	// Fields
	private String id;//项目Id
	private Long uniteGuNegoId;//联保体Id
	private String unGuId;//联保体协议编号
	private String customerNum;//联保体成员客户编号
	private String customerName;//客户名称
	private String currency;//币种
	private BigDecimal unTrustAmt;//授信金额
	private String certificateTypeCd;//联保体成员证件类型
	private String certificateNum;//联保体成员证件号码
	private String seqUnCustTab;
	private String status;//状态
	private String partyId;//客户Id
	private String flag;//联保体标识

	// Constructors
	/** default constructor */
	public UnCustTab() {
	}

	/** minimal constructor */
	public UnCustTab(String id) {
		this.id = id;
	}

	/** full constructor */
	public UnCustTab(String id, String unGuId, String customerNum,
			String customerName, String currency, BigDecimal unTrustAmt,
			String certificateTypeCd, String certificateNum,
			String seqUnCustTab, String status, String partyId, String flag) {
		this.id = id;
		this.unGuId = unGuId;
		this.customerNum = customerNum;
		this.customerName = customerName;
		this.currency = currency;
		this.unTrustAmt = unTrustAmt;
		this.certificateTypeCd = certificateTypeCd;
		this.certificateNum = certificateNum;
		this.seqUnCustTab = seqUnCustTab;
		this.status = status;
		this.partyId = partyId;
		this.flag = flag;
	}

	/*public UnCustTab(Object[] obj) {
		int i=0;
		this.id = CommonHelper.toLong(obj[i++]);
		this.unGuId = CommonHelper.toStr(obj[i++]);
		this.customerNum =  CommonHelper.toStr(obj[i++]);
		this.customerName = CommonHelper.toStr(obj[i++]);
		this.currency = CommonHelper.toStr(obj[i++]);
		this.unTrustAmt = CommonHelper.toBigDecimal(obj[i++]);
		this.certificateTypeCd = CommonHelper.toStr(obj[i++]);
		this.certificateNum = CommonHelper.toStr(obj[i++]);
		this.seqUnCustTab = CommonHelper.toStr(obj[i++]);
		this.status = CommonHelper.toStr(obj[i++]);
		this.partyId = CommonHelper.toStr(obj[i++]);
		this.flag = CommonHelper.toStr(obj[i++]);
	}*/

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "UN_GU_ID", length = 20)
	public String getUnGuId() {
		return this.unGuId;
	}

	public void setUnGuId(String unGuId) {
		this.unGuId = unGuId;
	}

	@Column(name = "CUSTOMER_NUM", length = 21)
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

	@Column(name = "CURRENCY", length = 6)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "UN_TRUST_AMT", precision = 20)
	public BigDecimal getUnTrustAmt() {
		return this.unTrustAmt;
	}

	public void setUnTrustAmt(BigDecimal unTrustAmt) {
		this.unTrustAmt = unTrustAmt;
	}

	@Column(name = "CERTIFICATE_TYPE_CD", length = 30)
	public String getCertificateTypeCd() {
		return this.certificateTypeCd;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

	@Column(name = "CERTIFICATE_NUM", length = 60)
	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	@Column(name = "SEQ_UN_CUST_TAB", length = 100)
	public String getSeqUnCustTab() {
		return this.seqUnCustTab;
	}

	public void setSeqUnCustTab(String seqUnCustTab) {
		this.seqUnCustTab = seqUnCustTab;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PARTY_ID", length = 20)
	public String getPartyId() {
		return this.partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	@Column(name = "FLAG", length = 2)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getUniteGuNegoId() {
		return uniteGuNegoId;
	}
	@Column(name = "UNITE_GU_NEGO_ID", precision=20,scale=0)
	public void setUniteGuNegoId(Long uniteGuNegoId) {
		this.uniteGuNegoId = uniteGuNegoId;
	}
}
