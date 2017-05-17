package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ProjectPawnInfo;

public interface ProjectPawnInfoDao extends
		PagingAndSortingRepository<ProjectPawnInfo, Long>,
		JpaSpecificationExecutor<ProjectPawnInfo> {

	List<ProjectPawnInfo> findByProjectId(Long projectId);

	List<ProjectPawnInfo> findByGuarantyId(Long guarantyId);

	// 获取某一业务的抵质押关联表中指定的抵押或质押数据
	@Query("select p from ProjectPawnInfo p where p.projectId = ?1 and p.guaranteeType = ?2")
	List<ProjectPawnInfo> findOneGuaranteeTypeByProjectId(Long projectId,
			String guaranteeType);

	@Query("select p from ProjectPawnInfo p where p.projectId != ?1 and p.pawnState = ?2 and p.guarantyId = ?3")
	List<ProjectPawnInfo> findByProjectIdAndPawnStateAndGuarantyId(
			Long projectId, String codeVal, Long guarantyId);
	//授信下借款查出授信合同余授信下借款合同的抵质押信息
	@Query("select p from ProjectPawnInfo p where p.projectId in(?1,?2)")
	List<ProjectPawnInfo> findByProjectIdAndParentProId(Long projectId,Long parentProId);
}
