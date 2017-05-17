package com.coamctech.bxloan.service.common;

import java.util.Date;

/**   
 * 类名称：SysCommonService<br/>
 * 类描述 ：<br/>
 * 创建人: hwl<br/>
 * 创建时间：2015年5月20日 下午2:51:54  <br/>
 * 修改人：hwl<br/>
 * 修改时间：2015年5月20日 下午2:51:54  <br/>
 * 修改备注：<br/>
 * 版本： V1.0<br/>
 */

public interface SysCommonService {
	
	/**
	 * 计算合同到期日期
	 * @param payLoanDate	放款日期
	 * @param term			合同期限
	 * @param termUnit		合同期限单位
	 * @since 2015-05-20 14:55
	 * @return
	 */
	Date computeExpirationDate(Date payLoanDate,Integer term,String termUnit);
	/**
	 * 获取管理员等级
	 * @param personId 用户id
	 * @author:gph
	 * @createTime:2015年5月29日
	 * @return R00011:一级管理员；R00012：二级管理员
	 */
	String getAdminLevels(Long personId);
}
