package com.coamctech.bxloan.service.approval;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;

public interface ApprovalRepayPlanService {
	Page<Object[]> searchRepayPlanList(Integer pageNumber, Integer pageSize,
			String projectNo, Long approvalId);

	void insertRepayPlanList(Long projectId, BigDecimal applyAmt,
			Integer applyTerm, String applyTermUnit, Date startDate,
			Integer monthDate, Integer repayDate) throws Exception;

	ApprovalHistoryRepayPlan getRepayPlan(Long id);

	void deleteRepayPlan(Long id);

	void saveRepayPlan(ApprovalHistoryRepayPlan form);
	
	/**检查自定义还款日是否存在*/
	boolean checkRepayDateIsExist(ApprovalHistoryRepayPlan form);

	void deleteRepayPlanByProjectId(Long projectId);
}
