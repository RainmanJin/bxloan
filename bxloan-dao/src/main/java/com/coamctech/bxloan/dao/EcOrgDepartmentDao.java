package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.coamctech.bxloan.entity.EcOrgDepartment;

public interface EcOrgDepartmentDao extends PagingAndSortingRepository<EcOrgDepartment, Long>,
		JpaSpecificationExecutor<EcOrgDepartment> {
	
	EcOrgDepartment findById(Long id);

	/**
	 * 查询当前登录用户有权限操作的机构
	 * 
	 */
	@Query("select distinct eod from EcOrgPerson eop, EcOrgDepartment eod, EcOrgPersonconnrole eopr where eop.id=eopr.id.personid and eopr.orgid=eod.id and eop.id=:id")
	List<EcOrgDepartment> findByPersonId(@Param("id") Long id);

	@Query("select e from EcOrgDepartment e where e.levels in (1,2) and e.state = 1")
	List<EcOrgDepartment> findByLevels();
	
	@Query("select t from EcOrgDepartment t where t.id <> '0' order by t.id")
	List<EcOrgDepartment> findAllItems();
   
	@Query("select t.name from EcOrgDepartment t where t.id = ?1 ")
	String findDeptNameById(Long id);
	/**
	 * @param level	1:总部，2：小贷公司，3：部门
	 * @return
	 */
	@Query("from EcOrgDepartment t where t.state=1 AND t.levels = ?1 ")
	List<EcOrgDepartment> findAllOrgList(int level);
	
	@Query("select t.parentdepartmentid from EcOrgDepartment t where t.id = ?1 ")
	Long findParentdepartmentidById(Long applyOrgId);
	
	@Query("select d from EcOrgDepartment d where d.parentdepartmentid='10001' and d.levels='2' and d.state='1' order by d.levels desc,name")
	List<EcOrgDepartment> getEcOrgDep();
	
	@Query("select t.orgAttr from EcOrgDepartment t where t.id = ?1 ")
	String findOrgAttrById(Long applyOrgId);
	
	@Query("select t.levels from EcOrgDepartment t where t.id = ?1 ")
	String findLevelsById(Long curLogOrgid);
	
	@Query (nativeQuery=true,value= "Select eod.name from contract c,ec_org_department eod where c.apply_org_id=eod.id and c.contract_id=?1 ")
	String findNameByContractId(Long contractId);
	
}
