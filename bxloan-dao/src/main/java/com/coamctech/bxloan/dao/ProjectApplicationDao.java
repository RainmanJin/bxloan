package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ProjectApplication;

public interface ProjectApplicationDao extends
		PagingAndSortingRepository<ProjectApplication, Long>,
		JpaSpecificationExecutor<ProjectApplication> {

	/**
	 * Description 根据customerNum查询具体的产品定价信息
	 * 
	 * @return ProductPrice
	 */
	@Query("select pro from ProjectApplication pro where pro.customerNum = ?1")
	ProjectApplication getProjectApplicationByCustomer(String customerNum);

	ProjectApplication findProjectApplicationByProjectId(Long projectId);

	@Query("from ProjectApplication p where p.projectNo = ?1")
	ProjectApplication getProjectApplicationByProjectNo(String projectNo);

	/**
	 * 根据prijectId修改prijectStatus
	 * 
	 * @param projectId
	 * @param projectStatus
	 * @author xc
	 */
	@Modifying
	@Query("UPDATE ProjectApplication set projectStatus=?2 WHERE workflowId=?1")
	void updateProjectStatusByWorkflowId(Long workflowId, String projectStatus);
	
	@Query("FROM ProjectApplication WHERE workflowId=?1")
	ProjectApplication findProjectApplicationByWorkflowId(Long workflowId);

	/**
	 * 获取客户当前未结束的申请
	 * 
	 * @param partyId
	 * @return
	 */
	@Query("from ProjectApplication pa where pa.projectStatus in (0,1,2) and pa.partyId = ?1")
	List<ProjectApplication> findInFlowAppByPartyId(Long partyId);

	/**
	 * 获取客户当前未结束的授薪业务
	 * 
	 * @param partyId
	 * @return
	 */
	@Query(value = "select * from credit_application ca where ca.project_status in (0,1,2) and ca.party_id = ?1", nativeQuery = true)
	List<Object[]> findInFlowCreditAppByPartyId(Long partyId);
	
	/**
	 * 通过workflowId获取projectId
	 * @param workflowId
	 * @return projectId Long
	 */
	@Query(value = "select pa.projectId from ProjectApplication pa where pa.workflowId = ?1")
	Long findProjectByWorkflowId(Long workflowId);
	
	/**
	 * 查找project对应的partyId
	 * @param projectId
	 * @return partyId
	 */
	@Query(value = "select pa.partyId from ProjectApplication pa where pa.projectId = ?1")
	Long findPartyIdByProjectId(Long projectId);
	
	/**
	 * 根据projectId修改projectStatus
	 * 
	 * @param projectId 业务ID
	 * @param projectStatus 业务状态
	 * @author wangyawei
	 */
	@Modifying
	@Query(value = "update project_application pa set pa.project_status=?2 where pa.project_id=?1", nativeQuery = true)
	void updateProjectStatusByProjectId(Long projectId, String projectStatus);
}
