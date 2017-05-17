package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.EcOrgPersonconnrole;

public interface EcOrgPersonconnroleDao extends PagingAndSortingRepository<EcOrgPersonconnrole, String>,
		JpaSpecificationExecutor<EcOrgPersonconnrole> {
	
	@Modifying
	@Query(value = "delete from ec_org_personconnrole eopr where eopr.roleid = (select eor.id from ec_org_role eor where eor.role_num = ?1) and eopr.personid = (select eop.id from ec_org_person eop where eop.usernum= ?2)", nativeQuery = true)
	void deleteByRolenum(String rolenum, String usernum);

	@Query(value = "select * from ec_org_personconnrole eopr where eopr.personid= ?1", nativeQuery = true)
	List<EcOrgPersonconnrole> findEcOrgPersonconnroleByPersonId(Long id);
	@Query(value = "select eopr from EcOrgPersonconnrole eopr where eopr.id.personid= ?1 and (eopr.orgid = ?2 or ?2 is null) and (eopr.id.deptid = ?3 or ?3 is null) and (eopr.id.roleid = ?4 or ?4 is null)")
	List<EcOrgPersonconnrole> findPersonConnRoleList(Long curUserId, Long orgId, Long deptId, Long roleId);
}
