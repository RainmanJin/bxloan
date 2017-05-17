package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ProjectAssurerInfo;

public interface ProjectAssurerInfoDao extends PagingAndSortingRepository<ProjectAssurerInfo, Long>,JpaSpecificationExecutor<ProjectAssurerInfo>{

	List<ProjectAssurerInfo> findByProjectId(Long projectId);
	
	
	/**
	 * 查询该业务已存在的保证人
	 * @param projectId
	 * @param partyIds
	 * @return
	 */
	@Query("FROM ProjectAssurerInfo pai where pai.projectId=?1 AND pai.partyId in (?2) ORDER by pai.projectAssurerInfoId")
	List<ProjectAssurerInfo> findByProjIdAndPartyIds(Long projectId,List<Long> partyIds);
	
	@Modifying
	@Query("delete ProjectAssurerInfo pai where pai.projectId=?1 and pai.assurerSource=?2")
	int deleteProjectAssurerInfoOfUgn(Long projectId,String assurerSource);
	////授信下借款查出授信合同余授信下借款合同的保证人信息
	@Query("FROM ProjectAssurerInfo pai where pai.projectId in (?1,?2)")
	List<ProjectAssurerInfo> findByProjectIdAndParentProId(Long projectId,Long parentProId);
}