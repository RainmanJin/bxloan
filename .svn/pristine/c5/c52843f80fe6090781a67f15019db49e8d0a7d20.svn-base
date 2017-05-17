package com.coamctech.bxloan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 *	单据详情
 */
@Entity
@Table(name = "TALLY_CERTIFICATE_DETAIL", schema = WD_SCHEMA)
public class TallyCertificateDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8846528361120197675L;
	@SequenceGenerator(name = "generator", sequenceName="SEQ_TALLY_CERTIFICATE_DETAIL", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="TALLY_CERTIFICATE_DETAIL_ID")
	private Long tallyCertificateDetailId;
	@Column(name="BILL_CD",length=50)
	private String billCd;
	@Column(name="REC_NO",length=10)
	private String recNo;
	@Column(name="REC_EXP")
	private String recExp;
	@Column(name="RULE_TYP")
	private Integer ruleTyp;
	@Column(name="ACT_CD",length=50)
	private String actCd;
	@Column(name="ACT_NM",length=100)
	private String actNm;
	@Column(name="CURY_CD",length=50)
	private String curyCd;
	@Column(name="BLC_DIR")
	private Integer blcDir;
	@Column(name="REC_AMT",precision=16,scale=2)
	private BigDecimal recAmt;
	@Column(name="AST_TYP_CD1",length=50)
	private String astTypCd1;
	@Column(name="AST_TYP_NM1",length=60)
	private String astTypNm1;
	@Column(name="AST_CD1",length=50)
	private String astCd1;
	@Column(name="AST_NM1",length=50)
	private String astNm1;
	@Column(name="AST_TYP_CD2",length=50)
	private String astTypCd2;
	@Column(name="AST_TYP_NM2",length=50)
	private String astTypNm2;
	@Column(name="AST_CD2",length=50)
	private String astCd2;
	@Column(name="AST_NM2",length=50)
	private String astNm2;
	@Column(name="AST_TYP_CD3",length=50)
	private String astTypCd3;
	@Column(name="AST_TYP_NM3",length=50)
	private String astTypNm3;
	@Column(name="AST_CD3",length=50)
	private String astCd3;
	@Column(name="AST_NM3",length=50)
	private String astNm3;
	@Column(name="AST_TYP_CD4",length=50)
	private String astTypCd4;
	@Column(name="AST_TYP_NM4",length=50)
	private String astTypNm4;
	@Column(name="AST_CD4",length=50)
	private String astCd4;
	@Column(name="AST_NM4",length=50)
	private String astNm4;
	@Column(name="AST_TYP_CD5",length=50)
	private String astTypCd5;
	@Column(name="AST_TYP_NM5",length=50)
	private String astTypNm5;
	@Column(name="AST_CD5",length=50)
	private String astCd5;
	@Column(name="AST_NM5",length=50)
	private String astNm5;
	@Column(name="AST_TYP_CD6",length=50)
	private String astTypCd6;
	@Column(name="AST_TYP_NM6",length=50)
	private String astTypNm6;
	@Column(name="AST_CD6",length=50)
	private String astCd6;
	@Column(name="AST_NM6",length=50)
	private String astNm6;
	@Column(name="CREATE_DATE")
	@Temporal(TemporalType.DATE)
	private Date createDate;
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	@Column(name="CASH_CD_M",length=50)
	private String cashCdM;
	@Column(name="CASH_NM_M",length=50)
	private String cashNmM;
	@Column(name="CASH_CD_F",length=50)
	private String cashCdF;
	@Column(name="CASH_NM_F",length=50)
	private String cashNmF;
	@Column(name="CASH_REC",length=50)
	private String cashRec;
	public Long getTallyCertificateDetailId() {
		return tallyCertificateDetailId;
	}
	public void setTallyCertificateDetailId(Long tallyCertificateDetailId) {
		this.tallyCertificateDetailId = tallyCertificateDetailId;
	}
	public String getBillCd() {
		return billCd;
	}
	public void setBillCd(String billCd) {
		this.billCd = billCd;
	}
	public String getRecNo() {
		return recNo;
	}
	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}
	public String getRecExp() {
		return recExp;
	}
	public void setRecExp(String recExp) {
		this.recExp = recExp;
	}
	public Integer getRuleTyp() {
		return ruleTyp;
	}
	public void setRuleTyp(Integer ruleTyp) {
		this.ruleTyp = ruleTyp;
	}
	public String getActCd() {
		return actCd;
	}
	public void setActCd(String actCd) {
		this.actCd = actCd;
	}
	public String getActNm() {
		return actNm;
	}
	public void setActNm(String actNm) {
		this.actNm = actNm;
	}
	public String getCuryCd() {
		return curyCd;
	}
	public void setCuryCd(String curyCd) {
		this.curyCd = curyCd;
	}
	public Integer getBlcDir() {
		return blcDir;
	}
	public void setBlcDir(Integer blcDir) {
		this.blcDir = blcDir;
	}
	public BigDecimal getRecAmt() {
		return recAmt;
	}
	public void setRecAmt(BigDecimal recAmt) {
		this.recAmt = recAmt;
	}
	public String getAstTypCd1() {
		return astTypCd1;
	}
	public void setAstTypCd1(String astTypCd1) {
		this.astTypCd1 = astTypCd1;
	}
	public String getAstTypNm1() {
		return astTypNm1;
	}
	public void setAstTypNm1(String astTypNm1) {
		this.astTypNm1 = astTypNm1;
	}
	public String getAstCd1() {
		return astCd1;
	}
	public void setAstCd1(String astCd1) {
		this.astCd1 = astCd1;
	}
	public String getAstNm1() {
		return astNm1;
	}
	public void setAstNm1(String astNm1) {
		this.astNm1 = astNm1;
	}
	public String getAstTypCd2() {
		return astTypCd2;
	}
	public void setAstTypCd2(String astTypCd2) {
		this.astTypCd2 = astTypCd2;
	}
	public String getAstTypNm2() {
		return astTypNm2;
	}
	public void setAstTypNm2(String astTypNm2) {
		this.astTypNm2 = astTypNm2;
	}
	public String getAstCd2() {
		return astCd2;
	}
	public void setAstCd2(String astCd2) {
		this.astCd2 = astCd2;
	}
	public String getAstNm2() {
		return astNm2;
	}
	public void setAstNm2(String astNm2) {
		this.astNm2 = astNm2;
	}
	public String getAstTypCd3() {
		return astTypCd3;
	}
	public void setAstTypCd3(String astTypCd3) {
		this.astTypCd3 = astTypCd3;
	}
	public String getAstTypNm3() {
		return astTypNm3;
	}
	public void setAstTypNm3(String astTypNm3) {
		this.astTypNm3 = astTypNm3;
	}
	public String getAstCd3() {
		return astCd3;
	}
	public void setAstCd3(String astCd3) {
		this.astCd3 = astCd3;
	}
	public String getAstNm3() {
		return astNm3;
	}
	public void setAstNm3(String astNm3) {
		this.astNm3 = astNm3;
	}
	public String getAstTypCd4() {
		return astTypCd4;
	}
	public void setAstTypCd4(String astTypCd4) {
		this.astTypCd4 = astTypCd4;
	}
	public String getAstTypNm4() {
		return astTypNm4;
	}
	public void setAstTypNm4(String astTypNm4) {
		this.astTypNm4 = astTypNm4;
	}
	public String getAstCd4() {
		return astCd4;
	}
	public void setAstCd4(String astCd4) {
		this.astCd4 = astCd4;
	}
	public String getAstNm4() {
		return astNm4;
	}
	public void setAstNm4(String astNm4) {
		this.astNm4 = astNm4;
	}
	public String getAstTypCd5() {
		return astTypCd5;
	}
	public void setAstTypCd5(String astTypCd5) {
		this.astTypCd5 = astTypCd5;
	}
	public String getAstTypNm5() {
		return astTypNm5;
	}
	public void setAstTypNm5(String astTypNm5) {
		this.astTypNm5 = astTypNm5;
	}
	public String getAstCd5() {
		return astCd5;
	}
	public void setAstCd5(String astCd5) {
		this.astCd5 = astCd5;
	}
	public String getAstNm5() {
		return astNm5;
	}
	public void setAstNm5(String astNm5) {
		this.astNm5 = astNm5;
	}
	public String getAstTypCd6() {
		return astTypCd6;
	}
	public void setAstTypCd6(String astTypCd6) {
		this.astTypCd6 = astTypCd6;
	}
	public String getAstTypNm6() {
		return astTypNm6;
	}
	public void setAstTypNm6(String astTypNm6) {
		this.astTypNm6 = astTypNm6;
	}
	public String getAstCd6() {
		return astCd6;
	}
	public void setAstCd6(String astCd6) {
		this.astCd6 = astCd6;
	}
	public String getAstNm6() {
		return astNm6;
	}
	public void setAstNm6(String astNm6) {
		this.astNm6 = astNm6;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCashCdM() {
		return cashCdM;
	}
	public void setCashCdM(String cashCdM) {
		this.cashCdM = cashCdM;
	}
	public String getCashNmM() {
		return cashNmM;
	}
	public void setCashNmM(String cashNmM) {
		this.cashNmM = cashNmM;
	}
	public String getCashCdF() {
		return cashCdF;
	}
	public void setCashCdF(String cashCdF) {
		this.cashCdF = cashCdF;
	}
	public String getCashNmF() {
		return cashNmF;
	}
	public void setCashNmF(String cashNmF) {
		this.cashNmF = cashNmF;
	}
	public String getCashRec() {
		return cashRec;
	}
	public void setCashRec(String cashRec) {
		this.cashRec = cashRec;
	}

}
