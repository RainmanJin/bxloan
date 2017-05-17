package com.coamctech.bxloan.entity;
// default package

import java.math.BigDecimal;
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
 * Individual entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INDIVIDUAL", schema = GlobalConstants.WD_SCHEMA)
public class Individual  implements java.io.Serializable {

	// Fields

	private Long partyId;
	private String customerNum;
	private String customerName;
	private String englishName;
	private Date createDate;
	private Date sysUpdateTime;
	private String chineseShortName;
	private String englishShortName;
	/**
	 * 性别
	 */
	private String genderCd;
	private Date birthday;
	private String marriageCd; //婚姻状况
	private String appellationCd;
	private String degreeCd;  //学历
	private String professionalLevelCd;
	private String permanentAddress;
	private String nation;
	private String workCompany;
	private String metier;
	private BigDecimal monIncome;
	private BigDecimal factMonIncome;
	private Date workBeginTime;
	private String spouseName;
	private String spouseCertificateType;
	private String spouseCertificateNum;
	private String spouseWorkCompany;
	private String spouseCompanyAddress;
	private String spouseCompanyTel;
	private String spouseMobileTel;
	private String spousePermanentAddress;
	private String inhabitancyStatus;
	private String postalcode;
	private BigDecimal familyMonIncome;
	private BigDecimal familyFactMonIncome;
	private BigDecimal FEqualMonIncome;
	private String mainPocketbook;
	private String otherPocketbook;
	private BigDecimal familyTotalAsset;
	private BigDecimal familyTotalOwes;
	private BigDecimal familyOutAssureTotal;
	private BigDecimal familyZcfzRatio;
	private BigDecimal familyHyzcfzRatio;
	private String socialSecurityCode;
	private String endowmentInsuranceInd;
	private String unemploymentInsuranceInd;
	private String accumulationFundInd;
	private String medicareInd;
	private String lodgingAllowanceInd;
	private String industryCd; 				//所属行业
	private Integer operationTerm;
	private String operationScope;
	private String extendCertificateAddress;
	private String principalBiz;
	private String secondaryBiz;
	private String custSource;
	private String orgCd;
	private String certificateTypeCd;
	private String certificateNum;
	private String mobileTel;
	private String companyTel;
	private String status;
	private String companyAddress;
	private String companyFax;
	private String qq;
	private String msn;
	private String familyAddress;
	private String familyTel;
	private String region;
	private BigDecimal bonusAndOtherIncome;
	private String houseCharacter;
	private BigDecimal maintainPersonNum;
	private String socialSecurityCase;
	private String companyType;
	private String professional;
	private BigDecimal cultureArea;
	private BigDecimal breedArea;
	private String breedType;
	private String agricultureMachine;
	private BigDecimal plantAndBreedIncome;
	private String businessLicenseNum;
	private String indCustomerTypeCd;
	private String customerSource; //客户来源
	private String localInhabitancyYears;//本地居住时间
	private String healthStatus;
	private String businessInsuranceCase;
	private String firsteEffect;
	private BigDecimal monthlyGiveBackAmt;
	private BigDecimal monthlyGovernableTotalAmt;
	private BigDecimal familyMonthlyTotalAmt;
	private BigDecimal familyMonthlyTotalPayout;
	private String alreadyManagementTime;
	private BigDecimal monthlyOtherAmt;
	private BigDecimal monthlyManagementCost;
	private BigDecimal monthlyIncomeTax;
	private BigDecimal monthlyBusinessIncome;
	private BigDecimal area;
	private String position;
	private BigDecimal nowPrice;
	private BigDecimal primaryPrice;
	private String centerPersonCredit;
	private String creditRecord;
	private String beforeCreditHistory;
	private String indBusinessesFeature;
	private String detailRemark;
	private String workRemark;
	private String economyRemark;
	private String statusFlag;
	private String markType;
	private String corporationtypecd;
	private String corporationBusinessAddress;
	private String corporationName;
	private BigDecimal annualSalesRevenue;
	private BigDecimal annualNetProfit;
	private String employmentType; //雇佣类型
	private BigDecimal companyFoundYear;
	private BigDecimal localWorkYear;
	private String houseCondition;
	private String telphone2;
    private String channelName;
    private String microAccount;
    private String email;
    private String chiledNum;
    private String censusType;
    private String paySocialSecurityInd;
    private String payFundInd;
    private String houseShareCondition;
    private BigDecimal houseShareNum;
    private String positiveInd;           //是否转正
    private BigDecimal currCompanyWorkYears; //现单位工作年限
    private String nationalityCd;         //国籍
	private String provinceCd;            //省份
	private String cityCd;                //城市
	private String countyCd;              //乡镇
	private String payday;                //还款日
	
	private Integer age; //年龄
	private String agroPopulation; //是否是农业人口
	private BigDecimal livingArea; //居住面积
	private String appliancesCase; //家电情况
	private String spouseCase; //配偶情况
	private String spouseEmployment; //配偶就业情况
	private Integer familySize; //家庭人口
	private Integer workForce; //劳动人口
	private Integer dependentPopulation; //供养人口
	private BigDecimal dependentPopulationRate; //供养人口比
	private String yearsInIndustry;  //现单位工作年限
	private String agroBizType; //是否农业为主
	private String idCardNum; //身份证号
	// Constructors
   

	/** default constructor */
	public Individual() {
	}

	/** minimal constructor */
	public Individual(Long partyId) {
		this.partyId = partyId;
	}

	/** full constructor */
	
	public Individual(Long partyId, String customerNum, String customerName,
			String englishName, Date createDate, Date sysUpdateTime,
			String chineseShortName, String englishShortName, String genderCd,
			Date birthday, String marriageCd, String appellationCd,
			String degreeCd, String professionalLevelCd,
			String permanentAddress, String nation, String workCompany,
			String metier, BigDecimal monIncome, BigDecimal factMonIncome,
			Date workBeginTime, String spouseName,
			String spouseCertificateType, String spouseCertificateNum,
			String spouseWorkCompany, String spouseCompanyAddress,
			String spouseCompanyTel, String spouseMobileTel,
			String spousePermanentAddress, String inhabitancyStatus,
			String postalcode, BigDecimal familyMonIncome,
			BigDecimal familyFactMonIncome, BigDecimal fEqualMonIncome,
			String mainPocketbook, String otherPocketbook,
			BigDecimal familyTotalAsset, BigDecimal familyTotalOwes,
			BigDecimal familyOutAssureTotal, BigDecimal familyZcfzRatio,
			BigDecimal familyHyzcfzRatio, String socialSecurityCode,
			String endowmentInsuranceInd, String unemploymentInsuranceInd,
			String accumulationFundInd, String medicareInd,
			String lodgingAllowanceInd, String industryCd,
			Integer operationTerm, String operationScope,
			String extendCertificateAddress, String principalBiz,
			String secondaryBiz, String custSource, String orgCd,
			String certificateTypeCd, String certificateNum, String mobileTel,
			String companyTel, String status, String companyAddress,
			String companyFax, String qq, String msn, String familyAddress,
			String familyTel, String region, BigDecimal bonusAndOtherIncome,
			String houseCharacter, BigDecimal maintainPersonNum,
			String socialSecurityCase, String companyType, String professional,
			BigDecimal cultureArea, BigDecimal breedArea, String breedType,
			String agricultureMachine, BigDecimal plantAndBreedIncome,
			String businessLicenseNum, String indCustomerTypeCd,
			String customerSource, String localInhabitancyYears,
			String healthStatus, String businessInsuranceCase,
			String firsteEffect, BigDecimal monthlyGiveBackAmt,
			BigDecimal monthlyGovernableTotalAmt, BigDecimal familyMonthlyTotalAmt,
			BigDecimal familyMonthlyTotalPayout, String alreadyManagementTime,
			BigDecimal monthlyOtherAmt, BigDecimal monthlyManagementCost,
			BigDecimal monthlyIncomeTax, BigDecimal monthlyBusinessIncome, BigDecimal area,
			String position, BigDecimal nowPrice, BigDecimal primaryPrice,
			String centerPersonCredit, String creditRecord,
			String beforeCreditHistory, String indBusinessesFeature,
			String detailRemark, String workRemark, String economyRemark,
			String statusFlag, String markType, String corporationtypecd,
			String corporationBusinessAddress, String corporationName,
			BigDecimal annualSalesRevenue, BigDecimal annualNetProfit,
			String employmentType, BigDecimal companyFoundYear,
			BigDecimal localWorkYear, String houseCondition,
			String telphone2, String channelName, String microAccount,
			String email, String chiledNum, String censusType,
			String paySocialSecurityInd, String payFundInd,
			String houseShareCondition, BigDecimal houseShareNum,
			String positiveInd, BigDecimal currCompanyWorkYears) {
		super();
		this.partyId = partyId;
		this.customerNum = customerNum;
		this.customerName = customerName;
		this.englishName = englishName;
		this.createDate = createDate;
		this.sysUpdateTime = sysUpdateTime;
		this.chineseShortName = chineseShortName;
		this.englishShortName = englishShortName;
		this.genderCd = genderCd;
		this.birthday = birthday;
		this.marriageCd = marriageCd;
		this.appellationCd = appellationCd;
		this.degreeCd = degreeCd;
		this.professionalLevelCd = professionalLevelCd;
		this.permanentAddress = permanentAddress;
		this.nation = nation;
		this.workCompany = workCompany;
		this.metier = metier;
		this.monIncome = monIncome;
		this.factMonIncome = factMonIncome;
		this.workBeginTime = workBeginTime;
		this.spouseName = spouseName;
		this.spouseCertificateType = spouseCertificateType;
		this.spouseCertificateNum = spouseCertificateNum;
		this.spouseWorkCompany = spouseWorkCompany;
		this.spouseCompanyAddress = spouseCompanyAddress;
		this.spouseCompanyTel = spouseCompanyTel;
		this.spouseMobileTel = spouseMobileTel;
		this.spousePermanentAddress = spousePermanentAddress;
		this.inhabitancyStatus = inhabitancyStatus;
		this.postalcode = postalcode;
		this.familyMonIncome = familyMonIncome;
		this.familyFactMonIncome = familyFactMonIncome;
		FEqualMonIncome = fEqualMonIncome;
		this.mainPocketbook = mainPocketbook;
		this.otherPocketbook = otherPocketbook;
		this.familyTotalAsset = familyTotalAsset;
		this.familyTotalOwes = familyTotalOwes;
		this.familyOutAssureTotal = familyOutAssureTotal;
		this.familyZcfzRatio = familyZcfzRatio;
		this.familyHyzcfzRatio = familyHyzcfzRatio;
		this.socialSecurityCode = socialSecurityCode;
		this.endowmentInsuranceInd = endowmentInsuranceInd;
		this.unemploymentInsuranceInd = unemploymentInsuranceInd;
		this.accumulationFundInd = accumulationFundInd;
		this.medicareInd = medicareInd;
		this.lodgingAllowanceInd = lodgingAllowanceInd;
		this.industryCd = industryCd;
		this.operationTerm = operationTerm;
		this.operationScope = operationScope;
		this.extendCertificateAddress = extendCertificateAddress;
		this.principalBiz = principalBiz;
		this.secondaryBiz = secondaryBiz;
		this.custSource = custSource;
		this.orgCd = orgCd;
		this.certificateTypeCd = certificateTypeCd;
		this.certificateNum = certificateNum;
		this.mobileTel = mobileTel;
		this.companyTel = companyTel;
		this.status = status;
		this.companyAddress = companyAddress;
		this.companyFax = companyFax;
		this.qq = qq;
		this.msn = msn;
		this.familyAddress = familyAddress;
		this.familyTel = familyTel;
		this.region = region;
		this.bonusAndOtherIncome = bonusAndOtherIncome;
		this.houseCharacter = houseCharacter;
		this.maintainPersonNum = maintainPersonNum;
		this.socialSecurityCase = socialSecurityCase;
		this.companyType = companyType;
		this.professional = professional;
		this.cultureArea = cultureArea;
		this.breedArea = breedArea;
		this.breedType = breedType;
		this.agricultureMachine = agricultureMachine;
		this.plantAndBreedIncome = plantAndBreedIncome;
		this.businessLicenseNum = businessLicenseNum;
		this.indCustomerTypeCd = indCustomerTypeCd;
		this.customerSource = customerSource;
		this.localInhabitancyYears = localInhabitancyYears;
		this.healthStatus = healthStatus;
		this.businessInsuranceCase = businessInsuranceCase;
		this.firsteEffect = firsteEffect;
		this.monthlyGiveBackAmt = monthlyGiveBackAmt;
		this.monthlyGovernableTotalAmt = monthlyGovernableTotalAmt;
		this.familyMonthlyTotalAmt = familyMonthlyTotalAmt;
		this.familyMonthlyTotalPayout = familyMonthlyTotalPayout;
		this.alreadyManagementTime = alreadyManagementTime;
		this.monthlyOtherAmt = monthlyOtherAmt;
		this.monthlyManagementCost = monthlyManagementCost;
		this.monthlyIncomeTax = monthlyIncomeTax;
		this.monthlyBusinessIncome = monthlyBusinessIncome;
		this.area = area;
		this.position = position;
		this.nowPrice = nowPrice;
		this.primaryPrice = primaryPrice;
		this.centerPersonCredit = centerPersonCredit;
		this.creditRecord = creditRecord;
		this.beforeCreditHistory = beforeCreditHistory;
		this.indBusinessesFeature = indBusinessesFeature;
		this.detailRemark = detailRemark;
		this.workRemark = workRemark;
		this.economyRemark = economyRemark;
		this.statusFlag = statusFlag;
		this.markType = markType;
		this.corporationtypecd = corporationtypecd;
		this.corporationBusinessAddress = corporationBusinessAddress;
		this.corporationName = corporationName;
		this.annualSalesRevenue = annualSalesRevenue;
		this.annualNetProfit = annualNetProfit;
		this.employmentType = employmentType;
		this.companyFoundYear = companyFoundYear;
		this.localWorkYear = localWorkYear;
		this.telphone2 = telphone2;
		this.channelName = channelName;
		this.microAccount = microAccount;
		this.email = email;
		this.chiledNum = chiledNum;
		this.censusType = censusType;
		this.paySocialSecurityInd = paySocialSecurityInd;
		this.payFundInd = payFundInd;
		this.houseShareCondition = houseShareCondition;
		this.houseShareNum = houseShareNum;
		this.positiveInd = positiveInd;
		this.currCompanyWorkYears = currCompanyWorkYears;
	}
	
	
	


	
	@Id
	@Column(name = "PARTY_ID", unique = true, nullable = false, precision = 12, scale = 0)
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

	@Column(name = "CUSTOMER_NAME", length = 100)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "ENGLISH_NAME", length = 100)
	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
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

	@Column(name = "CHINESE_SHORT_NAME", length = 50)
	public String getChineseShortName() {
		return this.chineseShortName;
	}

	public void setChineseShortName(String chineseShortName) {
		this.chineseShortName = chineseShortName;
	}

	@Column(name = "ENGLISH_SHORT_NAME", length = 18)
	public String getEnglishShortName() {
		return this.englishShortName;
	}

	public void setEnglishShortName(String englishShortName) {
		this.englishShortName = englishShortName;
	}

	@Column(name = "GENDER_CD", length = 1)
	public String getGenderCd() {
		return this.genderCd;
	}

	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	@Column(name = "BIRTHDAY", length = 7)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "MARRIAGE_CD", length = 30)
	public String getMarriageCd() {
		return this.marriageCd;
	}

	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}

	@Column(name = "APPELLATION_CD", length = 30)
	public String getAppellationCd() {
		return this.appellationCd;
	}

	public void setAppellationCd(String appellationCd) {
		this.appellationCd = appellationCd;
	}

	@Column(name = "DEGREE_CD", length = 30)
	public String getDegreeCd() {
		return this.degreeCd;
	}

	public void setDegreeCd(String degreeCd) {
		this.degreeCd = degreeCd;
	}

	@Column(name = "PROFESSIONAL_LEVEL_CD", length = 30)
	public String getProfessionalLevelCd() {
		return this.professionalLevelCd;
	}

	public void setProfessionalLevelCd(String professionalLevelCd) {
		this.professionalLevelCd = professionalLevelCd;
	}

	@Column(name = "PERMANENT_ADDRESS", length = 200)
	public String getPermanentAddress() {
		return this.permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	@Column(name = "NATION", length = 30)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "WORK_COMPANY", length = 100)
	public String getWorkCompany() {
		return this.workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	@Column(name = "METIER", length = 30)
	public String getMetier() {
		return this.metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}

	@Column(name = "MON_INCOME", precision = 20)
	public BigDecimal getMonIncome() {
		return this.monIncome;
	}

	public void setMonIncome(BigDecimal monIncome) {
		this.monIncome = monIncome;
	}

	@Column(name = "FACT_MON_INCOME", precision = 20)
	public BigDecimal getFactMonIncome() {
		return this.factMonIncome;
	}

	public void setFactMonIncome(BigDecimal factMonIncome) {
		this.factMonIncome = factMonIncome;
	}

	@Column(name = "WORK_BEGIN_TIME", length = 7)
	public Date getWorkBeginTime() {
		return this.workBeginTime;
	}

	public void setWorkBeginTime(Date workBeginTime) {
		this.workBeginTime = workBeginTime;
	}

	@Column(name = "SPOUSE_NAME", length = 100)
	public String getSpouseName() {
		return this.spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	@Column(name = "SPOUSE_CERTIFICATE_TYPE", length = 30)
	public String getSpouseCertificateType() {
		return this.spouseCertificateType;
	}

	public void setSpouseCertificateType(String spouseCertificateType) {
		this.spouseCertificateType = spouseCertificateType;
	}

	@Column(name = "SPOUSE_CERTIFICATE_NUM", length = 50)
	public String getSpouseCertificateNum() {
		return this.spouseCertificateNum;
	}

	public void setSpouseCertificateNum(String spouseCertificateNum) {
		this.spouseCertificateNum = spouseCertificateNum;
	}

	@Column(name = "SPOUSE_WORK_COMPANY", length = 100)
	public String getSpouseWorkCompany() {
		return this.spouseWorkCompany;
	}

	public void setSpouseWorkCompany(String spouseWorkCompany) {
		this.spouseWorkCompany = spouseWorkCompany;
	}

	@Column(name = "SPOUSE_COMPANY_ADDRESS", length = 200)
	public String getSpouseCompanyAddress() {
		return this.spouseCompanyAddress;
	}

	public void setSpouseCompanyAddress(String spouseCompanyAddress) {
		this.spouseCompanyAddress = spouseCompanyAddress;
	}

	@Column(name = "SPOUSE_COMPANY_TEL", length = 100)
	public String getSpouseCompanyTel() {
		return this.spouseCompanyTel;
	}

	public void setSpouseCompanyTel(String spouseCompanyTel) {
		this.spouseCompanyTel = spouseCompanyTel;
	}

	@Column(name = "SPOUSE_MOBILE_TEL", length = 100)
	public String getSpouseMobileTel() {
		return this.spouseMobileTel;
	}

	public void setSpouseMobileTel(String spouseMobileTel) {
		this.spouseMobileTel = spouseMobileTel;
	}

	@Column(name = "SPOUSE_PERMANENT_ADDRESS", length = 100)
	public String getSpousePermanentAddress() {
		return this.spousePermanentAddress;
	}

	public void setSpousePermanentAddress(String spousePermanentAddress) {
		this.spousePermanentAddress = spousePermanentAddress;
	}

	@Column(name = "INHABITANCY_STATUS", length = 30)
	public String getInhabitancyStatus() {
		return this.inhabitancyStatus;
	}

	public void setInhabitancyStatus(String inhabitancyStatus) {
		this.inhabitancyStatus = inhabitancyStatus;
	}

	@Column(name = "POSTALCODE", length = 30)
	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	@Column(name = "FAMILY_MON_INCOME", precision = 20)
	public BigDecimal getFamilyMonIncome() {
		return this.familyMonIncome;
	}

	public void setFamilyMonIncome(BigDecimal familyMonIncome) {
		this.familyMonIncome = familyMonIncome;
	}

	@Column(name = "FAMILY_FACT_MON_INCOME", precision = 20)
	public BigDecimal getFamilyFactMonIncome() {
		return this.familyFactMonIncome;
	}

	public void setFamilyFactMonIncome(BigDecimal familyFactMonIncome) {
		this.familyFactMonIncome = familyFactMonIncome;
	}

	@Column(name = "F_EQUAL_MON_INCOME", precision = 20)
	public BigDecimal getFEqualMonIncome() {
		return this.FEqualMonIncome;
	}

	public void setFEqualMonIncome(BigDecimal FEqualMonIncome) {
		this.FEqualMonIncome = FEqualMonIncome;
	}

	@Column(name = "MAIN_POCKETBOOK", length = 100)
	public String getMainPocketbook() {
		return this.mainPocketbook;
	}

	public void setMainPocketbook(String mainPocketbook) {
		this.mainPocketbook = mainPocketbook;
	}

	@Column(name = "OTHER_POCKETBOOK", length = 100)
	public String getOtherPocketbook() {
		return this.otherPocketbook;
	}

	public void setOtherPocketbook(String otherPocketbook) {
		this.otherPocketbook = otherPocketbook;
	}

	@Column(name = "FAMILY_TOTAL_ASSET", precision = 20)
	public BigDecimal getFamilyTotalAsset() {
		return this.familyTotalAsset;
	}

	public void setFamilyTotalAsset(BigDecimal familyTotalAsset) {
		this.familyTotalAsset = familyTotalAsset;
	}

	@Column(name = "FAMILY_TOTAL_OWES", precision = 20)
	public BigDecimal getFamilyTotalOwes() {
		return this.familyTotalOwes;
	}

	public void setFamilyTotalOwes(BigDecimal familyTotalOwes) {
		this.familyTotalOwes = familyTotalOwes;
	}

	@Column(name = "FAMILY_OUT_ASSURE_TOTAL", precision = 20)
	public BigDecimal getFamilyOutAssureTotal() {
		return this.familyOutAssureTotal;
	}

	public void setFamilyOutAssureTotal(BigDecimal familyOutAssureTotal) {
		this.familyOutAssureTotal = familyOutAssureTotal;
	}

	@Column(name = "FAMILY_ZCFZ_RATIO", precision = 16, scale = 8)
	public BigDecimal getFamilyZcfzRatio() {
		return this.familyZcfzRatio;
	}

	public void setFamilyZcfzRatio(BigDecimal familyZcfzRatio) {
		this.familyZcfzRatio = familyZcfzRatio;
	}

	@Column(name = "FAMILY_HYZCFZ_RATIO", precision = 16, scale = 8)
	public BigDecimal getFamilyHyzcfzRatio() {
		return this.familyHyzcfzRatio;
	}

	public void setFamilyHyzcfzRatio(BigDecimal familyHyzcfzRatio) {
		this.familyHyzcfzRatio = familyHyzcfzRatio;
	}

	@Column(name = "SOCIAL_SECURITY_CODE", length = 30)
	public String getSocialSecurityCode() {
		return this.socialSecurityCode;
	}

	public void setSocialSecurityCode(String socialSecurityCode) {
		this.socialSecurityCode = socialSecurityCode;
	}

	@Column(name = "ENDOWMENT_INSURANCE_IND", length = 1)
	public String getEndowmentInsuranceInd() {
		return this.endowmentInsuranceInd;
	}

	public void setEndowmentInsuranceInd(String endowmentInsuranceInd) {
		this.endowmentInsuranceInd = endowmentInsuranceInd;
	}

	@Column(name = "UNEMPLOYMENT_INSURANCE_IND", length = 1)
	public String getUnemploymentInsuranceInd() {
		return this.unemploymentInsuranceInd;
	}

	public void setUnemploymentInsuranceInd(String unemploymentInsuranceInd) {
		this.unemploymentInsuranceInd = unemploymentInsuranceInd;
	}

	@Column(name = "ACCUMULATION_FUND_IND", length = 1)
	public String getAccumulationFundInd() {
		return this.accumulationFundInd;
	}

	public void setAccumulationFundInd(String accumulationFundInd) {
		this.accumulationFundInd = accumulationFundInd;
	}

	@Column(name = "MEDICARE_IND", length = 1)
	public String getMedicareInd() {
		return this.medicareInd;
	}

	public void setMedicareInd(String medicareInd) {
		this.medicareInd = medicareInd;
	}

	@Column(name = "LODGING_ALLOWANCE_IND", length = 1)
	public String getLodgingAllowanceInd() {
		return this.lodgingAllowanceInd;
	}

	public void setLodgingAllowanceInd(String lodgingAllowanceInd) {
		this.lodgingAllowanceInd = lodgingAllowanceInd;
	}

	@Column(name = "INDUSTRY_CD", length = 30)
	public String getIndustryCd() {
		return this.industryCd;
	}

	public void setIndustryCd(String industryCd) {
		this.industryCd = industryCd;
	}

	@Column(name = "OPERATION_TERM", precision = 8, scale = 0)
	public Integer getOperationTerm() {
		return this.operationTerm;
	}

	public void setOperationTerm(Integer operationTerm) {
		this.operationTerm = operationTerm;
	}

	@Column(name = "OPERATION_SCOPE", length = 500)
	public String getOperationScope() {
		return this.operationScope;
	}

	public void setOperationScope(String operationScope) {
		this.operationScope = operationScope;
	}

	@Column(name = "EXTEND_CERTIFICATE_ADDRESS", length = 100)
	public String getExtendCertificateAddress() {
		return this.extendCertificateAddress;
	}

	public void setExtendCertificateAddress(String extendCertificateAddress) {
		this.extendCertificateAddress = extendCertificateAddress;
	}

	@Column(name = "PRINCIPAL_BIZ", length = 100)
	public String getPrincipalBiz() {
		return this.principalBiz;
	}

	public void setPrincipalBiz(String principalBiz) {
		this.principalBiz = principalBiz;
	}

	@Column(name = "SECONDARY_BIZ", length = 100)
	public String getSecondaryBiz() {
		return this.secondaryBiz;
	}

	public void setSecondaryBiz(String secondaryBiz) {
		this.secondaryBiz = secondaryBiz;
	}

	@Column(name = "CUST_SOURCE", length = 30)
	public String getCustSource() {
		return this.custSource;
	}

	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	@Column(name = "ORG_CD", length = 30)
	public String getOrgCd() {
		return this.orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
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

	@Column(name = "MOBILE_TEL", length = 100)
	public String getMobileTel() {
		return this.mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	@Column(name = "COMPANY_TEL", length = 100)
	public String getCompanyTel() {
		return this.companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "COMPANY_ADDRESS", length = 100)
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "COMPANY_FAX", length = 50)
	public String getCompanyFax() {
		return this.companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	@Column(name = "QQ", length = 30)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "MSN", length = 30)
	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(name = "FAMILY_ADDRESS", length = 200)
	public String getFamilyAddress() {
		return this.familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	@Column(name = "FAMILY_TEL", length = 100)
	public String getFamilyTel() {
		return this.familyTel;
	}

	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
	}

	@Column(name = "REGION", length = 30)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "BONUS_AND_OTHER_INCOME", precision = 20)
	public BigDecimal getBonusAndOtherIncome() {
		return this.bonusAndOtherIncome;
	}

	public void setBonusAndOtherIncome(BigDecimal bonusAndOtherIncome) {
		this.bonusAndOtherIncome = bonusAndOtherIncome;
	}

	@Column(name = "HOUSE_CHARACTER", length = 30)
	public String getHouseCharacter() {
		return this.houseCharacter;
	}

	public void setHouseCharacter(String houseCharacter) {
		this.houseCharacter = houseCharacter;
	}

	@Column(name = "MAINTAIN_PERSON_NUM", precision = 20, scale = 0)
	public BigDecimal getMaintainPersonNum() {
		return this.maintainPersonNum;
	}

	public void setMaintainPersonNum(BigDecimal maintainPersonNum) {
		this.maintainPersonNum = maintainPersonNum;
	}

	@Column(name = "SOCIAL_SECURITY_CASE", length = 30)
	public String getSocialSecurityCase() {
		return this.socialSecurityCase;
	}

	public void setSocialSecurityCase(String socialSecurityCase) {
		this.socialSecurityCase = socialSecurityCase;
	}

	@Column(name = "COMPANY_TYPE", length = 30)
	public String getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name = "PROFESSIONAL", length = 30)
	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	@Column(name = "CULTURE_AREA", precision = 20)
	public BigDecimal getCultureArea() {
		return this.cultureArea;
	}

	public void setCultureArea(BigDecimal cultureArea) {
		this.cultureArea = cultureArea;
	}

	@Column(name = "BREED_AREA", precision = 20)
	public BigDecimal getBreedArea() {
		return this.breedArea;
	}

	public void setBreedArea(BigDecimal breedArea) {
		this.breedArea = breedArea;
	}

	@Column(name = "BREED_TYPE", length = 30)
	public String getBreedType() {
		return this.breedType;
	}

	public void setBreedType(String breedType) {
		this.breedType = breedType;
	}

	@Column(name = "AGRICULTURE_MACHINE", length = 30)
	public String getAgricultureMachine() {
		return this.agricultureMachine;
	}

	public void setAgricultureMachine(String agricultureMachine) {
		this.agricultureMachine = agricultureMachine;
	}

	@Column(name = "PLANT_AND_BREED_INCOME", precision = 20)
	public BigDecimal getPlantAndBreedIncome() {
		return this.plantAndBreedIncome;
	}

	public void setPlantAndBreedIncome(BigDecimal plantAndBreedIncome) {
		this.plantAndBreedIncome = plantAndBreedIncome;
	}

	@Column(name = "BUSINESS_LICENSE_NUM", length = 30)
	public String getBusinessLicenseNum() {
		return this.businessLicenseNum;
	}

	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}

	@Column(name = "IND_CUSTOMER_TYPE_CD", length = 30)
	public String getIndCustomerTypeCd() {
		return this.indCustomerTypeCd;
	}

	public void setIndCustomerTypeCd(String indCustomerTypeCd) {
		this.indCustomerTypeCd = indCustomerTypeCd;
	}

	@Column(name = "CUSTOMER_SOURCE", length = 30)
	public String getCustomerSource() {
		return this.customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	@Column(name = "LOCAL_INHABITANCY_YEARS", length = 30)
	public String getLocalInhabitancyYears() {
		return this.localInhabitancyYears;
	}

	public void setLocalInhabitancyYears(String localInhabitancyYears) {
		this.localInhabitancyYears = localInhabitancyYears;
	}

	@Column(name = "HEALTH_STATUS", length = 200)
	public String getHealthStatus() {
		return this.healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	@Column(name = "BUSINESS_INSURANCE_CASE", length = 30)
	public String getBusinessInsuranceCase() {
		return this.businessInsuranceCase;
	}

	public void setBusinessInsuranceCase(String businessInsuranceCase) {
		this.businessInsuranceCase = businessInsuranceCase;
	}

	@Column(name = "FIRSTE_EFFECT", length = 2000)
	public String getFirsteEffect() {
		return this.firsteEffect;
	}

	public void setFirsteEffect(String firsteEffect) {
		this.firsteEffect = firsteEffect;
	}

	@Column(name = "MONTHLY_GIVE_BACK_AMT", precision = 20)
	public BigDecimal getMonthlyGiveBackAmt() {
		return this.monthlyGiveBackAmt;
	}

	public void setMonthlyGiveBackAmt(BigDecimal monthlyGiveBackAmt) {
		this.monthlyGiveBackAmt = monthlyGiveBackAmt;
	}

	@Column(name = "MONTHLY_GOVERNABLE_TOTAL_AMT", precision = 20)
	public BigDecimal getMonthlyGovernableTotalAmt() {
		return this.monthlyGovernableTotalAmt;
	}

	public void setMonthlyGovernableTotalAmt(BigDecimal monthlyGovernableTotalAmt) {
		this.monthlyGovernableTotalAmt = monthlyGovernableTotalAmt;
	}

	@Column(name = "FAMILY_MONTHLY_TOTAL_AMT", precision = 20)
	public BigDecimal getFamilyMonthlyTotalAmt() {
		return this.familyMonthlyTotalAmt;
	}

	public void setFamilyMonthlyTotalAmt(BigDecimal familyMonthlyTotalAmt) {
		this.familyMonthlyTotalAmt = familyMonthlyTotalAmt;
	}

	@Column(name = "FAMILY_MONTHLY_TOTAL_PAYOUT", precision = 20)
	public BigDecimal getFamilyMonthlyTotalPayout() {
		return this.familyMonthlyTotalPayout;
	}

	public void setFamilyMonthlyTotalPayout(BigDecimal familyMonthlyTotalPayout) {
		this.familyMonthlyTotalPayout = familyMonthlyTotalPayout;
	}

	@Column(name = "ALREADY_MANAGEMENT_TIME", length = 30)
	public String getAlreadyManagementTime() {
		return this.alreadyManagementTime;
	}

	public void setAlreadyManagementTime(String alreadyManagementTime) {
		this.alreadyManagementTime = alreadyManagementTime;
	}

	@Column(name = "MONTHLY_OTHER_AMT", precision = 20)
	public BigDecimal getMonthlyOtherAmt() {
		return this.monthlyOtherAmt;
	}

	public void setMonthlyOtherAmt(BigDecimal monthlyOtherAmt) {
		this.monthlyOtherAmt = monthlyOtherAmt;
	}

	@Column(name = "MONTHLY_MANAGEMENT_COST", precision = 20)
	public BigDecimal getMonthlyManagementCost() {
		return this.monthlyManagementCost;
	}

	public void setMonthlyManagementCost(BigDecimal monthlyManagementCost) {
		this.monthlyManagementCost = monthlyManagementCost;
	}

	@Column(name = "MONTHLY_INCOME_TAX", precision = 20)
	public BigDecimal getMonthlyIncomeTax() {
		return this.monthlyIncomeTax;
	}

	public void setMonthlyIncomeTax(BigDecimal monthlyIncomeTax) {
		this.monthlyIncomeTax = monthlyIncomeTax;
	}

	@Column(name = "MONTHLY_BUSINESS_INCOME", precision = 20)
	public BigDecimal getMonthlyBusinessIncome() {
		return this.monthlyBusinessIncome;
	}

	public void setMonthlyBusinessIncome(BigDecimal monthlyBusinessIncome) {
		this.monthlyBusinessIncome = monthlyBusinessIncome;
	}

	@Column(name = "AREA", precision = 20)
	public BigDecimal getArea() {
		return this.area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	@Column(name = "POSITION", length = 200)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "NOW_PRICE", precision = 20)
	public BigDecimal getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}

	@Column(name = "PRIMARY_PRICE", precision = 20)
	public BigDecimal getPrimaryPrice() {
		return this.primaryPrice;
	}

	public void setPrimaryPrice(BigDecimal primaryPrice) {
		this.primaryPrice = primaryPrice;
	}

	@Column(name = "CENTER_PERSON_CREDIT", length = 1)
	public String getCenterPersonCredit() {
		return this.centerPersonCredit;
	}

	public void setCenterPersonCredit(String centerPersonCredit) {
		this.centerPersonCredit = centerPersonCredit;
	}

	@Column(name = "CREDIT_RECORD", length = 1)
	public String getCreditRecord() {
		return this.creditRecord;
	}

	public void setCreditRecord(String creditRecord) {
		this.creditRecord = creditRecord;
	}

	@Column(name = "BEFORE_CREDIT_HISTORY", length = 1)
	public String getBeforeCreditHistory() {
		return this.beforeCreditHistory;
	}

	public void setBeforeCreditHistory(String beforeCreditHistory) {
		this.beforeCreditHistory = beforeCreditHistory;
	}

	@Column(name = "IND_BUSINESSES_FEATURE", length = 20)
	public String getIndBusinessesFeature() {
		return this.indBusinessesFeature;
	}

	public void setIndBusinessesFeature(String indBusinessesFeature) {
		this.indBusinessesFeature = indBusinessesFeature;
	}

	@Column(name = "DETAIL_REMARK", length = 2000)
	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	@Column(name = "WORK_REMARK", length = 2000)
	public String getWorkRemark() {
		return this.workRemark;
	}

	public void setWorkRemark(String workRemark) {
		this.workRemark = workRemark;
	}

	@Column(name = "ECONOMY_REMARK", length = 2000)
	public String getEconomyRemark() {
		return this.economyRemark;
	}

	public void setEconomyRemark(String economyRemark) {
		this.economyRemark = economyRemark;
	}

	@Column(name = "STATUS_FLAG", length = 20)
	public String getStatusFlag() {
		return this.statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	@Column(name = "MARK_TYPE", length = 20)
	public String getMarkType() {
		return this.markType;
	}

	public void setMarkType(String markType) {
		this.markType = markType;
	}

	@Column(name = "CORPORATIONTYPECD", length = 1)
	public String getCorporationtypecd() {
		return this.corporationtypecd;
	}

	public void setCorporationtypecd(String corporationtypecd) {
		this.corporationtypecd = corporationtypecd;
	}

	@Column(name = "CORPORATION_BUSINESS_ADDRESS", length = 200)
	public String getCorporationBusinessAddress() {
		return this.corporationBusinessAddress;
	}

	public void setCorporationBusinessAddress(String corporationBusinessAddress) {
		this.corporationBusinessAddress = corporationBusinessAddress;
	}

	@Column(name = "CORPORATION_NAME", length = 100)
	public String getCorporationName() {
		return this.corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	@Column(name = "ANNUAL_SALES_REVENUE", precision = 20)
	public BigDecimal getAnnualSalesRevenue() {
		return this.annualSalesRevenue;
	}

	public void setAnnualSalesRevenue(BigDecimal annualSalesRevenue) {
		this.annualSalesRevenue = annualSalesRevenue;
	}

	@Column(name = "ANNUAL_NET_PROFIT", precision = 20)
	public BigDecimal getAnnualNetProfit() {
		return this.annualNetProfit;
	}

	public void setAnnualNetProfit(BigDecimal annualNetProfit) {
		this.annualNetProfit = annualNetProfit;
	}

	@Column(name = "EMPLOYMENT_TYPE", length = 2)
	public String getEmploymentType() {
		return this.employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	@Column(name = "COMPANY_FOUND_YEAR", precision = 20, scale = 0)
	public BigDecimal getCompanyFoundYear() {
		return this.companyFoundYear;
	}

	public void setCompanyFoundYear(BigDecimal companyFoundYear) {
		this.companyFoundYear = companyFoundYear;
	}

	@Column(name = "LOCAL_WORK_YEAR", precision = 20, scale = 0)
	public BigDecimal getLocalWorkYear() {
		return this.localWorkYear;
	}

	public void setLocalWorkYear(BigDecimal localWorkYear) {
		this.localWorkYear = localWorkYear;
	}

	@Column(name = "HOUSE_CONDITION", length=2)
	public String getHouseCondition() {
		return this.houseCondition;
	}

	public void setHouseCondition(String houseCondition) {
		this.houseCondition = houseCondition;
	}
	
	
	

	@Column(name = "TELPHONE2", length = 50)
	public String getTelphone2() {
		return this.telphone2;
	}

	public void setTelphone2(String telphone2) {
		this.telphone2 = telphone2;
	}
	
	@Column(name = "CHANNEL_NAME", length = 100)
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	@Column(name = "MICRO_ACCOUNT", length = 50)
	public String getMicroAccount() {
		return microAccount;
	}

	public void setMicroAccount(String microAccount) {
		this.microAccount = microAccount;
	}
	
	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "CHILD_NUM", length = 20)
	public String getChiledNum() {
		return chiledNum;
	}

	public void setChiledNum(String chiledNum) {
		this.chiledNum = chiledNum;
	}
	@Column(name = "CENSUS_TYPE", length = 5)
	public String getCensusType() {
		return censusType;
	}

	public void setCensusType(String censusType) {
		this.censusType = censusType;
	}
	@Column(name = "PAY_SOCIALSECURITY_IND", length = 5)
	public String getPaySocialSecurityInd() {
		return paySocialSecurityInd;
	}

	public void setPaySocialSecurityInd(String paySocialSecurityInd) {
		this.paySocialSecurityInd = paySocialSecurityInd;
	}
	@Column(name = "PAY_FUND_IND", length = 5)
	public String getPayFundInd() {
		return payFundInd;
	}

	public void setPayFundInd(String payFundInd) {
		this.payFundInd = payFundInd;
	}
	@Column(name = "HOUSE_SHARE_CONDITION", length = 20)
	public String getHouseShareCondition() {
		return houseShareCondition;
	}

	public void setHouseShareCondition(String houseShareCondition) {
		this.houseShareCondition = houseShareCondition;
	}
	
	@Column(name = "HOUSE_SHARE_NUM", precision = 20, scale = 0)
	public BigDecimal getHouseShareNum() {
		return houseShareNum;
	}

	public void setHouseShareNum(BigDecimal houseShareNum) {
		this.houseShareNum = houseShareNum;
	}
	@Column(name = "POSITIVE_IND", length = 5)
	public String getPositiveInd() {
		return positiveInd;
	}

	public void setPositiveInd(String positiveInd) {
		this.positiveInd = positiveInd;
	}
	@Column(name = "CURR_COMPANY_WORK_YEARS", precision = 20, scale = 0)
	public BigDecimal getCurrCompanyWorkYears() {
		return currCompanyWorkYears;
	}

	public void setCurrCompanyWorkYears(BigDecimal currCompanyWorkYears) {
		this.currCompanyWorkYears = currCompanyWorkYears;
	}
	
	@Column(name = "NATIONALITY_CD", length = 30)
	public String getNationalityCd() {
		return this.nationalityCd;
	}

	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
	}

	@Column(name = "PROVINCE_CD", length = 30)
	public String getProvinceCd() {
		return this.provinceCd;
	}

	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}

	@Column(name = "CITY_CD", length = 30)
	public String getCityCd() {
		return this.cityCd;
	}

	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}

	@Column(name = "COUNTY_CD", length = 30)
	public String getCountyCd() {
		return this.countyCd;
	}

	public void setCountyCd(String countyCd) {
		this.countyCd = countyCd;
	}

	@Column(name = "PAYDAY", length = 2)
	public String getPayday() {
		return this.payday;
	}

	public void setPayday(String payday) {
		this.payday = payday;
	}
	@Column(name = "AGE", precision = 20, scale = 0)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name = "AGRO_POPULATION", length=1)
	public String getAgroPopulation() {
		return agroPopulation;
	}

	public void setAgroPopulation(String agroPopulation) {
		this.agroPopulation = agroPopulation;
	}
	@Column(name = "LIVING_AREA", precision = 20, scale = 0)
	public BigDecimal getLivingArea() {
		return livingArea;
	}

	public void setLivingArea(BigDecimal livingArea) {
		this.livingArea = livingArea;
	}
	@Column(name = "APPLIANCES_CASE", length = 10)
	public String getAppliancesCase() {
		return appliancesCase;
	}

	public void setAppliancesCase(String appliancesCase) {
		this.appliancesCase = appliancesCase;
	}
	@Column(name = "SPOUSE_CASE", length = 10)
	public String getSpouseCase() {
		return spouseCase;
	}

	public void setSpouseCase(String spouseCase) {
		this.spouseCase = spouseCase;
	}
	@Column(name = "SPOUSE_EMPLOYMENT", length = 10)
	public String getSpouseEmployment() {
		return spouseEmployment;
	}

	public void setSpouseEmployment(String spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}
	@Column(name = "FAMILY_SIZE", precision = 20, scale = 0)
	public Integer getFamilySize() {
		return familySize;
	}

	public void setFamilySize(Integer familySize) {
		this.familySize = familySize;
	}
	@Column(name = "WORKFORCE", precision = 20, scale = 0)
	public Integer getWorkForce() {
		return workForce;
	}

	public void setWorkForce(Integer workForce) {
		this.workForce = workForce;
	}
	@Column(name = "DEPENDENT_POPULATION", precision = 20, scale = 0)
	public Integer getDependentPopulation() {
		return dependentPopulation;
	}

	public void setDependentPopulation(Integer dependentPopulation) {
		this.dependentPopulation = dependentPopulation;
	}
	@Column(name = "DEPENDENT_POPULATION_RATE", precision = 20, scale = 0)
	public BigDecimal getDependentPopulationRate() {
		return dependentPopulationRate;
	}

	public void setDependentPopulationRate(BigDecimal dependentPopulationRate) {
		this.dependentPopulationRate = dependentPopulationRate;
	}
	@Column(name = "YEARS_IN_INDUSTRY", length=20)
	public String getYearsInIndustry() {
		return yearsInIndustry;
	}

	public void setYearsInIndustry(String yearsInIndustry) {
		this.yearsInIndustry = yearsInIndustry;
	}
	
	@Column(name = "ARGO_BIZ_TYPE", length = 1)
	public String getAgroBizType() {
		return agroBizType;
	}

	public void setAgroBizType(String agroBizType) {
		this.agroBizType = agroBizType;
	}
	@Column(name = "ID_CARD_NUM", length = 30)
	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	
}