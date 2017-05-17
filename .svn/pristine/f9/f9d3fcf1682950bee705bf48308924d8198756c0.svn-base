package com.coamctech.bxloan.service.afterloan;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.service.model.afterloan.ContractListParams;
import com.coamctech.bxloan.service.model.afterloan.LrFeeRegisterVo;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoParams;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoVo;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;

/**
 *	贷款回收
 */
public interface LoanRecoverService {
	/**
	 * @param id 合同id
	 * @return
	 */
	Contract findContract(Long id);
	/**
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	Page<Object[]> findContractPage(int pageSize, int pageNum,String userId,ContractListParams params);
	/**
	 * 查询还款记录
	 * @param contractId
	 * @param contractNum
	 * @param repaymentStatusCd
	 * @param valid
	 * @return
	 * 
	 */
	List<RepayLoan> findRepayLoanList(Long contractId,String contractNum,String repaymentStatusCd,String valid);
	
	/**
	 * <b>查询费用登记信息</b>
	 * @since 2014-10-22
	 * 
	 * @param contractId
	 * @return
	 */
	LrFeeRegisterVo findFeeRegisterInfo(Long contractId,Long curOrgId);
	
	/**
	 * <b>保存费用登记信息</b>
	 * @since 2014-10-22
	 * 
	 * 
	 * @param lrVo
	 */
	void saveFeeRegisterInfo(LrFeeRegisterVo lrVo);
	/**
	 * 还款时的信息校验
	 * @param contractId	合同id
	 * @param overdueFlag	true：逾期；false：未逾期，正常
	 * @return
	 */
	MsgResult findRepayInfoBeforeCheck(LrRepayInfoParams lrParams);
	
	/**
	 * 查询还款信息(用于正常还款和逾期还款页面展示)
	 * @param lrParams	
	 * @return
	 */
	LrRepayInfoVo findRepayInfo(LrRepayInfoParams lrParams);
	
	/**
	 * 还款前校验
	 * @param lrParams
	 * @return
	 */
	MsgResult saveRepayLoanBeforeCheck(LrRepayInfoParams lrParams);
	/**
	 * 
	 * 保存还款信息
	 * @param lrParams
	 * @return
	 */
	void saveRepayLoan(LrRepayInfoParams lrParams);
	
	/**
	 * 查询还款记录
	 * @param pageSize
	 * @param pageNum
	 * @param contractId
	 * @return
	 */
	Page<Object[]> findRepayLoanList(int pageSize, int pageNum,Long contractId);
	
	/**
	 * 查询还款计划明细
	 * @param pageSize
	 * @param pageNum
	 * @param contractId
	 * @return
	 */
	Page<Object[]> findRepayingPlanInfoList(int pageSize, int pageNum,Long contractId);
	/**
	 * 查询还款计划-逾期试算
	 * @param contractId
	 * @param projectId
	 * @param payLoanId
	 * @return LrRepayInfoVo
	 * */
	LrRepayInfoVo findRepayingPlanDetailCountData(Long contractId, Long project, Long payLoanId);
	
}
