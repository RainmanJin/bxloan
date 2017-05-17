package com.coamctech.bxloan.entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 催收
 */
@Entity
@Table(name = "DUN_REGISTER", schema = GlobalConstants.WD_SCHEMA)
public class DunRegister implements java.io.Serializable {

	// Fields

	private Long dunRegisterId;
	private Long projectId;
	private Long contractId;
	private String projectNo;
	private String dunningType;
	private String dunningMeasure;
	private Timestamp dunningDate;
	private String dunningResult;
	private Double replevyAmt;
	private String dunningPeople;
	private String situationDescription;
	private String registrant;
	private String registrantRole;
	private String registrantInBizGroup;
	private Timestamp createdTime;
	private Timestamp sysUpdateTime;
	private Double ovdueAmt;
	private Double ovdueInterest;
	private String dunningObject;
	private String lettertype;
	private String letterbillsnum;
	private String trustorgselect;
	private String trustlowname;
	private String trustcorgname;
	private String trustmainpeo;
	private String lawsuitplan;
	private String lawsuitissl;
	private String lawsuitreason;
	private String lawsuitcourt;
	private String lawsuitisla;
	private String lawsuitunreason;
	private String lawsuitcourtname;
	private Timestamp lawsuitfirstdate;
	private Timestamp lawsuitseconddate;
	private Timestamp lawsuitthreedate;
	private Timestamp lawsuitexecutedate;
	private String arbitrateorgname;
	private String notaryorgname;
	private String notarycourtname;
	private Timestamp notarydate;
	private String notaryresult;
	private String guarantyselect;
	private String guarantycardnum;
	private String guarantyway;
	private String guarantyorgname;
	private Timestamp guarantydate;
	private String guarantycardtype;
	private String guarantynumselect;
	private String guarantynamem;
	private String guarantytypecd;

	// Constructors

	/** default constructor */
	public DunRegister() {
	}

	/** minimal constructor */
	public DunRegister(Long dunRegisterId) {
		this.dunRegisterId = dunRegisterId;
	}

	/** full constructor */
	public DunRegister(Long dunRegisterId, Long projectId, Long contractId,
			String projectNo, String dunningType, String dunningMeasure,
			Timestamp dunningDate, String dunningResult, Double replevyAmt,
			String dunningPeople, String situationDescription,
			String registrant, String registrantRole,
			String registrantInBizGroup, Timestamp createdTime,
			Timestamp sysUpdateTime, Double ovdueAmt, Double ovdueInterest,
			String dunningObject, String lettertype, String letterbillsnum,
			String trustorgselect, String trustlowname, String trustcorgname,
			String trustmainpeo, String lawsuitplan, String lawsuitissl,
			String lawsuitreason, String lawsuitcourt, String lawsuitisla,
			String lawsuitunreason, String lawsuitcourtname,
			Timestamp lawsuitfirstdate, Timestamp lawsuitseconddate,
			Timestamp lawsuitthreedate, Timestamp lawsuitexecutedate,
			String arbitrateorgname, String notaryorgname,
			String notarycourtname, Timestamp notarydate, String notaryresult,
			String guarantyselect, String guarantycardnum, String guarantyway,
			String guarantyorgname, Timestamp guarantydate,
			String guarantycardtype, String guarantynumselect,
			String guarantynamem, String guarantytypecd) {
		this.dunRegisterId = dunRegisterId;
		this.projectId = projectId;
		this.contractId = contractId;
		this.projectNo = projectNo;
		this.dunningType = dunningType;
		this.dunningMeasure = dunningMeasure;
		this.dunningDate = dunningDate;
		this.dunningResult = dunningResult;
		this.replevyAmt = replevyAmt;
		this.dunningPeople = dunningPeople;
		this.situationDescription = situationDescription;
		this.registrant = registrant;
		this.registrantRole = registrantRole;
		this.registrantInBizGroup = registrantInBizGroup;
		this.createdTime = createdTime;
		this.sysUpdateTime = sysUpdateTime;
		this.ovdueAmt = ovdueAmt;
		this.ovdueInterest = ovdueInterest;
		this.dunningObject = dunningObject;
		this.lettertype = lettertype;
		this.letterbillsnum = letterbillsnum;
		this.trustorgselect = trustorgselect;
		this.trustlowname = trustlowname;
		this.trustcorgname = trustcorgname;
		this.trustmainpeo = trustmainpeo;
		this.lawsuitplan = lawsuitplan;
		this.lawsuitissl = lawsuitissl;
		this.lawsuitreason = lawsuitreason;
		this.lawsuitcourt = lawsuitcourt;
		this.lawsuitisla = lawsuitisla;
		this.lawsuitunreason = lawsuitunreason;
		this.lawsuitcourtname = lawsuitcourtname;
		this.lawsuitfirstdate = lawsuitfirstdate;
		this.lawsuitseconddate = lawsuitseconddate;
		this.lawsuitthreedate = lawsuitthreedate;
		this.lawsuitexecutedate = lawsuitexecutedate;
		this.arbitrateorgname = arbitrateorgname;
		this.notaryorgname = notaryorgname;
		this.notarycourtname = notarycourtname;
		this.notarydate = notarydate;
		this.notaryresult = notaryresult;
		this.guarantyselect = guarantyselect;
		this.guarantycardnum = guarantycardnum;
		this.guarantyway = guarantyway;
		this.guarantyorgname = guarantyorgname;
		this.guarantydate = guarantydate;
		this.guarantycardtype = guarantycardtype;
		this.guarantynumselect = guarantynumselect;
		this.guarantynamem = guarantynamem;
		this.guarantytypecd = guarantytypecd;
	}

	// Property accessors
	@Id
	@Column(name = "DUN_REGISTER_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getDunRegisterId() {
		return this.dunRegisterId;
	}

	public void setDunRegisterId(Long dunRegisterId) {
		this.dunRegisterId = dunRegisterId;
	}

	@Column(name = "PROJECT_ID", precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CONTRACT_ID", precision = 12, scale = 0)
	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@Column(name = "PROJECT_NO", length = 30)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "DUNNING_TYPE", length = 30)
	public String getDunningType() {
		return this.dunningType;
	}

	public void setDunningType(String dunningType) {
		this.dunningType = dunningType;
	}

	@Column(name = "DUNNING_MEASURE", length = 30)
	public String getDunningMeasure() {
		return this.dunningMeasure;
	}

	public void setDunningMeasure(String dunningMeasure) {
		this.dunningMeasure = dunningMeasure;
	}

	@Column(name = "DUNNING_DATE", length = 7)
	public Timestamp getDunningDate() {
		return this.dunningDate;
	}

	public void setDunningDate(Timestamp dunningDate) {
		this.dunningDate = dunningDate;
	}

	@Column(name = "DUNNING_RESULT", length = 30)
	public String getDunningResult() {
		return this.dunningResult;
	}

	public void setDunningResult(String dunningResult) {
		this.dunningResult = dunningResult;
	}

	@Column(name = "REPLEVY_AMT", precision = 20)
	public Double getReplevyAmt() {
		return this.replevyAmt;
	}

	public void setReplevyAmt(Double replevyAmt) {
		this.replevyAmt = replevyAmt;
	}

	@Column(name = "DUNNING_PEOPLE", length = 20)
	public String getDunningPeople() {
		return this.dunningPeople;
	}

	public void setDunningPeople(String dunningPeople) {
		this.dunningPeople = dunningPeople;
	}

	@Column(name = "SITUATION_DESCRIPTION", length = 4000)
	public String getSituationDescription() {
		return this.situationDescription;
	}

	public void setSituationDescription(String situationDescription) {
		this.situationDescription = situationDescription;
	}

	@Column(name = "REGISTRANT", length = 20)
	public String getRegistrant() {
		return this.registrant;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	@Column(name = "REGISTRANT_ROLE", length = 30)
	public String getRegistrantRole() {
		return this.registrantRole;
	}

	public void setRegistrantRole(String registrantRole) {
		this.registrantRole = registrantRole;
	}

	@Column(name = "REGISTRANT_IN_BIZ_GROUP", length = 100)
	public String getRegistrantInBizGroup() {
		return this.registrantInBizGroup;
	}

	public void setRegistrantInBizGroup(String registrantInBizGroup) {
		this.registrantInBizGroup = registrantInBizGroup;
	}

	@Column(name = "CREATED_TIME", length = 7)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "SYS_UPDATE_TIME", length = 7)
	public Timestamp getSysUpdateTime() {
		return this.sysUpdateTime;
	}

	public void setSysUpdateTime(Timestamp sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	@Column(name = "OVDUE_AMT", precision = 20)
	public Double getOvdueAmt() {
		return this.ovdueAmt;
	}

	public void setOvdueAmt(Double ovdueAmt) {
		this.ovdueAmt = ovdueAmt;
	}

	@Column(name = "OVDUE_INTEREST", precision = 20)
	public Double getOvdueInterest() {
		return this.ovdueInterest;
	}

	public void setOvdueInterest(Double ovdueInterest) {
		this.ovdueInterest = ovdueInterest;
	}

	@Column(name = "DUNNING_OBJECT", length = 30)
	public String getDunningObject() {
		return this.dunningObject;
	}

	public void setDunningObject(String dunningObject) {
		this.dunningObject = dunningObject;
	}

	@Column(name = "LETTERTYPE", length = 100)
	public String getLettertype() {
		return this.lettertype;
	}

	public void setLettertype(String lettertype) {
		this.lettertype = lettertype;
	}

	@Column(name = "LETTERBILLSNUM", length = 100)
	public String getLetterbillsnum() {
		return this.letterbillsnum;
	}

	public void setLetterbillsnum(String letterbillsnum) {
		this.letterbillsnum = letterbillsnum;
	}

	@Column(name = "TRUSTORGSELECT", length = 100)
	public String getTrustorgselect() {
		return this.trustorgselect;
	}

	public void setTrustorgselect(String trustorgselect) {
		this.trustorgselect = trustorgselect;
	}

	@Column(name = "TRUSTLOWNAME", length = 100)
	public String getTrustlowname() {
		return this.trustlowname;
	}

	public void setTrustlowname(String trustlowname) {
		this.trustlowname = trustlowname;
	}

	@Column(name = "TRUSTCORGNAME", length = 100)
	public String getTrustcorgname() {
		return this.trustcorgname;
	}

	public void setTrustcorgname(String trustcorgname) {
		this.trustcorgname = trustcorgname;
	}

	@Column(name = "TRUSTMAINPEO", length = 100)
	public String getTrustmainpeo() {
		return this.trustmainpeo;
	}

	public void setTrustmainpeo(String trustmainpeo) {
		this.trustmainpeo = trustmainpeo;
	}

	@Column(name = "LAWSUITPLAN", length = 100)
	public String getLawsuitplan() {
		return this.lawsuitplan;
	}

	public void setLawsuitplan(String lawsuitplan) {
		this.lawsuitplan = lawsuitplan;
	}

	@Column(name = "LAWSUITISSL", length = 100)
	public String getLawsuitissl() {
		return this.lawsuitissl;
	}

	public void setLawsuitissl(String lawsuitissl) {
		this.lawsuitissl = lawsuitissl;
	}

	@Column(name = "LAWSUITREASON", length = 100)
	public String getLawsuitreason() {
		return this.lawsuitreason;
	}

	public void setLawsuitreason(String lawsuitreason) {
		this.lawsuitreason = lawsuitreason;
	}

	@Column(name = "LAWSUITCOURT", length = 100)
	public String getLawsuitcourt() {
		return this.lawsuitcourt;
	}

	public void setLawsuitcourt(String lawsuitcourt) {
		this.lawsuitcourt = lawsuitcourt;
	}

	@Column(name = "LAWSUITISLA", length = 100)
	public String getLawsuitisla() {
		return this.lawsuitisla;
	}

	public void setLawsuitisla(String lawsuitisla) {
		this.lawsuitisla = lawsuitisla;
	}

	@Column(name = "LAWSUITUNREASON", length = 100)
	public String getLawsuitunreason() {
		return this.lawsuitunreason;
	}

	public void setLawsuitunreason(String lawsuitunreason) {
		this.lawsuitunreason = lawsuitunreason;
	}

	@Column(name = "LAWSUITCOURTNAME", length = 100)
	public String getLawsuitcourtname() {
		return this.lawsuitcourtname;
	}

	public void setLawsuitcourtname(String lawsuitcourtname) {
		this.lawsuitcourtname = lawsuitcourtname;
	}

	@Column(name = "LAWSUITFIRSTDATE", length = 7)
	public Timestamp getLawsuitfirstdate() {
		return this.lawsuitfirstdate;
	}

	public void setLawsuitfirstdate(Timestamp lawsuitfirstdate) {
		this.lawsuitfirstdate = lawsuitfirstdate;
	}

	@Column(name = "LAWSUITSECONDDATE", length = 7)
	public Timestamp getLawsuitseconddate() {
		return this.lawsuitseconddate;
	}

	public void setLawsuitseconddate(Timestamp lawsuitseconddate) {
		this.lawsuitseconddate = lawsuitseconddate;
	}

	@Column(name = "LAWSUITTHREEDATE", length = 7)
	public Timestamp getLawsuitthreedate() {
		return this.lawsuitthreedate;
	}

	public void setLawsuitthreedate(Timestamp lawsuitthreedate) {
		this.lawsuitthreedate = lawsuitthreedate;
	}

	@Column(name = "LAWSUITEXECUTEDATE", length = 7)
	public Timestamp getLawsuitexecutedate() {
		return this.lawsuitexecutedate;
	}

	public void setLawsuitexecutedate(Timestamp lawsuitexecutedate) {
		this.lawsuitexecutedate = lawsuitexecutedate;
	}

	@Column(name = "ARBITRATEORGNAME", length = 100)
	public String getArbitrateorgname() {
		return this.arbitrateorgname;
	}

	public void setArbitrateorgname(String arbitrateorgname) {
		this.arbitrateorgname = arbitrateorgname;
	}

	@Column(name = "NOTARYORGNAME", length = 100)
	public String getNotaryorgname() {
		return this.notaryorgname;
	}

	public void setNotaryorgname(String notaryorgname) {
		this.notaryorgname = notaryorgname;
	}

	@Column(name = "NOTARYCOURTNAME", length = 100)
	public String getNotarycourtname() {
		return this.notarycourtname;
	}

	public void setNotarycourtname(String notarycourtname) {
		this.notarycourtname = notarycourtname;
	}

	@Column(name = "NOTARYDATE", length = 7)
	public Timestamp getNotarydate() {
		return this.notarydate;
	}

	public void setNotarydate(Timestamp notarydate) {
		this.notarydate = notarydate;
	}

	@Column(name = "NOTARYRESULT", length = 100)
	public String getNotaryresult() {
		return this.notaryresult;
	}

	public void setNotaryresult(String notaryresult) {
		this.notaryresult = notaryresult;
	}

	@Column(name = "GUARANTYSELECT", length = 100)
	public String getGuarantyselect() {
		return this.guarantyselect;
	}

	public void setGuarantyselect(String guarantyselect) {
		this.guarantyselect = guarantyselect;
	}

	@Column(name = "GUARANTYCARDNUM", length = 100)
	public String getGuarantycardnum() {
		return this.guarantycardnum;
	}

	public void setGuarantycardnum(String guarantycardnum) {
		this.guarantycardnum = guarantycardnum;
	}

	@Column(name = "GUARANTYWAY", length = 100)
	public String getGuarantyway() {
		return this.guarantyway;
	}

	public void setGuarantyway(String guarantyway) {
		this.guarantyway = guarantyway;
	}

	@Column(name = "GUARANTYORGNAME", length = 100)
	public String getGuarantyorgname() {
		return this.guarantyorgname;
	}

	public void setGuarantyorgname(String guarantyorgname) {
		this.guarantyorgname = guarantyorgname;
	}

	@Column(name = "GUARANTYDATE", length = 7)
	public Timestamp getGuarantydate() {
		return this.guarantydate;
	}

	public void setGuarantydate(Timestamp guarantydate) {
		this.guarantydate = guarantydate;
	}

	@Column(name = "GUARANTYCARDTYPE", length = 100)
	public String getGuarantycardtype() {
		return this.guarantycardtype;
	}

	public void setGuarantycardtype(String guarantycardtype) {
		this.guarantycardtype = guarantycardtype;
	}

	@Column(name = "GUARANTYNUMSELECT", length = 100)
	public String getGuarantynumselect() {
		return this.guarantynumselect;
	}

	public void setGuarantynumselect(String guarantynumselect) {
		this.guarantynumselect = guarantynumselect;
	}

	@Column(name = "GUARANTYNAMEM", length = 100)
	public String getGuarantynamem() {
		return this.guarantynamem;
	}

	public void setGuarantynamem(String guarantynamem) {
		this.guarantynamem = guarantynamem;
	}

	@Column(name = "GUARANTYTYPECD", length = 100)
	public String getGuarantytypecd() {
		return this.guarantytypecd;
	}

	public void setGuarantytypecd(String guarantytypecd) {
		this.guarantytypecd = guarantytypecd;
	}

}