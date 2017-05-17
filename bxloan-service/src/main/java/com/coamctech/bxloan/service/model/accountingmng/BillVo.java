package com.coamctech.bxloan.service.model.accountingmng;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.utils.DateTools;

/**
 *	单据Vo
 */
public class BillVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7808058282799676137L;
	/**
	 * 单据凭证id
	 */
	private Long tcId;
	/**
	 * 单据编号
	 */
	private String billCd;
	private String billDesc;
	private String contCd;
	private String orgCd;
	private String billState;
	private String busiTypNm;
	private Date busiDt;
	private String busiDtStr;
	private String billUrl;
	
	public BillVo(Object[] input) {
		super();
		this.tcId=toLong(input[0]);
		this.billCd=str(input[1]);
		this.billDesc=str(input[2]);
		this.orgCd=str(input[3]);
		this.contCd=str(input[4]);
		this.billState=str(input[5]);
		this.busiTypNm=str(input[6]);
		this.busiDt=(Date)input[7];
		this.busiDtStr=DateTools.dateToString(busiDt, "yyyy-MM-dd");
		this.billUrl=str(input[8]);
	}
	private String str(Object obj){
		return (obj!=null&&obj instanceof String)?StringUtils.trimToEmpty(String.valueOf(obj)):StringUtils.EMPTY;
	}
	private Long toLong(Object obj){
		return (obj!=null&&obj instanceof BigDecimal)?Long.parseLong(String.valueOf(obj)):null;
	}
	public String getBillCd() {
		return billCd;
	}
	public String getBillDesc() {
		return billDesc;
	}
	public String getContCd() {
		return contCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public String getBillState() {
		return billState;
	}
	public String getBusiTypNm() {
		return busiTypNm;
	}
	public Date getBusiDt() {
		return busiDt;
	}
	public void setBusiDt(Date busiDt) {
		this.busiDt = busiDt;
	}
	public String getBusiDtStr() {
		return busiDtStr;
	}
	public void setBusiDtStr(String busiDtStr) {
		this.busiDtStr = busiDtStr;
	}
	public void setBillCd(String billCd) {
		this.billCd = billCd;
	}
	public void setBillDesc(String billDesc) {
		this.billDesc = billDesc;
	}
	public void setContCd(String contCd) {
		this.contCd = contCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public void setBillState(String billState) {
		this.billState = billState;
	}
	public void setBusiTypNm(String busiTypNm) {
		this.busiTypNm = busiTypNm;
	}
	public Long getTcId() {
		return tcId;
	}
	public void setTcId(Long tcId) {
		this.tcId = tcId;
	}
	public String getBillUrl() {
		return billUrl;
	}
	public void setBillUrl(String billUrl) {
		this.billUrl = billUrl;
	}
	

}
