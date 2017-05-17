package com.coamctech.bxloan.service.model.datatransend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.excel.upload.ExcelImportBaseBo;

/**   
 * 类名称：DataImportVo
 * 类描述 ：数据导入信息vo类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class DataImportVo extends ExcelImportBaseBo implements Serializable{
	private static final long serialVersionUID = -115091197351720725L;
	private Long excelInId;		    //id
	private String contractNum;		//合同编号
	private Date repayDate;			//还款时间
	private BigDecimal repayAmt; 	//还款金额
	private String orgId; 			//机构id
	private Date insertDate;		//导入时间
	private String insertUser;		//操作人
	private String yqFlag; 			//转出钱是否逾期标志（1、是  null 否）
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
	@Override
	public String toString() {
		return "ExcelInBo [excelInId=" + excelInId + ", contractNum="
				+ contractNum + ", repayDate=" + repayDate + ", repayAmt="
				+ repayAmt + ", orgId=" + orgId + ", insertDate=" + insertDate
				+ ", insertUser=" + insertUser + "]";
	}
}
