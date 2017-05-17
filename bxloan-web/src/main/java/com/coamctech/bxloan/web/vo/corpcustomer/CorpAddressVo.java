package com.coamctech.bxloan.web.vo.corpcustomer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.DateTools;

public class CorpAddressVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3462297876807654549L;
	private Long addressId;
	private String addressTypeCd;
	private String streetAddress;
	private String telephone;
	private String updateTimeStr;
	private String nationCd;
	private String nationName;
	private String provinceCd;
	private String provinceName;
	private String cityCd;
	private String cityName;
	private String countyCd;
	private String countyName;
	
	public CorpAddressVo(Object[] input) {
		super();
		this.addressId=toLong(input[0]);
		this.addressTypeCd=str(input[1]);
		this.streetAddress=str(input[2]);
		this.telephone=str(input[3]);
		this.updateTimeStr=DateTools.dateToString((Date)input[4], "yyyy-MM-dd HH:mm:ss");
		this.nationCd=str(input[5]);
		this.nationName=str(input[6]);
		this.provinceCd=str(input[7]);
		this.provinceName=str(input[8]);
		this.cityCd=str(input[9]);
		this.cityName=str(input[10]);
		this.countyCd=str(input[11]);
		this.countyName=str(input[12]);
	}
	private String str(Object obj){
		return obj==null?"":obj.toString().trim();
	}
	private Long toLong(Object obj){
		return (obj!=null&&obj instanceof BigDecimal)?Long.parseLong(String.valueOf(obj)):0L;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getAddressTypeCd() {
		return addressTypeCd;
	}
	public void setAddressTypeCd(String addressTypeCd) {
		this.addressTypeCd = addressTypeCd;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
	public String getNationCd() {
		return nationCd;
	}
	public void setNationCd(String nationCd) {
		this.nationCd = nationCd;
	}
	public String getNationName() {
		return nationName;
	}
	public void setNationName(String nationName) {
		this.nationName = nationName;
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
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountyCd() {
		return countyCd;
	}
	public void setCountyCd(String countyCd) {
		this.countyCd = countyCd;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
}
