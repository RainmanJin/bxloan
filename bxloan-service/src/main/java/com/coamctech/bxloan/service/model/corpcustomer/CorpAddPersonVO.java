package com.coamctech.bxloan.service.model.corpcustomer;

import static com.coamctech.bxloan.commons.GlobalConstants.CORPCUS_RELA_PERSON_TYPE_CONTROLLER;
import static com.coamctech.bxloan.commons.GlobalConstants.CORPCUS_RELA_PERSON_TYPE_MANAGER;
import static com.coamctech.bxloan.commons.GlobalConstants.CORPCUS_RELA_PERSON_TYPE_STOCK;
import static com.coamctech.bxloan.commons.GlobalConstants.CORPCUS_RELA_PERSON_TYPE_LEGAL_REPRESENTATIVE;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 新增自然人VO
 * @author xc
 */
public class CorpAddPersonVO {
	
	public static final String TYPE_SEP_STR = ",";

	private String id;
	private String partyId;
	/** * 类型*/
	private String[] personType;
	/** *客户姓名:	*/
	private String name;
	/*** 证件类型:	 */
	private String certificateTypeCd;
	/****证件号码:	**/
	private String certificateCd;
	/****出资方式:	*/
	private String[] investmentTypeCd;
	/***出资比例(%):	*/
	private String stockProportion;
	/****币种:	*/
	private String investmentCurrencyType;
	/***投资金额(万元):	*/
	private String investmentAmt;
	/**家庭电话:	**/
	private String familyTelNum;
	/***联系电话:	*/
	private String contactTelNum;
	/** *性别:*/
	private String sexCd;
	/****出生日期:	*/
	private String birthDate;
	/***民族:	*/
	private String nation;
	/***婚姻状况:	*/
	private String marriageCd;
	/***最高学历/教育状况:	*/
	private String degreeCd;
	/***居住类型:	*/
	private String inhabitancyStatus;
	/***邮箱/QQ:	*/
	private String mailQq;
	/***家庭详细住址:*/
	private String familyAddress;
	/***户籍所在地:	*/
	private String permanentAddress;
	/***工作单位:	*/
	private String workCompany;
	/***单位类型:	*/
	private String workCompanyType;
	/***单位电话:	*/
	private String workCompanyTel;
	/***职务/岗位:*/
	private String professional;
	/***职务*/
	private String positionCd;
	/***单位详细地址:	*/
	private String companyAddress;
	/***基本工资(元/年):*/
	private String incomeYear;
	/***奖金及其他收入(元/年):	*/
	private String incomeBonusYear;
	/***实际月收入(平均/元):	*/
	private String incomeMonthFact;
	/***家庭总资产(元):	*/
	private String familyTotalAsset;
	/***家庭总负债(元):	*/
	private String familyTotalOwes;
	/***家庭月均总收入(元):	*/
	private String familyMonthlyTotalAmt;
	/***家庭月均总支出(元):	*/
	private String familyMonthlyTotalPayout;
	/***信贷记录:	*/
	private String creditRecord;
	/***以前信贷历史(是否逾期)*/
	private String creditRecordOverdue;
	/***信贷备注:	*/
	private String creditRemark;
	/***自有住宅位置*/
	private String residencePosition;
	/***自有住宅面积*/
	private String residenceArea;
	/***自有住宅原价*/
	private String residencePrimaryPrice;
	/***自有住宅现价*/
	private String residenceNowPrice;
	
	///////////////
	////FOR COPY////
	////////////////
	public String getInvestmentTypeCd() {
		if(this.investmentTypeCd==null||this.investmentTypeCd.length<=0){
			return "";
		}
		return StringUtils.join(investmentTypeCd,",");
	}
	public String getPersonIsStockHolder() {
		if (personType != null && personType.length > 0) {
			for (String type : personType) {
				if (type.equals(CORPCUS_RELA_PERSON_TYPE_STOCK)) {
					return "1";
				}
			}
		}
		return "0";
	}
	
	public String getPersonIsActController() {
		if (personType != null && personType.length > 0) {
			for (String type : personType) {
				if (type.equals(CORPCUS_RELA_PERSON_TYPE_CONTROLLER)) {
					return "1";
				}
			}
		}
		return "0";
	}
	
	public String getPersonIsManager() {
		if (personType != null && personType.length > 0) {
			for (String type : personType) {
				if (type.equals(CORPCUS_RELA_PERSON_TYPE_MANAGER)) {
					return "1";
				}
			}
		}
		return "0";
	}
	public String getPersonIsLegalRepresent() {
		if (personType != null && personType.length > 0) {
			for (String type : personType) {
				if (type.equals(CORPCUS_RELA_PERSON_TYPE_LEGAL_REPRESENTATIVE)) {
					return "1";
				}
			}
		}
		return "0";
	}
	
	public Long getPartyId() {
		return strToLong(this.partyId);
	}
	public Long getId() {
		return strToLong(this.id);
	}
	public BigDecimal getStockProportion() {
		return strToDecimal(stockProportion);
	}
	public BigDecimal getInvestmentAmt() {
		return strToDecimal(investmentAmt);
	}
	public Date getBirthDate() {
		if(StringUtils.isBlank(this.birthDate)){
			return null;
		}
		Date result = null;
		try {
			result = new SimpleDateFormat(GlobalConstants.DATE_FORMAT).parse(this.birthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	public BigDecimal getIncomeYear() {
		return strToDecimal(incomeYear);
	}
	public BigDecimal getIncomeBonusYear() {
		return strToDecimal(incomeBonusYear);
	}
	public BigDecimal getIncomeMonthFact() {
		return strToDecimal(incomeMonthFact);
	}
	public Long getResidenceArea() {
		return strToLong(residenceArea);
	}
	public BigDecimal getResidencePrimaryPrice() {
		return strToDecimal(residencePrimaryPrice);
	}
	public BigDecimal getResidenceNowPrice() {
		return strToDecimal(residenceNowPrice);
	}
	public BigDecimal getFamilyTotalAsset() {
		return strToDecimal(familyTotalAsset);
	}
	public BigDecimal getFamilyTotalOwes() {
		return strToDecimal(familyTotalOwes);
	}
	public BigDecimal getFamilyMonthlyTotalPayout() {
		return strToDecimal(familyMonthlyTotalPayout);
	}
	public BigDecimal getFamilyMonthlyTotalAmt() {
		return strToDecimal(familyMonthlyTotalAmt);
	}
	////////////////////////
	/////PRIVATE///////
	///////////////////
	private Long strToLong(String str){
		return StringUtils.isBlank(str)?null:Long.parseLong(str);
	}
	private BigDecimal strToDecimal(String str){
		return StringUtils.isBlank(str)?null:new BigDecimal(str);
	}
	/////////////////////////
	/////GETTERS&SETTERS/////
	//////////////////////////
	
	public String[] getPersonType() {
		return personType;
	}
	public String getPositionCd() {
		return positionCd;
	}
	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}
	public String getContactTelNum() {
		return contactTelNum;
	}
	public void setContactTelNum(String contactTelNum) {
		this.contactTelNum = contactTelNum;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPersonType(String[] personType) {
		this.personType = personType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertificateTypeCd() {
		return certificateTypeCd;
	}
	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}
	public String getCertificateCd() {
		return certificateCd;
	}
	public void setCertificateCd(String certificateCd) {
		this.certificateCd = certificateCd;
	}
	
	public void setInvestmentTypeCd(String[] investmentTypeCd) {
		this.investmentTypeCd = investmentTypeCd;
	}
	public void setStockProportion(String stockProportion) {
		this.stockProportion = stockProportion;
	}
	public String getInvestmentCurrencyType() {
		return investmentCurrencyType;
	}
	public void setInvestmentCurrencyType(String investmentCurrencyType) {
		this.investmentCurrencyType = investmentCurrencyType;
	}
	public void setInvestmentAmt(String investmentAmt) {
		this.investmentAmt = investmentAmt;
	}
	public String getFamilyTelNum() {
		return familyTelNum;
	}
	public void setFamilyTelNum(String familyTelNum) {
		this.familyTelNum = familyTelNum;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getMarriageCd() {
		return marriageCd;
	}
	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}
	public String getDegreeCd() {
		return degreeCd;
	}
	public void setDegreeCd(String degreeCd) {
		this.degreeCd = degreeCd;
	}
	public String getInhabitancyStatus() {
		return inhabitancyStatus;
	}
	public void setInhabitancyStatus(String inhabitancyStatus) {
		this.inhabitancyStatus = inhabitancyStatus;
	}
	public String getMailQq() {
		return mailQq;
	}
	public void setMailQq(String mailQq) {
		this.mailQq = mailQq;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getWorkCompany() {
		return workCompany;
	}
	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}
	public String getWorkCompanyType() {
		return workCompanyType;
	}
	public void setWorkCompanyType(String workCompanyType) {
		this.workCompanyType = workCompanyType;
	}
	public String getWorkCompanyTel() {
		return workCompanyTel;
	}
	public void setWorkCompanyTel(String workCompanyTel) {
		this.workCompanyTel = workCompanyTel;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public void setIncomeYear(String incomeYear) {
		this.incomeYear = incomeYear;
	}
	public void setIncomeBonusYear(String incomeBonusYear) {
		this.incomeBonusYear = incomeBonusYear;
	}
	public void setIncomeMonthFact(String incomeMonthFact) {
		this.incomeMonthFact = incomeMonthFact;
	}
	public void setFamilyTotalAsset(String familyTotalAsset) {
		this.familyTotalAsset = familyTotalAsset;
	}
	public void setFamilyTotalOwes(String familyTotalOwes) {
		this.familyTotalOwes = familyTotalOwes;
	}
	public void setFamilyMonthlyTotalAmt(String familyMonthlyTotalAmt) {
		this.familyMonthlyTotalAmt = familyMonthlyTotalAmt;
	}
	
	public void setFamilyMonthlyTotalPayout(String familyMonthlyTotalPayout) {
		this.familyMonthlyTotalPayout = familyMonthlyTotalPayout;
	}
	public String getCreditRecord() {
		return creditRecord;
	}
	public void setCreditRecord(String creditRecord) {
		this.creditRecord = creditRecord;
	}
	public String getCreditRecordOverdue() {
		return creditRecordOverdue;
	}
	public void setCreditRecordOverdue(String creditRecordOverdue) {
		this.creditRecordOverdue = creditRecordOverdue;
	}
	public String getCreditRemark() {
		return creditRemark;
	}
	public void setCreditRemark(String creditRemark) {
		this.creditRemark = creditRemark;
	}
	public String getResidencePosition() {
		return residencePosition;
	}
	public void setResidencePosition(String residencePosition) {
		this.residencePosition = residencePosition;
	}
	public void setResidenceArea(String residenceArea) {
		this.residenceArea = residenceArea;
	}
	public void setResidencePrimaryPrice(String residencePrimaryPrice) {
		this.residencePrimaryPrice = residencePrimaryPrice;
	}
	public void setResidenceNowPrice(String residenceNowPrice) {
		this.residenceNowPrice = residenceNowPrice;
	}
	
	
	
}
