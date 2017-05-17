package com.coamctech.bxloan.service.pettyloan;

import java.util.Date;

/**
 *	业务单据凭证服务
 */
public interface LoanTallyCertificateService {
	/** * 入账 */
	public static final String STATUS_ENTER="1";
	/** * 退单 */
	public static final String STATUS_BACK="2";
	/**
	 * 处理账单
	 * @param billCd	业务凭证编号
	 * @param opertor	操作人
	 * @param busiDt	业务时间
	 * @param status	1：入账，2：退单
	 * @param backCause	退单原因（退单时不可为空）
	 */
	public void dealBill(String billCd,String operator,Date busiDate,String status,String backCause);
	
	
	/**
	 * 更新账单及单据凭证
	 * @param billCd	业务凭证编号
	 * @param opertor	操作人
	 */	
	public void updateBillOfFlushes(String billCd,String operator);
}
