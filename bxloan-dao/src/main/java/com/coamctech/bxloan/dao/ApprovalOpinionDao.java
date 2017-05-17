package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ApprovalOpinion;

public interface ApprovalOpinionDao extends JpaSpecificationExecutor<ApprovalOpinion>,
PagingAndSortingRepository<ApprovalOpinion, Long> {
	/**
	 * 查询指定环节审批建议
	 * @param projectId 业务Id
	 * @param taskStageCode	环节编号
	 * @return
	 */
	@Query("from ApprovalOpinion ao where ao.projectId=?1 and ao.taskStageCode=?2 order by ao.signtime DESC")
	List<ApprovalOpinion> findApprovalOpinionList(Long projectId,String taskStageCode);
}
