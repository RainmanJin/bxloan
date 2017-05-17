package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *	企业客户地址
 */
@Entity
@Table(name = "ADDRESS",schema=WD_SCHEMA)
public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1424352007689033015L;
	/**
	 * 
	 */
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_ADDRESS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="ADDRESS_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long addressId;
	@Column(name="PARTY_ID",precision=12,scale=0)
	private Long partyId;
	@Column(name="CUSTOMER_NUM",length=30)
	private String customerNum;
	@Column(name="ADDRESS_TYPE_CD",length=30)
	private String addressTypeCd;
	@Column(name="NATIONALITY_CD",length=30)
	private String nationalityCd;
	@Column(name="PROVINCE_CD",length=30)
	private String provinceCd;
	@Column(name="CITY_CD",length=30)
	private String cityCd;
	@Column(name="COUNTY_CD",length=30)
	private String countyCd;
	@Column(name="STREET_ADDRESS",length=100)
	private String streetAddress;
	@Column(name="ZIP_NUM",length=30)
	private String zipNum;
	@Column(name="TELEPHONE",length=20)
	private String telephone;
	@Column(name="MOBILE_TELEPHONE",length=20)
	private String mobileTelephone;
	@Column(name="FAX",length=20)
	private String fax;
	@Column(name="QQ",length=30)
	private String qq;
	@Column(name="WEBSITE",length=100)
	private String website;
	@Column(name="CREATE_DATE")
	private Date createDate;
	@Column(name="SYS_UPDATE_TIME")
	private Date sysUpdateTime;
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getAddressTypeCd() {
		return addressTypeCd;
	}
	public void setAddressTypeCd(String addressTypeCd) {
		this.addressTypeCd = addressTypeCd;
	}
	public String getNationalityCd() {
		return nationalityCd;
	}
	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
	}
	public String getProvinceCd() {
		return provinceCd;
	}
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}
	public String getCityCd() {
		return cityCd;
	}
	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}
	public String getCountyCd() {
		return countyCd;
	}
	public void setCountyCd(String countyCd) {
		this.countyCd = countyCd;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getZipNum() {
		return zipNum;
	}
	public void setZipNum(String zipNum) {
		this.zipNum = zipNum;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobileTelephone() {
		return mobileTelephone;
	}
	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}
	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}
}
