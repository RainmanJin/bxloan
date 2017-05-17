package com.coamctech.bxloan.entity;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

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
@Table(name = "DATA_TRAN_RECORD", schema = WD_SCHEMA)
public class DataTranRecord implements java.io.Serializable {
	private static final long serialVersionUID = -4733124739763343064L;
	/**主键ID*/
	@Id
	@SequenceGenerator(name = "SEQ_DATA_TRAN_RECORD", sequenceName = GlobalConstants.WD_SCHEMA+".SEQ_DATA_TRAN_RECORD", allocationSize = 1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DATA_TRAN_RECORD") 
	@Column(name = "DATA_TRAN_RECORD_ID", unique = true, nullable = false)
	private Long recordId;
	
	/**操作人名称*/
	@Column(name = "OPERATOR_NAME", nullable = false, length = 50)
	private String operatorName;
	
	/**操作人机构*/
	@Column(name = "LOG_ORG_NAME", nullable = false, length = 100)
	private String logOrgName;
	
	/**开始日期*/
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;
	
	/**结束日期*/
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;
	
	/**操作时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATE_TIME", nullable = false)
	private Date operateTime;
	
	/** default constructor */
	public DataTranRecord() {
		super();
	}
	
	public DataTranRecord(Object[] objs){
		super();
		int i = 0;
		this.recordId = CommonHelper.toLong(objs[i++]); 
		this.operatorName = CommonHelper.toStr(objs[i++]);
		this.logOrgName = CommonHelper.toStr(objs[i++]);
		this.startDate = CommonHelper.toDate(objs[i++]);
		this.endDate = CommonHelper.toDate(objs[i++]);
		this.operateTime = CommonHelper.toDate(objs[i++]);
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getLogOrgName() {
		return logOrgName;
	}

	public void setLogOrgName(String logOrgName) {
		this.logOrgName = logOrgName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
}