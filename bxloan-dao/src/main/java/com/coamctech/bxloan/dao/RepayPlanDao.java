package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayPlan;

public interface RepayPlanDao extends
		PagingAndSortingRepository<RepayPlan, Long>,
		JpaSpecificationExecutor<RepayPlan> {
	/**
	 * 查询还款计划
	 * 
	 * @param projectId
	 *            项目id
	 * @return
	 */
	@Query("FROM RepayPlan rp WHERE rp.projectId= ?1  order by rp.repayDate")
	List<RepayPlan> findListByProjId(Long projectId);

	/**
	 * 查询某条业务的还款计划总额
	 * 
	 * @param projectId
	 * @return
	 */
	@Query(value = "select sum(t.repay_amt) from REPAY_PLAN t where t.project_id = ?1", nativeQuery = true)
	BigDecimal findTotalByProjectId(Long projectId);

	/**
	 * 查询某条业务的还款计划总额
	 * 
	 * @param projectId
	 * @return
	 */
	@Query(value = "select sum(t.repay_amt) from approval_history_repay_plan t where t.project_no = ?1 and t.approval_history_id = 0", nativeQuery = true)
	BigDecimal findTotalByProjectNo(String projectNo);

	/**
	 * 批量删除某条业务的还款计划
	 * 
	 * @param projectId
	 */
	void deleteByProjectId(Long projectId);

	/**
	 * 根据业务No批量删除某条业务的还款计划
	 * 
	 * @param projectNo
	 */
	void deleteByProjectNo(String projectNo);

	/**
	 * 根据projectNo获取还款计划列表
	 * 
	 * @param projectNo
	 * @return
	 */
	List<RepayPlan> findByProjectNo(String projectNo);

	@Query(value = "select sum(t.repay_amt) from REPAY_PLAN_TEMP t where t.project_no is null", nativeQuery = true)
	BigDecimal findTotalForCounter();
	
	void deleteByProjectIdAndRepayAmtAndRepayDate(Long projectId, BigDecimal repayAmt, Date repayDate);
}
