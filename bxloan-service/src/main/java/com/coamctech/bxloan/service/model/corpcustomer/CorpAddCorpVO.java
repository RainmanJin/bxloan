package com.coamctech.bxloan.service.model.corpcustomer;

import static com.coamctech.bxloan.commons.GlobalConstants.CORPCUS_RELA_PERSON_TYPE_CONTROLLER;
import static com.coamctech.bxloan.commons.GlobalConstants.CORPCUS_RELA_PERSON_TYPE_STOCK;

import static com.coamctech.bxloan.commons.utils.NumberUtil.parseBigDecimal;
import static com.coamctech.bxloan.commons.utils.NumberUtil.parseLong;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


/**
 * 增加相关组织VO
 * @author xc
 */
public class CorpAddCorpVO {
	
	private String id;
	
	private String partyId;
	private String corpName;
	private String certificateCd;
	private String certificateTypeCd;
	private String businessLicenseNum;
	private String taxRegistrationNumNational;
	private String taxRegistrationNumLocal;
	private String orgTypeCd;
	private String currencyCd;
	private String registeredCapital;
	private String actualRevAmt;
	private String loanCardNum;
	private String corpEstablishDate;
	private String contactorName;
	private String contactorTelNum;
	private String addressRegist;
	private String addressContact;
	private String[] investmentTypeCd;
	private String investmentAmt;
	private String stockProportion;
	private String[] corpType;
	
	/////////////////////////
	/////FRO COPY/////////
	/////////////////
	public String getInvestmentTypeCd(){
		if(this.investmentTypeCd!=null&&investmentTypeCd.length>0){
			return StringUtils.join(investmentTypeCd,",");
		}
		return "";
	}
	public Long getId() {
		return parseLong(id);
	}
	public Long getPartyId() {
		return parseLong(partyId);
	}
	public BigDecimal getRegisteredCapital() {
		return parseBigDecimal(registeredCapital);
	}
	public BigDecimal getActualRevAmt() {
		return parseBigDecimal(actualRevAmt);
	}
	public BigDecimal getInvestmentAmt() {
		return parseBigDecimal(investmentAmt);
	}
	public BigDecimal getStockProportion() {
		return parseBigDecimal(stockProportion);
	}
	public Date getCorpEstablishDate() {
		if(StringUtils.isBlank(corpEstablishDate)){
			return null;
		}
		Date result = null;
		try {
			result = new SimpleDateFormat("yyyy-MM-dd").parse(corpEstablishDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getCorpIsActController() {
		if(this.corpType!=null&&this.corpType.length>0){
			for (String typeCd : corpType) {
				if(typeCd.equals(CORPCUS_RELA_PERSON_TYPE_CONTROLLER)){
					return "1";
				}
			}
		}
		return "0";
	}
	public String getCorpIsStockHolder() {
		if(this.corpType!=null&&this.corpType.length>0){
			for (String typeCd : corpType) {
				if(typeCd.equals(CORPCUS_RELA_PERSON_TYPE_STOCK)){
					return "1";
				}
			}
		}
		return "0";
	}
	///////////////////////////
	///////GETTERS&SETTERS/////
	///////////////////////////.
	public String[] getCorpType() {
		return corpType;
	}
	public void setCorpType(String[] corpType) {
		this.corpType = corpType;
	}
	public String getCorpName() {
		return corpName;
	}
	public String getCertificateCd() {
		return certificateCd;
	}
	public String getCertificateTypeCd() {
		return certificateTypeCd;
	}
	public String getBusinessLicenseNum() {
		return businessLicenseNum;
	}
	public String getTaxRegistrationNumNational() {
		return taxRegistrationNumNational;
	}
	public String getTaxRegistrationNumLocal() {
		return taxRegistrationNumLocal;
	}
	public String getOrgTypeCd() {
		return orgTypeCd;
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public String getLoanCardNum() {
		return loanCardNum;
	}
	public String getContactorName() {
		return contactorName;
	}
	public String getContactorTelNum() {
		return contactorTelNum;
	}
	public String getAddressRegist() {
		return addressRegist;
	}
	public String getAddressContact() {
		return addressContact;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public void setCertificateCd(String certificateCd) {
		this.certificateCd = certificateCd;
	}
	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}
	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}
	public void setTaxRegistrationNumNational(String taxRegistrationNumNational) {
		this.taxRegistrationNumNational = taxRegistrationNumNational;
	}
	public void setTaxRegistrationNumLocal(String taxRegistrationNumLocal) {
		this.taxRegistrationNumLocal = taxRegistrationNumLocal;
	}
	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public void setActualRevAmt(String actualRevAmt) {
		this.actualRevAmt = actualRevAmt;
	}
	public void setLoanCardNum(String loanCardNum) {
		this.loanCardNum = loanCardNum;
	}
	public void setCorpEstablishDate(String corpEstablishDate) {
		this.corpEstablishDate = corpEstablishDate;
	}
	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}
	public void setContactorTelNum(String contactorTelNum) {
		this.contactorTelNum = contactorTelNum;
	}
	public void setAddressRegist(String addressRegist) {
		this.addressRegist = addressRegist;
	}
	public void setAddressContact(String addressContact) {
		this.addressContact = addressContact;
	}
	public void setInvestmentTypeCd(String[] investmentTypeCd) {
		this.investmentTypeCd = investmentTypeCd;
	}
	public void setInvestmentAmt(String investmentAmt) {
		this.investmentAmt = investmentAmt;
	}
	public void setStockProportion(String stockProportion) {
		this.stockProportion = stockProportion;
	}
	
	
}
