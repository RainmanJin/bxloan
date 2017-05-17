package com.coamctech.bxloan.entity;

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
 * 类名称：CustomerCorrelativeRelations
 * 类描述 ：客户关联关系表
 * 创建人:   
 * 创建时间：
 * 修改人：gph
 * 修改时间：2015年5月26日 下午15:07:26
 * 修改备注：增加各字段备注信息
 * 版本： V1.0
 */
@Entity
@Table(name = "CUSTOMER_CORRELATIVE_RELATIONS", schema = GlobalConstants.WD_SCHEMA)
public class CustomerCorrelativeRelations implements java.io.Serializable {
	private static final long serialVersionUID = -3879448913116199438L;
	
	/**编号：主键*/
	private Long correlativeRelationsId;
	/**参与人Id*/
	private Long partyId;
	/**客户编号*/
	private String customerNum;
	/**关联方客户类型*/
	private String correlativeCustomerTypeCd;
	/**关联方客户名称*/
	private String correlativeCustomerName;
	/**创建日期*/
	private Date createDate;
	/**系统更新日期*/
	private Date sysUpdateTime;
	/**是否有效*/
	private String availabilityInd;
	/**关联方证件类型*/
	private String corCertificateTypeCd;
	/**关联方证件号码*/
	private String correlativeCertificateCd;
	/**关联方客户Id*/
	private Long correlativeCustomerId;
	/**电话号码*/
	private String telphone;
	/**手机号码*/
	private String mobileNum;
	/**证件号码*/
	private String certificateCd;
	/**证件类型*/
	private String certificateTypeCd;

	// Constructors

	/** default constructor */
	public CustomerCorrelativeRelations() {
	}

	/** minimal constructor */
	public CustomerCorrelativeRelations(Long correlativeRelationsId,
			Long partyId) {
		this.correlativeRelationsId = correlativeRelationsId;
		this.partyId = partyId;
	}

	/** full constructor */
	public CustomerCorrelativeRelations(Long correlativeRelationsId,
			Long partyId, String customerNum, String correlativeCustomerTypeCd,
			String correlativeCustomerName, Date createDate,
			Date sysUpdateTime, String availabilityInd,
			String corCertificateTypeCd, String correlativeCertificateCd,
			Long correlativeCustomerId, String telphone, String mobileNum,
			String certificateCd, String certificateTypeCd) {
		this.correlativeRelationsId = correlativeRelationsId;
		this.partyId = partyId;
		this.customerNum = customerNum;
		this.correlativeCustomerTypeCd = correlativeCustomerTypeCd;
		this.correlativeCustomerName = correlativeCustomerName;
		this.createDate = createDate;
		this.sysUpdateTime = sysUpdateTime;
		this.availabilityInd = availabilityInd;
		this.corCertificateTypeCd = corCertificateTypeCd;
		this.correlativeCertificateCd = correlativeCertificateCd;
		this.correlativeCustomerId = correlativeCustomerId;
		this.telphone = telphone;
		this.mobileNum = mobileNum;
		this.certificateCd = certificateCd;
		this.certificateTypeCd = certificateTypeCd;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_CUSTOMER_CORRELATIVE_RELAT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "CORRELATIVE_RELATIONS_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getCorrelativeRelationsId() {
		return this.correlativeRelationsId;
	}

	public void setCorrelativeRelationsId(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}

	@Column(name = "PARTY_ID", nullable = false, precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CUSTOMER_NUM", length = 30)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "CORRELATIVE_CUSTOMER_TYPE_CD", length = 30)
	public String getCorrelativeCustomerTypeCd() {
		return this.correlativeCustomerTypeCd;
	}

	public void setCorrelativeCustomerTypeCd(String correlativeCustomerTypeCd) {
		this.correlativeCustomerTypeCd = correlativeCustomerTypeCd;
	}

	@Column(name = "CORRELATIVE_CUSTOMER_NAME", length = 100)
	public String getCorrelativeCustomerName() {
		return this.correlativeCustomerName;
	}

	public void setCorrelativeCustomerName(String correlativeCustomerName) {
		this.correlativeCustomerName = correlativeCustomerName;
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

	@Column(name = "AVAILABILITY_IND", length = 1)
	public String getAvailabilityInd() {
		return this.availabilityInd;
	}

	public void setAvailabilityInd(String availabilityInd) {
		this.availabilityInd = availabilityInd;
	}

	@Column(name = "COR_CERTIFICATE_TYPE_CD", length = 30)
	public String getCorCertificateTypeCd() {
		return this.corCertificateTypeCd;
	}

	public void setCorCertificateTypeCd(String corCertificateTypeCd) {
		this.corCertificateTypeCd = corCertificateTypeCd;
	}

	@Column(name = "CORRELATIVE_CERTIFICATE_CD", length = 50)
	public String getCorrelativeCertificateCd() {
		return this.correlativeCertificateCd;
	}

	public void setCorrelativeCertificateCd(String correlativeCertificateCd) {
		this.correlativeCertificateCd = correlativeCertificateCd;
	}

	@Column(name = "CORRELATIVE_CUSTOMER_ID", precision = 12, scale = 0)
	public Long getCorrelativeCustomerId() {
		return this.correlativeCustomerId;
	}

	public void setCorrelativeCustomerId(Long correlativeCustomerId) {
		this.correlativeCustomerId = correlativeCustomerId;
	}

	@Column(name = "TELPHONE", length = 20)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "MOBILE_NUM", length = 20)
	public String getMobileNum() {
		return this.mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	@Column(name = "CERTIFICATE_CD", length = 30)
	public String getCertificateCd() {
		return this.certificateCd;
	}

	public void setCertificateCd(String certificateCd) {
		this.certificateCd = certificateCd;
	}

	@Column(name = "CERTIFICATE_TYPE_CD", length = 30)
	public String getCertificateTypeCd() {
		return this.certificateTypeCd;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

}