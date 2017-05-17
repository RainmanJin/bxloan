package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

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

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.utils.CommonHelper;

@Entity
@Table(name = "KM_EXCEL_IN", schema = WD_SCHEMA)
public class KmExcelIn implements java.io.Serializable {
	private static final long serialVersionUID = -4715300785577508576L;
	
	/** 主键id*/
	@Id
	@SequenceGenerator(name = "KM_EXCEL_IN_SEQ", sequenceName = GlobalConstants.WD_SCHEMA+".KM_EXCEL_IN_SEQ", allocationSize = 1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KM_EXCEL_IN_SEQ") 
	@Column(name = "ID", unique = true, nullable = false)
	private Long excelInId;	
	
	/** 合同编号*/
	@Column(name = "CONTRACT_NUM", nullable = false, length = 100)
	private String contractNum;	
	
	/** 还款时间*/
	@Temporal(TemporalType.DATE)
	@Column(name = "REPAY_DATE", nullable = false, length = 10)
	private Date repayDate;	
	
	/** 还款金额*/
	@Column(name = "REPAY_AMT", nullable = false, precision = 20, scale=2)
	private BigDecimal repayAmt; 
	
	/** 机构id*/
	@Column(name = "ORG_ID", nullable = false, length = 50)
	private String orgId; 
	
	/** 导入时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE", nullable = false)
	private Date insertDate; 
	
	/** 操作人*/
	@Column(name = "INSERT_USER", nullable = false, length = 50)
	private String insertUser; 
	
	/** 转出钱是否逾期标志（1、是  null 否）*/
	@Column(name = "YQ_FLAG", length = 20)
	private String yqFlag; 
	
	public KmExcelIn() {
		super();
	}
	
	public KmExcelIn(Object[] objs){
		super();
		int i = 0;
		this.excelInId = CommonHelper.toLong(objs[i++]); 
		this.contractNum = CommonHelper.toStr(objs[i++]);
		this.repayDate = CommonHelper.toDate(objs[i++]);
		this.repayAmt = CommonHelper.toBigDecimal(objs[i++]);
		this.insertDate = CommonHelper.toDate(objs[i++]);
		this.insertUser = CommonHelper.toStr(objs[i++]);
		this.orgId = CommonHelper.toStr(objs[i++]); 
		this.yqFlag = CommonHelper.toStr(objs[i++]);
	}
	
	public Long getExcelInId() {
		return excelInId;
	}

	public void setExcelInId(Long excelInId) {
		this.excelInId = excelInId;
	}

	public String getContractNum() {
		return contractNum;
	}
	
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	
	public Date getRepayDate() {
		return repayDate;
	}
	
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	
	public BigDecimal getRepayAmt() {
		return repayAmt;
	}
	
	public void setRepayAmt(BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Date getInsertDate() {
		return insertDate;
	}
	
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	public String getInsertUser() {
		return insertUser;
	}
	
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	
	public String getYqFlag() {
		return yqFlag;
	}
	
	public void setYqFlag(String yqFlag) {
		this.yqFlag = yqFlag;
	}
}
