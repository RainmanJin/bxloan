package com.coamctech.bxloan.webservices;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 微贷账务接口
 *
 */
@WebService
public interface BxloanAccountingServices {
	/**
	 * 业务凭证入账接口
	 * @param billCd	单据编号
	 * @param busiDt	业务时间（yyyyMMdd）
	 * @param operator	操作人
	 * @return
	 */
	public String dealBill(@WebParam(name="billCd")String billCd,@WebParam(name="operator")String operator,@WebParam(name="busiDt")String busiDt);
	
	/**
	 * 业务凭证退单接口
	 * @param billCd	单据编号
	 * @param rejCause	退单原因
	 * @param operator	操作人
	 * @return
	 */
	public String cancBill(@WebParam(name="billCd")String billCd,@WebParam(name="operator") String operator, @WebParam(name="rejCause")String backCause);
}
