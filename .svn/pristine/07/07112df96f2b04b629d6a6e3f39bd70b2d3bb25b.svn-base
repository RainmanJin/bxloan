package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**
 * 共同借款人
 * @author xc
 */
@Entity
@Table(name = "COMMON_BORROW", schema = WD_SCHEMA)
public class CommonBorrow implements java.io.Serializable {
	private Long commonBorrowId;
	private String customerName;
	private String certificateTypeCd;
	private String certificateNum;
	private String mobilePhone;
	private String phone;
	private String address;
	private Long projectId;
	private Long partyId;
	private String flag;
	private String spouseflag;
	//add by wangyawei 20150805 start 授信审批流程-制定电子合同环节-共同借款人列表增加
	/** 客户类型  */
	private String partyTypeCd;
	//add by wangyawei 20150805 end

	/** default constructor */
	public CommonBorrow() {
	}

	/** minimal constructor */
	public CommonBorrow(Long commonBorrowId) {
		this.commonBorrowId = commonBorrowId;
	}

	/** full constructor */
	public CommonBorrow(Long commonBorrowId, String customerName,
			String certificateTypeCd, String certificateNum,
			String mobilePhone, String phone, String address, Long projectId,
			Long partyId, String flag, String spouseflag) {
		this.commonBorrowId = commonBorrowId;
		this.customerName = customerName;
		this.certificateTypeCd = certificateTypeCd;
		this.certificateNum = certificateNum;
		this.mobilePhone = mobilePhone;
		this.phone = phone;
		this.address = address;
		this.projectId = projectId;
		this.partyId = partyId;
		this.flag = flag;
		this.spouseflag = spouseflag;
	}
	
	public CommonBorrow(Object[] objs) {
		super();
		int i = 0;
		this.commonBorrowId = CommonHelper.toLong(objs[i++]);
		this.customerName = CommonHelper.toStr(objs[i++]);
		this.certificateTypeCd = CommonHelper.toStr(objs[i++]);
		this.certificateNum = CommonHelper.toStr(objs[i++]);
		this.mobilePhone = CommonHelper.toStr(objs[i++]);
		this.phone = CommonHelper.toStr(objs[i++]);
		this.address = CommonHelper.toStr(objs[i++]);
		this.projectId = CommonHelper.toLong(objs[i++]);
		this.spouseflag = CommonHelper.toStr(objs[i++]);
		this.partyId = CommonHelper.toLong(objs[i++]);
		this.partyTypeCd = CommonHelper.toStr(objs[i++]);
	}

	@Id
	@Column(name = "COMMON_BORROW_ID", unique = true, nullable = false, precision = 12, scale = 0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_COMMON_BORROW", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	public Long getCommonBorrowId() {
		return this.commonBorrowId;
	}

	public void setCommonBorrowId(Long commonBorrowId) {
		this.commonBorrowId = commonBorrowId;
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

	@Column(name = "MOBILE_PHONE", length = 30)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "PHONE", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PROJECT_ID", precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "FLAG", length = 30)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "SPOUSEFLAG", length = 10)
	public String getSpouseflag() {
		return this.spouseflag;
	}

	public void setSpouseflag(String spouseflag) {
		this.spouseflag = spouseflag;
	}

	@Transient
	public String getPartyTypeCd() {
		return partyTypeCd;
	}

	public void setPartyTypeCd(String partyTypeCd) {
		this.partyTypeCd = partyTypeCd;
	}
}