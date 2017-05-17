package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.EcOrgrolemenu;

public interface EcOrgrolemenuDao extends PagingAndSortingRepository<EcOrgrolemenu, Long>,
		JpaSpecificationExecutor<EcOrgrolemenu> {
	
	@Query(value = "select * from ec_orgrolemenu orm where orm.role_id= ?1", nativeQuery = true)
	List<EcOrgrolemenu> findRoleMenuByRoleId(Long id);

	@Modifying
	@Query(value = "delete from ec_orgrolemenu orm where orm.role_id= ?1", nativeQuery = true)
	void deleteRoleMenuByRoleId(Long id);
}
