package com.coamctech.bxloan.service.model.corpcustomer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class CorpCusSaveVO {
	
	private String partyId;
	private String customerName;
	private String industryLevelTwoCd;
	private String certificateNum;
	private String businessLicenseNum;
	private String registeredCapital;
	private String actualRevAmt;
	private String standedDate;
	private String nationalTaxRegistrationNum;
	private String localTaxRegistrationCum;
	private String loanCardNum;
	private String capitalScale;
	private String customerScale;
	private String staffNum;
	private String settlementAccountsOne;
	private String settlementAccountsFirst;
	private String settlementAccountsTwo;
	private String settlementAccountsSecond;
	private String linkmanName;
	private String linkmanTel;
	private String lastUpdateUserNum;
	private String saveType ;//保存类型
	private String markType[];
	/***所有制类别*/
	private String orgTypeCd;
	/***评级行业类型*/
	private String ratingIndustryType;
	/***企业客户特征*/
	private String corCustomerFeature;
	/***客户来源 */
	private String customerSource;
	////////////////
	/////COPY/////
	/////////////
	public Long getPartyId() {
		return Long.parseLong(partyId);
	}
	public BigDecimal getRegisteredCapital() {
		return StringUtils.isBlank(registeredCapital)?null:new BigDecimal(registeredCapital);
	}
	public BigDecimal getActualRevAmt() {
		return StringUtils.isBlank(actualRevAmt)?null:new BigDecimal(actualRevAmt);
	}
	public Date getStandedDate() {
		Date result = null;
		
		if(StringUtils.isBlank(standedDate)){
			return null;
		}else{
			try {
				result = new SimpleDateFormat("yyy-MM-dd").parse(standedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public Integer getStaffNum() {
		return StringUtils.isBlank(staffNum)?null:Integer.parseInt(staffNum);
	}
	public String getMarkType() {
		if(this.markType==null||this.markType.length<=0){
			return "";
		}
		return StringUtils.join(markType,",");
	}
	//////////////////////////////
	/////GETTERS&SETTERS////
	///////////////////////////
	
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getRatingIndustryType() {
		return ratingIndustryType;
	}
	public void setRatingIndustryType(String ratingIndustryType) {
		this.ratingIndustryType = ratingIndustryType;
	}
	public String getCorCustomerFeature() {
		return corCustomerFeature;
	}
	public void setCorCustomerFeature(String corCustomerFeature) {
		this.corCustomerFeature = corCustomerFeature;
	}
	public String getOrgTypeCd() {
		return orgTypeCd;
	}
	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}
	public void setMarkType(String[] markType) {
		this.markType = markType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getIndustryLevelTwoCd() {
		return industryLevelTwoCd;
	}
	public void setIndustryLevelTwoCd(String industryLevelTwoCd) {
		this.industryLevelTwoCd = industryLevelTwoCd;
	}
	public String getBusinessLicenseNum() {
		return businessLicenseNum;
	}
	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public void setActualRevAmt(String actualRevAmt) {
		this.actualRevAmt = actualRevAmt;
	}
	public void setStandedDate(String standedDate) {
		this.standedDate = standedDate;
	}
	public String getNationalTaxRegistrationNum() {
		return nationalTaxRegistrationNum;
	}
	public void setNationalTaxRegistrationNum(String nationalTaxRegistrationNum) {
		this.nationalTaxRegistrationNum = nationalTaxRegistrationNum;
	}
	public String getLocalTaxRegistrationCum() {
		return localTaxRegistrationCum;
	}
	public void setLocalTaxRegistrationCum(String localTaxRegistrationCum) {
		this.localTaxRegistrationCum = localTaxRegistrationCum;
	}
	public String getLoanCardNum() {
		return loanCardNum;
	}
	public void setLoanCardNum(String loanCardNum) {
		this.loanCardNum = loanCardNum;
	}
	public String getCapitalScale() {
		return capitalScale;
	}
	public void setCapitalScale(String capitalScale) {
		this.capitalScale = capitalScale;
	}
	public String getCustomerScale() {
		return customerScale;
	}
	public void setCustomerScale(String customerScale) {
		this.customerScale = customerScale;
	}
	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}
	public String getSettlementAccountsOne() {
		return settlementAccountsOne;
	}
	public void setSettlementAccountsOne(String settlementAccountsOne) {
		this.settlementAccountsOne = settlementAccountsOne;
	}
	public String getSettlementAccountsFirst() {
		return settlementAccountsFirst;
	}
	public void setSettlementAccountsFirst(String settlementAccountsFirst) {
		this.settlementAccountsFirst = settlementAccountsFirst;
	}
	public String getSettlementAccountsTwo() {
		return settlementAccountsTwo;
	}
	public void setSettlementAccountsTwo(String settlementAccountsTwo) {
		this.settlementAccountsTwo = settlementAccountsTwo;
	}
	public String getSettlementAccountsSecond() {
		return settlementAccountsSecond;
	}
	public void setSettlementAccountsSecond(String settlementAccountsSecond) {
		this.settlementAccountsSecond = settlementAccountsSecond;
	}
	public String getLinkmanName() {
		return linkmanName;
	}
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	public String getLinkmanTel() {
		return linkmanTel;
	}
	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}
	public String getLastUpdateUserNum() {
		return lastUpdateUserNum;
	}
	public void setLastUpdateUserNum(String lastUpdateUserNum) {
		this.lastUpdateUserNum = lastUpdateUserNum;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getCertificateNum() {
		return certificateNum;
	}
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	public String getCustomerSource() {
		return customerSource;
	}
	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}
	
}
