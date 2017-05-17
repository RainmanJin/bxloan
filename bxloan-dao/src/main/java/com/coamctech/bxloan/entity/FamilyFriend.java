package com.coamctech.bxloan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：FamilyFriend
 * 类描述 ：重要联系人实体类
 * 创建人:   
 * 创建时间：
 * 修改人：王亚威
 * 修改时间：2015年5月5日 下午7:53:13  
 * 修改备注：增加各字段备注信息
 * 版本： V1.0
 */
@Entity
@Table(name = "FAMILY_FRIEND", schema = GlobalConstants.WD_SCHEMA)
public class FamilyFriend implements java.io.Serializable {
	private static final long serialVersionUID = -5792691798286016972L;
	private Long correlativeRelationsId;
	private Long partyId;
	private String name;				//联系人姓名
	private String certificateTypeCd;	//联系人证件类型
	private String certificateCd;		//联系人证件号码
	private String familyFriendType;	//与借款人关系重要联系人类型
	private String relationsType;		//联系人类型
	private String telphone;			//联系人电话
	private String address;				//联系人地址
	private String company;				//工作单位
	private String mobileTel;			//手机2
	private String censusRegister;		//户籍所在地
	private String school;				//学校名称
	private String grade;				//就读年级
	private String schoolTel;			//学校电话
	private String schoolAddress;		//学校地址
	private String receiveEducation;	//教育程度（客户性质为受薪）
	private String metier;				//职务
	private String workCompany;			//单位名称
	private String companyAddress;		//单位地址	
	private String companyTel;			//单位电话
	private String nowLiveAddress;		//现居住地

	private Integer age;				//年龄
	private String personStatus;		//状态codeType=FamiliyMemberStatus
	private BigDecimal monthlyIncome;	//月收入
	private String degreeCd;			//教育程度（客户性质为经营和农户）
	private String familyMembers;		//是否是家庭成员
	
	//comment by wangyawei 20150505 start 暂时未使用，可废弃
	private BigDecimal cultureArea;
	private BigDecimal familyHyzcfzRatio;
	private String collectionCriterion;		
	//comment by wangyawei 20150505 end

	/** default constructor */
	public FamilyFriend() {
		super();
	}

	/** minimal constructor */
	public FamilyFriend(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}

	/** full constructor */

	public FamilyFriend(Long correlativeRelationsId, Long partyId, String name,
			String certificateTypeCd, String certificateCd,
			String familyFriendType, String relationsType, String telphone,
			String address, String company, String mobileTel,
			String censusRegister, String school, String grade,
			String schoolTel, String schoolAddress, String receiveEducation,
			String metier, String workCompany, String companyAddress,
			String companyTel, String nowLiveAddress, Integer age,
			String personStatus, BigDecimal monthlyIncome, String degreeCd,
			String familyMembers, BigDecimal cultureArea,
			BigDecimal familyHyzcfzRatio, String collectionCriterion) {
		super();
		this.correlativeRelationsId = correlativeRelationsId;
		this.partyId = partyId;
		this.name = name;
		this.certificateTypeCd = certificateTypeCd;
		this.certificateCd = certificateCd;
		this.familyFriendType = familyFriendType;
		this.relationsType = relationsType;
		this.telphone = telphone;
		this.address = address;
		this.company = company;
		this.mobileTel = mobileTel;
		this.censusRegister = censusRegister;
		this.school = school;
		this.grade = grade;
		this.schoolTel = schoolTel;
		this.schoolAddress = schoolAddress;
		this.receiveEducation = receiveEducation;
		this.metier = metier;
		this.workCompany = workCompany;
		this.companyAddress = companyAddress;
		this.companyTel = companyTel;
		this.nowLiveAddress = nowLiveAddress;
		this.age = age;
		this.personStatus = personStatus;
		this.monthlyIncome = monthlyIncome;
		this.degreeCd = degreeCd;
		this.familyMembers = familyMembers;
		this.cultureArea = cultureArea;
		this.familyHyzcfzRatio = familyHyzcfzRatio;
		this.collectionCriterion = collectionCriterion;
	}
	
	/**
	 * object数组对象转换为object
	 * 
	 * @param objs
	 */
	public FamilyFriend(Object[] objs) {
		int i = 0;
		this.correlativeRelationsId=CommonHelper.toLong(objs[i++]);
		this.name=CommonHelper.toStr(objs[i++]);
		this.familyFriendType=CommonHelper.toStr(objs[i++]);
		this.certificateTypeCd=CommonHelper.toStr(objs[i++]);
		this.certificateCd=CommonHelper.toStr(objs[i++]);
		this.nowLiveAddress=CommonHelper.toStr(objs[i++]);
		this.telphone=CommonHelper.toStr(objs[i++]);
		this.companyTel=CommonHelper.toStr(objs[i++]);
		this.workCompany=CommonHelper.toStr(objs[i++]);
		this.companyAddress=CommonHelper.toStr(objs[i++]);
		this.partyId=CommonHelper.toLong(objs[i++]);
		this.relationsType=CommonHelper.toStr(objs[i++]);
	}
	// @SequenceGenerator(name = "generator", sequenceName="SEQ_FAMILY_FRIEND",
	// allocationSize = 1)
	@Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "generator")
	@Column(name = "CORRELATIVE_RELATIONS_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getCorrelativeRelationsId() {
		return this.correlativeRelationsId;
	}

	public void setCorrelativeRelationsId(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CERTIFICATE_TYPE_CD", length = 30)
	public String getCertificateTypeCd() {
		return this.certificateTypeCd;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

	@Column(name = "CERTIFICATE_CD", length = 50)
	public String getCertificateCd() {
		return this.certificateCd;
	}

	public void setCertificateCd(String certificateCd) {
		this.certificateCd = certificateCd;
	}

	@Column(name = "TELPHONE", length = 100)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "COLLECTION_CRITERION", length = 4000)
	public String getCollectionCriterion() {
		return this.collectionCriterion;
	}

	public void setCollectionCriterion(String collectionCriterion) {
		this.collectionCriterion = collectionCriterion;
	}

	@Column(name = "FAMILY_FRIEND_TYPE", length = 30)
	public String getFamilyFriendType() {
		return this.familyFriendType;
	}

	public void setFamilyFriendType(String familyFriendType) {
		this.familyFriendType = familyFriendType;
	}

	@Column(name = "COMPANY", length = 100)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "MOBILE_TEL", length = 30)
	public String getMobileTel() {
		return this.mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	@Column(name = "CENSUS_REGISTER", length = 100)
	public String getCensusRegister() {
		return this.censusRegister;
	}

	public void setCensusRegister(String censusRegister) {
		this.censusRegister = censusRegister;
	}

	@Column(name = "SCHOOL", length = 40)
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "GRADE", length = 20)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "SCHOOL_TEL", length = 100)
	public String getSchoolTel() {
		return this.schoolTel;
	}

	public void setSchoolTel(String schoolTel) {
		this.schoolTel = schoolTel;
	}

	@Column(name = "SCHOOL_ADDRESS", length = 100)
	public String getSchoolAddress() {
		return this.schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	@Column(name = "RECEIVE_EDUCATION", length = 2)
	public String getReceiveEducation() {
		return this.receiveEducation;
	}

	public void setReceiveEducation(String receiveEducation) {
		this.receiveEducation = receiveEducation;
	}

	@Column(name = "CULTURE_AREA", precision = 20, scale = 0)
	public BigDecimal getCultureArea() {
		return cultureArea;
	}
	
	public void setCultureArea(BigDecimal cultureArea) {
		this.cultureArea = cultureArea;
	}

	@Column(name = "FAMILY_HYZCFZ_RATIO", precision = 20, scale = 0)
	public BigDecimal getFamilyHyzcfzRatio() {
		return familyHyzcfzRatio;
	}
	
	public void setFamilyHyzcfzRatio(BigDecimal familyHyzcfzRatio) {
		this.familyHyzcfzRatio = familyHyzcfzRatio;
	}

	@Column(name = "METIER", length = 20)
	public String getMetier() {
		return this.metier;
	}


	public void setMetier(String metier) {
		this.metier = metier;
	}

	@Column(name = "WORK_COMPANY", length = 100)
	public String getWorkCompany() {
		return this.workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	@Column(name = "COMPANY_ADDRESS", length = 100)
	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "COMPANY_TEL", length = 50)
	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	@Column(name = "NOW_LIVE_ADDRESS", length = 50)
	public String getNowLiveAddress() {
		return nowLiveAddress;
	}

	public void setNowLiveAddress(String nowLiveAddress) {
		this.nowLiveAddress = nowLiveAddress;
	}

	@Column(name = "PARTY_ID", precision = 20, scale = 0)
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "RELATIONS_TYPE", length = 2)
	public String getRelationsType() {
		return relationsType;
	}

	public void setRelationsType(String relationsType) {
		this.relationsType = relationsType;
	}
	@Column(name = "AGE", precision = 20, scale = 0)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@Column(name = "PERSON_STATUS", length=3)
	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}
	@Column(name = "MONTHLY_INCOME", precision = 20, scale = 0)
	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}
	
	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	
	@Column(name = "DEGREECD", length=3)
	public String getDegreeCd() {
		return degreeCd;
	}


	public void setDegreeCd(String degreeCd) {
		this.degreeCd = degreeCd;
	}
	@Column(name = "Family_Members", precision = 2, scale = 0)
	public String getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(String familyMembers) {
		this.familyMembers = familyMembers;
	}
}