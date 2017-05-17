package com.coamctech.bxloan.web.vo.customermng;

import java.math.BigDecimal;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class FamilyFriendAgroVo {
	private Long correlativeRelationsId;
	private String name;
	private String familyFriendType;
	private BigDecimal age;
	private String personStatus;
	private String workCompany;
	private BigDecimal monthlyIncome;
	private String degreeCd;
	private String telphone;
	private Long partyId;
	private String familyMembers;//是否是家庭成员
	
	
	public Long getCorrelativeRelationsId() {
		return correlativeRelationsId;
	}
	public void setCorrelativeRelationsId(Long correlativeRelationsId) {
		this.correlativeRelationsId = correlativeRelationsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamilyFriendType() {
		return familyFriendType;
	}
	public void setFamilyFriendType(String familyFriendType) {
		this.familyFriendType = familyFriendType;
	}
	public BigDecimal getAge() {
		return age;
	}
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	public String getPersonStatus() {
		return personStatus;
	}
	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}
	public String getWorkCompany() {
		return workCompany;
	}
	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}
	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getDegreeCd() {
		return degreeCd;
	}
	public void setDegreeCd(String degreeCd) {
		this.degreeCd = degreeCd;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	private FamilyFriendAgroVo(Long correlativeRelationsId, String name,
			String familyFriendType, BigDecimal age, String personStatus,
			String workCompany, BigDecimal monthlyIncome, String degreeCd,
			String telphone, String familyMembers) {
		super();
		this.correlativeRelationsId = correlativeRelationsId;
		this.name = name;
		this.familyFriendType = familyFriendType;
		this.age = age;
		this.personStatus = personStatus;
		this.workCompany = workCompany;
		this.monthlyIncome = monthlyIncome;
		this.degreeCd = degreeCd;
		this.telphone = telphone;
		this.familyMembers = familyMembers;
	}
	private FamilyFriendAgroVo() {
		super();
	}
	public FamilyFriendAgroVo(Object[] input) {
		int i = 0;
		this.correlativeRelationsId = CommonHelper.toLong(input[i++]);
		this.name = CommonHelper.toStr(input[i++]);
		this.familyFriendType = CommonHelper.toStr(input[i++]);
		this.age = CommonHelper.toBigDecimal(input[i++]);
		this.personStatus = CommonHelper.toStr(input[i++]);
		this.workCompany = CommonHelper.toStr(input[i++]);
		this.monthlyIncome = CommonHelper.toBigDecimal(input[i++]);
		this.degreeCd = CommonHelper.toStr(input[i++]);
		this.telphone = CommonHelper.toStr(input[i++]);
		this.partyId = CommonHelper.toLong(input[i++]);
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	public String getFamilyMembers() {
		return familyMembers;
	}
	public void setFamilyMembers(String familyMembers) {
		this.familyMembers = familyMembers;
	}
}
