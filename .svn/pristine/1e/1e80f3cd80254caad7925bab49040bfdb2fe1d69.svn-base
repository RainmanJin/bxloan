package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.coamctech.bxloan.entity.EcFunctiontreenode;

public interface EcFunctiontreenodeDao extends PagingAndSortingRepository<EcFunctiontreenode, Long>,
		JpaSpecificationExecutor<EcFunctiontreenode> {
	
	@Query("select eft from EcFunctiontreenode eft where eft.sysCd ='3' order by eft.id")
	List<EcFunctiontreenode> findAllItems();

	@Query(value = "select ef.* from ec_functiontreenode ef where ef.id in (select orm.menu_id from ec_orgrolemenu orm where orm.role_id= ?1)", nativeQuery = true)
	List<EcFunctiontreenode> findFunctionByRoleId(Long id);

	@Query("select distinct efn from EcFunctiontreenode efn ,EcOrgrolemenu eom ,EcOrgPersonconnrole eopr where eopr.id.personid = :id and"
			+ " eopr.id.roleid = eom.id.roleId and efn.id = eom.id.menuId and efn.state=1 and efn.sysCd in(1,2) and eopr.orgid=:orgid order by efn.id")
	List<EcFunctiontreenode> findMenusByPermission(@Param("id") Long id, @Param("orgid") Long orgid);

}
