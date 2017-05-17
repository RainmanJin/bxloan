package com.coamctech.bxloan.service.pettyloan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.coamctech.bxloan.entity.LossProvision;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;


/**
 *	贷款公用
 */
public interface LoanCommonServeice {
	
	/**
	 * 获取同类型的多个code值
	 * @param codeType
	 * @param key
	 * @return
	 */
	List<String> getCodeValList(String codeType,String... keys);
	/**
	 * 获取code值
	 * @param codeType
	 * @param key
	 * @return
	 */
	String getCodeVal(String codeType,String key);
	/**
	 * 获取贷款状态值
	 * @param loanStatus
	 * @return
	 */
	String getCodeVal(LoanStatus loanStatus);
	/**
	 * 获取code名称
	 * @param codeType
	 * @param value
	 * @return
	 */
	String getCodeName(String codeType,String value);
	/**
	 * 系统参数
	 * @param sysParamType
	 * @param sysParamKey
	 * @return
	 */
	String getSysParamVal(String sysParamType,String sysParamKey);
	
	/**
	 * 查询自定义还款计划的产品配置
	 * @return
	 */
	Set<String> getProductConfForCustRepayPlan();
	/**
	 * 获取贷款状态
	 * @param val
	 * @return
	 */
	LoanStatus getLoanStatus(String codeType,String val);
	
	/**
	 * 机构的账务联动接口是否打开
	 * @param orgId
	 * @param key
	 * @return
	 */
	boolean isOpenOfOrgInterface(String orgId, String key);
	
	
	/**
	 * 获取账务交易编号（单据编号）
	 * @param bussType
	 * @param bchId
	 * @param customerType
	 * @return
	 */
	String getTransNo(String bussType, String bchId, int customerType);
	
	
	/**
	 * 获取账务信息表最大序号
	 * @param txrefNo	业务编号
	 * @return
	 */
	Long getMaxSeqno(String txrefNo);
	
	/**
	 * 获取用户姓名
	 * @param customerNum	客户编号
	 * @return
	 */
	String getCustomerName(String customerNum);
	
	/**
	 * 获取机构名称
	 * @param orgId	机构id
	 * @return
	 */
	String getOrgDeptmentName(Long orgId);
	
	/**
	 * 单据凭证URL
	 * @param flag	1:放款,2:还款
	 * @return
	 */
	String findBillUrl(int flag);
	
	/**
	 * 返回还款计划最后一次的还款日期
	 * @param contractId	合同id
	 * @return
	 */
	Date getRepayDateOfLastRepayingPlan(Long contractId);
	
	
	//TODO 相关状态判断
	/**
	 * 合同已发起提前还款，正在流程中
	 * @param contractNum	合同编号
	 * @return
	 */
	boolean isAdvanceRepayOfContract(String contractNum);
	
	/**
	 * 合同正在展期
	 * @param contractNum
	 * @return
	 */
	boolean isExtensionOfContact(String contractNum);
	
	/**
	 * 合同正在核销
	 * @param contractNum
	 * @return
	 */
	boolean isVerifCancelOfContact(String contractNum);
	
	/**
	 * 合同未入账或冲正未入账
	 * @param contractNum
	 * @return
	 */
	boolean isNoneEnterOfContact(String contractNum);
	
	
	/**
	 * 判断是否是该合同的最后一条单据（单据冲销依次进行）
	 * @param billCd
	 * @param contractNum
	 * @return
	 */
	boolean isLastTallyCertificate(String billCd,String contractNum);
	
	/**
	 * 是否跨年冲正
	 * @param billCd
	 * @param contractNum
	 * @return
	 */
	boolean isAcrossYearFlushes(String billCd,String contractNum);
	
	/**
	 * 合同性质校验
	 * @param contractNum
	 * @return true：合同做过展期或变更，已不是原合同，无法进行冲正;false :可以冲正
	 */
	boolean isAllowContractNature(String contractNum);
	/**
	 * 还款计划中出现还款日期小于贷款放款日期
	 * @param repayingPlanId	还款计划id
	 * @return
	 */
	boolean isLtDateRepayAndPay(Long repayingPlanId);
	/**
	 * 该合同，还款计划中的还款金额累计不等于放款金额累计
	 * @param repayingPlanId	还款计划id
	 * @return
	 */
	boolean isNotEqTotalAmtOfRepayAndPay(Long repayingPlanId,BigDecimal loanAmount);
	
	/**
	 * 查询损失计提（serialNum降序）
	 * @param orgIdOrContId	机构id或合同id
	 * @param objectDimensionType 1 ：按经办机构计提，2：按单笔合同计提
	 * @param transactionStatus	业务状态
	 * @return
	 */
	List<LossProvision> findLossProvisionList(Long orgIdOrContId,String objectDimensionType,String transactionStatus);
	
}
