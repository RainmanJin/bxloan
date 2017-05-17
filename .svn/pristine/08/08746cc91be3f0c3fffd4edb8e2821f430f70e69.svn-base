package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

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

/**
 * @author AcoreHeng
 *
 */
@Entity
@Table(name = "EXPECTED_CASH_FLOW_INFO", schema = WD_SCHEMA)
public class ExpectedCashFlowInfo implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4031172499860219956L;
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_EXPECTED_CASH_FLOW_INFO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false, precision = 12, scale = 0)
	private Long id;
	@Column(name="PROJECT_ID")
	private Long projectId;
	/**
	 * 年月（yyyy-MM）
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="month_of_year")
	private Date monthOfYear;
	/**
	 * 收支标示:1,收入，2：支出
	 */
	@Column(name="income_expend_flag",length=1)
	private String incomeExpendFlag;
	@Column(name="obj_id")
	private Long objId;
	/**
	 * 0501：学杂费，0502：其他贷款
	 */
	@Column(name="obj_code")
	private String objCode;
	/**
	 * 类型：01:种植业,02:养殖业,03:工商业,04:其他,05:家庭生活消费
	 */
	@Column(name="obj_type",length=20)
	private String objType;
	@Column(name="obj_name",length=200)
	private String objName;
	@Column(name="obj_content")
	private String objContent;
	@Column(name="amt_money",precision=20,scale=2)
	private BigDecimal amtMoney;
	@Column(name="remark")
	private String remark;
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Date getMonthOfYear() {
		return monthOfYear;
	}
	public void setMonthOfYear(Date monthOfYear) {
		this.monthOfYear = monthOfYear;
	}
	public String getIncomeExpendFlag() {
		return incomeExpendFlag;
	}
	public void setIncomeExpendFlag(String incomeExpendFlag) {
		this.incomeExpendFlag = incomeExpendFlag;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public String getObjCode() {
		return objCode;
	}
	public void setObjCode(String objCode) {
		this.objCode = objCode;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getObjContent() {
		return objContent;
	}
	public void setObjContent(String objContent) {
		this.objContent = objContent;
	}
	public BigDecimal getAmtMoney() {
		return amtMoney;
	}
	public void setAmtMoney(BigDecimal amtMoney) {
		this.amtMoney = amtMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
