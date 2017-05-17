package com.coamctech.bxloan.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * 业务凭证单据列表
 * @author WallenHeng
 *
 */
@Entity
@Table(name = "TALLY_CERTIFICATE", schema = GlobalConstants.WD_SCHEMA)
public class TallyCertificate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2445849843883755061L;
	/**
	 * 待处理业务凭证单据列表ID
	 */
	@SequenceGenerator(name = "generator", sequenceName="SEQ_TALLY_CERTIFICATE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "TALLY_CERTIFICATE_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long tallyCertificateId;
	/**
	 * 单据编号
	 */
	@Column(name = "BILL_CD")
	private String billCd;
	/**
	 * 单据描述
	 */
	@Column(name = "BILL_DESC")
	private String billDesc;
	/**
	 * 机构编号
	 */
	@Column(name = "ORG_CD")
	private String orgCd;
	/**
	 * 客户编号
	 */
	@Column(name = "CUST_CD")
	private String custCd;
	/**
	 * 合同编号
	 */
	@Column(name = "CONT_CD")
	private String contCd;
	@Column(name = "BILL_STS")
	private String billSts;
	@Column(name = "SYS_CD")
	private String sysCd;
	@Column(name = "SYS_NM")
	private String sysNm;
	@Column(name = "BUSI_TYP_CD")
	private String busiTypCd;
	@Column(name = "BUSI_TYP_NM")
	private String busiTypNm;
	@Column(name = "BUSI_DT")
	private Date busiDt;
	@Column(name = "IF_FEED")
	private Integer ifFeed;
	@Column(name = "can_Ref")
	private Integer canRef;
	@Column(name = "BILL_URL")
	private String billUrl;
	@Column(name = "PM_PRN")
	private String pmPrn;
	@Column(name = "SND_DT")
	private Date sndDt;
	@Column(name = "SND_PRN")
	private String sndPrn;
	@Column(name = "ACC_DT")
	private Date accDt;
	@Column(name = "ACC_PRN")
	private String accPrn;
	@Column(name = "REJ_DT")
	private Date rejDt;
	@Column(name = "REJ_PRN")
	private String rejPrn;
	@Column(name = "REJ_CAUSE")
	private String rejCause;
	@Column(name = "SND_DT_X")
	private Date sndDtX;
	@Column(name = "SND_PRN_X")
	private String sndPrnX;
	@Column(name = "ACC_DT_X")
	private Date accDtX;
	@Column(name = "ACC_PRN_X")
	private String accPrnX;
	@Column(name = "REJ_DT_X")
	private Date rejDtX;
	@Column(name = "REJ_PRN_X",length=50)
	private String rejPrnX;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_DATE")
	private Date createDate;
	/**
	 * 更新时间
	 */
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
	/**
	 * 默认：正常合同 1:资产转让2:授信放款
	 */
	@Column(name = "DIS_FLAG")
	private Integer disFlag;
	
	public Long getTallyCertificateId() {
		return tallyCertificateId;
	}
	public void setTallyCertificateId(Long tallyCertificateId) {
		this.tallyCertificateId = tallyCertificateId;
	}
	public String getBillCd() {
		return billCd;
	}
	public void setBillCd(String billCd) {
		this.billCd = billCd;
	}
	public String getBillDesc() {
		return billDesc;
	}
	public void setBillDesc(String billDesc) {
		this.billDesc = billDesc;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCustCd() {
		return custCd;
	}
	public void setCustCd(String custCd) {
		this.custCd = custCd;
	}
	public String getContCd() {
		return contCd;
	}
	public void setContCd(String contCd) {
		this.contCd = contCd;
	}
	public String getBillSts() {
		return billSts;
	}
	public void setBillSts(String billSts) {
		this.billSts = billSts;
	}
	public String getSysCd() {
		return sysCd;
	}
	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
	public String getSysNm() {
		return sysNm;
	}
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}
	public String getBusiTypCd() {
		return busiTypCd;
	}
	public void setBusiTypCd(String busiTypCd) {
		this.busiTypCd = busiTypCd;
	}
	public String getBusiTypNm() {
		return busiTypNm;
	}
	public void setBusiTypNm(String busiTypNm) {
		this.busiTypNm = busiTypNm;
	}
	public Date getBusiDt() {
		return busiDt;
	}
	public void setBusiDt(Date busiDt) {
		this.busiDt = busiDt;
	}
	public Integer getIfFeed() {
		return ifFeed;
	}
	public void setIfFeed(Integer ifFeed) {
		this.ifFeed = ifFeed;
	}
	public Integer getCanRef() {
		return canRef;
	}
	public void setCanRef(Integer canRef) {
		this.canRef = canRef;
	}
	public String getBillUrl() {
		return billUrl;
	}
	public void setBillUrl(String billUrl) {
		this.billUrl = billUrl;
	}
	public String getPmPrn() {
		return pmPrn;
	}
	public void setPmPrn(String pmPrn) {
		this.pmPrn = pmPrn;
	}
	public Date getSndDt() {
		return sndDt;
	}
	public void setSndDt(Date sndDt) {
		this.sndDt = sndDt;
	}
	public String getSndPrn() {
		return sndPrn;
	}
	public void setSndPrn(String sndPrn) {
		this.sndPrn = sndPrn;
	}
	public Date getAccDt() {
		return accDt;
	}
	public void setAccDt(Date accDt) {
		this.accDt = accDt;
	}
	public String getAccPrn() {
		return accPrn;
	}
	public void setAccPrn(String accPrn) {
		this.accPrn = accPrn;
	}
	public Date getRejDt() {
		return rejDt;
	}
	public void setRejDt(Date rejDt) {
		this.rejDt = rejDt;
	}
	public String getRejPrn() {
		return rejPrn;
	}
	public void setRejPrn(String rejPrn) {
		this.rejPrn = rejPrn;
	}
	public String getRejCause() {
		return rejCause;
	}
	public void setRejCause(String rejCause) {
		this.rejCause = rejCause;
	}
	public Date getSndDtX() {
		return sndDtX;
	}
	public void setSndDtX(Date sndDtX) {
		this.sndDtX = sndDtX;
	}
	public String getSndPrnX() {
		return sndPrnX;
	}
	public void setSndPrnX(String sndPrnX) {
		this.sndPrnX = sndPrnX;
	}
	public Date getAccDtX() {
		return accDtX;
	}
	public void setAccDtX(Date accDtX) {
		this.accDtX = accDtX;
	}
	public String getAccPrnX() {
		return accPrnX;
	}
	public void setAccPrnX(String accPrnX) {
		this.accPrnX = accPrnX;
	}
	public Date getRejDtX() {
		return rejDtX;
	}
	public void setRejDtX(Date rejDtX) {
		this.rejDtX = rejDtX;
	}
	public String getRejPrnX() {
		return rejPrnX;
	}
	public void setRejPrnX(String rejPrnX) {
		this.rejPrnX = rejPrnX;
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
	public Integer getDisFlag() {
		return disFlag;
	}
	public void setDisFlag(Integer disFlag) {
		this.disFlag = disFlag;
	}

}
