package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgRole;

public interface EcOrgRoleDao extends PagingAndSortingRepository<EcOrgRole, Long>,
		JpaSpecificationExecutor<EcOrgRole> {
	
	@Query(value = "select * from ec_org_role where role_num = ?1", nativeQuery = true)
	EcOrgRole findByRoleNum(String roleNum);

	@Query(value = "select * from ec_org_role eor, ec_orgrolemenu eorm where role_num = ?1", nativeQuery = true)
	List<EcFunctiontreenode> findFunctionByRoleId(String roleNum);

}
