package com.coamctech.bxloan.service.repayplan;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.model.excel.ExRepayingPlanVo;
import com.coamctech.bxloan.service.model.statistics.BizRepayingPlanVo;

public interface RepayPlanService {
	/**
	 * 还款计划列表查询
	 * 
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	List<RepayingPlanDetail> findRepayPlanBySearch(int i, Integer pageSize, List<Object> params);
	/**
	 * 查询还款情况（分页）
	 * @param pageNumber
	 * @param pageSize
	 * @param contractId
	 * @return
	 */
	Page<BizRepayingPlanVo> findRepayPlanByCondition(Integer pageNumber, Integer pageSize, String contractId);
	/**
	 * 查询还款情况（不分页）
	 * @param pageNumber
	 * @param pageSize
	 * @param contractId
	 * @return
	 */
	List<BizRepayingPlanVo> findRepayPlanByConditionNotPaging(String contractId);
	/**
	 * 查询还款情况参数
	 * @param id
	 * @return
	 */
	List<Object> findRepayPlanParams(String contractId);
	
	
	/**
	 * 根据合同id查询还款计划（Excel导出）
	 * @param contractId
	 * @return
	 */
	ExRepayingPlanVo findRepayingPlanForExcelByContractId(Long contractId);
}











