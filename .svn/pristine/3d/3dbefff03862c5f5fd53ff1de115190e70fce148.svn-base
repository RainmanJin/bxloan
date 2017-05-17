package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CommonInfo;

public interface CommonInfoDao extends PagingAndSortingRepository<CommonInfo, Long>,JpaSpecificationExecutor<CommonInfo> {
	@Query("select ci from CommonInfo ci where ci.projectId = ?1 and ci.type = ?2")
	List<CommonInfo> findByProjectIdAndType(Long projectId, String type);
}
