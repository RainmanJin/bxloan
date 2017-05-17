package com.coamctech.bxloan.service.model.datatransend;

import java.io.Serializable;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;
/**   
 * 类名称：RepayInfoExportVo
 * 类描述 ：导出还款信息vo类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class RepayInfoExportVo implements Serializable {
	private static final long serialVersionUID = -3012267806550186554L;
	private String contractNum;    //合同编号
	private String principalAmt;   //还款金额
	private String repayDate;      //还款日期
	
	public RepayInfoExportVo() {
		super();
	}

	public RepayInfoExportVo(Object[] objs){
		super();
		int i = 0;
		this.contractNum = CommonHelper.toStr(objs[i++]);
		this.principalAmt = CommonHelper.toStr(objs[i++]);
		this.repayDate = CommonHelper.date2Str((Date) objs[i++], CommonHelper.DF_DATE);
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getPrincipalAmt() {
		return principalAmt;
	}

	public void setPrincipalAmt(String principalAmt) {
		this.principalAmt = principalAmt;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	
}
